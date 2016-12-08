<%@ page contentType="text/html;charset=utf-8" language="java" pageEncoding="utf-8"
	      import="java.util.*" %>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML>
<html>
  <head>
    <title>职责管理</title>
    <base href="<%=basePath%>"> 
    <meta http-equiv="content-type" content="text/html;charset=gb2312">
	<link rel="stylesheet" href="plugin/css/font-awesome.min.css">
	<link rel="stylesheet" href="plugin/css/jquery-ui.min.css">
	<link rel="stylesheet" type="text/css" href="plugin/css/public.css">
	<link rel="stylesheet" type="text/css" href="plugin/css/basic/resp.css">
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
        <table id="tb" data-table="Resp">
          <tr>
            <th class="RESP_CODE" data-column="db">职责编码</th>
            <th class="RESP_NAME" data-column="db">职责名称</th>
     	    <th class="DESCRIPTION" data-column="db">职责描述</th>
     	    <th class="MENU_CODE" data-column="db">菜单编码</th>
     	    <th class="MENU_NAME" data-column="db">菜单名称</th>
     	    <th class="START_DATE" data-column="db">启用日期</th>
     	    <th class="END_DATE" data-column="db">失效日期</th>
     	    <th class="ACTION" data-column="normal">操作</th> 
     	    <th class="MENU_ID" style="display:none" data-column="hidden">&nbsp;</th>
     	    <th class="RESP_ID" style="display:none" data-column="hidden">&nbsp;</th>
     	  </tr>
     	  <tr>
     	    <td class="RESP_CODE" data-column="db"></td>
            <td class="RESP_NAME" data-column="db"></td>
     	    <td class="DESCRIPTION" data-column="db"></td>
     	    <td class="MENU_CODE" data-column="db"></td>
     	    <td class="MENU_NAME" data-column="db"></td>
     	    <td class="START_DATE" data-column="db"></td>
     	    <td class="END_DATE" data-column="db"></td>
     	    <td class="ACTION" data-column="normal">
     	      <i class="fa fa-pencil fa-fw update pointer hidden" title="更新职责" data-show="true" data-reveal-id="ui" data-dismissmodalclass="close-ui-frame" data-crudtype="pre-update" data-preupdateurl="resp/preUpdate.do" data-type="update" data-updateparam=["RESP_ID",".RESP_ID"]></i>
     	    </td>
     	    <td class="MENU_ID" style="display:none" data-column="hidden">&nbsp;</td>
     	    <td class="RESP_ID" style="display:none" data-column="hidden">&nbsp;</td>
     	  </tr>
        </table>
      </div>
      <!-- 主表格区域 end -->
   
      <!-- 主表格按钮区域 start -->
      <div class="table_button" id="table" data-table="Resp">
        <div class="setting">
          <i class="fa fa-cog pointer" data-reveal-id="setting" title="表格设置" data-dismissmodalclass="close-setting"></i>
        </div>
        <div class="setting">
          <i class="fa fa-search pointer" data-reveal-id="query" title="条件查询" data-dismissmodalclass="close-query-frame"></i>
        </div>
        <div class="setting">
          <i class="fa fa-plus-circle pointer" data-reveal-id="ui" title="新增职责" data-dismissmodalclass="close-ui-frame" data-crudtype="pre-insert" data-type="insert"></i>
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
          <input type="hidden" data-type="orderby" id="ORDER_BY" value="RESP_ID"/> 
          <input type="hidden" data-type="cond"/>
          <input type="hidden" data-type="url" value="resp/getRespPage.do"/>
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
      <div id='ui' class='update_frame'>     
        <div class='title pointer'>      
          <span data-type="update"><i class="fa fa-globe fa-1x" aria-hidden="true"></i>&nbsp;更新职责</span>
          <span data-type="insert"><i class="fa fa-globe fa-1x" aria-hidden="true"></i>&nbsp;新增职责</span>
        </div>
        <a class="close-ui-frame" data-type="close">&#215;</a>
        <div class='line'></div>
        <div class='content'>
          <form id='updateData'>
            <input type='hidden' id='RESP_ID' name="RESP_ID" data-update="db"/>
            <label for='RESP_CODE' class='left'>职责编码</label>
            <input type='text' id='RESP_CODE' name='RESP_CODE' data-update="db" required='required' class='left'/>
            <label for='RESP_NAME' class='left'>职责名称</label>
            <input type='text' id='RESP_NAME' name='RESP_NAME' data-update="db" required='required' class='left'/>
            <label for='DESCRIPTION' class='left'>职责描述</label>
            <input type='text' id='DESCRIPTION' name='DESCRIPTION' data-update="db" class='left'/>
            <label for='MENU_CODE' class='left'>职责菜单</label> 
            <input type="text" id="MENU_CODE" name="MENU_CODE" data-update="db" class="left short" data-modify="true" required="required" data-pageframe="ui" data-validurl="lov/validMenuCode.do" data-queryurl="lov/getMenuId.do" data-lovbtn="MENU_LOV" data-hiddenid=["MENU_ID"] data-hiddenval=["MENU_ID"] data-param="menucode"/>
            <input type='hidden' id='MENU_ID' name='MENU_ID' data-update="db"/>
            <input type='button' id="MENU_LOV" class='left button pointer' data-pageframe="lov" data-reveal-id="lov" data-bg="lov-modal-bg" data-dismissmodalclass='close-lov' data-lovname="菜单查询" data-queryurl="lov/getMenuPage.do" data-jsontype="menu" data-defaultquery="true" data-th=["菜单ID","菜单编码","菜单名称","描述"] data-td=["MENU_ID","MENU_CODE","MENU_NAME","MENU_DESC"] data-selectname=["菜单编码","菜单名称"] data-selectvalue=["MENU_CODE","MENU_NAME"] data-choose=[".MENU_ID",".MENU_CODE"] data-recid=["#MENU_ID","#MENU_CODE"] value="···"/>
            <br style="clear:both"/>
            <label for='START_DATE' class='left'>启用日期</label>
            <input type='text' id='START_DATE' name='START_DATE' data-update="db" required='required' class='left'/>
            <label for='END_DATE' class='left'>失效日期</label>
            <input type='text' id='END_DATE' name='END_DATE' data-update="db" class='left'/>  
          </form>
        </div>
        <div class='foot'>       
          <button class="right update_confirm pointer" data-type="update" data-crudtype="update" data-pageframe="ui" data-updateurl="resp/update.do" data-updateparam=["RESP_ID","#RESP_ID"] >提交更新</button>
          <button class="right update_confirm pointer" data-type="insert" data-crudtype="insert" data-pageframe="ui" data-inserturl="resp/insert.do">新增</button>
        </div>    
      </div> 
      <!-- 更新/新增用户区域 end -->
     
      <!-- 条件查询区域 start -->
      <div id='query' class='query_frame'>     
        <div class='title pointer'>      
          <span><i class="fa fa-globe"></i>&nbsp;职责查询</span>
        </div>
        <a class="close-query-frame" data-type="close">&#215;</a>
        <div class='line'></div>
        <div class='content'>
          <form>
            <label for='RESP_NAME_Q' class='left mid'>职责:</label> 
            <input type='text' id='RESP_NAME_Q' name='RESP_NAME' data-update="db" class='left mid' data-modify='true' data-pageframe="query" data-validurl='lov/validRespName.do' data-queryurl='lov/getRespId.do' data-lovbtn='RESP_LOV' data-hiddenid=["RESP_ID_Q"] data-hiddenval=["RESP_ID"] data-param="respname"/>
            <input type='hidden' id='RESP_ID_Q' name='RESP_ID' data-update="db"/>
            <input type='button' id="RESP_LOV_Q" class='left button pointer' data-pageframe="lov" data-reveal-id="lov" data-bg="lov-modal-bg" data-dismissmodalclass='close-lov' data-lovname="职责查询" data-queryurl="lov/getRespPage.do" data-jsontype="resp" data-defaultquery="true" data-th=["职责ID","职责编码","职责名称","描述"] data-td=["RESP_ID","RESP_CODE","RESP_NAME","RESP_DESC"] data-selectname=["职责编码","职责名称"] data-selectvalue=["RESP_CODE","RESP_NAME"] data-choose=[".RESP_ID",".RESP_NAME"] data-recid=["#RESP_ID_Q","#RESP_NAME_Q"] value="···"/>
            <br style="clear:both"/>
            <label for='MENU_NAME_Q' class='left mid'>菜单:</label> 
            <input type="text" id="MENU_NAME_Q" name="MENU_NAME" data-update="db" class="left mid" data-modify="true" data-pageframe="query" data-validurl="lov/validMenuCode.do" data-queryurl="lov/getMenuId.do" data-lovbtn="MENU_LOV_Q" data-hiddenid=["MENU_ID_Q","MENU_NAME_Q"] data-hiddenval=["MENU_ID","MENU_NAME"] data-param="menucode"/>
            <input type='hidden' id='MENU_ID_Q' name='MENU_ID' data-update="db"/>
            <input type='button' id="MENU_LOV_Q" class='left button pointer' data-pageframe="lov" data-reveal-id="lov" data-bg="lov-modal-bg" data-dismissmodalclass='close-lov' data-lovname="菜单查询" data-queryurl="lov/getMenuPage.do" data-jsontype="menu" data-defaultquery="true" data-th=["菜单ID","菜单编码","菜单名称","描述"] data-td=["MENU_ID","MENU_CODE","MENU_NAME","MENU_DESC"] data-selectname=["菜单编码","菜单名称"] data-selectvalue=["MENU_CODE","MENU_NAME"] data-choose=[".MENU_ID",".MENU_NAME"] data-recid=["#MENU_ID_Q","#MENU_NAME_Q"] value="···"/>
            <br style="clear:both"/>
            <label for="START_DATE_F" class="left mid">启用日期:</label>
            <input type="text" id="START_DATE_F" name="START_DATE_F" class="left long" placeholder="起始启用日期"/>
            <input type="text" id="START_DATE_T" name="START_DATE_T" class="left long" placeholder="截止启用日期"/>
            <br style="clear:both"/>
            <label for="END_DATE_F" class="left mid">失效日期:</label>
            <input type="text" id="END_DATE_F" name="END_DATE_F" class="left long" placeholder="起始失效日期"/>
            <input type="text" id="END_DATE_T" name="END_DATE_T" class="left long" placeholder="截止失效日期"/>
          </form>
        </div>
        <div class='foot'>             
          <button class="right pointer"  data-buttonframe="table" data-crudtype="query" data-pageframe="query">职责查询</button>
        </div> 
      </div>
      <!-- 条件查询区域 end -->    
      
      <!-- 用户信息存放区域 start -->
      <input type="hidden" id="USER_ID" value="${USER_ID}"/>  
      <input type="hidden" id="INTERACT_CODE" value="RESP_MANAGE"/> 
      <input type="hidden" id="HEADER_ID" value=""/> 
      <!-- 用户信息存放区域 end -->  
    </div>   
    <script>       
        $(function() {
            //设置拖拽
            $("#ui").draggable({ handle: ".title" });
    		$("#query").draggable({ handle: ".title" });
    		//初始化CRUD和LOV条件查询
    		$().crudListener();	
    		$().revealListener(); 	 		
        });
        
        jQuery.json={
        	getContent:function(data,JSONtype){  
        	    if(JSONtype=='table'){
        	        for(i=0;i<(pageMaxRow-pageMinRow+1);i++){
                    	$('.RESP_CODE',$('#tb tr:eq('+(i+1)+')')).html(data.rows[i].RESP_CODE); 
                   	 	$('.RESP_NAME',$('#tb tr:eq('+(i+1)+')')).html(data.rows[i].RESP_NAME); 
                    	$('.DESCRIPTION',$('#tb tr:eq('+(i+1)+')')).html(data.rows[i].DESCRIPTION);
                    	$('.MENU_CODE',$('#tb tr:eq('+(i+1)+')')).html(data.rows[i].MENU_CODE); 
                    	$('.MENU_NAME',$('#tb tr:eq('+(i+1)+')')).html(data.rows[i].MENU_NAME);   
                    	$('.START_DATE',$('#tb tr:eq('+(i+1)+')')).html(data.rows[i].START_DATE);   
                    	$('.END_DATE',$('#tb tr:eq('+(i+1)+')')).html(data.rows[i].END_DATE); 
                    	$('.MENU_ID',$('#tb tr:eq('+(i+1)+')')).html(data.rows[i].MENU_ID); 
                    	$('.RESP_ID',$('#tb tr:eq('+(i+1)+')')).html(data.rows[i].RESP_ID); 
                	}
                	$().crudListener();
                	$().revealListener(); 
        	    }else if(JSONtype=='menu'){
        	        if(pageMaxRow==0&&pageMinRow==0){
        	            console.log('no data');
        	        }else{
        	            for(i=0;i<(pageMaxRow-pageMinRow+1);i++){
       	            	    $('td:eq(0)',$('.contentbox tr:eq('+(i+1)+')')).html(data.rows[i].MENU_ID);  
       	            	    $('td:eq(1)',$('.contentbox tr:eq('+(i+1)+')')).html(data.rows[i].MENU_CODE);     	                    
       	            	    $('td:eq(2)',$('.contentbox tr:eq('+(i+1)+')')).html(data.rows[i].MENU_NAME);
       	            	    $('td:eq(3)',$('.contentbox tr:eq('+(i+1)+')')).html(data.rows[i].DESCRIPTION);       	               	        
       	            	} 
        	        }       	            	    
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
        	    }else if(JSONtype=='resp'){
        	        if(pageMaxRow==0&&pageMinRow==0){
        	            console.log('no data');
        	        }else{
        	            for(i=0;i<(pageMaxRow-pageMinRow+1);i++){
       	            	    $('td:eq(0)',$('.contentbox tr:eq('+(i+1)+')')).html(data.rows[i].RESP_ID);  
       	            	    $('td:eq(1)',$('.contentbox tr:eq('+(i+1)+')')).html(data.rows[i].RESP_CODE);     	                    
       	            	    $('td:eq(2)',$('.contentbox tr:eq('+(i+1)+')')).html(data.rows[i].RESP_NAME);
       	            	    $('td:eq(3)',$('.contentbox tr:eq('+(i+1)+')')).html(data.rows[i].DESCRIPTION);       	               	        
       	            	} 
        	        }       	            	    
        	    }            	   	
       	    },	
       	    getUpdateJSON:function(data){   
       	        $('#RESP_ID').val(data.rows[0].RESP_ID);
       	        $('#RESP_CODE').val(data.rows[0].RESP_CODE);
       	        $('#RESP_NAME').val(data.rows[0].RESP_NAME);
       	        $('#DESCRIPTION').val(data.rows[0].DESCRIPTION);
       	        $('#MENU_ID').val(data.rows[0].MENU_ID);
       	        $('#MENU_CODE').val(data.rows[0].MENU_CODE);
       	        $('#START_DATE').val(data.rows[0].START_DATE);
       	        $('#END_DATE').val(data.rows[0].END_DATE);   	        
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