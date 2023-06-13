package com.ccread.common.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NovelScoreDto {

//    private Long id;

    //评分
    private Integer scoreAvg;
    //所属小说ID
    private Long novelId;

}
