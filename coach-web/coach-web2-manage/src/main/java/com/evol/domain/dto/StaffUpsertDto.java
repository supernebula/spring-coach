package com.evol.domain.dto;

import lombok.Data;

import java.util.Date;

@Data
public class StaffUpsertDto {

    private Integer createStaffId;

    private String loginName;

    private String password;

    private String realName;

    private String mobile;

    private Integer status;

    private String passwordResetCode;

    private Integer accessFailedCount;

    private Date lastLoginTime;

    private Boolean isDeleted;

    private Date deleteTime;

    private Date createTime;

    private Date updateTime;

}
