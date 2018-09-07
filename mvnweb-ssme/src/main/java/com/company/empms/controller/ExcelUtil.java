package com.company.empms.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;
 
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
 
public class ExcelUtil {
 
    public static Workbook createSingleWorkBook(List<Map<String, Object>> list,String []keys,String columnNames[]) {
        Workbook wb = new HSSFWorkbook();
        Sheet sheet = wb.createSheet(list.get(0).get("sheetName").toString());
        for(int i=0;i<keys.length;i++)
        {
            sheet.setColumnWidth((short) i, (short) (35.7 * 150));
        }
 
        Row row = sheet.createRow((short) 0);
 
        CellStyle cs = wb.createCellStyle();
        CellStyle cs2 = wb.createCellStyle();
 
        Font f = wb.createFont();
        Font f2 = wb.createFont();
 
        f.setFontHeightInPoints((short) 10);
        f.setColor(IndexedColors.BLACK.getIndex());
        f.setBoldweight(Font.BOLDWEIGHT_BOLD);
 
        f2.setFontHeightInPoints((short) 10);
        f2.setColor(IndexedColors.BLACK.getIndex());
 
        cs.setFont(f);
        cs.setFillForegroundColor(IndexedColors.AQUA.getIndex());
        cs.setFillPattern(CellStyle.SOLID_FOREGROUND);
        cs.setBorderLeft(CellStyle.BORDER_THIN);
        cs.setBorderRight(CellStyle.BORDER_THIN);
        cs.setBorderTop(CellStyle.BORDER_THIN);
        cs.setBorderBottom(CellStyle.BORDER_THIN);
        cs.setAlignment(CellStyle.ALIGN_CENTER);
 
        cs2.setFont(f2);
        cs2.setBorderLeft(CellStyle.BORDER_THIN);
        cs2.setBorderRight(CellStyle.BORDER_THIN);
        cs2.setBorderTop(CellStyle.BORDER_THIN);
        cs2.setBorderBottom(CellStyle.BORDER_THIN);
        cs2.setAlignment(CellStyle.ALIGN_CENTER);
        for(int i=0;i<columnNames.length;i++){
            Cell cell = row.createCell(i);
            cell.setCellValue(columnNames[i]);
            cell.setCellStyle(cs);
        }
        for (short i = 1; i < list.size(); i++) {
            Row row1 = sheet.createRow((short) i);
            for(short j=0;j<keys.length;j++){
                Cell cell = row1.createCell(j);
                cell.setCellValue(list.get(i).get(keys[j]) == null?" ": list.get(i).get(keys[j]).toString());
                cell.setCellStyle(cs2);
            }
        }
        return wb;
    }
    
    public static void ExcelSingleOutputStream(List<Map<String, Object>> list,String []keys,String columnNames[],
    		String fileName, HttpServletRequest request, HttpServletResponse response)
	{
    	ByteArrayOutputStream os = new ByteArrayOutputStream();
    	try {
 			createSingleWorkBook(list,keys,columnNames).write(os);
		} catch (IOException e2) {
			e2.printStackTrace();
		}
    	ExcelOutputStream(fileName,request,response,os);
	}
    
    private static void ExcelOutputStream( String fileName, HttpServletRequest request, HttpServletResponse response,ByteArrayOutputStream os)
    {
        byte[] content = os.toByteArray();
        InputStream is = new ByteArrayInputStream(content);
        
        response.reset();
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        try {
			response.setHeader("Content-Disposition", "attachment;filename="+ new String((fileName + ".xls").getBytes(), "iso-8859-1"));
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
        ServletOutputStream out = null;
		try {
			out = response.getOutputStream();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
 
        try {
            bis = new BufferedInputStream(is);
            bos = new BufferedOutputStream(out);
            byte[] buff = new byte[2048];
            int bytesRead;
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
        } catch ( IOException e) {
            e.printStackTrace();
        } finally {
            if (bis != null)
				try {
					bis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
            if (bos != null)
				try {
					bos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
        }
    }
}
