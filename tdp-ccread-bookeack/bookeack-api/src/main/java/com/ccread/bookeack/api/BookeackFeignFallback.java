package com.ccread.bookeack.api;

import com.ccread.common.result.R;
import org.springframework.stereotype.Component;

@Component
    class BookeackFeignFallback implements BookeackFeign{

        @Override
        public R createPurchase(Long novelId) {
            return R.failed("添加失败");
        }
    }