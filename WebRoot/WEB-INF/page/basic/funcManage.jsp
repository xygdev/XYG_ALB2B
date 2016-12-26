<%@ page contentType="text/html;charset=utf-8" language="java" pageEncoding="utf-8"
	      import="java.util.*" %>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML>
<html>
  <head>
    <title>功能管理</title>
    <base href="<%=basePath%>"> 
    <meta http-equiv="content-type" content="text/html;charset=gb2312">
	<link rel="stylesheet" href="plugin/css/font-awesome.min.css">
	<link rel="stylesheet" href="plugin/css/jquery-ui.min.css">
	<link rel="stylesheet" type="text/css" href="plugin/css/public.css">
	<link rel="stylesheet" type="text/css" href="plugin/css/basic/func.css">
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
            <th class="FUNCTION_CODE" data-column="db">功能编码</th>
            <th class="FUNCTION_NAME" data-column="db">功能名称</th>
            <th class="FUNCTION_HREF" data-column="db">功能链接</th>
     	    <th class="DESCRIPTION" data-column="db">功能描述</th>
     	    <th class="ICON_CODE" data-column="db">图标编码</th>
     	    <th class="ACTION" data-column="normal">操作</th> 
     	    <th class="FUNCTION_ID" style="display:none" data-column="hidden">&nbsp;</th>
     	  </tr>
     	  <tr>
     	    <td class="FUNCTION_CODE" data-column="db"></td>
            <td class="FUNCTION_NAME" data-column="db"></td>
            <td class="FUNCTION_HREF" data-column="db"></td>
     	    <td class="DESCRIPTION" data-column="db"></td>
     	    <td class="ICON_CODE" data-column="db"></td>
     	    <td class="ACTION" data-column="normal">
     	      <i class="fa fa-pencil fa-fw update pointer hidden" data-show="true" data-reveal-id="ui" data-key="true"  data-dismissmodalclass="close-ui-frame" data-crudtype="pre-update" data-preupdateurl="func/preUpdate.do" data-type="update" data-updateparam=["FUNCTION_ID",".FUNCTION_ID"]></i>
     	    </td>
     	    <td class="FUNCTION_ID" style="display:none" data-column="hidden">&nbsp;</td>
     	  </tr>
        </table>
      </div>
      <!-- 主表格区域 end -->
   
      <!-- 主表格按钮区域 start -->
      <div class="table_button" id="table" data-table="Resp">
        <div class="setting">
          <i class="fa fa-cog pointer" title="表格设置" data-reveal-id="setting" data-dismissmodalclass="close-setting"></i>
        </div>
        <div class="setting">
          <i class="fa fa-search pointer" title="条件查询" data-reveal-id="query" data-key="true" data-dismissmodalclass="close-query-frame"></i>
        </div>
        <div class="setting">
          <i class="fa fa-plus-circle pointer" title="新增功能" data-reveal-id="ui" data-key="true"  data-dismissmodalclass="close-ui-frame" data-crudtype="pre-insert" data-type="insert"></i>
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
          <input type="hidden" data-type="orderby" id="ORDER_BY" value="FUNCTION_ID ASC"/> 
          <input type="hidden" data-type="cond"/>
          <input type="hidden" data-type="url" value="func/getFuncPage.do"/>
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
          <span data-type="update"><i class="fa fa-cogs fa-1x" aria-hidden="true"></i>&nbsp;更新功能</span>
          <span data-type="insert"><i class="fa fa-cogs fa-1x" aria-hidden="true"></i>&nbsp;新增功能</span>
        </div>
        <a class="close-ui-frame" data-type="close">&#215;</a>
        <div class='line'></div>
        <div class='content'>
          <form id='updateData'>
            <input type="hidden" id="FUNCTION_ID" name="FUNCTION_ID" data-update="db"/>
            <label for='FUNCTION_CODE' class='left'>功能编码</label>
            <input type='text' id='FUNCTION_CODE' name='FUNCTION_CODE' data-update="db" required='required' class='left'/>
            <label for='FUNCTION_NAME' class='left'>功能名称</label>
            <input type='text' id='FUNCTION_NAME' name='FUNCTION_NAME' data-update="db" required='required' class='left'/>
            <label for='FUNCTION_HREF' class='left'>功能链接</label>
            <input type='text' id='FUNCTION_HREF' name='FUNCTION_HREF' data-update="db" required='required' class='left'/>
            <label for='DESCRIPTION' class='left'>功能描述</label>
            <input type='text' id='DESCRIPTION' name='DESCRIPTION' data-update="db" class='left'/>
            <label for='ICON_CODE' class='left'>图标编码</label> 
            <input type="text" id="ICON_CODE" name="ICON_CODE" data-update="db" class="left short" required="required" readonly="readonly"/>
            <input type='hidden' id='ICON_ID' name='ICON_ID' data-update="db"/>
            <input type='button' id="ICON_LOV" class='left button pointer' data-pageframe="lov" data-reveal-id="lov" data-key="true" data-callback="ui" data-bg="lov-modal-bg" data-dismissmodalclass='close-lov' data-lovname="图标查询" data-queryurl="lov/getIconPage.do" data-jsontype="icon" data-defaultquery="true" data-th=["图标ID","图标编码","图标描述","来源"] data-td=["ICON_ID","ICON_CODE","ICON_DESC","ICON_SOURCE"] data-selectname=["图标编码","图标描述"] data-selectvalue=["ICON_CODE","ICON_DESC"] data-choose=[".ICON_ID",".ICON_CODE"] data-recid=["#ICON_ID","#ICON_CODE"] value="···"/>           
          </form>
        </div>
        <div class='foot'>       
          <button class="right update_confirm pointer" data-type="update" data-keyup="enter" data-crudtype="update" data-pageframe="ui" data-updateurl="func/update.do">提交更新</button>
          <button class="right update_confirm pointer" data-type="insert" data-keyup="enter" data-crudtype="insert" data-pageframe="ui" data-inserturl="func/insert.do">新增</button>
        </div>    
      </div> 
      <!-- 更新/新增用户区域 end -->
     
      <!-- 条件查询区域 start -->
      <div id='query' class='query_frame'>     
        <div class='title pointer'>      
          <span><i class="fa fa-cogs"></i>&nbsp;功能查询</span>
        </div>
        <a class="close-query-frame" data-type="close">&#215;</a>
        <div class='line'></div>
        <div class='content'>
          <form> 
            <label for='FUNCTION_CODE_Q' class='left mid'>功能编码:</label> 
            <input type="text" id="FUNCTION_CODE_Q" name="FUNCTION_CODE" data-update="db" class="left mid" data-modify="true" data-pageframe="query" data-validurl="lov/validFuncCode.do" data-queryurl="lov/getFuncId.do" data-lovbtn="FUNCTION_LOV" data-hiddenid=["FUNCTION_ID_Q","FUNCTION_NAME_Q"] data-hiddenval=["FUNCTION_ID","FUNCTION_NAME"] data-param="funccode"/>
            <input type='hidden' id='FUNCTION_ID_Q' name='FUNCTION_ID' data-update="db"/>
            <input type='button' id="FUNCTION_LOV_Q" class='left button pointer' data-pageframe="lov" data-reveal-id="lov" data-key="true" data-callback="query" data-bg="lov-modal-bg" data-dismissmodalclass='close-lov' data-lovname="功能查询" data-queryurl="lov/getFuncPage.do" data-jsontype="func" data-defaultquery="true" data-th=["功能ID","功能编码","功能名称","描述"] data-td=["FUNC_ID","FUNC_CODE","FUNC_NAME","FUNC_DESC"] data-selectname=["功能编码","功能名称"] data-selectvalue=["FUNCTION_CODE","FUNCTION_NAME"] data-choose=[".FUNC_ID",".FUNC_CODE",".FUNC_NAME"] data-recid=["#FUNCTION_ID_Q","#FUNCTION_CODE_Q","#FUNCTION_NAME_Q"] value="···"/>  
            <label for='FUNCTION_NAME_Q' class='left mid'>功能名称:</label> 
            <input type="text" id="FUNCTION_NAME_Q" name="FUNCTION_NAME" data-update="db" class="left long"  readonly="readonly"/>
          </form>
        </div>
        <div class='foot'>             
          <button class="right pointer"  data-buttonframe="table" data-crudtype="query" data-keyup="enter" data-pageframe="query">功能查询</button>
        </div> 
      </div>
      <!-- 条件查询区域 end -->    
      
      <!-- 用户信息存放区域 start -->
      <input type="hidden" id="USER_ID" value="${USER_ID}"/>  
      <input type="hidden" id="INTERACT_CODE" value="FUNC_MANAGE"/> 
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
                    	$('.FUNCTION_CODE',$('#tb tr:eq('+(i+1)+')')).html(data.rows[i].FUNCTION_CODE); 
                   	 	$('.FUNCTION_NAME',$('#tb tr:eq('+(i+1)+')')).html(data.rows[i].FUNCTION_NAME); 
                   	 	$('.FUNCTION_HREF',$('#tb tr:eq('+(i+1)+')')).html(data.rows[i].FUNCTION_HREF); 
                    	$('.DESCRIPTION',$('#tb tr:eq('+(i+1)+')')).html(data.rows[i].DESCRIPTION);
                    	$('.ICON_CODE',$('#tb tr:eq('+(i+1)+')')).html(data.rows[i].ICON_CODE); 
                    	$('.FUNCTION_ID',$('#tb tr:eq('+(i+1)+')')).html(data.rows[i].FUNCTION_ID); 
                	}
                	$().crudListener();
                	$().revealListener(); 
        	    }else if(JSONtype=='icon'){
        	        if(pageMaxRow==0&&pageMinRow==0){
        	            console.log('no data');
        	        }else{
        	            for(i=0;i<(pageMaxRow-pageMinRow+1);i++){
       	            	    $('td:eq(0)',$('.contentbox tr:eq('+(i+1)+')')).html(data.rows[i].ICON_ID);  
       	            	    $('td:eq(1)',$('.contentbox tr:eq('+(i+1)+')')).html(data.rows[i].ICON_CODE);     	                    
       	            	    $('td:eq(2)',$('.contentbox tr:eq('+(i+1)+')')).html(data.rows[i].DESCRIPTION);
       	            	    $('td:eq(3)',$('.contentbox tr:eq('+(i+1)+')')).html(data.rows[i].ICON_SOURCE);       	               	        
       	            	} 
        	        }       	            	    
        	    }else if(JSONtype=='func'){
        	        if(pageMaxRow==0&&pageMinRow==0){
        	            console.log('no data');
        	        }else{
        	            for(i=0;i<(pageMaxRow-pageMinRow+1);i++){
       	            	    $('td:eq(0)',$('.contentbox tr:eq('+(i+1)+')')).html(data.rows[i].FUNCTION_ID);  
       	            	    $('td:eq(1)',$('.contentbox tr:eq('+(i+1)+')')).html(data.rows[i].FUNCTION_CODE);     	                    
       	            	    $('td:eq(2)',$('.contentbox tr:eq('+(i+1)+')')).html(data.rows[i].FUNCTION_NAME);
       	            	    $('td:eq(3)',$('.contentbox tr:eq('+(i+1)+')')).html(data.rows[i].DESCRIPTION);       	               	        
       	            	} 
        	        }       	            	    
        	    }  	            	   	
       	    },	
       	    getUpdateJSON:function(data){   
       	        $('#FUNCTION_ID').val(data.rows[0].FUNCTION_ID);
       	        $('#FUNCTION_CODE').val(data.rows[0].FUNCTION_CODE);
       	        $('#FUNCTION_NAME').val(data.rows[0].FUNCTION_NAME);
       	        $('#FUNCTION_HREF').val(data.rows[0].FUNCTION_HREF);
       	        $('#DESCRIPTION').val(data.rows[0].DESCRIPTION);
       	        $('#ICON_ID').val(data.rows[0].ICON_ID);
       	        $('#ICON_CODE').val(data.rows[0].ICON_CODE);	        
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