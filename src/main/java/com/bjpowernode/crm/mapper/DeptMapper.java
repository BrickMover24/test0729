package com.bjpowernode.crm.mapper;

import com.bjpowernode.crm.pojo.Dept;
import com.bjpowernode.crm.pojo.Value;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.List;

public interface DeptMapper {

    Integer getCount();
    List<Dept> getData(@Param("start") Integer start,
                 @Param("length") Integer length);
    Dept get(Serializable id);
    void save(Dept dept);
    void update(Dept dept);
    void delete(String[] id);
}
