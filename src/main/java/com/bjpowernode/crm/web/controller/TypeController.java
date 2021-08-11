package com.bjpowernode.crm.web.controller;


import com.bjpowernode.crm.pojo.Type;
import com.bjpowernode.crm.service.TypeService;
import com.bjpowernode.crm.service.UserService;
import com.bjpowernode.crm.web.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("type")
public class TypeController {

    @Autowired
    private TypeService typeService;

    @GetMapping("list.json")
    public List list(){
        return typeService.getAll();
    }

    @GetMapping("get.json")
    public Type get(String code){
        return typeService.get(code);
     }

     @PostMapping("save.do")
     public Map save(Type type){
        typeService.save(type);
        return Result.SUCCESS;
     }

     @PutMapping("update.do")
     public Map update(Type type){
         typeService.update(type);
         return Result.SUCCESS;
     }

    @DeleteMapping("delete.do")
    public Map delete(String[] ids) {
        typeService.delete(ids);
        return Result.SUCCESS;
    }
}
