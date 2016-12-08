/*********************************************************
                    jQuery 多维排序功能
                    Create Date:2016.6.29
                    Create By:bird
                    Last Update Date:2016.7.13
                    Last Update By:bird
                          修改日志
           2016.6.29   创建文件
           2016.7.13   优化代码,并新增插件配置说明       
		   2016.8.11   整合Spring框架进行的修改:和数据库栏位相关的变量统一大写。另外，插件js名称添加irr关键字。 by sam.t     
*********************************************************/

(function($) {                         	
	/******************listener start***********************
				监听<a>标签的data-ordertable属性
				监听<button>标签的data-order属性
		  如需对更多标签进行绑定，请在listener区域绑定新的监听标签
	*********************************************************/	
	$.fn.orderListener = function(){ 
		/****绑定<a>标签****/
		$('a[data-ordertable]').on('click', function(e) {	
			e.preventDefault();//阻止<a>标签默认的点击事件（超链接跳转）
			$(this).orderby($(this).data());
		});
		/****绑定<button>标签****/
		$('button[data-order]').on('click', function(e) {	
			$(this).order($(this).data());
		});
		
	}   
	/******************listener end***********************/	
	
	/****执行监听函数****/
	$().orderListener();

    $.fn.orderby = function(options) {	        
		/*********************************************
                           设置默认属性
        *********************************************/	    
		var defaults={
			setframe:'#setting',
			orderframe:'#orderby',
			orderpara:'#ORDER_BY'
		}		
		/****继承默认属性****/
        var options = $.extend({}, defaults, options); 
			
        return this.each(function() {
        	lastindex=($(options.ordertable+' td').length/($(options.ordertable+' tr').length-1))-1;
        	$(options.setframe).css('visibility','hidden');
        	$(options.orderframe+' select').html('');
        	$(options.orderframe+' input[value="ASC"]').prop("checked","checked");
        	$(options.orderframe+' select').append('<option value="blank"></option>');
        	for(i=0;i<lastindex;i++){
        		value=$(options.ordertable+' th:eq('+i+')').attr('class');
        		name=$(options.ordertable+' th:eq('+i+')').text();
        		column=$(options.ordertable+' th:eq('+i+')').attr('data-column');
        		if(column=="db"){
        			$(options.orderframe+' select').append('<option value="'+value+'" >'+name+'</option>');	
        		}else{
        			continue;
        		}	
        	}
        	order_by=$(options.orderpara).val();
        	order_group=new Array;
        	order_group=order_by.split(",");
        	order_count=order_group.length;
        	for(i=0;i<order_count;i++){
        		order_detail=new Array;
        		order_detail=order_group[i].split(" ");
        		a=i+1;
        		column='col'+a;
        		$('#'+column+' option[value="'+order_detail[0]+'"]').prop('selected',true);
        		$('#'+column+' input[value="'+order_detail[1]+'"]').prop("checked","checked");
        	}
		});
	}
    
    $.fn.order = function(options) {	        
		/*********************************************
                           设置默认属性
        *********************************************/	    
		var defaults={
			col1:'col1',
			col2:'col2',
			col3:'col3',
			orderpara:'#ORDER_BY',
			pageno:'#page_no',
			refresh:'#refresh',
			orderframe:'#orderby'		
		}		
		/****继承默认属性****/
        var options = $.extend({}, defaults, options); 
			
        return this.each(function() {
        	if(options.order==true){
        		orderby=null;
            	col1=$('#'+options.col1+' option:selected').val();
            	col2=$('#'+options.col2+' option:selected').val();
            	col3=$('#'+options.col3+' option:selected').val(); 	
            	if(col1!='blank'){
            		order1=$('input[name="'+options.col1+'"]:checked ').val(); 
            		orderby=col1+' '+order1;
            		if(col2!='blank'){
                		order2=$('input[name="'+options.col2+'"]:checked ').val(); 
                		orderby=orderby+','+col2+' '+order2;
                		if(col3!='blank'){
                    		order3=$('input[name="'+options.col3+'"]:checked ').val(); 
                    		orderby=orderby+','+col3+' '+order3;
                    	}
                	}else{
            			if(col3!='blank'){
            				alert('排序二不能为空！');
            				return;
                    	}
            		}
            	}
            	else{
            		if(col2=='blank'){
            			if(col3=='blank'){
            				alert('排序不能为空！');
            			}else{
            				alert('排序一，排序二不能为空！');
            			}
            		}else{
            			alert('排序一不能为空！');
            		}
            	}
            	
            	if(orderby!=null){
            		$(options.orderpara).val(orderby);
            		$(options.pageno).val(1);
    				$(options.refresh).click();
    				$(options.orderframe+' .close-reveal-modal').click();
            	}       		
        	}else{
        		alert('多维排序功能暂未启用，请联系IT部门人员')
        	}       	
		});
	}
})(jQuery);
/*****************************插件配置说明*****************************
@                    $.fn.orderby 配置参数                             
@    必需参数：														 
@    data-ordertable:
@    data-ordertable 为要进行多维排序的表格的id
@    非必需参数：
@	 data-setframe:设置框id
@    不设置此参数则默认为'#setting'
@	 data-orderframe:排序面板id
@    不设置此参数则默认为'#orderby'
@	 data-orderpara:排序参数值存放标签id
@    不设置此参数则默认为'#order_para'
@
@					$.fn.order 配置参数   
@    必需参数：														 
@    data-order:
@    data-order 为多维排序按钮的启用标志，如果为true，则为按钮启用
@    反之false则为暂未启用
@    非必需参数：
@	 data-col1:维度一设置框
@    不设置此参数则默认为'col1'
@	 data-col2:维度二设置框
@    不设置此参数则默认为'col2'
@	 data-col3:维度三设置框
@    不设置此参数则默认为'col3'
@	 data-orderframe:排序面板id
@    不设置此参数则默认为'#orderby'
@	 data-orderpara:排序参数值存放标签id
@    不设置此参数则默认为'#order_para'
@    data-pageno:页码框id
@    不设置此参数则默认为'#page_no'
@	 data-refresh:刷新按钮id
@    不设置此参数则默认为'#refresh'
@
**********************************************************/