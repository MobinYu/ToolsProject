package com.study.annotation.springbean;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.METHOD, ElementType.FIELD}) // 注解适用地方(类接口、字段和方法) 
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotation {

	//注解的name属性  
    public String name() default ""; 
}
