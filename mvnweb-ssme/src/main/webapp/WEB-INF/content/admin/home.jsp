<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<base href="<%=basePath%>">
	<title>EMP系统</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--<link rel="stylesheet" type="text/css" href="styles.css">-->
	<!-- 引入EASYUI主题 -->
	<link id="link" rel="stylesheet" type="text/css" href="/staticresource/easyui/themes/default/easyui.css">
	<!-- 引入EASYUI CSS样式-->
	<link rel="stylesheet" type="text/css" href="/staticresource/easyui/themes/icon.css">
	<!--引入JQUERY JS  -->
	<script type="text/javascript" src="/staticresource/easyui/jquery.min.js"></script>
	<!--引入EASYUI JS  -->
	<script type="text/javascript" src="/staticresource/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="/staticresource/easyui/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="staticresource/echarts/echarts.min.js"></script>
	<!-- 引入可编辑表格插件 -->
	<script type="text/javascript" src="/staticresource/easyui/jquery.edatagrid.js"></script>
	
</head>
<body>
	<!-- 主框 -->
	<div id="cc" class="easyui-layout" style="width:100%;height:600px;">   
		<!-- 北 -->
	    <div data-options="region:'north',title:'皮肤',split:true,collapsible:false" style="height:80px;">
	    	<div id="themeshead" style="float:left">
				<input id="themes" style="width: 100px;"/>
			</div>
			<p align="center" style="font-size: 30px;line-height: 0px;font-family:cursive;">员工管理系统</p>
	    </div>
	    <!-- 西 -->
	    <div data-options="region:'west',title:'导航栏',split:true" style="width:150px;">
	    	<ul id="tt">菜单</ul>
	    </div>
	    <!-- 展示区 -->
    	<div id="tb" class="easyui-tabs" data-options="region:'center'">
    		<!-- <ul id="tb" class="easyui-tabs"></ul> -->
    	</div>
	</div>  
</body>
</html>
<script type="text/javascript">

/* 转换json数据 */
function convert(rows) {
	function exists(rows, parentId) {
		for (var i = 0; i < rows.length; i++) {
			if (rows[i].id == parentId)
				return true;
		}
		return false;
	}

	var nodes = [];
	// get the top level nodes
	for (var i = 0; i < rows.length; i++) {
		var row = rows[i];
		if (!exists(rows, row.parentId)) {
			nodes.push(row);
		}
	}

	var toDo = [];
	for (var i = 0; i < nodes.length; i++) {
		toDo.push(nodes[i]);
	}
	while (toDo.length) {
		var node = toDo.shift(); // the parent node
		// get the children nodes
		for (var i = 0; i < rows.length; i++) {
			var row = rows[i];
			
			if (row.parentId == node.id) {
				
				if (node.children) {
					node.children.push(row);
				} else {
					node.children = [ row ];
				}
				toDo.push(row);
			}
		}
	}
	
	return nodes;
}

/* 跳转页面 */
function addTab(title, url) {
	if ($('#tb').tabs('exists', title)) {
		$('#tb').tabs('select', title);
	} else {
		$('#tb').tabs('add', {
			title : title,
			href : url,
			closable : true
		});
	}
}


$(function(){
	/* 修改主题 */
	$("#themes").combobox({
		 valueField:'value',
		 textField:'id',
		 url:"json/themes.json",
		 onChange:function(newValue,oldValue){
			$("#link").prop("href",$("#themes").combobox("getValue",'none'));
		 },
	});
	
	
	/* 加载树 */
	$('#tt').tree({
	    url:'json/nav.json',
	   loadFilter:function(data){
		   data=convert(data);
			return data;
	   },
	   onLoadSuccess:function(){
		   /* $('#tt').tree('collapseAll'); */
	   },
	   onClick: function(node){
			if(node.href){
				addTab(node.text,node.href);
			}
		}
	}); 
})
</script>
