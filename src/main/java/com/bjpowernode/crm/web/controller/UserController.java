package com.bjpowernode.crm.web.controller;

import com.bjpowernode.crm.pojo.User;
import com.bjpowernode.crm.service.UserService;
import com.bjpowernode.crm.utlis.MD5Util;
import com.bjpowernode.crm.web.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@RestController//@Controller+@ResponseBody
@RequestMapping("user")
public class UserController {
    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpServletResponse response;

    @Autowired
    private UserService userService;

    @PostMapping("login.do")
    public Map login(String username, String password, Boolean autoLogin) {

        String ip = request.getRemoteAddr();

        System.out.println(ip);

        User user = userService.getUser(username, password, ip);

        request.getSession().setAttribute("loginUser", user);

        if (autoLogin) {
            int maxAge = 3600 * 24 * 10;
            Cookie cookie1 = new Cookie("J9K97jo", username);
            cookie1.setMaxAge(maxAge);
            cookie1.setPath("/");

            // 由于Cookie是保存在客户端的磁盘中，因此对于密码类的数据，需要进行加密保存
            // 加密算法可以使用公司内部的一些算法，也可以使用某些不可逆的加密算法如MD5
            Cookie cookie2 = new Cookie("kOLhj771", MD5Util.getMD5(password));
            cookie2.setMaxAge(maxAge);
            cookie2.setPath("/");

            response.addCookie(cookie1);
            response.addCookie(cookie2);
        }
        return Result.SUCCESS;
    }

    @GetMapping("owners.json")
    public List owners(){
        return userService.getOwners();
    }

}
