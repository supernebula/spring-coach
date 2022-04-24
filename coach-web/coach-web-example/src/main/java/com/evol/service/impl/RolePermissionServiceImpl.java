package com.evol.service.impl;

import com.evol.domain.model.RolePermission;
import com.evol.domain.model.RolePermissionExample;
import com.evol.mapper.RolePermissionMapper;
import com.evol.service.RolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class RolePermissionServiceImpl implements RolePermissionService {
	@Autowired
	private RolePermissionMapper rolePermissionMapper;

	public List<RolePermission> getRolePermsByRole(Integer roleId) {
		RolePermissionExample example = new RolePermissionExample();
		example.createCriteria().andRoleIdEqualTo(roleId);
		return rolePermissionMapper.selectByExample(example);
	}

	public int deleteRolePermission(Integer roleId) {
		RolePermissionExample example = new RolePermissionExample();
		example.createCriteria().andRoleIdEqualTo( roleId);
		return rolePermissionMapper.deleteByExample(example);
	}

	public int deleteRolePermsByIds(List<Integer> permIds) {
		RolePermissionExample example = new RolePermissionExample();
		example.createCriteria().andPermissionIdIn(permIds);
		return rolePermissionMapper.deleteByExample(example);
	}

	public void deleteRolePermissionsByRoleId(List<Integer> roleIds) {
		RolePermissionExample example = new RolePermissionExample();
		example.createCriteria().andRoleIdIn(roleIds);
		rolePermissionMapper.deleteByExample(example);
	}

}
