<%@ page contentType="text/html;charset=utf-8" language="java" pageEncoding="utf-8"
	      import="java.util.*" %>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML>
<html>
  <head>
    <title>保留库存查询</title>
    <base href="<%=basePath%>"> 
    <meta http-equiv="content-type" content="text/html;charset=gb2312">
	<link rel="stylesheet" href="plugin/css/font-awesome.min.css">
	<link rel="stylesheet" href="plugin/css/jquery-ui.min.css">
	<link rel="stylesheet" type="text/css" href="plugin/css/public.css">
	<link rel="stylesheet" type="text/css" href="plugin/css/inv/inv.css">
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
        <table id="tb" data-table="ResnInv">
          <tr>
            <th class="PARTY_NAME" data-column="db">客户名称</th>
            <th class="ORGANIZATION_NAME" data-column="db">库存组织</th>
            <th class="ITEM_NUMBER" data-column="db">物料编码</th>
     	    <th class="ITEM_DESC" data-column="db">物料描述</th>
     	    <th class="COATING_TYPE" data-column="db">膜系</th>
     	    <th class="PACK_DESC" data-column="db">包装方式</th>
     	    <th class="SPEC" data-column="db">规格</th>
     	    <th class="BOX_QTY" data-column="db">总箱数</th>
     	    <th class="ORGANIZATION_ID" style="display:none" data-column="hidden">&nbsp;</th>
     	    <th class="INVENTORY_ITEM_ID" style="display:none" data-column="hidden">&nbsp;</th>
     	  </tr>
     	  <tr>
     	    <td class="PARTY_NAME" data-column="db"></td>
     	    <td class="ORGANIZATION_NAME" data-column="db"></td>
            <td class="ITEM_NUMBER" data-column="db"></td>
     	    <td class="ITEM_DESC" data-column="db"></td>
			<td class="COATING_TYPE" data-column="db"></td>
			<td class="PACK_DESC" data-column="db"></td>
     	    <td class="SPEC" data-column="db"></td>
     	    <td class="BOX_QTY" data-column="db"></td>
     	    <td class="ORGANIZATION_ID" style="display:none" data-column="hidden">&nbsp;</td>
     	    <td class="INVENTORY_ITEM_ID" style="display:none" data-column="hidden">&nbsp;</td>
     	  </tr>
        </table>
      </div>
      <!-- 主表格区域 end -->
   
      <!-- 主表格按钮区域 start -->
      <div class="table_button" id="table" data-table="ResnInv">
        <div class="setting">
          <i class="fa fa-cog pointer" data-reveal-id="setting" data-dismissmodalclass="close-setting"></i>
        </div>
        <div class="setting">
          <i class="fa fa-search pointer" data-reveal-id="query" data-key="true" data-dismissmodalclass="close-query-frame"></i>
        </div>
        <div class="setting">
          <i id='refresh' class="fa fa-refresh pointer" data-pagetype='refresh' data-pageframe="table" data-func="$().validateOrgan()"></i>
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
          <input type="hidden" data-type="orderby" id="ORDER_BY" value="INVENTORY_ITEM_ID DESC"/> 
          <input type="hidden" data-type="cond"/>
          <input type="hidden" data-type="autoquery" value="N"/>
          <input type="hidden" data-type="url" value="inv/getResnInvPage.do"/>
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
        <div class='title pointer'>      
          <span><i class="fa fa-cube"></i>&nbsp;查询条件</span>
        </div>
        <a class="close-query-frame" data-type="close">&#215;</a>
        <div class='line'></div>
        <div class='content'>
          <form>
            <label for='PARTY_NAME' class='left mid'>客户名称</label> 
            <input type='text' id='PARTY_NAME' name='PARTY_NAME' class='left short' required="required" readonly="readonly"/>          
            <input type='hidden' id='CUSTOMER_ID' name='CUSTOMER_ID'/>
            <input type='button' id="CUSTOMER_LOV" class='left button pointer' data-pageframe="lov" data-reveal-id="lov" data-key="true" data-callback="query" data-bg="lov-modal-bg" data-dismissmodalclass='close-lov' data-lovname="客户查询" data-queryurl="lov/getUserCustPage.do" data-jsontype="cust" data-defaultquery="true" data-th=["客户id","客户名称","客户帐号","销售公司"] data-td=["CUSTOMER_ID","PARTY_NAME","ACCOUNT_NUMBER","ORG_NAME"] data-selectname=["客户名称","客户账号"] data-selectvalue=["PARTY_NAME","ACCOUNT_NUMBER"] data-choose=[".CUSTOMER_ID",".PARTY_NAME",".ACCOUNT_NUMBER"] data-recid=["#CUSTOMER_ID","#PARTY_NAME","#ACCOUNT_NUMBER"] value="···"/>
            <label for='ACCOUNT_NUMBER' class='left mid'>客户帐号</label>
            <input type='text' id='ACCOUNT_NUMBER' name='ACCOUNT_NUMBER' required='required' class='left long' readonly="readonly"/>
            <br style="clear:both"/>
            <label for='COATING_NAME' class='left mid'>膜系</label> 
            <input type='text' id='COATING_NAME' name='COATING_NAME' class='left short' data-modify="true" data-pageframe="query" data-validurl="lov/validCoating.do" data-queryurl="lov/getCoatingCode.do" data-lovbtn="COATING_LOV" data-hiddenid=["COATING_CODE"] data-hiddenval=["LOOKUP_CODE"] data-param="meaning"/>          
            <input type='hidden' id='COATING_CODE' name='COATING_TYPE'/>
            <input type='button' id="COATING_LOV" class='left button pointer' data-pageframe="lov" data-reveal-id="lov" data-key="true" data-callback="query" data-bg="lov-modal-bg" data-dismissmodalclass='close-lov' data-lovname="膜系查询" data-queryurl="lov/getCoatingPage.do" data-jsontype="coating" data-defaultquery="true" data-th=["膜系编码","膜系名称","描述"] data-td=["COATING_CODE","COATING_NAME","COATING_DESC"] data-selectname=["膜系编码","膜系名称"] data-selectvalue=["LOOKUP_CODE","MEANING"] data-choose=[".COATING_CODE",".COATING_NAME"] data-recid=["#COATING_NAME","#COATING_CODE"] value="···"/>    
            <label for='PACK_NAME' class='left mid'>包装方式</label> 
            <input type='text' id='PACK_NAME' name='PACK_NAME' class='left short' data-modify="true" data-pageframe="query" data-validurl="lov/validPack.do" data-queryurl="lov/getPackCode.do" data-lovbtn="PACK_LOV" data-hiddenid=["PACK_CODE"] data-hiddenval=["LOOKUP_CODE"] data-param="meaning"/>          
            <input type='hidden' id='PACK_CODE' name='PACK_CODE'/>
            <input type='button' id="PACK_LOV" class='left button pointer' data-pageframe="lov" data-reveal-id="lov" data-key="true" data-callback="query" data-bg="lov-modal-bg" data-dismissmodalclass='close-lov' data-lovname="包装方式查询" data-queryurl="lov/getPackPage.do" data-jsontype="pack" data-defaultquery="true" data-th=["包装编码","包装名称","描述"] data-td=["PACK_CODE","PACK_NAME","PACK_DESC"] data-selectname=["包装编码","包装名称"] data-selectvalue=["LOOKUP_CODE","MEANING"] data-choose=[".PACK_CODE",".PACK_NAME"] data-recid=["#PACK_NAME","#PACK_CODE"] value="···"/>    
            <br style="clear:both"/>
            <label for='WIDTH' class='left mid'>宽度</label> 
            <input type='text' id='WIDTH' name='WIDTH' class='left long'/>
            <label for='HEIGHT' class='left mid'>高度</label> 
            <input type='text' id='HEIGHT' name='HEIGHT' class='left long'/>
          </form>
        </div>
        <div class='foot'>             
          <button class="right pointer"  data-buttonframe="table" data-keyup="enter" data-crudtype="query" data-pageframe="query">查询</button>
        </div> 
      </div>
      <!-- 条件查询区域 end -->    
      
      <!-- 用户信息存放区域 start -->
      <input type="hidden" id="USER_ID" value="${USER_ID}"/>  
      <input type="hidden" id="INTERACT_CODE" value="RESN_INV"/> 
      <input type="hidden" id="HEADER_ID" value=""/> 
      <!-- 用户信息存放区域 end -->  
    </div>   
    <script>       
        $(function() {
            //设置拖拽
    		$("#query").draggable({ handle: ".title" });
    		
    		//初始化CRUD和LOV条件查询
    		$().crudListener();	
    		$().revealListener(); 		
    		
    		$.fn.validateOrgan = function(){
    		   custId = $('#CUSTOMER_ID').val();
    		   if(custId==null||custId==''){
    		       $('.ajax_loading').hide();
    		       layer.alert('必须选择客户才能查询保留库存！',{title:'警告',offset:[150]});
    		       throw new error('必须选择客户才能查询保留库存！');
    		   }  
    		} 		
        });
        
        jQuery.json={
        	getContent:function(data,JSONtype){  
        	    if(JSONtype=='table'){
        	        for(i=0;i<(pageMaxRow-pageMinRow+1);i++){
                    	$('.PARTY_NAME',$('#tb tr:eq('+(i+1)+')')).html(data.rows[i].PARTY_NAME); 
                    	$('.ORGANIZATION_NAME',$('#tb tr:eq('+(i+1)+')')).html(data.rows[i].ORGANIZATION_NAME); 
                   	 	$('.ITEM_NUMBER',$('#tb tr:eq('+(i+1)+')')).html(data.rows[i].ITEM_NUMBER); 
                    	$('.ITEM_DESC',$('#tb tr:eq('+(i+1)+')')).html(data.rows[i].ITEM_DESC); 
						$('.PACK_DESC',$('#tb tr:eq('+(i+1)+')')).html(data.rows[i].PACK_DESC); 
                    	$('.SPEC',$('#tb tr:eq('+(i+1)+')')).html(data.rows[i].SPEC); 
                    	$('.COATING_TYPE',$('#tb tr:eq('+(i+1)+')')).html(data.rows[i].COATING_TYPE); 
                    	$('.BOX_QTY',$('#tb tr:eq('+(i+1)+')')).html(data.rows[i].BOX_QTY); 
                    	$('.ORGANIZATION_ID',$('#tb tr:eq('+(i+1)+')')).html(data.rows[i].ORGANIZATION_ID); 
                    	$('.INVENTORY_ITEM_ID',$('#tb tr:eq('+(i+1)+')')).html(data.rows[i].INVENTORY_ITEM_ID); 
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
       	            	    $('td:eq(3)',$('.contentbox tr:eq('+(i+1)+')')).html(data.rows[i].ORG_NAME); 
       	            	} 
        	        }       	            	    
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
        	    }else if(JSONtype=='pack'){
        	        if(pageMaxRow==0&&pageMinRow==0){
        	            console.log('no data');
        	        }else{
        	            for(i=0;i<(pageMaxRow-pageMinRow+1);i++){
       	            	    $('td:eq(0)',$('.contentbox tr:eq('+(i+1)+')')).html(data.rows[i].LOOKUP_CODE);  
       	            	    $('td:eq(1)',$('.contentbox tr:eq('+(i+1)+')')).html(data.rows[i].MEANING);     	                    
       	            	    $('td:eq(2)',$('.contentbox tr:eq('+(i+1)+')')).html(data.rows[i].DESCRIPTION);    	               	        
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