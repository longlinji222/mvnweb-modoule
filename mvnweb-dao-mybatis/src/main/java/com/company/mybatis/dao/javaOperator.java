package com.company.mybatis.dao;

public class javaOperator {
	public static void main(String[] args) {
		//位运算的使用场景
		//============判断int型变量a是奇数还是偶数 ================
		/*
		a&1 = 0 偶数 
		a&1 = 1 奇数*/
		
		int number=21;
		int a=number&1;
		System.out.println(a);
		//============判断int型变量a是奇数还是偶数 ================
		int b=5%3;
		System.out.println(b);
	}
}
