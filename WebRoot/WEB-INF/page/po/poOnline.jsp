<%@ page contentType="text/html;charset=utf-8" language="java" pageEncoding="utf-8"
	      import="java.util.*" %>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML>
<html>
  <head>
    <title>PO订单</title>
    <base href="<%=basePath%>"> 
    <meta http-equiv="content-type" content="text/html;charset=gb2312">
	<link rel="stylesheet" href="plugin/css/font-awesome.min.css">
	<link rel="stylesheet" href="plugin/css/jquery-ui.min.css">
	<link rel="stylesheet" type="text/css" href="plugin/css/public.css">
	<link rel="stylesheet" type="text/css" href="plugin/css/po/poOnline.css">
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
        <table id="pHeader" data-table="PoHeader">
          <tr>
            <th class="PO_NUMBER" data-column="db">PO订单号</th>
            <th class="CUSTOMER_CONTRACT_NUMBER" data-column="db">客订单号</th>
     	    <th class="CURR_CODE" data-column="db">币种</th>
     	    <th class="PARTY_NAME" data-column="db">客户名称</th>
     	    <th class="ACCOUNT_NUMBER" data-column="db">客户账号</th>
     	    <th class="SALES_ORG_NAME" data-column="db">销售公司</th>
     	    <th class="ORGANIZATION_NAME" data-column="db">发货组织</th>
     	    <th class="STATUS_DESC" data-column="db">状态</th>
     	    <th class="REMARK" data-column="db">备注</th>
     	    <th class="ACTION" data-column="normal">操作</th> 
     	    <th class="BOOK_NAME" data-column="db" style="display:none">登记人</th>
     	    <th class="BOOK_DATE" data-column="db" style="display:none">登记时间</th>
     	    <th class="CHECK_NAME" data-column="db" style="display:none">审核人</th>
     	    <th class="CHECK_DATE" data-column="db" style="display:none">审核时间</th>
     	    <th class="APPROVE_NAME" data-column="db" style="display:none">终审人</th>
     	    <th class="APPROVE_DATE" data-column="db" style="display:none">终审时间</th>
     	    <th class="CANCEL_NAME" data-column="db" style="display:none">取消人</th>
     	    <th class="CANCEL_DATE" data-column="db" style="display:none">取消时间</th>
     	    <th class="PO_HEADER_ID" style="display:none" data-column="hidden">&nbsp;</th>
     	    <th class="SHIP_FROM_ORG_ID" style="display:none" data-column="hidden">&nbsp;</th>
     	    <th class="STATUS" style="display:none" data-column="hidden">&nbsp;</th>
     	  </tr>
     	  <tr>
     	    <td class="PO_NUMBER" data-column="db"></td>
            <td class="CUSTOMER_CONTRACT_NUMBER" data-column="db"></td>
     	    <td class="CURR_CODE" data-column="db"></td>
     	    <td class="PARTY_NAME" data-column="db"></td>
     	    <td class="ACCOUNT_NUMBER" data-column="db"></td>
     	    <td class="SALES_ORG_NAME" data-column="db"></td>
     	    <td class="ORGANIZATION_NAME" data-column="db"></td>
     	    <td class="STATUS_DESC" data-column="db"></td>
     	    <td class="REMARK" data-column="db"></td>
     	    <td class="ACTION" data-column="normal">
     	      <i class="fa fa-pencil fa-fw update pointer hidden" title="更新PO头信息" data-show="true" data-reveal-id="ui" data-key="true" data-dismissmodalclass="close-ui-frame" data-crudtype="pre-update" data-preupdateurl="po/preUpdatePoHeader.do" data-type="update" data-updateparam=["PO_HEADER_ID",".PO_HEADER_ID"]></i>
     	      <i class="fa fa fa-eye view pointer show_detail hidden" title="菜单明细" data-show="true" data-reveal-id="detail" data-dismissmodalclass="close-detail-frame" ></i>
     	      <i class="fa fa fa-trash-o pointer hidden" title="删除数据" data-show="true" data-crudtype="del" data-delurl="po/deletePoHeader.do" data-col="PO_NUMBER" data-delmsg="是否删除PO单" data-delparam=["PO_HEADER_ID",".PO_HEADER_ID"] ></i>
     	    </td>	    
     	    <td class="BOOK_NAME" data-column="db" style="display:none"></td>
     	    <td class="BOOK_DATE" data-column="db" style="display:none"></td>
     	    <td class="CHECK_NAME" data-column="db" style="display:none"></td>
     	    <td class="CHECK_DATE" data-column="db" style="display:none"></td>
     	    <td class="APPROVE_NAME" data-column="db" style="display:none"></td>
     	    <td class="APPROVE_DATE" data-column="db" style="display:none"></td>
     	    <td class="CANCEL_NAME" data-column="db" style="display:none"></td>
     	    <td class="CANCEL_DATE" data-column="db" style="display:none"></td>
     	    <td class="PO_HEADER_ID" style="display:none" data-column="hidden">&nbsp;</td>
     	    <td class="SHIP_FROM_ORG_ID" style="display:none" data-column="hidden">&nbsp;</td>
     	    <td class="STATUS" style="display:none" data-column="hidden">&nbsp;</td>
     	  </tr>
        </table>
      </div>
      <!-- 主表格区域 end -->
   
      <!-- 主表格按钮区域 start -->
      <div class="table_button" id="table" data-table="PoHeader">
        <div class="setting">
          <i class="fa fa-cog pointer" title="" data-reveal-id="setting" data-dismissmodalclass="close-setting"></i>
        </div>
        <div class="setting">
          <i class="fa fa-search pointer" title="条件查询" data-reveal-id="query" data-key="true" data-dismissmodalclass="close-query-frame"></i>
        </div>
        <div class="setting">
          <i class="fa fa-plus-circle pointer" title="新增PO单" data-reveal-id="ui" data-key="true" data-dismissmodalclass="close-ui-frame" data-crudtype="pre-insert" data-type="insert"></i>
        </div>
        <div class="setting">
          <i id='refresh' class="fa fa-refresh pointer" title="刷新数据" data-pagetype='refresh' data-pageframe="table"></i>
        </div>
        <div id="setting">
          <!-- 设置菜单区域 start -->
          <jsp:include page="../public/setting.jsp" >
			<jsp:param name="rdtable" value="#pHeader" />
			<jsp:param name="odtable" value="#pHeader" />
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
          <input type="hidden" data-type="orderby" id="ORDER_BY" value="PO_HEADER_ID ASC"/> 
          <input type="hidden" data-type="cond"/>
          <input type="hidden" data-type="url" value="po/getPoPage.do"/>
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
     
      <!-- 更新/新增PO头信息区域 start -->
      <div id='ui' class="pop_frame row-6">     
        <div class='title pointer'>      
          <span data-type="update"><i class="fa fa-th-list fa-1x" aria-hidden="true"></i>&nbsp;更新PO采购单</span>
          <span data-type="insert"><i class="fa fa-th-list fa-1x" aria-hidden="true"></i>&nbsp;新增PO采购单</span>
        </div>
        <a class="close-ui-frame" data-type="close">&#215;</a>
        <div class='line'></div>
        <div class="content row-6">
          <form id='updateData'>
            <input type='hidden' id='PO_HEADER_ID' name="PO_HEADER_ID" data-update="db"/>
            <label for='PO_NUMBER' class='left md'>PO单号</label>
            <input type='text' id='PO_NUMBER' name='PO_NUMBER' data-update="db" class='left lg'/>
            <label for='CUSTOMER_CONTRACT_NUMBER' class='left md'>客订单号</label>
            <input type='text' id='CUSTOMER_CONTRACT_NUMBER' name='CUSTOMER_CONTRACT_NUMBER' data-update="db" class='left lg'/>
            <label for='PARTY_NAME' class='left md'>客户名称</label> 
            <input type='text' id='PARTY_NAME' name='PARTY_NAME' class='left md' data-update="db" required="required" readonly="readonly"/>          
            <input type='hidden' id='CUSTOMER_ID' name='CUSTOMER_ID' data-update="db"/>
            <input type='button' id="CUSTOMER_LOV" class='left button pointer' data-pageframe="lov" data-reveal-id="lov" data-key="true" data-callback="ui" data-bg="lov-modal-bg" data-dismissmodalclass='close-lov' data-lovname="客户查询" data-queryurl="lov/getUserCustPage.do" data-jsontype="cust" data-defaultquery="true" data-th=["客户id","客户名称","客户帐号","公司id","销售公司","库存id","库存组织"] data-td=["CUSTOMER_ID&none","PARTY_NAME","ACCOUNT_NUMBER","ORG_ID&none","ORG_NAME","ORGANIZATION_ID&none","ORGANIZATION_NAME"] data-selectname=["客户名称","客户账号"] data-selectvalue=["PARTY_NAME","ACCOUNT_NUMBER"] data-choose=[".CUSTOMER_ID",".PARTY_NAME",".ACCOUNT_NUMBER",".ORG_ID",".ORG_NAME",".ORGANIZATION_ID",".ORGANIZATION_NAME"] data-recid=["#CUSTOMER_ID","#PARTY_NAME","#ACCOUNT_NUMBER","#ORG_ID","#ORG_NAME","#ORGANIZATION_ID","#ORGANIZATION_NAME"] value="···"/>
            <label for='ACCOUNT_NUMBER' class='left md'>客户帐号</label>
            <input type='text' id='ACCOUNT_NUMBER' name='ACCOUNT_NUMBER' data-update="db" required='required' class='left lg' readonly="readonly"/> 
            <input type="hidden" id="ORG_ID" name="SALES_ORG_ID" data-update="db"/>
            <label for="ORG_NAME" class="left md">销售公司</label>
            <input type="text" id="ORG_NAME" name="SALES_ORG_NAME" data-update="db" required="required" class="left lg" readonly="readonly"/>
            <label for='ORGANIZATION_NAME' class='left md'>发货组织</label> 
            <input type='text' id='ORGANIZATION_NAME' name='ORGANIZATION_NAME' class='left md' data-update="db" required="required" readonly="readonly"/>          
            <input type='hidden' id='ORGANIZATION_ID' name='ORGANIZATION_ID' data-update="db"/>
            <input type='button' id="ORGANIZATION_LOV" class='left button pointer' data-pageframe="lov" data-reveal-id="lov" data-key="true" data-callback="ui" data-bg="lov-modal-bg" data-dismissmodalclass='close-lov' data-lovname="库存组织查询" data-queryurl="lov/getJbUserOrganPage.do" data-jsontype="organ" data-defaultquery="true" data-th=["库存id","库存编码","库存组织"] data-td=["ORGANIZATION_ID&none","ORGANIZATION_CODE","ORGANIZATION_NAME"] data-selectname=["库存编码","库存组织"] data-selectvalue=["ORGANIZATION_CODE","ORGANIZATION_NAME"] data-choose=[".ORGANIZATION_ID",".ORGANIZATION_NAME"] data-recid=["#ORGANIZATION_ID","#ORGANIZATION_NAME"] value="···"/>         
            <input type="hidden" id="CURR_CODE" name="CURR_CODE" required="required" value="CNY"/>
            <input type="hidden" id="STATUS" name="STATUS" data-update="db"/>
            <br style="clear:both"/>
            <label for="REMARK" class="left md">备注</label>
            <textarea id="REMARK" name="REMARK" data-update="db" cols="100"></textarea>
          </form>
          </form>
        </div>
        <div class='foot'>       
          <button class="right pointer" data-type="update" data-keyup="enter" data-crudtype="update" data-pageframe="ui" data-updateurl="po/updatePoHeader.do">保存</button>
          <button class="right pointer" data-type="insert" data-keyup="enter" data-crudtype="insert" data-pageframe="ui" data-inserturl="po/insertPoHeader.do">提交</button>
        </div>    
      </div> 
      <!-- 更新/新增用户区域 end -->
     
      <!-- 条件查询区域 start -->
      <div id='query' class="pop_frame row-2">     
        <div class='title pointer'>      
          <span><i class="fa fa-th-list"></i>&nbsp;PO单查询</span>
        </div>
        <a class="close-query-frame" data-type="close">&#215;</a>
        <div class='line'></div>
        <div class="content row-2">
          <form>
            <label for='PO_NUMBER_Q' class='left md'>PO单号:</label> 
            <input type="text" id="PO_NUMBER_Q" name="PO_NUMBER" class="left lg"/>
            <label for="CUSTOMER_CONTRACT_NUMBER_Q" class='left md'>客订单号:</label>
            <input type="text" id="CUSTOMER_CONTRACT_NUMBER_Q" name="CUSTOMER_CONTRACT_NUMBER"  class="left lg"/>
            <br style="clear:both"/>
            <label for="STATUS_Q" class="left md">订单状态:</label> 
            <select class='left lg' id='STATUS_Q' name='STATUS' data-notnull="false" data-listurl="list/getPoStatus.do"></select>
            <label for="PARTY_NAME_Q" class="left md">客户名称:</label> 
            <input type="text" id="PARTY_NAME_Q" name="PARTY_NAME" class="left md" data-modify="true" data-pageframe="query"  data-lovbtn="PARTY_LOV_Q"  data-param="PARTY_NAME"/>
            <input type="hidden" id="CUSTOMER_ID_Q" name="CUSTOMER_ID"/>
            <input type="button" id="PARTY_LOV_Q" class="left button pointer" data-pageframe="lov" data-reveal-id="lov" data-key="true" data-callback="query" data-bg="lov-modal-bg" data-dismissmodalclass='close-lov' data-lovname="客户查询" data-queryurl="lov/getUserCustPage.do" data-jsontype="cust_q" data-defaultquery="true" data-th=["销售公司ID","销售公司","客户ID","客户名称","客户账号"] data-td=["ORG_ID&none","ORG_NAME","CUST_ID&none","PARTY_NAME","ACCOUNT_NUMBER"] data-selectname=["客户名称","客户账号"] data-selectvalue=["PARTY_NAME","ACCOUNT_NUMBER"] data-choose=[".CUST_ID",".PARTY_NAME"] data-recid=["#CUSTOMER_ID_Q","#PARTY_NAME_Q"] value="···"/> 
          </form>
        </div>
        <div class='foot'>             
          <button class="right pointer"  data-buttonframe="table" data-keyup="enter" data-crudtype="query" data-pageframe="query">PO单查询</button>
        </div> 
      </div>
      <!-- 条件查询区域 end --> 
      
