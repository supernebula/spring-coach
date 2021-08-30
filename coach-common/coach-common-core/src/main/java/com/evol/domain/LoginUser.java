package com.evol.domain;

import lombok.Data;

@Data
public class LoginUser {

    private Integer userType;

    private Integer id;

    private String loginName;
}