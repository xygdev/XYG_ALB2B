<%@ page contentType="text/html;charset=utf-8" language="java" pageEncoding="utf-8"
	      import="java.util.*" %>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML>
<html>
  <head>
    <title>库存设置</title>
    <base href="<%=basePath%>"> 
    <meta http-equiv="content-type" content="text/html;charset=gb2312">
	<link rel="stylesheet" href="plugin/css/font-awesome.min.css">
	<link rel="stylesheet" href="plugin/css/jquery-ui.min.css">
	<link rel="stylesheet" type="text/css" href="plugin/css/public.css">
	<link rel="stylesheet" type="text/css" href="plugin/css/basic/onhandSet.css">
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
        <table id="tb" data-table="OnhandSet">
          <tr>
            <th class="ORG_NAME" data-column="db">库存组织</th>
     	    <th class="GREATER_BOX" data-column="db">库存上限值</th>
     	    <th class="DISPLAY_BOX" data-column="db">库存显示值</th>
     	    <th class="ACTION" data-column="normal">操作</th> 
     	    <th class="ORGANIZATION_ID" style="display:none" data-column="hidden">&nbsp;</th>
     	  </tr>
     	  <tr>
     	    <td class="ORG_NAME" data-column="db"></td>
     	    <td class="GREATER_BOX" data-column="db"></td>
     	    <td class="DISPLAY_BOX" data-column="db"></td>
     	    <td class="ACTION" data-column="normal">
     	      <i class="fa fa-pencil fa-fw update pointer hidden" title="更新库存设置" data-show="true" data-reveal-id="ui" data-dismissmodalclass="close-ui-frame" data-crudtype="pre-update" data-preupdateurl="inv/preUpdateOS.do" data-type="update" data-updateparam=["ORGANIZATION_ID",".ORGANIZATION_ID"]></i>
     	    </td>
     	    <td class="ORGANIZATION_ID" style="display:none" data-column="hidden">&nbsp;</td>
     	  </tr>
        </table>
      </div>
      <!-- 主表格区域 end -->
   
      <!-- 主表格按钮区域 start -->
      <div class="table_button" id="table" data-table="OnhandSet">
        <div class="setting">
          <i class="fa fa-cog pointer" title="表格设置" data-reveal-id="setting" data-dismissmodalclass="close-setting"></i>
        </div>
        <div class="setting">
          <i class="fa fa-search pointer" title="条件查询" data-reveal-id="query" data-dismissmodalclass="close-query-frame"></i>
        </div>
        <div class="setting">
          <i class="fa fa-plus-circle pointer" title="新增库存设置" data-reveal-id="ui" data-dismissmodalclass="close-ui-frame" data-crudtype="pre-insert" data-type="insert"></i>
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
          <input type="hidden" data-type="orderby" id="ORDER_BY" value="ORGANIZATION_ID DESC"/> 
          <input type="hidden" data-type="cond"/>
          <input type="hidden" data-type="url" value="inv/getOnhandSetPage.do"/>
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
          <span data-type="update"><i class="fa fa-truck fa-1x" aria-hidden="true"></i>&nbsp;更新库存设定</span>
          <span data-type="insert"><i class="fa fa-truck fa-1x" aria-hidden="true"></i>&nbsp;新增库存设定</span>
        </div>
        <a class="close-ui-frame" data-type="close">&#215;</a>
        <div class='line'></div>
        <div class='content'>
          <form id='updateData'>
            <label for='ORGANIZATION_CODE' class='left mid'>库存编码</label> 
            <input type='text' id='ORGANIZATION_CODE' name='ORGANIZATION_CODE' class='left short' data-update="db" required="required" readonly="readonly"/>          
            <input type='hidden' id='ORGANIZATION_ID' name='ORGANIZATION_ID'/>
            <input type='button' id="ORGANIZATION_LOV" class='left button pointer' data-pageframe="lov" data-reveal-id="lov" data-bg="lov-modal-bg" data-dismissmodalclass='close-lov' data-lovname="库存组织查询" data-queryurl="lov/getOrganPage.do" data-jsontype="organ" data-defaultquery="true" data-th=["库存id","库存编码","库存组织","产业"] data-td=["ORGANIZATION_ID","ORGANIZATION_CODE","ORGANIZATION_NAME","GLASS_INDUSTRY"] data-selectname=["库存编码","库存组织"] data-selectvalue=["ORGANIZATION_CODE","ORGANIZATION_NAME"] data-choose=[".ORGANIZATION_ID",".ORGANIZATION_CODE",".ORGANIZATION_NAME"] data-recid=["#ORGANIZATION_ID","#ORGANIZATION_CODE","#ORGANIZATION_NAME"] value="···"/>
            <label for='ORGANIZATION_NAME' class='left'>库存组织</label>
            <input type='text' id='ORGANIZATION_NAME' name='ORGANIZATION_NAME' data-update="db" required='required' class='left' readonly="readonly"/>
            <br style="clear:both"/>
            <label for='GREATER_BOX' class='left'>上限值</label>
            <input type='text' id='GREATER_BOX' name='GREATER_BOX' data-update="db" required='required' class='left'/>
            <label for='DISPLAY_BOX ' class='left'>显示值</label>
            <input type='text' id='DISPLAY_BOX' name='DISPLAY_BOX' data-update="db" required='required' class='left'/>  
          </form>
        </div>
        <div class='foot'>       
          <button class="right update_confirm pointer" data-type="update" data-crudtype="update" data-pageframe="ui" data-updateurl="inv/updateOS.do">提交更新</button>
          <button class="right update_confirm pointer" data-type="insert" data-crudtype="insert" data-pageframe="ui" data-inserturl="inv/insertOS.do">新增</button>
        </div>    
      </div> 
      <!-- 更新/新增用户区域 end -->
     
      <!-- 条件查询区域 start -->
      <div id='query' class='query_frame'>     
        <div class='title pointer'>      
          <span><i class="fa fa-truck"></i>&nbsp;库存设定查询</span>
        </div>
        <a class="close-query-frame" data-type="close">&#215;</a>
        <div class='line'></div>
        <div class='content'>
          <form>
            <label for='ORGANIZATION_CODE_Q' class='left mid'>库存编码</label> 
            <input type='text' id='ORGANIZATION_CODE_Q' name='ORGANIZATION_CODE' class='left mid' data-update="db" data-modify="true" data-pageframe="query" data-validurl="lov/validOrganCode.do" data-queryurl="lov/getOrganId.do" data-lovbtn="ORGANIZATION_LOV_Q" data-hiddenid=["ORGANIZATION_ID_Q","ORGANIZATION_NAME_Q"] data-hiddenval=["ORGANIZATION_ID","ORGANIZATION_NAME"] data-param="organCode"/>          
            <input type='hidden' id='ORGANIZATION_ID_Q' name='ORGANIZATION_ID'/>
            <input type='button' id="ORGANIZATION_LOV_Q" class='left button pointer' data-pageframe="lov" data-reveal-id="lov" data-bg="lov-modal-bg" data-dismissmodalclass='close-lov' data-lovname="库存组织查询" data-queryurl="lov/getOrganPage.do" data-jsontype="organ" data-defaultquery="true" data-th=["库存id","库存编码","库存组织","产业"] data-td=["ORGANIZATION_ID","ORGANIZATION_CODE","ORGANIZATION_NAME","GLASS_INDUSTRY"] data-selectname=["库存编码","库存组织"] data-selectvalue=["ORGANIZATION_CODE","ORGANIZATION_NAME"] data-choose=[".ORGANIZATION_ID",".ORGANIZATION_CODE",".ORGANIZATION_NAME"] data-recid=["#ORGANIZATION_ID_Q","#ORGANIZATION_CODE_Q","#ORGANIZATION_NAME_Q"] value="···"/>
            <label for='ORGANIZATION_NAME_Q' class='left mid'>库存组织</label>
            <input type='text' id='ORGANIZATION_NAME_Q' name='ORGANIZATION_NAME' data-update="db" class='left long' readonly="readonly"/>    
          </form>
        </div>
        <div class='foot'>             
          <button class="right pointer"  data-buttonframe="table" data-crudtype="query" data-pageframe="query">设定查询</button>
        </div> 
      </div>
      <!-- 条件查询区域 end -->    
      
      <!-- 用户信息存放区域 start -->
      <input type="hidden" id="USER_ID" value="${USER_ID}"/>  
      <input type="hidden" id="INTERACT_CODE" value="ONHAND_SET"/> 
      <input type="hidden" id="HEADER_ID" value=""/> 
      <!-- 用户信息存放区域 end -->  
    </div>   
    <script>       
        $(function() {
            //设置拖拽
            $("#ui").draggable({ handle: ".title"});
    		$("#query").draggable({ handle: ".title"});
    		
    		//初始化CRUD和LOV条件查询
    		$().crudListener();	
    		$().revealListener(); 	 		
        });
        
        jQuery.json={
        	getContent:function(data,JSONtype){  
        	    if(JSONtype=='table'){
        	        for(i=0;i<(pageMaxRow-pageMinRow+1);i++){
                    	$('.ORG_NAME',$('#tb tr:eq('+(i+1)+')')).html(data.rows[i].ORG_NAME);  
                    	$('.GREATER_BOX',$('#tb tr:eq('+(i+1)+')')).html(data.rows[i].ONHAND_GREATER_BOX);   
                    	$('.DISPLAY_BOX',$('#tb tr:eq('+(i+1)+')')).html(data.rows[i].ONHAND_DISPLAY_BOX); 
                    	$('.ORGANIZATION_ID',$('#tb tr:eq('+(i+1)+')')).html(data.rows[i].ORGANIZATION_ID); 
                	}
                	$().crudListener();
                	$().revealListener(); 
        	    }else if(JSONtype=='organ'){
        	        if(pageMaxRow==0&&pageMinRow==0){
        	            console.log('no data');
        	        }else{
        	            for(i=0;i<(pageMaxRow-pageMinRow+1);i++){
       	            	    $('td:eq(0)',$('.contentbox tr:eq('+(i+1)+')')).html(data.rows[i].ORGANIZATION_ID);  
       	            	    $('td:eq(1)',$('.contentbox tr:eq('+(i+1)+')')).html(data.rows[i].ORGANIZATION_CODE);     	                    
       	            	    $('td:eq(2)',$('.contentbox tr:eq('+(i+1)+')')).html(data.rows[i].ORGANIZATION_NAME); 
       	            	    $('td:eq(3)',$('.contentbox tr:eq('+(i+1)+')')).html(data.rows[i].GLASS_INDUSTRY);    	               	        
       	            	} 
        	        }       	            	    
        	    }           	   	
       	    },	
       	    getUpdateJSON:function(data){   
       	        $('#ORGANIZATION_CODE').val(data.rows[0].ORG_CODE);
       	        $('#ORGANIZATION_NAME').val(data.rows[0].ORG_NAME);
       	        $('#ORGANIZATION_ID').val(data.rows[0].ORGANIZATION_ID);
       	        $('#GREATER_BOX').val(data.rows[0].ONHAND_GREATER_BOX);
       	        $('#DISPLAY_BOX').val(data.rows[0].ONHAND_DISPLAY_BOX);	        
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