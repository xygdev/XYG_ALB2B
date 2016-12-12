<%@ page contentType="text/html;charset=utf-8" language="java" pageEncoding="utf-8"
	      import="java.util.*" %>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML>
<html>
  <head>
    <title>系统功能权限分配</title>
    <base href="<%=basePath%>"> 
    <meta http-equiv="content-type" content="text/html;charset=gb2312">
	<link rel="stylesheet" href="plugin/css/font-awesome.min.css">
	<link rel="stylesheet" href="plugin/css/jquery-ui.min.css">
	<link rel="stylesheet" type="text/css" href="plugin/css/public.css">
	<link rel="stylesheet" type="text/css" href="plugin/css/basic/funcPerm.css">
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
        <table id="tb" data-table="FuncPerm">
          <tr>
            <th class="USER_NAME" data-column="db">用户账号</th>
     	    <th class="USER_DESC" data-column="db">用户姓名</th>
     	    <th class="FUNCTION_CODE" data-column="db">功能编码</th>
     	    <th class="FUNCTION_NAME" data-column="db">功能名称</th>
     	    <th class="INSERT_FLAG" data-column="db">新增</th>
     	    <th class="UPDATE_FLAG" data-column="db">更新</th>
     	    <th class="APPROVE_FLAG" data-column="db">审批</th>
     	    <th class="FINAL_APPROVE_FLAG" data-column="db">终审</th>
     	    <th class="DOWNLOAD_FLAG" data-column="db">下载</th>
     	    <th class="START_DATE" data-column="db">启用日期</th>
     	    <th class="END_DATE" data-column="db">失效日期</th>
     	    <th class="ACTION" data-column="normal">操作</th> 
     	    <th class="P_ID" style="display:none" data-column="hidden">&nbsp;</th>
     	  </tr>
     	  <tr>
            <td class="USER_NAME" data-column="db"></td>
     	    <td class="USER_DESC" data-column="db"></td>
     	    <td class="FUNCTION_CODE" data-column="db"></td>
     	    <td class="FUNCTION_NAME" data-column="db"></td>
     	    <td class="INSERT_FLAG" data-column="db"></td>
     	    <td class="UPDATE_FLAG" data-column="db"></td>
     	    <td class="APPROVE_FLAG" data-column="db"></td>
     	    <td class="FINAL_APPROVE_FLAG" data-column="db"></td>
     	    <td class="DOWNLOAD_FLAG" data-column="db"></td>
     	    <td class="START_DATE" data-column="db"></td>
     	    <td class="END_DATE" data-column="db"></td>
     	    <td class="ACTION" data-column="normal">
     	      <i class="fa fa-pencil fa-fw update pointer hidden" title="更新功能权限" data-show="true" data-reveal-id="ui" data-dismissmodalclass="close-ui-frame" data-crudtype="pre-update" data-preupdateurl="perm/preUpdateFP.do" data-type="update" data-updateparam=["P_ID",".P_ID"]></i>
     	    </td>
     	    <td class="P_ID" style="display:none" data-column="hidden">&nbsp;</td>
     	  </tr>
        </table>
      </div>
      <!-- 主表格区域 end -->
   
      <!-- 主表格按钮区域 start -->
      <div class="table_button" id="table" data-table="FuncPerm">
        <div class="setting">
          <i class="fa fa-cog pointer" title="表格设置" data-reveal-id="setting" data-dismissmodalclass="close-setting"></i>
        </div>
        <div class="setting">
          <i class="fa fa-search pointer" title="条件查询" data-reveal-id="query" data-dismissmodalclass="close-query-frame"></i>
        </div>
        <div class="setting">
          <i class="fa fa-plus-circle pointer" title="新增功能权限" data-reveal-id="ui" data-dismissmodalclass="close-ui-frame" data-crudtype="pre-insert" data-type="insert" data-revealfunc="$().afterReveal(); " ></i>
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
          <input type="hidden" data-type="orderby" id="ORDER_BY" value="P_ID"/> 
          <input type="hidden" data-type="cond"/>
          <input type="hidden" data-type="url" value="perm/getFuncPerm.do"/>
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
          <span data-type="update"><i class="fa fa-expeditedssl fa-1x" aria-hidden="true"></i>&nbsp;更新职责</span>
          <span data-type="insert"><i class="fa fa-expeditedssl fa-1x" aria-hidden="true"></i>&nbsp;新增职责</span>
        </div>
        <a class="close-ui-frame" data-type="close">&#215;</a>
        <div class='line'></div>
        <div class='content'>
          <form id='updateData'>
            <input type='hidden' id='P_ID' name="P_ID" data-update="db"/>
            <label for='USER_NAME' class='left mid'>用户账号</label> 
            <input type='text' id='USER_NAME' name='USER_NAME' class='left short' data-update="db" required="required" readonly="readonly"/>          
            <input type='hidden' id='U_ID' name='USER_ID'/>
            <input type='button' id="USER_LOV" class='left button pointer' data-pageframe="lov" data-reveal-id="lov" data-bg="lov-modal-bg" data-dismissmodalclass='close-lov' data-lovname="用户查询" data-queryurl="lov/getUserPage.do" data-jsontype="user" data-defaultquery="true" data-th=["用户id","用户账号","用户姓名"] data-td=["USER_ID","USER_NAME","DESCRIPTION"] data-selectname=["用户账号","用户姓名"] data-selectvalue=["USER_NAME","DESCRIPTION"] data-choose=[".USER_ID",".USER_NAME",".DESCRIPTION"] data-recid=["#U_ID","#USER_NAME","#USER_DESC"] value="···"/>
            <label for='USER_DESC' class='left'>用户姓名</label>
            <input type='text' id='USER_DESC' name='USER_DESC' data-update="db" required='required' class='left' readonly="readonly"/>
            <label for='FUNCTION_CODE' class='left mid'>功能编码</label> 
            <input type="text" id="FUNCTION_CODE" name="FUNCTION_CODE" data-update="db" class="left short" readonly="readonly"/>
            <input type='hidden' id='FUNCTION_ID' name='FUNCTION_ID' data-update="db"/>
            <input type='button' id="FUNCTION_LOV" class='left button pointer' data-pageframe="lov" data-reveal-id="lov" data-bg="lov-modal-bg" data-dismissmodalclass='close-lov' data-lovname="功能查询" data-queryurl="lov/getFuncPage.do" data-jsontype="func" data-defaultquery="true" data-th=["功能ID","功能编码","功能名称","描述"] data-td=["FUNC_ID","FUNC_CODE","FUNC_NAME","FUNC_DESC"] data-selectname=["功能编码","功能名称"] data-selectvalue=["FUNCTION_CODE","FUNCTION_NAME"] data-choose=[".FUNC_ID",".FUNC_CODE",".FUNC_NAME"] data-recid=["#FUNCTION_ID","#FUNCTION_CODE","#FUNCTION_NAME"] value="···"/>  
            <label for='FUNCTION_NAME' class='left'>功能名称</label> 
            <input type="text" id="FUNCTION_NAME" name="FUNCTION_NAME" data-update="db" class="left"  readonly="readonly"/>
            <br style="clear:both"/>
            <label for='START_DATE' class='left'>启用日期</label>
            <input type='text' id='START_DATE' name='START_DATE' data-update="db" data-datatype="date" required='required' class='left'/>
            <label for='END_DATE' class='left'>失效日期</label>
            <input type='text' id='END_DATE' name='END_DATE' data-update="db" data-datatype="date" class='left'/>  
            <br style="clear:both"/>
            <label for="INSERT_FLAG" class='left checkbox'>新增</label>
            <input type="checkbox" id="INSERT_FLAG" name="INSERT_FLAG" class="left checkbox"/>
            <label for="UPDATE_FLAG" class='left checkbox'>更新</label>
            <input type="checkbox" id="UPDATE_FLAG" name="UPDATE_FLAG" class="left checkbox"/>
            <label for="APPROVE_FLAG" class='left checkbox'>审批</label>
            <input type="checkbox" id="APPROVE_FLAG" name="APPROVE_FLAG" class="left checkbox"/>
            <label for="FINAL_APPROVE_FLAG" class='left checkbox'>终审</label>
            <input type="checkbox" id="FINAL_APPROVE_FLAG" name="FINAL_APPROVE_FLAG" class="left checkbox"/>
            <label for="DOWNLOAD_FLAG" class='left checkbox'>下载</label>
            <input type="checkbox" id="DOWNLOAD_FLAG" name="DOWNLOAD_FLAG" class="left checkbox"/>
          </form>
        </div>
        <div class='foot'>       
          <button class="right update_confirm pointer" data-type="update" data-crudtype="update" data-pageframe="ui" data-updateurl="perm/updateFP.do">提交更新</button>
          <button class="right update_confirm pointer" data-type="insert" data-crudtype="insert" data-pageframe="ui" data-inserturl="perm/insertFP.do">新增</button>
        </div>    
      </div> 
      <!-- 更新/新增用户区域 end -->
     
      <!-- 条件查询区域 start -->
      <div id='query' class='query_frame'>     
        <div class='title pointer'>      
          <span><i class="fa fa-expeditedssl"></i>&nbsp;功能权限查询</span>
        </div>
        <a class="close-query-frame" data-type="close">&#215;</a>
        <div class='line'></div>
        <div class='content'>
          <form>
            <label for='USER_NAME_Q' class='left mid'>用户账号:</label> 
            <input type='text' id='USER_NAME_Q' name='USER_NAME' class='left mid' data-modify='true' data-pageframe="query" data-validurl='lov/validUserName.do' data-queryurl='lov/getUserId.do' data-lovbtn='USER_LOV_Q' data-hiddenid=["USER_ID_Q","DESCRIPTION_Q"] data-hiddenval=["USER_ID","DESCRIPTION"] data-param="username" />          
            <input type='hidden' id='USER_ID_Q' name='USER_ID'/>
            <input type='button' id="USER_LOV_Q" class='left button pointer' data-pageframe="lov" data-reveal-id="lov" data-bg="lov-modal-bg" data-dismissmodalclass='close-lov' data-lovname="发件人查询" data-queryurl="lov/getUserPage.do" data-jsontype="user" data-defaultquery="true" data-th=["用户id","发件账号","发件人"] data-td=["USER_ID","USER_NAME","DESCRIPTION"] data-selectname=["发件账号","发件人"] data-selectvalue=["USER_NAME","DESCRIPTION"] data-choose=[".USER_ID",".USER_NAME",".DESCRIPTION"] data-recid=["#USER_ID_Q","#USER_NAME_Q","#DESCRIPTION_Q"] value="···"/>
            <label for='DESCRIPTION_Q' class='left mid'>用户名:</label> 
            <input type='text' id='DESCRIPTION_Q' name="DESCRIPTION" class="long" readonly="readonly"/> 
            <label for='FUNCTION_CODE_Q' class='left mid'>功能编码:</label> 
            <input type="text" id="FUNCTION_CODE_Q" name="FUNCTION_CODE" data-update="db" class="left mid" data-modify="true" data-pageframe="query" data-validurl="lov/validFuncCode.do" data-queryurl="lov/getFuncId.do" data-lovbtn="FUNCTION_LOV" data-hiddenid=["FUNCTION_ID_Q","FUNCTION_NAME_Q"] data-hiddenval=["FUNCTION_ID","FUNCTION_NAME"] data-param="funccode"/>
            <input type='hidden' id='FUNCTION_ID_Q' name='FUNCTION_ID' data-update="db"/>
            <input type='button' id="FUNCTION_LOV_Q" class='left button pointer' data-pageframe="lov" data-reveal-id="lov" data-bg="lov-modal-bg" data-dismissmodalclass='close-lov' data-lovname="功能查询" data-queryurl="lov/getFuncPage.do" data-jsontype="func" data-defaultquery="true" data-th=["功能ID","功能编码","功能名称","描述"] data-td=["FUNC_ID","FUNC_CODE","FUNC_NAME","FUNC_DESC"] data-selectname=["功能编码","功能名称"] data-selectvalue=["FUNCTION_CODE","FUNCTION_NAME"] data-choose=[".FUNC_ID",".FUNC_CODE",".FUNC_NAME"] data-recid=["#FUNCTION_ID_Q","#FUNCTION_CODE_Q","#FUNCTION_NAME_Q"] value="···"/>  
            <label for='FUNCTION_NAME_Q' class='left mid'>功能名称:</label> 
            <input type="text" id="FUNCTION_NAME_Q" name="FUNCTION_NAME" data-update="db" class="left long"  readonly="readonly"/>
            <br style="clear:both"/>
            <label for="START_DATE_F" class="left mid">启用日期:</label>
            <input type="text" id="START_DATE_F"  name="START_DATE_F" class="left long" data-datatype="date" placeholder="起始启用日期"/>
            <input type="text" id="START_DATE_T"  name="START_DATE_T" class="left long" data-datatype="date" placeholder="截止启用日期"/>
            <br style="clear:both"/>
            <label for="END_DATE_F" class="left mid">失效日期:</label>
            <input type="text" id="END_DATE_F" name="END_DATE_F" class="left long" data-datatype="date" placeholder="起始失效日期"/>
            <input type="text" id="END_DATE_T" name="END_DATE_T" class="left long" data-datatype="date" placeholder="截止失效日期"/>
          </form>
        </div>
        <div class='foot'>             
          <button class="right pointer"  data-buttonframe="table" data-crudtype="query" data-pageframe="query">权限查询</button>
        </div> 
      </div>
      <!-- 条件查询区域 end -->    
      
      <!-- 用户信息存放区域 start -->
      <input type="hidden" id="USER_ID" value="${USER_ID}"/>  
      <input type="hidden" id="INTERACT_CODE" value="FUNC_PERM"/> 
      <input type="hidden" id="HEADER_ID" value=""/> 
      <!-- 用户信息存放区域 end -->  
    </div>   
    <script>       
        $(function() {
            //设置拖拽
            $("#ui").draggable();
    		$("#detail").draggable();
    		$("#query").draggable();
			$.fn.afterReveal = function(){
    		    $('#START_DATE').val(new Date().format('yyyy-MM-dd hh:mm:ss'));
    		    //$('label[for="USER_NAME"]').click();
    		}
    		
    		//初始化CRUD和LOV条件查询
    		$().crudListener();	
    		$().revealListener();   	 
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
                   	 	$('.USER_NAME',$('#tb tr:eq('+(i+1)+')')).html(data.rows[i].USER_NAME); 
                    	$('.USER_DESC',$('#tb tr:eq('+(i+1)+')')).html(data.rows[i].USER_DESC);
                    	$('.FUNCTION_CODE',$('#tb tr:eq('+(i+1)+')')).html(data.rows[i].FUNCTION_CODE); 
                    	$('.FUNCTION_NAME',$('#tb tr:eq('+(i+1)+')')).html(data.rows[i].FUNCTION_NAME);
                    	if(data.rows[i].INSERT_FLAG=='Y'){
                    	    $('.INSERT_FLAG',$('#tb tr:eq('+(i+1)+')')).html('<i class="fa fa-check green"></i>');
                    	}else{
                    	    $('.INSERT_FLAG',$('#tb tr:eq('+(i+1)+')')).html('<i class="fa fa-close red"></i>');
                    	}  
                    	if(data.rows[i].UPDATE_FLAG=='Y'){
                    	    $('.UPDATE_FLAG',$('#tb tr:eq('+(i+1)+')')).html('<i class="fa fa-check green"></i>');
                    	}else{
                    	    $('.UPDATE_FLAG',$('#tb tr:eq('+(i+1)+')')).html('<i class="fa fa-close red"></i>');
                    	}
                    	if(data.rows[i].APPROVE_FLAG=='Y'){
                    	    $('.APPROVE_FLAG',$('#tb tr:eq('+(i+1)+')')).html('<i class="fa fa-check green"></i>');
                    	}else{
                    	    $('.APPROVE_FLAG',$('#tb tr:eq('+(i+1)+')')).html('<i class="fa fa-close red"></i>');
                    	}
                    	if(data.rows[i].FINAL_APPROVE_FLAG=='Y'){
                    	    $('.FINAL_APPROVE_FLAG',$('#tb tr:eq('+(i+1)+')')).html('<i class="fa fa-check green"></i>');
                    	}else{
                    	    $('.FINAL_APPROVE_FLAG',$('#tb tr:eq('+(i+1)+')')).html('<i class="fa fa-close red"></i>');
                    	}
                    	if(data.rows[i].DOWNLOAD_FLAG=='Y'){
                    	    $('.DOWNLOAD_FLAG',$('#tb tr:eq('+(i+1)+')')).html('<i class="fa fa-check green"></i>');
                    	}else{
                    	    $('.DOWNLOAD_FLAG',$('#tb tr:eq('+(i+1)+')')).html('<i class="fa fa-close red"></i>');
                    	} 
                    	$('.START_DATE',$('#tb tr:eq('+(i+1)+')')).html(data.rows[i].START_DATE);   
                    	$('.END_DATE',$('#tb tr:eq('+(i+1)+')')).html(data.rows[i].END_DATE); 
                    	$('.P_ID',$('#tb tr:eq('+(i+1)+')')).html(data.rows[i].P_ID); 
                	}
                	$().crudListener();
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
				$('#P_ID').val(data.rows[0].P_ID); 
				$('#U_ID').val(data.rows[0].USER_ID);
       	        $('#USER_NAME').val(data.rows[0].USER_NAME);
       	        $('#USER_DESC').val(data.rows[0].USER_DESC);
       	        $('#FUNCTION_ID').val(data.rows[0].FUNCTION_ID);
       	        $('#FUNCTION_CODE').val(data.rows[0].FUNCTION_CODE);
       	        $('#FUNCTION_NAME').val(data.rows[0].FUNCTION_NAME);
       	        $('#START_DATE').val(data.rows[0].START_DATE);
       	        $('#END_DATE').val(data.rows[0].END_DATE); 
       	        if(data.rows[0].INSERT_FLAG=='Y'){
       	            $('#INSERT_FLAG').prop('checked',true);
       	        }  
       	        if(data.rows[0].UPDATE_FLAG=='Y'){
       	            $('#UPDATE_FLAG').prop('checked',true);
       	        } 
       	        if(data.rows[0].APPROVE_FLAG=='Y'){
       	            $('#APPROVE_FLAG').prop('checked',true);
       	        } 	        
       	        if(data.rows[0].FINAL_APPROVE_FLAG=='Y'){
       	            $('#FINAL_APPROVE_FLAG').prop('checked',true);
       	        } 
       	        if(data.rows[0].DOWNLOAD_FLAG=='Y'){
       	            $('#DOWNLOAD_FLAG').prop('checked',true);
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
    <script type="text/javascript" src="plugin/js/jQuery.reveal.js"></script> 
    <script type="text/javascript" src="plugin/js/jQuery.page.js"></script>
    <script type="text/javascript" src="plugin/js/jQuery.lov.js"></script> 
    <script type="text/javascript" src="plugin/js/jQuery.crud.js"></script> 
    <script type="text/javascript" src="plugin/js/jQuery.rowdefine.js"></script>
    <script type="text/javascript" src="plugin/js/jQuery.irr.orderby.js"></script>	
    <script type="text/javascript" src="plugin/js/jQuery.irr.init.js"></script>	    
  </body>
</html>