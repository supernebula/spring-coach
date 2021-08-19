package com.evol.controller;

import com.evol.web.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gateway")
public class GatewayController {
    @GetMapping(value = "/find")
    public ApiResponse find(){
        return ApiResponse.success("find one success");
    }
}
