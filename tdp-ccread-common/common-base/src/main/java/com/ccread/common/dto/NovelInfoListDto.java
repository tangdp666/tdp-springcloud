package com.ccread.common.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NovelInfoListDto {
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

}
