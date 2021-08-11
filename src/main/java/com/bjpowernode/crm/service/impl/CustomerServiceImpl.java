package com.bjpowernode.crm.service.impl;

import com.bjpowernode.crm.mapper.CustomerMapper;
import com.bjpowernode.crm.pojo.Customer;
import com.bjpowernode.crm.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerMapper customerMapper;

    public List getByName(String name) {
        return customerMapper.getByName(name);
    }
}
