package org.seckill.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.entity.Seckill;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillCloseException;
import org.seckill.exception.SeckillException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by zhj on 2017-7-5.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-service.xml",
                        "classpath:spring/spring-dao.xml"})
public class SeckillServiceTest {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private  SeckillService seckillService;
    @Test
    public void getSeckillList() throws Exception {
        List<Seckill> list = seckillService.getSeckillList();
        logger.info("list={}",list);

    }

    @Test
    public void getById() throws Exception {
        Seckill s = seckillService.getById(1000l);
        logger.info("seckill={}",s);
    }

    @Test
    public void exportSeckillUrl() throws Exception {
        Exposer exposer = seckillService.exportSeckillUrl(1000l);
        logger.info("exposer={}",exposer);

    }

    @Test
    public void executeSeckill() throws Exception {
        SeckillExecution seckillExecution = null;
        try {
            seckillExecution = seckillService.executeSeckill(1000l, 18801147384l, "487fe9f7e6480106e5044d81ab68c45c");
        }catch (RepeatKillException e){
            logger.error(e.getMessage());
        }catch (SeckillCloseException e) {
            logger.error(e.getMessage());
        }catch (SeckillException e) {
            e.printStackTrace();
        }
        logger.info("result={}",seckillExecution);
    }

}