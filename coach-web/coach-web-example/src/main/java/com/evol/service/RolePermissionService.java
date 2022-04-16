package com.evol.service;

import com.evol.domain.model.Permission;
import com.evol.domain.model.RolePermission;

import java.util.List;

public interface RolePermissionService {
    List<RolePermission> getRolePermsByRole(Integer roleId);

    int deleteRolePermission(Integer roleId);

    int deleteRolePermsByIds(List<Integer> permIds);

    void deleteRolePermissionsByRoleId(List<Integer> roleIds);
}
