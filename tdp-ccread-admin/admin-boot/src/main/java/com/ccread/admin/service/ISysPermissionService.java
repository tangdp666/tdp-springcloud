package com.ccread.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ccread.admin.entity.SysPermission;

import java.util.List;

public interface ISysPermissionService extends IService<SysPermission> {
    /**
     * 刷新Redis缓存中角色菜单的权限规则，角色和菜单信息变更调用
     */
    boolean refreshPermRolesRules();

    List<SysPermission> listPermRoles();
}