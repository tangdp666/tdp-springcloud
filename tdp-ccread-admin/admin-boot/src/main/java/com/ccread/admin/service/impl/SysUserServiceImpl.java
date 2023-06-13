package com.ccread.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ccread.admin.dto.UserAuthDTO;
import com.ccread.admin.entity.Account;
import com.ccread.admin.entity.SysUser;
import com.ccread.admin.entity.UserRole;
import com.ccread.admin.mapper.AccountMapper;
import com.ccread.admin.mapper.SysUserMapper;
import com.ccread.admin.mapper.UserRoleMapper;
import com.ccread.admin.service.ISysUserService;
import com.ccread.common.dto.RegisterUserDto;
import com.ccread.common.dto.UpUserDto;
import com.ccread.common.dto.UserInfoListDto;
import com.ccread.common.redis.utils.RedisUtils;
import com.ccread.common.result.R;
import com.ccread.common.result.ResultCode;
import com.ccread.common.utils.BeanCopyUtils;
import com.ccread.common.vo.UserInfoVo;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    private final PasswordEncoder passwordEncoder;

    private final AccountMapper accountMapper;

    private final RedisUtils redisUtils;

    private final UserRoleMapper userRoleMapper;




    @Override
    public UserAuthDTO getByUsername(String username) {
        UserAuthDTO userAuthInfo = this.baseMapper.getByUsername(username);
        return userAuthInfo;
    }

    @Override
    public R<SysUser> getuserInfoById() {
        SysUser sysUser = getById(5L);
        return R.ok(sysUser);
    }

    @Override
    public R register(RegisterUserDto registerUserDto) {
        SysUser sysUser = new SysUser();
        if(!Objects.isNull(registerUserDto.getUsername())){
            LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(SysUser::getUsername,registerUserDto.getUsername());
            SysUser username = getOne(queryWrapper);
            if (!Objects.isNull(username)){
                return R.failed(ResultCode.USERNAME_ISEXIST);
            }
            sysUser.setUsername(registerUserDto.getUsername());
        }
        String password = passwordEncoder.encode(registerUserDto.getPassword());

        sysUser.setPassword(password);
        sysUser.setEmail(registerUserDto.getEmail());
        sysUser.setNickname("新注册用户");
        save(sysUser);

        System.out.println("1)"+sysUser.getId());
        Account account = new Account();
        account.setUserid(sysUser.getId());
        account.setAccount(0);
        accountMapper.insert(account);

        UserRole userRole =new UserRole();
        userRole.setUserId(sysUser.getId());
        userRole.setRoleId(6L);

        userRoleMapper.insert(userRole);
        return R.ok();
    }

    @Override
    public R<UserInfoVo> getUserInfo() {
        SysUser sysUser = getById((Serializable) redisUtils.get("userid"));
        UserInfoVo userInfoVo = BeanCopyUtils.copyBean(sysUser, UserInfoVo.class);
        LambdaQueryWrapper<Account> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Account::getUserid,redisUtils.get("userid"));
        Integer account = accountMapper.selectOne(queryWrapper).getAccount();
        userInfoVo.setAccount(account);
        return R.ok(userInfoVo);
    }

    @Override
    public R updateUserInfo(UpUserDto upUserDto) {
        LambdaUpdateWrapper<SysUser> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(SysUser::getId,redisUtils.get("userid"));
        updateWrapper.set(Objects.nonNull(upUserDto.getNickname()) && upUserDto.getNickname() != "",SysUser::getNickname,upUserDto.getNickname());
        updateWrapper.set(Objects.nonNull(upUserDto.getGender()) && upUserDto.getGender() >= 0,SysUser::getGender,upUserDto.getGender());

        update(updateWrapper);
        return R.ok();
    }

    @Override
    public R<List<UserInfoListDto>> getUserInfoByIds(List<Long> idList) {
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(SysUser::getId,idList);
        List<SysUser> sysUsers = list(queryWrapper);
        List<UserInfoListDto> userInfoListDtos = BeanCopyUtils.copyBeanList(sysUsers, UserInfoListDto.class);

        return R.ok(userInfoListDtos);
    }

}
