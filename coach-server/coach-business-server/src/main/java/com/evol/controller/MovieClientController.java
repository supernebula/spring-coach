package com.evol.controller;

import com.evol.web.ApiResponse;
import com.evol.domain.PageBase;
import com.evol.domain.model.Movie;
import com.evol.domain.request.MovieQueryRequest;
import com.evol.service.MovieService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "C端电影接口")
@RestController
@RequestMapping("/client/movie")
public class MovieClientController {

    @Autowired
    private MovieService movieService;


    @ApiOperation(value = "查询电影", response = ApiResponse.class)
    @PostMapping("/query")
    public ApiResponse query(MovieQueryRequest reqParam){
        PageBase<Movie> result = movieService.queryPage(reqParam);
        return ApiResponse.success(result);
    }

    @ApiOperation(value = "获取电影信息", response = ApiResponse.class)
    @PostMapping("/get")
    public ApiResponse get(Integer movieId){
        Movie movie = movieService.getMovie(movieId);
        return ApiResponse.success(movie);
    }

}