<!----------------------------------------------PO明细-------------------------------------------------------- -->      
   
      <div class="detail_frame" id="detail">
        <div class='title pointer'>      
          <span><i class="fa fa-th-list"></i>&nbsp;PO行明细</span>
        </div>
        <a class="close-detail-frame" data-type="close">&#215;</a>
        <div class="detail_header">
          <div class="header_container">
            <input type="hidden" id="PO_HEADER_ID_LINES" />
            <label class="mid left" for="PO_NUMBER_LINES">PO单号</label>
            <input type="text" id="PO_NUMBER_LINES" class="long left" readonly="readonly"/>
            <label class="mid left" for="CUSTOMER_CONTRACT_NUMBER_LINES">客订单号</label>
            <input type="text" id="CUSTOMER_CONTRACT_NUMBER_LINES" class="long left" readonly="readonly"/>
            <br style="clear:both"/>
            <input type="hidden" id="ORGANIZATION_ID_LINES"/>
            <label class="mid left" for="ORGANIZATION_NAME_LINES">发货组织</label>
            <input type="text" id="ORGANIZATION_NAME_LINES" class="long left" readonly="readonly"/>
            <input type="hidden" id="STATUS_LINES"/>
            <label class="mid left" for="STATUS_DESC_LINES">状态</label>
            <input type="text" id="STATUS_DESC_LINES" class="long left" readonly="readonly"/>
          </div>
        </div>
        
        <!-- PO明细表格区域 start -->
        <div class='detail_table'>
          <table id="pLine" data-table="PoLine">
            <tr>
              <th class="LINE_NUM" data-column="db">序号</th>
              <th class="THICKNESS" data-column="db">厚度</th>
     	      <th class="COATING_TYPE" data-column="db">膜系</th>
     	      <th class="ITEM_DESC" data-column="db">物料描述</th>
     	      <th class="WIDTH" data-column="db">宽度</th>
     	      <th class="HEIGHT" data-column="db">高度</th>
     	      <th class="PIE_QUANTITY" data-column="db">片数</th>
     	      <th class="PIE_UNIT_PRICE" data-column="db">片单价</th>
     	      <th class="SQM" data-column="db">面积</th>
     	      <th class="SQM_UNIT_PRICE" data-column="db">面积单价</th>
     	      <th class="AMOUNT" data-column="db">金额</th>
     	      <th class="REMARK" data-column="db">备注</th>
     	      <th class="ACTION" data-column="normal">操作</th> 
     	      <th class="PO_LINE_ID" style="display:none" data-column="hidden">&nbsp;</th>
     	    </tr>
     	    <tr>
     	      <td class="LINE_NUM" data-column="db"></td>
              <td class="THICKNESS" data-column="db"></td>
     	      <td class="COATING_TYPE" data-column="db"></td>
     	      <td class="ITEM_DESC" data-column="db"></td>
     	      <td class="WIDTH" data-column="db"></td>
     	      <td class="HEIGHT" data-column="db"></td>
     	      <td class="PIE_QUANTITY" data-column="db"></td>
     	      <td class="PIE_UNIT_PRICE" data-column="db"></td>
     	      <td class="SQM" data-column="db"></td>
     	      <td class="SQM_UNIT_PRICE" data-column="db"></td>
     	      <td class="AMOUNT" data-column="db"></td>
     	      <td class="REMARK" data-column="db"></td>
     	      <td class="ACTION" data-column="normal">
     	        <i class="fa fa-pencil fa-fw update pointer" data-show="true" data-reveal-id="detail_ui" data-key="true" data-bg="detail-modal-bg" data-dismissmodalclass="close-detail-ui-frame" data-crudtype="pre-update" data-preupdateurl="po/preUpdatePoLine.do" data-type="update" data-updateparam=["PO_LINE_ID",".PO_LINE_ID"] data-func="$().beforePreUpdateDetail();"></i>
     	        <i class="fa fa fa-trash-o pointer" title="删除数据" data-show="true" data-crudtype="del" data-refresh="sub_refresh" data-delurl="po/deletePoLine.do" data-col="LINE_NUM" data-delmsg="是否删除PO明细行-" data-delparam=["PO_LINE_ID",".PO_LINE_ID"] ></i>
     	      </td>
     	      <td class="PO_LINE_ID" style="display:none" data-column="hidden">&nbsp;</td> 
     	    </tr>
          </table>
        </div>
        <!-- 菜单明细表格区域 end --> 
        
        <div class="table_button" id="sub_table" data-table="PoLine">
          <div class="setting">
            <i class="fa fa-plus-circle pointer" data-reveal-id="detail_ui" data-key="true" data-bg="detail-modal-bg" data-dismissmodalclass="close-detail-ui-frame" data-crudtype="pre-insert" data-type="insert" data-func="$().autoAddSeq();"></i>
          </div>
          <div class="setting">
            <i id='sub_refresh' class="fa fa-refresh pointer" data-pagetype='refresh' data-pageframe="sub_table" data-func="$().setParam();"></i>
          </div>
          <div class="setting">
            <i class="fa fa-podcast pointer" data-statustype="BOOK" title="登记"></i>
          </div>
          <div class="setting">
            <i class="fa fa-pencil-square-o pointer" data-statustype="CHECKED" title="审批"></i>
          </div>
          <div class="setting">
            <i class="fa fa-check-square-o pointer" data-statustype="APPROVED" title="终审"></i>
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
            <input type="hidden" data-type="orderby" value="LINE_NUM ASC"/> 
            <input type="hidden" data-type="cond"/>
            <input type="hidden" data-type="url" value="po/getPoLine.do"/>
            <input type="hidden" data-type="jsontype" value="subtable"/> 
          </div>
        </div>
      </div>
    </div>   
    
    
    <!-- PO明细新增/更新区域 start -->
    <div id='detail_ui' class="pop_frame row-9" style="z-index:104">     
        <div class='title pointer'>      
          <span data-type="update"><i class="fa fa-th-list  fa-1x" aria-hidden="true"></i>&nbsp;更新PO明细</span>
          <span data-type="insert"><i class="fa fa-th-list  fa-1x" aria-hidden="true"></i>&nbsp;新增PO明细</span>
        </div>
        <a class="close-detail-ui-frame" data-type="close">&#215;</a>
        <div class='line'></div>
        <div class="content row-9">
          <form id='updateDetailData'>
            <input type='hidden' id='PO_HEADER_ID_D' name="PO_HEADER_ID" data-update="db" value=""/>
            <input type='hidden' id='PO_LINE_ID_D' name="PO_LINE_ID" data-update="db" value=""/>
            <input type='hidden' id='STATUS_D' name="STATUS" data-update="db" value=""/>
            <input type='hidden' id='SHIP_FROM_ORG_ID_D' name="SHIP_FROM_ORG_ID" data-update="db" value=""/>
            <label for='LINE_NUM_D' class='left md'>行号</label> 
            <input type="text" id="LINE_NUM_D" name="LINE_NUM" data-update="db" class="left lg" required="required" readonly="readonly"/>
            <label for='COATING_NAME_D' class='left md'>膜系</label> 
            <input type='text' id='COATING_NAME_D' name='COATING_NAME' data-update="db" class='left md clean_item' required="required" data-modify="true" data-pageframe="query"  data-lovbtn="COATING_LOV"  data-param="MEANING"/>          
            <input type='hidden' id='COATING_CODE_D' name='COATING_TYPE' data-update="db"/>
            <input type='button' id="COATING_LOV" class='left button pointer' data-pageframe="lov" data-reveal-id="lov" data-key="true" data-callback="detail_ui" data-bg="lov-modal-bg" data-dismissmodalclass='close-lov' data-lovname="膜系查询" data-queryurl="lov/getCoatingPage.do" data-jsontype="coating" data-defaultquery="true" data-th=["膜系编码","膜系名称","描述"] data-td=["LOOKUP_CODE","MEANING","DESCRIPTION"] data-selectname=["膜系编码","膜系名称"] data-selectvalue=["LOOKUP_CODE","MEANING"] data-choose=[".LOOKUP_CODE",".MEANING"] data-recid=["#COATING_CODE_D","#COATING_NAME_D"] data-clickfunc="$().lovClickExtends();" value="···"/>  
            <br style="clear:both"/>
            <label for="THICKNESS_D" class="left md">厚度</label> 
            <input type="text" id="THICKNESS_D" name="THICKNESS" data-update="db" class="left lg clean_item" required="required"/> 
            <label for='ITEM_D' class='md left'>物料</label> 
            <input type='text' id='ITEM_D' name='INVENTORY_ITEM' data-update="db" class='left md' required="required" data-modify="true"  data-lovbtn="ITEM_LOV" data-suffixflag="false" data-param="ITEM_DESC"/>          
            <input type='hidden' id='ITEM_ID_D' name='INVENTORY_ITEM_ID'/>
            <input type='button' id="ITEM_LOV" class='left button pointer' data-pageframe="lov" data-reveal-id="lov" data-key="true" data-callback="detail_ui" data-bg="lov-modal-bg" data-dismissmodalclass='close-lov' data-lovname="物料查询" data-queryurl="lov/getItemPage.do" data-jsontype="item" data-defaultquery="true" data-extparam=["THICKNESS","COATING_CODE","ORGANIZATION_ID"] data-extparamid=["#THICKNESS_D","#COATING_CODE_D","#ORGANIZATION_ID_LINES"] data-th=["物料ID","物料编码","物料描述","明细"] data-td=["INVENTORY_ITEM_ID&none","ITEM_NUMBER","ITEM_DESC","ITEM_LONG_DESC"] data-selectname=["物料描述","物料编码"] data-selectvalue=["ITEM_DESC","ITEM_NUMBER"] data-choose=[".INVENTORY_ITEM_ID",".ITEM_DESC"] data-recid=["#ITEM_ID_D","#ITEM_D"] value="···"/> 
            <br style="clear:both"/>
            <label for="WIDTH_D" class="left md">宽度(mm)</label> 
            <input type="text" id="WIDTH_D" name="WIDTH" data-update="db" class="left lg sqm_calc" required="required"/> 
            <label for="HEIGHT_D" class="left md">高度(mm)</label> 
            <input type="text" id="HEIGHT_D" name="HEIGHT" data-update="db" class="left lg sqm_calc" required="required"/> 
            <label for="PIE_QUANTITY_D" class="left md">片数</label> 
            <input type="text" id="PIE_QUANTITY_D" name="PIE_QUANTITY" data-update="db" class="left lg sqm_calc" required="required"/> 
            <label for="SQM_D" class="left md">面积(m²)</label> 
            <input type="text" id="SQM_D" name="SQM" data-update="db" class="left lg" readonly="readonly"/>
            <label for="SQM_UNIT_PRICE_D" class="left md" style="display:none">面积单价</label> 
            <input type="text" id="SQM_UNIT_PRICE_D" name="SQM_UNIT_PRICE" data-update="db" class="left lg amount_calc" style="display:none"/> 
            <label for="AMOUNT_D" class="left md" style="display:none">金额</label> 
            <input type="text" id="AMOUNT_D" name="AMOUNT" data-update="db" class="left lg" style="display:none" readonly="readonly"/>
            <br style="clear:both"/>
            <label for="REMARK_D" class="left md">备注</label>
            <textarea id="REMARK_D" name="REMARK" data-update="db" cols="100"></textarea>
          </form>
        </div>
        <div class='foot'>       
          <button class="right update_confirm pointer" data-type="update" data-keyup="enter" data-crudtype="update" data-pageframe="detail_ui" data-updateurl="po/updatePoLine.do" data-refresh="sub_refresh" data-func="$().beforeUpdateDetail();">保存</button>
          <button class="right update_confirm pointer" data-type="insert" data-keyup="enter" data-crudtype="insert" data-pageframe="detail_ui" data-inserturl="po/insertPoLine.do" data-refresh="sub_refresh" data-func="$().beforeInsertDetail();">新增</button>
        </div>    
      </div>   
    <!-- PO明细新增/更新区域 end -->  

