package com.yu.toolMethod;

import com.yu.model.User;
import com.yu.model.UserQr;
import com.yu.utils.CopyPropertiesUtils;

public class CopyProperties {

	/**
	 * 对象属性值copy
	 * 
	 */
	public static void copyPropertiesIgnoreNull(){
		UserQr userQr = new UserQr("d:", "12345", "ceshi", 20);
		
		User user = new User();
		
		CopyPropertiesUtils.copyPropertiesIgnoreNull(userQr, user);
		
		System.out.println(user.toString());
	}
	
	
	public static void main(String[] args) {
		copyPropertiesIgnoreNull();
	}
}
