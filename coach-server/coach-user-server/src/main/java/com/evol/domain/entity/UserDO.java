package com.evol.domain.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
//@TableName(value = "user")
public class UserDO implements Serializable {

    private static final long serialVersionUID = 1L;

    //@TableId(type = IdType.INPUT)
    private Long userId;

    private String userName;
    private String password;
    private String nickName;
    private String openid;
    private String userImg;
    private String mobile;

    /**
     * 1、男，2、女，3、未知
     */
    private Integer sex;

    private String province;
    private String city;
    private String county;

    private Date createTime;
    private Date updateTime;

}
