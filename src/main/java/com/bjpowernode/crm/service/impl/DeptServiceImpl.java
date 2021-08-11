package com.bjpowernode.crm.service.impl;

import com.bjpowernode.crm.mapper.DeptMapper;
import com.bjpowernode.crm.pojo.Dept;
import com.bjpowernode.crm.pojo.Page;
import com.bjpowernode.crm.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper mapper;

    public void getPage(Page page) {
       //总记录数
        Integer totalRows= mapper.getCount();
        if (totalRows==0){
            return;
        }
        // 总页数: (总记录数 - 1) / 每页条数 + 1
       Integer  totalPages=(totalRows-1)/page.getRowsPerPage()+1;

        //当前页数据
        Integer start= (page.getCurrentPage()-1)*page.getRowsPerPage();
        List data= mapper.getData(start,page.getRowsPerPage());

        page.setData(data);
        page.setTotalPages(totalPages);
        page.setTotalRows(totalRows);
    }

    public Dept get(Serializable id){return mapper.get(id);}

    public void save(Dept dept){mapper.save(dept);}

    public void update(Dept dept){mapper.update(dept);}

    public void delete(String[] ids){mapper.delete(ids);}
}
