package com.bjpowernode.crm.web.controller;

import com.bjpowernode.crm.pojo.Dept;
import com.bjpowernode.crm.pojo.Page;
import com.bjpowernode.crm.service.DeptService;
import com.bjpowernode.crm.web.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("dept")
public class DeptController {

    @Autowired
    private DeptService Service;

    @GetMapping("page.json")
    public Page page(Page page){
        Service.getPage(page);
        return page;
    }

    @GetMapping("get.json")
    public Dept get(String id){
        return Service.get(id);
    }

    @PostMapping("save.do")
    public Map save(Dept dept){
       Service.save(dept);
        return Result.SUCCESS;
    }

    @PutMapping("update.do")
    public Map update(Dept dept) {
        Service.update(dept);
        return Result.SUCCESS;
    }

    @DeleteMapping("delete.do")
    public Map delete(String[] ids){
        Service.delete(ids);
        return Result.SUCCESS;
    }
}
