package com.evol.domain;

import lombok.Data;
import java.io.Serializable;

@Data
public class LoginUser implements Serializable {

    private Integer userType;

    private Integer id;

    private String loginName;

    private String realName;
}