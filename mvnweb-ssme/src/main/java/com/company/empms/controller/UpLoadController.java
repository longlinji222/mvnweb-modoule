package com.company.empms.controller;



import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.company.empms.commn.BaseAction;

@RestController
@RequestMapping("/upLoad")
public class UpLoadController extends BaseAction{
	
	//跳转到list页面
	@RequestMapping("/upFile/page")
	public ModelAndView list(ModelAndView mv) {
		mv.setViewName("admin/upload/list");
		return mv;
	}
}
