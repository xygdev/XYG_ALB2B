<%@ page contentType="text/html;charset=utf-8" language="java" pageEncoding="utf-8"
	      import="java.util.*" %>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML>
<html>
  <head>
    <title>收件箱</title>
    <base href="<%=basePath%>"> 
    <meta http-equiv="content-type" content="text/html;charset=gb2312">
	<link rel="stylesheet" href="plugin/css/font-awesome.min.css">
	<link rel="stylesheet" href="plugin/css/jquery-ui.min.css">
	<link rel="stylesheet" type="text/css" href="plugin/css/public.css">
	<link rel="stylesheet" type="text/css" href="plugin/css/mail/receive.css">
	<script type="text/javascript" src="plugin/jQuery/jQuery-2.1.4.min.js"></script>
	<script src="plugin/jQuery/jquery-ui.min.js"></script>	
	<link rel="stylesheet" href="plugin/css/jquery.datetimepicker.css">
	<script src="plugin/jQuery/jquery.datetimepicker.full.js"></script>	
	<script src="plugin/js/xygdev.commons.js"></script>	
  </head> 
  <body>
    <div id="container">
      <!-- 数据加载动画 start -->
  	  <div class="ajax_loading">
        <i class="fa fa-spinner fa-pulse fa-4x" style="color:white"></i>
      </div>
      <!-- 数据加载动画 end -->
    
      <!-- 主表格区域 start -->
      <div class="table">
        <table id="tb" data-table="Mail">
          <tr>
            <th class="READ_FLAG" data-column="db">邮件状态</th>
            <th class="MAIL_TITLE" data-column="db">邮件标题</th>
     	    <th class="SEND_USER_NAME" data-column="db">发件账号</th>
     	    <th class="SEND_USER_DESC" data-column="db">发件人</th>
     	    <th class="SEND_DATE" data-column="db">发件时间</th>
     	    <th class="READ_DATE" data-column="db">阅件时间</th>
     	    <th class="ACTION" data-column="normal">操作</th> 
     	    <th class="SEND_ID" style="display:none" data-column="hidden">&nbsp;</th>
     	    <th class="RECEIVE_ID" style="display:none" data-column="hidden">&nbsp;</th>
     	  </tr>
     	  <tr>
     	    <td class="READ_FLAG" data-column="db"></td>
     	    <td class="MAIL_TITLE" data-column="db"></td>
     	    <td class="SEND_USER_NAME" data-column="db"></td>
     	    <td class="SEND_USER_DESC" data-column="db"></td>
     	    <td class="SEND_DATE" data-column="db"></td>
     	    <td class="READ_DATE" data-column="db"></td>
     	    <td class="ACTION" data-column="normal">
     	      <i class="fa fa fa-eye view pointer" title="邮件详情" data-reveal-id="detail" data-dismissmodalclass="close-detail-frame" data-crudtype="pre-update" data-preupdateurl="mail/getRecMailDetail.do" data-type="detail" data-updateparam=["SEND_ID",".SEND_ID"] data-mailtype="detail" data-btn="#mailrefresh" data-mailurl="mail/updateRecMail.do" data-mailparam=[".READ_DATE","RECEIVE_ID",".RECEIVE_ID"]></i>
     	    </td>
     	    <td class="SEND_ID" style="display:none" data-column="hidden">&nbsp;</td>
     	    <td class="RECEIVE_ID" style="display:none" data-column="hidden">&nbsp;</td>
     	  </tr>
        </table>
      </div>
      <!-- 主表格区域 end -->
   
      <!-- 主表格按钮区域 start -->
      <div class="table_button" id="table" data-table="Mail">
        <div class="setting">
          <i class="fa fa-cog pointer" title="表格设置" data-reveal-id="setting" data-dismissmodalclass="close-setting"></i>
        </div>
        <div class="setting">
          <i class="fa fa-search pointer" title="条件查询" data-reveal-id="query" data-dismissmodalclass="close-query-frame"></i>
        </div>
        <div class="setting">
          <i id='refresh' class="fa fa-refresh pointer" title="刷新数据" data-pagetype='refresh' data-pageframe="table"></i>
        </div>
        <div id="setting">
          <!-- 设置菜单区域 start -->
          <jsp:include page="../public/setting.jsp" >
			<jsp:param name="rdtable" value="#tb" />
			<jsp:param name="odtable" value="#tb" />
			<jsp:param name="pageframe" value="table" />
		  </jsp:include>
          <!-- 设置菜单区域 end -->    
        </div>
        <div>
          <!-- 分页按钮区域 start -->
          <jsp:include page="../public/pageArrow.jsp" >
			<jsp:param name="pageframe" value="table" />
		  </jsp:include>
          <!-- 分页按钮区域 end -->
          <input type="hidden" data-type="size" id="page_size" value="10"/>
          <input type="hidden" data-type="number" id="page_no" value="1"/>
          <input type="hidden" data-type="orderby" id="ORDER_BY" value="SEND_DATE DESC"/> 
          <input type="hidden" data-type="cond"/>
          <input type="hidden" data-type="autoquery" value="N"/>
          <input type="hidden" data-type="url" value="mail/getRecMail.do"/>
          <input type="hidden" data-type="jsontype" value="table"/> 
        </div>
      </div>
      <!-- 主表格按钮区域 end --> 
   
      <!-- 定义列区域 start --> 
      <jsp:include page="../public/rowdefine.jsp"></jsp:include>
      <!-- 定义列区域 end -->
    
      <!-- 多维排序区域 start -->
      <jsp:include page="../public/orderby.jsp"></jsp:include>
      <!-- 多维排序区域 end -->
   
      <!-- 个人配置区域 start -->
      <jsp:include page="../public/config.jsp"></jsp:include>
      <!-- 个人配置区域 end -->
     
      <!-- lov区域 start -->
      <jsp:include page="../public/lov.jsp"></jsp:include>
      <!-- lov区域 end -->
     
      <!-- 邮件详情区域 start -->
      <div id="detail" class="detail_frame">
        <div class='title pointer'>      
          <span data-type="detail"><i class="fa fa-envelope"></i>&nbsp;邮件详情</span>
        </div>
        <a class="close-detail-frame" data-type="close">&#215;</a>
        <div class='line'></div>
        <div class='content'>
          <div class="left short"><span>发件账号:</span><span id="SEND_USER_NAME"></span></div>
          <div class="left short"><span>发件人:</span><span id="SEND_USER_DESC"></span></div>
          <div class="left long"><span>发件时间:</span><span id="SEND_DATE"></span></div>
          <br style="clear:both"/>
          <label class="left mail_title">标题:</label>
          <input type="text" id="MAIL_TITLE" class="left mail_title" readonly="readonly"/>
          <br style="clear:both"/>
          <div id="MAIL_CONTENT" name="MAIL_CONTENT" class="textarea"></div> 
        </div>
      </div>
      <!-- 邮件详情区域 end -->
     
      <!-- 条件查询区域 start -->
      <div id='query' class='query_frame'>     
        <div class='title pointer'>      
          <span><i class="fa fa-envelope"></i>&nbsp;邮件查询</span>
        </div>
        <a class="close-query-frame" data-type="close">&#215;</a>
        <div class='line'></div>
        <div class='content'>
          <form>
            <label for='USER_NAME_Q' class='left mid'>发件账号:</label> 
            <input type='text' id='USER_NAME_Q' name='USER_NAME' class='left mid' data-modify='true' data-pageframe="query" data-validurl='lov/validUserName.do' data-queryurl='lov/getUserId.do' data-lovbtn='USER_LOV_Q' data-hiddenid=["USER_ID_Q","DESCRIPTION_Q"] data-hiddenval=["USER_ID","DESCRIPTION"] data-param="username" />          
            <input type='hidden' id='USER_ID_Q' name='SEND_ID'/>
            <input type='button' id="USER_LOV_Q" class='left button pointer' data-pageframe="lov" data-reveal-id="lov" data-bg="lov-modal-bg" data-dismissmodalclass='close-lov' data-lovname="发件人查询" data-queryurl="lov/getUserPage.do" data-jsontype="user" data-defaultquery="true" data-th=["用户id","发件账号","发件人"] data-td=["USER_ID","USER_NAME","DESCRIPTION"] data-selectname=["发件账号","发件人"] data-selectvalue=["USER_NAME","DESCRIPTION"] data-choose=[".USER_ID",".USER_NAME",".DESCRIPTION"] data-recid=["#USER_ID_Q","#USER_NAME_Q","#DESCRIPTION_Q"] value="···"/>
            <label for='DESCRIPTION_Q' class='left mid'>发件人:</label> 
            <input type='text' id='DESCRIPTION_Q' name="DESCRIPTION" class="long" readonly="readonly"/>
            <br style="clear:both"/>
            <label for="MAIL_TITLE_Q" class="left mid">邮件标题:</label>
            <input type="text" id="MAIL_TITLE_Q" name="MAIL_TITLE" class="left long_x2"/>
            <br style="clear:both"/>
            <label for='SEND_DATE_F' class='left mid'>发件时间:</label> 
            <input type="text" id="SEND_DATE_F" name="SEND_DATE_F" class="left long_x1" data-datatype="date" placeholder="起始发件时间"/>
            <input type="text" id="SEND_DATE_T" name="SEND_DATE_T" class="left long_x1" data-datatype="date" placeholder="截止发件时间"/>
            <br style="clear:both"/>
            <label for='READ_DATE_F' class='left mid'>阅件时间:</label> 
            <input type="text" id="READ_DATE_F" name="READ_DATE_F" class="left long_x1" data-datatype="date" placeholder="起始阅件时间"/>
            <input type="text" id="READ_DATE_T" name="READ_DATE_T" class="left long_x1" data-datatype="date" placeholder="截止阅件时间"/>
          </form>
        </div>
        <div class='foot'>             
          <button class="right pointer"  data-buttonframe="table" data-crudtype="query" data-pageframe="query">邮件查询</button>
        </div> 
      </div>
      <!-- 条件查询区域 end -->
      
      <!-- 用户信息存放区域 start -->
      <input type="hidden" id="USER_ID" value="${USER_ID}"/>  
      <input type="hidden" id="INTERACT_CODE" value="RECEIVE_MAIL"/> 
      <input type="hidden" id="HEADER_ID" value=""/> 
      <!-- 用户信息存放区域 end -->  
    </div>
     
    <script>       
        $(function() {
            //设置拖拽
    		$("#detail").draggable({ handle: ".title" });
    		$("#query").draggable({ handle: ".title" });  
    		//初始化CRUD和LOV条件查询
    		$().crudListener();	
    		$().revealListener(); 
    		
    		$.fn.apResult = function(){
        	    $('#output').off('click');
        	    $('#output').on('click',function(e){
        	        e.preventDefault();/****阻止<a>标签默认的点击事件（超链接跳转）****/
        	        href = $(this).attr('href');
        	        //alert(href);
        	        layer.open({
                        type: 2,
                        title: '对账单详情',
                        shadeClose: true,
                        maxmin: true, //开启最大化最小化按钮
                        area: ['893px', '600px'],
                        anim: 2,
                        content: [href, 'yes'] //iframe的url，no代表不显示滚动条
                    });
        	    });
        	}	 
    		//日期选择2016.12.8 by sam.t
    		$('input[data-datatype="date"]').datetimepicker({
				  lang:"ch",           //语言选择中文
				  timepicker:true,    //启用时间选项
				  format:"Y-m-d H:i:s",      //格式化日期
				  step: 30,
				  showOnClick: true
			});
        });
        
        jQuery.json={
        	getContent:function(data,JSONtype){  
        	    if(JSONtype=='table'){
        	        for(i=0;i<(pageMaxRow-pageMinRow+1);i++){
        	            if(data.rows[i].READ_FLAG=='N'){
        	                $('.READ_FLAG',$('#tb tr:eq('+(i+1)+')')).html('<i class="fa fa-envelope-o"></i>');
        	            }else{
        	                $('.READ_FLAG',$('#tb tr:eq('+(i+1)+')')).html('<i class="fa fa-envelope-open"></i>');
        	            }
                    	$('.MAIL_TITLE',$('#tb tr:eq('+(i+1)+')')).html(data.rows[i].MAIL_TITLE); 
                   	 	$('.SEND_USER_NAME',$('#tb tr:eq('+(i+1)+')')).html(data.rows[i].SEND_USER_NAME); 
                    	$('.SEND_USER_DESC',$('#tb tr:eq('+(i+1)+')')).html(data.rows[i].SEND_USER_DESC); 
                    	$('.SEND_DATE',$('#tb tr:eq('+(i+1)+')')).html(data.rows[i].SEND_DATE);   
                    	$('.READ_DATE',$('#tb tr:eq('+(i+1)+')')).html(data.rows[i].READ_DATE); 
                    	$('.SEND_ID',$('#tb tr:eq('+(i+1)+')')).html(data.rows[i].SEND_ID); 
                    	$('.RECEIVE_ID',$('#tb tr:eq('+(i+1)+')')).html(data.rows[i].RECEIVE_ID); 
                	}
                	$().crudListener();
                	$().mailListener();
                	$().revealListener();  
        	    }else if(JSONtype=='user'){
        	        if(pageMaxRow==0&&pageMinRow==0){
        	            console.log('no data');
        	        }else{
        	            for(i=0;i<(pageMaxRow-pageMinRow+1);i++){
       	            	    $('td:eq(0)',$('.contentbox tr:eq('+(i+1)+')')).html(data.rows[i].USER_ID);       	                    
       	            	    $('td:eq(1)',$('.contentbox tr:eq('+(i+1)+')')).html(data.rows[i].USER_NAME);
       	            	    $('td:eq(2)',$('.contentbox tr:eq('+(i+1)+')')).html(data.rows[i].DESCRIPTION);       	               	        
       	            	} 
        	        }       	            	    
        	    }   	            	   	
       	    },	
       	    getUpdateJSON:function(data){   
       	        $('#SEND_USER_NAME').text(data.rows[0].SEND_USER_NAME);
       	        $('#SEND_USER_DESC').text(data.rows[0].SEND_USER_DESC);
       	        $('#SEND_DATE').text(data.rows[0].SEND_DATE);
       	        $('#MAIL_TITLE').val(data.rows[0].MAIL_TITLE);
       	        $('#MAIL_CONTENT').html(data.rows[0].MAIL_CONTENT);
       	        $().apResult();
       	    },
       	    getMSG:function(data){
       	        pageMinRow=parseInt(data.pageMinRow);
        	    pageMaxRow=parseInt(data.pageMaxRow);
        	    firstPageFlag=data.firstPageFlag;
        	    lastPageFlag=data.lastPageFlag;
        	   	totalPages=data.totalPages;
       	    }
       	}
    </script>
    <script type="text/javascript" src="plugin/layer/layer.js"></script>
    <script type="text/javascript" src="plugin/js/jQuery.reveal.js"></script> 
    <script type="text/javascript" src="plugin/js/jQuery.page.js"></script>
    <script type="text/javascript" src="plugin/js/jQuery.lov.js"></script> 
    <script type="text/javascript" src="plugin/js/jQuery.crud.js"></script> 
    <script type="text/javascript" src="plugin/js/jQuery.mail.js"></script> 
    <script type="text/javascript" src="plugin/js/jQuery.rowdefine.js"></script>
    <script type="text/javascript" src="plugin/js/jQuery.irr.orderby.js"></script>	
    <script type="text/javascript" src="plugin/js/jQuery.irr.init.js"></script>	    
  </body>
</html>