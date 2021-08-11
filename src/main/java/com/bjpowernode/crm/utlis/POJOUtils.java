package com.bjpowernode.crm.utlis;

import com.bjpowernode.crm.pojo.User;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpSession;
import java.lang.reflect.InvocationTargetException;

public class POJOUtils {

    public static void initSave(Object o, HttpSession session){
        User user = (User)session.getAttribute("loginUser");
        String createBy = user.getLoginAct() + "|" + user.getName();
        String createTime = DateTimeUtil.getSysTimeStr();
        try {
            BeanUtils.setProperty(o,"createBy",createBy);
            BeanUtils.setProperty(o,"createTime",createTime);
            //BeanUtils.setProperty(o,"id",UUIDUtil.getUUID());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }

/*    public static void initEdit(Object o, HttpSession session){
        User user = (User)session.getAttribute("loginUser");
        String editBy = user.getLoginAct() + "|" + user.getName();
        String editTime = DateTimeUtil.getSysTimeStr();
        try {
            BeanUtils.setProperty(o,"editBy",editBy);
            BeanUtils.setProperty(o,"editTime",editTime);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }*/

}
