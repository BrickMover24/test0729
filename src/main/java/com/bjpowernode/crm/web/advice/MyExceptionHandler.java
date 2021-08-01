package com.bjpowernode.crm.web.advice;

import com.bjpowernode.crm.exception.LoginException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class MyExceptionHandler {
    // 捕获登录相关的异常
    @ExceptionHandler(LoginException.class)
    @ResponseBody
    public Map LoginException(Exception e) {
        return new HashMap(){{
            put("success", false);
            put("msg", e.getMessage());
        }};
    }

    // 其它异常
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Map Exception(Exception e) {
        e.printStackTrace();
        return new HashMap(){{
            put("success", false);
            put("msg", e.getMessage());
        }};
    }
}
