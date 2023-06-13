package com.ccread.admin.controller;

import com.ccread.admin.dto.UserAuthDTO;
import com.ccread.admin.entity.SysUser;
import com.ccread.admin.mapper.AccountMapper;
import com.ccread.admin.service.AccountService;
import com.ccread.admin.service.ISysUserService;
import com.ccread.common.dto.RegisterUserDto;
import com.ccread.common.dto.UpUserDto;
import com.ccread.common.dto.UserInfoListDto;
import com.ccread.common.result.R;
import com.ccread.common.vo.UserInfoVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@Slf4j
@RequiredArgsConstructor
public class UserController {


    private final ISysUserService iSysUserService;

    private final AccountService accountService;

    /**
     * 获取用户信息
     */
    @GetMapping("/username/{username}")
    public R<UserAuthDTO> getUserByUsername(@PathVariable String username) {
        UserAuthDTO user = iSysUserService.getByUsername(username);
        return R.ok(user);
    }

    @GetMapping("/getuserInfoById")
    public R<SysUser> getuserInfoById(){
        return iSysUserService.getuserInfoById();
    }

    /**
     * 注册
     * @param registerUserDto
     * @return
     */
    @PostMapping("/register")
    public R register(@RequestBody RegisterUserDto registerUserDto){
        return iSysUserService.register(registerUserDto);
    }
    /**
     * 个人信息
     */
    @GetMapping("/getUserInfo")
    public R<UserInfoVo> getUserInfo(){
        return iSysUserService.getUserInfo();
    }
    /**
     * 修改个人信息
     */
    @PutMapping("/updateUserInfo")
    public R updateUserInfo(@RequestBody UpUserDto upUserDto){
        return iSysUserService.updateUserInfo(upUserDto);
    }


    /**
     *批量查询用户byId
     */
    @GetMapping("/getUserInfoByIds")
    public R<List<UserInfoListDto>> getUserInfoByIds(@RequestBody List<Long> idList){
        return iSysUserService.getUserInfoByIds(idList);
    }

    /**
     * 扣款
     */
    @PutMapping("subUserAccount")
    public R subUserAccount(@RequestParam Integer accountVal){
        return accountService.subUserAccount(accountVal);
    }
    /**
     * 充值
     */
    @PutMapping("addUserAccount")
    public R addUserAccount(@RequestParam Integer accountVal){
        return accountService.addUserAccount(accountVal);
    }






}