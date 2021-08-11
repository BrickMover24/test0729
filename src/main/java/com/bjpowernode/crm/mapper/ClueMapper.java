package com.bjpowernode.crm.mapper;

import com.bjpowernode.crm.pojo.Activity;
import com.bjpowernode.crm.pojo.Clue;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ClueMapper {

    //map中的值通过#{data.key}获取值
    List<Clue> getData(@Param("start") Integer start,
                       @Param("length") Integer rowsPerPage,
                       @Param("data") Map<String, String> data);

    //map中的值通过#{key}取值
    Integer getCount(@Param("data") Map<String, String> data);

    Clue get(String id);

}
