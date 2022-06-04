package com.evol.exhandle;

import com.evol.exhandle.exception.MyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ExTestController {

//    @GetMapping("/error")
//    public String error(){
//        return "error";
//    }

    @GetMapping("/400")
    public String error400(){
        return "400";
    }

    @GetMapping("/500")
    public String error500(){
        return "500";
    }

    @GetMapping("/ex/index")
    @ResponseBody
    public String exIndex(){
        int n =1 /0;
        return "SUCCESS";
    }

    @GetMapping("/excustom")
    @ResponseBody
    public String exCustom(){
        throw new RuntimeException("excustom1");
    }

    @GetMapping("/excustomp")
    public String exCustomPage(){
        if(1 ==1 ){
            throw new MyException("excustom1");
        }
        return "error";
    }
}
