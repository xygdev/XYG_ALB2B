<%@ page contentType="text/html;charset=utf-8" language="java" pageEncoding="utf-8" %>
<html>
  <body>
    <!-- 多维排序区域 start -->
    <div id="orderby">
       <div class="title pointer">
         <span class="title_name">多维排序</span>
       </div>
       <a class="close-reveal-modal">&#215;</a>
       <div class="line"></div>
       <div class="contain">
         <div class="item" id="col1">
           <span>排序一：</span>
           <select class="select"></select>
		   <input type="radio" name="col1" class="col1" checked="checked" value="ASC" />ASC 
           <input type="radio" name="col1" class="col1" value="DESC" />DESC 
         </div>  
         <div class="item" id="col2">
           <span>排序二：</span>
           <select class="select"></select>
		   <input type="radio" name="col2" class="col2" checked="checked" value="ASC" />ASC 
           <input type="radio" name="col2" class="col2" value="DESC" />DESC 
         </div> 
         <div class="item" id="col3">
           <span>排序三：</span>
           <select class="select"></select>
		   <input type="radio" name="col3" class="col3" checked="checked" value="ASC" />ASC 
           <input type="radio" name="col3" class="col3" value="DESC" />DESC 
         </div> 
       </div>
       <div class="footer">
         <button class='right pointer' data-order=true >排序</button>
       </div>
     </div>
     <script>
         $("#orderby").draggable({ handle: ".title" });
     </script>
     <!-- 多维排序区域 end -->
  </body>
</html>