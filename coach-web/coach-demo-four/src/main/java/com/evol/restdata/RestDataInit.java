package com.evol.restdata;

import com.evol.restdata.dao.UserRepository;
import com.evol.restdata.domain.AddressDTO;
import com.evol.restdata.domain.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;

@Component
@Slf4j
public class RestDataInit {
    @Autowired
    private UserRepository userRepository;

    //初始化，向数据库中插入一条数据
    @PostConstruct
    public void init(){
        log.debug("RestDataInit---------");
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1l);
        userDTO.setSex("man");
        userDTO.setFirstName("Jack");
        userDTO.setLastName("Mr");
        userDTO.setPassword("123456");
        userDTO.setCreatedTime(LocalDateTime.now());
        userDTO.setModifiedDate(LocalDateTime.now());
        userDTO.setModifiedDate(LocalDateTime.now());
        userDTO.setAddressDTO(new AddressDTO("the five street", "123456", "New York", "Y"));
        userRepository.save(userDTO);
    }
}
