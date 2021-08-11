package com.bjpowernode.crm.service;

import com.bjpowernode.crm.pojo.Clue;
import com.bjpowernode.crm.pojo.Page;


public interface ClueService {

    void getPage(Page page);

    void rel(String clueId, String[] actIds);

    Clue get(String id);
}
