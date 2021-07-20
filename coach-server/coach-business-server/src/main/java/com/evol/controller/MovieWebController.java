package com.evol.controller;

import com.evol.domain.PageBase;
import com.evol.domain.dto.MoiveUpsertDto;
import com.evol.domain.model.Movie;
import com.evol.domain.request.MovieQueryRequest;
import com.evol.enums.ApiResponseEnum;
import com.evol.service.MovieService;
import com.evol.web.ApiResponse;
import com.evol.web.FileUploadUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

/**
 * @author admin
 */
@Api(tags = "电影管理")
@RestController
@RequestMapping("/web/movie")
@CrossOrigin
public class MovieWebController {

    @Autowired
    private  MovieService movieService;

    @Value("${file.upload.pic.path}")
    private String uploadFilePath;
//
//    @Value("${file.access.pic.url}")
//    private String uploadFileUrl = "E:\\upload\\";

    @CrossOrigin(value = "http://localhost:8080")
    @ApiOperation(value = "添加电影", response = ApiResponse.class)
    @PostMapping("/create")
    public ApiResponse createMovie(MoiveUpsertDto param){
        Integer id =  movieService.createMovie(param);
        return ApiResponse.success(id);
    }

    @CrossOrigin(value = "http://localhost:8080")
    @ApiOperation(value = "删除电影", response = ApiResponse.class)
    @PostMapping("/delete")
    public ApiResponse deleteMovie(Integer movieId){
        Integer num = movieService.deleteMoive(movieId);
        return ApiResponse.success(num);
    }

    @CrossOrigin(value = "http://localhost:8080")
    @ApiOperation(value = "修改电影", response = ApiResponse.class)
    @PostMapping("/update")
    public ApiResponse updateMovie(Integer movieId, MoiveUpsertDto param){
        Integer id =  movieService.updateMovie(movieId, param);
        return ApiResponse.success(id);
    }

    @CrossOrigin(value = "http://localhost:8080")
    @ApiOperation(value = "查询电影记录", response = ApiResponse.class)
    @GetMapping("/query")
    public ApiResponse queryMovie(MovieQueryRequest reqParam){
        PageBase<Movie> pageList = movieService.queryPage(reqParam);
        return ApiResponse.success(pageList);
    }

    @CrossOrigin(value = "http://localhost:8080")
    @ApiOperation(value = "获取电影", response = ApiResponse.class)
    @PostMapping("/get")
    public ApiResponse getMovie(Integer movieId){
        Movie movie = movieService.getMovie(movieId);
        return ApiResponse.success(movie);
    }

    @CrossOrigin(value = "http://localhost:8080")
    @ApiOperation(value = "上传封面文件", response = ApiResponse.class)
    @PostMapping("/uploadCover")
    public ApiResponse uploadCover(@RequestParam("files") MultipartFile files[]){
        List<String> filenames = FileUploadUtil.SaveFile(files, uploadFilePath);
        return ApiResponse.success(filenames);
    }

}
