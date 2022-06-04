package com.evol.exhandle.config;

import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.http.HttpStatus;

public class MyErrorPageRegistrar implements ErrorPageRegistrar {
    @Override
    public void registerErrorPages(ErrorPageRegistry errPagReg){
        ErrorPage page400 = new ErrorPage(HttpStatus.BAD_REQUEST, "/400");
        ErrorPage page500 = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/500");
        //page400指定路径 /400，page500指定路径 /500
        //，可以新建一个Controller的action分别映射 /400 和 /500
        errPagReg.addErrorPages(page400, page500);
    }
}
