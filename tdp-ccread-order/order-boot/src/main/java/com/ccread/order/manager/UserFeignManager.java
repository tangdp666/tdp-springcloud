package com.ccread.order.manager;


import com.ccread.admin.api.UserFeignClient;
import com.ccread.common.result.R;
import com.ccread.common.vo.UserInfoVo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserFeignManager {

    private final UserFeignClient userFeignClient;

    public UserInfoVo getUserInfo(){
        R<UserInfoVo> userInfo = userFeignClient.getUserInfo();
        return userInfo.getData();
    }
}
