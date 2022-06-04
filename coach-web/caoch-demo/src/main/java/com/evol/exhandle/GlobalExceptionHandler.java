package com.evol.exhandle;

import com.evol.exhandle.exception.MyException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    //返回错误页面
    @ExceptionHandler(RuntimeException.class)
    public ModelAndView handle(RuntimeException ex){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("errorPage");
        modelAndView.addObject("code", 500);
        modelAndView.addObject("msg", ex.getMessage());
        return modelAndView;
    }

    //返回JSON
    @ResponseBody
    @ExceptionHandler(MyException.class)
    public Map<String, Object> handleMyException(MyException ex){
        Map<String, Object> map = new HashMap<>();
        map.put("code", 500);
        map.put("message", ex.getMsg());
        return map;
    }
}
