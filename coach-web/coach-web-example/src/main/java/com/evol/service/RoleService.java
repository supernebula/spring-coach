package com.evol.service;

import com.evol.domain.model.*;
import java.util.List;

public interface RoleService {
    void addRoleWithPerm(Role role, Integer[] permissionIds);

    void UpdateRoleWithPerm(Role role, Integer[] permissionIds);


    boolean updateRole(Role role);

    List<Role> getRoleListByUser(Long userId);

    Role getRoleById(Integer roleId);

    List<Role> getRoleList(Role role);

    void deleteRoles(List<Integer> roleIds);
}
