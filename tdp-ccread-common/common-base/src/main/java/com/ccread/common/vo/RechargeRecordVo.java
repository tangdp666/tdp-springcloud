package com.ccread.common.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RechargeRecordVo {

    //主键@TableId
    private Long id;
    //金额
    private Integer money;
    //充值类型：0-消费；1-充值
    private Integer recordType;
    //创建时间
    private Date createTime;
    private String rechargeMsg;

}
