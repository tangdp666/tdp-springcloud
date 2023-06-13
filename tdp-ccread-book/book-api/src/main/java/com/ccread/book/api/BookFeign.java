package com.ccread.book.api;


import com.ccread.common.dto.NovelInfoDto;
import com.ccread.common.dto.NovelInfoListDto;
import com.ccread.common.dto.NovelListDto;
import com.ccread.common.result.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;


@Component
@FeignClient(value = "ccread-book",fallback = BookFeignFallback.class)
public interface BookFeign {

    /**
     * 批量图书信息
     */
    @GetMapping("/book/chapter/getNovelInfoList")
    R<List<NovelInfoListDto>> getNovelInfoList(@RequestBody List<Long> idList);

    /**
     * 图书信息
     */
    @GetMapping("/book/chapter/getNovelById/{novelId}")
    R<NovelInfoDto> getNovelById(@PathVariable("novelId") Long novelId);


}
