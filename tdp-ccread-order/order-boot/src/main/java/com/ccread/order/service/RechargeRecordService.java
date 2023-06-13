package com.ccread.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ccread.common.result.R;
import com.ccread.common.vo.RechargeRecordVo;
import com.ccread.order.entity.RechargeRecord;

import java.util.List;


/**
 * 账户记录表(RechargeRecord)表服务接口
 *
 * @author makejava
 * @since 2023-06-03 20:07:35
 */
public interface RechargeRecordService extends IService<RechargeRecord> {

    R createOrder(Long novelId);

    R addAccount(Integer accountVal);

    R<List<RechargeRecordVo>> getRechargeList(Integer typeId);

    R getVipStatus();

    R rechargeVip();

    R<Boolean> getVipStatusByRead();
}
