package com.ccread.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ccread.admin.entity.UserRole;
import org.apache.ibatis.annotations.Mapper;


/**
 * 用户和角色关联表(UserRole)表数据库访问层
 *
 * @author makejava
 * @since 2023-06-02 10:08:44
 */
@Mapper
public interface UserRoleMapper extends BaseMapper<UserRole> {

}
