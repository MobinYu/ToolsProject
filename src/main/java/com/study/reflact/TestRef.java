package com.study.reflact;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * 反射
 * https://www.cnblogs.com/rocomp/p/4781987.html
 * 
 * @author Administrator
 *
 */
public class TestRef {

	public static void main(String[] args) {
		
		threeWaysGetObject();
		
		basicOperate();
		
		fanxing();
		

	}
	
	/**
	 * 1、万事万物皆对象，（当然，基本数据类型，静态成员不是面向对象（属于类的）），所以我们创建的每一个类也都是对象，即类本身是java.lang.Class类的实例对象，
	 * 但是这些对象都不需要new出来，因为java.lang.Class类的构造方法是私有的
	 * 2、任何一个类都是Class类的实例对象，这个实例对象有三种表示方式
	 * 
	 * 运行时加载类是动态加载类 --> Class c =  Class.forName("类的全名")
	 * 
	 */
	private static void threeWaysGetObject(){
		//1
		Class c1 = User.class;
		
		//2
		Class c2 = new User().getClass();
		
		//3
		Class c3 = null;
		try {
			c3 = Class.forName("User");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		System.out.println("c1:" + c1);
	}
	
	/**
	 * 反射类基本操作
	 */
	private static void basicOperate(){
		Class c = User.class;
		//1、获取类名
		System.out.println("类名："+ c.getName());
		
		System.out.println("===========================");
		
		//2、获取方法名+方法参数类型
		Method[] methods1 = c.getMethods(); //获取所有的public的函数，包括父类继承而来的
		Method[] methods2 = c.getDeclaredMethods(); //获取自己声明的方法
		for (Method method : methods2) {
			Class[] paraTypes = method.getParameterTypes();
			
			List<String> params = new ArrayList<String>();
			for (Class pType : paraTypes) {
				params.add(pType.getName());
			}
			
			System.out.println("方法：" + method.getName() + "的参数：" + params.toString());
		}
		System.out.println("===========================");
		
		//3、获取成员变量+类型
		Field[] fields = c.getDeclaredFields();
		for (Field field : fields) {
			System.out.println("字段名称："+field.getName() + "\t字段类型："+ field.getType());
		}
		
		//4、获取构造函数+参数
		Constructor[] cs1 = c.getConstructors();
//		Constructor[] cs2 = c.getDeclaredConstructors();
		for (Constructor constructor : cs1) {
			
			Class[] paraTypes = constructor.getParameterTypes();
			List<String> params = new ArrayList<String>();
			for (Class pType : paraTypes) {
				params.add(pType.getName());
			}
			System.out.println("构造函数：" + constructor.getName() + "的参数：" + params.toString());
		}
		
	}
	
	/**
	 * 泛型应用
	 */
	private static void fanxing(){
		List list1 = new ArrayList();
		List<String> list2 = new ArrayList<String>();
		list2.add("haha");
		
		Class c1 = list1.getClass();
		Class c2 = list2.getClass();
		
		System.out.println(c1==c2);
		
		//因为反射的操作都是编译之后的操作，也就是运行时的操作，c1==c2返回true，说明编译之后集合的泛型是去泛型化的。
		//那么我们就可以理解为，Java集合中的泛型，是用于防止错误类型元素输入的，比如在list2中我们add一个int，add(10)就会编译报错，那么这个泛型就可以只在编译阶段有效，通过了编译阶段，泛型就不存在了。
		
//		list2.add(10); //编译报错
		try {
			Method m = c1.getMethod("add", Object.class);
			m.invoke(list2, 10); //通过反射，不会报错，并且成功添加
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println(list2);
		
		//成功添加后，list2中存储了多个不同类型的数据（String型，和int型），就不能用for-each来遍历了，会抛出类型转换错误异常ClassCastException
		for (String string : list2) {
			System.out.println(string);
		}
		
	}
}
