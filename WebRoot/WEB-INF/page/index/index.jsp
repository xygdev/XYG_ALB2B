<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base id="base_id" href="<%=basePath%>">    
    <title>信义玻璃B2B平台</title>
	<meta http-equiv="content-type" content="text/html;charset=gb2312">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.5 -->
    <link rel="stylesheet" href="plugin/bootstrap/css/bootstrap.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="plugin/css/font-awesome.min.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="plugin/bootstrap/css/AdminLTE.css">
    <!-- AdminLTE Skins -->
    <link rel="stylesheet" href="plugin/bootstrap/css/skins/_all-skins.min.css"> 
    <!-- 私有样式表 -->
    <link rel="stylesheet" href="plugin/css/index/index.css"> 
    <!-- Websocket -->
    <script src="plugin/js/webSocket.js"></script>
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    <style>
        .sidebar-toggle:hover{
            background-color:#006633 !important;
        }
        .dark-green{
            background-color:#006633 !important;
        }
        .green{
            background-color:#009933 !important;
        }
        .sidebar-menu >li:hover>a,
        .sidebar-menu >li.active>a{
            border-left-color:#006633 !important;
        }
    </style>
  </head>
  
  <body class="hold-transition skin-blue sidebar-mini sidebar-collapse" >  
    <div class="wrapper">    
       <header class="main-header">
        <!-- Logo -->
        <a href="#" data-iframehref="home.do" class="logo dark-green">
          <!-- mini logo for sidebar mini 50x50 pixels -->
          <span class="logo-mini"><b>X</b>YG</span>
          <!-- logo for regular state and mobile devices -->
          <span class="logo-lg"><b>Xinyi</b>GLASS</span>
        </a>
        <!-- Header Navbar: style can be found in header.less -->
        <nav class="navbar navbar-static-top green" role="navigation">
          <!-- Sidebar toggle button-->
          <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
            <span class="sr-only">Toggle navigation</span>
          </a>
          <!-- Navbar Right Menu -->
          <div class="navbar-custom-menu">
            <ul class="nav navbar-nav"> 
              <li class="dropdown messages-menu" id="mailbox">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                  <i class="fa fa-envelope-o"></i>
                  <span data-type="tips" class="label label-danger"></span>
                </a>
                <ul class="dropdown-menu">
                  <li class="header">你有<span data-type="inside"></span>条未读消息</li>
                  <li>
                    <!-- 新邮件消息区域 start -->
                    <ul id="unread_menu" class="menu"></ul>  
                    <!-- 新邮件消息区域 end -->          
                  <li class="footer pointer"><a href="#" data-iframehref="mail/RecMail.do" data-title="收件箱">点击查看全部</a></li>
                </ul>
              </li>          
              <!-- User Account: style can be found in dropdown.less -->
              <li class="dropdown user user-menu">
                <input type="hidden" id="USER_ID" value="${USER_ID}"/> 
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                  <img id="user-img-sm" src="/image/user/${IMG}" class="user-image" alt="User Image">
                  <span class="hidden-xs">${DESC}</span>
                </a>
                <ul class="dropdown-menu">
                  <!-- User image -->
                  <li class="user-header green">
                    <img id="user-img-md" src="/image/user/${IMG}" class="img-circle" alt="User Image">
                    <p>
                      ${DESC} - ${RESP}
                      <small>Member since Nov. 2012</small>
                    </p>
                  </li>
                  <!-- Menu Footer-->
                  <li class="user-footer">
                    <div class="pull-left">
                      <a href="#" class="btn btn-default btn-flat" data-reveal-id="modifyPWD" data-bg="pwd-modal-bg" data-dismissmodalclass="close-pwd-frame">修改密码</a>
                    </div>
                    <div class="pull-left">
                      <a href="#" id="changeIMG" class="btn btn-default btn-flat" style="margin-left:6px" data-reveal-id="headimg" data-bg="pwd-modal-bg">更换头像</a>
                    </div>
                    <div class="pull-right">
                      <form id="logoutFrom" name="logoutFrom" method="post" action="logout.do">
                        <button id="signout" type="submit" class="btn btn-default btn-flat">退出登录</button>
                      </form>
                    </div>
                  </li>
                </ul>
              </li>          
            </ul>
          </div>
        </nav>
      </header>
      
      <jsp:include page="Left-SideBar.jsp"/>
      
      <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
          <h1 id="test">
           	 信义玻璃B2B电子商务平台
            <small>Version 1.0</small>
          </h1>
          <ol class="breadcrumb">
            <li><a href="#" data-iframehref="home.do"><i class="fa fa-dashboard"></i> 主页</a></li>
          </ol>
        </section>

        <!-- Main content -->
        <section class="content" style="padding:0">
          <!-- Info boxes -->
          <iframe frameborder="0" src="home.do" id="mainframe" scrolling="yes" framespacing="0"  style="width:100%;padding:0" ></iframe>
        </section><!-- /.content -->
        
        <!-- 用户信息存放区域 start -->
     	<input type="hidden" id="USER_ID" value="${USER_ID}"/>  
     	<input type="hidden" id="USER_NAME" value="${USER_NAME}"/>
        <button id="mailrefresh" data-mailtype="unread" data-counturl="mail/countUnReadMail.do" data-mailurl="mail/findUnReadMail.do" data-pageframe="mailbox" style="visibility:hidden;position:fixed"></button>
     	<!-- 用户信息存放区域 end -->
     	
     	
      <!-- 密码修改弹出框 start -->
      <div class="pwd-modal-bg"></div>
      <div id="modifyPWD" class="pwd_frame">
        <div class='title'>      
          <span><i class="fa fa-user fa-1x" aria-hidden="true"></i>&nbsp;修改密码</span>
        </div>
        <a class="close-pwd-frame" data-type="close">&#215;</a>
        <div class='line'></div>
          <div class='content'>
            <form id='updateData'>
              <label for='O_PASSWORD' class='left'>原密码</label>
              <input type='password' id='O_PASSWORD' name='O_PASSWORD' data-update="db" class='left password'/>
              <i class="fa fa-eye-slash pointer left" data-pwd="show" data-frame="O_PASSWORD"></i>
              <i class="fa fa-eye pointer left hide" data-pwd="hide" data-frame="O_PASSWORD"></i>
              <label for='N_PASSWORD' class='left'>新密码</label>
              <input type='password' id='N_PASSWORD' name='N_PASSWORD' data-update="db" class='left password'/>
              <i class="fa fa-eye-slash pointer left" data-pwd="show" data-frame="N_PASSWORD"></i>
              <i class="fa fa-eye pointer left hide" data-pwd="hide" data-frame="N_PASSWORD"></i>
            </form>
          </div>
          <div class='foot'>       
            <button id="confirm" class="right update_confirm pointer">确认修改</button>
          </div>   
        </div>
        <!-- 密码修改弹出框 end-->
      </div><!-- /.content-wrapper -->   
      
      <!-- 用户头像区域 start -->
      <jsp:include page="../public/headImg.jsp">
        <jsp:param name="headImgType" value="personal" />
      </jsp:include>
      <!-- 用户头像区域 end -->  
    </div>
    
    
    
    <!-- jQuery 2.1.4 -->
    <script src="plugin/jQuery/jQuery-2.1.4.min.js"></script>
    <!-- Bootstrap 3.3.5 -->
    <script src="plugin/bootstrap/js/bootstrap.min.js"></script>
    <!-- AdminLTE App -->
    <script src="plugin/bootstrap/js/app.min.js"></script>
    <script src="plugin/js/auto_menu.js"></script> 
    <script type="text/javascript" src="plugin/js/jQuery.mail.js"></script>  
    <script type="text/javascript" src="plugin/js/jQuery.reveal.js"></script> 
    <script type="text/javascript" src="plugin/js/cropbox.js"></script>
    <script type="text/javascript">
        $('i[data-pwd]').on('click',function(){
    		if($(this).data('pwd')=='show'){
    			$('.fa-eye[data-frame="'+$(this).data('frame')+'"]').css('cssText', 'display:block!important');
    			$(this).css('display','none');
    			$('#'+$(this).data('frame')).attr('type','text');
    		}else if($(this).data('pwd')=='hide'){
    			$('.fa-eye-slash[data-frame="'+$(this).data('frame')+'"]').css('cssText', 'display:block!important');
    			$(this).css('display','none');
    			$('#'+$(this).data('frame')).attr('type','password');
    		}
     	});
     	
     	$('a[data-reveal-id="modifyPWD"]').on('click',function(){
     	    $('#N_PASSWORD').val('');
     	    $('#O_PASSWORD').val('');
     	});
     	
     	$('#confirm').on('click',function(){
     	    newPwd=$('#N_PASSWORD').val();
     	    oldPwd=$('#O_PASSWORD').val();
     	    if(newPwd==oldPwd){
     	      alert('新密码不能与原密码相同,请重新输入!');
     	      return;
     	    }  
     	    param = $('#updateData').serialize();
     	    $.ajax({
				type:'post', 
				data:param,
				url:'user/updatePassword.do',
				dataType:'json',
				success: function (data) {
				    if(data.retcode=="0"){
				    	alert("密码修改成功!");
				    	$('#modifyPWD a[data-type="close"]').click();
				    }else{
				    	alert(data.errbuf);
				    }						  
				},
				error: function () {
				    alert("获取数据失败");
				}	
            });              
     	});
     	
        $('#changeIMG').on('click',function(){
    		$('.cropped_old img').removeAttr('src');
    		$('.imageBox').css('background-image','');
    		$('.cropped_new img').attr('src','/image/user/system_blank.png');
    		imgurl=$('#user-img-sm').attr('src');
    		$('#headimg_uid').val($('#USER_ID').val());
    		$('#headimg_uname').val($('#USER_NAME').val());
    		$('.cropped_old img').attr('src',imgurl+'?temp=' + Math.random()); 
    	});    		 
    
        jQuery.json={
            getUnreadMail:function(data){
                $('#unread_menu').html('');
            	record=data.rows.length;
				if(record>3){
				    for(i=0;i<3;i++){
				        newmail='<li><a href="javascript:void(0)"><div class="pull-left"><img src="/image/login/mail.png" class="img-circle" alt="User Image"></div><h4>'
                		+(data.rows[i].MAIL_TITLE).substring(0, 15)+'...<small><i class="fa fa-clock-o"></i>'
                		+data.rows[i].SEND_DATE+'</small></h4><p>From:<span>'
                		+data.rows[i].SEND_USER_DESC+'</span></p></a></li>';
				        $('#unread_menu').append(newmail);    	
				    }	
				}else{
				    for(i=0;i<record;i++){
				        newmail='<li><a href="javascript:void(0)"><div class="pull-left"><img src="/image/login/mail.png" class="img-circle" alt="User Image"></div><h4>'
                		+(data.rows[i].MAIL_TITLE).substring(0, 15)+'...</h4><p>From:<span>'
                		+data.rows[i].SEND_USER_DESC+'</span></p><p><small><i class="fa fa-clock-o"></i>'
                		+data.rows[i].SEND_DATE+'</small></p></a></li>';
				        $('#unread_menu').append(newmail); 		
				    }
				}
            	
            }
        }
        $().iframeListener();
        
        $('#mailrefresh').click();
    
    	width=$(window).width();
        height=$(window).height();
        if(width>=768){
            headerheight=$('section.content-header').innerHeight();
            ifmheight=height-headerheight-55;
            $('#mainframe').css('height',ifmheight+'px');
        }else{
            logoheight=$('a.logo').height();
            headerheight=$('section.content-header').innerHeight();
            ifmheight=height-logoheight-headerheight-55;
            $('#mainframe').css('height',ifmheight+'px');
        }
    	
    	var isResizing = false;
    	window.onresize=function(){ 
    		if (!isResizing) { 
    			console.log('do resizing now!'); 
    			width=$(window).width();
            	height=$(window).height();
            	if(width>=768){
            		headerheight=$('section.content-header').innerHeight();
            		ifmheight=height-headerheight-55;

            		$('#mainframe').css('height',ifmheight+'px');
            	}else{
            		console.log('debug2');
                	logoheight=$('a.logo').height();
            		headerheight=$('section.content-header').innerHeight();
            		ifmheight=height-logoheight-headerheight-55;
            		$('#mainframe').css('height',ifmheight+'px');
           		}
    			setTimeout(function () { 
    				isResizing = false; 
    			}, 1000); 
    		} 
    		isResizing = true; 
    	}; 
	</script>
  </body>
</html>
