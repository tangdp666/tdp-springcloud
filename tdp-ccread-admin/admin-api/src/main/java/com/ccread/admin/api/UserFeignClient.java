package com.ccread.admin.api;

import com.ccread.admin.dto.UserAuthDTO;
import com.ccread.common.dto.UserInfoListDto;
import com.ccread.common.result.R;
import com.ccread.common.vo.UserInfoVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@FeignClient(value = "ccread-admin",fallback = UserFeignClientFallback.class)
public interface UserFeignClient {

    @GetMapping("/api/v1/users/username/{username}")
    R<UserAuthDTO> getUserByUsername(@PathVariable String username);

    /**
     * 批量查询用户信息
     */
    @GetMapping("/api/v1/users/getUserInfoByIds")
    R<List<UserInfoListDto>> getUserInfoByIds(@RequestBody List<Long> idList);
    /**
     * 个人信息
     */
    @GetMapping("/api/v1/users/getUserInfo")
     R<UserInfoVo> getUserInfo();
    /**
     * 扣款
     */
    @PutMapping("/api/v1/users/subUserAccount")
    R subUserAccount(@RequestParam Integer accountVal);

    /**
     * 充值
     */
    @PutMapping("/api/v1/users/addUserAccount")
    R addUserAccount(@RequestParam Integer accountVal);



}