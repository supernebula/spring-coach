package com.evol.mongo.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.Date;

/**
 * @Auther: moerhai@qq.com
 * @Date: 2020/7/12 00:45
 */
@Document
@Data
public class UserDTO implements Serializable {

    //标识集合中的“_id”字段
    @Id
    private int id;
    //指定自定义文档的字段名
    @Field("name")
    private String name;
    @Field("age")
    private int age;
    @Field("sex")
    private String sex;
    @Field("update_time")
    //修改jackjson默认的格式
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date updateTime;

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                ", updateTime=" + updateTime +
                '}';
    }
}