/*********************************************************
                    jQuery 交互式报表(IRR)格式初始化/保存/加载功能
                    Create Date:2016.7.13
                    Create By:bird
                    Last Update Date:2016.7.13
                    Last Update By:bird
                          修改日志
           2016.7.13   创建文件,并新增插件配置说明  
		   2016.8.11   整合Spring框架进行的修改:和数据库栏位相关的变量统一大写。另外，插件js名称添加irr关键字。 by sam.t     
*********************************************************/
(function($) {
	/****属性设置****/
	options={
	    		
	}

	/**************************
        	默认属性
	**************************/	
	var defaults = {  
		userid:'#USER_ID',     			/****用户id值存放标签id****/
		interactcode:'#INTERACT_CODE',  /****报表格式code值存放标签id****/
		orderpara:'#ORDER_BY',        /****排序参数值存放标签id****/
		headerid:'#HEADER_ID',      	/****报表格式头id值存放标签id****/
		pageframe:'table',             
		pagesize:'#page_size',          /****每页显示行数值存放标签id****/
		setframe:'#setting',			/****设置框id****/
		tableid:'#tb',					/****表格id****/
		refresh:'#refresh',				/****刷新按钮id****/
		url:'irr/getDefaultIrr.do'	   /****ajax get地址****/
	}; 
		
	/****继承默认属性****/
    var options = $.extend({}, defaults, options); 
    var tablename=$('#'+options.pageframe).attr('data-table');
    var autoquery=$('#'+options.pageframe+' input[data-type="autoquery"]').val();
    
    console.log('Start init');
    user_id=$(options.userid).val();
    interact_code=$(options.interactcode).val();
    param='USER_ID='+user_id+'&INTERACT_CODE='+interact_code;
    $.ajax({
    	type:'post', 
		data:param,
		url:options.url,
		dataType:'json',
		success: function (data) {
			if(data.EXISTS=='Y'){
				$('#'+options.pageframe+' input[data-type="orderby"]').val(data.rows.HEADER[0].ORDER_BY);
				$(options.headerid).val(data.rows.HEADER[0].HEADER_ID);
				$('#'+options.pageframe+' input[data-type="pagesize"]').val(data.rows.HEADER[0].PAGE_SIZE);
				$(options.setframe+' i[data-value]').css('visibility','hidden');
		        $(options.setframe+' i[data-value="'+data.rows.HEADER[0].PAGE_SIZE+'"]').css('visibility','visible');
		        $('table[data-table="'+tablename+'"] th').css('display','none');
		        $('table[data-table="'+tablename+'"] td').css('display','none');
		        linenum=$('table[data-table="'+tablename+'"] tr').length-1;
            	setnum=data.rows.SEQ.length;
            	data.rows.SEQ.reverse();
            	for(i=0;i<setnum;i++){		   
					if(data.rows.SEQ[i].COLUMN_NAME!=null){			  				
						row=$('.'+data.rows.SEQ[i].COLUMN_NAME);
						rowindex=row.index();				
						$('.'+data.rows.SEQ[i].COLUMN_NAME).css('display','');
						if(rowindex!=0){
							$('table[data-table="'+tablename+'"] th:eq('+rowindex+')').insertBefore($('table[data-table="'+tablename+'"] th:eq(0)'));
							for(j=0;j<=linenum;j++){
								$('td:eq('+rowindex+')',$('table[data-table="'+tablename+'"] tr:eq('+j+')')).insertBefore($('td:eq(0)',$('table[data-table="'+tablename+'"] tr:eq('+j+')')));
     	            		}
						}
						else{
							$('table[data-table="'+tablename+'"] th:eq('+rowindex+')').insertBefore($('table[data-table="'+tablename+'"] th:eq(1)'));
							for(j=0;j<=linenum;j++){
								$('td:eq('+rowindex+')',$('table[data-table="'+tablename+'"] tr:eq('+j+')')).insertBefore($('td:eq(1)',$('table[data-table="'+tablename+'"] tr:eq('+j+')')));													
     	            		}
						}
					}
				}	
            	if(autoquery=='Y'){
            		$(options.refresh).click();
            	}else{
            		/**modify by bird 2016-12-6**/
            		$('td[data-column="db"]',$('table[data-table="'+tablename+'"] tr:eq(1)')).html('');
    				blank_tr=$('tr:eq(1)',$('table[data-table="'+tablename+'"]'));
    				$('td',$('table[data-table="'+tablename+'"]')).parent().remove();
    				for(j=1;j<=5;j++){
    					$('tr:eq(0)',$('table[data-table="'+tablename+'"]')).parent().append(blank_tr.clone());
    				}
    				/**modify by bird 2016-12-6**/
            		$('#'+options.pageframe+' i[data-pagetype="prevpage"]').css('display','none');
					$('#'+options.pageframe+' i[data-pagetype="firstpage"]').css('display','none');
					$('#'+options.pageframe+' i[data-pagetype="lastpage"]').css('display','none');
					$('#'+options.pageframe+' i[data-pagetype="nextpage"]').css('display','none');
					$('#'+options.pageframe+' i[data-reveal-id="query"]').click();
            	}	
			}
			else{
				if(autoquery=='Y'){
            		$(options.refresh).click();
            	}else{
            		/**modify by bird 2016-12-6**/
            		$('td[data-column="db"]',$('table[data-table="'+tablename+'"] tr:eq(1)')).html('');
    				blank_tr=$('tr:eq(1)',$('table[data-table="'+tablename+'"]'));
    				$('td',$('table[data-table="'+tablename+'"]')).parent().remove();
    				for(j=1;j<=5;j++){
    					$('tr:eq(0)',$('table[data-table="'+tablename+'"]')).parent().append(blank_tr.clone());
    				}
    				/**modify by bird 2016-12-6**/
            		$('#'+options.pageframe+' i[data-pagetype="prevpage"]').css('display','none');
					$('#'+options.pageframe+' i[data-pagetype="firstpage"]').css('display','none');
					$('#'+options.pageframe+' i[data-pagetype="lastpage"]').css('display','none');
					$('#'+options.pageframe+' i[data-pagetype="nextpage"]').css('display','none');
					$('#'+options.pageframe+' i[data-reveal-id="query"]').click();
            	}
			}			
		},
		error: function () {
			alert("获取Json数据失败");
		}
	}); 
    
    /******************listener start***********************
					监听data-config属性
				暂时只设置对<button> <a> 两种标签的绑定
			如需对更多标签进行绑定，请在listener区域绑定新的监听标签
    *********************************************************/	
    $.fn.configListener = function(){ 
    	/****绑定<a>标签****/
		$('a[data-config]').on('click', function(e) {	
			/****阻止<a>标签默认的点击事件（超链接跳转)****/
			e.preventDefault();
			$(this).config($(this).data());
		});
		/****绑定<button>标签****/
		$('button[data-config]').on('click', function() {	
			$(this).config($(this).data());
		});
    }
    /******************listener end***********************/	
    
    /****执行监听函数****/
    $().configListener();
    
    $.fn.config = function(options) {	        
		/*********************************************
                           设置默认属性
        *********************************************/	    
		var defaults={
			userid:'#USER_ID',     			/****用户id值存放标签id****/
			interactcode:'#INTERACT_CODE',  /****报表格式code值存放标签id****/
			interactname:'#USER_INTERACT_NAME',	/****报表格式name值存放标签id****/
			interactdesc:'#DESCRIPTION',	/****报表格式desc值存放标签id****/
			orderpara:'#ORDER_BY',        /****排序参数值存放标签id****/
			headerid:'#HEADER_ID',      	/****报表格式头id值存放标签id****/
			pagesize:'#page_size',          /****每页显示行数值存放标签id****/
			tableid:'#tb',					/****表格id****/
			configid:'#config',				/****个人配置框id****/
			refresh:'#refresh',				/****刷新按钮id****/
			setframe:'#setting',			/****设置框id****/
			initurl:'irr/getIrrHead.do',	    /****获取用户报表格式信息url****/
			saveurl:'irr/saveIrr.do',		/****保存用户报表格式信息url****/
			loadurl:'irr/getIrr.do',			/****加载用户报表格式信息url****/
			publicflag:'#PUBLIC_FLAG',		/****共享标志框id****/
			defaultflag:'#DEFAULT_FLAG',	/****默认标志框id****/
			autoflag:'#AUTOQUERY_FLAG'		/****自动查询标志框id****/
		}		
		/****继承默认属性****/
        var options = $.extend({}, defaults, options); 
			
        return this.each(function() {
        	configType=options.config;
        	interact_code=$(options.interactcode).val();
    		user_id=$(options.userid).val();
    		header_id=$(options.headerid).val();
        	if(configType=='init'){
        		$(options.setframe).css('visibility','hidden');
        		$(options.interactname).val('');
        		$(options.interactdesc).val('');
        		param='INTERACT_CODE='+interact_code+'&USER_ID='+user_id;
        		$.ajax({
    				type:'post', 
    				data:param,
    				url:options.initurl,
    				dataType:'json',
    				success: function (data) {
    					if(data.EXISTS=='Y'){
    						$(options.configid+' select').html('');
        					loadingindex=data.rows.length;
        					for(i=0;i<loadingindex;i++){
        						name=data.rows[i].USER_INTERACT_NAME;
        						value=data.rows[i].HEADER_ID;
        						$(options.configid+' select').append('<option value="'+value+'" >'+name+'</option>');	
        					}
        					$(options.configid+' option[value="'+header_id+'"]').prop('selected',true);
    					}else{
    					    console.log("查无数据");	
    					}
    				},error: function(){
    				    alert("获取json数据失败");
    				}
    			});	
        	}
        	else if(configType=='save'){   		
        		user_interact_name=$(options.interactname).val();
        		order_by=$(options.orderpara).val();
        		page_size=$(options.pagesize).val();
        		if(user_interact_name==''){
        			alert('自定义名称不能为空');
        			return;
        		}
        		description=$(options.interactdesc).val();
        		seq = new Array();
        		count=$(options.tableid+' th').length-1;/****获取总列数（排除最后一列的空列）****/
         	    for(i=0;i<count;i++){/****遍历标题列（除最后一列空列外）****/
         	        if($(options.tableid+' th:eq('+i+')').css('display')!='none'){
    				    seq[i]=$(options.tableid+' th:eq('+i+')').attr('class');
         	        }
         	    }
         	    //seq.reverse();
        		if($(options.publicflag).prop("checked")==true){
        			public_flag='Y';
        		}
        		else{
        			public_flag='N';
        		}
        		if($(options.defaultflag).prop("checked")==true){
        			default_flag='Y';
        		}
        		else{
        			default_flag='N';
        		}
        		if($(options.autoflag).prop("checked")==true){
        			autoquery_flag='Y';
        		}
        		else{
        			autoquery_flag='N';
        		}
        		param='INTERACT_CODE='+interact_code+'&USER_ID='+user_id+'&USER_INTERACT_NAME='+user_interact_name+'&ORDER_BY='+order_by+'&PAGE_SIZE='+page_size+'&DESCRIPTION='+description+'&SEQ='+seq+'&PUBLIC_FLAG='+public_flag+'&DEFAULT_FLAG='+default_flag+'&AUTOQUERY_FLAG='+autoquery_flag;
        		$.ajax({
    				type:'post', 
    				data:param,
    				url:options.saveurl,
    				dataType:'json',
    				success: function (data) {
    					if(data.retcode!=0){
    						alert("保存处理失败！错误信息:"+data.errbuf);
    					}else{
    						alert("保存成功");
        					$(options.configid+' .close-reveal-modal').click();
    					}
    				},error: function(){
    				    alert("save出现未知错误");
    				}
    			});
        	}
        	else if(configType=='load'){
        		header_id=$(options.configid+' option:selected').val()      
        		if(header_id=='blank'){
        			alert('载入模板不能为空，请重新选择！');
        			return;
        		}
                param='HEADER_ID='+header_id;
                $.ajax({
    				type:'post', 
    				data:param,
    				url:options.loadurl,
    				dataType:'json',
    				success: function (data) {
    				    if(data.EXISTS=='Y'){
    				        $(options.orderpara).val(data.rows.HEADER[0].ORDER_BY);
    				        $(options.headerid).val(data.rows.HEADER[0].HEADER_ID);
    				        $(options.pagesize).val(data.rows.HEADER[0].PAGE_SIZE);
    				        $(options.setframe+' i[data-value]').css('visibility','hidden');
    				        $(options.setframe+' i[data-value="'+data.rows.HEADER[0].PAGE_SIZE+'"]').css('visibility','visible');
    				        $(options.tableid+' th').css('display','none');
                			$(options.tableid+' td').css('display','none');
                			linenum=$(options.tableid+' tr').length-1;
                			setnum=data.rows.SEQ.length;
                			data.rows.SEQ.reverse();
                			for(i=0;i<setnum;i++){		   
    							if(data.rows.SEQ[i].COLUMN_NAME!=null){			  				
    								row=$('.'+data.rows.SEQ[i].COLUMN_NAME);
    								rowindex=row.index();				
    								$('.'+data.rows.SEQ[i].COLUMN_NAME).css('display','');
    								if(rowindex!=0){
    									$(options.tableid+' th:eq('+rowindex+')').insertBefore($(options.tableid+' th:eq(0)'));
    									for(j=0;j<=linenum;j++){
         	                				$('td:eq('+rowindex+')',$(options.tableid+' tr:eq('+j+')')).insertBefore($('td:eq(0)',$(options.tableid+' tr:eq('+j+')')));
         	            				}
    								}
    								else{
    									$(options.tableid+' th:eq('+rowindex+')').insertBefore($(options.tableid+' th:eq(1)'));
    									for(j=0;j<=linenum;j++){
         	                				$('td:eq('+rowindex+')',$(options.tableid+' tr:eq('+j+')')).insertBefore($('td:eq(1)',$(options.tableid+' tr:eq('+j+')')));
         	            				}
    								}
    							}
    				    	}	
    				    	$(options.refresh).click();
    				    	alert("加载成功");
        					$(options.configid+' .close-reveal-modal').click();
    				    }
    				    else{
    				    
    				    }			
    				},
    				error: function () {
    					alert("获取Json数据失败");
    				}
    			}); 	      		
        	}
        	else{
        		alert('获取json数据失败，请联系IT部门');
        	}       	
		});
	}		
})(jQuery);