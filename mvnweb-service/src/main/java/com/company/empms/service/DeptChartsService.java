package com.company.empms.service;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface DeptChartsService {
	//查询部门人数制作图表
	public List<Map> getData();
}
