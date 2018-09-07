package com.company.empms.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.company.empms.service.DeptChartsService;


@RestController
@RequestMapping("/users")
public class AdminController {
	@Resource
	DeptChartsService deptChartsService;
	
	@RequestMapping("/admin") // users/admin.do
	public ModelAndView Runadmin(ModelAndView mv) {
		
		System.out.println("bbbbbbbbbb");
		List<Map> d=deptChartsService.getData();
				
		// ["衬衫","羊毛衫","雪纺衫","裤子","高跟鞋","袜子"]
		String deptArrays="";
		//  [5, 20, 36, 10, 10, 20]
		String empCountArrays="";
		for (Map deptCharts : d) {
			deptArrays += "'"+deptCharts.get("DNAME")+"',";
			empCountArrays += deptCharts.get("EMPCOUNT") +",";
		}
		if(deptArrays.length()>0 ||empCountArrays.length()>0) {
			deptArrays=deptArrays.substring(0, deptArrays.length()-1);
			empCountArrays=empCountArrays.substring(0, empCountArrays.length()-1);
		}else {
			deptArrays="";
			empCountArrays="";
		}
			mv.addObject("xAxisData", deptArrays);
			mv.addObject("seriesData", empCountArrays);
			mv.setViewName("admin/admin");//转发到admin.jsp
		return mv;
	}
}