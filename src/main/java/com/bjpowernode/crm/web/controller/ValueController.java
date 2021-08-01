package com.bjpowernode.crm.web.controller;

import com.bjpowernode.crm.pojo.Value;
import com.bjpowernode.crm.service.ValueService;
import com.bjpowernode.crm.web.Result;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("value")
public class ValueController {

    @Autowired
    private ValueService valueService;

    @GetMapping("list.json")
    public List list(){
        return valueService.getAll();
    }
    @GetMapping("get.json")
    public Value get(String id){
       return valueService.get(id);

    }
    @PostMapping("save.do")
    public Map save(Value value){
        valueService.save(value);
        return Result.SUCCESS;
    }
    @PutMapping("update.do")
    public Map update(Value value){
        valueService.update(value);
        return Result.SUCCESS;
    }
    @DeleteMapping("delete.do")
    public Map dalete(String[] ids){
        valueService.delete(ids);
        return Result.SUCCESS;
    }
}
