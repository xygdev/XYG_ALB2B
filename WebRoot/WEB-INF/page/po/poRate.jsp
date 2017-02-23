<%@ page contentType="text/html;charset=utf-8" language="java" pageEncoding="utf-8"
	      import="java.util.*" %>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML>
<html>
  <head>
    <title>PO订单进度查询</title>
    <base href="<%=basePath%>"> 
    <meta http-equiv="content-type" content="text/html;charset=gb2312">
	<link rel="stylesheet" href="plugin/css/font-awesome.min.css">
	<link rel="stylesheet" href="plugin/css/jquery-ui.min.css">
	<link rel="stylesheet" type="text/css" href="plugin/css/public.css">
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
        <table id="tb" data-table="PoRate">
          <tr>
            <th class="PO_NUMBER" data-column="db">PO单号</th>
            <th class="PARTY_NAME" data-column="db">客户名称</th>
     	    <th class="ACCOUNT_NUMBER" data-column="db">客户账号</th> 
     	    <th class="COATING_TYPE" data-column="db">膜系</th>
     	    <th class="THICKNESS" data-column="db">厚度</th>
     	    <th class="WIDTH" data-column="db">宽度</th>
     	    <th class="HEIGHT" data-column="db">高度</th>
     	    <th class="APPROVAL_DATE" data-column="db">终审时间</th>     	    
     	    <th class="PIE_QUANTITY" data-column="db">需求量</th>
     	    <th class="ORDER_QUANTITY" data-column="db">下单量</th>
     	    <th class="BAK_QUANTITY" data-column="db">备货量</th>
     	    <th class="SHIPPED_QUANTITY" data-column="db">发货量</th>
     	    <th class="PO_LINE_ID" style="display:none" data-column="hidden">&nbsp;</th>
     	  </tr>
     	  <tr>
     	    <td class="PO_NUMBER" data-column="db"></td>
            <td class="PARTY_NAME" data-column="db"></td>
     	    <td class="ACCOUNT_NUMBER" data-column="db"></td> 
     	    <td class="COATING_TYPE" data-column="db"></td>
     	    <td class="THICKNESS" data-column="db"></td>
     	    <td class="WIDTH" data-column="db"></td>
     	    <td class="HEIGHT" data-column="db"></td>
     	    <td class="APPROVAL_DATE" data-column="db"></td>     	    
     	    <td class="PIE_QUANTITY" data-column="db"></td>
     	    <td class="ORDER_QUANTITY" data-column="db"></td>
     	    <td class="BAK_QUANTITY" data-column="db"></td>
     	    <td class="SHIPPED_QUANTITY" data-column="db"></td>
     	    <td class="PO_LINE_ID" style="display:none" data-column="hidden">&nbsp;</td>
     	  </tr>
        </table>
      </div>
      <!-- 主表格区域 end -->
   
      <!-- 主表格按钮区域 start -->
      <div class="table_button" id="table" data-table="PoRate">
        <div class="setting">
          <i class="fa fa-cog pointer" data-reveal-id="setting" data-dismissmodalclass="close-setting"></i>
        </div>
        <div class="setting">
          <i class="fa fa-search pointer" data-reveal-id="query" data-key="true" data-dismissmodalclass="close-query-frame"></i>
        </div>
        <div class="setting">
          <i id='refresh' class="fa fa-refresh pointer" data-pagetype='refresh' data-pageframe="table"></i>
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
          <input type="hidden" data-type="orderby" id="ORDER_BY" value="APPROVAL_DATE ASC"/> 
          <input type="hidden" data-type="cond"/>
          <input type="hidden" data-type="autoquery" value="N"/>
          <input type="hidden" data-type="url" value="po/getPoRate.do"/>
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
      <div id='query' class="pop_frame row-4">     
        <div class='title pointer'>      
          <span><i class="fa fa-cubes"></i>&nbsp;查询条件</span>
        </div>
        <a class="close-query-frame" data-type="close">&#215;</a>
        <div class='line'></div>
        <div class="content row-4">
          <form>
            <label for='PARTY_NAME' class='left md'>客户名称:</label> 
            <input type='text' id='PARTY_NAME' name="PARTY_NAME" class="left md" readonly="readonly"/>          
            <input type='hidden' id='CUSTOMER_ID' name='CUSTOMER_ID'/>
            <input type='hidden' id='ORG_ID' name='ORG_ID'/>
            <input type='button' id="CUSTOMER_LOV" class='left button pointer' data-pageframe="lov" data-reveal-id="lov" data-key="true" data-callback="query" data-bg="lov-modal-bg" data-dismissmodalclass='close-lov' data-lovname="客户查询" data-queryurl="lov/getUserCustPage.do" data-jsontype="cust" data-defaultquery="true" data-th=["客户id","客户名称","客户帐号","公司id","销售公司"] data-td=["CUSTOMER_ID&none","PARTY_NAME","ACCOUNT_NUMBER","ORG_ID&none","ORG_NAME"] data-selectname=["客户名称","客户账号"] data-selectvalue=["PARTY_NAME","ACCOUNT_NUMBER"] data-choose=[".CUSTOMER_ID",".PARTY_NAME",".ACCOUNT_NUMBER",".ORG_ID"] data-recid=["#CUSTOMER_ID","#PARTY_NAME","#ACCOUNT_NUMBER","#ORG_ID"] value="···"/>
            <label for='ACCOUNT_NUMBER' class='left md'>客户帐号:</label>
            <input type='text' id='ACCOUNT_NUMBER' name='ACCOUNT_NUMBER' class='left lg' readonly="readonly"/>
            <br style="clear:both"/>            
            <label for='COATING_NAME' class='left md'>膜系:</label> 
            <input type='text' id='COATING_NAME' name='COATING_NAME' class='left md' data-modify="true" data-pageframe="query" data-lovbtn="COATING_LOV" data-param="LOOKUP_CODE"/>          
            <input type='hidden' id='COATING_CODE' name='COATING_TYPE'/>
            <input type='button' id="COATING_LOV" class='left button pointer' data-pageframe="lov" data-reveal-id="lov" data-key="true" data-callback="query" data-bg="lov-modal-bg" data-dismissmodalclass='close-lov' data-lovname="膜系查询" data-queryurl="lov/getCoatingPage.do" data-jsontype="coating" data-defaultquery="true" data-th=["膜系编码","膜系名称","描述"] data-td=["LOOKUP_CODE","MEANING","DESCRIPTION"] data-selectname=["膜系编码","膜系名称"] data-selectvalue=["LOOKUP_CODE","MEANING"] data-choose=[".LOOKUP_CODE",".MEANING"] data-recid=["#COATING_NAME","#COATING_CODE"] value="···"/>    
            <label for='THICKNESS' class='left md'>厚度:</label> 
            <input type='text' id='THICKNESS' name='THICKNESS' class='left lg'/>
            <label for='WIDTH' class='left md'>宽度:</label> 
            <input type='text' id='WIDTH' name='WIDTH' class='left lg'/>
            <label for='HEIGHT' class='left md'>高度:</label> 
            <input type='text' id='HEIGHT' name='HEIGHT' class='left lg'/>  
            <br style="clear:both"/>
            <label for="APPROVAL_DATE_F" class="left md">终审时间:</label>
            <input type="text" id="APPROVAL_DATE_F" name="APPROVAL_DATE_F" class="left lg" data-datatype="date" placeholder="起始终审时间"/>
            <label class="left blank"></label>
            <input type="text" id="APPROVAL_DATE_T" name="APPROVAL_DATE_T" class="left lg" data-datatype="date" placeholder="截止终审时间"/>         
          </form>
        </div>
        <div class='foot'>             
          <button class="right pointer"  data-buttonframe="table" data-keyup="enter" data-crudtype="query" data-pageframe="query" data-func="$().beforeQuery();">查询</button>
        </div> 
      </div>
      <!-- 条件查询区域 end -->    
      
      <!-- 用户信息存放区域 start -->
      <input type="hidden" id="USER_ID" value="${USER_ID}"/>  
      <input type="hidden" id="INTERACT_CODE" value="PO_RATE"/> 
      <input type="hidden" id="HEADER_ID" value=""/> 
      <!-- 用户信息存放区域 end -->  
    </div>   
    <script>       
        $(function() {
            //设置拖拽
    		$("#query").draggable({ handle: ".title" });
    		
    		//日期选择 2016.1.18 by bird
    		$('input[data-datatype="date"]').datetimepicker({
				  lang:"ch",           //语言选择中文
				  timepicker:true,    //启用时间选项
				  format:"Y-m-d H:i:s",      //格式化日期
				  step: 30,
				  showOnClick: true
			});
    		
    		$().crudListener();	
    		
    		$.fn.beforeQuery = function(){
    		    RegExpValidate('^[0-9]+(.[0-9]{1})?$','THICKNESS','regExpError("厚度请输入小数点后至多一位的正实数！");');
    		    RegExpValidate('^\\+?[1-9][0-9]*$','WIDTH','regExpError("宽度请输入非零正整数!");');
    		    RegExpValidate('^\\+?[1-9][0-9]*$','HEIGHT','regExpError("高度请输入非零正整数!");');
    		}
    				
        });
        
        jQuery.json={
        	getContent:function(data,JSONtype){  
        	    if(JSONtype=='table'){
        	        for(i=0;i<(pageMaxRow-pageMinRow+1);i++){
                    	$('.PO_NUMBER',$('#tb tr:eq('+(i+1)+')')).html(data.rows[i].PO_NUMBER); 
                   	 	$('.PARTY_NAME',$('#tb tr:eq('+(i+1)+')')).html(data.rows[i].PARTY_NAME); 
                    	$('.ACCOUNT_NUMBER',$('#tb tr:eq('+(i+1)+')')).html(data.rows[i].ACCOUNT_NUMBER);
                    	$('.COATING_TYPE',$('#tb tr:eq('+(i+1)+')')).html(data.rows[i].COATING_TYPE);   
                    	$('.THICKNESS',$('#tb tr:eq('+(i+1)+')')).html(data.rows[i].THICKNESS);   
                    	$('.WIDTH',$('#tb tr:eq('+(i+1)+')')).html(data.rows[i].WIDTH); 
                    	$('.HEIGHT',$('#tb tr:eq('+(i+1)+')')).html(data.rows[i].HEIGHT); 
                    	$('.APPROVAL_DATE',$('#tb tr:eq('+(i+1)+')')).html(data.rows[i].APPROVAL_DATE); 
                    	$('.PIE_QUANTITY',$('#tb tr:eq('+(i+1)+')')).html(data.rows[i].PIE_QUANTITY); 
                    	$('.ORDER_QUANTITY',$('#tb tr:eq('+(i+1)+')')).html(data.rows[i].ORDER_QUANTITY); 
                    	$('.BAK_QUANTITY',$('#tb tr:eq('+(i+1)+')')).html(data.rows[i].BAK_QUANTITY); 
                    	$('.SHIPPED_QUANTITY',$('#tb tr:eq('+(i+1)+')')).html(data.rows[i].SHIPPED_QUANTITY); 
                    	$('.PO_LINE_ID',$('#tb tr:eq('+(i+1)+')')).html(data.rows[i].PO_LINE_ID); 
                	}
                	$().crudListener();
                	$().revealListener(); 
        	    }else if(JSONtype=='coating'){
        	        if(pageMaxRow==0&&pageMinRow==0){
        	            console.log('no data');
        	        }else{
        	            for(i=0;i<(pageMaxRow-pageMinRow+1);i++){
       	            	    $('td:eq(0)',$('.contentbox tr:eq('+(i+1)+')')).html(data.rows[i].LOOKUP_CODE);  
       	            	    $('td:eq(1)',$('.contentbox tr:eq('+(i+1)+')')).html(data.rows[i].MEANING);     	                    
       	            	    $('td:eq(2)',$('.contentbox tr:eq('+(i+1)+')')).html(data.rows[i].DESCRIPTION);    	               	        
       	            	} 
        	        }       	            	    
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
    <script type="text/javascript" src="plugin/js/data.validate.js"></script>
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