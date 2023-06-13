package com.ccread.book.controller;


import com.ccread.book.entity.Chapter;
import com.ccread.book.service.ChapterService;
import com.ccread.book.service.NovelService;
import com.ccread.common.dto.ChapterDto;
import com.ccread.common.dto.NovelInfoDto;
import com.ccread.common.dto.NovelInfoListDto;
import com.ccread.common.dto.NovelListDto;
import com.ccread.common.result.R;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book/chapter")
@RequiredArgsConstructor
public class ChapterController {

    private final NovelService novelService;

    private final ChapterService chapterService;

    //目录
    //章节详情
    /**
     * 批量图书信息
     */
    @GetMapping("/getNovelInfoList")
    public R<List<NovelInfoListDto>> getNovelInfoList(@RequestBody List<Long> idList){
        return novelService.getNovelInfoList(idList);
    }
    /**
     * 查询图书信息
     */
    @GetMapping("/getNovelById/{novelId}")
    public R<NovelInfoDto> getNovelById(@PathVariable("novelId") Long novelId){
        return novelService.getNovelById(novelId);
    }

    /**
     * 章节页
     */
    @PostMapping("/getChapter")
    public R<List<Chapter>> getChapter(@RequestBody ChapterDto chapterDto){
        return chapterService.getChapter(chapterDto);
    }

}
