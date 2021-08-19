package com.bjpowernode.crm.web.controller;

import com.bjpowernode.crm.pojo.Page;
import com.bjpowernode.crm.pojo.Transaction;
import com.bjpowernode.crm.pojo.User;
import com.bjpowernode.crm.service.TreansactionService;
import com.bjpowernode.crm.service.CustomerService;
import com.bjpowernode.crm.web.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

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

    //自动补全功能
    @RequestMapping("getCustomerName.do")
    public List  getCustomerName(String name){
        return customerService.getByName(name);
    }



    @RequestMapping("changeStage.do")
    public Map changeStage(String id, String stage, HttpSession session){

        User user = (User) session.getAttribute("loginUser");
        String editBy=user.getLoginAct()+"|"+user.getName();

        treansactionService.changeStage(id,stage,editBy);
        return Result.SUCCESS;
    }

    @GetMapping("charts.json")
    public List charts(){
        return treansactionService.getCharts();
    }

    }
