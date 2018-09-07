package com.company.empms.service;

public enum SumtypeEnum {
	DEFALUT("",""),
	SUM("sum","求和"),
	MAX("max","最大值"),
	MIN("min","最小值");
	String mStatus;
	String mDesc;
	SumtypeEnum(String status,String desc){
		mStatus=status;
		mDesc=desc;
	}
	
	public static SumtypeEnum getsumtypeEnum(String status) {
		for(SumtypeEnum sum:values()) {
			if(status==sum.mStatus) {
				return sum;
			}
		}
		return DEFALUT;
	}
}
