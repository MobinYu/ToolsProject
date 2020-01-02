package com.study.annotation.self;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;


public class TagSupport<E> {
	
	public TagSupport(){
		// 获得EntityDaoSupport包含泛型实参的类型
		ParameterizedType parameterizedType = (ParameterizedType) getClass().getGenericSuperclass();
		Type[] params = parameterizedType.getActualTypeArguments();
		Class<E> entityClass = (Class<E>) params[0];
		
		init(entityClass);
	}

	private void init(Class<E> entityClass) {
		TagTable annotation = entityClass.getAnnotation(TagTable.class);
		
	}

}
