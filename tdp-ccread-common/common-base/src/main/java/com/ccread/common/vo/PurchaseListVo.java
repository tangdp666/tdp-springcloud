package com.ccread.common.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseListVo {

    //主键@TableId
    private Long id;

    //小说ID
    private Long novelId;
    //创建人
    private Long createBy;


    //小说名
    private String novelName;
    //封面
    private String cover;

}
