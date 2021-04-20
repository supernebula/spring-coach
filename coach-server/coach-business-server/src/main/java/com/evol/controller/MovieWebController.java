package com.evol.controller;

import com.evol.domain.ApiResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Api(tags = "电影管理")
@RestController
@RequestMapping("/web/movie")
public class MovieWebController {

    @ApiOperation(value = "添加电影", response = ApiResponse.class)
    @PostMapping("/addMovie")
    public ApiResponse addMovie(){
        return null;
    }

    @ApiOperation(value = "删除电影", response = ApiResponse.class)
    @PostMapping("/deleteMovie")
    public ApiResponse deleteMovie(){
        return null;
    }

    @ApiOperation(value = "修改电影", response = ApiResponse.class)
    @PostMapping("/modifyMovie")
    public ApiResponse modifyMovie(){
        return null;
    }

    @ApiOperation(value = "查询电影记录", response = ApiResponse.class)
    @GetMapping("/queryMovie")
    public ApiResponse queryMovie(String key, Integer page, Integer pageSize){
        return null;
    }

    @ApiOperation(value = "获取电影", response = ApiResponse.class)
    @PostMapping("/getMovie")
    public ApiResponse getMovie(Integer movieId){
        return null;
    }

    @ApiOperation(value = "上传封面文件", response = ApiResponse.class)
    @PostMapping("/uploadCover")
    public ApiResponse uploadCover(MultipartFile file){
        return null;
    }

}
