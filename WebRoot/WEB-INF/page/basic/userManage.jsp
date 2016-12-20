<%@ page contentType="text/html;charset=utf-8" language="java" pageEncoding="utf-8"
	      import="java.util.*" %>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML>
<html>
  <head>
    <title>用户管理</title>
    <base href="<%=basePath%>"> 
    <meta http-equiv="content-type" content="text/html;charset=gb2312">
	<link rel="stylesheet" href="plugin/css/font-awesome.min.css">
	<link rel="stylesheet" href="plugin/css/jquery-ui.min.css">
	<link rel="stylesheet" type="text/css" href="plugin/css/public.css">
	<link rel="stylesheet" type="text/css" href="plugin/css/basic/user.css">
	<link rel="stylesheet" href="plugin/css/cutpic.css" type="text/css" />
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
        <table id="user" data-table="User">
          <tr>
            <th class="USER_NAME" data-column="db">用户账号</th>
            <th class="DESCRIPTION" data-column="db">用户名称</th>
     	    <th class="START_DATE" data-column="db">启用日期</th>
     	    <th class="END_DATE" data-column="db">失效日期</th>
     	    <th class="RESP_NAME" data-column="db">职责</th>
     	    <th class="USER_TYPE" data-column="db">用户类别</th>
     	    <th class="EMP_NUMBER" data-column="db">员工工号</th>
     	    <th class="EMP_NAME" data-column="db">员工名称</th>
     	    <th class="GROUP_NAME" data-column="db">工作组</th>
     	    <th class="USER_IMG" data-column="normal">用户头像</th>
     	    <th class="ACTION" data-column="normal">操作</th> 
     	    <th class="USER_ID" style="display:none" data-column="hidden">&nbsp;</th>
     	    <th class="IMG_URL" style="display:none" data-column="hidden">&nbsp;</th>
     	  </tr>
     	  <tr>
     	    <td class="USER_NAME" data-column="db"></td>
            <td class="DESCRIPTION" data-column="db"></td>
     	    <td class="START_DATE" data-column="db"></td>
     	    <td class="END_DATE" data-column="db"></td>
     	    <td class="RESP_NAME" data-column="db"></td>
     	    <td class="USER_TYPE" data-column="db"></td>
     	    <td class="EMP_NUMBER" data-column="db"></td>
     	    <td class="EMP_NAME" data-column="db"></td>
     	    <td class="GROUP_NAME" data-column="db"></td>
     	    <td class="USER_IMG" data-column="normal">
     	      <i class="fa fa-picture-o changeIMG pointer hidden" title="更改头像" data-show="true" data-reveal-id="headimg"></i>
     	    </td>
     	    <td class="ACTION" data-column="normal">
     	      <i class="fa fa-pencil fa-fw update pointer hidden" data-show="true" title="更新客户" data-reveal-id="ui" data-dismissmodalclass="close-ui-frame" data-crudtype="pre-update" data-preupdateurl="user/userPreUpdate.do" data-type="update" data-updateparam=["USER_ID",".USER_ID"]  data-func="$().setUpdatePWD();"></i>
     	      &nbsp;
     	      <i class="fa fa-user-circle fa-fw pointer hidden show_detail hidden" data-show="true" title="客户分配" data-reveal-id="detail" data-dismissmodalclass="close-detail-frame"></i>
     	    </td>
     	    <td class="USER_ID" style="display:none" data-column="hidden">&nbsp;</td>
     	    <td class="IMG_URL" style="display:none" data-column="hidden">&nbsp;</td>
     	  </tr>
        </table>
      </div>
      <!-- 主表格区域 end -->
   
      <!-- 主表格按钮区域 start -->
      <div class="table_button" id="table" data-table="User">
        <div class="setting">
          <i class="fa fa-cog pointer" data-reveal-id="setting" title="表格设置" data-dismissmodalclass="close-setting"></i>
        </div>
        <div class="setting">
          <i class="fa fa-search pointer" data-reveal-id="query" title="条件查询" data-dismissmodalclass="close-query-frame"></i>
        </div>
        <div class="setting">
          <i class="fa fa-user-plus pointer" data-reveal-id="ui" title="新增用户" data-dismissmodalclass="close-ui-frame" data-crudtype="pre-insert" data-type="insert" data-func="$().beforeInsert(); " data-revealfunc="$().afterReveal(); " ></i>
        </div>
        <div class="setting">
          <i id='refresh' class="fa fa-refresh pointer" title="刷新数据" data-pagetype='refresh' data-pageframe="table"></i>
        </div>
        <div id="setting">
          <!-- 设置菜单区域 start -->
          <jsp:include page="../public/setting.jsp" >
			<jsp:param name="rdtable" value="#user" />
			<jsp:param name="odtable" value="#user" />
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
          <input type="hidden" data-type="size" id="page_size" value="10"/>
          <input type="hidden" data-type="number" id="page_no" value="1"/>
          <input type="hidden" data-type="orderby" id="ORDER_BY" value="USER_ID"/> 
          <input type="hidden" data-type="cond"/>
          <input type="hidden" data-type="url" value="user/getUserPage.do"/>
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
      
      <!-- 条件查询区域start -->
      <div id='query' class='query_frame'>     
        <div class='title pointer'>      
          <span><i class="fa fa-user"></i>&nbsp;用户查询</span>
        </div>
        <a class="close-query-frame" data-type="close">&#215;</a>
        <div class='line'></div>
        <div class='content'>
          <form>
            <label for='USER_NAME_Q' class='left mid'>用户账号:</label> 
            <input type='text' id='USER_NAME_Q' name='USER_NAME' class='left mid' data-modify='true' data-pageframe="query" data-validurl='lov/validUserName.do' data-queryurl='lov/getUserId.do' data-lovbtn='USER_LOV_Q' data-hiddenid=["USER_ID_Q","DESCRIPTION_Q"] data-hiddenval=["USER_ID","DESCRIPTION"] data-param="username" />          
            <input type='hidden' id='USER_ID_Q' name='USER_ID'/>
            <input type='button' id="USER_LOV_Q" class='left button pointer' data-pageframe="lov" data-reveal-id="lov" data-bg="lov-modal-bg" data-dismissmodalclass='close-lov' data-lovname="发件人查询" data-queryurl="lov/getUserPage.do" data-jsontype="user" data-defaultquery="true" data-th=["用户id","发件账号","发件人"] data-td=["USER_ID","USER_NAME","DESCRIPTION"] data-selectname=["发件账号","发件人"] data-selectvalue=["USER_NAME","DESCRIPTION"] data-choose=[".USER_ID",".USER_NAME",".DESCRIPTION"] data-recid=["#USER_ID_Q","#USER_NAME_Q","#DESCRIPTION_Q"] value="···"/>
            <label for='DESCRIPTION_Q' class='left mid'>用户名称:</label> 
            <input type='text' id='DESCRIPTION_Q' name="DESCRIPTION" class="long" readonly="readonly"/>  
            <label for='RESP_NAME_Q' class='left mid'>职责:</label> 
            <input type='text' id='RESP_NAME_Q' name='RESP_NAME' data-update="db" class='left mid' data-modify='true' data-pageframe="query" data-validurl='lov/validRespName.do' data-queryurl='lov/getRespId.do' data-lovbtn='RESP_LOV' data-hiddenid=["RESP_ID_Q"] data-hiddenval=["RESP_ID"] data-param="respname"/>
            <input type='hidden' id='RESP_ID_Q' name='RESP_ID' data-update="db"/>
            <input type='button' id="RESP_LOV_Q" class='left button pointer' data-pageframe="lov" data-reveal-id="lov" data-bg="lov-modal-bg" data-dismissmodalclass='close-lov' data-lovname="职责查询" data-queryurl="lov/getRespPage.do" data-jsontype="resp" data-defaultquery="true" data-th=["职责ID","职责编码","职责名称","描述"] data-td=["RESP_ID","RESP_CODE","RESP_NAME","RESP_DESC"] data-selectname=["职责编码","职责名称"] data-selectvalue=["RESP_CODE","RESP_NAME"] data-choose=[".RESP_ID",".RESP_NAME"] data-recid=["#RESP_ID_Q","#RESP_NAME_Q"] value="···"/>
            <label for='USER_TYPE_Q' class='left mid'>用户类型:</label>
            <select class='left long' id='USER_TYPE_Q' name='USER_TYPE' data-update="db" required='required' data-notnull="true" data-listurl="list/getUserType.do"></select> 
            <br style="clear:both"/>
            <label for="START_DATE_F" class="left mid">启用日期:</label>
            <input type="text" id="START_DATE_F" name="START_DATE_F" class="left long" data-datatype="date" placeholder="起始启用日期"/>
            <input type="text" id="START_DATE_T" name="START_DATE_T" class="left long" data-datatype="date" placeholder="截止启用日期"/>
            <br style="clear:both"/>
            <label for="END_DATE_F" class="left mid">失效日期:</label>
            <input type="text" id="END_DATE_F" name="END_DATE_F" class="left long" data-datatype="date" placeholder="起始失效日期"/>
            <input type="text" id="END_DATE_T" name="END_DATE_T" class="left long" data-datatype="date" placeholder="截止失效日期"/>
          </form> 
        </div>
        <div class='foot'>             
          <button class="right pointer"  data-buttonframe="table" data-crudtype="query" data-pageframe="query">用户查询</button>
        </div> 
      </div>
      <!-- 条件查询区域end -->
     
      <!-- 更新/新增用户区域 start -->
      <div id='ui' class='update_frame'>     
        <div class='title pointer'>      
          <span data-type="update"><i class="fa fa-user fa-1x" aria-hidden="true"></i>&nbsp;更新用户</span>
          <span data-type="insert"><i class="fa fa-user fa-1x" aria-hidden="true"></i>&nbsp;新增用户</span>
        </div>
        <a class="close-ui-frame" data-type="close">&#215;</a>
        <div class='line'></div>
        <div class='content'>
          <form id='updateData'>
            <input type='hidden' id='U_ID' name="U_ID" data-update="db"/>
            <label for='USER_NAME' class='left'>用户账号</label>
            <input type='text' id='USER_NAME' name='USER_NAME' data-update="db" required='required' class='left' />
            <br style="clear:both"/>
            <label for='DESC' class='left'>用户名称</label>
            <input type='text' id='DESC' name='DESC' data-update="db" required='required' class='left'/>
            <label for='USER_TYPE' class='left'>用户类型</label>
            <select class='left long' id='USER_TYPE' name='USER_TYPE' data-update="db" required='required' data-notnull="true" data-listurl="list/getUserType.do"></select> 
            <label for='START_DATE' class='left'>启用日期</label>
            <input type='text' id='START_DATE' name='START_DATE' data-update="db" data-datatype="date" required='required' class='left'/>
            <label for='END_DATE' class='left'>失效日期</label>
            <input type='text' id='END_DATE' name='END_DATE' data-update="db" data-datatype="date" class='left'/>
            <label for='PASSWORD' class='left'>密码</label>
            <input type='password' id='PASSWORD' name='PASSWORD' data-update="db" class='left password'/>
            <i class="fa fa-eye-slash pointer left" data-pwd="show"></i>
            <i class="fa fa-eye pointer left hide" data-pwd="hide"></i>     
            <input type="hidden" id="ENCRYPTED_USER_PASSWORD" name="ENCRYPTED_USER_PASSWORD" data-update="db"/>
            <input type="hidden" id="PASSWORD_DATE" name="PASSWORD_DATE" data-update="db"/>
            <label for="RESP_NAME" class="left">职责</label> 
            <input type="text" id="RESP_NAME" name="RESP_NAME" data-update="db" class="left short" data-modify="true" required="required" data-pageframe="ui" data-validurl="lov/validRespName.do" data-queryurl="lov/getRespId.do" data-lovbtn="RESP_LOV" data-hiddenid=["RESP_ID"] data-hiddenval=["RESP_ID"] data-param="respname"/>
            <input type="hidden" id="RESP_ID" name="RESP_ID" data-update="db"/>
            <input type="button" id="RESP_LOV" class="left button pointer" data-pageframe="lov" data-reveal-id="lov" data-bg="lov-modal-bg" data-dismissmodalclass="close-lov" data-lovname="职责查询" data-queryurl="lov/getRespPage.do" data-jsontype="resp" data-defaultquery="true" data-th=["职责ID","职责编码","职责名称","描述"] data-td=["RESP_ID","RESP_CODE","RESP_NAME","RESP_DESC"] data-selectname=["职责编码","职责名称"] data-selectvalue=["RESP_CODE","RESP_NAME"] data-choose=[".RESP_ID",".RESP_NAME"] data-recid=["#RESP_ID","#RESP_NAME"] value="···"/>
            <br style="clear:both"/>
            <label for="EMP_NAME" class="left">员工名称</label> 
            <input type="text" id="EMP_NAME" name="EMP_NAME" data-update="db" class="left short" data-modify="true" data-pageframe="ui" data-validurl="lov/validEmpName.do" data-queryurl="lov/getEmpId.do" data-lovbtn="EMP_LOV" data-hiddenid=["EMP_ID"] data-hiddenval=["EMP_ID"] data-param="ename"/>
            <input type="hidden" id='EMP_ID' name="EMP_ID" data-update="db"/>
            <input type="button" id="EMP_LOV" class="left button pointer" data-pageframe="lov" data-reveal-id="lov" data-bg="lov-modal-bg" data-dismissmodalclass="close-lov" data-lovname="员工查询" data-queryurl="lov/getEmpPage.do" data-jsontype="emp" data-defaultquery="true" data-th=["员工ID","员工姓名","工号"] data-td=["EMP_ID","ENAME","ENO"] data-selectname=["员工姓名","工号"] data-selectvalue=["ENAME","ENO"] data-choose=[".EMP_ID",".ENAME"] data-recid=["#EMP_ID","#EMP_NAME"] value="···"/>
            <label for="GROUP_NAME" class="left">工作组</label>
            <input type="text" id="GROUP_NAME" name="GROUP_NAME" data-update="db" class="left short" data-modify="true" data-pageframe="ui" data-validurl="lov/validGroupName.do" data-queryurl="lov/getGroupId.do" data-lovbtn="GROUP_LOV" data-hiddenid=["GROUP_ID"] data-hiddenval=["GROUP_ID"] data-param="groupName"/>
            <input type="hidden" id="GROUP_ID" name="GROUP_ID" data-update="db"/>
            <input type="button" id="GROUP_LOV" class="left button pointer" data-pageframe="lov" data-reveal-id="lov" data-bg="lov-modal-bg" data-dismissmodalclass='close-lov' data-lovname="工作组查询" data-queryurl="lov/getGroupPage.do" data-jsontype="group" data-defaultquery="true" data-th=["工作组ID","工作组编码","工作组名称","描述"] data-td=["GROUP_ID","GROUP_CODE","GROUP_NAME","DESCRIPTION"] data-selectname=["工作组编码","工作组名称"] data-selectvalue=["GROUP_CODE","GROUP_NAME"] data-choose=[".GROUP_ID",".GROUP_NAME"] data-recid=["#GROUP_ID","#GROUP_NAME"]  value="···"/>
            <input type="hidden" id="IMG_URL" name="IMG_URL" data-update="db"/>
          </form>
        </div>
        <div class='foot'>       
          <button class="right update_confirm pointer" data-type="update" data-crudtype="update" data-pageframe="ui" data-updateurl="user/update.do" data-func="$().validateUserType();">提交更新</button>
          <button class="right update_confirm pointer" data-type="insert" data-crudtype="insert" data-pageframe="ui" data-inserturl="user/insert.do" data-func="$().validateUserType();">新增</button>
        </div>    
      </div> 
      <!-- 更新/新增用户区域 end -->
     
