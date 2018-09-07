package com.company.mybatis.commn;

import org.apache.shiro.crypto.hash.Md5Hash;

public class MD5Util {

	
	// 彩虹表
	
	public static String encode(String raw, String salt) {
		return new Md5Hash(raw, salt).toHex();
	}
	
	
	public static void main(String[] args) {
		
		System.out.println(encode("123456", "admin"));
		System.out.println(encode("123456", "jay"));
		System.out.println(encode("123456", "test"));
	}
	
}
