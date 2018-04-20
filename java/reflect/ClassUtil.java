package com.franky.reflect;

import java.lang.*;
import java.lang.reflect.*;

public class ClassUtil {
	/**
	* 打印类的信息，包括类的成员函数、成员变量
	*/
	public void printClassMessage(Object obj){
		//首先获取类的类类型
		Class c = obj.getClass();
		//获取类的名称
		System.out.println("类的名称："+c.getName());

		/**
		* 获取方法
		*/
		Method[] ms = c.getMethods();
		for (Method m : ms) {
			Class returnType = m.getReturnType();
			System.out.print(returnType.getName()+" ");
			System.out.print(m.getName()+"(");
			Class[] paramTypes = m.getParameterTypes();
			for(Class clazz : paramTypes){
				System.out.print(clazz.getName()+", ");
			}
			System.out.println(")");
		}
		
		System.out.println("-----------");
		
		/**
		* 获取成员变量
		*/
//		Field[] fs = c.getFields();
		Field[] fs = c.getDeclaredFields();
		for(Field f : fs){
			Class fieldType = f.getType();
			System.out.println(fieldType.getName()+" "+f.getName());
		}

		System.out.println("===========");
		
		/**
		* 获取构造函数
		*/
		Constructor[] cs = c.getConstructors();
		for(Constructor cons : cs){
			System.out.print(cons.getName()+"(");
			Class[] paramTypes = cons.getParameterTypes();
			for(Class clazz : paramTypes){
				System.out.print(clazz.getName()+", ");
			}
			System.out.println(")");
		}
	}
	
	public static void main(String[] args){
		ClassUtil classUtil=new ClassUtil();
		classUtil.printClassMessage("hello");
		classUtil.printClassMessage(1);
	}
}