<!----------------------------------------------菜单明细-------------------------------------------------------- -->       
      
      <!-- 用户信息存放区域 start -->
      <input type="hidden" id="USER_ID" value="${USER_ID}"/> 
      <input type="hidden" id="FUNC_ID" value="${FUNC_ID}"/> 
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
    		
    		$("#detail").resizable({
    		    maxHeight: 389,
      			minHeight: 389,
      			minWidth: 1000
    		});
 
    		//初始化CRUD和LOV条件查询
    		$().crudListener();	
    		$().revealListener(); 
    		
    		//功能权限前端验证
    		$(function() {
    		    $.ajax({
				    type:'post',  
				    url:'perm/getUserFuncPerm.do',
				    dataType:'json',
				    success: function (data) {
				       var insertFlag = data.rows[0].INSERT_FLAG;
				       var updateFlag = data.rows[0].UPDATE_FLAG;
				       var checkFlag = data.rows[0].APPROVE_FLAG;
				       var approvedFlag = data.rows[0].FINAL_APPROVE_FLAG;
				       var deleteFlag = data.rows[0].DELETE_FLAG;
				       if(insertFlag == 'N'){
				           $('i[data-crudtype="pre-insert"]').parent().css('display','none');
				       };
				       if(updateFlag == 'N'){
				           $('i[data-crudtype="pre-update"]').css('display','none');
				       };
				       if(checkFlag == 'N'){
				           $('i[data-statustype="CHECKED"]').parent().css('display','none');
				       };
				       if(approvedFlag == 'N'){
				           $('i[data-statustype="APPROVED"]').parent().css('display','none');
				       };
				       if(deleteFlag == 'N'){
				           $('i[data-crudtype="del"]').css('display','none');
				       };
				    },
				    error: function () {
				    	layer.alert('获取数据失败',{title:'警告',offset:[150]});
				    }
				});
    		});
    		
    		//状态变更按钮绑定
    		$('i[data-statustype]').on('click',function(){
    		    poHeaderId = $('#PO_HEADER_ID_LINES').val();
    		    poNumber = $('#PO_NUMBER_LINES').val();
    		    status = $(this).data('statustype');
    		    param = 'PO_HEADER_ID='+poHeaderId+'&STATUS='+status;
    		    $.ajax({
				    type:'post', 
				    data:param,  
				    url:'po/changeStatus.do',
				    dataType:'json',
				    success: function (data) {
				        if(data.retcode=="0"){
				            if(status=='BOOK'){
				                layer.msg('PO单:'+poNumber+'登记成功');
				                $('#STATUS_DESC_LINES').val('登记');
				            }else if(status=='CHECKED'){
				                layer.msg('PO单:'+poNumber+'审批成功');
				                $('#STATUS_DESC_LINES').val('审批');
				            }else if(status=='APPROVED'){
				                layer.msg('PO单:'+poNumber+'终审成功');
				                $('#STATUS_DESC_LINES').val('终审');
				            }
				    		$('#refresh').click();				    	    
				    	}else{
				    	    if(status=='BOOK'){
				                layer.alert("登记失败！错误信息:"+data.errbuf,{title:'警告',offset:[150]});
				            }else if(status=='CHECKED'){
				                layer.alert("审核失败！错误信息:"+data.errbuf,{title:'警告',offset:[150]});
				            }else if(status=='APPROVED'){
				                layer.alert("终审失败！错误信息:"+data.errbuf,{title:'警告',offset:[150]});
				            }
				    	} 
				    },
				    error: function () {
				    	layer.alert('获取数据失败',{title:'警告',offset:[150]});
				    }	
				});      
    		});  
    		
    		//绑定清空栏位
    		$('#detail_ui .clean_item').on('input',function(){
    		    $('#ITEM_D').val('');
    		    $('#ITEM_ID_D').val('');
    		});
    		
    		//总金额自动计算逻辑
    		$('#detail_ui .amount_calc').on('input',function(){
    		    sqm = $('#SQM_D').val();
    		    if(sqm==null||sqm==''){
    		        sqm = 0;
    		    }
    		    sqm_price = $('#SQM_UNIT_PRICE_D').val();
    		    if(sqm_price==null||sqm_price==''){
    		        return;
    		    }
    		    amount = parseFloat(sqm) * parseFloat(sqm_price);
    		    amount = amount.toFixed(2);
    		    $('#AMOUNT_D').val(amount);
    		});
    		
    		//平米数自动计算逻辑
    		$('#detail_ui .sqm_calc').on('input',function(){
    		    width = $('#WIDTH_D').val();
    		    if(width==null||width==''){
    		        width = 0;
    		    }
    		    height = $('#HEIGHT_D').val()
    		    if(height==null||height==''){
    		        height = 0;
    		    }
    		    pie = $('#PIE_QUANTITY_D').val();
    		    if(pie==null||pie==''){
    		        pie = 0;
    		    }
    		    sqm = (parseInt(width)/1000) * (parseInt(height)/1000) * parseInt(pie);
    		    if(sqm==0){
    		        sqm = null;
    		    }
    		    $('#SQM_D').val(sqm);
    		});
    		
    		/******匿名函数区域Start******/
    		$.fn.setParam = function(){
    		    poHeaderId = $('#PO_HEADER_ID_LINES').val();
    		    param = param + '&PO_HEADER_ID='+poHeaderId;
    		}	
    		
    		$.fn.detailShow = function(){
    		    $('.show_detail').off('click');
    		    $('.show_detail').on('click',function(){
    		        tr = $(this).parent().parent();
    		        poHeaderId = tr.children('.PO_HEADER_ID').text();
    		    	poNumber = tr.children('.PO_NUMBER').text();
    		    	customerContractNo = tr.children('.CUSTOMER_CONTRACT_NUMBER').text();
    		    	organizationName = tr.children('.ORGANIZATION_NAME').text();
    		    	organizationId = tr.children('.SHIP_FROM_ORG_ID').text();
    		    	status = tr.children('.STATUS').text();
    		    	statusDesc = tr.children('.STATUS_DESC').text();
    		    	$('.detail_header input').val('');
    		    	$('#sub_table input[data-type="number"]').val('1');
    		    	$('#PO_HEADER_ID_LINES').val(poHeaderId);
    		    	$('#PO_NUMBER_LINES').val(poNumber);
    		    	$('#CUSTOMER_CONTRACT_NUMBER_LINES').val(customerContractNo);
    		    	$('#ORGANIZATION_NAME_LINES').val(organizationName);
    		    	$('#ORGANIZATION_ID_LINES').val(organizationId);
    		    	$('#STATUS_LINES').val(status);
    		    	$('#STATUS_DESC_LINES').val(statusDesc);
    		    	$('#sub_refresh').click();
    		    });    		   
    		} 	  
    		
    		$.fn.beforeInsertDetail = function(){
    			RegExpValidate('^[0-9]+(.[0-9]{2})?$','THICKNESS_D','regExpError("厚度只能输入小数点后至多一位的正实数");');
    			RegExpValidate('^\\+?[1-9][0-9]*$','WIDTH_D','regExpError("宽度只能输入非零正整数!");');
    			RegExpValidate('^\\+?[1-9][0-9]*$','HEIGHT_D','regExpError("高度只能输入非零正整数!");');
    			RegExpValidate('^\\+?[1-9][0-9]*$','PIE_QUANTITY_D','regExpError("片数只能输入非零正整数!");');
    		
    		}
    		$.fn.beforeUpdateDetail = function(){
    		   RegExpValidate('^[0-9]+(.[0-9]{2})?$','THICKNESS_D','regExpError("厚度只能输入小数点后至多一位的正实数");');
    		   RegExpValidate('^\\+?[1-9][0-9]*$','WIDTH_D','regExpError("宽度只能输入非零正整数!");');
    		   RegExpValidate('^\\+?[1-9][0-9]*$','HEIGHT_D','regExpError("高度只能输入非零正整数!");');
    		   RegExpValidate('^\\d+$','PIE_QUANTITY_D','regExpError("片数只能输入正整数!");');
    		   RegExpValidate('^[0-9]+(.[0-9]{2})?$','SQM_UNIT_PRICE_D','regExpError("单位面积必须是精确到小数点后两位的正实数");');
    		}	
    		
    		$.fn.autoAddSeq = function(){
    		    poHeaderId = $('#PO_HEADER_ID_LINES').val();
    		    param = 'PO_HEADER_ID='+poHeaderId;
    		    $.ajax({
				    type:'post', 
				    data:param,
				    url:'po/getAutoAddSeq.do',
				    dataType:'json',
				    success: function (data) {
				        $('#PO_HEADER_ID_D').val($('#PO_HEADER_ID_LINES').val());
				        $('#SHIP_FROM_ORG_ID_D').val($('#ORGANIZATION_ID_LINES').val());
				        $('#STATUS_D').val($('#STATUS_LINES').val());
				        $('#LINE_NUM_D').val(data.rows[0].LINE_NUM);
				    },
				    error: function () {
				    	layer.alert('获取数据失败',{title:'警告',offset:[150]});
				    }			
				});	
    		}	
    		
    		/*
    		$.fn.lovExtends = function(){
    		    thickness = $('#THICKNESS_D').val();
    		    coatingCode = $('#COATING_CODE_D').val();
    		    organizationId = $('#ORGANIZATION_ID_LINES').val();
    		    param = '&ORGANIZATION_ID=' + organizationId + '&THICKNESS=' + thickness +'&COATING_CODE=' + coatingCode;
    		    $('#lov input[data-type="extend_param"]').val(param);
    		}*/
    		
    		$.fn.lovClickExtends = function(){
    		    $('#ITEM_D').val('');
    		    $('#ITEM_ID_D').val('');
    		}
    		
    		$.fn.beforePreUpdateDetail = function(){
    		    userType='${USER_TYPE}';
    		    if(userType=='EMP'){
    		        $('.detail_update_frame').css('height','420px');
    		        $('.detail_update_frame .content').css('height','280px');
    		        $('label[for="SQM_UNIT_PRICE_D"]').css('display','');
    		        $('#SQM_UNIT_PRICE_D').css('display','');
    		        $('#SQM_UNIT_PRICE_D').attr('required','required');
    		        $('label[for="AMOUNT_D"]').css('display','');
    		        $('#AMOUNT_D').css('display','');
    		    }
    		}    		
    		/******匿名函数区域end******/
        });
        
        jQuery.json={
        	getContent:function(data,JSONtype){  
        	    if(JSONtype=='table'){
        	        for(i=0;i<(pageMaxRow-pageMinRow+1);i++){
                    	$('.PO_NUMBER',$('#pHeader tr:eq('+(i+1)+')')).html(data.rows[i].PO_NUMBER); 
                   	 	$('.CUSTOMER_CONTRACT_NUMBER',$('#pHeader tr:eq('+(i+1)+')')).html(data.rows[i].CUSTOMER_CONTRACT_NUMBER); 
                    	$('.CURR_CODE',$('#pHeader tr:eq('+(i+1)+')')).html(data.rows[i].CURR_CODE);
                    	$('.PARTY_NAME',$('#pHeader tr:eq('+(i+1)+')')).html(data.rows[i].PARTY_NAME); 
                    	$('.ACCOUNT_NUMBER',$('#pHeader tr:eq('+(i+1)+')')).html(data.rows[i].ACCOUNT_NUMBER);     
                    	$('.SALES_ORG_NAME',$('#pHeader tr:eq('+(i+1)+')')).html(data.rows[i].ORGANIZATION_NAME);           	
                    	$('.ORGANIZATION_NAME',$('#pHeader tr:eq('+(i+1)+')')).html(data.rows[i].SALES_ORG_NAME);           	
                    	$('.STATUS_DESC',$('#pHeader tr:eq('+(i+1)+')')).html(data.rows[i].STATUS_DESC); 
                    	$('.BOOK_NAME',$('#pHeader tr:eq('+(i+1)+')')).html(data.rows[i].BOOK_NAME); 
                    	$('.BOOK_DATE',$('#pHeader tr:eq('+(i+1)+')')).html(data.rows[i].BOOK_DATE); 
                    	$('.CHECK_NAME',$('#pHeader tr:eq('+(i+1)+')')).html(data.rows[i].CHECK_NAME); 
                    	$('.CHECK_DATE',$('#pHeader tr:eq('+(i+1)+')')).html(data.rows[i].CHECK_DATE); 
                    	$('.APPROVE_NAME',$('#pHeader tr:eq('+(i+1)+')')).html(data.rows[i].APPROVE_NAME); 
                    	$('.APPROVE_DATE',$('#pHeader tr:eq('+(i+1)+')')).html(data.rows[i].APPROVE_DATE); 
                    	$('.CANCEL_NAME',$('#pHeader tr:eq('+(i+1)+')')).html(data.rows[i].CANCEL_NAME); 
                    	$('.CANCEL_DATE',$('#pHeader tr:eq('+(i+1)+')')).html(data.rows[i].CANCEL_DATE); 
                    	$('.REMARK',$('#pHeader tr:eq('+(i+1)+')')).html(data.rows[i].REMARK); 
                    	$('.PO_HEADER_ID',$('#pHeader tr:eq('+(i+1)+')')).html(data.rows[i].PO_HEADER_ID); 
                    	$('.SHIP_FROM_ORG_ID',$('#pHeader tr:eq('+(i+1)+')')).html(data.rows[i].SHIP_FROM_ORG_ID); 
                    	$('.STATUS',$('#pHeader tr:eq('+(i+1)+')')).html(data.rows[i].STATUS); 
                	}
                	$().crudListener();
                	$().detailShow();
                	$().revealListener(); 
        	    }else if(JSONtype=='subtable'){
        	        for(i=0;i<(pageMaxRow-pageMinRow+1);i++){
        	            $('.PO_LINE_ID',$('#pLine tr:eq('+(i+1)+')')).html(data.rows[i].PO_LINE_ID); 
        	            $('.LINE_NUM',$('#pLine tr:eq('+(i+1)+')')).html(data.rows[i].LINE_NUM); 
                   	 	$('.THICKNESS',$('#pLine tr:eq('+(i+1)+')')).html(data.rows[i].THICKNESS); 
                    	$('.COATING_TYPE',$('#pLine tr:eq('+(i+1)+')')).html(data.rows[i].COATING_TYPE);
                    	$('.ITEM_DESC',$('#pLine tr:eq('+(i+1)+')')).html(data.rows[i].ITEM_DESC); 
                    	$('.WIDTH',$('#pLine tr:eq('+(i+1)+')')).html(data.rows[i].WIDTH);   
                    	$('.HEIGHT',$('#pLine tr:eq('+(i+1)+')')).html(data.rows[i].HEIGHT); 
                    	$('.PIE_QUANTITY',$('#pLine tr:eq('+(i+1)+')')).html(data.rows[i].PIE_QUANTITY);
                    	$('.PIE_UNIT_PRICE',$('#pLine tr:eq('+(i+1)+')')).html(data.rows[i].PIE_UNIT_PRICE);
                    	$('.SQM',$('#pLine tr:eq('+(i+1)+')')).html(data.rows[i].SQM);
                    	$('.SQM_UNIT_PRICE',$('#pLine tr:eq('+(i+1)+')')).html(data.rows[i].SQM_UNIT_PRICE);
                    	$('.AMOUNT',$('#pLine tr:eq('+(i+1)+')')).html(data.rows[i].AMOUNT);
                    	$('.REMARK',$('#pLine tr:eq('+(i+1)+')')).html(data.rows[i].REMARK);
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
       	            	    $('td:eq(5)',$('.contentbox tr:eq('+(i+1)+')')).html(data.rows[i].ORGANIZATION_ID); 
       	            	    $('td:eq(6)',$('.contentbox tr:eq('+(i+1)+')')).html(data.rows[i].ORGANIZATION_NAME); 
       	            	} 
        	       	}       	            	    
        	    }else if(JSONtype=='cust_q'){
        	        if(pageMaxRow==0&&pageMinRow==0){
        	            console.log('no data');
        	        }else{
        	            for(i=0;i<(pageMaxRow-pageMinRow+1);i++){
        	                $('td:eq(0)',$('.contentbox tr:eq('+(i+1)+')')).html(data.rows[i].ORG_ID);   	               	        
       	            	    $('td:eq(1)',$('.contentbox tr:eq('+(i+1)+')')).html(data.rows[i].ORG_NAME); 
       	            	    $('td:eq(2)',$('.contentbox tr:eq('+(i+1)+')')).html(data.rows[i].CUSTOMER_ID);  
       	            	    $('td:eq(3)',$('.contentbox tr:eq('+(i+1)+')')).html(data.rows[i].PARTY_NAME);     	                    
       	            	    $('td:eq(4)',$('.contentbox tr:eq('+(i+1)+')')).html(data.rows[i].ACCOUNT_NUMBER); 
       	            	} 
        	       	}       	            	    
        	    }else if(JSONtype=='organ'){
        	        if(pageMaxRow==0&&pageMinRow==0){
        	            console.log('no data');
        	        }else{
        	            for(i=0;i<(pageMaxRow-pageMinRow+1);i++){
       	            	    $('td:eq(0)',$('.contentbox tr:eq('+(i+1)+')')).html(data.rows[i].ORGANIZATION_ID);  
       	            	    $('td:eq(1)',$('.contentbox tr:eq('+(i+1)+')')).html(data.rows[i].ORGANIZATION_CODE);     	                    
       	            	    $('td:eq(2)',$('.contentbox tr:eq('+(i+1)+')')).html(data.rows[i].ORGANIZATION_NAME);    	               	        
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
        	    }else if(JSONtype=='item'){
        	        if(pageMaxRow==0&&pageMinRow==0){
        	            console.log('no data');
        	        }else{
        	            for(i=0;i<(pageMaxRow-pageMinRow+1);i++){
       	            	    $('td:eq(0)',$('.contentbox tr:eq('+(i+1)+')')).html(data.rows[i].INVENTORY_ITEM_ID);  
       	            	    $('td:eq(1)',$('.contentbox tr:eq('+(i+1)+')')).html(data.rows[i].ITEM_NUMBER);     	                    
       	            	    $('td:eq(2)',$('.contentbox tr:eq('+(i+1)+')')).html(data.rows[i].ITEM_DESC); 
       	            	    $('td:eq(3)',$('.contentbox tr:eq('+(i+1)+')')).html(data.rows[i].ITEM_LONG_DESC);   	               	        
       	            	} 
        	        }       	            	    
        	    }     	            	   	
       	    },	
       	    getUpdateJSON:function(data,pageframe){   
       	        if(pageframe=='ui'){
       	            $('#PO_HEADER_ID').val(data.rows[0].PO_HEADER_ID);
       	        	$('#PO_NUMBER').val(data.rows[0].PO_NUMBER);
       	        	$('#CUSTOMER_CONTRACT_NUMBER').val(data.rows[0].CUSTOMER_CONTRACT_NUMBER);
       	        	$('#CURR_CODE').val(data.rows[0].CURR_CODE);
       	        	$('#CUSTOMER_ID').val(data.rows[0].CUSTOMER_ID);  
       	        	$('#PARTY_NAME').val(data.rows[0].PARTY_NAME);
       	        	$('#ACCOUNT_NUMBER').val(data.rows[0].ACCOUNT_NUMBER);
       	        	$('#ORG_ID').val(data.rows[0].SALES_ORG_ID);
       	        	$('#ORG_NAME').val(data.rows[0].SALES_ORG_NAME);
       	        	$('#ORGANIZATION_ID').val(data.rows[0].ORGANIZATION_ID);
       	        	$('#ORGANIZATION_NAME').val(data.rows[0].ORGANIZATION_NAME);
       	        	$('#REMARK').val(data.rows[0].REMARK);	
       	        }else if(pageframe='detail_ui'){
       	            $('#PO_LINE_ID_D').val(data.rows[0].PO_LINE_ID);
       	            $('#PO_HEADER_ID_D').val(data.rows[0].PO_HEADER_ID);
       	            $('#STATUS_D').val(data.rows[0].STATUS);
       	            $('#SHIP_FROM_ORG_ID_D').val(data.rows[0].SHIP_FROM_ORG_ID);
       	            $('#LINE_NUM_D').val(data.rows[0].LINE_NUM);
       	            $('#COATING_CODE_D').val(data.rows[0].COATING_TYPE);
       	            $('#COATING_NAME_D').val(data.rows[0].COATING_TYPE);
       	            $('#THICKNESS_D').val(data.rows[0].THICKNESS);
       	            $('#ITEM_D').val(data.rows[0].ITEM_DESC);
       	            $('#ITEM_ID_D').val(data.rows[0].INVENTORY_ITEM_ID);
       	            $('#WIDTH_D').val(data.rows[0].WIDTH);
       	            $('#HEIGHT_D').val(data.rows[0].HEIGHT);
       	            $('#PIE_QUANTITY_D').val(data.rows[0].PIE_QUANTITY);
       	            $('#SQM_D').val(data.rows[0].SQM);
       	            $('#SQM_UNIT_PRICE_D').val(data.rows[0].SQM_UNIT_PRICE);
       	            $('#AMOUNT_D').val(data.rows[0].AMOUNT);
       	            $('#REMARK_D').val(data.rows[0].REMARK);
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
    <script type="text/javascript" src="plugin/js/jQuery.rowdefine.js"></script>
    <script type="text/javascript" src="plugin/js/jQuery.irr.orderby.js"></script>	
    <script type="text/javascript" src="plugin/js/jQuery.irr.init.js"></script>	    
  </body>
</html>