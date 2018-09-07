<%@page import="com.company.mybatis.status.Monitor"%>
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<nav class="navbar navbar-inverse navbar-fixed-top">
      <div class="container-fluid">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="admin.action">EmpMS 员工管理系统  </a>
			<span class="navbar-brand" style=" font-size: 15px; color: green;">当前在线人数：<%= Monitor.count.get() %></span>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav navbar-right">
           <!--  <li><a href="#">Dashboard</a></li>
            <li><a href="#">Settings</a></li>
            <li><a href="#">Profile</a></li> -->
            <li><span style="line-height: 50px; color: #fff">您好， ${USER.username}(
            <c:choose>
            	<c:when test="${USER.username=='admin'}">管理员</c:when>
            	<c:when test="${USER.username=='test'}">测试员</c:when>
            	<c:otherwise>普通用户</c:otherwise>
            </c:choose>
            
            
            )</span></li>
            <li><a href="/outLogin">安全退出</a></li>
          </ul>
       <!--    <form class="navbar-form navbar-right">
            <input type="text" class="form-control" placeholder="Search...">
          </form> -->
        </div>
      </div>
    </nav>
    
    
