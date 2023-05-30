package com.ccread.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ccread.admin.dto.UserAuthDTO;
import com.ccread.admin.entity.SysUser;
import com.ccread.admin.mapper.SysUserMapper;
import com.ccread.admin.service.ISysUserService;
import com.ccread.common.result.R;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {
    @Override
    public UserAuthDTO getByUsername(String username) {
        UserAuthDTO userAuthInfo = this.baseMapper.getByUsername(username);
        System.out.println("1)"+username);
        System.out.println("2)"+userAuthInfo);
        return userAuthInfo;
    }

    @Override
    public R<SysUser> getuserInfoById() {
        SysUser sysUser = getById(4L);
        return R.ok(sysUser);
    }

}
