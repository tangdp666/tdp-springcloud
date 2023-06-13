package com.ccread.common.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CollectionListVo {
    //主键@TableId
    private Long id;

    //小说ID
    private Long novelId;
    //状态：0-正常；1-取消
    private Integer status;
    //创建人
    private Long createBy;

    //小说名
    private String novelName;
    //封面
    private String cover;

}
