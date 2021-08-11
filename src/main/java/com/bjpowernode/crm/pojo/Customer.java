package com.bjpowernode.crm.pojo;

import com.bjpowernode.crm.utlis.UUIDUtil;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

@Data
public class Customer implements Serializable {
    private String id;
    private String owner;
    private String name;
    private String phone;
    private String website;
    private String description;
    private String createBy;
    private String createTime;
    private String editBy;
    private String editTime;
    private String contactSummary;
    private String nextContactTime;
    private String address;
    public String getId() {
        if (StringUtils.isBlank(id)) {
            return UUIDUtil.getUUID();
        }
        return id;
    }
}
