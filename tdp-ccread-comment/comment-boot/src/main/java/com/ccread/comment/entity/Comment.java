package com.ccread.comment.entity;

import java.util.Date;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * 评分、评论表(Comment)表实体类
 *
 * @author makejava
 * @since 2023-05-30 16:49:30
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("tb_comment")
public class Comment  {
    //主键@TableId
    private Long id;

    //评论人ID
    private Long userId;
    //评分
    private Integer score;
    //评论
    private String comment;
    //所属小说ID
    private Long novelId;
    //创建时间
    private Date createTime;
    //创建人
    private Long createBy;
    //更新人
    private Long updateBy;
    //更新时间
    private Date updateTime;
    //删除标志：0-未删除；1-已删除
    private Integer delFlag;



}
