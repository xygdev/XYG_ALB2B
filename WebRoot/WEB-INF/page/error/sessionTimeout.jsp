<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head> 
  <title>Session过期页面</title>
  <base href="<%=basePath%>">    
  <meta http-equiv="content-type" content="text/html;charset=gb2312">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta name="keywords" content="this by them, txt, custom websites, custom web applications, web development, mobile development, roadmapping, custom cms website, design with purpose">
  <link href="plugin/css/error/404.css" rel="stylesheet" type="text/css" media="screen, projection" />
  <script type="text/javascript" src="plugin/js/error/typekit.js"></script>
  <script type="text/javascript">try{Typekit.load();}catch(e){}</script>
  <script type="text/javascript" src="plugin/jQuery/jQuery-2.1.4.min.js"></script>
  <!--[if lte IE 9]>
  <script src="plugin/js/error/vendor-ie9.js" type="text/javascript"></script>
  <link href="plugin/css/error/404-ie9.css" rel="stylesheet" type="text/css" media="screen, projection" />
  <![endif]-->
</head>
<body class="x404 x404_index">
  <div class="animation-overlfow-hide">
  <div class="container no-page-wrapper">
    <h2>
      <span class="slogan">当前浏览页面的Session已过期</span><br><b>请点击<a id="quit" href="javascript:void(0)">此处</a>重新登陆</b>
    </h2>
    <div class="major-tom">
        <div class="toms-text">
          <p class="text-1">I Lost The Session...</p>
          <p class="text-2">Help Me!</p>
        </div>
        <figure class="legs"><img src="/image/error/legs.svg" /></figure>
        <figure class="left-arm"><img src="/image/error/left-arm.svg" /></figure>
        <figure class="right-arm"><img src="/image/error/right-arm.svg" /></figure>
        <figure class="torso"><img src="/image/error/torso.svg" /></figure>
    </div>
  </div>
  <canvas id="starfield" width="1020" height="1020" data-view="StarfieldView"></canvas>
  <script type="text/javascript">
      $('#quit').on('click',function(){
          if (top != self) {
             parent.$('#signout').click();
          }else{
             $(this).attr('href',$('base').attr('href'));
             window.location.href($('base').attr('href'));
          }
      });
      window.analytics||(window.analytics=[]),window.analytics.methods=["identify","track","trackLink","trackForm","trackClick","trackSubmit","page","pageview","ab","alias","ready","group","on","once","off"],window.analytics.factory=function(t){return function(){var a=Array.prototype.slice.call(arguments);return a.unshift(t),window.analytics.push(a),window.analytics}};for(var i=0;window.analytics.methods.length>i;i++){var method=window.analytics.methods[i];window.analytics[method]=window.analytics.factory(method)}window.analytics.load=function(t){var a=document.createElement("script");a.type="text/javascript",a.async=!0,a.src=("https:"===document.location.protocol?"https://":"http://")+"d2dq2ahtl5zl1z.cloudfront.net/analytics.js/v1/"+t+"/analytics.min.js";var n=document.getElementsByTagName("script")[0];n.parentNode.insertBefore(a,n)},window.analytics.SNIPPET_VERSION="2.0.8",
	  window.analytics.load("04kof9hy7t");
      window.analytics.page();
  </script>
  <script src="plugin/js/error/vendor.js" type="text/javascript"></script>
  <script src="plugin/js/error/all.js" type="text/javascript"></script>
</body>
</html>

