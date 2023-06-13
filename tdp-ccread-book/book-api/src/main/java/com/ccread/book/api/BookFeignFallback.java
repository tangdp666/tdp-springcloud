package com.ccread.book.api;

import com.ccread.common.dto.NovelInfoDto;
import com.ccread.common.dto.NovelInfoListDto;
import com.ccread.common.result.R;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
    class BookFeignFallback implements BookFeign{

        @Override
        public R<List<NovelInfoListDto>> getNovelInfoList(List<Long> idList) {
            return R.ok(new ArrayList<>(0));
        }

        @Override
        public R<NovelInfoDto> getNovelById(Long novelId) {
            return R.failed("查询失败");
        }
    }