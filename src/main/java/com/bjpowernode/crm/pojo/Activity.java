package com.bjpowernode.crm.pojo;


import com.bjpowernode.crm.utlis.UUIDUtil;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

@Data
public class Activity {
   private String id;
   private String owner;
   private String name;
   private String startDate;
   private String endDate;
   private String cost;
   private String description;
   private String createBy;
   private String createTime;
   private String editBy;
   private String editTime;

    public String getId() {
        if (StringUtils.isBlank(id)) {
            return UUIDUtil.getUUID();
        }
        return id;
    }
}
