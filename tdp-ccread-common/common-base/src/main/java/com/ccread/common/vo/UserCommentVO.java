package com.ccread.common.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCommentVO {
    //主键@TableId
    private Long id;
    //评分
    private Integer score;
    //评论
    private String comment;
    //所属小说ID
    private Long novelId;
    //小说名
    private String novelName;
    //简介
    private String intro;
    //作者
    private String author;
    //封面
    private String cover;
    //创建时间
    private Date createTime;
}
