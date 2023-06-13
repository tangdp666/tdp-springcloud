package com.ccread.order.entity;

import java.util.Date;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * 账户记录表(RechargeRecord)表实体类
 *
 * @author makejava
 * @since 2023-06-03 20:07:35
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("tb_recharge_record")
public class RechargeRecord  {
    //主键@TableId
    private Long id;

    //金额
    private Integer money;
    //充值类型：0-消费；1-充值
    private Integer recordType;
    //创建时间
    private Date createTime;
    //创建人
    private Long createBy;
    //更新时间
    private Date updateTime;
    //更新人
    private Long updateBy;
    //删除标志：0-未删除；1-已删除
    private Integer delFlag;

    private String rechargeMsg;



}
