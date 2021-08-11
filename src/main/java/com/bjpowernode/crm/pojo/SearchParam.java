package com.bjpowernode.crm.pojo;

import lombok.Data;

import java.util.Map;

@Data
public class SearchParam {

    //接收查询条件
    private Map<String,String> data;
    //接收分页相关的条件:当前也,每页条数
    private Map<String,Integer> page;
    public Map<String, String> getData() {
        return data;
    }

    public void setData(Map<String, String> data) {
        this.data = data;
    }

    public Map<String, Integer> getPage() {
        return page;
    }

    public void setPage(Map<String, Integer> page) {
        this.page = page;
    }

    @Override
    public String toString() {
        return "SearchParam{" +
                "data=" + data +
                ", page=" + page +
                '}';
    }
}
