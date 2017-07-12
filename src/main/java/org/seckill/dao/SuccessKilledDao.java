package org.seckill.dao;

import org.apache.ibatis.annotations.Param;
import org.seckill.entity.SuccessKilled;

public interface SuccessKilledDao {
	
	/**
	 * 插入购买明细,可过滤重复  联合主键
	 * @Description:
	 * @param
	 * @return:
	 * @author zhj
	 * @date 2017年7月3日
	 */
	int insertSuccessKilled(@Param("seckillId") long seckillId, @Param("userPhone") long userPhone);
	
	
	/**
	 * 查询成功记录并携带 产品对象
	 * @Description:
	 * @param
	 * @return:
	 * @author zhj
	 * @date 2017年7月3日
	 */
	SuccessKilled queryByIdWithSeckill(@Param("seckillId") long seckillId,@Param("userPhone") long userPhone);

}
