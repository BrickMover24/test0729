package com.bjpowernode.crm.service.impl;

import com.bjpowernode.crm.mapper.TransactionMapper;
import com.bjpowernode.crm.pojo.Page;
import com.bjpowernode.crm.service.TreansactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TreansactionServiceImpl implements TreansactionService {

    @Autowired
    private TransactionMapper mapper;

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
}
