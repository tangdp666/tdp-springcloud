package com.ccread.common.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetileNovelVo {

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
    //阅读量
    private Integer viewCount;
    //是否VIP可读：0-非VIP；1-VIP
    private Integer isVip;

    //评分
    private Integer score;
    //评论条数
    private Integer commentCount;
    //目录条数
    private Integer catalogCount;
}
