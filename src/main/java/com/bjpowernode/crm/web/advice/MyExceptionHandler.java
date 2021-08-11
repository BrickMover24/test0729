package com.bjpowernode.crm.web.advice;

import com.bjpowernode.crm.exception.LoginException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice // 对Controller进行增强，可以对异常进行集中处理
public class MyExceptionHandler {
    // 当前方法可以捕获LoginException类型的异常
    @ExceptionHandler(LoginException.class)
    @ResponseBody
    // 方法中的Exception为捕获到的异常对象
    public Map LoginException(Exception e) {
        return new HashMap(){{
            put("success", false);
            put("msgs", e.getMessage());
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
