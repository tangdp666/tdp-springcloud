package com.ccread.bookeack.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ccread.bookeack.entity.Collection;
import org.apache.ibatis.annotations.Mapper;


/**
 * 收藏表(Collection)表数据库访问层
 *
 * @author makejava
 * @since 2023-06-03 15:15:02
 */
@Mapper
public interface CollectionMapper extends BaseMapper<Collection> {

}
