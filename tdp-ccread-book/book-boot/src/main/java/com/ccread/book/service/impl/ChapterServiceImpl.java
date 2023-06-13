package com.ccread.book.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ccread.book.entity.Category;
import com.ccread.book.entity.Chapter;
import com.ccread.book.mapper.ChapterMapper;
import com.ccread.book.service.ChapterService;
import com.ccread.common.constant.SystemConstants;
import com.ccread.common.dto.ChapterDto;
import com.ccread.common.result.R;
import com.ccread.common.utils.BeanCopyUtils;
import com.ccread.common.vo.ChapterCatalogVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 章节表(Chapter)表服务实现类
 *
 * @author makejava
 * @since 2023-05-30 20:30:22
 */
@Service("chapterService")
public class ChapterServiceImpl extends ServiceImpl<ChapterMapper, Chapter> implements ChapterService {

    @Override
    public R<List> getCatalogCount(Long novelId) {
        LambdaQueryWrapper<Chapter> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Chapter::getDelFlag,SystemConstants.IS_NODELFLAG);
        queryWrapper.eq(Chapter::getNovelId,novelId);
        queryWrapper.orderByAsc(Chapter::getOrderNum);

        List<Chapter> chapterList = list(queryWrapper);
        List<ChapterCatalogVo> chapterCatalogVoList = BeanCopyUtils.copyBeanList(chapterList, ChapterCatalogVo.class);
        return R.ok(chapterCatalogVoList);
    }

    @Override
    public R<List<Chapter>> getChapter(ChapterDto chapterDto) {

        LambdaQueryWrapper<Chapter> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Chapter::getNovelId, chapterDto.getNovelId());

//        List<Chapter> chapters = list(queryWrapper);
        Page<Chapter> page = new Page<>(chapterDto.getPageNub(),1);
        page(page,queryWrapper);

        List<Chapter> chapters = page.getRecords();

        return R.ok(chapters);
    }
}
