package com.company.mybatis.entity;

import java.io.Serializable;


import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;


/**
 * The persistent class for the EMP database table.
 * 
 */
public class Emp implements Serializable {
	private static final Long serialVersionUID = 1L;

	private Long empno;

	private Double comm;

	private String ename;
	
	
	private Date hiredate;

	private String job;

	private int mgr;

	private Double sal;
	
	private Long deptno;
	//bi-directional many-to-one association to Dept
	private Dept dept;

	public Emp() {
	}

	public Long getEmpno() {
		return this.empno;
	}

	public void setEmpno(Long empno) {
		this.empno = empno;
	}

	public Double getComm() {
		return this.comm;
	}

	public void setComm(Double comm) {
		this.comm = comm;
	}

	public String getEname() {
		return this.ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT+8")
	public Date getHiredate() {
		return this.hiredate;
	}

	public void setHiredate(Date hiredate) {
		this.hiredate = hiredate;
	}

	public String getJob() {
		return this.job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public int getMgr() {
		return this.mgr;
	}

	public void setMgr(int mgr) {
		this.mgr = mgr;
	}

	public Double getSal() {
		return this.sal;
	}

	public void setSal(Double sal) {
		this.sal = sal;
	}

	public Dept getDept() {
		return this.dept;
	}

	public void setDept(Dept dept) {
		this.dept = dept;
	}

	public Long getDeptno() {
		return deptno;
	}

	public void setDeptno(Long deptno) {
		this.deptno = deptno;
	}

	@Override
	public String toString() {
		return "Emp [empno=" + empno + ", comm=" + comm + ", ename=" + ename + ", hiredate=" + hiredate + ", job=" + job
				+ ", mgr=" + mgr + ", sal=" + sal + ", deptno=" + deptno + ", dept=" + dept + "]";
	}

	public Emp(Long empno, Double comm, String ename, Date hiredate, String job, int mgr, Double sal, Long deptno,
			Dept dept) {
		super();
		this.empno = empno;
		this.comm = comm;
		this.ename = ename;
		this.hiredate = hiredate;
		this.job = job;
		this.mgr = mgr;
		this.sal = sal;
		this.deptno = deptno;
		this.dept = dept;
	}

	public Emp(Double comm, String ename, Date hiredate, String job, int mgr, Double sal, Long deptno, Dept dept) {
		super();
		this.comm = comm;
		this.ename = ename;
		this.hiredate = hiredate;
		this.job = job;
		this.mgr = mgr;
		this.sal = sal;
		this.deptno = deptno;
		this.dept = dept;
	}

	
}