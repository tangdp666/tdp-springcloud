package com.ccread.common.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryListVo {

    //主键@TableId
    private Long id;
    //分类名
    private String categoryName;

}
