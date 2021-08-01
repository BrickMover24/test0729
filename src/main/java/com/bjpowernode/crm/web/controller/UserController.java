package com.bjpowernode.crm.web.controller;


import com.bjpowernode.crm.pojo.User;
import com.bjpowernode.crm.service.UserService;
import com.bjpowernode.crm.utlis.MD5Util;
import com.bjpowernode.crm.web.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
@RequestMapping("user")
public class UserController {

    @Resource
    private UserService userService;
    @Resource
   private HttpServletRequest request;
    @Resource
    private HttpServletResponse response;

    @PostMapping("login.do")
    public Map login(String username,String password,Boolean autoLogin) {

        //获取IP地址
        String ip = request.getRemoteAddr();
        System.out.println(ip);
         User user= userService.getUser(username,password,ip);
        //将用户信息添加到session中
         request.getSession().setAttribute("loginUser",user);

         //是否免登录
        if (autoLogin){
           int age=3600*24*10;
            Cookie cookie1 = new Cookie("6SD5S3", username);
            cookie1.setMaxAge(age);
            cookie1.setPath("/");

            Cookie cookie2 = new Cookie("7J2Y9L", MD5Util.getMD5(password));
            cookie1.setMaxAge(age);
            cookie1.setPath("/");

            response.addCookie(cookie1);
            response.addCookie(cookie2);
        }
        return Result.SUCCESS;
    }

}
