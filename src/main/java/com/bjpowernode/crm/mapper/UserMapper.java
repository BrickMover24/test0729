package com.bjpowernode.crm.mapper;

import com.bjpowernode.crm.pojo.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    User getUser(@Param("loginAct") String username, @Param("loginPwd") String password);
}
