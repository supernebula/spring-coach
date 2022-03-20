package com.evol.service.impl;

import com.evol.domain.model.*;
import com.evol.mapper.PermissionMapper;
import com.evol.mapper.RolePermissionMapper;
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
public class PermissionServiceImpl  { //extends BaseService<Permission>
	@Autowired
	private PermissionMapper permissionMapper;

	@Autowired
	private RolePermissionMapper rolePermissionMapper;

	@Autowired
	private RolePermissionServiceImpl rolePermissionService;

	private List<Integer> permissionIds(Integer roleId){
		ArrayList<Integer> list = new ArrayList<>();
		list.add(roleId);
		return this.permissionIds(list);
	}

	private List<Integer> permissionIds(List<Integer> roleIds){
		RolePermissionExample rpExample = new RolePermissionExample();
		rpExample.createCriteria().andRoleIdIn(roleIds);
		List<RolePermission> rpList = rolePermissionMapper.selectByExample(rpExample);
		if(rpList == null || rpList.size() == 0){
			return new ArrayList<Integer>();
		}
		List<Integer> pIds = rpList.stream().map(e -> e.getPermissionId()).collect(Collectors.toList());
		return pIds;
	}

	public List<Permission> getRolePermissions(Integer roleId) {
		List<Integer> pIds = this.permissionIds(roleId);
		PermissionExample permissionExample = new PermissionExample();
		permissionExample.createCriteria().andIdIn(pIds);
		return permissionMapper.selectByExample(permissionExample);
	}

	public List<Permission> getUnionPermission(List<Integer> roleIds) {
		List pIds = this.permissionIds(roleIds);
		PermissionExample permissionExample = new PermissionExample();
		permissionExample.createCriteria().andIdIn(pIds);
		permissionExample.setDistinct(true);
		permissionExample.setOrderByClause("order_num desc");
		return permissionMapper.selectByExample(permissionExample);
	}

	public List<Permission> getUnionPermissionByType(List<Integer> roleIds, Integer type) {

		List pIds = this.permissionIds(roleIds);
		PermissionExample permissionExample = new PermissionExample();
		permissionExample.createCriteria().andIdIn(pIds).andIsShowEqualTo(1).andTypeLessThanOrEqualTo(type);
		permissionExample.setDistinct(true);
		permissionExample.setOrderByClause("order_num desc");
		return permissionMapper.selectByExample(permissionExample);
	}

	public List<Permission> getAllUnionPermissionByType(Integer type) {
		PermissionExample permissionExample = new PermissionExample();
		permissionExample.createCriteria().andIsShowEqualTo(1).andTypeLessThanOrEqualTo(type);
		permissionExample.setDistinct(true);
		permissionExample.setOrderByClause("order_num desc");
		return permissionMapper.selectByExample(permissionExample);
	}

	public List<Permission> getAllMenus() {
		return permissionMapper.selectByExample(new PermissionExample());
	}

	public int savePermission(Permission authPermission) {
		return permissionMapper.insert(authPermission);
	}

	public int updatePermission(Permission authPermission) {
		return permissionMapper.updateByPrimaryKeySelective(authPermission);
	}

	public Permission getPermissionById(Integer permId) {
		return permissionMapper.selectByPrimaryKey(permId);
	}

	public void deletePerms(List<Integer> permIds) {
		this.batchDeleteById(permIds);
		this.rolePermissionService.deleteRolePermsByIds(permIds);

		RolePermissionExample rpExample = new RolePermissionExample();
		rpExample.createCriteria().andPermissionIdIn(permIds);
		rolePermissionMapper.deleteByExample(rpExample);

		PermissionExample pExample = new PermissionExample();
		pExample.createCriteria().andPidIn(permIds);
		List<Permission> list = permissionMapper.selectByExample(pExample);
		for (Permission perm: list) {
			perm.setPid(0);
			permissionMapper.updateByPrimaryKeySelective(perm);
		}

	}

	public Permission findByNameAndType(String name, Integer type) {
		PermissionExample example = new PermissionExample();
		example.createCriteria().andNameEqualTo(name).andTypeEqualTo(type);
		List<Permission> list = permissionMapper.selectByExample(example);
		return list.get(0);
	}

	public Permission findByPerm(String permCode) {
		PermissionExample example = new PermissionExample();
		example.createCriteria().andPermsEqualTo(permCode);
		List<Permission> list = permissionMapper.selectByExample(example);
		return list.get(0);
	}

	public int batchDeleteById(List<Integer> list) {
		PermissionExample example = new PermissionExample();
		example.createCriteria().andIdIn(list);
		return this.permissionMapper.deleteByExample(example);
	}
}
