<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

	 <%
	 if(session.getAttribute("MSG")!=null){
		%>
		 <div class="alert alert-danger alert-dismissible" role="alert">
	  <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	  <strong>Error!</strong><%=session.getAttribute("MSG")%>
	</div>
<%
		session.removeAttribute("MSG");
	}
	%>