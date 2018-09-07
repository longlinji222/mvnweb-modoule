package com.company.empms.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.xml.crypto.Data;

import org.springframework.stereotype.Service;

import com.company.empms.service.EmpService;
import com.company.mybatis.commn.PageBean;
import com.company.mybatis.criteria.EmpCriteria;
import com.company.mybatis.dao.EmpDAO;
import com.company.mybatis.entity.Emp;

@Service("empService")
public class EmpServiceImpl implements EmpService {
	
	@Resource
	EmpDAO empDAO;

	@Override
	public boolean add(Emp emp) {
		emp.setHiredate(new Date());
		return empDAO.add(emp);
	}

	@Override
	public boolean update(Emp emp) {
		return empDAO.update(emp);
	}

	@Override
	public boolean delete(String empno) {
		return empDAO.delete(empno);
	}

	@Override
	public Emp get(int empno) {
		return empDAO.get(empno);
	}


	@Override
	public void listByPageBean(PageBean<Emp> pb ,EmpCriteria ec) {
		pb.setTableName(" emp e ");//设置表名
		pb.setCondition(ec.getCondition());//获取查询条件
		pb.setSort("deptno");//设置排序字段
		
		List<Emp> data = empDAO.listByPageBean(pb.getSql(), ec);//查询的结果
		BigDecimal bd=empDAO.count(pb.getSqlCount(), ec);
		pb.setData(data);;//将查询结果设置到pb
		pb.setMaxRow(bd.intValue());//设置总记录数
	}
	
	

}
