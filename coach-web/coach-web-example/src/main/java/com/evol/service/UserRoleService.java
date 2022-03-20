package com.evol.service;

import com.evol.domain.model.UserRole;
import java.util.List;

public interface UserRoleService {

    void deleteUserRolesByRoleId(List<Integer> roleIds);

    List<UserRole> getUserRoleListByUser(Long userId);

    int deleteUserRole(Long userId);

    int batchDeleteUserRole(List<Long> userIds);

    int save(UserRole ur);

}
