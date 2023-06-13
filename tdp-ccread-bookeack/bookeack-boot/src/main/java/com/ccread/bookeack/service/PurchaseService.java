package com.ccread.bookeack.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ccread.bookeack.entity.Purchase;
import com.ccread.common.result.R;
import com.ccread.common.vo.PurchaseListVo;

import java.util.List;


/**
 * 购买表(Purchase)表服务接口
 *
 * @author makejava
 * @since 2023-06-03 15:16:36
 */
public interface PurchaseService extends IService<Purchase> {

    R createPurchase(Long novelId);

    R<Boolean> getPirchaseFalg(Long novelId);

    R<List<PurchaseListVo>> getPurchaseList();
}
