package org.seckill.dao.cache;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtobufIOUtil;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import org.seckill.entity.Seckill;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhj on 2017-7-7.
 */
public class RedisDao{
    private Logger log = LoggerFactory.getLogger(this.getClass());
    private final JedisPool jedisPool;
    private RuntimeSchema<Seckill> schema = RuntimeSchema.createFrom(Seckill.class);

    public RedisDao(String ip,int port) {
         jedisPool = new JedisPool(ip, port);
    }

    public Seckill getSeckill(long secillId){
        //redis 操作逻辑
        try {
            Jedis jedis = jedisPool.getResource();
            try {
                  String key = "seckill:"+secillId;
                //没有实现内部序列化
                //采用自定义方式序列化
                byte[] bytes = jedis.get(key.getBytes());
                if(null!=bytes){
                    Seckill seckill = schema.newMessage();
                    ProtobufIOUtil.mergeFrom(bytes,seckill,schema);
                    //被反序列化  压缩到原来大小的10分之1  压缩速度两个数量级，节省cpu
                    return seckill;
                }
            }finally {
                jedis.close();
            }
        } catch (Exception e) {
            log.error(e.getMessage(),e);
        }
        return null;
    }

    public String putSeckill(Seckill seckill){
        try {
            Jedis jedis = jedisPool.getResource();
            try {
                String key = "seckill:"+seckill.getSeckillId();
                byte[] bytes = ProtobufIOUtil.toByteArray(seckill, schema,
                        LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
                int timeout = 60*60;//一小时
                String result = jedis.setex(key.getBytes(), timeout, bytes);
                return result;
            }finally {
                jedis.close();
            }
        } catch (Exception e) {
            log.error(e.getMessage(),e);
        }
        return null;
    }


}
