package com.evol.multidatas.controller;

import com.evol.multidatas.domain.model.Movie;
import com.evol.multidatas.domain.model.NetOrder;
import com.evol.multidatas.domain.model.User;
import com.evol.multidatas.service.business.MovieService;
import com.evol.multidatas.service.order.OrderService;
import com.evol.multidatas.service.user.MuUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/multi")
@RestController
public class MultiController {

    @Autowired
    private MuUserService muUserService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private MovieService movieService;

    @RequestMapping("/getUser")
    public User getUser(Integer id){
        return muUserService.findById(id);
    }

    @RequestMapping("/getNetOrder")
    public NetOrder getNetOrder(Integer id){
        return orderService.findById(id);
    }

    @RequestMapping("/getMovie")
    public Movie getMovie(Integer id){
        return movieService.findById(id);
    }
}
