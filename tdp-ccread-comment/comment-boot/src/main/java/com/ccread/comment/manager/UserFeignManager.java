package com.ccread.comment.manager;


import com.ccread.admin.api.UserFeignClient;
import com.ccread.common.dto.UserInfoListDto;
import com.ccread.common.result.R;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
@AllArgsConstructor
public class UserFeignManager {
    private UserFeignClient userFeignClient;

    public List<UserInfoListDto> getUserInfoByIds(List<Long> idList){
        R<List<UserInfoListDto>> userInfoByIds = userFeignClient.getUserInfoByIds(idList);
        return userInfoByIds.getData();
    }



}
