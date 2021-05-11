package com.evol.model;

import lombok.Data;

import java.util.Date;

@Data
public class TestDto{

    private Integer id;

    private String name;

    private String remark;

    private Date createTime;

}