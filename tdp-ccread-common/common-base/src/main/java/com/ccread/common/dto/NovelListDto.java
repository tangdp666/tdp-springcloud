package com.ccread.common.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NovelListDto {
    private Long id;
    //小说名
    private String novelName;
    //分类ID
    private Long categoryId;
    //作者
    private String author;
    //限制条数
    private Boolean limitNub;
    //排序
    private Integer orderType;
}
