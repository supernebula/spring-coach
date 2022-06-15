package com.evol.multidatas.service.user;

import com.evol.multidatas.config.RoutingDataSource;
import com.evol.multidatas.domain.model.User;
import com.evol.multidatas.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MuUserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoutingDataSource routingDataSource;

    public User findById(Integer id) {
        try {
            log.debug("DataSource Catalog: {}", routingDataSource.getConnection().getCatalog());
        }catch (Exception ex){
            log.error(ex.getMessage(), ex);
        }
        return userMapper.selectByPrimaryKey(id);
    }

}
