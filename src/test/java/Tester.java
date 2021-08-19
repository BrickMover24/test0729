/*
import com.bjpowernode.crm.mapper.ActivityMapper;
import com.bjpowernode.crm.mapper.DeptMapper;
import com.bjpowernode.crm.mapper.TransactionMapper;
import com.bjpowernode.crm.pojo.Activity;
import com.bjpowernode.crm.pojo.Dept;
import com.bjpowernode.crm.pojo.Transaction;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Random;

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


    @Autowired
    private TransactionMapper mapper;

    @Test
    public void test04(){

        String[] stages = {
                "07成交",
                "01资质审查",
                "08丢失的线索",
                "09因竞争丢失关闭",
                "02需求分析",
                "05提案/报价",
                "03价值建议",
                "04确定决策者",
                "06谈判/复审"
        };
        Random random = new Random();

        for (int i = 0; i < 88; i++) {
            int index = random.nextInt(stages.length);
            Transaction transaction = new Transaction();
            transaction.setStage( stages[index] );
            mapper.save(transaction);
        }
    }
}
*/
