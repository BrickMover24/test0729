package com.bjpowernode.crm.service.impl;

import com.bjpowernode.crm.mapper.TypeMapper;
import com.bjpowernode.crm.pojo.Type;
import com.bjpowernode.crm.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {

   @Autowired
   private TypeMapper typeMapper;

    public List getAll() {
        return typeMapper.getAll();
    }

    public Type get(String code) {
        return typeMapper.get(code);
    }

    public void save(Type type) {
        typeMapper.save(type);
    }

    public void update(Type type) {
        typeMapper.update(type);
    }

    public void delete(String[] ids) {
        typeMapper.delete(ids);
    }
}
