package com.study.reflact;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.apache.commons.lang3.StringUtils;

public class TestRef2 {
	
	private static <T> void dictionaryConversion(T entity){
		Class<?> c = entity.getClass();
		Field[] fields = c.getDeclaredFields();
		for (Field field : fields) {
			boolean isAnn = field.isAnnotationPresent(CodeToName.class);
			if (isAnn){
				//获取注解字段的注解信息
				CodeToName codeToName = field.getAnnotation(CodeToName.class);
				String cType = codeToName.type();
				String cField = codeToName.field(); //注解中指定被填充的字段名称
				
				try {
					//获取被注解字段的值
					String fieldName = field.getName();
					PropertyDescriptor pd = new PropertyDescriptor(fieldName, c);
					Method readMethod = pd.getReadMethod() ;
					String cantonValue = (String) readMethod.invoke(entity) ;
					
					if (StringUtils.isNotBlank(cantonValue)){
						String cantonName = "无";
						if (cantonValue.equals("320505")){
							cantonName = "苏州市工业园区";
						}
						
						//向目标字段赋值
						pd = new PropertyDescriptor(cField, c);
						Method writeMethod = pd.getWriteMethod();
						
						writeMethod.invoke(entity, cantonName);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		}
	}
	
	public static void main(String[] args) {
		City city = new City();
		city.setCityName("苏州");
		city.setCantonCode("320505");
		
		dictionaryConversion(city);
		
		System.out.println(city.getCantonName());
	}
	

}
