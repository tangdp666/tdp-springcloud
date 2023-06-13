package com.ccread.common.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentAddDto {

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
}
