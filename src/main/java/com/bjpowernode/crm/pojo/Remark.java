package com.bjpowernode.crm.pojo;

import com.bjpowernode.crm.utlis.UUIDUtil;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

@Data
public class Remark {
    private String id;
    private String notePerson;
    private String noteContent;
    private String noteTime;
    private String editPerson;
    private String editTime;
    private String editFlag;
    private String marketingActivitiesId;

    public String getId() {
        if (StringUtils.isBlank(id)) {
            return UUIDUtil.getUUID();
        }
        return id;
    }
}
