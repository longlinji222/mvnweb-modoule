package com.company.mybatis.factory;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringUtil {
	static ApplicationContext ac=new ClassPathXmlApplicationContext(
			"spring/ApplicationContext.xml");
	
	public static <T> T getBean(String id) {
		return (T) ac.getBean(id);
	}
}
