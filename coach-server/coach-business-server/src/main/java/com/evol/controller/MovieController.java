package com.evol.controller;

import com.evol.domain.ApiResponse;
import com.evol.domain.PageBase;
import com.evol.domain.model.Movie;
import com.evol.domain.request.MovieQueryRequest;
import com.evol.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieController {

    @Autowired
    private MovieService movieService;

    public ApiResponse list(MovieQueryRequest reqParam){
        PageBase<Movie> result = movieService.queryPage(reqParam);
        return new ApiResponse(result);
    }

}
