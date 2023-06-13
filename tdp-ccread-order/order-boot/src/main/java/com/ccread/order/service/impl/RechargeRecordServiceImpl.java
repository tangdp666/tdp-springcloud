package com.ccread.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ccread.admin.api.UserFeignClient;
import com.ccread.bookeack.api.BookeackFeign;
import com.ccread.common.constant.SystemConstants;
import com.ccread.common.dto.NovelInfoDto;
import com.ccread.common.dto.NovelListDto;
import com.ccread.common.redis.utils.RedisUtils;
import com.ccread.common.result.R;
import com.ccread.common.utils.BeanCopyUtils;
import com.ccread.common.vo.RechargeRecordVo;
import com.ccread.common.vo.UserInfoVo;
import com.ccread.order.entity.RechargeRecord;
import com.ccread.order.manager.BookFeignManager;
import com.ccread.order.manager.UserFeignManager;
import com.ccread.order.mapper.RechargeRecordMapper;
import com.ccread.order.service.RechargeRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 账户记录表(RechargeRecord)表服务实现类
 *
 * @author makejava
 * @since 2023-06-03 20:07:36
 */
@Service("rechargeRecordService")
@RequiredArgsConstructor
public class RechargeRecordServiceImpl extends ServiceImpl<RechargeRecordMapper, RechargeRecord> implements RechargeRecordService {

    private final BookFeignManager bookFeignManager;

    private final UserFeignManager userFeignManager;
    private final UserFeignClient userFeignClient;

    private final BookeackFeign bookeackFeign;

    private final RedisUtils redisUtils;

    private final RedisTemplate redisTemplate;

    @Override
    public R createOrder(Long novelId) {
        //查询图书信息
        NovelInfoDto novelById = bookFeignManager.getNovelById(novelId);
        String novelName = novelById.getNovelName();
        Integer price =  novelById.getPrice();
        //查询账户余额
        UserInfoVo userInfo = userFeignManager.getUserInfo();
        Integer account = userInfo.getAccount();

        if (account < price){
            return R.failed("余额不足");
        }
        //扣款
        R res = userFeignClient.subUserAccount(price);


        //添加购买信息
        R res1 = bookeackFeign.createPurchase(novelId);

        //添加消费记录
        Integer userid  = (Integer) redisUtils.get("userid");
        RechargeRecord rechargeRecord =new RechargeRecord();
        rechargeRecord.setMoney(price);
        rechargeRecord.setRecordType(0);
        rechargeRecord.setRechargeMsg("购买《"+novelName+"》");
        rechargeRecord.setCreateBy(Long.valueOf(userid));
        rechargeRecord.setCreateTime(new Date());
        rechargeRecord.setUpdateBy(Long.valueOf(userid));
        rechargeRecord.setUpdateTime(new Date());

        save(rechargeRecord);
        return R.ok();
    }

    @Override
    public R addAccount(Integer accountVal) {
        R res = userFeignClient.addUserAccount(accountVal);

        Integer userid  = (Integer) redisUtils.get("userid");
        RechargeRecord rechargeRecord = new RechargeRecord();
        rechargeRecord.setMoney(accountVal);
        rechargeRecord.setRecordType(1);
        rechargeRecord.setRechargeMsg("充值");
        rechargeRecord.setCreateBy(Long.valueOf(userid));
        rechargeRecord.setCreateTime(new Date());
        rechargeRecord.setUpdateBy(Long.valueOf(userid));
        rechargeRecord.setUpdateTime(new Date());

        save(rechargeRecord);
        return R.ok();
    }

    @Override
    public R<List<RechargeRecordVo>> getRechargeList(Integer typeId) {
        LambdaQueryWrapper<RechargeRecord> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(RechargeRecord::getCreateBy,redisUtils.get("userid"));
        queryWrapper.eq(RechargeRecord::getDelFlag, SystemConstants.IS_NODELFLAG);
        queryWrapper.eq(RechargeRecord::getRecordType,typeId);
        queryWrapper.orderByDesc(RechargeRecord::getUpdateBy);

        List<RechargeRecord> rechargeRecords = list(queryWrapper);
        List<RechargeRecordVo> rechargeRecordVos = BeanCopyUtils.copyBeanList(rechargeRecords, RechargeRecordVo.class);
        return R.ok(rechargeRecordVos);
    }

    @Override
    public R getVipStatus() {
        Integer userId  = (Integer) redisUtils.get("userid");
        String vipKey = "vip:" + userId;
        BoundValueOperations<String, Long> ops = redisTemplate.boundValueOps(vipKey);
        Long expireTime = ops.get();
        Long now = System.currentTimeMillis();
        if (expireTime == null) {
            return R.ok("您当前还不是VIP");
        }

        if (expireTime < now) {
            return R.ok("您的VIP已过期");
        }

        Long remainDays = TimeUnit.MILLISECONDS.toDays(expireTime - now);
        return R.ok("您的VIP还剩" + remainDays + "天");
    }

    @Override
    public R rechargeVip() {
        Integer userId  = (Integer) redisUtils.get("userid");
        String vipKey = "vip:" + userId;
        BoundValueOperations<String, Long> ops = redisTemplate.boundValueOps(vipKey);
        Long expireTime = ops.get();
        Long now = System.currentTimeMillis();
        if (expireTime == null || expireTime < now) {
            expireTime = now + TimeUnit.MINUTES.toMinutes(6*1000);
            ops.set(expireTime);
        }
        expireTime = expireTime +TimeUnit.MINUTES.toMinutes(6*1000);
        ops.set(expireTime);


        userFeignClient.subUserAccount(500);



        //添加消费记录
        Integer userid  = (Integer) redisUtils.get("userid");
        RechargeRecord rechargeRecord =new RechargeRecord();
        rechargeRecord.setMoney(500);
        rechargeRecord.setRecordType(0);
        rechargeRecord.setRechargeMsg("会员充值");
        rechargeRecord.setCreateBy(Long.valueOf(userid));
        rechargeRecord.setCreateTime(new Date());
        rechargeRecord.setUpdateBy(Long.valueOf(userid));
        rechargeRecord.setUpdateTime(new Date());

        save(rechargeRecord);

        return R.ok();
    }

    @Override
    public R<Boolean> getVipStatusByRead() {

        Integer userId  = (Integer) redisUtils.get("userid");
        String vipKey = "vip:" + userId;
        BoundValueOperations<String, Long> ops = redisTemplate.boundValueOps(vipKey);
        Long expireTime = ops.get();
        Long now = System.currentTimeMillis();
        if (expireTime == null || expireTime < now) {
            return R.ok(false);
        }
        return R.ok(true);
    }
}
