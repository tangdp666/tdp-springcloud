package com.ccread.comment.api;

import com.ccread.common.result.R;
import org.springframework.stereotype.Component;

@Component
    class CommentFeignFallback implements CommentFeign {

        @Override
        public R<Integer> getBookScoreByNovelId(Long novelId) {
            return R.ok(5);
        }

        @Override
        public R<Integer> getCommentCount(Long novelId) {
            return R.ok(5);
        }
    }