package com.bjpowernode.crm.pojo;

import lombok.Data;

@Data
public class User {
    private String id;
    private String deptId;
    private String loginAct;
    private String name;
    private String loginPwd;
    private String email;
    private String expireTime;
    private String lockStatus;
    private String allowIps;
    private String createBy;
    private String createTime;
    private String editBy;
    private String editTime;

}
