package com.ccread.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ccread.admin.entity.Account;
import com.ccread.admin.mapper.AccountMapper;
import com.ccread.admin.service.AccountService;
import com.ccread.common.redis.utils.RedisUtils;
import com.ccread.common.result.R;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * (Account)表服务实现类
 *
 * @author makejava
 * @since 2023-06-01 17:16:29
 */
@Service("accountService")
@RequiredArgsConstructor
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements AccountService {

    private final RedisUtils redisUtils;

    @Override
    public R subUserAccount(Integer accountVal) {
        LambdaQueryWrapper<Account> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Account::getUserid,redisUtils.get("userid"));

        Account account = getOne(queryWrapper);
        Integer oldVal = account.getAccount();
        account.setAccount(oldVal - accountVal);

        update(account,queryWrapper);
        return R.ok();
    }

    @Override
    public R addUserAccount(Integer accountVal) {
        LambdaQueryWrapper<Account> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Account::getUserid,redisUtils.get("userid"));

        Account account = getOne(queryWrapper);
        Integer oldVal = account.getAccount();
        account.setAccount(oldVal + accountVal);

        update(account,queryWrapper);
        return R.ok();
    }
}
