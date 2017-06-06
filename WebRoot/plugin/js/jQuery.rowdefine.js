/*********************************************************
                    jQuery 定义列功能
                    Create Date:2015.12.9
                    Create By:bird
                    Last Update Date:2016.7.12
                    Last Update By:bird
                          修改日志
           2015.12.9   创建文件
           2016.1.15   解决同时上移（或下移）两个或两个以上的栏位的
                       时候，当栏位到达顶部（或底部）的时候，后面的
                       栏位会顶到前面，的问题
           2016.7.11   代码优化
           2016.7.12   新增插件配置说明 
           2016.8.27   代码参数优化
*********************************************************/

(function($) {
	/******************listener start***********************
	  		  		监听data-rowdefine属性
		        暂时只设置对<button> <a> 两种标签的绑定
		    如需对更多标签进行绑定，请在listener区域绑定新的监听标签
	*********************************************************/	
	$.fn.rowdefineListener = function(){ 	
		/****绑定<button>标签****/
		$('button[data-rowdefine]').on('click', function(e) {
			$(this).rowdefine($(this).data());
		});	
		/****绑定<a>标签****/
		$('a[data-rowdefine]').on('click', function(e) {
			e.preventDefault();/****阻止<a>标签默认的点击事件（超链接跳转）****/
			$(this).rowdefine($(this).data());
		});	
	}   
	/******************listener end***********************/	

	/****执行监听函数****/
	$().rowdefineListener();
	
	$.fn.rowdefine = function(options) {
		/**************************
        		设置默认属性
		**************************/	
		var defaults = {  
			setbutton:'#setting',  /****设置按钮id默认值***/
		}; 
		
		/****继承默认属性****/
        var options = $.extend({}, defaults, options); 
        
        /*****************************
        		  设置全局变量
        *****************************/
        var hide=$('#'+options.pageframe+' select[data-type="hide"]');
        var show=$('#'+options.pageframe+' select[data-type="show"]');
                     	
        return this.each(function() {
            /****定义列按钮****/       
        	if(options.rowdefine=='init'){
        		$('#'+options.pageframe+' input[data-type="table"]').val(options.table);
        		table=options.table;
        		linenum=($(table+' tr').length-1);/****获取表格除标题栏外的总行数****/
            	$(options.setbutton).css('visibility','hidden');
            	show.empty();/****清空show <option>中的内容****/
            	hide.empty();/****清空hide <option>中的内容****/
        		//count=($(table+' th').length-1);/****获取总列数（排除最后一列的空列）****/
            	count=$(table+' th').length;
             	for(i=0;i<count;i++){/****遍历标题列（除最后一列空列外）****/
             		if($(table+' th:eq('+i+')').css('display')!='none'){
        				/****如果标题列不隐藏，则标题名加入show <option>中****/
             			show.append('<option value='+$(table+' th:eq('+i+')').attr('class')+'>'+$(table+' th:eq('+i+')').text()+'</option>');
             	    }
             	    else{
             	    	if($(table+' th:eq('+i+')').attr('data-column')=='hidden'){
             	    		continue;
             	    	}else{
             	    		/****如果标题列隐藏，则标题名加入hide <option>中****/
                 	    	hide.append('<option value='+$(table+' th:eq('+i+')').attr('class')+'>'+$(table+' th:eq('+i+')').text()+'</option>');            	    	
             	    	}
        				/****如果标题列隐藏，则标题名加入hide <option>中****/
             	    	//hide.append('<option value='+$(table+' th:eq('+i+')').attr('class')+'>'+$(table+' th:eq('+i+')').text()+'</option>');            	    	
             	    }
             	} 
        	}       	
        	/****显示按钮****/   
        	else if(options.rowdefine=='show'){
        		table=$('#'+options.pageframe+' input[data-type="table"]').val();
        		value=[];
        		linenum=($(table+' tr').length-1);
        		hiddenindex=($('option',show).length);
        		$('option:selected',hide).each(function(){	
             	    value.push($(this).val());/****遍历hide <option>，将选中项的值存入value数组****/
             	});
                value=value.reverse();/****将value数组的元素逆序排列****/
                show.append($('option:selected',hide));
                $('option:selected',hide).remove();
             	for(i=0;i!=-1;i++){
        			/****遍历value数组****/
             	    if(value[i]!=null){
             	        rowindex=$('.'+value[i]).index();/****设置列索引值rowindex为<option>中value的索引值****/ 
        				if(rowindex!=hiddenindex){
        					$(table+' th:eq('+rowindex+')').insertBefore($(table+' th:eq('+hiddenindex+')'));    			
        					for(j=0;j<=linenum;j++){
        						$('td:eq('+rowindex+')',$(table+' tr:eq('+j+')')).insertBefore($('td:eq('+hiddenindex+')',$(table+' tr:eq('+j+')')));
        						/****将每一行中索引值为rowindex的<td>单元格插入到隐藏列第一列相应单元格的前一格****/
             	            }
        				}
        				$('.'+value[i]).css('display','');/****将class为value[i]的元素显示****/
             	    }
             	    else{
             	        break;
             	    }
             	}
        	}
        	/****显示所有按钮****/   
        	else if(options.rowdefine=='show_all'){
        		table=$('#'+options.pageframe+' input[data-type="table"]').val();
        		value=[];
        		linenum=($(table+' tr').length-1);
        		hiddenindex=($('option',show).length);
        		$('option',hide).each(function(){
             	    value.push($(this).val());
             	}); 	
             	value=value.reverse();
             	show.append($('option',hide));
             	$('option',hide).remove();
             	for(i=0;i!=-1;i++){
             	    if(value[i]!=null){
             	        rowindex=$('.'+value[i]).index();
             	       $(table+' th:eq('+rowindex+')').insertBefore($(table+' th:eq('+hiddenindex+')'));
             	        for(j=0;j<=linenum;j++){
             	        	$('td:eq('+rowindex+')',$(table+' tr:eq('+j+')')).insertBefore($('td:eq('+hiddenindex+')',$(table+' tr:eq('+j+')')));
             	        }
                        $('.'+value[i]).css('display','');			
             	    }
             	    else{
             	        break;
             	    }
             	} 
        	}
        	/****隐藏按钮****/   
        	else if(options.rowdefine=='hide'){
        		table=$('#'+options.pageframe+' input[data-type="table"]').val();
        		value=[];		
        		linenum=($(table+' tr').length-1);
        		lastindex=($(table+' td').length/($(table+' tr').length-1))-1;
        		$('option:selected',show).each(function(){
             	    value.push($(this).val());
             	});
        		hide.append($('option:selected',show));  
        		$('option:selected',show).hide();
             	for(i=0;i!=-1;i++){
             	    if(value[i]!=null){
             	        rowindex=$('.'+value[i]).index();
             	        $(table+' th:eq('+rowindex+')').insertBefore($(table+' th:eq('+lastindex+')'));
             	        for(j=0;j<=linenum;j++){
             	        	$('td:eq('+rowindex+')',$(table+' tr:eq('+j+')')).insertBefore($('td:eq('+lastindex+')',$(table+' tr:eq('+j+')')));
             	        } 
             	        $('.'+value[i]).css('display','none');
             	    }
             	    else{
        				
             	        break;
             	    }
             	} 
        	}
        	/****隐藏所有按钮****/   
        	else if(options.rowdefine=='hide_all'){
        		table=$('#'+options.pageframe+' input[data-type="table"]').val();
        		value=[];
        		linenum=($(table+' tr').length-1);
        		lastindex=($(table+' td').length/($(table+' tr').length-1))-1;
        		$('option',show).each(function(){
             	    value.push($(this).val());
             	});
        		hide.append($('option',show));
        		$('option',show).remove(); 
             	for(i=0;i!=-1;i++){
             	    if(value[i]!=null){ 
             	        rowindex=$('.'+value[i]).index(); 
             	        $(table+' th:eq('+rowindex+')').insertBefore($(table+' th:eq('+lastindex+')'));
             	        for(j=0;j<=linenum;j++){
             	        	$('td:eq('+rowindex+')',$(table+' tr:eq('+j+')')).insertBefore($('td:eq('+lastindex+')',$(table+' tr:eq('+j+')')));
             	        } 
             	        $('.'+value[i]).css('display','none');
             	    }
             	    else{
             	        break;
             	    }
             	}	
        	}
        	/****上移按钮****/   
        	else if(options.rowdefine=='up'){
        		table=$('#'+options.pageframe+' input[data-type="table"]').val();
            	value=[];
            	linenum=($(table+' tr').length-1);
            	$('option:selected',show).each(function(){
             	    value.push($(this).val());
             	});
             	for(i=0;i!=-1;i++){
             	    if(value[i]!=null){
             	        rowindex=$('.'+value[i]).index();
             	        if(rowindex!=0){
             	        	$(table+' th:eq('+rowindex+')').insertBefore($(table+' th:eq('+(rowindex-1)+')'));
             	            for(j=0;j<=linenum;j++){
             	            	$('td:eq('+rowindex+')',$(table+' tr:eq('+j+')')).insertBefore($('td:eq('+(rowindex-1)+')',$(table+' tr:eq('+j+')')));
             	            }
             	             $('option:eq('+rowindex+')',show).insertBefore($('option:eq('+(rowindex-1)+')',show));  
             	        }
             	        else{
             	            break;
             	        }
             	    }	
             	    else{
             	        break;
             	    }
             	}   
            }
        	/****下移按钮****/   
            else if(options.rowdefine=='down'){
            	table=$('#'+options.pageframe+' input[data-type="table"]').val();
            	value=[];
            	linenum=($(table+' tr').length-1);
            	$('option:selected',show).each(function(){
             	    value.push($(this).val());
             	}); 
            	showindex=($('option',show).length-1);
             	value=value.reverse();
             	for(i=0;i!=-1;i++){
             	    if(value[i]!=null){
             	        rowindex=$('.'+value[i]).index();
             	        if(rowindex!=showindex){
             	        	$(table+' th:eq('+rowindex+')').insertAfter($(table+' th:eq('+(rowindex+1)+')'));
             	            for(j=0;j<=linenum;j++){
             	            	$('td:eq('+rowindex+')',$(table+' tr:eq('+j+')')).insertAfter($('td:eq('+(rowindex+1)+')',$(table+' tr:eq('+j+')')));
             	            }
             	           $('option:eq('+rowindex+')',show).insertAfter($('option:eq('+(rowindex+1)+')',show)); 
             	        }
             	        else{
             	            break;
             	        }
             	    }
             	    else{
             	        break;
             	    }
             	}
            }
        	/****上移至顶部按钮****/   
            else if(options.rowdefine=='top'){
            	table=$('#'+options.pageframe+' input[data-type="table"]').val();
            	value=[];
            	linenum=($(table+' tr').length-1);
            	$('option:selected',show).each(function(){
             	    value.push($(this).val());
             	});
             	value=value.reverse();
             	for(i=0;i!=-1;i++){
             	    if(value[i]!=null){
             	        rowindex=$('.'+value[i]).index(); 
             	        if(rowindex!=0){
             	        	$(table+' th:eq('+rowindex+')').insertBefore($(table+' th:eq(0)'));
             	            for(j=0;j<=linenum;j++){
             	            	$('td:eq('+rowindex+')',$(table+' tr:eq('+j+')')).insertBefore($('td:eq(0)',$(table+' tr:eq('+j+')')));
             	            }
             	           $('option:eq('+rowindex+')',show).insertBefore($('option:eq(0)',show)); 
             	        }
             	        else{
                 	        break;
             	        }
             	    }
             	    else{
             	        break;
             	    }
             	}
            }
        	/****下移至底部按钮****/   
            else if(options.rowdefine=='bottom'){
            	table=$('#'+options.pageframe+' input[data-type="table"]').val();
            	value=[];
            	linenum=($(table+' tr').length-1);
            	$('option:selected',show).each(function(){
             	    value.push($(this).val());
             	}); 
            	showindex=($('option',show).length-1);
             	for(i=0;i!=-1;i++){
             	    if(value[i]!=null){
             	        rowindex=$('.'+value[i]).index();
             	        if(rowindex!=showindex){
             	        	$(table+' th:eq('+rowindex+')').insertAfter($(table+' th:eq('+showindex+')'));
             	            for(j=0;j<=linenum;j++){
             	            	$('td:eq('+rowindex+')',$(table+' tr:eq('+j+')')).insertAfter($('td:eq('+showindex+')',$(table+' tr:eq('+j+')')));
             	            }
             	           $('option:eq('+rowindex+')',show).insertAfter($('option:eq('+showindex+')',show)); 
             	        }
             	        else{
             	            break;
             	        }
             	    }
             	    else{
             	        break;
             	    }
             	}
            }
        	if(options.func!=null||options.func!=''){
				eval(options.func);
			}
        });     
    }
})(jQuery);

/*****************************插件配置说明*****************************
@                              配置参数                             
@												 
@
@
******************************************************************/