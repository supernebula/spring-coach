package com.evol.domain.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class StaffChangePwdDto {

    @NotNull(message = "页码不能为空")
    public Integer staffId;

    @NotNull(message = "就密码不能为空")
    public String password;


    @NotNull(message = "新密码不能为空")
    public String newPassword;


    @NotNull(message = "重复新密码不能为空")
    public String confimPassword;

}
