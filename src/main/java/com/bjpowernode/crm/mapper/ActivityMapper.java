package com.bjpowernode.crm.mapper;

import com.bjpowernode.crm.pojo.Activity;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ActivityMapper {

   //map中的值通过#{data.key}获取值
    List<Activity> getData(@Param("start") Integer start,
                 @Param("length") Integer rowsPerPage,
                 @Param("data") Map<String, String> data);

    //map中的值通过#{key}取值
    Integer getCount(@Param("data") Map<String, String> data);

    void save(Activity activity);

    Activity get(String id);

    void update(Activity activity);

    List<Activity> getAll();

    void saveList(List data);

    List<Activity> getByClueId(String clueId);

    List<Activity> getAll(String clueId);

}
