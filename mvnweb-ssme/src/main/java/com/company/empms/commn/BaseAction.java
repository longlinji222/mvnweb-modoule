package com.company.empms.commn;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;



public class BaseAction {

	public static final String LIST="list";
	public static final String TOLIST="tolist";
	public static final String ADD="add";
	public static final String EDIT="edit";
	public static final String JSON="json";
	public static final String LOGIN="login";
	public static final String TOLOGIN="tologin";
	public static final String REDIRECT_LIST="redirect_list";
	public static final String REDIRECT_TOEDIT="redirect_toedit";
	public static final String REDIRECT_TOADD="redirect_toAdd";
	
	
	// .....
	
	protected HttpServletRequest request;
	protected ServletContext application;
	
	public static final String MSG="msg";
	public static final String STATUS="status";
	protected Map<String, Object> data=new HashMap<String, Object>();
	
	
	public Map<String, Object> getData() {
		return data;
	}
	public void setData(Map<String, Object> data) {
		this.data = data;
	}
	
	public void setMsg(String msg) {
		request.getSession().setAttribute("MSG", msg);
	}
	
}
