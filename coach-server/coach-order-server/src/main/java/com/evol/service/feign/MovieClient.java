//package com.evol.service.feign;
//
//import com.evol.base.client.UserDTO;
//import com.evol.domain.dto.MovieDetailDTO;
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//
//@FeignClient("coach-business-server")
//public interface MovieClient {
//
//    @RequestMapping(method = RequestMethod.GET, value = "/inner/movie/get")
//    MovieDetailDTO getMovie(@PathVariable("movieId") Long movieId);
//
//    @RequestMapping(method = RequestMethod.POST, value = "/users/upsert/{userId}", consumes = "application/json")
//    UserDTO update(@PathVariable("userId") Long userId, UserDTO user);
//
//}
//
