package com.ccread.book.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ccread.book.entity.Chapter;
import com.ccread.common.dto.ChapterDto;
import com.ccread.common.result.R;
import com.ccread.common.vo.ChapterCatalogVo;

import java.util.List;


/**
 * 章节表(Chapter)表服务接口
 *
 * @author makejava
 * @since 2023-05-30 20:30:22
 */
public interface ChapterService extends IService<Chapter> {

    R<List> getCatalogCount(Long novelId);

    R<List<Chapter>> getChapter(ChapterDto chapterDto);
}
