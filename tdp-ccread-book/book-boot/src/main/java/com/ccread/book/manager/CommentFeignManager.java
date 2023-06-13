package com.ccread.book.manager;


import com.ccread.comment.api.CommentFeign;
import com.ccread.common.result.R;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CommentFeignManager {
    private final CommentFeign commentFeign;

    public Integer getBookScoreByNovelId(Long novelId){
        R<Integer> bookScoreByNovelId = commentFeign.getBookScoreByNovelId(novelId);
        return bookScoreByNovelId.getData();
    }
}
