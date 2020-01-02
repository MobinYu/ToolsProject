package com.java.base;

/**
 * 数据类型
 * 
 * @author Administrator
 *
 */
public class DataType {
	public static void main(String[] args) {
		
		testString();
		
		testDataType();
		
		testChange();
	}

	private static void testString() {
		String s1 = "abc";
		String s2 = "abc";
		
		System.out.println(s1 == s2);
		
		String str1 = new String("abc");
		String str2 = new String("abc");
		
		System.out.println(str1 == str2);
	}
	
	private static void testDataType(){
		//整型
		byte b = 127; //8位，【-128（-2^7）】~【-127（-2^7-1）】
		short s = 3566; //16位，【-32768（-2^15）】~【32767（-2^15-1）】
		int a = 0; //32位，【-2^32~2^32-1（大约20多亿）】
		long l = 3; //64位， 【-2^63~2^63-1（大约20多亿）】
		Long lo = 5L;
		
		//浮点型 （浮点数的默认类型为double类型）
		float f = 1.2f; //单精度、32位
		double d = 2.5; //双精度、64 位
		
		//字符型
		char c = 1; //单一的 16 位 Unicode 字符【\u0000（即为0） ~ \uffff（即为65,535）】
		
		//布尔型
		boolean flag = true;
		
		String str;
		
		//=======================================================================//
		//低转高会损失精度
		
		/*
		 * 低  ----------------------------------------------->  高
		 *   byte,short,char—> int —> long—> float —> double
		*/
		
		int i = 05;
		char cc = 'Z';
		
		double d1 = 6.6666f;
		
		float f1 = (float) d1;
		
		System.out.println((int)cc); //得到字符的char值
		System.out.println(d1);
		System.out.println(f1);
	}
	
	//char+字符+string转换
	private static void testChange(){
		String str = "abc321好";
		char[] c = str.toCharArray();
		
		String ss = String.valueOf(c);
		
		
		//数字（ASCII码） <--> 字符
		int A = 65;
		char c1 = (char)A;
		
		char c2 = 'A';
		int A2 = (int)'A';
		
		
	}

}
