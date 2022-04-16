package com.evol.controller.system;

import com.evol.common.entity.QueryRequest;
import com.evol.controller.BaseController;
import com.evol.domain.model.Role;
import com.evol.domain.model.RolePermission;
import com.evol.enums.ApiResponseEnum;
import com.evol.service.RolePermissionService;
import com.evol.service.RoleService;
import com.evol.web.ApiResponse;
import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/system/role")
public class RoleController extends BaseController {
	private Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private RoleService roleService;
	@Autowired
	private RolePermissionService rolePermissionService;

	@RequestMapping({ "", "/index" })
	public String index() {
		return "role/role";
	}

	@RequestMapping("/getRole")
	@ResponseBody
	public ApiResponse getRole(Integer roleId) {
		try {
			Role role = this.roleService.getRoleById(roleId);
			List<Integer> permissionIds = this.rolePermissionService.getRolePermsByRole(roleId).stream()
					.map(RolePermission::getPermissionId).collect(Collectors.toList());
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("role", role);
			data.put("permissionIds", permissionIds);
			return ApiResponse.success(data);
		} catch (Exception e) {
			log.error("获取角色信息失败", e);
			return ApiResponse.fail(ApiResponseEnum.USER_DEFINED_ERROR, "获取角色信息失败，请联系网站管理员！");
		}
	}

	@RequestMapping("/add")
	@ResponseBody
	public ApiResponse addRole(Role role, Integer[] permissionIds) {
		try {
			role.setCreateTime(new Date());
			this.roleService.addRoleWithPerm(role, permissionIds);
			return ApiResponse.success("新增角色成功！");
		} catch (Exception e) {
			log.error("新增角色失败", e);
			return ApiResponse.fail(ApiResponseEnum.USER_DEFINED_ERROR, "新增角色失败，请联系网站管理员！");
		}
	}

	@RequestMapping("/update")
	@ResponseBody
	public ApiResponse updateRole(Role role, Integer[] permissionIds) {
		try {
			role.setUpdateTime(new Date());
			this.roleService.UpdateRoleWithPerm(role, permissionIds);
			return ApiResponse.success("修改角色成功！");
		} catch (Exception e) {
			log.error("修改角色失败", e);
			return ApiResponse.fail(ApiResponseEnum.USER_DEFINED_ERROR, "修改角色失败，请联系网站管理员！");
		}
	}

	@RequestMapping("/delete")
	@ResponseBody
	public ApiResponse deleteRoles(String roleIds) {
		try {
			List<Integer> roleIdList =
					Arrays.stream(roleIds.split(",")).filter(e -> NumberUtils.isNumber(e)).map(e -> Integer.parseInt(e)).collect(Collectors.toList());
			this.roleService.deleteRoles(roleIdList);
			return ApiResponse.success("删除角色成功！");
		} catch (Exception e) {
			log.error("删除角色失败", e);
			return ApiResponse.fail(ApiResponseEnum.USER_DEFINED_ERROR, "删除角色失败，请联系网站管理员！");
		}
	}

	@RequestMapping("/list")
	@ResponseBody
	public Map<String, Object> roleList(QueryRequest request, Role role) {
		return super.selectByPageNumSize(request, () -> this.roleService.getRoleList(role));
	}
}
