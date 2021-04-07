package com.evol.service.business.impl;

import com.evol.domain.model.business.Movie;
import com.evol.mapper.business.MovieMapper;
import com.evol.service.business.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieMapper movieMapper;

    @Override
    public Movie getMovieById(Integer movieId) {
        Movie item = movieMapper.selectByPrimaryKey(movieId);
        return item;
    }
}
