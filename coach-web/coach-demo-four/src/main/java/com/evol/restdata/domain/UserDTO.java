package com.evol.restdata.domain;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Data
public class UserDTO {
    @Id
    @GeneratedValue
    private Long id;
    private String firstName;
    private String lastName;
    private String sex;

    @JsonIgnore  //可以隐藏属性
    private String password;

    @CreatedDate
    LocalDateTime createdTime;

    @LastModifiedDate
    LocalDateTime modifiedDate;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    AddressDTO addressDTO;
}
