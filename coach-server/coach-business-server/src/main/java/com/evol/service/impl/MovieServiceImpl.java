package com.evol.service.impl;

import com.evol.domain.PageBase;
import com.evol.domain.model.Movie;
import com.evol.domain.model.MovieExample;
import com.evol.domain.request.MovieQueryRequest;
import com.evol.mapper.MovieMapper;
import com.evol.service.MovieService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import java.util.ArrayList;
import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieMapper movieMapper;

    @Override
    public PageBase<Movie> queryPage(MovieQueryRequest movieQueryRequest) {
        Page page =  PageHelper.startPage(movieQueryRequest.getPageNo(), movieQueryRequest.getPageSize());
        MovieExample movieExample = new MovieExample();
        movieExample.createCriteria();
        List<Movie> movieList = movieMapper.selectByExample(movieExample);
        if(CollectionUtils.isEmpty(movieList)){
            return PageBase.create(page.getTotal(), new ArrayList<>());
        }
        return PageBase.create(page.getTotal(),movieList);
    }
}
