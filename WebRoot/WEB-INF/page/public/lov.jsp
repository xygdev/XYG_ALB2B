<%@ page contentType="text/html;charset=utf-8" language="java" pageEncoding="utf-8" %>
<html>
  <body>
    <!-- Lov区域 start -->
	<div class='lov_frame' id='lov' data-table="LOV">
  	  <a class="close-lov">&#215;</a>
      <div class='lov_title pointer'>
        <h1 data-type="title">此处填写标题</h1>
      </div>
      <div class='blackline'></div>
      <div class='querybox'>
        <div class="left">
          Search  
        </div>  
        <select data-type="select" class="left"></select>  
        <input type="text" data-type="query_val" class="left" >
        <input type="hidden" data-type="extend_param"/>
        <div class="left lov_button">
          <i class="fa fa-search pointer" data-keyup="enter" data-crudtype="lovquery" data-pageframe="lov"></i>
        </div>
        <div class="left lov_button">
          <i class="fa fa-arrow-circle-left pointer" data-pagetype="prevpage" data-pageframe="lov"></i>
        </div>
        <input type="text" id="lov_page_no" data-type="number" class="left" value="1" readonly='readonly'>
        <div class="left lov_button">
          <i class="fa fa-arrow-circle-right pointer" data-pagetype="nextpage" data-pageframe="lov"></i> 
        </div>   
        <input type="hidden" data-type="size" value="10">
        <input type="hidden" data-type="url">
        <input type="hidden" data-type="jsontype">                
      </div>
      <div class='contentbox'>
        <table data-table="LOV"></table>
      </div>
      <div class='footer'></div>
    </div>
    <div class='lov-modal-bg'></div>
    <script>
         $("#lov").draggable({ handle: ".lov_title" });
     </script>
    <!-- lov区域 end -->
  </body>
</html>
