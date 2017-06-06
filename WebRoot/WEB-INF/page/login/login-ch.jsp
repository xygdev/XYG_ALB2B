<%@ page language="java" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE>
<html>
  <head>
    <base href="<%=basePath%>">    
    <title>信义玻璃</title>   
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE9,chrome=1">   
    <!-- 图标cdn引入 -->
    <link rel="stylesheet" href="plugin/css/font-awesome.min.css">
    <!-- 新 Bootstrap 核心 CSS 文件 -->
	<link rel="stylesheet" href="plugin/bootstrap/css/bootstrap.min.css">
	<!-- 核心布局 CSS文件 -->
	<link rel="stylesheet" href="plugin/css/login/login.css">
	<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
	<script src="plugin/jQuery/jQuery-2.1.4.min.js"></script>
	<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
	<script src="plugin/bootstrap/js/bootstrap.min.js"></script>	
  </head>
  
  <body>
    <!--[if lte IE 9]>
    <!--<![endif]-->
    
    <!-- 欢迎页区域 start -->
    <div id="welcome-page" style="opacity:1">
      <div class="reveal-modal-bg"></div>
      <!-- 右上角区域 start -->
      <!-- 
      <div class="dropdown langue-frame">
   		<button type="button" class="btn dropdown-toggle langue-btn" id="dropdownMenu1" data-toggle="dropdown">
                            语言<span class="caret"></span>
   		</button>
   		<ul class="dropdown-menu" role="menu" style="min-width:100px" aria-labelledby="dropdownMenu1">
      	  <li role="presentation">
            <a role="menuitem" tabindex="-1" href="login-ch.jsp">中文</a>
          </li>
          <li role="presentation">
            <a role="menuitem" tabindex="-1" href="login-en.jsp">英文</a>
          </li>
        </ul>
      </div>
       -->
      <!-- 右上角区域 end -->
      <!-- 左上角区域 start -->
      <div class="logo-frame">
        <img src="/image/login/xyg.png" class="logo-img">
        <h1 class="logo-desc">信义玻璃</h1>
      </div>
      <!-- 左上角区域 end -->
      <!-- 正中间区域 start -->
      <div class="center-text">
        <h1 class="welcome-text">欢 迎 来 到 信 义 玻 璃 B2B 电 子 商 务 平 台</h1> 
        <button type="button" class="btn start-btn">点击进入</button>   
      </div>
      <!-- 正中间区域 end -->  
      <!-- 背景图轮播区域 start -->
      <div id="myCarousel" class="carousel slide">   	  
   	    <div class="carousel-inner">
      	  <div class="item active bg-img bg1">
      	    <div class="img-desc">
      	      <!--  
              <h1>深圳龙岗大运体育馆</h1>
      	      <h3>外墙玻璃产品由信义玻璃提供</h3>
      	       -->
      	    </div>
      	  </div>
      	  <div class="item bg-img bg2">
      	    <div class="img-desc">
      	      <!--
      	      <h1>深圳龙岗大运体育馆</h1>
      	      <h3>外墙玻璃产品由信义玻璃提供</h3>
      	       -->
      	    </div>
      	  </div>
      	  <div class="item bg-img bg3">
      	    <div class="img-desc">
      	      <!--  
      	      <h1>深圳京基100大厦</h1>
      	      <h3>外墙双银Low-E中空玻璃由信义玻璃提供</h3>
      	       -->
      	    </div>
      	  </div>
      	  <div class="item bg-img bg4">
      	    <div class="img-desc">
      	      <!--
      	      <h1>北京数字大厦</h1>
      	      <h3>外墙双银Low-E中空玻璃由信义玻璃提供</h3>
      	       -->
      	    </div>
      	  </div>
   	    </div>
	  </div> 
	  <!-- 背景图轮播区域 end -->
	</div>
	<!-- 欢迎页区域 end -->
	
	<!-- 登录页区域 start -->
	<div id='login-page'>
	  <!-- 右上角区域 start -->
	  <button type='button' class="go-back white">
	    <i class="fa fa-reply fa-2x" aria-hidden="true"></i>	    
	  </button>
	  <!-- 右上角区域 end -->
	  <!-- 正中间区域 start -->
	  <div class="login-frame">
	    <img src="/image/login/xyg.gif" style="width:100%">
	    <form role="form" id="loginFrom" name="loginFrom" method="post" action="login.do">
   		  <div class="form-group" style="padding:0 25px">
     	    <label for="name">用户名</label>
      	    <input type="text" class="form-control" id="username" name="username" placeholder="请填入用户名" autocomplete="off" required="required"/>
   		  </div>
   		  <div class="form-group" style="padding:0 25px">
     	    <label for="name">密码</label>
      	    <input type="password" class="form-control" id="password" name="password" placeholder="请填入密码" autocomplete="off" required="required"/>
   		  </div>
   		  <input type="hidden" id="lang" name="lang" value="ZHS"/>
   		  <button type="submit" class="btn btn-defult login-btn">登录</button>
	    </form>
	    <hr></hr>
	    <p class="split-line" style="width:40%">忘记密码?</p>
	    <button type="button" class="btn etc-btn" style="background-color:#2C4477 !important">
	      <i style="font-size:1.5em !important" class="fa fa-volume-control-phone"></i>
	      <span style="font-size:1.1em;line-height:0.5">打电话报警</span>      
	    </button>
	    <button type="button" class="btn etc-btn" style="margin:15px 0;background-color:#BB3C31 !important">
	      <i style="font-size:1.5em !important" class="fa fa-gavel fa-2x"></i>
	      <span style="font-size:1.1em;line-height:0.5">砸掉电脑</span>      
	    </button>	    
	  </div>
	  <input type="hidden" id="errorMsg" value=${errorMsg} >
	  <%
	     session.setAttribute("errorMsg",null); 
	   %>
	  <!-- 正中间区域 end -->
	</div>
	<!-- 登录页区域 end -->
	
  <script type="text/javascript" src="plugin/layer/layer.js"></script>
  <script>
  	$('#myCarousel').carousel({
  		interval: 5000
	})
	
	errorMSG=$('#errorMsg').val();
	if(errorMSG!=null&&errorMSG!=''){
	    $("#welcome-page").animate({"opacity":0});
	    $("#welcome-page").css("display","none");
	    $("#login-page").animate({"opacity":1});
	    $('.start-btn')[0].click();
	    layer.alert(errorMSG,{title:'警告',offset:[150]});
	}
	
	$('.start-btn').on('click',function(){
	    $("#welcome-page").animate({"opacity":0});
	    $("#welcome-page").css("display","none");
	    $("#login-page").animate({"opacity":1});
	});
	
	$('.go-back').on('click',function(){	    
	    $("#welcome-page").css("display","");
	    $("#login-page").animate({"opacity":0});
	    $("#welcome-page").animate({"opacity":1});
	});
  </script>
  </body>
</html>