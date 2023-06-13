package com.ccread.order.manager;


import com.ccread.book.api.BookFeign;
import com.ccread.common.dto.NovelInfoDto;
import com.ccread.common.dto.NovelListDto;
import com.ccread.common.result.R;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@AllArgsConstructor
public class BookFeignManager {
       public BookFeign bookFeign;

       public NovelInfoDto getNovelById( Long novelId){
              R<NovelInfoDto> novelById = bookFeign.getNovelById(novelId);
              return novelById.getData();
       }


}
