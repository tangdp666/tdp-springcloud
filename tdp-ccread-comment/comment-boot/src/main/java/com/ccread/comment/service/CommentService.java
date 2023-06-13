package com.ccread.comment.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ccread.comment.entity.Comment;
import com.ccread.common.dto.CommentAddDto;
import com.ccread.common.dto.NovelScoreDto;
import com.ccread.common.result.R;
import com.ccread.common.vo.UserCommentVO;

import java.util.List;
import java.util.Map;


/**
 * 评分、评论表(Comment)表服务接口
 *
 * @author makejava
 * @since 2023-05-30 16:49:31
 */
public interface CommentService extends IService<Comment> {

    R<Integer> getBookScoreByNovelId(Long novelId);

    R<Integer> getCommentCount(Long novelId);

    R<List> getCommentList(Long novelId);

    R userAddComment(CommentAddDto commentAddDto);


    R<List<UserCommentVO>> getUserComment();
}
