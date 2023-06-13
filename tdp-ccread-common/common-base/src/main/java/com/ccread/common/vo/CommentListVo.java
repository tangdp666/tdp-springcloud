package com.ccread.common.vo;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentListVo {

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
    //创建人头像
    private String avatar;
    //创建人昵称
    private String nickname;

}
