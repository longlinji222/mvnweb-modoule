package com.company.mybatis.dao;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.company.mybatis.criteria.DeptCriteria;
import com.company.mybatis.entity.Dept;


public interface DeptDAO  {

	
		public boolean add(Dept dept); // 新增
		
		public boolean update(Dept dept); // 更新
		
		public boolean delete(@Param("deptno") String deptno); // 删除
		
		public Dept get(String deptno); //根据id查询
		
		public List<Dept> list(); // 查询所有
		
		public BigDecimal count(@Param("sql")String sql ,@Param("dc") DeptCriteria dc);//查询总记录数
		
		public List<Dept> listByPageBean(@Param("sql")String sql ,@Param("dc") DeptCriteria dc);// 分页查询
		
}