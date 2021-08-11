package com.bjpowernode.crm.web.controller;

import com.bjpowernode.crm.pojo.User;
import com.bjpowernode.crm.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
public class CommonsController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private UserService userService;


    @RequestMapping("/")
    public String root(@CookieValue(value = "J9K97jo", required = false) String username,
                       @CookieValue(value = "kOLhj771", required = false) String password) {

        if (StringUtils.isNoneBlank(username, password)) {
            String ip = request.getRemoteAddr();
            User user = userService.getUserForAutoLogin(username, password, ip);
            if (user != null) {
                request.getSession().setAttribute("loginUser", user);
                return "redirect:/workbench/index.jsp";
            }
        }
        return "redirect:/login.jsp";
    }

    //下拉选项服务接口
    @GetMapping("options.json")
    @ResponseBody
    public List options(String typeCode,HttpServletRequest request){
        Map<String,List> dicMap=(Map<String,List>)request.getServletContext().getAttribute("dicMap");
        return dicMap.get(typeCode);
    }

}
