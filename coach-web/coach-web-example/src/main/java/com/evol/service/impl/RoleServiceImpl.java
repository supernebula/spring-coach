package com.evol.service.impl;

import com.evol.domain.model.*;
import com.evol.mapper.RoleMapper;
import com.evol.mapper.RolePermissionMapper;
import com.evol.mapper.UserRoleMapper;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class RoleServiceImpl{
	private Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private RoleMapper roleMapper;

	@Autowired
	private UserRoleMapper userRoleMapper;

	@Autowired
	private RolePermissionMapper rolePermissionMapper;
	@Autowired
	private RolePermissionServiceImpl rolePermissionService;
	@Autowired
	private UserRoleServiceImpl userRoleService;

	public void addRoleWithPerm(Role role, Integer[] permissionIds) {
		roleMapper.insert(role);
		Arrays.stream(permissionIds).forEach(permissionId -> {
			RolePermission rolePerm = new RolePermission();
			rolePerm.setPermissionId(permissionId);
			rolePerm.setRoleId(role.getId());
			rolePermissionMapper.insert(rolePerm);
		});
	}

	public void UpdateRoleWithPerm(Role role, Integer[] permissionIds) {
		roleMapper.updateByPrimaryKeySelective(role);
		RolePermissionExample example = new RolePermissionExample();
		example.createCriteria().andRoleIdEqualTo(role.getId());
		rolePermissionMapper.deleteByExample(example);
		Arrays.stream(permissionIds).forEach(permissionId -> {
			RolePermission rolePerm = new RolePermission();
			rolePerm.setPermissionId(permissionId);
			rolePerm.setRoleId(role.getId());
			rolePermissionMapper.insert(rolePerm);
		});
	}

//	@Transactional
//	public void deleteRoles(List<Integer> roleIds) {
//
//		RolePermissionExample deleteExample = new RolePermissionExample();
//		deleteExample.createCriteria().andIdIn(roleIds);
//		rolePermissionMapper.deleteByExample(deleteExample);
//		this.rolePermissionService.deleteRolePermissionsByRoleId(roleIds);
//		this.userRoleService.deleteUserRolesByRoleId(roleIds);
//	}

//	@Transactional
//	public int batchDelete(List<String> list, String property) {
//		RolePermissionExample example = new RolePermissionExample();
//		example.createCriteria().and(property, list);
//		return this.roleMapper.deleteByExample(example);
//	}

	public boolean updateRole(Role role) {
		return roleMapper.updateByPrimaryKeySelective(role) > 0;
	}

	public List<Role> getRoleListByUser(Long userId) {
		UserRoleExample urExample = new UserRoleExample();
		urExample.createCriteria().andUserIdEqualTo(userId);
		List<UserRole> urs = userRoleMapper.selectByExample(urExample);
		if(urs == null || urs.size() == 0){
			return new ArrayList<Role>();
		}

		RoleExample roleExample = new RoleExample();
		roleExample.createCriteria().andIdIn(urs.stream().map(e -> e.getRoleId()).collect(Collectors.toList()));
		List<Role> roles = roleMapper.selectByExample(roleExample);
		return roles;
	}

	public Role getRoleById(Integer roleId) {
		return roleMapper.selectByPrimaryKey(roleId);
	}

	public List<Role> getRoleList(Role role) {
		try {
			RoleExample example = new RoleExample();
			if (StringUtils.isNotBlank(role.getName())) {
				example.createCriteria().andNameEqualTo(role.getName());
			}
			example.setOrderByClause("create_time");
			return roleMapper.selectByExample(example);
		} catch (Exception e) {
			log.error("获取角色信息失败", e);
			return new ArrayList<>();
		}
	}

}
