<%@ page contentType="text/html;charset=utf-8" language="java" pageEncoding="utf-8"
	      import="java.util.*" %>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML>
<html>
  <head>
    <title>菜单管理</title>
    <base href="<%=basePath%>"> 
    <meta http-equiv="content-type" content="text/html;charset=gb2312">
	<link rel="stylesheet" href="plugin/css/font-awesome.min.css">
	<link rel="stylesheet" href="plugin/css/jquery-ui.min.css">
	<link rel="stylesheet" type="text/css" href="plugin/css/public.css">
	<link rel="stylesheet" type="text/css" href="plugin/css/basic/menu.css">
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
        <table id="mHeader" data-table="MenuHeader">
          <tr>
            <th class="MENU_CODE" data-column="db">菜单编码</th>
            <th class="MENU_NAME" data-column="db">菜单名称</th>
     	    <th class="DESCRIPTION" data-column="db">菜单描述</th>
     	    <th class="ICON_CODE" data-column="db">图标编码</th>
     	    <th class="ICON_DESC" data-column="db">图标描述</th>
     	    <th class="ACTION" data-column="normal">操作</th> 
     	    <th class="MENU_ID" style="display:none" data-column="hidden">&nbsp;</th>
     	  </tr>
     	  <tr>
     	    <td class="MENU_CODE" data-column="db"></td>
            <td class="MENU_NAME" data-column="db"></td>
     	    <td class="DESCRIPTION" data-column="db"></td>
     	    <td class="ICON_CODE" data-column="db"></td>
     	    <td class="ICON_DESC" data-column="db"></td>
     	    <td class="ACTION" data-column="normal">
     	      <i class="fa fa-pencil fa-fw update pointer hidden" title="更新菜单" data-show="true" data-reveal-id="ui" data-key="true" data-dismissmodalclass="close-ui-frame" data-crudtype="pre-update" data-preupdateurl="menu/preUpdateMenuHeader.do" data-type="update" data-updateparam=["MENU_ID",".MENU_ID"]></i>
     	      &nbsp;
     	      <i class="fa fa fa-eye view pointer show_detail hidden" title="菜单明细" data-show="true" data-reveal-id="detail" data-dismissmodalclass="close-detail-frame" ></i>
     	    </td>
     	    <td class="MENU_ID" style="display:none" data-column="hidden">&nbsp;</td>
     	  </tr>
        </table>
      </div>
      <!-- 主表格区域 end -->
   
      <!-- 主表格按钮区域 start -->
      <div class="table_button" id="table" data-table="MenuHeader">
        <div class="setting">
          <i class="fa fa-cog pointer" title="" data-reveal-id="setting" data-dismissmodalclass="close-setting"></i>
        </div>
        <div class="setting">
          <i class="fa fa-search pointer" title="条件查询" data-reveal-id="query" data-key="true" data-dismissmodalclass="close-query-frame"></i>
        </div>
        <div class="setting">
          <i class="fa fa-plus-circle pointer" title="新增菜单" data-reveal-id="ui" data-key="true" data-dismissmodalclass="close-ui-frame" data-crudtype="pre-insert" data-type="insert"></i>
        </div>
        <div class="setting">
          <i id='refresh' class="fa fa-refresh pointer" title="刷新数据" data-pagetype='refresh' data-pageframe="table"></i>
        </div>
        <div id="setting">
          <!-- 设置菜单区域 start -->
          <jsp:include page="../public/setting.jsp" >
			<jsp:param name="rdtable" value="#mHeader" />
			<jsp:param name="odtable" value="#mHeader" />
			<jsp:param name="pageframe" value="table" />
		  </jsp:include>
          <!-- 设置菜单区域 end -->    
        </div>
        <div>
          <!-- 分页按钮区域 start -->
          <jsp:include page="../public/pageArrow.jsp" >
			<jsp:param name="pageframe" value="table" />
			<jsp:param name="func" value="" />
		  </jsp:include>
          <!-- 分页按钮区域 end -->
          <input type="hidden" data-type="size" value="10"/>
          <input type="hidden" data-type="number" value="1"/>
          <input type="hidden" data-type="orderby" id="ORDER_BY" value="MENU_ID ASC"/> 
          <input type="hidden" data-type="cond"/>
          <input type="hidden" data-type="url" value="menu/getMenuHeaderPage.do"/>
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
          <span data-type="update"><i class="fa fa-th-list fa-1x" aria-hidden="true"></i>&nbsp;更新菜单</span>
          <span data-type="insert"><i class="fa fa-th-list fa-1x" aria-hidden="true"></i>&nbsp;新增菜单</span>
        </div>
        <a class="close-ui-frame" data-type="close">&#215;</a>
        <div class='line'></div>
        <div class='content'>
          <form id='updateData'>
            <input type='hidden' id='MENU_ID' name="MENU_ID" data-update="db"/>
            <label for='MENU_CODE' class='left'>菜单编码</label>
            <input type='text' id='MENU_CODE' name='MENU_CODE' data-update="db" required='required' class='left'/>
            <label for='MENU_NAME' class='left'>菜单名称</label>
            <input type='text' id='MENU_NAME' name='MENU_NAME' data-update="db" required='required' class='left'/>
            <label for='DESCRIPTION' class='left'>菜单描述</label>
            <input type='text' id='DESCRIPTION' name='DESCRIPTION' data-update="db" class='left'/>
            <label for='ICON_CODE' class='left'>图标编码</label> 
            <input type="text" id="ICON_CODE" name="ICON_CODE" data-update="db" class="left short" required="required" readonly="readonly"/>
            <input type='hidden' id='ICON_ID' name='ICON_ID' data-update="db"/>
            <input type='button' id="ICON_LOV" class='left button pointer' data-pageframe="lov" data-reveal-id="lov" data-key="true" data-callback="ui" data-bg="lov-modal-bg" data-dismissmodalclass='close-lov' data-lovname="图标查询" data-queryurl="lov/getIconPage.do" data-jsontype="icon" data-defaultquery="true" data-th=["图标ID","图标编码","图标描述","来源"] data-td=["ICON_ID","ICON_CODE","ICON_DESC","ICON_SOURCE"] data-selectname=["图标编码","图标描述"] data-selectvalue=["ICON_CODE","ICON_DESC"] data-choose=[".ICON_ID",".ICON_CODE"] data-recid=["#ICON_ID","#ICON_CODE"] value="···"/>  
          </form>
        </div>
        <div class='foot'>       
          <button class="right update_confirm pointer" data-type="update" data-keyup="enter" data-crudtype="update" data-pageframe="ui" data-updateurl="menu/updateMenuHeader.do">提交更新</button>
          <button class="right update_confirm pointer" data-type="insert" data-keyup="enter" data-crudtype="insert" data-pageframe="ui" data-inserturl="menu/insertMenuHeader.do">新增</button>
        </div>    
      </div> 
      <!-- 更新/新增用户区域 end -->
     
      <!-- 条件查询区域 start -->
      <div id='query' class='query_frame'>     
        <div class='title pointer'>      
          <span><i class="fa fa-th-list"></i>&nbsp;菜单查询</span>
        </div>
        <a class="close-query-frame" data-type="close">&#215;</a>
        <div class='line'></div>
        <div class='content'>
          <form>
            <label for='MENU_CODE_Q' class='left mid'>菜单编码:</label> 
            <input type="text" id="MENU_CODE_Q" name="MENU_CODE" data-update="db" class="left mid" data-modify="true" data-pageframe="query" data-validurl="lov/validMenuCode.do" data-queryurl="lov/getMenuId.do" data-lovbtn="MENU_LOV_Q" data-hiddenid=["MENU_ID_Q","MENU_NAME_Q","MENU_CODE_Q"] data-hiddenval=["MENU_ID","MENU_NAME","MENU_CODE"] data-param="menucode"/>
            <input type='hidden' id='MENU_ID_Q' name='MENU_ID' data-update="db"/>
            <input type='button' id="MENU_LOV_Q" class='left button pointer' data-pageframe="lov" data-reveal-id="lov" data-key="true" data-callback="query" data-bg="lov-modal-bg" data-dismissmodalclass='close-lov' data-lovname="菜单查询" data-queryurl="lov/getMenuPage.do" data-jsontype="menu" data-defaultquery="true" data-th=["菜单ID","菜单编码","菜单名称","描述"] data-td=["MENU_ID","MENU_CODE","MENU_NAME","MENU_DESC"] data-selectname=["菜单编码","菜单名称"] data-selectvalue=["MENU_CODE","MENU_NAME"] data-choose=[".MENU_ID",".MENU_CODE",".MENU_NAME"] data-recid=["#MENU_ID_Q","#MENU_CODE_Q","#MENU_NAME_Q"] value="···"/>
            <label for="MENU_NAME_Q" class='left mid'>菜单名称:</label>
            <input type="text" id="MENU_NAME_Q" name="MENU_NAME"  class="left long"/>
          </form>
        </div>
        <div class='foot'>             
          <button class="right pointer"  data-buttonframe="table" data-keyup="enter" data-crudtype="query" data-pageframe="query">菜单查询</button>
        </div> 
      </div>
      <!-- 条件查询区域 end --> 
      
