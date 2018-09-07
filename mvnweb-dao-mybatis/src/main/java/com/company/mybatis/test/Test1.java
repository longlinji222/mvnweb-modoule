package com.company.mybatis.test;

public class Test1 {
	public static void main(String[] args) {
		int x = 10;
		int y = 10;
		String str1 = new String("abc");
		String str2 = new String("abc");
		System.out.println(x == y); // 输出true
		System.out.println(str2.equals(str1)); // 输出false
	}
}
