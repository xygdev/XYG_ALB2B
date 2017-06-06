<%@ page contentType="text/html;charset=utf-8" language="java" pageEncoding="utf-8" %>
<html>
  <body>
    <!-- 定义列区域 start --> 
    <div id="row-def">
       <div class="title pointer">
         <span class="title_name">定义列</span>
       </div>
	   <a class="close-reveal-modal">&#215;</a>
       <div class="line"></div>
       <div class="contain">
         <select class="option_frame" data-type="hide" multiple='multiple' title='隐藏'></select>
         <div class="button_frame">
     	   <button class="button" data-rowdefine="hide" data-pageframe="row-def" data-func="$().afterRowDefine();">
     	     <i class="fa fa-angle-left fa-2x"></i>
     	   </button>
     	   <button class="button" data-rowdefine="show" data-pageframe="row-def" data-func="$().afterRowDefine();">
     	     <i class="fa fa-angle-right fa-2x"></i>
     	   </button>
     	   <button class="button" data-rowdefine="hide_all" data-pageframe="row-def" data-func="$().afterRowDefine();">
     	     <i class="fa fa-angle-double-left fa-2x"></i>
     	   </button>
     	   <button class="button" data-rowdefine="show_all" data-pageframe="row-def" data-func="$().afterRowDefine();">
     	     <i class="fa fa-angle-double-right fa-2x"></i>
     	   </button>
         </div>
         <select class="option_frame" data-type="show" multiple='multiple' title='显示'></select>
         <div class="button_frame">
     	   <button class="button" data-rowdefine="up" data-pageframe="row-def" data-func="$().afterRowDefine();">
     	     <i class="fa fa-angle-up fa-2x"></i>
     	   </button>
     	   <button class="button" data-rowdefine="down" data-pageframe="row-def" data-func="$().afterRowDefine();">
     	     <i class="fa fa-angle-down fa-2x"></i>
     	   </button>
     	   <button class="button" data-rowdefine="top" data-pageframe="row-def" data-func="$().afterRowDefine();">
     	     <i class="fa fa-angle-double-up fa-2x"></i>
     	   </button>
     	   <button class="button" data-rowdefine="bottom" data-pageframe="row-def" data-func="$().afterRowDefine();">
     	     <i class="fa fa-angle-double-down fa-2x"></i>
     	   </button>
         </div>
         <input type="hidden" data-type="table">
       </div>
     </div>
     <script type="text/javascript" src="plugin/js/colResizable/colResizable-1.6.js"></script>
     <script>
         $("#row-def").draggable({ handle: ".title" });
         
         $("#main-table").colResizable({
             liveDrag:true, 
             draggingClass:"dragging", 
             //resizeMode:'flex'
             resizeMode:'overflow'
       	 });
       	 
       	 $.fn.afterRowDefine = function(){
    	 	 console.log("colResizable");
    		 //$(window).trigger("resize");
    		 $("#main-table").colResizable({
    			 disable: true	
    		 });
    		 $("#main-table").colResizable({
    			 liveDrag:true, 
            	 draggingClass:"dragging", 
            	 //resizeMode:'flex'
            	 resizeMode:'overflow'
    		 });
    	  }
     </script>
     <!-- 定义列区域 end -->
  </body>
</html>