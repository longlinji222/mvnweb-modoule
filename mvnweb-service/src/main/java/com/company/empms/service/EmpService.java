package com.company.empms.service;

import java.math.BigDecimal;

import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import com.company.mybatis.commn.PageBean;
import com.company.mybatis.criteria.EmpCriteria;
import com.company.mybatis.entity.Emp;

@Transactional
public interface EmpService {
	// CRUD
	public boolean add(Emp emp);
	
	public boolean update(Emp emp);
	
	//批量删除员工
	public boolean delete( String empno);
	
	public Emp get(int empno);
	
	public void listByPageBean(PageBean<Emp> pb ,EmpCriteria ec);// 分页查询
}
