package com.ccread.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ccread.admin.entity.SysPermission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysPermissionMapper extends BaseMapper<SysPermission> {

    List<SysPermission> listPermRoles();
}