package com.company.empms.service;

import org.springframework.transaction.annotation.Transactional;

import com.company.mybatis.entity.Myuser;

@Transactional
public interface MyUsersService {
	@Transactional(readOnly=true)
	public Myuser login(String username,String userpwd);
}
