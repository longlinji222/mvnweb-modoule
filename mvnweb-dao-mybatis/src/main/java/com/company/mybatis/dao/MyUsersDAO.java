package com.company.mybatis.dao;

import org.apache.ibatis.annotations.Param;

import com.company.mybatis.entity.Myuser;

public interface MyUsersDAO {

	public Myuser login(@Param("username") String username,@Param("userpwd") String userpwd);
	
}
