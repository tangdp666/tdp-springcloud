package com.ccread.admin.controller;

import com.ccread.admin.dto.UserAuthDTO;
import com.ccread.admin.entity.SysUser;
import com.ccread.admin.service.ISysUserService;
import com.ccread.common.result.R;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final ISysUserService iSysUserService;

    /**
     * 获取用户信息
     */
    @GetMapping("/username/{username}")
    public R<UserAuthDTO> getUserByUsername(@PathVariable String username) {
        System.out.println("1)"+username);
        UserAuthDTO user = iSysUserService.getByUsername(username);
        return R.ok(user);
    }

    @GetMapping("/getuserInfoById")
    public R<SysUser> getuserInfoById(){
        return iSysUserService.getuserInfoById();
    }
}