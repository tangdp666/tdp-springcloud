package com.ccread.admin.api;


import com.ccread.admin.dto.UserAuthDTO;
import com.ccread.common.dto.UserInfoListDto;
import com.ccread.common.result.R;
import com.ccread.common.vo.UserInfoVo;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
class UserFeignClientFallback implements UserFeignClient{

        @Override
        public R<UserAuthDTO> getUserByUsername(String username) {
            return R.failed("查询失败");
        }

        @Override
        public R<List<UserInfoListDto>> getUserInfoByIds(List<Long> idList) {
            return R.ok(new ArrayList<>(0));
        }

        @Override
        public R<UserInfoVo> getUserInfo() {
            return R.failed("查询失败");
        }

        @Override
        public R subUserAccount(Integer accountVal) {
            return R.failed("扣款失败");
        }

        @Override
        public R addUserAccount(Integer accountVal) {
            return R.failed("充值失败");
        }
    }