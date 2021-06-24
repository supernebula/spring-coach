package com.evol.controller.inner;

import com.evol.web.ApiResponse;
import com.evol.domain.model.Movie;
import com.evol.service.MovieService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "内部电影接口")
@RestController
@RequestMapping("/inner/movie")
@CrossOrigin
public class MovieInnerController {

    @Autowired
    private MovieService movieService;


    @CrossOrigin(value = "http://localhost:8090")
    @ApiOperation(value = "获取电影信息", response = ApiResponse.class)
    @GetMapping("/get")
    public ApiResponse get(@RequestParam(name = "movieId")  Integer movieId){
        Movie movie = movieService.getMovie(movieId);
        return ApiResponse.success(movie);
    }

    @CrossOrigin(value = "http://localhost:8090")
    @ApiOperation(value = "获取电影信息", response = ApiResponse.class)
    @GetMapping("/get2")
    public ApiResponse get2(){
        Movie movie = movieService.getMovie(2);
        return ApiResponse.success(movie);
    }

}

