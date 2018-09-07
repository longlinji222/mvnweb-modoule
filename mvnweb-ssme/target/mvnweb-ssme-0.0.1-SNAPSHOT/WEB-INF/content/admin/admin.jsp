<%@page import="com.company.mybatis.entity.Myuser"%>
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
 <% 
 String path = request.getContextPath();
 String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
 
 %>
<%@ include file="../../comm/checkuser.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<base href="<%=basePath%>">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">	
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--<link rel="stylesheet" type="text/css" href="styles.css">-->
	<%@ include file="../../comm/header.jsp" %>
	<link rel="stylesheet" type="text/css" href="/staticresource/css/dashboard.css">

</head>
<body>

    <div class="container-fluid">
      <div class="row">
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
          <div class="row">
            <div class="col-md-12 col-lg-12 col-sm-12" id="main" style="height: 400px">
            </div>
          </div>
          
        <!-- 图表 -->
       <script type="text/javascript">
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('main'));

        // 指定图表的配置项和数据
        var option = {
            title: {
                text: '部门员工统计'
            },
            tooltip: {},
            legend: {
                data:['部门人数']
            },
            xAxis: {
                data: [${xAxisData}]
            },
            yAxis: {},
            series: [{
                name: '数量',
                type: 'bar',
                data: [${seriesData}]
            }]
        };

        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
    </script>
        </div>
      </div>
    </div>
	
	<%@ include file="../../comm/footer.jsp" %>
</body>
</html>