package com.bjpowernode.crm.pojo;

import com.bjpowernode.crm.utlis.UUIDUtil;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

@Data
public class Transaction implements Serializable {
    private String id;
    private String owner;
    private String amountOfMoney;
    private String name;
    private String expectedClosingDate;
    private String customerId;
    private String stage;
    private String type;
    private String source;
    private String activityId;
    private String contactsId;
    private String description;
    private String createBy;
    private String createTime;
    private String editBy;
    private String editTime;
    private String contactSummary;
    private String nextContactTime;

    // 对象引用，需要在查询时将这些对象相关的数据查询出来，并进行封装
    private Customer customer;
    private Contacts contacts;
    private Activity activity;

    public String getId() {
        if (StringUtils.isBlank(id)) {
            return UUIDUtil.getUUID();
        }
        return id;
    }
}
