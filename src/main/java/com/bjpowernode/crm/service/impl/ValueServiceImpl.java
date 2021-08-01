package com.bjpowernode.crm.service.impl;

import com.bjpowernode.crm.mapper.ValueMapper;
import com.bjpowernode.crm.pojo.Value;
import com.bjpowernode.crm.service.ValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

@Service
public class ValueServiceImpl  implements ValueService {

    @Autowired
    private ValueMapper valueMapper;

    public List<Value> getAll() {
        return valueMapper.getAll();
    }

    public Value get(Serializable id) {
        return valueMapper.get(id);
    }

    public void save(Value value) {
        valueMapper.save(value);
    }

    public void update(Value value) {

        valueMapper.update(value);
    }

    public void delete(Serializable... id) {
           valueMapper.delete(id);
    }
}
