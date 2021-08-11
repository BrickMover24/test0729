package com.bjpowernode.crm.service;

import com.bjpowernode.crm.pojo.Activity;
import com.bjpowernode.crm.pojo.Page;
import com.bjpowernode.crm.pojo.SearchParam;

import java.util.List;

public interface ActivityService {

    void getPage(Page page);

    Activity get(String id);

    void update(Activity activity);

    List<Activity> getAll();

    void saveList(List data);

    void save(Activity activity);

    List getByClueId(String clueId);

}
