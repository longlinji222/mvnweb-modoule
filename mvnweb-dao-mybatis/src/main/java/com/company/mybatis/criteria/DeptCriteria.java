package com.company.mybatis.criteria;

import com.company.mybatis.commn.EasyCriteria;

public class DeptCriteria extends EasyCriteria {
	private String deptno;
	private String dname;

	public boolean isNotNullOrEmpty(String str) {
		return str != null && (!str.equals(""));
	}
	@Override
	public String getCondition() {
		values.clear(); //清除条件数据
		
 		StringBuilder sbBuilder = new StringBuilder();
 		if (isNotNullOrEmpty(deptno)) {
			sbBuilder.append(" and d.deptno=#{dc.deptno}");
		}
		if (isNotNullOrEmpty(dname)) {
			sbBuilder.append(" and d.dname like #{dc.dname}");
			dname="%" + dname + "%";
		}
		
		return sbBuilder.toString();
	}
	public String getDeptno() {
		return deptno;
	}
	public void setDeptno(String deptno) {
		this.deptno = deptno;
	}
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	public DeptCriteria(String deptno, String dname) {
		super();
		this.deptno = deptno;
		this.dname = dname;
	}
	public DeptCriteria() {
		super();
	}
	@Override
	public String toString() {
		return "DeptCriteria [deptno=" + deptno + ", dname=" + dname + "]";
	}
	
}
