package com.bjpowernode.crm.mapper;

import com.bjpowernode.crm.pojo.Transaction;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface TransactionMapper {
    //map中的值通过#{data.key}获取值
    List getData(@Param("start") Integer start,
                              @Param("length") Integer rowsPerPage,
                              @Param("data") Map<String, String> data);

    //map中的值通过#{key}取值
    Integer getCount(@Param("data") Map<String, String> data);


    Transaction get(String id);

    void update(Transaction transaction);

    void save(Transaction transaction);

    List getCharts();

}
