//package com.evol.controller;
//
//import com.evol.provider.JwtProvider;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.validation.Valid;
//
//@RestController
//@RequestMapping("/api/auth")
//public class AuthController {
//
//    @Autowired
//    private AuthService authService;
//    @Autowired
//    private JwtProvider jwtProvider;
//
//    /**
//     * 登录方法
//     * <p>
//     * loginAccount：user
//     * password：123456
//     *
//     * @param loginInfo
//     * @return ApiResult
//     */
//    @PostMapping("/login")
//    public ApiResult login(@Valid @RequestBody LoginInfo loginInfo) {
//        return authService.login(loginInfo.getLoginAccount(), loginInfo.getPassword());
//    }
//
//    @PostMapping("/logout")
//    public ApiResult logout() {
//        return authService.logout();
//    }
//
//    @PostMapping("/refresh")
//    public ApiResult refreshToken(HttpServletRequest request) {
//        return authService.refreshToken(jwtProvider.getToken(request));
//    }
//
//
//}
