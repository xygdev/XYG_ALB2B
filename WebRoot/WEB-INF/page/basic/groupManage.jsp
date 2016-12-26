<%@ page contentType="text/html;charset=utf-8" language="java" pageEncoding="utf-8"
	      import="java.util.*" %>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML>
<html>
  <head>
    <title>工作组管理</title>
    <base href="<%=basePath%>"> 
    <meta http-equiv="content-type" content="text/html;charset=gb2312">
	<link rel="stylesheet" href="plugin/css/font-awesome.min.css">
	<link rel="stylesheet" href="plugin/css/jquery-ui.min.css">
	<link rel="stylesheet" type="text/css" href="plugin/css/public.css">
	<link rel="stylesheet" type="text/css" href="plugin/css/basic/group.css">
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
        <table id="gHeader" data-table="GroupHeader">
          <tr>
            <th class="GROUP_CODE" data-column="db">工作组编码</th>
            <th class="GROUP_NAME" data-column="db">工作组名称</th>
     	    <th class="DESCRIPTION" data-column="db">描述</th>
     	    <th class="ACTION" data-column="normal">操作</th> 
     	    <th class="GROUP_ID" style="display:none" data-column="hidden">&nbsp;</th>
     	  </tr>
     	  <tr>
     	    <td class="GROUP_CODE" data-column="db"></td>
            <td class="GROUP_NAME" data-column="db"></td>
     	    <td class="DESCRIPTION" data-column="db"></td>
     	    <td class="ACTION" data-column="normal">
     	      <i class="fa fa-pencil fa-fw update pointer hidden" title="更新工作组" data-show="true" data-reveal-id="ui" data-key="true" data-dismissmodalclass="close-ui-frame" data-crudtype="pre-update" data-preupdateurl="group/preUpdateGroupHeader.do" data-type="update" data-updateparam=["GROUP_ID",".GROUP_ID"]></i>
     	      &nbsp;
     	      <i class="fa fa fa-eye view pointer show_detail hidden" title="工作组明细"  data-show="true" data-reveal-id="detail" data-dismissmodalclass="close-detail-frame" ></i>
     	    </td>
     	    <td class="GROUP_ID" style="display:none" data-column="hidden">&nbsp;</td>
     	  </tr>
        </table>
      </div>
      <!-- 主表格区域 end -->
   
      <!-- 主表格按钮区域 start -->
      <div class="table_button" id="table" data-table="GroupHeader">
        <div class="setting">
          <i class="fa fa-cog pointer" title="表格设置" data-reveal-id="setting" data-dismissmodalclass="close-setting"></i>
        </div>
        <div class="setting">
          <i class="fa fa-search pointer" title="条件查询" data-reveal-id="query" data-key="true" data-dismissmodalclass="close-query-frame"></i>
        </div>
        <div class="setting">
          <i class="fa fa-plus-circle pointer" title="新增工作组" data-reveal-id="ui" data-key="true" data-dismissmodalclass="close-ui-frame" data-crudtype="pre-insert" data-type="insert"></i>
        </div>
        <div class="setting">
          <i id='refresh' class="fa fa-refresh pointer" title="刷新数据" data-pagetype='refresh' data-pageframe="table"></i>
        </div>
        <div id="setting">
          <!-- 设置菜单区域 start -->
          <jsp:include page="../public/setting.jsp" >
			<jsp:param name="rdtable" value="#gHeader" />
			<jsp:param name="odtable" value="#gHeader" />
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
          <input type="hidden" data-type="orderby" id="ORDER_BY" value="GROUP_ID ASC"/> 
          <input type="hidden" data-type="cond"/>
          <input type="hidden" data-type="url" value="group/getGroupHeaderPage.do"/>
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
          <span data-type="update"><i class="fa fa-users fa-1x" aria-hidden="true"></i>&nbsp;更新工作组</span>
          <span data-type="insert"><i class="fa fa-users fa-1x" aria-hidden="true"></i>&nbsp;新增工作组</span>
        </div>
        <a class="close-ui-frame" data-type="close">&#215;</a>
        <div class='line'></div>
        <div class='content'>
          <form id='updateData'>
            <input type='hidden' id='GROUP_ID' name="GROUP_ID" data-update="db"/>
            <label for='GROUP_CODE' class='left'>工作组编码</label>
            <input type='text' id='GROUP_CODE' name='GROUP_CODE' data-update="db" required='required' class='left'/>
            <label for='GROUP_NAME' class='left'>工作组名称</label>
            <input type='text' id='GROUP_NAME' name='GROUP_NAME' data-update="db" required='required' class='left'/>
            <label for='DESCRIPTION' class='left'>工作组描述</label>
            <input type='text' id='DESCRIPTION' name='DESCRIPTION' data-update="db" class='left long'/>
          </form>
        </div>
        <div class='foot'>       
          <button class="right update_confirm pointer" data-keyup="enter" data-type="update" data-crudtype="update" data-pageframe="ui" data-updateurl="group/updateGroupHeader.do">提交更新</button>
          <button class="right update_confirm pointer" data-keyup="enter" data-type="insert" data-crudtype="insert" data-pageframe="ui" data-inserturl="group/insertGroupHeader.do">新增</button>
        </div>    
      </div> 
      <!-- 更新/新增用户区域 end -->
     
      <!-- 条件查询区域 start -->
      <div id='query' class='query_frame'>     
        <div class='title'>      
          <span><i class="fa fa-users"></i>&nbsp;工作组查询</span>
        </div>
        <a class="close-query-frame" data-type="close">&#215;</a>
        <div class='line'></div>
        <div class='content'>
          <form>
            <label for='GROUP_NAME_Q' class='left mid'>组名称:</label> 
            <input type="text" id="GROUP_NAME_Q" name="GROUP_NAME" data-update="db" class="left mid" data-modify='true' data-pageframe="query" data-validurl='lov/validGroupName.do' data-queryurl='lov/getGroupId.do' data-lovbtn='GROUP_LOV_Q' data-hiddenid=["GROUP_ID_Q,GROUP_CODE_Q"] data-hiddenval=["GROUP_ID,GROUP_CODE"] data-param="respname"/>
            <input type='hidden' id='GROUP_ID_Q' name='GROUP_ID' data-update="db"/>
            <input type="button" id="GROUP_LOV_Q" class="left button pointer" data-pageframe="lov" data-reveal-id="lov" data-bg="lov-modal-bg" data-dismissmodalclass='close-lov' data-lovname="工作组查询" data-queryurl="lov/getGroupPage.do" data-jsontype="group" data-defaultquery="true" data-th=["工作组ID","工作组编码","工作组名称","描述"] data-td=["LOV_GROUP_ID","LOV_GROUP_CODE","LOV_GROUP_NAME","LOV_DESCRIPTION"] data-selectname=["工作组编码","工作组名称"] data-selectvalue=["GROUP_CODE","GROUP_NAME"] data-choose=[".LOV_GROUP_ID",".LOV_GROUP_CODE",".LOV_GROUP_NAME"] data-recid=["#GROUP_ID_Q","#GROUP_CODE_Q","#GROUP_NAME_Q"]  value="···"/> 
            <label for='GROUP_CODE_Q' class='left mid'>组编码:</label> 
            <input type="text" id="GROUP_CODE_Q" name="GROUP_CODE" data-update="db" class="left long" readonly="readonly"/>
          </form>
        </div>
        <div class='foot'>             
          <button class="right pointer"  data-buttonframe="table" data-crudtype="query" data-keyup="enter" data-pageframe="query">工作组查询</button>
        </div> 
      </div>
      <!-- 条件查询区域 end --> 
      
