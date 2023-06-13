package com.ccread.common.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChapterCatalogVo {

    //主键@TableId
    private Long id;

    //章节名
    private String chapterName;
}
