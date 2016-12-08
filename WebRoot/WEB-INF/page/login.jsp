<%@ page contentType="text/html;charset=utf-8" language="java" pageEncoding="utf-8"
	      import="java.util.*" %>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML>
<html>
  <head>
    <title>用户|登录</title>
    <base href="<%=basePath%>"> 
    <meta http-equiv="content-type" content="text/html;charset=gb2312">
	<link rel="stylesheet" href="plugin/css/font-awesome.min.css">
	<link rel="stylesheet" href="plugin/css/jquery-ui.min.css">
	<link rel="stylesheet" type="text/css" href="plugin/css/style.css">
	<script type="text/javascript" src="plugin/jQuery/jQuery-2.1.4.min.js"></script>	
	<script src="plugin/js/jquery-ui.min.js"></script>
	<script src="http://192.168.88.123:8080/web08Spring/plugin/js/webSocket.js"></script>
  </head> 
  <body>  
    <form id="loginFrom" name="loginFrom" method="post" action="login.do">
        <label>请输入USER_ID:</label>
        <input id="USER_ID" name="USER_ID" type="text"/>
        <button id="login" type="submit">登录</button>    
    </form>
    <h2 id="errorMsg">${errorMsg}</h2>
  </body>
</html>