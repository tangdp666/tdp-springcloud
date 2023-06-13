package com.ccread.bookeack.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ccread.bookeack.entity.Purchase;
import org.apache.ibatis.annotations.Mapper;


/**
 * 购买表(Purchase)表数据库访问层
 *
 * @author makejava
 * @since 2023-06-03 15:16:36
 */
@Mapper
public interface PurchaseMapper extends BaseMapper<Purchase> {

}
