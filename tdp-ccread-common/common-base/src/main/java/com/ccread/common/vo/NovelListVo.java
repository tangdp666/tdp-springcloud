package com.ccread.common.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NovelListVo {

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
    //评分

    private Integer score;
    //是否VIP可读：0-非VIP；1-VIP
    private Integer isVip;
    //状态：0表示正常，1表示下架
    private Integer isGrounding;

}
