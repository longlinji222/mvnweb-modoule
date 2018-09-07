<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	
	<script type="text/javascript">
	
	<%-- 验证码输入错误 --%>
	<%
	if(session.getAttribute("MSG")!=null){
		%>
		alert('<%=session.getAttribute("MSG")%>');
		<%
		session.removeAttribute("MSG");
	}
	%>

	</script>
	
	 
