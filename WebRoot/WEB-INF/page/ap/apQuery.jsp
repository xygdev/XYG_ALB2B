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
	<link rel="stylesheet" type="text/css" href="plugin/css/ap/ap.css">
	<link rel="stylesheet" href="plugin/css/cutpic.css" type="text/css" />
	<script type="text/javascript" src="plugin/jQuery/jQuery-2.1.4.min.js"></script>
	<script src="plugin/jQuery/jquery-ui.min.js"></script>	
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
        <table id="tb" data-table="Ap">
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
     	      <i class="fa fa-eye fa-fw pointer hidden" title="点击查看结果详情" data-show="true" data-reveal-id="ui" data-dismissmodalclass="close-ui-frame"></i>
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
          <i class="fa fa-file-pdf-o pointer" title="对账单请求" data-reveal-id="query" data-dismissmodalclass="close-query-frame"></i>
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
    <div id='query' class='query_frame'>     
        <div class='title'>      
          <span><i class="fa fa-file-pdf-o"></i>&nbsp;对账单</span>
        </div>
        <a class="close-query-frame" data-type="close">&#215;</a>
        <div class='line'></div>
        <div class='content'>
          <form>
            <label for='PARTY_NAME' class='left mid'>客户名称</label> 
            <input type='text' id='PARTY_NAME' name='PARTY_NAME' class='left short' required="required" readonly="readonly"/>          
            <input type='hidden' id='CUSTOMER_ID' name='CUSTOMER_ID'/>
            <input type='hidden' id='ORG_ID' name='ORG_ID'/>
            <input type='button' id="CUSTOMER_LOV" class='left button pointer' data-pageframe="lov" data-reveal-id="lov" data-bg="lov-modal-bg" data-dismissmodalclass='close-lov' data-lovname="客户查询" data-queryurl="lov/getUserCustPage.do" data-jsontype="cust" data-defaultquery="true" data-th=["客户id","客户名称","客户帐号","公司id","销售公司"] data-td=["CUSTOMER_ID","PARTY_NAME","ACCOUNT_NUMBER","ORG_ID","ORG_NAME"] data-selectname=["客户名称","客户账号"] data-selectvalue=["PARTY_NAME","ACCOUNT_NUMBER"] data-choose=[".CUSTOMER_ID",".PARTY_NAME",".ACCOUNT_NUMBER",".ORG_ID"] data-recid=["#CUSTOMER_ID","#PARTY_NAME","#ACCOUNT_NUMBER","#ORG_ID"] value="···"/>
            <label for='ACCOUNT_NUMBER' class='left mid'>客户帐号</label>
            <input type='text' id='ACCOUNT_NUMBER' name='ACCOUNT_NUMBER' required='required' class='left long' readonly="readonly"/>
            <br style="clear:both"/>
            <label for='AP_DATE' class='left mid'>对账单时间</label>
            <input type='text' id='AP_DATE' name='AP_DATE' data-update="db" required='required' class='left long'/>
          </form>
        </div>
        <div class='foot'>             
          <button class="right pointer" id="report">生成报表</button>
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

    		//报表提交
        	$('#report').on('click',function(){	 
        		param = $('#query form').serialize();
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
        	});
        	
        	
        }); 
        
        jQuery.json={
        	getContent:function(data,JSONtype){ 
        	    if(JSONtype=='table'){
        	        for(i=0;i<(pageMaxRow-pageMinRow+1);i++){ 
                    	$('.REQUEST_ID',$('#tb tr:eq('+(i+1)+')')).html(data.rows[i].REQUEST_ID); 
                   	 	$('.USER_NAME',$('#tb tr:eq('+(i+1)+')')).html(data.rows[i].USER_NAME); 
                    	$('.USER_DESC',$('#tb tr:eq('+(i+1)+')')).html(data.rows[i].USER_DESC);
                    	$('.REQUEST_DATE',$('#tb tr:eq('+(i+1)+')')).html(data.rows[i].REQUEST_DATE); 
                    	$('.ACTUAL_START_DATE',$('#tb tr:eq('+(i+1)+')')).html(data.rows[i].ACTUAL_START_DATE);   
                    	$('.ACTUAL_COMPLETION_DATE',$('#tb tr:eq('+(i+1)+')')).html(data.rows[i].ACTUAL_COMPLETION_DATE);   
                    	$('.COMPLETION_TEXT',$('#tb tr:eq('+(i+1)+')')).html(data.rows[i].COMPLETION_TEXT); 
                    	$('.REQUEST_LOG_ID',$('#tb tr:eq('+(i+1)+')')).html(data.rows[i].REQUEST_LOG_ID); 
                    	$('.STATUS_CODE',$('#tb tr:eq('+(i+1)+')')).html(data.rows[i].STATUS_CODE); 
                    	$('.ACTION i',$('#tb tr:eq('+(i+1)+')')).show();
                    	if(data.rows[i].STATUS_CODE!='C'){
                    	  $('.ACTION i',$('#tb tr:eq('+(i+1)+')')).hide();
                    	}
                	}
                	$().crudListener();
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