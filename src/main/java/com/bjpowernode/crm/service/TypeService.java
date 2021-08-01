package com.bjpowernode.crm.service;

import com.bjpowernode.crm.pojo.Type;

import java.util.List;

public interface TypeService {

    List getAll();
    Type get(String code);
    void save(Type type);
    void update(Type type);
    void delete(String[] ids);
}
