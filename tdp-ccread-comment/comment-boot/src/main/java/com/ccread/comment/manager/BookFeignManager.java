package com.ccread.comment.manager;


import com.ccread.book.api.BookFeign;
import com.ccread.common.dto.NovelInfoListDto;
import com.ccread.common.result.R;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Component
@AllArgsConstructor
public class BookFeignManager {

    private final BookFeign bookFeign;

    public List<NovelInfoListDto> getNovelInfoList(List<Long> idList){
        R<List<NovelInfoListDto>> novelInfoList = bookFeign.getNovelInfoList(idList);
        return novelInfoList.getData();
    }
}
