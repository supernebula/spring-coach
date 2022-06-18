package com.evol.restdata.dao;

import com.evol.restdata.domain.UserDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

//自定义返回字段属性, Projection实现域对象的进一步封装
@Projection(name = "virtual", types = UserDTO.class)
public interface UserDtoExcerpt {
    String getFirstName();

    String getLastName();

    String getSex();

    @Value("#{target.firstName} #{target.lastName}")
    String getFullName();

    @Value("#target.addressDTO.toString()")
    String getAddressDTO();
}
