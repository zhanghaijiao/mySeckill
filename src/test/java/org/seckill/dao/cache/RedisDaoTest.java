package org.seckill.dao.cache;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.dao.SeckillDao;
import org.seckill.entity.Seckill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by zhj on 2017-7-7.
 */
@RunWith(SpringJUnit4ClassRunner.class)
//告诉junit spring配置文件
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class RedisDaoTest {

    private long id = 1000;
    @Autowired
    private  RedisDao redisDao;
    @Autowired
    private SeckillDao seckillDao;

    @Test
    public void gSeckill() throws Exception {
        Seckill seckill = redisDao.getSeckill(id);
        
        if(seckill == null){
             seckill = seckillDao.queryById(id);
            if(seckill != null){
                String s = redisDao.putSeckill(seckill);
                System.out.println("s:"+s);
                Seckill seckill2 = redisDao.getSeckill(id);
                System.out.println("seckill2:"+seckill2);
            }
        }
        
    }


}