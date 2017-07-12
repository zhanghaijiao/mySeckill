package org.seckill.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.entity.SuccessKilled;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * Created by zhj on 2017-7-4.
 */
@RunWith(SpringJUnit4ClassRunner.class)
//告诉junit spring配置文件
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SuccessKilledDaoTest {

    //注入dao类实现依赖
    @Resource
    private SuccessKilledDao dao;


    @Test
    public void insertSuccessKilled() throws Exception {
        int i = dao.insertSuccessKilled(1000l, 18801147383l);
        System.out.println("执行结果："+i);
    }

    @Test
    public void queryByIdWithSeckill() throws Exception {
        SuccessKilled successKilled = dao.queryByIdWithSeckill(1000l, 18801147383l);
        System.out.println(successKilled.getSeckill().getName());
    }

}