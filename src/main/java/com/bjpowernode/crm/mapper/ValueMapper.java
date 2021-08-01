package com.bjpowernode.crm.mapper;

import com.bjpowernode.crm.pojo.Value;

import java.io.Serializable;
import java.util.List;

public interface ValueMapper {
    List<Value> getAll();
    Value get(Serializable id);
    void save(Value value);
    void update(Value value);
  void delete(Serializable...id);
}
