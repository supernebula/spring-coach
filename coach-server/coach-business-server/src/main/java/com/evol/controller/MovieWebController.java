package com.evol.controller;

import com.evol.domain.PageBase;
import com.evol.domain.dto.MoiveUpsertDto;
import com.evol.domain.model.Movie;
import com.evol.domain.request.MovieQueryRequest;
import com.evol.service.MovieService;
import com.evol.web.ApiResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Api(tags = "电影管理")
@RestController
@RequestMapping("/web/movie")
public class MovieWebController {

    @Autowired
    private  MovieService movieService;

    @ApiOperation(value = "添加电影", response = ApiResponse.class)
    @PostMapping("/addMovie")
    public ApiResponse addMovie(MoiveUpsertDto param){
        Integer id =  movieService.addMovie(param);
        return ApiResponse.success(id);
    }

    @ApiOperation(value = "删除电影", response = ApiResponse.class)
    @PostMapping("/deleteMovie")
    public ApiResponse deleteMovie(Integer movieId){
        Integer num = movieService.deleteMoive(movieId);
        return ApiResponse.success(num);
    }

    @ApiOperation(value = "修改电影", response = ApiResponse.class)
    @PostMapping("/modifyMovie")
    public ApiResponse modifyMovie(Integer movieId, MoiveUpsertDto param){
        Integer id =  movieService.modifyMovie(movieId, param);
        return ApiResponse.success(id);
    }

    @ApiOperation(value = "查询电影记录", response = ApiResponse.class)
    @GetMapping("/queryMovie")
    public ApiResponse queryMovie(MovieQueryRequest reqParam){
        PageBase<Movie> pageList = movieService.queryPage(reqParam);
        return ApiResponse.success(pageList);
    }

    @ApiOperation(value = "获取电影", response = ApiResponse.class)
    @PostMapping("/getMovie")
    public ApiResponse getMovie(Integer movieId){
        Movie movie = movieService.getMovie(movieId);
        return ApiResponse.success(movie);
    }

    @ApiOperation(value = "上传封面文件", response = ApiResponse.class)
    @PostMapping("/uploadCover")
    public ApiResponse uploadCover(MultipartFile file){
        return null;
    }

}
