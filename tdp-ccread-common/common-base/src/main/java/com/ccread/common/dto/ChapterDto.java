package com.ccread.common.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChapterDto {

    //所属小说ID
    private Long novelId;

    private Integer pageNub;

    private Integer pageSize;





}
