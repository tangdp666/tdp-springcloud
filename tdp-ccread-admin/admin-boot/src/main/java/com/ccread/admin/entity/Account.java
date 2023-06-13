package com.ccread.admin.entity;

import java.util.Date;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * (Account)表实体类
 *
 * @author makejava
 * @since 2023-06-01 17:16:29
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("tb_account")
public class Account  {
    //id@TableId
    private Long id;

    //账户id
    private Long userid;
    //账户余额
    private Integer account;
    
    private Long createBy;
    
    private Date createTime;
    
    private Long updateBy;
    
    private Date updateTime;



}
