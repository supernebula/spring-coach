package com.evol.domain.dto;

import lombok.Data;

@Data
public class UserChangePwdParam {

    private Long userId;

    private String password;

    private String newPassword;

    private String confirmPassword;


}
