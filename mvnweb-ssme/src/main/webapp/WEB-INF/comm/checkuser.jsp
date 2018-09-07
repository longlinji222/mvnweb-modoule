<%@page import="com.company.mybatis.entity.Myuser"%>
    
<%
String path2 = request.getContextPath();
String basePath2 = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path2+"/";

Myuser u=(Myuser)session.getAttribute("USER");
System.out.print(u);
if(u==null){
	response.sendRedirect(basePath2+"toLogin");
	return;
}


%>