package com.yu.utils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

public class CopyPropertiesUtils {

	public static void copyPropertiesIgnoreNull(Object source, Object target) {
        BeanUtils.copyProperties(source, target, getNullPropertyNames(source));
    }
	
	public static String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<String>();
        for (java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null)
                emptyNames.add(pd.getName());
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }
	
	public static void copy(Object src, Object dest) {

        Map<String, Object> srcMap = new HashMap<String, Object>();
        Field[] srcFields = src.getClass().getDeclaredFields();
        for (Field fd : srcFields) {
            try {
                srcMap.put(fd.getName(), fd.get(src)); // 获取属性值
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        Field[] destFields = dest.getClass().getDeclaredFields();
        for (Field fd : destFields) {
            if (srcMap.get(fd.getName()) == null) {
                continue;
            }
            try {
                fd.set(dest, srcMap.get(fd.getName())); // 给属性赋值
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
