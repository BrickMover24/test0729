package com.bjpowernode.crm.web.controller;

import com.bjpowernode.crm.pojo.Activity;
import com.bjpowernode.crm.pojo.Page;
import com.bjpowernode.crm.pojo.User;
import com.bjpowernode.crm.service.ActivityService;
import com.bjpowernode.crm.utlis.DateTimeUtil;
import com.bjpowernode.crm.utlis.POJOUtils;
import com.bjpowernode.crm.web.Result;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("act")
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    @GetMapping("page.json")
    public Page page2(Page page) {
        System.out.println(page);
        activityService.getPage(page);
        return page;
    }

    @RequestMapping("save.do")
    public Map save(Activity activity,HttpSession session){

        User user = (User)session.getAttribute("loginUser");
        /*String createBy = user.getLoginAct() + "|" + user.getName();
        String createTime = DateTimeUtil.getSysTimeStr();
        activity.setCreateBy(createBy);
        activity.setCreateTime(createTime);*/

       // POJOUtils.initSave(activity,session);
        activityService.save(activity);
        return Result.SUCCESS;
    }

    @GetMapping("get.json")
    public Activity get(String id) {
        return activityService.get(id);
    }

    @PutMapping("update.do")
    public Map update(Activity activity, HttpSession session) {
        /*User user = (User) session.getAttribute("loginUser");
        String editBy = user.getLoginAct() + "|" + user.getName();
        String editTime = DateTimeUtil.getSysTimeStr();
        activity.setEditBy(editBy);
        activity.setEditTime(editTime);*/
        //POJOUtils.initEdit(activity,session);
        activityService.update(activity);
        return Result.SUCCESS;
    }

    @GetMapping("export.do")
    public void exportFile(HttpServletResponse response) throws IOException {
      /*  FileInputStream in = new FileInputStream("d:/exportfile");
        response.setHeader("Content-Disposition","attachment;filename=exportfile");
        ServletOutputStream out = response.getOutputStream();
            int len;
            byte[] bytes= new byte[1024];
            while ((len=in.read(bytes))!=-1){
                out.write(bytes,0,len);
            }
             out.close();
            in.close();*/

        //使用POI生成Excel文件
        //1.创建一个空的Excel文件
        HSSFWorkbook excel = new HSSFWorkbook();
        //2.创建页签sheet
        HSSFSheet sheet = excel.createSheet();
        //3.创建行
        int rowIndex = 0;
        HSSFRow row = sheet.createRow(rowIndex++);
        //4.创建列
        int cellIndex = 0;
        row.createCell(cellIndex).setCellValue("名称");
        row.createCell(cellIndex).setCellValue("所有者");
        row.createCell(cellIndex).setCellValue("开始日期");
        row.createCell(cellIndex).setCellValue("结束日期");

        //5.查询数据库
        List<Activity> list = activityService.getAll();

        for (Activity activity : list) {
            row = sheet.createRow(rowIndex++);
            cellIndex = 0;
            row.createCell(cellIndex++).setCellValue(activity.getName());
            row.createCell(cellIndex++).setCellValue(activity.getOwner());
            row.createCell(cellIndex++).setCellValue(activity.getStartDate());
            row.createCell(cellIndex++).setCellValue(activity.getEndDate());
        }
        response.setHeader("Content-Disposition", "attachment;filename=activity.xls");
        excel.write(response.getOutputStream());
        excel.close();
    }

    @PostMapping("import.do")
    public Map importFile(MultipartFile upFile, HttpSession session) throws IOException {

        HSSFWorkbook excel = new HSSFWorkbook(upFile.getInputStream());
        HSSFSheet sheet = excel.getSheetAt(0);
        Row row;
        int rowIndex = 1;

        User user = (User) session.getAttribute("loginUser");
        //String createBy = user.getLoginAct() + "|" + user.getName();
        String createTime = DateTimeUtil.getSysTimeStr();

        List data = new ArrayList();

        while ((row = sheet.getRow(rowIndex++)) != null) {
            int cellIndex = 0;
            String name = row.getCell(cellIndex++).getStringCellValue();
            String owner = row.getCell(cellIndex++).getStringCellValue();
            String startDate = row.getCell(cellIndex++).getStringCellValue();
            String endDate = row.getCell(cellIndex++).getStringCellValue();
            Activity activity = new Activity();
            activity.setName(name);
            activity.setOwner(owner);
            activity.setStartDate(startDate);
            activity.setEndDate(endDate);
            //activity.setCreateBy(createBy);
            activity.setCreateTime(createTime);

            data.add(activity);

        }
        activityService.saveList(data);
         return Result.SUCCESS;
    }

    //查询某个线索关联的市场活动
    @GetMapping("getByClueId.json")
    public List getByClueId(String clueId){
     return   activityService.getByClueId(clueId);
    }

    //所有的市场活动
    @GetMapping("activities.json")
    public List getAll(){
       return  activityService.getAll();
    }
}
