package com.company.empms.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.company.empms.commn.BaseAction;
import com.company.empms.service.DeptService;
import com.company.mybatis.commn.PageBean;
import com.company.mybatis.criteria.DeptCriteria;
import com.company.mybatis.entity.Dept;

@RestController
@RequestMapping("/dept")
public class DeptController extends BaseAction {

	@Resource // 注入DeptService
	DeptService ds;

	Dept dept = new Dept();

	// 跳转到list页面
	@RequestMapping("/depts/page")
	public ModelAndView list(ModelAndView mv) {
		mv.setViewName("admin/dept/list");
		return mv;
	}

	// 分页查询
	@RequestMapping(value = "/depts", method = RequestMethod.GET)
	public Map<String, Object> listData(PageBean<Dept> pb, DeptCriteria dc, int page, int rows) {
		Map<String, Object> datas = new HashMap<String, Object>();
		pb.setCurPage(page);
		pb.setRowsPerPage(rows);
		ds.listByPageBean(pb, dc);

		datas.put("rows", pb.getData());
		datas.put("total", pb.getMaxRow());
		datas.put("page", pb.getCurPage());
		return datas;
	}

	// 添加部门
	@RequestMapping(value = "/depts", method = RequestMethod.POST)
	public Map<String, Object> add(Dept dept) {
		data.put(MSG, "添加失败！");
		data.put(STATUS, "2");
		try {
			if (ds.add(dept)) {
				data.put(MSG, "添加成功！");
				data.put(STATUS, "1");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

	// 修改部门
	@RequestMapping(value = "/depts", method = RequestMethod.PUT)
	public Map<String, Object> edit(Dept dept) {

		data.put(MSG, "修改失败！");
		data.put(STATUS, "2");
		try {
			if (ds.update(dept)) {
				data.put(MSG, "修改成功！");
				data.put(STATUS, "1");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

	// 删除部门
	@RequestMapping(value = "/depts/{id}", method = RequestMethod.DELETE)
	public Map<String, Object> delet(@PathVariable("id") String id) {
		data.put(MSG, "删除失败！");
		data.put(STATUS, "2");
		try {
			if (ds.delete(id)) {
				data.put(MSG, "删除成功！");
				data.put(STATUS, "1");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

	// 检查部门编号是否存在
	@RequestMapping("/dept_checkDeptno")
	public boolean dept_checkDeptno(String deptno) {
		boolean flag = false;
		try {
			if (ds.get(deptno) != null) {
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	// export部门
	@RequestMapping(value = "/depts/export", method = RequestMethod.POST)
	public void export() {
		try {
			ds.export();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/download", method = RequestMethod.GET)
	public Map<String, Object> downstudents(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 一、从后台拿数据
		List<Dept> list = null;
		list=ds.list();
		try {
			// 二、 数据转成excel
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/x-download");

			String fileName = "dept.xls";
			fileName = URLEncoder.encode(fileName, "UTF-8");
			response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
			// 第一步：定义一个新的工作簿
			HSSFWorkbook wb = new HSSFWorkbook();
			// 第二步：创建一个Sheet页
			HSSFSheet sheet = wb.createSheet("deptsheet");
			HSSFCellStyle style=wb.createCellStyle();
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER);//设置居中(无效)
			sheet.setDefaultRowHeight((short) (256));// 设置行高
			sheet.setColumnWidth(0, 2000);// 设置列宽
			sheet.setColumnWidth(1, 5000);
			sheet.setColumnWidth(2, 5500);
			sheet.setColumnWidth(3, 5500);
			
			HSSFFont font = wb.createFont();
			font.setFontName("宋体");//设置字体
			font.setFontHeightInPoints((short) 16);//设置文字大小
			
			HSSFRow row = sheet.createRow(0);
			HSSFCell cell = row.createCell(0);
			cell.setCellValue("序号");
			cell = row.createCell(1);
			cell.setCellValue("部门编号");
			cell = row.createCell(2);
			cell.setCellValue("部门名称");
			cell = row.createCell(3);
			cell.setCellValue("地址");

			HSSFRow rows;
			HSSFCell cells;
			for (int i = 0; i < list.size(); i++) {
				// 第三步：在这个sheet页里创建一行
				rows = sheet.createRow(i + 1);
				// 第四步：在该行创建一个单元格
				cells = rows.createCell(0);
				// 第五步：在该单元格里设置值
				cells.setCellValue(i+1);
				cells = rows.createCell(1);
				cells.setCellValue(list.get(i).getDeptno());
				cells = rows.createCell(2);
				cells.setCellValue(list.get(i).getDname());
				cells = rows.createCell(3);
				cells.setCellValue(list.get(i).getLoc());

			}

			OutputStream out = response.getOutputStream();
			wb.write(out);
			out.close();
			wb.cloneSheet(0);
			data.put(STATUS, "1");
		} catch (IOException e) {
			data.put(STATUS, "2");
			e.printStackTrace();
		}
		return data;
	}

}
