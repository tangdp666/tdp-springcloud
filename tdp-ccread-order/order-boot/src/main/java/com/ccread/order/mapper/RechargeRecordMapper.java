package com.ccread.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ccread.order.entity.RechargeRecord;
import org.apache.ibatis.annotations.Mapper;


/**
 * 账户记录表(RechargeRecord)表数据库访问层
 *
 * @author makejava
 * @since 2023-06-03 20:07:34
 */
@Mapper
public interface RechargeRecordMapper extends BaseMapper<RechargeRecord> {

}
