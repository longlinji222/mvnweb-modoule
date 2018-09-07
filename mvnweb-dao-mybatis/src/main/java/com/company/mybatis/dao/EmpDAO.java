package com.company.mybatis.dao;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.company.mybatis.criteria.EmpCriteria;
import com.company.mybatis.entity.Emp;


public interface EmpDAO {
	// CRUD
	public boolean add(Emp emp);
	
	public boolean update(Emp emp);
	
	//批量删除员工
	public boolean delete(@Param("empno") String empno);
	
	public Emp get(int empno);
	
	public BigDecimal count(@Param("sql")String sql ,@Param("ec") EmpCriteria ec);//查询总记录数
	
	public List<Emp> listByPageBean(@Param("sql")String sql ,@Param("ec") EmpCriteria ec);// 分页查询
}
