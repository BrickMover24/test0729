package com.bjpowernode.crm.service.impl;

import com.bjpowernode.crm.exception.LoginException;
import com.bjpowernode.crm.mapper.UserMapper;
import com.bjpowernode.crm.pojo.User;
import com.bjpowernode.crm.service.UserService;
import com.bjpowernode.crm.utlis.DateTimeUtil;
import com.bjpowernode.crm.utlis.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.List;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    public User getUser(String username, String password, String ip) {

        //md5加密
        User user = userMapper.getUser(username,MD5Util.getMD5(password));

        if (user==null){
         throw new LoginException("用户名密码错误!");
       }
        //是否过期
        String expireTime = user.getExpireTime();
        if (StringUtils.isNotBlank(expireTime)) {
            long now = System.currentTimeMillis();
            try {
                long expire = DateTimeUtil.SDF10.parse(expireTime).getTime();
                if (now > expire) {
                    throw new LoginException("账号已过期！");
                }
            } catch (ParseException e) {
                e.printStackTrace();
                throw new LoginException("账号已过期！");
            }
        }

       //是否锁定
        if (!"1".equals(user.getLockStatus())){
            throw new LoginException("账号已锁定!");
        }

        //ip是否允许访问
        String allowIps = user.getAllowIps();
        if (StringUtils.isNotBlank(expireTime)){
            String[] ips = allowIps.split(",");
            List list = CollectionUtils.arrayToList(ips);

           if(!list.contains(ip)){
               throw new LoginException("ip不允许访问!");
           }
        }
        return user;
    }

    public User getUserForAutoLogin(String username, String password, String ip) {

        User user = userMapper.getUser(username,password);
        if (user==null){
            return null;
        }
        //是否过期
        String expireTime = user.getExpireTime();
        if (StringUtils.isNotBlank(expireTime)){
            long now = System.currentTimeMillis();
            try {
                long expire = DateTimeUtil.SDF19.parse(expireTime).getTime();
                if (now>expire){
                    return null;
                }
            } catch (ParseException e) {
                e.printStackTrace();
                return null;
            }
        }
        //是否锁定
        if (!"1".equals(user.getLockStatus())){
            return null;
        }

        //ip是否允许访问
        String allowIps = user.getAllowIps();

        if (StringUtils.isNotBlank(expireTime)){
            String[] ips = allowIps.split(",");
            List list = CollectionUtils.arrayToList(ips);

            if(!list.contains(ip)){
                return null;
            }
        }
        return user;
    }

    public List getOwners() {
        return userMapper.getOwners();
    }
}