<!----------------------------------------------菜单明细-------------------------------------------------------- -->      
 
      
      <div class="detail_frame" id="detail">
        <div class='title pointer'>      
          <span><i class="fa fa-th-list"></i>&nbsp;菜单明细</span>
        </div>
        <a class="close-detail-frame" data-type="close">&#215;</a>
        <div class="detail_header">
          <input type="hidden" id="MENU_ID_LINES" />
          <label class="mid" for="MENU_CODE_LINES">菜单编码</label>
          <input type="text" id="MENU_CODE_LINES" class="long" readonly="readonly"/>
          <label class="mid" for="MENU_NAME_LINES">菜单名称</label>
          <input type="text" id="MENU_NAME_LINES" class="long" readonly="readonly"/>
          <br style="clear:both"/>
        </div>
        
        <!-- PO明细表格区域 start -->
        <div class='detail_table'>
          <table id="mLine" data-table="MenuLine">
            <tr>
              <th class="MENU_SEQUENCE" data-column="db">序号</th>
              <th class="SUB_MENU_CODE" data-column="db">子菜单编码</th>
              <th class="SUB_MENU_NAME" data-column="db">子菜单名称</th>
     	      <th class="FUNCTION_CODE" data-column="db">功能编码</th>
     	      <th class="FUNCTION_NAME" data-column="db">功能名称</th>
     	      <th class="ENABLED" data-column="db">是否启用</th>
     	      <th class="ACTION" data-column="normal">操作</th> 
     	      <th class="MENU_ID" style="display:none" data-column="hidden">&nbsp;</th>
     	    </tr>
     	    <tr>
     	      <td class="MENU_SEQUENCE" data-column="db"></td>
     	      <td class="SUB_MENU_CODE" data-column="db"></td>
              <td class="SUB_MENU_NAME" data-column="db"></td>
     	      <td class="FUNCTION_CODE" data-column="db"></td>
     	      <td class="FUNCTION_NAME" data-column="db"></td>
     	      <td class="ENABLED" data-column="db"></td>
     	      <td class="ACTION" data-column="normal">
     	        <i class="fa fa-pencil fa-fw update pointer" data-show="true" data-reveal-id="detail_ui" data-key="true" data-bg="detail-modal-bg" data-dismissmodalclass="close-detail-ui-frame" data-crudtype="pre-update" data-preupdateurl="menu/preUpdateMenuLine.do" data-type="update" data-updateparam=["MENU_SEQUENCE",".MENU_SEQUENCE"] data-func="$().getMenuId();"></i>
     	      </td>
     	      <td class="MENU_ID" style="display:none" data-column="hidden">&nbsp;</td>     	     
     	    </tr>
          </table>
        </div>
        <!-- 菜单明细表格区域 end --> 
        
        <div class="table_button" id="sub_table" data-table="MenuLine">
          <div class="setting">
            <i class="fa fa-plus-circle pointer" data-reveal-id="detail_ui" data-bg="detail-modal-bg" data-dismissmodalclass="close-detail-ui-frame" data-crudtype="pre-insert" data-type="insert" data-func="$().autoAddSeq();"></i>
          </div>
          <div class="setting">
            <i id='sub_refresh' class="fa fa-refresh pointer" data-pagetype='refresh' data-pageframe="sub_table" data-func="$().setParam();"></i>
          </div>
          <div>
            <!-- PO明细分页按钮区域 start -->
            <jsp:include page="../public/pageArrow.jsp" >
			  <jsp:param name="pageframe" value="sub_table" />
			  <jsp:param name="func" value="$().setParam();" />
		    </jsp:include>
            <!-- PO明细分页按钮区域 end -->
            <input type="hidden" data-type="size" value="5"/>
            <input type="hidden" data-type="number" value="1"/>
            <input type="hidden" data-type="orderby" value="MENU_SEQUENCE ASC"/> 
            <input type="hidden" data-type="cond"/>
            <input type="hidden" data-type="url" value="menu/getMenuLinePage.do"/>
            <input type="hidden" data-type="jsontype" value="subtable"/> 
          </div>
        </div>
      </div>
    </div>   
    
    
    <!-- 菜单明细新增/更新区域 start -->
    <div id='detail_ui' class='detail_update_frame'>     
        <div class='title pointer'>      
          <span data-type="update"><i class="fa fa-th-list  fa-1x" aria-hidden="true"></i>&nbsp;更新菜单明细</span>
          <span data-type="insert"><i class="fa fa-th-list  fa-1x" aria-hidden="true"></i>&nbsp;新增菜单明细</span>
        </div>
        <a class="close-detail-ui-frame" data-type="close">&#215;</a>
        <div class='line'></div>
        <div class='content'>
          <form id='updateDetailData'>
            <input type='hidden' id='MENU_ID_DETAIL' name="MENU_ID" data-update="db" value=""/>
            <label for='MENU_SEQUENCE' class='left'>菜单序号</label> 
            <input type="text" id="MENU_SEQUENCE" name="MENU_SEQUENCE" data-update="db" class="left long" required="required"/>
            <label for='MENU_ENABLED_FLAG' class='left'>是否启用</label> 
            <select class='left long' id='MENU_ENABLED_FLAG' name='ENABLED_FLAG' data-update="db" data-notnull="true" required='required' data-listurl="list/getEnableFlag.do"></select> 
            <br style="clear:both"/>
            <label for='SUB_MENU_CODE' class='left'>子菜单编码</label> 
            <input type="text" id="SUB_MENU_CODE" name="SUB_MENU_CODE" data-update="db" class="left short" data-modify="true" data-pageframe="detail_ui" data-validurl="lov/validMenuCode.do" data-queryurl="lov/getMenuId.do" data-lovbtn="SUB_MENU_LOV" data-hiddenid=["SUB_MENU_ID"] data-hiddenval=["MENU_ID"] data-param="menucode"/>
            <input type='hidden' id='SUB_MENU_ID' name='SUB_MENU_ID' data-update="db"/>
            <input type='button' id="SUB_MENU_LOV" class='left button pointer' data-pageframe="lov" data-reveal-id="lov" data-key="true" data-callback="detail_ui" data-bg="lov-modal-bg" data-dismissmodalclass='close-lov' data-lovname="菜单查询" data-queryurl="lov/getMenuPage.do" data-jsontype="menu" data-defaultquery="true" data-th=["菜单ID","菜单编码","菜单名称","描述"] data-td=["MENU_ID","MENU_CODE","MENU_NAME","MENU_DESC"] data-selectname=["菜单编码","菜单名称"] data-selectvalue=["MENU_CODE","MENU_NAME"] data-choose=[".MENU_ID",".MENU_CODE",".MENU_NAME"] data-recid=["#SUB_MENU_ID","#SUB_MENU_CODE","#SUB_MENU_NAME"] value="···"/>  
            <label for='SUB_MENU_NAME' class='left'>子菜单名称</label> 
            <input type="text" id="SUB_MENU_NAME" name="SUB_MENU_NAME" data-update="db" class="left long" readonly="readonly"/>
            <label for='FUNCTION_CODE' class='left'>功能编码</label> 
            <input type="text" id="FUNCTION_CODE" name="FUNCTION_CODE" data-update="db" class="left short" data-modify="true" data-pageframe="detail_ui" data-validurl="lov/validFuncCode.do" data-queryurl="lov/getFuncId.do" data-lovbtn="FUNCTION_LOV" data-hiddenid=["FUNCTION_ID","FUNCTION_NAME"] data-hiddenval=["FUNCTION_ID","FUNCTION_NAME"] data-param="funccode"/>
            <input type='hidden' id='FUNCTION_ID' name='FUNCTION_ID' data-update="db"/>
            <input type='button' id="FUNCTION_LOV" class='left button pointer' data-pageframe="lov" data-reveal-id="lov" data-key="true" data-callback="detail_ui" data-bg="lov-modal-bg" data-dismissmodalclass='close-lov' data-lovname="功能查询" data-queryurl="lov/getFuncPage.do" data-jsontype="func" data-defaultquery="true" data-th=["功能ID","功能编码","功能名称","描述"] data-td=["FUNC_ID","FUNC_CODE","FUNC_NAME","FUNC_DESC"] data-selectname=["功能编码","功能名称"] data-selectvalue=["FUNCTION_CODE","FUNCTION_NAME"] data-choose=[".FUNC_ID",".FUNC_CODE",".FUNC_NAME"] data-recid=["#FUNCTION_ID","#FUNCTION_CODE","#FUNCTION_NAME"] value="···"/>  
            <label for='FUNCTION_NAME' class='left'>功能名称</label> 
            <input type="text" id="FUNCTION_NAME" name="FUNCTION_NAME" data-update="db" class="left long"  readonly="readonly"/>
          </form>
        </div>
        <div class='foot'>       
          <button class="right update_confirm pointer" data-type="update" data-keyup="enter" data-crudtype="update" data-pageframe="detail_ui" data-updateurl="menu/updateMenuLine.do" data-refresh="sub_refresh">提交更新</button>
          <button class="right update_confirm pointer" data-type="insert" data-keyup="enter" data-crudtype="insert" data-pageframe="detail_ui" data-inserturl="menu/insertMenuLine.do" data-refresh="sub_refresh">新增</button>
        </div>    
      </div>   
    <!-- 菜单明细新增/更新区域 end -->  

