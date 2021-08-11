package com.bjpowernode.crm.pojo;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class Page {

   private Integer  currentPage =1;        // 页码
   private Integer  rowsPerPage =10;      // 每页显示的记录条数
   private Integer  maxRowsPerPage=100;        // 每页最多显示的记录条数
   private Integer  totalPages;     // 总页数
   private Integer  totalRows;      // 总记录数
   private Integer  visiblePageLinks=10;      // 显示几个卡片
   private List data; //当前页数据

   //搜索条件
   private Map Search;


}
