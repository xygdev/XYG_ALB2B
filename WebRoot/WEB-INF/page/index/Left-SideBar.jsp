<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
  </head>
  
  <body>
    <script src="plugin/jQuery/jQuery-2.1.4.min.js"></script>  
    <script>
        $(function(){
      		var menu_url = 'menu/getPersonalMenu.do';
      	    $().menu(menu_url);   
      	    
      	    //动态生成面包屑
      	    
      	});
    </script>

    <aside class="main-sidebar" >
      <!-- sidebar: style can be found in sidebar.less -->
      <section class="sidebar">       
        <!-- sidebar menu: : style can be found in sidebar.less -->
        <ul class="sidebar-menu">      
        </ul>
      </section>
      <!-- sidebar -->
    </aside>
  </body>
</html>
