package com.company.mybatis.entity;

import java.io.Serializable;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

import com.company.mybatis.status.Monitor;


/**
 * The persistent class for the MYUSER database table.
 * 
 */
public class Myuser implements Serializable, HttpSessionBindingListener{
	private static final long serialVersionUID = 1L;

	/**
	 * 用户正常状态
	 */
	public static final int STATUS_NORMAL=0;
	/**
	 * 用户锁定状态
	 */
	public static final int STATUS_LOCK=1;
	
	
	
	
	private long id;

	private Long status;

	private String userinfo;

	private String username;

	private String userpwd;

	public Myuser() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Long getStatus() {
		return this.status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public String getUserinfo() {
		return this.userinfo;
	}

	public void setUserinfo(String userinfo) {
		this.userinfo = userinfo;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserpwd() {
		return this.userpwd;
	}

	public void setUserpwd(String userpwd) {
		this.userpwd = userpwd;
	}

	public Myuser(long id, Long status, String userinfo, String username, String userpwd) {
		super();
		this.id = id;
		this.status = status;
		this.userinfo = userinfo;
		this.username = username;
		this.userpwd = userpwd;
	}

	public Myuser(Long status, String username, String userpwd) {
		super();
		this.status = status;
		this.username = username;
		this.userpwd = userpwd;
	}
	


	@Override
	public String toString() {
		return "Myuser [id=" + id + ", status=" + status + ", userinfo=" + userinfo + ", username=" + username
				+ ", userpwd=" + userpwd + "]";
	}

	@Override
	public void valueBound(HttpSessionBindingEvent event) {
		Monitor.count.incrementAndGet();
	}

	@Override
	public void valueUnbound(HttpSessionBindingEvent event) {
		Monitor.count.getAndDecrement() ;
	}

	
}