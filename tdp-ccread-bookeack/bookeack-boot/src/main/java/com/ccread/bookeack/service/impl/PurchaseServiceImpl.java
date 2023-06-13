package com.ccread.bookeack.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ccread.bookeack.entity.Collection;
import com.ccread.bookeack.entity.Purchase;
import com.ccread.bookeack.manager.BookFeignManager;
import com.ccread.bookeack.mapper.PurchaseMapper;
import com.ccread.bookeack.service.PurchaseService;
import com.ccread.common.constant.SystemConstants;
import com.ccread.common.dto.NovelInfoListDto;
import com.ccread.common.redis.utils.RedisUtils;
import com.ccread.common.result.R;
import com.ccread.common.utils.BeanCopyUtils;
import com.ccread.common.vo.CollectionListVo;
import com.ccread.common.vo.PurchaseListVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 购买表(Purchase)表服务实现类
 *
 * @author makejava
 * @since 2023-06-03 15:16:36
 */
@Service("purchaseService")
@RequiredArgsConstructor
public class PurchaseServiceImpl extends ServiceImpl<PurchaseMapper, Purchase> implements PurchaseService {

    private final RedisUtils redisUtils;

    private final BookFeignManager bookFeignManager;

    @Override
    public R createPurchase(Long novelId) {
        Integer userId = (Integer) redisUtils.get("userid");
        Purchase purchase = new Purchase();
        purchase.setNovelId(novelId);
        purchase.setCreateBy(Long.valueOf(userId));
        purchase.setCreateTime(new Date());
        purchase.setUpdateBy(Long.valueOf(userId));
        purchase.setUpdateTime(new Date());
        save(purchase);
        return R.ok();
    }

    @Override
    public R<Boolean> getPirchaseFalg(Long novelId) {
        LambdaQueryWrapper<Purchase> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Purchase::getCreateBy,redisUtils.get("userid"));
        queryWrapper.eq(Purchase::getNovelId,novelId);

        Purchase purchase = getOne(queryWrapper);
        if (Objects.nonNull(purchase)){
            return R.ok(true);
        }
        return R.ok(false);
    }

    @Override
    public R<List<PurchaseListVo>> getPurchaseList() {
        LambdaQueryWrapper<Purchase>  queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Purchase::getDelFlag, SystemConstants.IS_NODELFLAG);
//        queryWrapper.eq(Purchase::getStatus,SystemConstants.COLLECTION_ISOK);
        queryWrapper.eq(Purchase::getCreateBy,redisUtils.get("userid"));
        queryWrapper.orderByDesc(Purchase::getCreateTime);

        List<Purchase> purchases = list(queryWrapper);

        List<Long> novelIds = purchases.stream().map(Purchase::getNovelId).distinct().collect(Collectors.toList());
        List<NovelInfoListDto> novelInfoListDtos = bookFeignManager.getNovelInfoList(novelIds);

        Map<Long, NovelInfoListDto> novelInfoListDtoMap = novelInfoListDtos.stream()
                .collect(Collectors.toMap(NovelInfoListDto::getId, Function.identity()));


        List<PurchaseListVo> purchaseListVos = BeanCopyUtils.copyBeanList(purchases, PurchaseListVo.class);
        purchaseListVos.stream()
                .map(purchaseListVo -> {
                    String novelName = novelInfoListDtoMap.get(purchaseListVo.getNovelId()).getNovelName();
                    purchaseListVo.setNovelName(novelName);
                    return purchaseListVo;
                })
                .map(purchaseListVo -> {
                    String cover = novelInfoListDtoMap.get(purchaseListVo.getNovelId()).getCover();
                    purchaseListVo.setCover(cover);
                    return purchaseListVo;
                })
                .collect(Collectors.toList());



        return R.ok(purchaseListVos);
    }
}
