package com.ccread.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ccread.admin.dto.UserAuthDTO;
import com.ccread.admin.entity.SysUser;
import com.ccread.common.result.R;

public interface ISysUserService extends IService<SysUser> {


    /**
     * 根据用户名获取认证用户信息，携带角色和密码
     *
     * @param username
     * @return
     */
    UserAuthDTO getByUsername(String username);

    R<SysUser> getuserInfoById();
}