package org.seckill.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.seckill.entity.Seckill;

public interface SeckillDao {
	
	/**
	 * 减库存
	 * @Description:
	 * @param
	 * @return: 
	 * @author zhj
	 * @date 2017年7月3日
	 */
	int reduceNumber(@Param("seckillId") long seckillId, @Param("killTime") Date killTime);
	
	
	/**
	 * 库存查询
	 * @Description:
	 * @param
	 * @return:
	 * @author zhj
	 * @date 2017年7月3日
	 */
	Seckill queryById(long seckillId);
	
	
	
	/**
	 * 根据偏移量查询秒杀商品列表
	 * @Description:
	 * @param
	 * @return:
	 * @author zhj
	 * @date 2017年7月3日
	 */
	List<Seckill> queryAll(@Param("offet") int offet, @Param("limit") int limit);


	void killByProcedure(Map<String,Object> paramMap);


}
