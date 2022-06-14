package com.evol.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

/**
 * 4.4 配置使用Spring Data JDBC
 * @author admin
 */
@Table("user")
@Data
public class UserEntity {
    @Id
    private Integer id;
    private String name;
    private int age;
}


