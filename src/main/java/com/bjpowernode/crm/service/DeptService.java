package com.bjpowernode.crm.service;

import com.bjpowernode.crm.pojo.Dept;
import com.bjpowernode.crm.pojo.Page;

import java.io.Serializable;

public interface DeptService {

    void getPage(Page page);

    Dept get(Serializable id);

    void save(Dept dept);

    void update(Dept dept);

    void delete(String[] ids);
}
