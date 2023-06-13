package com.ccread.book.entity;

import java.util.Date;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * 分类表(Category)表实体类
 *
 * @author makejava
 * @since 2023-05-31 16:50:24
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("tb_category")
public class Category  {
    //主键@TableId
    private Long id;

    //分类名
    private String categoryName;
    //创建时间
    private Date createTime;
    //创建人
    private String createBy;
    //更新人
    private String updateBy;
    //更新时间
    private Date updateTime;
    //删除标志：0-未删除；1-已删除
    private Integer delFlag;



}
