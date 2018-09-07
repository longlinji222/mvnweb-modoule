package com.company.empms.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.company.empms.service.DeptChartsService;
import com.company.mybatis.dao.DeptChartsDAO;

@Service("deptChartsService")
public class DeptChartsServiceImpl implements DeptChartsService {
	
	@Resource
	DeptChartsDAO deptChartsDAO;
	
	@Override
	public List<Map> getData() {
		return deptChartsDAO.getData();
	}

}
