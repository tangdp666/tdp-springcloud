package com.ccread.book.controller;


import com.ccread.book.service.CategoryService;
import com.ccread.book.service.ChapterService;
import com.ccread.book.service.NovelService;
import com.ccread.common.dto.NovelListDto;
import com.ccread.common.result.R;
import com.ccread.common.vo.ChapterCatalogVo;
import com.ccread.common.vo.DetileNovelVo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book/home")
public class HomeController {

    @Autowired
    private NovelService novelService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ChapterService chapterService;

    /**
     * 首页列表（时间+观看量）
     */
    @PostMapping("/getNovelListByHome")
    public R<List> getNovelListByHome(@RequestBody NovelListDto novelListDto){
        return novelService.getNovelListByHome(novelListDto);
    }

    /**
     * 分类查询
     */
    @GetMapping("/getCategoryList")
    public R<List> getCategoryList(){
        return categoryService.getCategoryList();
    }

    /**
     * 详情（评分+详情+目录条数+评论条数）
     * @param novel
     * @return
     */
    @GetMapping("/getDetileNovel/{novel}")
    public R<DetileNovelVo> getDetileNovel(@PathVariable("novel") Long novel){
        return novelService.getDetileNovel(novel);
    }

    /**
     * 目录列表
     */
    @GetMapping("/getCatalogList/{novelId}")
    public R<List> getCatalogCount(@PathVariable("novelId") Long novelId){
        return chapterService.getCatalogCount(novelId);
    }

    /**
     * 浏览量更新
     */
    @PutMapping("/updataViewCount/{id}")
    public R updataViewCount(@PathVariable("id") Long id){
        return novelService.updataViewCount(id);
    }


}
