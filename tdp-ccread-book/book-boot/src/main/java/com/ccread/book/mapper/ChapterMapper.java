package com.ccread.book.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ccread.book.entity.Chapter;
import org.apache.ibatis.annotations.Mapper;


/**
 * 章节表(Chapter)表数据库访问层
 *
 * @author makejava
 * @since 2023-05-30 20:30:22
 */
@Mapper
public interface ChapterMapper extends BaseMapper<Chapter> {

}
