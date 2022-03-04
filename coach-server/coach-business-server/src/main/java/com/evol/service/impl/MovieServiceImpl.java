package com.evol.service.impl;

import com.evol.domain.PageBase;
import com.evol.domain.dto.MoiveUpsertDto;
import com.evol.domain.model.Movie;
import com.evol.domain.model.MovieExample;
import com.evol.domain.request.MovieQueryRequest;
import com.evol.mapper.MovieMapper;
import com.evol.service.MovieService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieMapper movieMapper;

    @Override
    public Integer createMovie(MoiveUpsertDto dto) {
        Movie movie = new Movie();
        movie.setName(dto.getName());
        movie.setForeignName(dto.getForeignName());

        LocalDate.parse(dto.getReleaseDate());
        Date releaseDate =
                Date.from(LocalDate.parse(dto.getReleaseDate()).atStartOfDay(ZoneId.systemDefault()).toInstant());
        movie.setReleaseDate(releaseDate);
        movie.setMinutes(dto.getMinutes());
        movie.setDiscountCount(dto.getDiscountCount() == null ? 0 : dto.getDiscountCount());
        movie.setDiscountPrice(dto.getDiscountPrice());
        movie.setReleaseRegion(dto.getReleaseRegion());
        movie.setSpaceType(dto.getSpaceType());
        movie.setCoverUri(dto.getCoverUri());
        movie.setImages(dto.getImages() == null ? "" : dto.getImages());
        movie.setDescription(dto.getDescription());
        movie.setRatings(dto.getRatings() == null ? 0 : dto.getRatings());
        movie.setLanguage(dto.getLanguage());
        movie.setCreateTime(new Date());
        movieMapper.insert(movie);
        return movie.getId();
    }

    @Override
    public Integer updateMovie(Integer movieId, MoiveUpsertDto dto) {
        Movie movie = movieMapper.selectByPrimaryKey(movieId);
        if(movie == null){
            return null;
        }
        movie.setName(dto.getName());
        movie.setForeignName(dto.getForeignName());
        Date releaseDate = new Date();
        if(!org.apache.commons.lang3.StringUtils.isBlank(dto.getReleaseDate())){
            releaseDate =
                    Date.from(LocalDate.parse(dto.getReleaseDate()).atStartOfDay(ZoneId.systemDefault()).toInstant());
        }

        movie.setReleaseDate(releaseDate);
        movie.setMinutes(dto.getMinutes());
        movie.setDiscountCount(dto.getDiscountCount() == null ? 0 : dto.getDiscountCount());
        movie.setDiscountPrice(dto.getDiscountPrice());
        movie.setReleaseRegion(dto.getReleaseRegion() + "");
        movie.setSpaceType(dto.getSpaceType());
        movie.setCoverUri(dto.getCoverUri() + "");
        movie.setImages(dto.getImages() + "");
        movie.setDescription(dto.getDescription() + "");
        movie.setRatings(dto.getRatings() == null ? 0 : dto.getRatings());
        movie.setLanguage(dto.getLanguage() + "");
        movie.setUpdateTime(new Date());

        return movieMapper.updateByPrimaryKey(movie);
    }

    @Override
    public Integer deleteMoive(Integer movieId) {
        return movieMapper.deleteByPrimaryKey(movieId);
    }

    @Override
    public PageBase<Movie> queryPage(MovieQueryRequest movieQueryRequest) {

        Page<Object> page = PageHelper.startPage(movieQueryRequest.getPage(), movieQueryRequest.getPageSize(),"id asc");
        //Page page =  PageHelper.startPage(movieQueryRequest.getPageNo(), movieQueryRequest.getPageSize());

        MovieExample movieExample = new MovieExample();
        if(!StringUtils.isBlank(movieQueryRequest.getName())){
            movieExample.createCriteria().andNameLike("%" + movieQueryRequest.getName() + "%");
        }


        List<Movie> movieList = movieMapper.selectByExample(movieExample);
        if(CollectionUtils.isEmpty(movieList)){
            return PageBase.create(page.getTotal(), movieQueryRequest.getPage(), movieQueryRequest.getPageSize(),
                    new ArrayList<>());
        }
        return PageBase.create(page.getTotal(), movieQueryRequest.getPage(), movieQueryRequest.getPageSize(),movieList);
    }

    @Override
    public Movie getMovie(Integer movieId){
        return movieMapper.selectByPrimaryKey(movieId);
    }
}
