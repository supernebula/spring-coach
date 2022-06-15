package com.evol.multidatas.service.business;

import com.evol.multidatas.config.RoutingDataSource;
import com.evol.multidatas.domain.model.Movie;
import com.evol.multidatas.mapper.MovieMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.SQLException;

@Service
@Slf4j
public class MovieService {

    @Autowired
    private MovieMapper movieMapper;

    @Autowired
    private RoutingDataSource routingDataSource;

    public Movie findById(Integer id) {
        try {
            log.debug("DataSource Catalog: {}", routingDataSource.getConnection().getCatalog());
        }catch (Exception ex){
            log.error(ex.getMessage(), ex);
        }
        return movieMapper.selectByPrimaryKey(id);
    }

}
