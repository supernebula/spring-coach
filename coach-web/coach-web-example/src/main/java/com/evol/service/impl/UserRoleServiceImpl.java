package com.evol.service.impl;

import com.evol.domain.model.*;
import com.evol.mapper.UserRoleMapper;
import com.evol.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserRoleServiceImpl implements UserRoleService {
	@Autowired
	private UserRoleMapper userRoleMapper;

	public void deleteUserRolesByRoleId(List<Integer> roleIds) {
		UserRoleExample example = new UserRoleExample();
		example.createCriteria().andRoleIdIn(roleIds);
		userRoleMapper.deleteByExample(example);
	}

	public List<UserRole> getUserRoleListByUser(Long userId) {
		UserRoleExample urExample = new UserRoleExample();
		urExample.createCriteria().andUserIdEqualTo(userId);
		List<UserRole> urs = userRoleMapper.selectByExample(urExample);
		if(urs == null || urs.size() == 0){
			return new ArrayList<UserRole>();
		}
		return urs;
	}

	public int deleteUserRole(Long userId) {
		UserRoleExample urExample = new UserRoleExample();
		urExample.createCriteria().andUserIdEqualTo(userId);
		return userRoleMapper.deleteByExample(urExample);

	}

	public int batchDeleteUserRole(List<Long> userIds) {
		UserRoleExample urExample = new UserRoleExample();
		urExample.createCriteria().andUserIdIn(userIds);
		return userRoleMapper.deleteByExample(urExample);
	}

	public int save(UserRole ur){
		if(ur.getId() == null || ur.getId().equals(0)){
			return this.userRoleMapper.insert(ur);
		}
		return this.userRoleMapper.updateByPrimaryKey(ur);
	}

/*	public int deleteByExample(Example example) {
		return this.userRoleMapper.deleteByExample(example);
	}*/
}
