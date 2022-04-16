package com.evol.service;

import com.evol.domain.model.Permission;

import java.util.List;

public interface PermissionService {
    List<Permission> getRolePermissions(Integer roleId);

    List<Permission> getUnionPermission(List<Integer> roleIds);

    List<Permission> getUnionPermissionByType(List<Integer> roleIds, Integer type);

    List<Permission> getAllUnionPermissionByType(Integer type);

    List<Permission> getAllMenus();

    int savePermission(Permission authPermission);

    int updatePermission(Permission authPermission);

    Permission getPermissionById(Integer permId);

    void deletePerms(List<Integer> permIds);

    Permission findByNameAndType(String name, Integer type);

    Permission findByPerm(String permCode);

    int batchDeleteById(List<Integer> list);
}
