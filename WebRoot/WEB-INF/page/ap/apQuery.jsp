<%@ page contentType="text/html;charset=utf-8" language="java" pageEncoding="utf-8"
	      import="java.util.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <title>对账单查询</title>
    <base href="<%=basePath%>"> 
    <meta http-equiv="content-type" content="text/html;charset=gb2312">
	<link rel="stylesheet" href="plugin/css/font-awesome.min.css">
	<link rel="stylesheet" href="plugin/css/jquery-ui.min.css">
	<link rel="stylesheet" type="text/css" href="plugin/css/public.css">
	<link rel="stylesheet" href="plugin/css/cutpic.css" type="text/css" />
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
        <table id="main-table" data-table="Ap">
          <tr>
            <th class="REQUEST_ID" data-column="db">请求ID</th>            
     	    <th class="USER_NAME" data-column="db">请求账号</th>
     	    <th class="USER_DESC" data-column="db">请求人</th>
     	    <th class="REQUEST_DATE" data-column="db">提交时间</th>
     	    <th class="ACTUAL_START_DATE" data-column="db">开始时间</th>
     	    <th class="ACTUAL_COMPLETION_DATE" data-column="db">完成时间</th>
     	    <th class="COMPLETION_TEXT" data-column="db">请求结果</th>
     	    <th class="ACTION" data-column="normal">查看结果</th> 
     	    <th class="REQUEST_LOG_ID" style="display:none" data-column="hidden">&nbsp;</th>
     	    <th class="STATUS_CODE" style="display:none" data-column="hidden">&nbsp;</th>
     	  </tr>
     	  <tr>
     	    <td class="REQUEST_ID" data-column="db"></td>            
     	    <td class="USER_NAME" data-column="db"></td>
     	    <td class="USER_DESC" data-column="db"></td>
     	    <td class="REQUEST_DATE" data-column="db"></td>
     	    <td class="ACTUAL_START_DATE" data-column="db"></td>
     	    <td class="ACTUAL_COMPLETION_DATE" data-column="db"></td>
     	    <td class="COMPLETION_TEXT" data-column="db"></td>
     	    <td class="ACTION" data-column="normal">
     	      <i class="fa fa-eye fa-fw pointer hidden show_output" title="点击查看结果详情" data-show="true" data-reveal-id="ui" data-dismissmodalclass="close-ui-frame"></i>
     	    </td>
     	    <td class="REQUEST_LOG_ID" style="display:none" data-column="hidden">&nbsp;</td>
     	    <td class="STATUS_CODE" style="display:none" data-column="hidden">&nbsp;</td>
     	  </tr>
        </table>
      </div>
      <!-- 主表格区域 end -->
      
      <!-- 主表格按钮区域 start -->
      <div class="table_button" id="table" data-table="Ap">
        <div class="setting">
          <i class="fa fa-cog pointer" title="表格设置" data-reveal-id="setting" data-dismissmodalclass="close-setting"></i>
        </div>
        <div class="setting">
          <i class="fa fa-file-pdf-o pointer" title="对账单请求" data-reveal-id="query" data-key="true" data-dismissmodalclass="close-query-frame"></i>
        </div>
        <div class="setting">
          <i id='refresh' class="fa fa-refresh pointer" title="刷新数据" data-pagetype='refresh' data-pageframe="table"></i>
        </div>
        <div id="setting">
          <!-- 设置菜单区域 start -->
          <jsp:include page="../public/setting.jsp" >
			<jsp:param name="rdtable" value="#main-table" />
			<jsp:param name="odtable" value="#main-table" />
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
          <input type="hidden" data-type="orderby" id="ORDER_BY" value="REQUEST_LOG_ID DESC"/> 
          <input type="hidden" data-type="cond"/>
          <input type="hidden" data-type="url" value="ap/getApHistory.do"/>
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
      
      <!-- 条件查询区域 start -->
      <div id='query' class="pop_frame row-2">     
        <div class='title'>      
          <span><i class="fa fa-file-pdf-o"></i>&nbsp;对账单</span>
        </div>
        <a class="close-query-frame" data-type="close">&#215;</a>
        <div class='line'></div>
        <div class="content row-2">
          <form>
            <label for='PARTY_NAME' class='left md'>客户名称</label> 
            <input type='text' id='PARTY_NAME' name='PARTY_NAME' class='left md' required="required" readonly="readonly"/>          
            <input type='hidden' id='CUSTOMER_ID' name='CUSTOMER_ID'/>
            <input type='hidden' id='ORG_ID' name='ORG_ID'/>
            <input type='button' id="CUSTOMER_LOV" class='left button pointer' data-pageframe="lov" data-reveal-id="lov" data-key="true" data-callback="query" data-bg="lov-modal-bg" data-dismissmodalclass='close-lov' data-lovname="客户查询" data-queryurl="lov/getUserCustPage.do" data-jsontype="cust" data-defaultquery="true" data-th=["客户id","客户名称","客户帐号","公司id","销售公司"] data-td=["CUSTOMER_ID&none","PARTY_NAME","ACCOUNT_NUMBER","ORG_ID&none","ORG_NAME"] data-selectname=["客户名称","客户账号"] data-selectvalue=["PARTY_NAME","ACCOUNT_NUMBER"] data-choose=[".CUSTOMER_ID",".PARTY_NAME",".ACCOUNT_NUMBER",".ORG_ID"] data-recid=["#CUSTOMER_ID","#PARTY_NAME","#ACCOUNT_NUMBER","#ORG_ID"] value="···"/>
            <label for='ACCOUNT_NUMBER' class='left md'>客户帐号</label>
            <input type='text' id='ACCOUNT_NUMBER' name='ACCOUNT_NUMBER' required='required' class='left lg' readonly="readonly"/>
            <br style="clear:both"/>
            <label for='AP_DATE' class='left md'>对账单时间</label>
            <input type='text' id='AP_DATE' name='AP_DATE' data-update="db" required='required' data-datatype="date" class='left lg'/>
          </form>
        </div>
        <div class='foot'>             
          <button class="right pointer" id="report" data-keyup="enter">生成报表</button>
        </div> 
      </div>
      <!-- 条件查询区域 end --> 
    
      <!-- 用户信息存放区域 start -->
      <input type="hidden" id="USER_ID" value="${USER_ID}"/>  
      <input type="hidden" id="INTERACT_CODE" value="AP_QUERY"/> 
      <input type="hidden" id="HEADER_ID" value=""/> 
      <!-- 用户信息存放区域 end -->  
    </div>
    
     
      <script>       
        $(function() {
            //设置拖拽
    		$("#query").draggable();
    		
    		//初始化CRUD和LOV条件查询
    		$().crudListener();	
    		$().revealListener(); 		
    		
    		$('input[data-datatype="date"]').datetimepicker({
				  lang:"ch",           //语言选择中文
				  format:"Y-m",      //格式化日期
				  formatDate:"Y-m",
				  timepicker:false,    //启用时间选项
				  showOnClick: true,
				  closeOnDateSelect:true
			});

    		//报表提交
        	$('#report').on('click',function(){	 
        		param = $('#query form').serialize();
        		var validate_flag=$().validateRequired('query');
        		if(validate_flag==true){
        		    $.ajax({
				    	type:'post', 
				    	data:param,
				    	url:'ap/reqApQuery.do',
				    	success: function () {
				        	$('#query a[data-type="close"]').click();
				        	layer.msg("报表请求已提交，报表生成后将以邮件的形式发送到收件箱，请注意查收");        
				    	},error: function () {
				        	$('#query a[data-type="close"]').click();
				        	layer.alert("报表请求错误");
				    	}
				    });	
        		}
        	    
        	});
        	
        	//重新看报表结果 2016.12.12 new Add by Sam.T
    		$.fn.outputShow = function(){
    		    $('.show_output').off('click');
    		    $('.show_output').on('click',function(){
    		    	var requestId=parseInt($(this).parent().parent().find('.REQUEST_ID').html());
    		        if(requestId&&requestId>0){
	        	        var href = 'ap/getApOutput.do?REQUEST_ID='+requestId;
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
    		        }else{
    		        	layer.alert("报表请求ID不存在！");
    		        	return false;
    		        }
    		    });
    		} 	
        }); 
        
        jQuery.json={
        	getContent:function(data,JSONtype){ 
        	    if(JSONtype=='table'){
        	        for(i=0;i<(pageMaxRow-pageMinRow+1);i++){ 
                    	$('.REQUEST_ID',$('#main-table tr:eq('+(i+1)+')')).html(data.rows[i].REQUEST_ID); 
                   	 	$('.USER_NAME',$('#main-table tr:eq('+(i+1)+')')).html(data.rows[i].USER_NAME); 
                    	$('.USER_DESC',$('#main-table tr:eq('+(i+1)+')')).html(data.rows[i].USER_DESC);
                    	$('.REQUEST_DATE',$('#main-table tr:eq('+(i+1)+')')).html(data.rows[i].REQUEST_DATE); 
                    	$('.ACTUAL_START_DATE',$('#main-table tr:eq('+(i+1)+')')).html(data.rows[i].ACTUAL_START_DATE);   
                    	$('.ACTUAL_COMPLETION_DATE',$('#main-table tr:eq('+(i+1)+')')).html(data.rows[i].ACTUAL_COMPLETION_DATE);   
                    	$('.COMPLETION_TEXT',$('#main-table tr:eq('+(i+1)+')')).html(data.rows[i].COMPLETION_TEXT); 
                    	$('.REQUEST_LOG_ID',$('#main-table tr:eq('+(i+1)+')')).html(data.rows[i].REQUEST_LOG_ID); 
                    	$('.STATUS_CODE',$('#main-table tr:eq('+(i+1)+')')).html(data.rows[i].STATUS_CODE); 
                    	$('.ACTION i',$('#main-table tr:eq('+(i+1)+')')).show();
                    	if(data.rows[i].STATUS_CODE!='C'){
                    	  $('.ACTION i',$('#main-table tr:eq('+(i+1)+')')).hide();
                    	}
                	}
                	$().afterRowDefine();
                	$().crudListener();
                	$().outputShow();
                	$().revealListener(); 
        	    }else if(JSONtype=='cust'){
        	        if(pageMaxRow==0&&pageMinRow==0){
        	            console.log('no data');
        	        }else{
        	            for(i=0;i<(pageMaxRow-pageMinRow+1);i++){
       	            	    $('td:eq(0)',$('.contentbox tr:eq('+(i+1)+')')).html(data.rows[i].CUSTOMER_ID);  
       	            	    $('td:eq(1)',$('.contentbox tr:eq('+(i+1)+')')).html(data.rows[i].PARTY_NAME);     	                    
       	            	    $('td:eq(2)',$('.contentbox tr:eq('+(i+1)+')')).html(data.rows[i].ACCOUNT_NUMBER); 
       	            	    $('td:eq(3)',$('.contentbox tr:eq('+(i+1)+')')).html(data.rows[i].ORG_ID);   	               	        
       	            	    $('td:eq(4)',$('.contentbox tr:eq('+(i+1)+')')).html(data.rows[i].ORG_NAME); 
       	            	} 
        	       	}       	            	    
        	    }
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
    <script type="text/javascript" src="plugin/js/jQuery.rowdefine.js"></script>
    <script type="text/javascript" src="plugin/js/jQuery.irr.orderby.js"></script>	
    <script type="text/javascript" src="plugin/js/jQuery.irr.init.js"></script>	
  </body> 
</html>
