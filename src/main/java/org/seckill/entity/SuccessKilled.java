package org.seckill.entity;

import java.util.Date;

public class SuccessKilled {
	
	private long seckillId;
	private long unserPhone;
	private short statr;
	private Date createTime;
	
	//变通
	//多对一，
	private Seckill seckill;
	
	public long getSeckillId() {
		return seckillId;
	}
	public void setSeckillId(long seckillId) {
		this.seckillId = seckillId;
	}
	public long getUnserPhone() {
		return unserPhone;
	}
	public void setUnserPhone(long unserPhone) {
		this.unserPhone = unserPhone;
	}
	public short getStatr() {
		return statr;
	}
	public void setStatr(short statr) {
		this.statr = statr;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	
	public Seckill getSeckill() {
		return seckill;
	}
	public void setSeckill(Seckill seckill) {
		this.seckill = seckill;
	}
	@Override
	public String toString() {
		return "SuccessKilled [seckillId=" + seckillId + ", unserPhone="
				+ unserPhone + ", statr=" + statr + ", createTime="
				+ createTime + "]";
	}
	
	

}
