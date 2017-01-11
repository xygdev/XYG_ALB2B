<%@ page contentType="text/html;charset=utf-8" language="java" pageEncoding="utf-8"
	      import="java.util.*" %>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML>
<html>
  <head>
    <title>工作组分配</title>
    <base href="<%=basePath%>"> 
    <meta http-equiv="content-type" content="text/html;charset=gb2312">
	<link rel="stylesheet" href="plugin/css/font-awesome.min.css">
	<link rel="stylesheet" href="plugin/css/jquery-ui.min.css">
	<link rel="stylesheet" type="text/css" href="plugin/css/public.css">
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
        <table id="tb" data-table="Group">
          <tr>
            <th class="ORG_NAME" data-column="db">销售公司</th>
            <th class="PARTY_NAME" data-column="db">客户名称</th>
     	    <th class="ACCOUNT_NUMBER" data-column="db">账号编码</th>
     	    <th class="ACCOUNT_NAME" data-column="db">账号名称</th>
     	    <th class="STATUS_DESC" data-column="db">状态</th>
     	    <th class="GROUP_CODE" data-column="db">工作组编码</th>
     	    <th class="GROUP_NAME" data-column="db">工作组名称</th>
     	    <th class="ACTION" data-column="normal">操作</th> 
     	    <th class="ORG_ID" style="display:none" data-column="hidden">&nbsp;</th>
     	    <th class="CUST_ACCOUNT_ID" style="display:none" data-column="hidden">&nbsp;</th>
     	  </tr>
     	  <tr>
     	    <td class="ORG_NAME" data-column="db"></td>
            <td class="PARTY_NAME" data-column="db"></td>
     	    <td class="ACCOUNT_NUMBER" data-column="db"></td>
     	    <td class="ACCOUNT_NAME" data-column="db"></td>
     	    <td class="STATUS_DESC" data-column="db"></td>
     	    <td class="GROUP_CODE" data-column="db"></td>
     	    <td class="GROUP_NAME" data-column="db"></td>
     	    <td class="ACTION" data-column="normal">
     	      <i class="fa fa-pencil fa-fw update pointer hidden" title="分配工作组" data-show="true" data-reveal-id="ui" data-key="true" data-dismissmodalclass="close-ui-frame" data-crudtype="pre-update" data-preupdateurl="group/preUpdateCustGroup.do" data-type="update" data-updateparam=["ORG_ID",".ORG_ID"] data-func="$().getCustId(tr);"></i>
     	    </td>
     	    <td class="ORG_ID" style="display:none" data-column="hidden">&nbsp;</td>
     	    <td class="CUST_ACCOUNT_ID" style="display:none" data-column="hidden">&nbsp;</td>
     	  </tr>
        </table>
      </div>
      <!-- 主表格区域 end -->
   
      <!-- 主表格按钮区域 start -->
      <div class="table_button" id="table" data-table="Group">
        <div class="setting">
          <i class="fa fa-cog pointer" title="表格设置" data-reveal-id="setting" ddata-dismissmodalclass="close-setting"></i>
        </div>
        <div class="setting">
          <i class="fa fa-search pointer" title="条件查询" data-reveal-id="query" data-key="true" data-dismissmodalclass="close-query-frame"></i>
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
          <input type="hidden" data-type="orderby" id="ORDER_BY" value="PARTY_NAME DESC"/> 
          <input type="hidden" data-type="cond"/>
          <input type="hidden" data-type="url" value="group/getMatchPage.do"/>
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
     
      <!-- 更新/新增用户区域 start -->
      <div id='ui' class="pop_frame row-4">     
        <div class='title pointer'>      
          <span data-type="update"><i class="fa fa-users fa-1x" aria-hidden="true"></i>&nbsp;更新工作组</span>
          <span data-type="insert"><i class="fa fa-users fa-1x" aria-hidden="true"></i>&nbsp;新增工作组</span>
        </div>
        <a class="close-ui-frame" data-type="close">&#215;</a>
        <div class="line"></div>
        <div class="content row-4">
          <form id='updateData'>
            <input type='hidden' id='ORG_ID' name="ORG_ID" data-update="db"/>
            <input type='hidden' id='CUST_ACCOUNT_ID' name="CUST_ACCOUNT_ID" data-update="db"/>
            <label for='ORG_NAME' class='left md'>销售公司</label>
            <input type='text' id='ORG_NAME' name='ORG_NAME' data-update="db" readonly="readonly" class='left lgx2'/>
            <br style="clear:both"/>
            <label for='PARTY_NAME' class='left md'>客户名称</label>
            <input type='text' id='PARTY_NAME' name='PARTY_NAME' data-update="db" readonly="readonly" class='left lgx2'/>
            <br style="clear:both"/>
            <label for='ACCOUNT_NUMBER' class='left md'>账号编号</label>
            <input type='text' id='ACCOUNT_NUMBER' name='ACCOUNT_NUMBER' data-update="db" readonly="readonly" class='left lg'/>
            <label for='ACCOUNT_NAME' class='left md'>账号名称</label>
            <input type='text' id='ACCOUNT_NAME' name='ACCOUNT_NAME' data-update="db" readonly="readonly" class='left lg'/>
            <label for='STATUS_DESC' class='left md'>状态</label>
            <input type='text' id='STATUS_DESC' name='STATUS_DESC' data-update="db" readonly="readonly" class='left lg'/>
            <label for='GROUP_NAME' class='left md'>工作组</label>
            <input type='text' id="GROUP_NAME" name="GROUP_NAME" data-update="db" class="left md" data-modify="true" data-pageframe="ui" data-lovbtn='GROUP_LOV' data-param="GROUP_NAME"/>
            <input type='hidden' id='GROUP_ID' name='GROUP_ID' data-update="db"/>
            <input type="button" id="GROUP_LOV" class="left button pointer" data-pageframe="lov" data-reveal-id="lov" data-key="true" data-callback="ui" data-bg="lov-modal-bg" data-dismissmodalclass='close-lov' data-lovname="工作组查询" data-queryurl="lov/getGroupPage.do" data-jsontype="group" data-defaultquery="true" data-th=["工作组ID","工作组编码","工作组名称","描述"] data-td=["GROUP_ID&none","GROUP_CODE","GROUP_NAME","DESCRIPTION"] data-selectname=["工作组名称","工作组编码"] data-selectvalue=["GROUP_NAME","GROUP_CODE"] data-choose=[".GROUP_ID",".GROUP_NAME"] data-recid=["#GROUP_ID","#GROUP_NAME"]  value="···"/>
          </form>
        </div>
        <div class='foot'>       
          <button class="right update_confirm pointer" data-keyup="enter" data-type="update" data-crudtype="update" data-pageframe="ui" data-updateurl="group/updateCustGroup.do">提交更新</button>
        </div>    
      </div> 
      <!-- 更新/新增用户区域 end --> 
      
      <!-- 条件查询区域 start -->
      <div id='query' class='pop_frame row-1'>     
        <div class='title pointer'>      
          <span><i class="fa fa-users"></i>&nbsp;客户工作组查询</span>
        </div>
        <a class="close-query-frame" data-type="close">&#215;</a>
        <div class='line'></div>
        <div class='content row-1'>
          <form>
            <label for='GROUP_NAME_Q' class='left md'>工作组:</label>
            <input type='text' id="GROUP_NAME_Q" name="GROUP_NAME" data-update="db" class="left md" data-modify="true" data-pageframe="query" data-lovbtn="GROUP_LOV_Q" data-param="GROUP_NAME"/>
            <input type='hidden' id='GROUP_ID_Q' name='GROUP_ID' data-update="db"/>
            <input type="button" id="GROUP_LOV_Q" class="left button pointer" data-pageframe="lov" data-reveal-id="lov" data-key="true" data-callback="query" data-bg="lov-modal-bg" data-dismissmodalclass='close-lov' data-lovname="工作组查询" data-queryurl="lov/getGroupPage.do" data-jsontype="group" data-defaultquery="true" data-th=["工作组ID","工作组编码","工作组名称","描述"] data-td=["GROUP_ID&none","GROUP_CODE","GROUP_NAME","DESCRIPTION"] data-selectname=["工作组名称","工作组编码"] data-selectvalue=["GROUP_NAME","GROUP_CODE"] data-choose=[".GROUP_ID",".GROUP_NAME"] data-recid=["#GROUP_ID_Q","#GROUP_NAME_Q"]  value="···"/>
            
            <label for="PARTY_NAME_Q" class="left md">客户名称:</label> 
            <input type="text" id="PARTY_NAME_Q" name="PARTY_NAME" class="left md" data-modify="true" data-pageframe="query" data-lovbtn="PARTY_LOV_Q" data-param="PARTY_NAME"/>
            <input type="hidden" id="CUST_ACCOUNT_ID_Q" name="CUSTOMER_ID"/>
            <input type="button" id="PARTY_LOV_Q" class="left button pointer" data-pageframe="lov" data-reveal-id="lov" data-key="true" data-callback="query" data-bg="lov-modal-bg" data-dismissmodalclass='close-lov' data-lovname="客户查询" data-queryurl="lov/getCustPage.do" data-jsontype="cust" data-defaultquery="true" data-th=["销售公司ID","销售公司","客户ID","客户名称","客户账号"] data-td=["ORG_ID&none","ORG_NAME","CUST_ACCOUNT_ID","PARTY_NAME","ACCOUNT_NUMBER"] data-selectname=["客户名称","客户账号"] data-selectvalue=["PARTY_NAME","ACCOUNT_NUMBER"] data-choose=[".CUST_ACCOUNT_ID",".PARTY_NAME",".ACCOUNT_NUMBER"] data-recid=["#CUST_ACCOUNT_ID_Q","#PARTY_NAME_Q","#ACCOUNT_NUMBER_Q"] value="···"/> 
          </form>
        </div>
        <div class='foot'>             
          <button class="right pointer"  data-buttonframe="table" data-keyup="enter" data-crudtype="query" data-pageframe="query">客户查询</button>
        </div> 
      </div>
      <!-- 条件查询区域 end -->   
      
      <!-- 用户信息存放区域 start -->
      <input type="hidden" id="USER_ID" value="${USER_ID}"/>  
      <input type="hidden" id="INTERACT_CODE" value="GROUP_MATCH"/> 
      <input type="hidden" id="HEADER_ID" value=""/> 
      <!-- 用户信息存放区域 end -->  
    </div>   
    <script>       
        $(function() {
            //设置拖拽
            $("#ui").draggable({handle: ".title"});
    		$("#query").draggable({handle: ".title"});
 
    		//初始化CRUD和LOV条件查询
    		$().crudListener();
    		$().revealListener(); 
    		
    		$.fn.getCustId = function(tr){    
    		    param = 'CUST_ACCOUNT_ID='+tr.children('.CUST_ACCOUNT_ID').text()+'&';
    		}	 	
    		
        });
        
        jQuery.json={
        	getContent:function(data,JSONtype){  
        	    if(JSONtype=='table'){
        	        for(i=0;i<(pageMaxRow-pageMinRow+1);i++){
                    	$('.ORG_NAME',$('#tb tr:eq('+(i+1)+')')).html(data.rows[i].ORG_NAME); 
                   	 	$('.PARTY_NAME',$('#tb tr:eq('+(i+1)+')')).html(data.rows[i].PARTY_NAME); 
                    	$('.ACCOUNT_NUMBER',$('#tb tr:eq('+(i+1)+')')).html(data.rows[i].ACCOUNT_NUMBER);
                    	$('.ACCOUNT_NAME',$('#tb tr:eq('+(i+1)+')')).html(data.rows[i].ACCOUNT_NAME); 
                    	$('.STATUS_DESC',$('#tb tr:eq('+(i+1)+')')).html(data.rows[i].STATUS_DESC);   
                    	$('.GROUP_CODE',$('#tb tr:eq('+(i+1)+')')).html(data.rows[i].GROUP_CODE);   
                    	$('.GROUP_NAME',$('#tb tr:eq('+(i+1)+')')).html(data.rows[i].GROUP_NAME); 
                    	$('.CUST_ACCOUNT_ID',$('#tb tr:eq('+(i+1)+')')).html(data.rows[i].CUST_ACCOUNT_ID); 
                    	$('.ORG_ID',$('#tb tr:eq('+(i+1)+')')).html(data.rows[i].ORG_ID); 
                	}
                	$().crudListener();
                	$().revealListener(); 
        	    }else if(JSONtype=='group'){
        	        if(pageMaxRow==0&&pageMinRow==0){
        	            console.log('no data');
        	        }else{
        	            for(i=0;i<(pageMaxRow-pageMinRow+1);i++){
       	            	    $('td:eq(0)',$('.contentbox tr:eq('+(i+1)+')')).html(data.rows[i].GROUP_ID);  
       	            	    $('td:eq(1)',$('.contentbox tr:eq('+(i+1)+')')).html(data.rows[i].GROUP_CODE);     	                    
       	            	    $('td:eq(2)',$('.contentbox tr:eq('+(i+1)+')')).html(data.rows[i].GROUP_NAME);
       	            	    $('td:eq(3)',$('.contentbox tr:eq('+(i+1)+')')).html(data.rows[i].DESCRIPTION);     	               	        
       	            	} 
        	        }       	            	    
        	    }else if(JSONtype=='cust'){
        	        if(pageMaxRow==0&&pageMinRow==0){
        	            console.log('no data');
        	        }else{
        	            for(i=0;i<(pageMaxRow-pageMinRow+1);i++){
       	            	    $('td:eq(0)',$('.contentbox tr:eq('+(i+1)+')')).html(data.rows[i].ORG_ID);  
       	            	    $('td:eq(1)',$('.contentbox tr:eq('+(i+1)+')')).html(data.rows[i].ORG_NAME);     	                    
       	            	    $('td:eq(2)',$('.contentbox tr:eq('+(i+1)+')')).html(data.rows[i].CUST_ACCOUNT_ID);
       	            	    $('td:eq(3)',$('.contentbox tr:eq('+(i+1)+')')).html(data.rows[i].PARTY_NAME);  
       	            	    $('td:eq(4)',$('.contentbox tr:eq('+(i+1)+')')).html(data.rows[i].ACCOUNT_NUMBER);    	               	        
       	            	} 
        	        }       	            	    
        	    }   	            	   	
       	    },	
       	    getUpdateJSON:function(data){   
       	        $('#ORG_NAME').val(data.rows[0].ORG_NAME);
       	        $('#PARTY_NAME').val(data.rows[0].PARTY_NAME);
       	        $('#ACCOUNT_NUMBER').val(data.rows[0].ACCOUNT_NUMBER);
       	        $('#ACCOUNT_NAME').val(data.rows[0].ACCOUNT_NAME);
       	        $('#STATUS_DESC').val(data.rows[0].STATUS_DESC);
       	        $('#GROUP_NAME').val(data.rows[0].GROUP_NAME);
       	        $('#GROUP_ID').val(data.rows[0].GROUP_ID);
       	        $('#CUST_ACCOUNT_ID').val(data.rows[0].CUST_ACCOUNT_ID);   	  
       	        $('#ORG_ID').val(data.rows[0].ORG_ID);       
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