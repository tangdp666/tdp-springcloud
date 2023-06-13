package com.ccread.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ccread.admin.entity.Account;
import org.apache.ibatis.annotations.Mapper;


/**
 * (Account)表数据库访问层
 *
 * @author makejava
 * @since 2023-06-01 17:16:29
 */
@Mapper
public interface AccountMapper extends BaseMapper<Account> {

}
