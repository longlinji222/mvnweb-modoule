<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<base href="<%=basePath%>">
	<title><s:message code="login.h1"></s:message></title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!-- 引入EASYUI主题 -->
	<link id="link" rel="stylesheet" type="text/css" href="/staticresource/easyui/themes/default/easyui.css">
	<!-- 引入EASYUI CSS样式-->
	<link rel="stylesheet" type="text/css" href="/staticresource/easyui/themes/icon.css">
	<!--引入JQUERY JS  -->
	<script type="text/javascript" src="/staticresource/easyui/jquery.min.js"></script>
	<!--引入EASYUI JS  -->
	<script type="text/javascript" src="/staticresource/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="/staticresource/easyui/locale/easyui-lang-zh_CN.js"></script>
</head>

<body>
	<div style="width: 500px; margin: 60px auto" align="center">
		
	<div class="easyui-panel" title="用户登录" style="width: 400px;">
		<div id="themeshead" style="float:center">
			<input id="themes" style="width: 100px;" />
		</div>
		<div style="padding: 10px 10px 10px 10px;">
			<form id="ff" class="easyui-form" method="post"
				data-options="novalidate:true" action="login" align="center">
				<table cellpadding="10" align="center">
					<tr>
						<td style="text-align:right">账号:</td>
						<td style="width:20px"><input class="easyui-textbox " type="text" name="username" value="admin"
							style="height: 30px; width: 180px;" 
							data-options="validType:'length[3,10]',required:true,prompt:'请输入账号'"></td>
					</tr>
					<tr>
						<td style="text-align:right">密码:</td>
						<td id="pwda"><input id="pwd" class="easyui-passwordbox" type="text" name="userpwd" value="123456"
							style="height: 30px; width: 180px;" data-options="required:true,prompt:'请输入密码'"></td>
					</tr>
					<tr>
						<td style="text-align:right">验证码:</td>
						<td>
							<input class="textbox" type="text" id="captcha" name="captcha" style="height: 30px; width: 85px;float:left;"
								data-options="prompt:'请输入验证码'" />
							<img id="img" alt=" alt="验证码" src="VerifyCode.jsp" onclick="this.src='VerifyCode.jsp?r='+new Date()" style="height: 30px; width: 85px;float:right;">
						</td>
					</tr>
					<span id="username_msg"></span><br/>
				</table>
			</form>
			<div style="text-align: center; padding: 5px">
				<a class="easyui-linkbutton" iconCls="icon-man" id="loginBtn">Login</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<a class="easyui-linkbutton" iconCls="icon-clear" id="resetBtn">Reset</a>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">

	$('#loginBtn').on('click',function(){
		// 1. 验证
		if($('#ff').form('enableValidation').form('validate')){
			console.info('验证通过');
			// 2. 提交
			$('#ff')[0].submit();
		}else{
			console.info('验证未通过');	
		}
	});

	$('#resetBtn').on('click',function(){
		$('#ff').form('clear')
	});
	
</script>
	
	
</body>
</html>
<script type="text/javascript">
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
	
	/* 验证验证码 */
	/* $("#captcha").textbox({
		novalidate:true,
		required: true,
	    validType: "remote['login_checked','captcha']"
	}); */
	/* 开启验证码 验证 */
	/* $('#captcha+span input:first').on('blur',function(){
		 //1. 验证 
		if($('#captcha').textbox('enableValidation').textbox('isValid')){
			console.info('验证码验证通过');
			$("#submit").attr("disabled","");
		}else{
			console.info('验证码验证未通过');	
			$("#img").attr('src','VerifyCode.jsp?r='+new Date());
		}
	});  */
	
})
</script>




