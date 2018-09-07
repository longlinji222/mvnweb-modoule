package com.company.empms.service;

public class EnumTest {
	public static void main(String[] args) {
		SumtypeEnum sum=SumtypeEnum.getsumtypeEnum("sum");
		System.out.println(sum.mDesc);
		//输出 "求和"
	}
}
