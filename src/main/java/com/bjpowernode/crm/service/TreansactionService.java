package com.bjpowernode.crm.service;

import com.bjpowernode.crm.pojo.Page;

import java.util.List;

public interface TreansactionService {

    void getPage(Page page);

    void changeStage(String id, String stage,String editBy);

    List getCharts();

}
