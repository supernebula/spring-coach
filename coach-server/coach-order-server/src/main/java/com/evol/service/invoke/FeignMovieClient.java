package com.evol.service.invoke;

import com.evol.base.client.UserDTO;
import com.evol.domain.dto.MovieDetailDTO;
import com.evol.service.invoke.fallback.FeignMovieClientFallbackImpl;
import com.evol.web.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "coach-business-server",fallback = FeignMovieClientFallbackImpl.class)
public interface FeignMovieClient {

    @GetMapping(value = "/inner/movie/get")
    ApiResponse<MovieDetailDTO> getMovie(@RequestParam(name = "movieId") Integer movieId);

    @GetMapping(value = "/inner/movie/get2")
    ApiResponse<MovieDetailDTO> getMovie2();

    @PostMapping( value = "/users/upsert/{userId}", consumes = "application/json")
    UserDTO update(@PathVariable("userId") Long userId, UserDTO user);

}

