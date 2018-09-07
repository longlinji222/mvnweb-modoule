package com.company.empms.controller;


import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.company.empms.service.MyUsersService;
import com.company.mybatis.commn.MD5Util;
import com.company.mybatis.entity.Myuser;

@RestController
public class LoginController {
	@Resource
	HttpServletRequest request;
	@Resource
	HttpSession session;
	@Resource
	HttpServletResponse response;
	@Resource
	ServletContext application;
	@Resource
	MyUsersService myUsersService;
	
	
	@RequestMapping("/toLogin")
	public ModelAndView toLogin(ModelAndView mav) {
		mav.setViewName("login");
		return mav;
	}
	
	@RequestMapping("/login_checked")
	public String login_checked(String captcha) {
		//boolean flag=false;
		boolean flag=true;
		if(session.getAttribute("rand").equals(captcha)) {
			flag=true;
		}
		return flag+"";
	}
	
	@RequestMapping("/login") // login.do
	public ModelAndView login(String username, String userpwd, ModelAndView mav) {
		Myuser users= myUsersService.login(username, MD5Util.encode(userpwd, username.toLowerCase()));
		mav.setViewName("login");
		
		if(users!=null) {
			session.setAttribute("USER", users);
			//mav.setViewName("redirect:users/admin");
			mav.setViewName("admin/home");
		}
		return mav;
	}
	
	@RequestMapping("/outLogin")
	public ModelAndView outLogin(ModelAndView mav) {
		request.getSession().invalidate();
		mav.setViewName("login");
		return mav;
	}
	
}