<!----------------------------------------------菜单明细-------------------------------------------------------- -->       
      
      <!-- 用户信息存放区域 start -->
      <input type="hidden" id="USER_ID" value="${USER_ID}"/>  
      <input type="hidden" id="INTERACT_CODE" value="MENU_MANAGE"/> 
      <input type="hidden" id="HEADER_ID" value=""/> 
      <!-- 用户信息存放区域 end -->  
           
    <script>       
        $(function() {
            //设置拖拽
            $("#ui").draggable({ handle: ".title" });
    		$("#detail").draggable({ handle: ".title" });
    		$("#detail_ui").draggable({ handle: ".title" });
    		$("#query").draggable({ handle: ".title" });
 
    		//初始化CRUD和LOV条件查询
    		$().crudListener();	
    		$().revealListener(); 
    		
    		$.fn.setParam = function(){
    		    menuId=$('#MENU_ID_LINES').val();
    		    param=param+'&MENU_ID='+menuId;
    		}	
    		
    		$.fn.detailShow = function(){
    		    $('.show_detail').off('click');
    		    $('.show_detail').on('click',function(){
    		        tr=$(this).parent().parent();
    		        menuId=tr.children('.MENU_ID').text();
    		    	menuCode=tr.children('.MENU_CODE').text();
    		    	menuName=tr.children('.MENU_NAME').text();
    		    	$('.detail_header input').val('');
    		    	$('#sub_table input[data-type="number"]').val('1');
    		    	$('#MENU_ID_LINES').val(menuId);
    		    	$('#MENU_CODE_LINES').val(menuCode);
    		    	$('#MENU_NAME_LINES').val(menuName);
    		    	$('#sub_refresh').click();
    		    });    		   
    		} 	
    		
    		$.fn.autoAddSeq = function(){
    		    menuId = $('#MENU_ID_LINES').val();
    		    param = 'MENU_ID='+menuId;
    		    $.ajax({
				    type:'post', 
				    data:param,
				    url:'menu/getAutoAddSeq.do',
				    dataType:'json',
				    success: function (data) {
				        $('#MENU_ID_DETAIL').val(menuId);
				        $('#MENU_SEQUENCE').val(data.rows[0].MENU_SEQUENCE);
				    },
				    error: function () {
				    	layer.alert('获取数据失败',{title:'警告',offset:[150]});
				    }			
				});	
    		}	
    		
    		$.fn.getMenuId = function(){
    		    menuId = $('#MENU_ID_LINES').val();
    		    param = 'MENU_ID='+menuId+'&';
    		}
    		
        });
        
        jQuery.json={
        	getContent:function(data,JSONtype){  
        	    if(JSONtype=='table'){
        	        for(i=0;i<(pageMaxRow-pageMinRow+1);i++){
                    	$('.MENU_CODE',$('#mHeader tr:eq('+(i+1)+')')).html(data.rows[i].MENU_CODE); 
                   	 	$('.MENU_NAME',$('#mHeader tr:eq('+(i+1)+')')).html(data.rows[i].MENU_NAME); 
                    	$('.DESCRIPTION',$('#mHeader tr:eq('+(i+1)+')')).html(data.rows[i].DESCRIPTION);
                    	$('.ICON_CODE',$('#mHeader tr:eq('+(i+1)+')')).html(data.rows[i].ICON_CODE); 
                    	$('.ICON_DESC',$('#mHeader tr:eq('+(i+1)+')')).html(data.rows[i].ICON_DESC);   
                    	$('.MENU_ID',$('#mHeader tr:eq('+(i+1)+')')).html(data.rows[i].MENU_ID); 
                	}
                	$().crudListener();
                	$().detailShow();
                	$().revealListener(); 
        	    }else if(JSONtype=='subtable'){
        	        for(i=0;i<(pageMaxRow-pageMinRow+1);i++){
        	            $('.SUB_MENU_CODE',$('#mLine tr:eq('+(i+1)+')')).html(data.rows[i].SUB_MENU_CODE); 
                   	 	$('.SUB_MENU_NAME',$('#mLine tr:eq('+(i+1)+')')).html(data.rows[i].SUB_MENU_NAME); 
                    	$('.FUNCTION_CODE',$('#mLine tr:eq('+(i+1)+')')).html(data.rows[i].FUNCTION_CODE);
                    	$('.FUNCTION_NAME',$('#mLine tr:eq('+(i+1)+')')).html(data.rows[i].FUNCTION_NAME); 
                    	$('.MENU_ID',$('#mLine tr:eq('+(i+1)+')')).html(data.rows[i].MENU_ID);   
                    	$('.MENU_SEQUENCE',$('#mLine tr:eq('+(i+1)+')')).html(data.rows[i].MENU_SEQUENCE); 
                    	$('.ENABLED',$('#mLine tr:eq('+(i+1)+')')).html(data.rows[i].ENABLED);
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
       	    getUpdateJSON:function(data,pageframe){   
       	        if(pageframe=='ui'){
       	            $('#MENU_ID').val(data.rows[0].MENU_ID);
       	        	$('#MENU_CODE').val(data.rows[0].MENU_CODE);
       	        	$('#MENU_NAME').val(data.rows[0].MENU_NAME);
       	        	$('#DESCRIPTION').val(data.rows[0].DESCRIPTION);
       	        	$('#ICON_ID').val(data.rows[0].MENU_ICON_ID);  
       	        	$('#ICON_CODE').val(data.rows[0].ICON_CODE);
       	        }else if(pageframe='detail_ui'){
       	            $('#MENU_ID_DETAIL').val(data.rows[0].MENU_ID);
       	            $('#MENU_SEQUENCE').val(data.rows[0].MENU_SEQUENCE);
       	            $('#SUB_MENU_ID').val(data.rows[0].SUB_MENU_ID);
       	            $('#SUB_MENU_CODE').val(data.rows[0].SUB_MENU_CODE);
       	            $('#SUB_MENU_NAME').val(data.rows[0].SUB_MENU_NAME);
       	            $('#FUNCTION_ID').val(data.rows[0].FUNCTION_ID);
       	            $('#FUNCTION_CODE').val(data.rows[0].FUNCTION_CODE);
       	            $('#FUNCTION_NAME').val(data.rows[0].FUNCTION_NAME);
       	            $('#MENU_ENABLED_FLAG').val(data.rows[0].ENABLED_FLAG);
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