<!----------------------------------------------菜单明细-------------------------------------------------------- -->      
 
      
      <div class="detail_frame" id="detail">
        <div class='title pointer'>      
          <span><i class="fa fa-users"></i>&nbsp;工作组明细</span>
        </div>
        <a class="close-detail-frame" data-type="close">&#215;</a>
        <div class="detail_header">
          <input type="hidden" id="GROUP_ID_LINES" />
          <label class="long" for="GROUP_CODE_LINES">工作组编码</label>
          <input type="text" id="GROUP_CODE_LINES" class="mid" readonly="readonly"/>
          <label class="long" for="GROUP_NAME_LINES">工作组名称</label>
          <input type="text" id="GROUP_NAME_LINES" class="mid" readonly="readonly"/>
          <br style="clear:both"/>
        </div>
        
        <!-- 工作组明细表格区域 start -->
        <div class='detail_table'>
          <table id="gLine" data-table="GroupLine">
            <tr>
              <th class="GROUP_SEQUENCE" data-column="db">序号</th>
              <th class="SUB_GROUP_CODE" data-column="db">子工作组编码</th>
              <th class="SUB_GROUP_NAME" data-column="db">子工作组名称</th>
     	      <th class="ENABLED" data-column="db">是否启用</th>
     	      <th class="ACTION" data-column="normal">操作</th> 
     	      <th class="GROUP_ID" style="display:none" data-column="hidden">&nbsp;</th>
     	    </tr>
     	    <tr>
     	      <td class="GROUP_SEQUENCE" data-column="db"></td>
              <td class="SUB_GROUP_CODE" data-column="db"></td>
              <td class="SUB_GROUP_NAME" data-column="db"></td>
     	      <td class="ENABLED" data-column="db"></td>
     	      <td class="ACTION" data-column="normal">
     	        <i class="fa fa-pencil fa-fw update pointer" data-show="true" data-reveal-id="detail_ui" data-key="true" data-bg="detail-modal-bg" data-dismissmodalclass="close-detail-ui-frame" data-crudtype="pre-update" data-preupdateurl="group/preUpdateGroupLine.do" data-type="update" data-updateparam=["GROUP_SEQUENCE",".GROUP_SEQUENCE"] data-func="$().getGroupId();"></i>
     	      </td>
     	      <td class="GROUP_ID" style="display:none" data-column="hidden">&nbsp;</td>     	     
     	    </tr>
          </table>
        </div>
        <!-- 工作组明细表格区域 end --> 
        
        <div class="table_button" id="sub_table" data-table="GroupLine">
          <div class="setting none">
            <i class="fa fa-search pointer" data-reveal-id="query" data-dismissmodalclass="close-query-frame"></i>
          </div>
          <div class="setting">
            <i class="fa fa-plus-circle pointer" data-reveal-id="detail_ui" data-key="true" data-bg="detail-modal-bg" data-dismissmodalclass="close-detail-ui-frame" data-crudtype="pre-insert" data-type="insert" data-func="$().autoAddSeq();"></i>
          </div>
          <div class="setting">
            <i id='sub_refresh' class="fa fa-refresh pointer" data-pagetype='refresh' data-pageframe="sub_table" data-func="$().setParam();"></i>
          </div>
          <div>
            <!-- 工作组明细分页按钮区域 start -->
            <jsp:include page="../public/pageArrow.jsp" >
			  <jsp:param name="pageframe" value="sub_table" />
			  <jsp:param name="func" value="$().setParam();" />
		    </jsp:include>
            <!-- 工作组明细分页按钮区域 end -->
            <input type="hidden" data-type="size" value="5"/>
            <input type="hidden" data-type="number" value="1"/>
            <input type="hidden" data-type="orderby" value="GROUP_SEQUENCE ASC"/> 
            <input type="hidden" data-type="cond"/>
            <input type="hidden" data-type="url" value="group/getGroupLinePage.do"/>
            <input type="hidden" data-type="jsontype" value="subtable"/> 
          </div>
        </div>
      </div>
    </div>       
    
    <!-- 工作组明细新增/更新区域 start -->
    <div id='detail_ui' class='detail_update_frame'>     
      <div class='title pointer'>      
        <span data-type="update"><i class="fa fa-users fa-1x" aria-hidden="true"></i>&nbsp;更新工作组明细</span>
        <span data-type="insert"><i class="fa fa-users fa-1x" aria-hidden="true"></i>&nbsp;新增工作组明细</span>
      </div>
      <a class="close-detail-ui-frame" data-type="close">&#215;</a>
      <div class='line'></div>
      <div class='content'>
        <form id='updateDetailData'>
          <input type='hidden' id='GROUP_ID_DETAIL' name="GROUP_ID" data-update="db" value=""/>
          <label for='GROUP_SEQUENCE' class='left'>工作组序号</label> 
          <input type="text" id="GROUP_SEQUENCE" name="GROUP_SEQUENCE" data-update="db" class="left long" required="required"/>
          <label for='GROUP_ENABLED_FLAG' class='left'>是否启用</label> 
          <select class='left long' id='GROUP_ENABLED_FLAG' name='ENABLED_FLAG' data-update="db" data-notnull="true" required='required' data-listurl="list/getEnableFlag.do"></select> 
          <br style="clear:both"/>
          <label for='SUB_GROUP_CODE' class='left'>工作组编码</label> 
          <input type="text" id="SUB_GROUP_CODE" name="SUB_GROUP_CODE" data-update="db" class="left short" readonly="readonly" required='required'/>
          <input type='hidden' id='SUB_GROUP_ID' name='SUB_GROUP_ID' data-update="db"/>
          <input type="button" id="SUB_GROUP_LOV" class="left button pointer" data-pageframe="lov" data-reveal-id="lov" data-key="true" data-callback="detail_ui" data-bg="lov-modal-bg" data-dismissmodalclass='close-lov' data-lovname="工作组查询" data-queryurl="lov/getGroupPage.do" data-jsontype="group" data-defaultquery="true" data-th=["工作组ID","工作组编码","工作组名称","描述"] data-td=["LOV_GROUP_ID","LOV_GROUP_CODE","LOV_GROUP_NAME","LOV_DESCRIPTION"] data-selectname=["工作组编码","工作组名称"] data-selectvalue=["GROUP_CODE","GROUP_NAME"] data-choose=[".LOV_GROUP_ID",".LOV_GROUP_CODE",".LOV_GROUP_NAME"] data-recid=["#SUB_GROUP_ID","#SUB_GROUP_CODE","#SUB_GROUP_NAME"]  value="···"/> 
          <label for='SUB_GROUP_NAME' class='left'>工作组名称</label> 
          <input type="text" id="SUB_GROUP_NAME" name="SUB_GROUP_NAME" data-update="db" class="left long" readonly="readonly"/>
        </form>
      </div>
      <div class='foot'>       
        <button class="right update_confirm pointer" data-keyup="enter" data-type="update" data-crudtype="update" data-pageframe="detail_ui" data-updateurl="group/updateGroupLine.do" data-refresh="sub_refresh">提交更新</button>
        <button class="right update_confirm pointer" data-keyup="enter" data-type="insert" data-crudtype="insert" data-pageframe="detail_ui" data-inserturl="group/insertGroupLine.do" data-refresh="sub_refresh">新增</button>
      </div>    
    </div> 
    <!-- 工作组明细新增/更新区域 end -->  
