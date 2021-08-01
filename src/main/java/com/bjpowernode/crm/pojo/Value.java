package com.bjpowernode.crm.pojo;
import com.bjpowernode.crm.utlis.UUIDUtil;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

@Data
public class Value {
    private String id;
    private String value;
    private String text;
    private String orderNo;
    private String typeCode;

    public String getId() {
        if (StringUtils.isBlank(id)) {
            return UUIDUtil.getUUID();
        }
        return id;
    }
}
