package com.evol.domain.dto;

import lombok.Data;
import java.util.Date;

@Data
public class UserCreateParam {

    private String username;

    private String nickname;

    private String password;

    private String email;

    private String phone;

    private Integer sex;

    private String remarks;

    private Date createTime;

    private Date expiredTime;

    private Integer locked;

    private Date passwordExpiredTime;

    private Integer enabled;
}
