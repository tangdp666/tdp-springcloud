package com.ccread.comment.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ccread.comment.entity.Comment;
import org.apache.ibatis.annotations.Mapper;


/**
 * 评分、评论表(Comment)表数据库访问层
 *
 * @author makejava
 * @since 2023-05-30 16:49:29
 */
@Mapper
public interface CommentMapper extends BaseMapper<Comment> {

    Integer getScoreAvg(Long navelId);
}
