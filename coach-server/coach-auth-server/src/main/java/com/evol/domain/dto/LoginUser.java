package com.evol.domain.dto;

import lombok.Data;

@Data
public class LoginUser {

    private Integer id;

    private String loginName;

    private String realName;

    private String token;

}
