package com.evol.service.impl;

import com.evol.domain.model.*;
import com.evol.mapper.RoleMapper;
import com.evol.mapper.RolePermissionMapper;
import com.evol.mapper.UserRoleMapper;
import com.evol.service.RoleService;
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
public class RoleServiceImpl implements RoleService {
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

	@Override
	public void addRoleWithPerm(Role role, Integer[] permissionIds) {
		roleMapper.insert(role);
		Arrays.stream(permissionIds).forEach(permissionId -> {
			RolePermission rolePerm = new RolePermission();
			rolePerm.setPermissionId(permissionId);
			rolePerm.setRoleId(role.getId());
			rolePermissionMapper.insert(rolePerm);
		});
	}


	@Override
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

	@Override
	public boolean updateRole(Role role) {
		return roleMapper.updateByPrimaryKeySelective(role) > 0;
	}

	@Override
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

	@Override
	public Role getRoleById(Integer roleId) {
		return roleMapper.selectByPrimaryKey(roleId);
	}

	@Override
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

	@Override
	@Transactional
	public void deleteRoles(List<Integer> roleIds) {
		RolePermissionExample example = new RolePermissionExample();
		example.createCriteria().andRoleIdIn(roleIds);
		rolePermissionMapper.deleteByExample(example);
		this.rolePermissionService.deleteRolePermissionsByRoleId(roleIds);
		this.userRoleService.deleteUserRolesByRoleId(roleIds);
	}

}
