package com.java.base;

/**
 * 基本数据类型+包装类型 + 定义
 * 
 * 参考：https://www.cnblogs.com/oldthree3/p/9088029.html
 * @author Super Yu
 *
 */
public class DataTypeDefine {
	
	/**
	 * 基本类型基于数值，对象类型基于引用
	 * 基本类型变量只能存储数值，包装类型不仅可以存储数值，还提供各种各样对数值的操作方法
	 */
	
	/** 整型 start */
	byte b1 = 1;
	Byte b2 = 2;
	
	short s1 = 1;
	Short s2 = 2;
	
	int a = 1;
	Integer a2 = 2;
	
	long ln = 1;
//	Long ln2 = 2; //包装类型 必须加L
	/** 整型 end */
	
	/** 浮点型 start */
	float ft = 1;
//	Float ft2 = 2; //包装类型 必须加F/f
	
	double db = 1;
//	Double db2 = 2; //包装类型 必须加D/d
	/** 浮点型 end */
	
	/** char 数据类型可以储存任何字符（0~65535） */
	char c = 1;
	Character c2 = 2;
	
	/** 布尔类型 */
	boolean bl = false;
	Boolean bl2 = true;
	
	public void typeConvert(){
		/** 1、自动类型转换（byte → short(char) → int → long → float → double） */
		short sh = b1;
		
		/** 1、强制类型转换（double → float → long → int → short(char) → byte） */
		/** 会损失精度 */
		double de = 2.53;
		int i = (int) de;
	}
	
	public static void main(String[] args) {
		byte b1 = 127;
		byte b2 = (byte) (b1 + 1);
		
		System.out.println(b1+"--"+b2);
	}
	
	

}
