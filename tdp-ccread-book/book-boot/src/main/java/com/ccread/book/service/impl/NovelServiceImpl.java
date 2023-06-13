package com.ccread.book.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ccread.book.entity.Chapter;
import com.ccread.book.entity.Novel;
import com.ccread.book.manager.CommentFeignManager;
import com.ccread.book.mapper.ChapterMapper;
import com.ccread.book.mapper.NovelMapper;
import com.ccread.book.service.NovelService;
import com.ccread.comment.api.CommentFeign;
import com.ccread.common.constant.SystemConstants;
import com.ccread.common.dto.NovelInfoDto;
import com.ccread.common.dto.NovelInfoListDto;
import com.ccread.common.dto.NovelListDto;
import com.ccread.common.redis.utils.RedisUtils;
import com.ccread.common.result.R;
import com.ccread.common.result.ResultCode;
import com.ccread.common.utils.BeanCopyUtils;
import com.ccread.common.vo.DetileNovelVo;
import com.ccread.common.vo.NovelListVo;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 小说表(Novel)表服务实现类
 *
 * @author makejava
 * @since 2023-05-30 14:46:30
 */
@Service("novelService")
@AllArgsConstructor
//@RequiredArgsConstructor
@Slf4j
public class NovelServiceImpl extends ServiceImpl<NovelMapper, Novel> implements NovelService {

    @Autowired
    private CommentFeign commentFeign;

    @Autowired
    private ChapterMapper chapterMapper;

    @Autowired
    private CommentFeignManager commentFeignManager;

    @Autowired
    private RedisUtils redisUtils;


    @Override
    public R<List> getNovelListByHome(NovelListDto novelListDto) {
        LambdaQueryWrapper<Novel> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(Objects.nonNull(novelListDto.getNovelName()) && novelListDto.getNovelName() != "", Novel::getNovelName,novelListDto.getNovelName());
        queryWrapper.like(Objects.nonNull(novelListDto.getAuthor()) && novelListDto.getAuthor() !="", Novel::getAuthor, novelListDto.getAuthor());
        queryWrapper.eq(Objects.nonNull(novelListDto.getCategoryId()) && novelListDto.getCategoryId() > 0, Novel::getCategoryId,novelListDto.getCategoryId());
        queryWrapper.eq(Novel::getDelFlag, SystemConstants.IS_NODELFLAG);
        queryWrapper.eq(Novel::getIsGrounding,SystemConstants.NOVEL_IS_GROUNDING);
        if(novelListDto.getOrderType() != null && novelListDto.getOrderType() == 1){
            queryWrapper.orderByDesc(Novel::getViewCount);
        }else{
            queryWrapper.orderByDesc(Novel::getCreateTime);
        }
        if(novelListDto.getLimitNub() != null && novelListDto.getLimitNub() ==true){
            queryWrapper.last("LIMIT 3");
        }


        List<Novel> novelList = list(queryWrapper);
        List<NovelListVo> novelListVoList = BeanCopyUtils.copyBeanList(novelList, NovelListVo.class);
        return R.ok(novelListVoList);
    }

    @Override
    public R<DetileNovelVo> getDetileNovel(Long novel) {
        Novel novel1 = getById(novel);
        DetileNovelVo detileNovelVo = BeanCopyUtils.copyBean(novel1,DetileNovelVo.class);
        R<Integer> bookScoreByNovelId = commentFeign.getBookScoreByNovelId(novel);
//        Integer bookScoreByNovelId = commentFeignManager.getBookScoreByNovelId(novel);
//        System.out.println(bookScoreByNovelId.getData());
        //判断
        detileNovelVo.setScore(bookScoreByNovelId.getData());
//        detileNovelVo.setScore(bookScoreByNovelId);

        //目录
        LambdaQueryWrapper<Chapter> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Chapter::getNovelId,novel);
        queryWrapper.eq(Chapter::getDelFlag,SystemConstants.IS_NODELFLAG);
        detileNovelVo.setCatalogCount(chapterMapper.selectCount(queryWrapper));
        //评论条数
        R<Integer> commentCount = commentFeign.getCommentCount(novel);
        //判断
        detileNovelVo.setCommentCount(commentCount.getData());
        return R.ok(detileNovelVo);
    }

    @Override
    public R<List<NovelInfoListDto>> getNovelInfoList(List<Long> idList) {
        LambdaQueryWrapper<Novel> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Novel::getDelFlag, SystemConstants.IS_NODELFLAG);
        queryWrapper.in(Novel::getId,idList);

        List<Novel> novelList =list(queryWrapper);
        List<NovelInfoListDto> novelListDtos = BeanCopyUtils.copyBeanList(novelList, NovelInfoListDto.class);
        return R.ok(novelListDtos);
    }

    @Override
    public R<NovelInfoDto> getNovelById(Long novelId) {
        Novel novel = getById(novelId);
        NovelInfoDto novelInfoDto =  BeanCopyUtils.copyBean(novel, NovelInfoDto.class);
        return R.ok(novelInfoDto);
    }

    @Override
    public R updataViewCount(Long id) {
        Novel novel = getById(id);
        Integer viewCount = novel.getViewCount();

        LambdaUpdateWrapper<Novel> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(Novel::getId,id);
        updateWrapper.set(Novel::getViewCount,viewCount+1);

        update(updateWrapper);
        return R.ok();
    }


}
