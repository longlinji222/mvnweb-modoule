<%@ page language="java" import="java.util.*" trimDirectiveWhitespaces="true" contentType="text/plain; charset=UTF-8" pageEncoding="UTF-8"%>  
<%
//通过验证码文本框名称获得输入的
String vc = request.getParameter("captcha");  //数据

String res = "false";


if (vc != null && vc.equals(session.getAttribute("rand"))) {
    res = "true";
}
out.print(res);  //输出true代表通过，false代表未通过
%>