package com.bjpowernode.crm.service.impl;

import com.bjpowernode.crm.mapper.TransactionHistoryMapper;
import com.bjpowernode.crm.mapper.TransactionMapper;
import com.bjpowernode.crm.pojo.Page;
import com.bjpowernode.crm.pojo.Transaction;
import com.bjpowernode.crm.pojo.TransactionHistory;
import com.bjpowernode.crm.service.TreansactionService;
import com.bjpowernode.crm.utlis.DateTimeUtil;
import com.bjpowernode.crm.utlis.UUIDUtil;
import org.omg.CosNaming.IstringHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.unit.DataUnit;

import java.util.List;

@Service
public class TreansactionServiceImpl implements TreansactionService {

    @Autowired
    private TransactionMapper mapper;
    @Autowired
    private TransactionHistoryMapper transactionHistoryMapper;

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

    public void changeStage(String id, String stage,String editBy) {

        String editTimme = DateTimeUtil.getSysTimeStr();
        //修改交易状态
       Transaction transaction= mapper.get(id);
       transaction.setStage(stage);
       transaction.setEditBy(editBy);
       transaction.setEditTime(editTimme);
        mapper.update(transaction);
        //修改交易历史
        TransactionHistory history = new TransactionHistory();

        history.setId(UUIDUtil.getUUID());
        history.setAmountOfMoney(transaction.getAmountOfMoney());
        history.setEditBy(editBy);
        history.setEditTime(editTimme);
        history.setExpectedClosingDate(transaction.getExpectedClosingDate());
        history.setTransactionId(id);
        transactionHistoryMapper.add(history);
    }

    public List getCharts() {
        return mapper.getCharts();
    }
}
