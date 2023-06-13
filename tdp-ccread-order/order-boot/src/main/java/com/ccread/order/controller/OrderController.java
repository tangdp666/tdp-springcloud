package com.ccread.order.controller;


import com.ccread.common.result.R;
import com.ccread.common.vo.RechargeRecordVo;
import com.ccread.order.service.RechargeRecordService;
import io.lettuce.core.dynamic.annotation.Param;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("order")
@RequiredArgsConstructor
public class OrderController {

    private final RechargeRecordService rechargeRecordService;

    /**
     * 购买书籍
     */
    @PostMapping("/createOrder")
    public R createOrder(@RequestParam Long novelId){
        return rechargeRecordService.createOrder(novelId);
    }

    /**
     * 充值
     */
    @PostMapping("/addAccount")
    public R addAccount(@RequestParam Integer accountVal){
        return rechargeRecordService.addAccount(accountVal);
    }

    /**
     * vip判断
     */
    @GetMapping("/getVipStatus")
    public R getVipStatus(){
        return rechargeRecordService.getVipStatus();
    }

    /**
     * vip充值
     */
    @PostMapping("/rechargeVip")
    public R rechargeVip(){
        return rechargeRecordService.rechargeVip();
    }

    /**
     * vip判断阅读页
     */
    @GetMapping("/getVipStatusByRead")
    public R<Boolean> getVipStatusByRead(){
        return rechargeRecordService.getVipStatusByRead();
    }

    /**
     *充值记录
     */
    @GetMapping("/getRechargeList/{typeId}")
    public R<List<RechargeRecordVo>> getRechargeList(@PathVariable("typeId") Integer typeId){
        return rechargeRecordService.getRechargeList(typeId);
    }



}
