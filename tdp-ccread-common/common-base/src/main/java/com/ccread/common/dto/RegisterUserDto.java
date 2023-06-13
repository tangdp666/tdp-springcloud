package com.ccread.common.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserDto {

    private String username;

    private String nickname;

    private Integer gender;

    private String avatar;

    private String password;

    private String email;

    private Integer status;

}
