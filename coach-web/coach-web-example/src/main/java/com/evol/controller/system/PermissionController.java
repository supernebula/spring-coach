package com.evol.controller.permission;

import com.evol.constant.PermissionConstant;
import com.evol.controller.BaseController;
import com.evol.domain.dto.NodeTree;
import com.evol.domain.model.Permission;
import com.evol.domain.model.Role;
import com.evol.enums.ApiResponseEnum;
import com.evol.service.PermissionService;
import com.evol.service.RoleService;
import com.evol.service.UserService;
import com.evol.util.TreeUtil;
import com.evol.web.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import wiremock.org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/system/permission")
public class PermissionController extends BaseController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private PermissionService authPermissionService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private UserService userService;

	@RequestMapping({ "", "/index" })
	public String permissonList() {
		return "permission/permission";
	}

	@RequestMapping("/list")
	@ResponseBody
	public List<Permission> getPermissionList() {
		List<Permission> permissions = authPermissionService.getAllMenus();
		return permissions;
	}

	@RequestMapping("/add")
	@ResponseBody
	public ApiResponse addMenu(Permission authPermission) {
		String name="未知";
		if (PermissionConstant.TYPE_CATE.equals(authPermission.getType())) {
			name = "目录";
		}else if(PermissionConstant.TYPE_MENU.equals(authPermission.getType())){
			name = "菜单";
		} else if(PermissionConstant.TYPE_BUTTON.equals(authPermission.getType())){
			name = "按钮";
		}
		try {
			authPermission.setCreateTime(new Date());
			authPermissionService.savePermission(authPermission);
			return ApiResponse.success("新增" + name + "成功！");
		} catch (Exception e) {
			logger.error("新增{}失败", name, e);
			return ApiResponse.fail(ApiResponseEnum.USER_DEFINED_ERROR,"新增" + name + "失败，请联系网站管理员！");
		}
	}

	@RequestMapping("/update")
	@ResponseBody
	public ApiResponse update(Permission authPermission) {
		String name="未知";
		if (PermissionConstant.TYPE_CATE.equals(authPermission.getType())) {
			name = "目录";
		}else if(PermissionConstant.TYPE_MENU.equals(authPermission.getType())){
			name = "菜单";
		} else if(PermissionConstant.TYPE_BUTTON.equals(authPermission.getType())){
			name = "按钮";
		}
		try {
			authPermission.setUpdateTime(new Date());
			authPermissionService.updatePermission(authPermission);
			return ApiResponse.success("修改" + name + "成功！");
		} catch (Exception e) {
			logger.error("修改{}失败", name, e);
			return ApiResponse.fail(ApiResponseEnum.USER_DEFINED_ERROR ,"修改" + name + "失败，请联系网站管理员！");
		}
	}

	@RequestMapping("/delete")
	@ResponseBody
	public ApiResponse deletePerms(List<Integer> permIds) {
		try {
			this.authPermissionService.deletePerms(permIds);
			return ApiResponse.success("删除成功！");
		} catch (Exception e) {
			logger.error("权限删除失败", e);
			return ApiResponse.fail(ApiResponseEnum.USER_DEFINED_ERROR,"删除失败，请联系网站管理员！");
		}
	}

	@RequestMapping("/getPermission")
	@ResponseBody
	public ApiResponse getPermissionById(Integer permId) {
		try {
            Permission authPermission = this.authPermissionService.getPermissionById(permId);
			return ApiResponse.success(authPermission);
		} catch (Exception e) {
			logger.error("获取菜单信息失败", e);
			return ApiResponse.fail(ApiResponseEnum.USER_DEFINED_ERROR,"获取信息失败，请联系网站管理员！");
		}
	}

	@RequestMapping("/treeList")
	@ResponseBody
	public ApiResponse getPermissionTreeList() {
		List<NodeTree<Permission>> trees = new ArrayList<>();
		List<Permission> permissions = authPermissionService.getAllMenus();
		buildTrees(trees, permissions);
        NodeTree<Permission> authTree = TreeUtil.buildTree(trees);
		return ApiResponse.success(authTree);
	}

	@RequestMapping("/userTreeList")
	@ResponseBody
	public ApiResponse getUserPermissionTreeList() {
		List<NodeTree<Permission>> trees = new ArrayList<>();
		List<Role> roles = roleService.getRoleListByUser(this.getCurrentAccount().getAccountId());
		List<Integer> roleIds = roles.stream().map(Role::getId).collect(Collectors.toList());
		if (StpUtil.hasPermission("superadmin")) {
			List<Permission> permissions = authPermissionService
					.getAllUnionPermissionByType(PermissionConstant.TYPE_MENU);
			buildTrees(trees, permissions);
            NodeTree<Permission> authTree = TreeUtil.buildTree(trees);
			return ApiResponse.success(authTree);
		} else {
			List<Permission> permissions = authPermissionService.getUnionPermissionByType(roleIds,
                    PermissionConstant.TYPE_MENU);
			buildTrees(trees, permissions);
			NodeTree<Permission> authTree = TreeUtil.buildTree(trees);
			return ApiResponse.success(authTree);
		}

	}

	private void buildTrees(List<NodeTree<Permission>> trees, List<Permission> menus) {
		menus.forEach(menu -> {
            NodeTree<Permission> tree = new NodeTree<>();
			tree.setId(menu.getId().toString());
			tree.setParentId(menu.getPid() == null ? "0" : menu.getPid().toString());
			tree.setTitle(menu.getName());
			tree.setUrl(menu.getUrl());
			tree.setIcon(menu.getIcon());
			trees.add(tree);
		});
	}

	@RequestMapping("/checkMenuName")
	@ResponseBody
	public boolean checkMenuName(String name, String type, String oldName) {
		if (StringUtils.isNotBlank(oldName) && StringUtils.equalsIgnoreCase(name, oldName)) {
			return true;
		}
        Permission result = this.authPermissionService.findByNameAndType(name, type);
		return result == null;
	}

	@RequestMapping("/checkMenuCode")
	@ResponseBody
	public boolean checkMenuCode(String permCode, String opType, Integer menuId) {
		if (StringUtils.isBlank(permCode)) {
			return true;
		}
        Permission result = this.authPermissionService.findByPerm(permCode);
		if (opType.equalsIgnoreCase("add")) {
			return result == null;
		}
		if (null == result) {
			return true;
		}
		if (result.getId().equals(menuId)) {
			return true;
		}
		return false;

	}
}
