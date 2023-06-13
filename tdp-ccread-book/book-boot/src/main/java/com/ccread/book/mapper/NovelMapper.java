package com.ccread.book.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ccread.book.entity.Novel;
import org.apache.ibatis.annotations.Mapper;


/**
 * 小说表(Novel)表数据库访问层
 *
 * @author makejava
 * @since 2023-05-30 14:46:26
 */
@Mapper
public interface NovelMapper extends BaseMapper<Novel> {

}
