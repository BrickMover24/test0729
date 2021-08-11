package com.bjpowernode.crm.web;

import java.util.HashMap;
import java.util.Map;

public class Result {
    public static final Map SUCCESS= new HashMap(){
        {
            put("success",true);
            put("msges","操作成功!!");
        }
    };
}
