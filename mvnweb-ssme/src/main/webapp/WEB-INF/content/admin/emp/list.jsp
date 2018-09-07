<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

	<!-- 引入可编辑表格插件 -->
	<script type="text/javascript" src="/staticresource/easyui/jquery.edatagrid.js"></script>
	<!-- 员工列表 -->

<div id="emp_tb">
	<a id="emp_toadd" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">新增</a>
	<a id="emp_save" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true">保存</a>
	<a id="emp_delete" class="easyui-linkbutton"data-options="iconCls:'icon-cancel',plain:true">删除</a>
	<a id="emp_redo" class="easyui-linkbutton"data-options="iconCls:'icon-redo',plain:true">取消编辑</a>
</div>
<table id="emp_datagrid" >
	<thead>
		<tr>
			
			<th data-options="field:'ck',checkbox:true"></th>
			<th data-options="field:'empno',width:100" editor="{type:'validatebox',options:{required:true}}">员工编号</th>
			<th data-options="field:'ename',width:100" editor="text">员工姓名</th>
			<th data-options="field:'job',width:100" editor="{type:'numberbox',options:{precision:1}}">职务</th>
			<th data-options="field:'hiredate',width:100">入职时间</th>
			<th data-options="field:'mgr',width:100" editor="numberbox">上级</th>
			<th data-options="field:'sal',width:100" editor="numberbox">薪资</th>
			<th data-options="field:'comm',width:100" editor="numberbox">年终奖</th>
			<th data-options="field:'deptno',width:100" editor="numberbox">部门</th>
		</tr>
	</thead>
</table>

<script type="text/javascript">

$(function(){
	  
	
	$("#emp_toadd").on("click",function(){
		// 在第一行追加一个空行
		$('#emp_datagrid').edatagrid('addRow',0);

	});
	
	$("#emp_save").on("click",function(){
		// 保存
		var row = $('#emp_datagrid').datagrid('getSelections');
		$('#emp_datagrid').edatagrid('saveRow');
		console.info(row);

	});
	
	$("#emp_delete").on("click",function(){
		// 删除
		var row = $('#emp_datagrid').datagrid('getSelections');
		if(row.length!=0){
			var ids=[];
			for(var i=0;i<row.length;i++){
				ids.push(row[i].empno);
			}
			var empnos=ids.join(",")
			
			if(confirm("是否删除数据!")){
				$.ajax({
					url:"emp/emps/"+empnos,
					type:"delete",
					success:function(data){
						if(data.status==1){
							alert("删除成功!");
							$('#emp_datagrid').edatagrid('load');
						}else{
							alert("删除失败!");
						}
					}
				})
			}
		}else{
			alert("请选择要删除的数据!");
		}
	});
	
	
	$("#emp_redo").on("click",function(){
		// 取消编辑
		$('#emp_datagrid').edatagrid('cancelRow');
		//刷新
		$('#emp_datagrid').edatagrid('clearSelections');
	});
})


$(function(){
	var datagrid;// 定义全局变量
	var editRow=undefined;//当前编辑行
	
	datagrid=$('#emp_datagrid').edatagrid({   
		
		url:"/emp/emps",//加载的URL
		pagination:true,//显示分页
		pageSize:5,//分页大小
		pageList:[5,10,15,20],//每页的个数
	    height: 448,
		idField: "empno",
		iconCls:"icon-group",//图标
		method:'GET',
		checkbox : true,
		singleSelect : false,
		checkOnSelect : true,
		striped : true,
		rownumbers: true,
		fitColumns: true,
		toolbar:'#emp_tb',
		autoSave:true,
		onDblClickRow: function (rowIndex, rowData) {
	          //双击开启编辑行
	              if (editRow != undefined) {
	                  datagrid.datagrid("endEdit", editRow);
	              }
	              if (editRow == undefined) {
	                  datagrid.datagrid("beginEdit", rowIndex);
	                  editRow = rowIndex;
	              }
	          }
		
	    /* saveUrl: ...,    
	    updateUrl: ...,    
	    destroyUrl: ...    */ 
	});  

})






/* 增加修改按钮 */
/* function opFormater(value, row, index){ 
	return '<a class="op" onclick="emp_toedit()">编辑</a>'
}; */

</script>

