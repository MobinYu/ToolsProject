package com.yu.toolMethod;

import org.apache.commons.lang3.StringUtils;

/**
 * 字符串拼接指定字符、指定长度
 * 
 * @author Administrator
 *
 */
public class StringPad {
	
	public static void main(String[] args) {
		String str = "C3205";
		
		//1、得到8位的字符串，不够右边补"0"
		String strRight = StringUtils.rightPad(str, 8, "0");
		System.out.println(strRight);
		
		//1、得到8位的字符串，不够左边边补"0"
		String strLeft = StringUtils.leftPad(str, 8, "0");
		System.out.println(strLeft);
	}

}
