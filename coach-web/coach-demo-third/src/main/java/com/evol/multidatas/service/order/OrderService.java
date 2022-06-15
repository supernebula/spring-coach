package com.evol.multidatas.service.order;

import com.evol.multidatas.config.RoutingDataSource;
import com.evol.multidatas.domain.model.NetOrder;
import com.evol.multidatas.mapper.NetOrderMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
@Slf4j
public class OrderService {

    @Autowired
    private NetOrderMapper netOrderMapper;

    @Autowired
    private RoutingDataSource routingDataSource;

    public NetOrder findById(Integer id) {
        try {
            log.debug("DataSource Catalog: {}", routingDataSource.getConnection().getCatalog());
        }catch (Exception ex){
            log.error(ex.getMessage(), ex);
        }
        return netOrderMapper.selectByPrimaryKey(id);
    }

}
