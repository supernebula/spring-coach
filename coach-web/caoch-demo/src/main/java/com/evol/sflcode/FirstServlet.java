package com.evol.sflcode;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


public class FirstServlet extends HttpServlet {

    //处理get请求
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("FirstServlet执行doGet");
        doPost(req, resp);
    }

    //处理post请求
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("FirstServlet执行doPost");
        PrintWriter out = resp.getWriter();
        out.println("FirstServlet outer");
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        System.out.println("FirstServlet init");
        super.init(config);
    }

    @Override
    public void destroy(){
        System.out.println("FirstServlet destroy");
        super.destroy();
    }
}
