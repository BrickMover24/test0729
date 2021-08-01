package com.bjpowernode.crm.web.controller;

import com.bjpowernode.crm.pojo.User;
import com.bjpowernode.crm.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
public class CommonsController {

    @Resource
    private UserService userService;

    @Resource
   private HttpServletRequest request;

    @RequestMapping("/")
    public String root(@CookieValue(value = "6SD5S3",required = false) String username,
                       @CookieValue(value = "7J2Y9L",required = false) String password) {

        System.out.println("欢迎访问CRM系统首页");
        //isNoneBlank:都不为空(null,"",空白字符)
        if (StringUtils.isNoneBlank(username, password)) {
            String ip = request.getRemoteAddr();
            User user = userService.getUserForAuto(username, password, ip);
            if (user!=null){
                request.getSession().setAttribute("loginUser", user);
                return "redirect:/workbench/index.jsp";
            }
        }
        return "redirect:/login.jsp";
    }
}

