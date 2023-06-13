package com.ccread.comment.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ccread.comment.entity.Comment;
import com.ccread.comment.manager.BookFeignManager;
import com.ccread.comment.manager.UserFeignManager;
import com.ccread.comment.mapper.CommentMapper;
import com.ccread.comment.service.CommentService;
import com.ccread.common.constant.SystemConstants;
import com.ccread.common.dto.CommentAddDto;
import com.ccread.common.dto.NovelInfoListDto;
import com.ccread.common.dto.NovelScoreDto;
import com.ccread.common.dto.UserInfoListDto;
import com.ccread.common.redis.utils.RedisUtils;
import com.ccread.common.result.R;
import com.ccread.common.utils.BeanCopyUtils;
import com.ccread.common.vo.CommentListVo;
import com.ccread.common.vo.UserCommentVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 评分、评论表(Comment)表服务实现类
 *
 * @author makejava
 * @since 2023-05-30 16:49:31
 */
@Service("commentService")
@RequiredArgsConstructor
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {


    private final CommentMapper commentMapper;

    private final UserFeignManager userFeignManager;

    private final RedisUtils redisUtils;

    private final BookFeignManager bookFeignManager;
    @Override
    public R<Integer> getBookScoreByNovelId(Long novelId) {
        Integer scoreAvg = commentMapper.getScoreAvg(novelId);
        return R.ok(scoreAvg);
    }

    @Override
    public R<Integer> getCommentCount(Long novelId) {
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Comment::getNovelId, novelId);
        queryWrapper.eq(Comment::getDelFlag, SystemConstants.IS_NODELFLAG);
        Integer count = commentMapper.selectCount(queryWrapper);
        return R.ok(count);
    }

    @Override
    public R<List> getCommentList(Long novelId) {
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Comment::getDelFlag,SystemConstants.IS_NODELFLAG);
        queryWrapper.eq(Comment::getNovelId,novelId);
        queryWrapper.orderByDesc(Comment::getCreateTime);

        List<Comment> comments = list(queryWrapper);

        List<Long> userIds = comments.stream().map(Comment::getUserId).distinct().collect(Collectors.toList());
        List<UserInfoListDto> userInfoListDtos = userFeignManager.getUserInfoByIds(userIds);

        Map<Long, UserInfoListDto> userInfoListDtoMap = userInfoListDtos.stream()
                .collect(Collectors.toMap(UserInfoListDto::getId,Function.identity()));

        List<CommentListVo> commentListVos = BeanCopyUtils.copyBeanList(comments, CommentListVo.class);
        commentListVos.stream()
                .map(commentListVo -> {
                    String nickname = userInfoListDtoMap.get(commentListVo.getUserId()).getNickname();
                    commentListVo.setNickname(nickname);
                    return commentListVo;
                })
                .map(commentListVo -> {
                    String avatar = userInfoListDtoMap.get(commentListVo.getUserId()).getAvatar();
                    commentListVo.setAvatar(avatar);
                    return commentListVo;
                })
                .collect(Collectors.toList());
        return R.ok(commentListVos);
    }

    @Override
    public R userAddComment(CommentAddDto commentAddDto) {

        Integer userId = (Integer) redisUtils.get("userid");
        Comment comment = new Comment();
        comment.setComment(commentAddDto.getComment());
        comment.setScore(commentAddDto.getScore());
        comment.setUserId(userId.longValue());
        comment.setCreateTime(new Date());
        comment.setNovelId(commentAddDto.getNovelId());

        save(comment);
        return R.ok();
    }

    @Override
    public R<List<UserCommentVO>> getUserComment() {
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Comment::getUserId,redisUtils.get("userid"));
        queryWrapper.eq(Comment::getDelFlag,SystemConstants.IS_NODELFLAG);
        queryWrapper.orderByDesc(Comment::getCreateTime);

        List<Comment> comments = list(queryWrapper);
        List<Long> novelIds = comments.stream().map(Comment::getNovelId).distinct().collect(Collectors.toList());
        List<NovelInfoListDto> novelInfoList = bookFeignManager.getNovelInfoList(novelIds);

        Map<Long, NovelInfoListDto> novelInfoListDtoMap = novelInfoList.stream()
                .collect(Collectors.toMap(NovelInfoListDto::getId,Function.identity()));

        List<UserCommentVO> commentVOS = BeanCopyUtils.copyBeanList(comments, UserCommentVO.class);
        commentVOS.stream()
                .map(userCommentVO -> {
                    String novelName = novelInfoListDtoMap.get(userCommentVO.getNovelId()).getNovelName();
                    userCommentVO.setNovelName(novelName);
                    return userCommentVO;
                })
                .map(userCommentVO -> {
                    String cover = novelInfoListDtoMap.get(userCommentVO.getNovelId()).getCover();
                    userCommentVO.setCover(cover);
                    return userCommentVO;
                })
                .map(userCommentVO -> {
                    String author = novelInfoListDtoMap.get(userCommentVO.getNovelId()).getAuthor();
                    userCommentVO.setAuthor(author);
                    return userCommentVO;
                })
                .map(userCommentVO -> {
                    String intro = novelInfoListDtoMap.get(userCommentVO.getNovelId()).getIntro();
                    userCommentVO.setIntro(intro);
                    return userCommentVO;
                })
                .collect(Collectors.toList());

        return R.ok(commentVOS);
    }

}
