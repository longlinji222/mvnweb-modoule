package com.company.empms.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import com.company.mybatis.criteria.DeptCriteria;
import com.company.mybatis.entity.Dept;
import com.company.mybatis.commn.PageBean;

@Transactional
public interface DeptService  {
	
		public boolean add(Dept dept); // 新增
		
		public boolean update(Dept dept); // 更新
		
		public boolean delete( String deptno); // 删除
		
		public Dept get(String deptno); //根据id查询
		
		public List<Dept> list(); // 查询所有
		
		public void listByPageBean(PageBean<Dept> pb ,DeptCriteria dc);// 分页查询
		
		//导出
		public void export() throws IOException;
		

		
}