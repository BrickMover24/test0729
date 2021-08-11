package com.bjpowernode.crm.web.listener;

import com.bjpowernode.crm.pojo.Value;
import com.bjpowernode.crm.service.ValueService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.*;


@WebListener
public class LoadCache  implements ServletContextListener {

    @SneakyThrows
    public void contextInitialized(ServletContextEvent sce){

        System.out.println("开始读取数据字典缓存数据");
        //读取数组字典中的数据,保存到servletcontext中作为服务器缓存
        WebApplicationContext beanFactory = ContextLoader.getCurrentWebApplicationContext();
        ValueService valueService = beanFactory.getBean(ValueService.class);
        List<Value> list = valueService.getAll();
        sce.getServletContext().setAttribute("dicList",list);

        HashMap<String, List> map = new HashMap<>();
          for (Value v:list){
              String key = v.getTypeCode();
              List l= map.get(key);
              if (l==null){
                  l=new ArrayList();
                  map.put(key,l);
              }
              l.add(v);
          }
          System.out.println(map);
          sce.getServletContext().setAttribute("dicMap",map);
        System.out.println("数据字典缓存加载完毕!");

        //阶段和可能性的关系
        HashMap stage2poss = new HashMap();
        ResourceBundle resourceBundle = ResourceBundle.getBundle("stage2poss");
        Enumeration<String> keys = resourceBundle.getKeys();
        while (keys.hasMoreElements()){
            String key = keys.nextElement();
            String value = resourceBundle.getString(key);
            stage2poss.put(key,value);
        }

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(stage2poss);
        //sce.getServletContext().setAttribute("stage2Poss",stage2poss);
        sce.getServletContext().setAttribute("stage2PossObj",json);


       String stages = mapper.writeValueAsString(map.get("stage"));
        sce.getServletContext().setAttribute("stages",stages);

    }
}
