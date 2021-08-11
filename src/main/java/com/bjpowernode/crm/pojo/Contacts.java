package com.bjpowernode.crm.pojo;

import com.bjpowernode.crm.utlis.UUIDUtil;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

@Data
public class Contacts  implements Serializable {
    private String id;
    private String owner;
    private String source;
    private String appellation;
    private String fullName;
    private String email;
    private String job;
    private String mphone;
    private String description;
    private String birth;
    private String customerId;
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
