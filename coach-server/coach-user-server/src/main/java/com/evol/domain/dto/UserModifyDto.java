package com.evol.domain.dto;

import lombok.Data;

@Data
public class UserModifyDto {

    private Integer UserId;

    private String mobile;

    private String password;

    private String confirmPassword;

    private String nickname;
}
