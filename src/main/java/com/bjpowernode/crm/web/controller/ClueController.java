package com.bjpowernode.crm.web.controller;


import com.bjpowernode.crm.pojo.Clue;
import com.bjpowernode.crm.pojo.Page;
import com.bjpowernode.crm.service.ClueService;
import com.bjpowernode.crm.web.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@ResponseBody
@RequestMapping("clue")
public class ClueController {

     @Autowired
     private ClueService service;

       @GetMapping("page.json")
      public Page page(Page page) {
          service.getPage(page);
          return page;
      }


    @GetMapping("get.json")
    public Clue get(String id) {
        return service.get(id);
    }

      @GetMapping("rel.do")
      public Map rel(String clueId,String[] actIds){
             service.rel(clueId,actIds);
          return Result.SUCCESS;
      }

}
