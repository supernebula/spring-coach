//package com.evol.controller.permission;
//
//import com.evol.common.entity.QueryRequest;
//import com.evol.controller.BaseController;
//import com.evol.domain.model.UserRole;
//import com.evol.enums.ApiResponseEnum;
//import com.evol.service.UserRoleService;
//import com.evol.service.UserService;
//import com.evol.web.ApiResponse;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Collectors;
//
//@Controller
//@RequestMapping("/system/user")
//public class UserController extends BaseController {
//	private Logger log = LoggerFactory.getLogger(this.getClass());
//	@Autowired
//	private UserService userService;
//
//	@Autowired
//	private UserRoleService userRoleService;
//	private static final String ON = "on";
//
//	@RequestMapping({ "", "/index" })
//	public String index() {
//		return "user/user";
//	}
//
//	@RequestMapping("/add")
//	@ResponseBody
//	public ApiResponse add(JthinkUser user, Integer[] roleIds) {
//		try {
//			if (ON.equalsIgnoreCase(user.getStatus()))
//				user.setStatus(JthinkUser.STATUS_VALID);
//			else
//				user.setStatus(JthinkUser.STATUS_LOCK);
//			user.setPassword(LoginUtil.encodePwd(user.getPassword()));
//			user.setCreateTime(new Date());
//			this.userService.addUser(user, roleIds);
//
//			return ApiResponse.success("新增用户成功！");
//		} catch (Exception e) {
//			log.error("新增用户失败", e);
//			return ApiResponse.fail(ApiResponseEnum.USER_DEFINED_ERROR,"新增用户失败，请联系网站管理员！");
//		}
//	}
//	@SaCheckPermission(value = {"sys:user:add","superadmin"},mode=SaMode.OR)
//	@RequestMapping("/update")
//	@ResponseBody
//	public ApiResponse update(JthinkUser user, Integer[] roleIds) {
//		try {
//			if (ON.equalsIgnoreCase(user.getStatus()))
//				user.setStatus(JthinkUser.STATUS_VALID);
//			else
//				user.setStatus(JthinkUser.STATUS_LOCK);
//			user.setPassword(null);
//			user.setUsername(null);
//			user.setUpdateTime(new Date());
//			this.userService.updateUser(user, roleIds);
//			return ApiResponse.success("修改用户成功！");
//		} catch (Exception e) {
//			log.error("修改用户失败", e);
//			return ApiResponse.success("修改用户失败，请联系网站管理员！");
//		}
//	}
//
//	@RequestMapping("/delete")
//	@ResponseBody
//	public ApiResponse delete(String userIds) {
//		try {
//			// 删除用户
//			this.userService.deleteUsersByIds(userIds);
//			// 删除用户角色关联
//			this.userRoleService.batchDeleteUserRole(userIds);
//			return ApiResponse.success("删除成功！");
//		} catch (Exception e) {
//			log.error("删除用户失败", e);
//			return ApiResponse.fail(ApiResponseEnum.USER_DEFINED_ERROR, "删除失败，请联系网站管理员！");
//		}
//	}
//
//	@RequestMapping("/list")
//	@ResponseBody
//	public Map<String, Object> roleList(QueryRequest request, JthinkUser user) {
//		return super.selectByPageNumSize(request, () -> this.userService.getUserList(user));
//	}
//
//	@RequestMapping("/getUser")
//	@ResponseBody
//	public ApiResponse getUser(Long userId) {
//		try {
//			JthinkUser user = this.userService.selectByKey(userId);
//			List<Integer> roleIds = this.userRoleService.getUserRoleListByUser(userId).stream().map(UserRole::getRoleId)
//					.collect(Collectors.toList());
//			Map<String, Object> data = new HashMap<String, Object>();
//			user.setPassword(null);
//			data.put("user", user);
//			data.put("roleIds", roleIds);
//			return ApiResponse.success(data);
//		} catch (Exception e) {
//			log.error("获取用户失败", e);
//			return ApiResponse.fail(ApiResponseEnum.USER_DEFINED_ERROR, "获取用户失败，请联系网站管理员！");
//		}
//	}
//
//	@RequestMapping("/profile")
//	public String profileIndex(Model model) {
//		JthinkUser user = this.userService.selectByKey(StpUtil.getLoginIdAsString());
//		user.setPassword(null);
//		model.addAttribute("user", user);
//		return "/user/profile";
//	}
//
//	@RequestMapping("/getUserProfile")
//	@ResponseBody
//	public ApiResponse getUserProfile() {
//		try {
//			JthinkUser user = this.userService.selectByKey(StpUtil.getLoginIdAsString());
//			user.setPassword(null);
//			return ApiResponse.success(user);
//		} catch (Exception e) {
//			log.error("获取用户信息失败", e);
//			return ApiResponse.fail(ApiResponseEnum.USER_DEFINED_ERROR, "获取用户信息失败，请联系网站管理员！");
//		}
//	}
//
//	@RequestMapping("/updateUserProfile")
//	@ResponseBody
//	public ApiResponse updateUserProfile(JthinkUser user) {
//		try {
//			// 用户名和密码不改变
//			user.setUsername(null);
//			user.setPassword(null);
//			user.setUpdateTime(new Date());
//			this.userService.updateUserProfile(user);
//			return ApiResponse.success("更新个人信息成功！");
//		} catch (Exception e) {
//			log.error("更新用户信息失败", e);
//			return ApiResponse.fail(ApiResponseEnum.USER_DEFINED_ERROR, "更新用户信息失败，请联系网站管理员！");
//		}
//	}
//
//	@GetMapping("/updatePassword")
//	public String updatePassword() {
//
//		return "/user/updatePassword";
//	}
//
//	@PostMapping("/updatePassword")
//	@ResponseBody
//	public ApiResponse updatePassword(String oldPassword, String newPassword) {
//		JthinkUser user = this.userService.selectByKey(StpUtil.getLoginIdAsString());
//		try {
//			if (LoginUtil.matchPass(oldPassword, user.getPassword())) {
//				this.userService.updatePassword(newPassword, user.getId());
//			} else {
//				return ApiResponse.fail(ApiResponseEnum.USER_DEFINED_ERROR, "原密码不正确！");
//			}
//
//		} catch (Exception e) {
//			log.error("更新失败");
//			return ApiResponse.fail(ApiResponseEnum.USER_DEFINED_ERROR, "更新密码失败，请联系网站管理员！");
//		}
//
//		return ApiResponse.success("密码更新成功");
//	}
//}
