import com.bjpowernode.crm.mapper.ActivityMapper;
import com.bjpowernode.crm.mapper.DeptMapper;
import com.bjpowernode.crm.pojo.Activity;
import com.bjpowernode.crm.pojo.Dept;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration("classpath:applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)


public class Tester {
    @Autowired
    private DeptMapper deptMappermapper;
    @Autowired
    private ActivityMapper activityMapper;
    @Test
   public void test01(){
        for (int i=0; i<123; i++){
            Dept dept=new Dept();
            dept.setId(i+1+"");
            dept.setName("部门"+(1+i));
            dept.setNo("0000");
            deptMappermapper.save(dept);
        }
    }

    @Test
    public void test02(){
        for (int i=0; i<123; i++){ Activity activity = new Activity();
        activity.setName("包饺子"+i);
        activity.setOwner("广哥");
        activity.setStartDate("2021-3-12");
        activity.setEndDate("2021-12-12");
            activityMapper.save(activity);
        }
    }
}