<!----------------------------------------------客户分配---------------------------------------------------------->      
      
      <div class="detail_frame" id="detail">
        <div class='title pointer'>      
          <span><i class="fa fa-handshake-o"></i>&nbsp;客户分配</span>
        </div>
        <a class="close-detail-frame" data-type="close">&#215;</a>
        <div class="detail_header">
          <input type="hidden" id="USER_ID_LINES" />
          <label class="mid" for="USER_NAME_LINES">用户账号</label>
          <input type="text" id="USER_NAME_LINES" class="long" readonly="readonly"/>
          <label class="mid" for="DESC_LINES">用户名</label>
          <input type="text" id="DESC_LINES" class="long" readonly="readonly"/>
          <br style="clear:both"/>
        </div>
        
        <!-- 客户分配区域 start -->
        <div class='detail_table'>
          <table id="cust" data-table="custLine">
            <tr>
              <th class="ORG_NAME" data-column="db">销售公司</th>
              <th class="PARTY_NAME" data-column="db">客户名称</th>
              <th class="ACCOUNT_NUMBER" data-column="db">客户账号</th>
     	      <th class="ACCOUNT_NAME" data-column="db">账号名称</th>
     	      <th class="START_DATE" data-column="db">启用时间</th>
     	      <th class="END_DATE" data-column="db">失效时间</th>
     	      <th class="ACTION" data-column="normal">操作</th>   	      
     	      <th class="USER_CUST_ID" style="display:none" data-column="hidden">&nbsp;</th>
     	    </tr>
     	    <tr>
     	      <td class="ORG_NAME" data-column="db"></td>
     	      <td class="PARTY_NAME" data-column="db"></td>
              <td class="ACCOUNT_NUMBER" data-column="db"></td>
     	      <td class="ACCOUNT_NAME" data-column="db"></td>
     	      <td class="START_DATE" data-column="db"></td>
     	      <td class="END_DATE" data-column="db"></td>
     	      <td class="ACTION" data-column="normal">
     	        <i class="fa fa-pencil fa-fw update pointer" data-reveal-id="detail_ui" data-bg="detail-modal-bg" data-dismissmodalclass="close-detail-ui-frame" data-crudtype="pre-update" data-preupdateurl="cust/preUpdate.do" data-type="update" data-updateparam=["USER_CUST_ID",".USER_CUST_ID"] data-func="$().preUpdateUC();"></i>
     	      </td>
     	      <td class="USER_CUST_ID" style="display:none" data-column="hidden">&nbsp;</td>     	     
     	    </tr>
          </table>
        </div>
        <!-- 客户分配区域 end --> 
        
        <div class="table_button" id="sub_table" data-table="custLine">
          <div class="setting none">
            <i class="fa fa-search pointer" data-reveal-id="query" data-dismissmodalclass="close-query-frame"></i>
          </div>
          <div class="setting">
            <i class="fa fa-plus-circle pointer" data-reveal-id="detail_ui" data-bg="detail-modal-bg" data-dismissmodalclass="close-detail-ui-frame" data-crudtype="pre-insert" data-type="insert" data-func="$().preInsertUC();" ></i>
          </div>
          <div class="setting">
            <i id='sub_refresh' class="fa fa-refresh pointer" data-pagetype='refresh' data-pageframe="sub_table" data-func="$().setParam();"></i>
          </div>
          <div>
            <!-- 客户分配分页按钮区域 start -->
            <jsp:include page="../public/pageArrow.jsp" >
			  <jsp:param name="pageframe" value="sub_table" />
			  <jsp:param name="func" value="$().setParam();" />
		    </jsp:include>
            <!-- 客户分配分页按钮区域 end -->
            <input type="hidden" data-type="size" value="5"/>
            <input type="hidden" data-type="number" value="1"/>
            <input type="hidden" data-type="orderby" value="USER_CUST_ID ASC"/> 
            <input type="hidden" data-type="cond"/>
            <input type="hidden" data-type="url" value="cust/getUserCustPage.do"/>
            <input type="hidden" data-type="jsontype" value="subtable"/> 
          </div>
        </div>
      </div>
    </div>      
    
    <!-- 用户客户分配区域 start -->
    <div id='detail_ui' class='detail_update_frame'>     
        <div class='title pointer'>      
          <span data-type="update"><i class="fa fa-handshake-o fa-1x" aria-hidden="true"></i>&nbsp;更新客户状态</span>
          <span data-type="insert"><i class="fa fa-handshake-o fa-1x" aria-hidden="true"></i>&nbsp;新增客户</span>
        </div>
        <a class="close-detail-ui-frame" data-type="close">&#215;</a>
        <div class='line'></div>
        <div class='content'>
          <form id='updateDetailData'>
            <input type='hidden' id='USER_CUST_ID' name="USER_CUST_ID" data-update="db" value=""/>
            <label for='PARTY_NAME' class='left'>客户名称</label> 
            <input type="text" id="PARTY_NAME" name="PARTY_NAME" data-update="db" class="left longlov" readonly="readonly"/>
            <input type='hidden' id='CUSTOMER_ID' name='CUSTOMER_ID' data-update="db"/>
            <input type='button' id="PARTY_NAME_LOV" class='left button pointer' data-pageframe="lov" data-reveal-id="lov" data-bg="lov-modal-bg" data-dismissmodalclass='close-lov' data-lovname="客户查询" data-queryurl="lov/getCustPage.do" data-jsontype="cust" data-defaultquery="true" data-th=["销售公司ID","销售公司","客户ID","客户名称","客户账号"] data-td=["ORG_ID","ORG_NAME","CUST_ID","PARTY_NAME","ACCOUNT_NUMBER"] data-selectname=["客户名称","客户账号"] data-selectvalue=["PARTY_NAME","ACCOUNT_NUMBER"] data-choose=[".ORG_ID",".ORG_NAME",".CUST_ID",".PARTY_NAME"] data-recid=["#ORG_ID","#ORG_NAME","#CUSTOMER_ID","#PARTY_NAME"] value="···"/>  
            <br style="clear:both"/>
            <label for='ORG_NAME' class='left'>销售公司</label> 
            <input type="text" id="ORG_NAME" name="ORG_NAME" data-update="db" class="left long" readonly="readonly"/>
            <input type="hidden" id="ORG_ID" name="ORG_ID" data-update="db" readonly="readonly"/>
             <br style="clear:both"/>
            <label for='START_DATE_DETAIL' class='left'>启用日期</label>
            <input type='text' id='START_DATE_DETAIL' name='START_DATE' data-update="db" data-datatype="date" required='required' class='left'/>
            <label for='END_DATE_DETAIL' class='left'>失效日期</label>
            <input type='text' id='END_DATE_DETAIL' name='END_DATE' data-update="db" data-datatype="date" class='left'/>
          </form>
        </div>
        <div class='foot'>       
          <button class="right update_confirm pointer" data-type="update" data-crudtype="update" data-pageframe="detail_ui" data-updateurl="cust/update.do" data-refresh="sub_refresh">提交更新</button>
          <button class="right update_confirm pointer" data-type="insert" data-crudtype="insert" data-pageframe="detail_ui" data-inserturl="cust/insert.do" data-refresh="sub_refresh" data-func="$().setParam();">新增</button>
        </div>    
      </div>   
    <!-- 用户客户分配区域 end -->  
