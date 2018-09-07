package com.company.mybatis.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;


public class jsontolist {
	public static void main(String[] args) {
		//String json="{'id':'1','value':'北京'},},{'id':'2','value':'上海'},},{'id':'3','value':'广东'}";
		//Object[] list;
		//list=json.split(",},");
		/*for(int i=0;i<list.length;i++) {
			//将id:'1',value:'北京'转换成json对象
			JSONObject jsStr = JSONObject.parseObject(privace);
		}*/
		 String str = "[{'columnId':5,'columnName':'人文历史'},{'columnId':2,'columnName':'商业视野'}]}";
	        JSONArray jsonArray = null;
	        jsonArray = new JSONArray(str);
	        System.out.println(jsonArray.getJSONObject(1).get("columnId"));
	    
		
		
	}
}
