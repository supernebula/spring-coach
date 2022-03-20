package com.evol.service;

import com.evol.domain.model.RolePermission;

import java.util.List;

public interface RolePermissionService {
    public List<RolePermission> getRolePermsByRole(Integer roleId);

    public int deleteRolePermission(Integer roleId);

    public int deleteRolePermsByIds(List<Integer> permIds);

    public void deleteRolePermissionsByRoleId(List<Integer> roleIds);
}
