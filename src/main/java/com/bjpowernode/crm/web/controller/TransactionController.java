package com.bjpowernode.crm.web.controller;

import com.bjpowernode.crm.pojo.Page;
import com.bjpowernode.crm.service.TreansactionService;
import com.bjpowernode.crm.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
    @RequestMapping("transaction")
public class TransactionController {

    @Autowired
    private TreansactionService treansactionService;

    @Autowired
    private CustomerService customerService;

    @GetMapping("page.json")
    public Page page(Page page){
        treansactionService.getPage(page);
        return page;
    }

    @RequestMapping("getCustomerName.do")
    public List  getCustomerName(String name){
        return customerService.getByName(name);
    }

    }
