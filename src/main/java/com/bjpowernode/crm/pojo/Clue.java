package com.bjpowernode.crm.pojo;

import com.bjpowernode.crm.utlis.UUIDUtil;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

@Data
public class Clue {
   private String  id;
   private String  owner;
   private String  company;
   private String  phone;
   private String  website;
   private String  description;
   private String  fullName;
   private String  appellation;
   private String  source;
   private String  email;
   private String  mphone;
   private String  job;
   private String  state;
   private String  createBy;
   private String  createTime;
   private String  editBy;
   private String  editTime;
   private String  contactSummary;
   private String  nextContactTime;
   private String  address;

   public String getId() {
      if (StringUtils.isBlank(id)) {
         return UUIDUtil.getUUID();
      }
      return id;
   }

}