<!----------------------------------------------工作组明细-------------------------------------------------------- -->       
      
    <!-- 用户信息存放区域 start -->
    <input type="hidden" id="USER_ID" value="${USER_ID}"/>  
    <input type="hidden" id="INTERACT_CODE" value="GROUP_MANAGE"/> 
    <input type="hidden" id="HEADER_ID" value=""/> 
    <!-- 用户信息存放区域 end -->  
           
    <script>       
        $(function() {
            //设置拖拽
            $("#ui").draggable({ handle: ".title"});
    		$("#detail").draggable({ handle: ".title"});
    		$("#detail_ui").draggable({ handle: ".title"});
    		$("#query").draggable({ handle: ".title"}); 
    		
    		//初始化CRUD和LOV条件查询
    		$().crudListener();	
    		$().revealListener(); 
    		
    		$.fn.setParam = function(){
    		    groupId=$('#GROUP_ID_LINES').val();
    		    param=param+'&GROUP_ID='+groupId;
    		}	
    		
    		$.fn.detailShow = function(){
    		    $('.show_detail').off('click');
    		    $('.show_detail').on('click',function(){
    		        tr=$(this).parent().parent();
    		        groupId=tr.children('.GROUP_ID').text();
    		    	groupCode=tr.children('.GROUP_CODE').text();
    		    	groupName=tr.children('.GROUP_NAME').text();
    		    	$('.detail_header input').val('');
    		    	$('#sub_table input[data-type="number"]').val('1');
    		    	$('#GROUP_ID_LINES').val(groupId);
    		    	$('#GROUP_CODE_LINES').val(groupCode);
    		    	$('#GROUP_NAME_LINES').val(groupName);
    		    	$('#sub_refresh').click();
    		    });    		   
    		} 	
    		
    		$.fn.autoAddSeq = function(){
    		    groupId = $('#GROUP_ID_LINES').val();
    		    param = 'GROUP_ID='+groupId;
    		    $.ajax({
				    type:'post', 
				    data:param,
				    url:'group/getAutoAddSeq.do',
				    dataType:'json',
				    success: function (data) {
				        $('#GROUP_ID_DETAIL').val(groupId);
				        $('#GROUP_SEQUENCE').val(data.rows[0].MENU_SEQUENCE);
				    },
				    error: function () {
				    	alert('获取数据失败');
				    }			
				});	
    		}	
    		
    		$.fn.getGroupId = function(){
    		    groupId = $('#GROUP_ID_LINES').val();
    		    param = 'GROUP_ID='+groupId+'&';
    		}
    		
        });
        
        jQuery.json={
        	getContent:function(data,JSONtype){  
        	    if(JSONtype=='table'){
        	        for(i=0;i<(pageMaxRow-pageMinRow+1);i++){
                    	$('.GROUP_CODE',$('#gHeader tr:eq('+(i+1)+')')).html(data.rows[i].GROUP_CODE); 
                   	 	$('.GROUP_NAME',$('#gHeader tr:eq('+(i+1)+')')).html(data.rows[i].GROUP_NAME); 
                    	$('.DESCRIPTION',$('#gHeader tr:eq('+(i+1)+')')).html(data.rows[i].DESCRIPTION);  
                    	$('.GROUP_ID',$('#gHeader tr:eq('+(i+1)+')')).html(data.rows[i].GROUP_ID); 
                	}
                	$().crudListener();
                	$().detailShow();
                	$().revealListener(); 
        	    }else if(JSONtype=='subtable'){
        	        for(i=0;i<(pageMaxRow-pageMinRow+1);i++){
        	            $('.SUB_GROUP_CODE',$('#gLine tr:eq('+(i+1)+')')).html(data.rows[i].SUB_GROUP_CODE); 
                   	 	$('.SUB_GROUP_NAME',$('#gLine tr:eq('+(i+1)+')')).html(data.rows[i].SUB_GROUP_NAME); 
                    	$('.GROUP_ID',$('#gLine tr:eq('+(i+1)+')')).html(data.rows[i].GROUP_ID);   
                    	$('.GROUP_SEQUENCE',$('#gLine tr:eq('+(i+1)+')')).html(data.rows[i].GROUP_SEQUENCE); 
                    	$('.ENABLED',$('#gLine tr:eq('+(i+1)+')')).html(data.rows[i].ENABLED);
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
        	    }	            	   	
       	    },	
       	    getUpdateJSON:function(data,pageframe){   
       	        if(pageframe=='ui'){
       	            $('#GROUP_ID').val(data.rows[0].GROUP_ID);
       	        	$('#GROUP_CODE').val(data.rows[0].GROUP_CODE);
       	        	$('#GROUP_NAME').val(data.rows[0].GROUP_NAME);
       	        	$('#DESCRIPTION').val(data.rows[0].DESCRIPTION);
       	        }else if(pageframe='detail_ui'){
       	            $('#GROUP_ID_DETAIL').val(data.rows[0].GROUP_ID);
       	            $('#GROUP_SEQUENCE').val(data.rows[0].GROUP_SEQUENCE);
       	            $('#SUB_GROUP_ID').val(data.rows[0].SUB_GROUP_ID);
       	            $('#SUB_GROUP_CODE').val(data.rows[0].SUB_GROUP_CODE);
       	            $('#SUB_GROUP_NAME').val(data.rows[0].SUB_GROUP_NAME);
       	            $('#GROUP_ENABLED_FLAG').val(data.rows[0].ENABLED_FLAG);
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