<!----------------------------------------------客户分配-------------------------------------------------------- -->       
      
      <!-- 用户头像区域 start -->
      <jsp:include page="../public/headImg.jsp">
        <jsp:param name="headImgType" value="admin" />
      </jsp:include>
      <!-- 用户头像区域 end -->
      
      <!-- 用户信息存放区域 start -->
      <input type="hidden" id="USER_ID" value="${USER_ID}"/>  
      <input type="hidden" id="INTERACT_CODE" value="USER_MANAGE"/> 
      <input type="hidden" id="HEADER_ID" value=""/> 
      <!-- 用户信息存放区域 end -->  
    </div>   
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
    		
    		$('i[data-pwd]').on('click',function(){
    		    if($(this).data('pwd')=='show'){
    		        $('i[data-pwd="hide"]').css('display','block');
    		        $(this).css('display','none');
    		        $('#PASSWORD').attr('type','text');
    		    }else if($(this).data('pwd')=='hide'){
    		        $('i[data-pwd="show"]').css('display','block');
    		        $(this).css('display','none');
    		        $('#PASSWORD').attr('type','password');
    		    }
    		});
    		
    		$.fn.changeIMG = function(){
    		    $('.changeIMG').off('click');
    		    $('.changeIMG').on('click',function(){
    		        $('.cropped_old img').removeAttr('src');
    		        $('.imageBox').css('background-image','');
    		        $('.cropped_new img').attr('src','/image/user/system_blank.png');
    		    	tr=$(this).parent().parent();
    		    	imgurl='/image/user/'+tr.children('.IMG_URL').text();
    		    	$('#headimg_uid').val(tr.children('.USER_ID').text());
    		    	$('#headimg_uname').val(tr.children('.USER_NAME').text());
    		    	$('.cropped_old img').attr('src',imgurl+'?temp=' + Math.random()); 
    			});
    		}
    		
    		$.fn.beforeInsert = function(){
    		    $('#PASSWORD').attr('required','required');
    		    $('#USER_NAME').removeAttr('readonly');
    		}
    		
    		$.fn.afterReveal = function(){
    		    $('#START_DATE').val(new Date().format('yyyy-MM-dd hh:mm:ss'));
    		    $('label[for="USER_NAME"]').click();
    		}
    		
    		$.fn.setUpdatePWD = function(){
    		    $('#PASSWORD').removeAttr('required');
    		    $('#USER_NAME').attr('readonly','readonly');
    		}
    		
    		$.fn.validateUserType = function(){
    		    if($('#USER_TYPE').val()=='EMP'){
    		      if($('#EMP_ID').val()==null||$('#EMP_ID').val()==''){
    		          layer.alert('员工必须维护员工名称!',{title:'警告',offset: [150]});
    		          throw new error('员工必须维护员工名称!');
    		      }else if($('#GROUP_ID').val()==null||$('#GROUP_ID').val()==''){
    		          layer.alert('员工必须维护工作组!');
    		          throw new error('员工必须维护工作组!',{title:'警告',offset: [150]});
    		      }
    		    }	    
    		}
    		
    		$.fn.setParam = function(){
    		    userId=$('#USER_ID_LINES').val();
    		    param=param+'&USER_ID='+userId;
    		}
    		
    		$.fn.detailShow = function(){
    		    $('.show_detail').off('click');
    		    $('.show_detail').on('click',function(){
    		        $('#detail').css('margin-left','-400px'); 
    		        tr=$(this).parent().parent();
    		        userId=tr.children('.USER_ID').text();
    		    	userName=tr.children('.USER_NAME').text();
    		    	userDesc=tr.children('.DESCRIPTION').text();
    		    	$('.detail_header input').val('');
    		    	$('#sub_table input[data-type="number"]').val('1');
    		    	$('#USER_ID_LINES').val(userId);
    		    	$('#USER_NAME_LINES').val(userName);
    		    	$('#DESC_LINES').val(userDesc);
    		    	$('#sub_refresh').click();
    		    });    		   
    		} 
    		
    		$.fn.preInsertUC = function(){
    		    $('#START_DATE_DETAIL').removeAttr('readonly');
    		    $('#PARTY_NAME_LOV').removeAttr('disabled');
    		}
    		
    		$.fn.preUpdateUC = function(){
    		    $('#START_DATE_DETAIL').attr('readonly','readonly');
    		    $('#PARTY_NAME_LOV').attr('disabled','disabled');
    		}
    		//默认值
    		//$('#START_DATE_F').val(new Date().addMonths(-2).format('yyyy-MM-dd hh:mm:ss'));
    		//$('#START_DATE_T').val(new Date().format('yyyy-MM-dd hh:mm:ss'));
    		//日期选择2016.12.8 by sam.t
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
                    	$('.USER_ID',$('#user tr:eq('+(i+1)+')')).html(data.rows[i].USER_ID); 
                   	 	$('.USER_NAME',$('#user tr:eq('+(i+1)+')')).html(data.rows[i].USER_NAME); 
                    	$('.DESCRIPTION',$('#user tr:eq('+(i+1)+')')).html(data.rows[i].DESCRIPTION); 
                    	$('.START_DATE',$('#user tr:eq('+(i+1)+')')).html(data.rows[i].START_DATE);   
                    	$('.END_DATE',$('#user tr:eq('+(i+1)+')')).html(data.rows[i].END_DATE); 
                    	$('.RESP_NAME',$('#user tr:eq('+(i+1)+')')).html(data.rows[i].RESP_NAME); 
                    	$('.USER_TYPE',$('#user tr:eq('+(i+1)+')')).html(data.rows[i].USER_TYPE_M); 
                    	if(data.rows[i].USER_TYPE=='CUSTOMER'){
                    	  $('.ACTION i[data-reveal-id="detail"]',$('#user tr:eq('+(i+1)+')')).css('visibility','visible');
                    	}else{
                    	  $('.ACTION i[data-reveal-id="detail"]',$('#user tr:eq('+(i+1)+')')).css('visibility','hidden');
                    	};
                    	$('.EMP_NUMBER',$('#user tr:eq('+(i+1)+')')).html(data.rows[i].EMP_NUMBER); 
                    	$('.EMP_NAME',$('#user tr:eq('+(i+1)+')')).html(data.rows[i].EMP_NAME); 
                    	$('.GROUP_NAME',$('#user tr:eq('+(i+1)+')')).html(data.rows[i].GROUP_NAME); 
                    	$('.IMG_URL',$('#user tr:eq('+(i+1)+')')).html(data.rows[i].IMG_URL); 
                	}
                	$().crudListener();
                	$().detailShow();
                	$().changeIMG(); 
                	$().revealListener(); 
        	    }else if(JSONtype=='subtable'){
        	        for(i=0;i<(pageMaxRow-pageMinRow+1);i++){
        	            $('.ORG_NAME',$('#cust tr:eq('+(i+1)+')')).html(data.rows[i].ORG_NAME); 
                   	 	$('.PARTY_NAME',$('#cust tr:eq('+(i+1)+')')).html(data.rows[i].PARTY_NAME); 
                    	$('.ACCOUNT_NUMBER',$('#cust tr:eq('+(i+1)+')')).html(data.rows[i].ACCOUNT_NUMBER);
                    	$('.ACCOUNT_NAME',$('#cust tr:eq('+(i+1)+')')).html(data.rows[i].ACCOUNT_NAME); 
                    	$('.USER_CUST_ID',$('#cust tr:eq('+(i+1)+')')).html(data.rows[i].USER_CUST_ID);   
                    	$('.START_DATE',$('#cust tr:eq('+(i+1)+')')).html(data.rows[i].START_DATE);   
                    	$('.END_DATE',$('#cust tr:eq('+(i+1)+')')).html(data.rows[i].END_DATE);   
        	        }
        	        width='-'+parseInt($('#detail').css('width'))/2+'px';
			        $('#detail').css('margin-left',width); 
        	        $().crudListener();
                	$().revealListener(); 
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
        	    }else if(JSONtype=='emp'){
        	        if(pageMaxRow==0&&pageMinRow==0){
        	            console.log('no data');
        	        }else{
        	            for(i=0;i<(pageMaxRow-pageMinRow+1);i++){
       	            	    $('td:eq(0)',$('.contentbox tr:eq('+(i+1)+')')).html(data.rows[i].EMP_ID);  
       	            	    $('td:eq(1)',$('.contentbox tr:eq('+(i+1)+')')).html(data.rows[i].ENAME);     	                    
       	            	    $('td:eq(2)',$('.contentbox tr:eq('+(i+1)+')')).html(data.rows[i].EMPNO);     	               	        
       	            	} 
        	        }       	            	    
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
       	        if(pageframe=='ui'){ 
       	        	$('#U_ID').val(data.rows[0].USER_ID);
       	        	$('#USER_NAME').val(data.rows[0].USER_NAME);
       	        	$('#DESC').val(data.rows[0].DESCRIPTION);
       	        	$('#USER_TYPE').val(data.rows[0].USER_TYPE);
       	        	$('#START_DATE').val(data.rows[0].START_DATE);
       	        	$('#ENCRYPTED_USER_PASSWORD').val(data.rows[0].ENCRYPTED_USER_PASSWORD);
       	        	$('#PASSWORD_DATE').val(data.rows[0].PASSWORD_DATE);
       	        	$('#RESP_ID').val(data.rows[0].RESP_ID);
       	        	$('#RESP_NAME').val(data.rows[0].RESP_NAME);
       	        	$('#EMP_ID').val(data.rows[0].EMP_ID);
       	        	$('#EMP_NAME').val(data.rows[0].EMP_NAME);
       	        	$('#GROUP_ID').val(data.rows[0].USER_GROUP_ID);
                	$('#GROUP_NAME').val(data.rows[0].GROUP_NAME);
       	        	$('#IMG_URL').val(data.rows[0].IMG_URL);    
       	        }else if(pageframe='detail_ui'){
       	            $('#ORG_NAME').val(data.rows[0].ORG_NAME);
       	            $('#ORG_ID').val(data.rows[0].ORG_ID);
       	            $('#PARTY_NAME').val(data.rows[0].PARTY_NAME);
       	            $('#CUSTOMER_ID').val(data.rows[0].CUSTOMER_ID);
       	        	$('#START_DATE_DETAIL').val(data.rows[0].START_DATE);
       	        	$('#END_DATE_DETAIL').val(data.rows[0].END_DATE);
       	        	$('#USER_CUST_ID').val(data.rows[0].USER_CUST_ID);
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