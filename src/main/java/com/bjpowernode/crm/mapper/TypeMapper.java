package com.bjpowernode.crm.mapper;

import com.bjpowernode.crm.pojo.Type;

import java.util.List;

public interface TypeMapper {

    List<Type> getAll();
    Type get(String code);
    void save(Type type);
    void update(Type type);
    void delete(String[] ids);
}
