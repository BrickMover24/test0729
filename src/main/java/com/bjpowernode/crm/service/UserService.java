package com.bjpowernode.crm.service;

import com.bjpowernode.crm.pojo.User;

public interface UserService {


    User getUser(String username, String password, String ip);
}
