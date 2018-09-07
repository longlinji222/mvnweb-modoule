package com.company.mybatis.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Logtest {
	static Logger logger=LoggerFactory.getLogger(Logtest.class);
	
	public int divids(int num1,int num2) {
		int num3=0;
		logger.debug("进入divids方法！");
		
		logger.info("参数："+num1+","+num2);
		
		if(num1==0) {
			logger.warn("0除以任何数为0");
		}
		
		try {
			num3=num1/num2;
		} catch (Exception e) {
			logger.info("divids 发生异常!",e);
		}
		
		logger.info("执行结果:"+num3);
		return num3;
	}
	
	public static void main(String[] args) {
		new Logtest().divids(1, 1);
	}
}
