package com.company.empms.service.impl;

import javax.annotation.Resource;


import org.springframework.stereotype.Service;

import com.company.empms.service.MyUsersService;
import com.company.mybatis.dao.MyUsersDAO;
import com.company.mybatis.entity.Myuser;
	
@Service("myUsersService")
public class MyUsersServiceImpl implements MyUsersService {
	
	@Resource
	MyUsersDAO myUsersDAO;

	@Override
	public Myuser login(String username, String userpwd) {
		return myUsersDAO.login(username, userpwd);
	}

	

}
