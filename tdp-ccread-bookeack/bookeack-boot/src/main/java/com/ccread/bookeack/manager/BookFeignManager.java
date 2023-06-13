package com.ccread.bookeack.manager;


import com.ccread.book.api.BookFeign;
import com.ccread.common.dto.NovelInfoListDto;
import com.ccread.common.dto.NovelListDto;
import com.ccread.common.result.R;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class BookFeignManager {

    private BookFeign bookFeign;

    public List<NovelInfoListDto> getNovelInfoList(List<Long> idList){
        R<List<NovelInfoListDto>> novelInfoList = bookFeign.getNovelInfoList(idList);
        return novelInfoList.getData();
    }

}
