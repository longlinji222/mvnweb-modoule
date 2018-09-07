package com.company.mybatis.criteria;

import com.company.mybatis.commn.EasyCriteria;
import com.fasterxml.jackson.annotation.JsonFormat;

public class EmpCriteria extends EasyCriteria {
	private String empno;
	private String ename;
	private String job;
	private String deptno;
	private String lowSal;
	private String hiSal;
	private String startHiredate;
	private String endHiredate;

	public boolean isNotNullOrEmpty(String str) {
		return str != null && (!str.equals(""));
	}

	@Override
	public String getCondition() {
		values.clear(); //清除条件数据
		
 		StringBuilder sbBuilder = new StringBuilder();

		// where empno=7902 and ename like '%a%' and job='MANAGER'
		if (isNotNullOrEmpty(empno)) {
			sbBuilder.append(" and e.empno=#{ec.empno}");
		}
		if (isNotNullOrEmpty(ename)) {
			sbBuilder.append(" and e.ename like #{ec.ename}");
			ename="%" + ename + "%";
		}
		if (isNotNullOrEmpty(job)) {
			sbBuilder.append(" and e.job=#{ec.job}");
		}

		if (isNotNullOrEmpty(deptno)) {
			sbBuilder.append(" and e.deptno=#{ec.deptno}");
		}
											
		if (isNotNullOrEmpty(lowSal)) {
			sbBuilder.append(" and e.sal>=#{ec.lowSal}");
		}

		if (isNotNullOrEmpty(hiSal)) {
			sbBuilder.append(" and e.sal<=#{ec.hiSal}");
		}
		if (isNotNullOrEmpty(startHiredate)) {
			System.out.println(startHiredate);
			sbBuilder.append(" and e.hiredate>=to_date('"+startHiredate+"','YYYY-MM-DD')");
			
		}

		if (isNotNullOrEmpty(endHiredate)) {
			System.out.println(endHiredate);
			sbBuilder.append(" and e.hiredate<=to_date('"+endHiredate+"','YYYY-MM-DD')");
		}
 		
 		return sbBuilder.toString();
	}

	
	
	
	public String getEmpno() {
		return empno;
	}

	public void setEmpno(String empno) {
		this.empno = empno;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getDeptno() {
		return deptno;
	}

	public void setDeptno(String deptno) {
		this.deptno = deptno;
	}

	public String getLowSal() {
		return lowSal;
	}

	public void setLowSal(String lowSal) {
		this.lowSal = lowSal;
	}

	public String getHiSal() {
		return hiSal;
	}

	public void setHiSal(String hiSal) {
		this.hiSal = hiSal;
	}

	public String getStartHiredate() {
		return startHiredate;
	}

	public void setStartHiredate(String startHiredate) {
		this.startHiredate = startHiredate;
	}

	public String getEndHiredate() {
		return endHiredate;
	}

	public void setEndHiredate(String endHiredate) {
		this.endHiredate = endHiredate;
	}

	@Override
	public String toString() {
		return "EmpCriteria [empno=" + empno + ", ename=" + ename + ", job=" + job + ", deptno=" + deptno + ", lowSal="
				+ lowSal + ", hiSal=" + hiSal + ", startHiredate=" + startHiredate + ", endHiredate=" + endHiredate
				+ "]";
	}

	public EmpCriteria(String empno, String ename, String job, String deptno, String lowSal, String hiSal,
			String startHiredate, String endHiredate) {
		super();
		this.empno = empno;
		this.ename = ename;
		this.job = job;
		this.deptno = deptno;
		this.lowSal = lowSal;
		this.hiSal = hiSal;
		this.startHiredate = startHiredate;
		this.endHiredate = endHiredate;
	}

	public EmpCriteria() {
		super();
	}
}
