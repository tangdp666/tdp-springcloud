package com.ccread.book.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ccread.book.entity.Category;
import org.apache.ibatis.annotations.Mapper;


/**
 * 分类表(Category)表数据库访问层
 *
 * @author makejava
 * @since 2023-05-31 16:50:24
 */
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {

}
