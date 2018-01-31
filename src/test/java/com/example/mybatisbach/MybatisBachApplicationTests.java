package com.example.mybatisbach;

import com.example.mybatisbach.dao.UserMapper;
import com.example.mybatisbach.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisBachApplicationTests {

    @Autowired
    UserMapper userMapper;

    @Autowired
    @Qualifier("mybatisMasterTransactionManager")
    DataSourceTransactionManager dataSourceTransactionManager;


    @Test
    public void noTransaction() {

        long start = System.currentTimeMillis();
        for(int i=0;i<1000;i++){
            User user = new User();
            user.setUserName("aaa");
            user.setPassword("asdf");
            user.setPhone("123");
            userMapper.insert(user);
        }
        long end = System.currentTimeMillis();

        System.out.println("noTransaction用时：" + (end -start) + "ms");

    }

    @Test
    public void useTransaction() {

        //2.获取事务定义
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        //3.设置事务隔离级别，开启新事务
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        //4.获得事务状态
        TransactionStatus status = dataSourceTransactionManager.getTransaction(def);

        long start = System.currentTimeMillis();
        for(int i=0;i<1000;i++){
            User user = new User();
            user.setUserName("aa");
            user.setPassword("asdf");
            user.setPhone("1231");
            userMapper.insert(user);
        }
        long end = System.currentTimeMillis();

        dataSourceTransactionManager.commit(status);

        System.out.println("useTransaction用时：" + (end -start) + "ms");

    }

	@Test
	public void contextLoads() {

        //2.获取事务定义
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        //3.设置事务隔离级别，开启新事务
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        //4.获得事务状态
        TransactionStatus status = dataSourceTransactionManager.getTransaction(def);
        User user = new User();
        user.setUserId(2);
        user.setUserName("aa");
        user.setPassword("asdf");
        user.setPhone("123");

        try{
            userMapper.insert(user);
            dataSourceTransactionManager.commit(status);
            System.out.println("success.....");
        }catch (Exception e){
            System.out.println("fail.....");
            dataSourceTransactionManager.rollback(status);
        }

	}

}
