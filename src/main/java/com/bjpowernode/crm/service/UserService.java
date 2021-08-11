package com.bjpowernode.crm.service;

import com.bjpowernode.crm.pojo.User;

import java.util.List;

public interface UserService {

    User getUser(String username, String password, String ip);

    User getUserForAutoLogin(String username, String password, String ip);

    List getOwners();
}
