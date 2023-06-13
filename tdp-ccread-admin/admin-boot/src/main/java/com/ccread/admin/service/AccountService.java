package com.ccread.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ccread.admin.entity.Account;
import com.ccread.common.result.R;


/**
 * (Account)表服务接口
 *
 * @author makejava
 * @since 2023-06-01 17:16:29
 */
public interface AccountService extends IService<Account> {

    R subUserAccount(Integer accountVal);

    R addUserAccount(Integer accountVal);
}
