package com.ccread.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ccread.admin.dto.UserAuthDTO;
import com.ccread.admin.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {

    UserAuthDTO getByUsername(@Param("userName") String userName);
}