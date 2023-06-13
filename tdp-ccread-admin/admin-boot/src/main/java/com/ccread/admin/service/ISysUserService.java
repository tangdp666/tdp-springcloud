package com.ccread.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ccread.admin.dto.UserAuthDTO;
import com.ccread.admin.entity.SysUser;
import com.ccread.common.dto.RegisterUserDto;
import com.ccread.common.dto.UpUserDto;
import com.ccread.common.dto.UserInfoListDto;
import com.ccread.common.result.R;
import com.ccread.common.vo.UserInfoVo;

import java.util.List;

public interface ISysUserService extends IService<SysUser> {


    /**
     * 根据用户名获取认证用户信息，携带角色和密码
     *
     * @param username
     * @return
     */
    UserAuthDTO getByUsername(String username);

    R<SysUser> getuserInfoById();

    R register(RegisterUserDto registerUserDto);

    R<UserInfoVo> getUserInfo();

    R updateUserInfo(UpUserDto upUserDto);

    R<List<UserInfoListDto>> getUserInfoByIds(List<Long> idList);
}