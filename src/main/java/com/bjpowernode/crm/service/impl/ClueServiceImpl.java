package com.bjpowernode.crm.service.impl;

import com.bjpowernode.crm.mapper.ClueActivityMapper;
import com.bjpowernode.crm.mapper.ClueMapper;
import com.bjpowernode.crm.pojo.Clue;
import com.bjpowernode.crm.pojo.ClueActivity;
import com.bjpowernode.crm.pojo.Page;
import com.bjpowernode.crm.service.ClueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClueServiceImpl implements ClueService {
    @Autowired
    private ClueMapper mapper;

    @Autowired
    private ClueActivityMapper clueActivityMapper;

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


    public void rel(String clueId, String[] actIds) {

        // 多对多关系的维护，一般是先清除关联关系
        clueActivityMapper.unRel(clueId);

        // 添加新的关系
        if ( actIds != null && actIds.length > 0 ) {
            List data = new ArrayList();
            for (String actId : actIds) {
                ClueActivity ca = new ClueActivity();
                ca.setClueId(clueId);
                ca.setActivityId(actId);
                data.add(ca);
            }
            clueActivityMapper.saveList(data);
        }

    }

    public Clue get(String id) {
        return mapper.get(id);
    }
}
