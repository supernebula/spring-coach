package com.evol.service;

import com.evol.web.ApiResponse;

public interface AuthService {

    ApiResponse login(String loginAccount, String password);

    ApiResponse logout();

    ApiResponse refreshToken(String token);
}
