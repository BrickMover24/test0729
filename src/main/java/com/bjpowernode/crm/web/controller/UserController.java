package com.bjpowernode.crm.web.controller;


import com.bjpowernode.crm.pojo.User;
import com.bjpowernode.crm.service.UserService;
import com.bjpowernode.crm.web.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
   private HttpServletRequest request;

    @PostMapping("login.do")
    public Map login(String username,String password,Boolean autoLogin) {

        //获取IP地址
        String ip = request.getRemoteAddr();
         User user= userService.getUser(username,password,ip);
//将用户信息添加到session中
         request.getSession().setAttribute("loginUser",user);
        return Result.SUCCESS;
    }

}
