package com.ccread.book.entity;

import java.util.Date;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * 小说表(Novel)表实体类
 *
 * @author makejava
 * @since 2023-05-30 14:46:28
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("tb_novel")
public class Novel  {
    //主键@TableId
    private Long id;

    //小说名
    private String novelName;
    //简介
    private String intro;
    //作者
    private String author;
    //封面
    private String cover;
    //价格
    private Integer price;
    //分类ID
    private Long categoryId;
    //阅读量
    private Integer viewCount;
    //是否VIP可读：0-非VIP；1-VIP
    private Integer isVip;
    //状态：0表示正常，1表示下架
    private Integer isGrounding;
    //评分
    @TableField(exist = false)
    private Integer score;
    //创建人
    private Long createBy;
    //创建时间
    private Date createTime;
    //更新人
    private Long updateBy;
    //更新时间
    private Date updateTime;
    //删除标志：0-未删除；1-已删除
    private Integer delFlag;


    public Novel(Long id, Integer viewCount){
        this.id = id;
        this.viewCount = viewCount;
    }


}
