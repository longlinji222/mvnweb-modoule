package com.company.empms.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.company.empms.commn.BaseAction;
import com.company.empms.service.DeptService;
import com.company.empms.service.EmpService;
import com.company.mybatis.commn.PageBean;
import com.company.mybatis.criteria.EmpCriteria;
import com.company.mybatis.entity.Emp;

@RestController
@RequestMapping("/emp")
public class EmpController extends BaseAction{
	
	@Resource//注入empService
	EmpService es;
	
	@Resource//注入DeptService
	DeptService ds;
	
	//跳转到list页面
	@RequestMapping("/emps/page")
	public ModelAndView list(ModelAndView mv) {
		mv.setViewName("admin/emp/list");
		mv.addObject("dept", ds.list());
		return mv;
	}
	
	//分页查询
	@RequestMapping(value="/emps", method=RequestMethod.GET)
	public Map<String, Object>  listData(PageBean<Emp> pb,EmpCriteria ec,int page,int rows) {
		Map<String, Object> datas=new HashMap<String, Object>();
		pb.setCurPage(page);
		pb.setRowsPerPage(rows);
		es.listByPageBean(pb, ec);
		
		datas.put("rows", pb.getData());
		datas.put("total", pb.getMaxRow());
		datas.put("page", pb.getCurPage());
		
		return datas;
	}
	//添加员工
	@RequestMapping(value="/emps", method=RequestMethod.POST)
	public Map<String, Object> add(Emp emp) {
		data.put(MSG, "添加失败！");
		data.put(STATUS, "2");
		try {
			if(es.add(emp)) {
				data.put(MSG, "添加成功！");
				data.put(STATUS, "1");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}
	//修改员工
	@RequestMapping(value="/emps/{id}", method=RequestMethod.PUT)
	public Map<String, Object> edit(@PathVariable("id") int id,Emp emp) {
		data.put(MSG, "修改失败！");
		data.put(STATUS, "2");
		try {
			if(es.update(emp)) {
				data.put(MSG, "修改成功！");
				data.put(STATUS, "1");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}
	
	//删除员工
	@RequestMapping(value="/emps/{id}", method=RequestMethod.DELETE)
	public Map<String, Object> delet(@PathVariable("id") String  id) {
		data.put(MSG, "删除失败！");
		data.put(STATUS, "2");
		try {
			if(es.delete(id)) {
				data.put(MSG, "删除成功！");
				data.put(STATUS, "1");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}
	//检查员工编号是否存在
	@RequestMapping("/emp_checkempno")
	public boolean emp_checkempno(int empno) {
		boolean flag=false;
		try {
			if(es.get(empno)!=null) {
				flag=true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
}
