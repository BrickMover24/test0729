package com.bjpowernode.crm.pojo;

import com.bjpowernode.crm.utlis.UUIDUtil;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

@Data
public class ClueActivity {
     private String id;
     private String clueId;
     private String activityId;

    public String getId() {
        if (StringUtils.isBlank(id)) {
            return UUIDUtil.getUUID();
        }
        return id;
    }
}
