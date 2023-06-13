package com.ccread.book.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ccread.book.entity.Novel;
import com.ccread.common.dto.NovelInfoDto;
import com.ccread.common.dto.NovelInfoListDto;
import com.ccread.common.dto.NovelListDto;
import com.ccread.common.result.R;
import com.ccread.common.vo.DetileNovelVo;

import java.util.List;


/**
 * 小说表(Novel)表服务接口
 *
 * @author makejava
 * @since 2023-05-30 14:46:29
 */
public interface NovelService extends IService<Novel> {

    R<List> getNovelListByHome(NovelListDto novelListDto);

    R<DetileNovelVo> getDetileNovel(Long novel);

    R<List<NovelInfoListDto>> getNovelInfoList(List<Long> idList);

    R<NovelInfoDto> getNovelById(Long novelId);

    R updataViewCount(Long id);
}
