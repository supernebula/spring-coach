package com.evol.domain.dto;

import lombok.Data;

@Data
public class UserAddDto {

    private String username;

    private String password;

    private String mobile;

    private String nickname;

    private Integer gender;

    private String province;

    private String city;

    private String county;

}
