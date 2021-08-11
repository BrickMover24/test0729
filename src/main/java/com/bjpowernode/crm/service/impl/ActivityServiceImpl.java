package com.bjpowernode.crm.service.impl;

import com.bjpowernode.crm.mapper.ActivityMapper;
import com.bjpowernode.crm.pojo.Activity;
import com.bjpowernode.crm.pojo.Page;
import com.bjpowernode.crm.pojo.SearchParam;
import com.bjpowernode.crm.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityServiceImpl  implements ActivityService {

    @Autowired
    private ActivityMapper mapper;

  public void getPage(Page page) {
      // 总记录数
      Integer totalRows = mapper.getCount(page.getSearch());
      if (totalRows == 0) return;
      // 总页数: (总记录数 - 1) / 每页条数 + 1
      Integer totalPages = (totalRows - 1) / page.getRowsPerPage() + 1;
      // 当前页数据
      Integer start = (page.getCurrentPage()-1)*page.getRowsPerPage();
      List data = mapper.getData(start, page.getRowsPerPage(), page.getSearch());

      page.setData(data);
      page.setTotalRows(totalRows);
      page.setTotalPages(totalPages);
  }

    public Activity get(String id) {
        return mapper.get(id);
    }

    public void update(Activity activity) {
        mapper.update(activity);
    }

    public List<Activity> getAll() {
        return mapper.getAll();
    }

    public void saveList(List data) {
        mapper.saveList(data);
    }

    public void save(Activity activity) {
        mapper.save(activity);
    }

    public List getByClueId(String clueId) {
        return mapper.getByClueId( clueId);
    }


}
