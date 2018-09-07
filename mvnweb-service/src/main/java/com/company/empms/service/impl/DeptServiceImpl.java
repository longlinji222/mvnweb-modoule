package com.company.empms.service.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.company.empms.service.DeptService;
import com.company.mybatis.commn.PageBean;
import com.company.mybatis.criteria.DeptCriteria;
import com.company.mybatis.dao.DeptDAO;
import com.company.mybatis.entity.Dept;

@Service("deptService")
public class DeptServiceImpl implements DeptService {
	
	@Resource
	DeptDAO deptDAO;

	@Override
	public boolean add(Dept dept) {
		return deptDAO.add(dept);
	}

	@Override
	public boolean update(Dept dept) {
		return deptDAO.update(dept);
	}

	@Override
	public boolean delete(String deptno) {
		
		return deptDAO.delete(deptno);
	}

	@Override
	public Dept get(String deptno) {
		return deptDAO.get(deptno);
	}

	@Override
	public List<Dept> list() {
		return deptDAO.list();
	}

	@Override
	public void listByPageBean(PageBean<Dept> pb ,DeptCriteria dc) {
		pb.setTableName(" dept d");//设置表名
		pb.setCondition(dc.getCondition());//获取查询条件
		pb.setSort("deptno");//设置排序字段
		System.out.println(pb.getSql());
		List<Dept> data = deptDAO.listByPageBean(pb.getSql(), dc);//查询的结果
		BigDecimal bd=deptDAO.count(pb.getSqlCount(), dc);
		pb.setData(data);//将查询结果设置到pb
		pb.setMaxRow(bd.intValue());//设置总记录数
		
	}

	@Override
	public void export() throws IOException {
		
	}
}
