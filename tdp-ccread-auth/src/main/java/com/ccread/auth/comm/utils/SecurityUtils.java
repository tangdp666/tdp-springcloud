package com.ccread.auth.comm.utils;

import com.ccread.auth.security.details.user.SysUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {

    /**
     * 获取用户
     **/
    public static SysUserDetails getLoginUser()
    {
        return (SysUserDetails) getAuthentication().getPrincipal();
    }

    /**
     * 获取Authentication
     */
    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public static Boolean isAdmin(){
        Long id = getLoginUser().getUserId();
        return id != null && 1L == id;
    }

    public static Long getUserId() {
        return getLoginUser().getUserId();
    }
}