package com.evol.controller;

import com.evol.web.ApiResponse;
import com.evol.domain.PageBase;
import com.evol.domain.model.Movie;
import com.evol.domain.request.MovieQueryRequest;
import com.evol.service.MovieService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "C端电影接口")
@RestController
@RequestMapping("/client/movie")
@CrossOrigin
public class MovieClientController {

    @Autowired
    private MovieService movieService;


    @CrossOrigin(value = "http://localhost:8090")
    @ApiOperation(value = "查询电影列表", response = ApiResponse.class)
    @PostMapping("/query")
    public ApiResponse query(MovieQueryRequest reqParam){
        PageBase<Movie> result = movieService.queryPage(reqParam);
        return ApiResponse.success(result);
    }

    @CrossOrigin(value = "http://localhost:8090")
    @ApiOperation(value = "获取电影信息", response = ApiResponse.class)
    @GetMapping("/get")
    public ApiResponse get(Integer movieId){
        Movie movie = movieService.getMovie(movieId);
        return ApiResponse.success(movie);
    }

}
