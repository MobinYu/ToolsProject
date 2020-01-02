package com.study.annotation.springbean.service;

import com.study.annotation.springbean.MyAnnotation;
import com.study.annotation.springbean.dao.MyDao;
import com.study.annotation.springbean.dao.MyDao1;
import com.study.annotation.springbean.dao.MyDao2;

/**
 * @Description: 带有注解的服务
 * @author Administrator
 */
public class MyAnnServiceImpl {

	private MyDao myDao; //使用set方法注入（按名称）
	
	private MyDao1 myDao1; //使用set方法注入（按类型）
	
	@MyAnnotation
	private MyDao2 myDao2; //使用注解注入
	

	// set方法上的注解，带有name属性
	@MyAnnotation(name="myDao")
	public void setMyDao(MyDao myDao) {
		this.myDao = myDao;
	}

	// set方法上的注解，没有配置name属性
	@MyAnnotation
	public void setMyDao1(MyDao1 myDao1) {
		this.myDao1 = myDao1;
	}


	public void show() {
		if (myDao != null) {
			myDao.show();
		}
		
		if (myDao1 != null) {
			myDao1.show();
		}
		
		if (myDao2 != null) {
			myDao2.show();
		}
		
		System.out.println("这里是Service方法........");
	}
}

