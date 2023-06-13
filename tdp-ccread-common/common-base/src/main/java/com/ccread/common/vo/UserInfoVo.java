package com.ccread.common.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoVo {

    private Long id;

    private String username;

    private String nickname;

    private Integer gender;

    private String avatar;

    private String email;

    private Integer account;

}
