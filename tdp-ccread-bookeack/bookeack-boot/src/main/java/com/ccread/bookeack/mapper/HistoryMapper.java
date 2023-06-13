package com.ccread.bookeack.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ccread.bookeack.entity.History;
import org.apache.ibatis.annotations.Mapper;


/**
 * 浏览历史表(History)表数据库访问层
 *
 * @author makejava
 * @since 2023-06-03 15:18:05
 */
@Mapper
public interface HistoryMapper extends BaseMapper<History> {

}
