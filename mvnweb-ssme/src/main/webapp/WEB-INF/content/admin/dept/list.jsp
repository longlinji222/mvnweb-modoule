<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<div id="dept_search">用户名：
	<input type="text" id="sdname" name="sdname" class="easyui-textbox"/>	
	<a class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" id="dept_searchbtn" ></a>
</div>


<div id="dept_tb">
	<a id="dept_toadd" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">新增</a>
	<a id="dept_delete" class="easyui-linkbutton"data-options="iconCls:'icon-cancel',plain:true">删除</a>
	<a id="dept_export" class="easyui-linkbutton"data-options="iconCls:'icon-edit',plain:true">导出表单</a>
</div>

<!-- 部门列表 -->
<table id="dept_datagrid" >
	<thead>
		<tr>
			<th data-options="field:'ck',checkbox:true"></th>
			<th data-options="field:'deptno',width:100">部门编号</th>
			<th data-options="field:'dname',width:100">部门名称</th>
			<th data-options="field:'loc',width:100,align:'right'">地址</th>
			<th data-options="field:'x',width:100,align:'center',formatter:opFormater">操作</th>
		</tr>
	</thead>
</table>

<!-- 新增部门form -->
<div id="dept_adddiv" align="center" class="easyui-dialog" data-options="modal:true,closed: true">
	<div>
		<form id="modalForm" class="easyui-form" method="post"
			data-options="novalidate:true" align="center">
			<table cellpadding="10" align="center">
				<tr>
					<td style="text-align:right">部门ID:</td>
					<td style="width:20px"><input class="easyui-textbox " type="text" id="deptno" name="deptno"
						style="height: 30px; width: 180px;" 
						data-options="required:true,prompt:'请输入部门ID'"></input></td>
				</tr>
				<tr>
					<td style="text-align:right">部门名称:</td>
					<td style="width:20px"><input class="easyui-textbox " type="text" d="dname" name="dname"
						style="height: 30px; width: 180px;" 
						data-options="required:true,prompt:'请输入部门名称'"></input></td>
				</tr>
				<tr>
					<td style="text-align:right">部门地址:</td>
					<td style="width:20px"><input class="easyui-textbox " type="text" id="loc" name="loc"
						style="height: 30px; width: 180px;" 
						data-options="required:true,prompt:'请输入部门地址'"></input></td>
				</tr>
			</table>
		</form>
	</div>
</div>

<!-- function -->
<script type="text/javascript">

/* 分页查询 */
	$('#dept_datagrid').datagrid({
		url:"/dept/depts",//加载的URL
		pagination:true,//显示分页
		pageSize:5,//分页大小
		pageList:[5,10,15,20],//每页的个数
	    height: 448,
		idField: "deptno",
		iconCls:"icon-group",//图标
		method:'GET',
		checkbox : true,
		singleSelect : false,
		checkOnSelect : true,
		striped : true,
		rownumbers: true,
		fitColumns: true,
		toolbar:'#dept_tb',
		onLoadSuccess:function(){
			$('.op').linkbutton({
			    iconCls: 'icon-edit',
			    plain:true
			});
		}
	});
	
	/* 搜索 */
	$('#dept_searchbtn').on("click",function(){
		$('#dept_datagrid').datagrid('load',{
			dname:$('#sdname').val()
		});
	})
	/* 增加修改按钮 */
	function opFormater(value, row, index){ 
		return '<a class="op" onclick="dept_toedit()">编辑</a>'
	};
	
	/* 编辑部门 */
	function dept_toedit(){
		var row = $('#dept_datagrid').datagrid('getSelections');
		if(row.length==1){
			/* 将获取到的数据填充到form表单 */
				$("#modalForm").form('load',{
					deptno:row[0].deptno,
					dname:row[0].dname,
					loc:row[0].loc
				});
				
			$('#dept_adddiv').dialog({    
			    title: '修改部门',
			    width: 340,    
			    height: 250, 
			    draggable:false,
			    constrain:true,
			    closed: false,   
			    cache: false,    
			    modal: true,
			    buttons:[{
					text:'修改',
					handler:function(){
						// 1. 验证
						if($('#modalForm').form('enableValidation').form('validate')){
							console.info('验证通过');
							// 2. 提交
							$.ajax({
								url:"dept/depts/",
								type:"put",
								data:$("#modalForm").serialize(),
								success:function(data){
									if(data.status==1){
										$('#dept_datagrid').datagrid("load");
										$('#modalForm').form('clear');
										$('#dept_adddiv').window('close');
									}else{
										$.messager.alert('警告','修改失败!'); 
									}
								}
							})

						}else{
							console.info('验证未通过');	
						}
					}
				},{
					text:'关闭',
					handler:function(){
						$('#dept_adddiv').window('close');
					}
				}]
			});
		}
	}
$(function(){
	/* 新增部门js */
	$('#dept_toadd').on('click',function(){
		$('#modalForm').form('clear');//清空表单
		$('#modalForm').form('disableValidation');//关闭验证
		$('#dept_adddiv').dialog({    
		    title: '新增部门',
		    width: 340,    
		    height: 250, 
		    draggable:false,
		    constrain:true,
		    closed: false,   
		    cache: false,    
		    modal: true,
		    buttons:[{
				text:'保存',
				handler:function(){
					var url="dept/depts";
					if($('#modalForm').form('enableValidation').form('validate')){
						$.post(url,$("#modalForm").serialize(),function(data){
							if(data.status==1){
								$('#dept_datagrid').datagrid("load");
								$('#modalForm').form('clear');
								$('#dept_adddiv').window('close');
							}else{
								$.messager.alert('警告','添加失败!'); 
							}
						})
					}else{
						console.info('验证未通过');	
					}
				}
			},{
				text:'关闭',
				handler:function(){
					$('#dept_adddiv').window('close');
				}
			}]
		});
	});
	
	/* 删除 */
	$('#dept_delete').on('click',function(){
		var row = $('#dept_datagrid').datagrid('getSelections');
		if(row.length!=0){
			var ids=[];
			for(var i=0;i<row.length;i++){
				ids.push(row[i].deptno);
			}
			var deptno=ids.join(",")
			var dele="DELETE";
			if(confirm("是否删除选中项!")){
				$.post("/dept/depts/"+deptno,{"_method":dele},function(data){
					if(data.status==1){
						$('#dept_datagrid').datagrid("load");
					}else{
						$.messager.alert('警告','删除失败!'); 
					}
				})
			}
		}else{
			alert("请选择要删除项");
		}
	});
	/* export */
	$('#dept_export').on('click',function(){
		window.open("/dept/download")
	});
	
	
});
</script>