/*********************************************************
                    jQuery 分页功能
                    Create Date:2015.12.12
                    Create By:bird
                    Last Update Date:2017.5.25
                    Last Update By:bird
                          修改日志
           2015.12.12   创建文件
           2016.4.18    新增按钮动作绑定监听
           2016.4.28    新增数据遍历参数值jsontype
           				（用于分辨主表数据和不同lov的表格数据）
           2016.5.06    修改html标签data数据传入格式，		
           				由字符串改为json数组传入
           2016.5.10    新增隐藏空白行功能，当当前页数据不足一页时，
           				自动遍历设置空白行display:none
           2016.5.11    代码调优，由400行代码减少至150行
           2016.7.12    代码优化，并新增插件配置说明
           2016.8.26    代码优化，减少配置参数
           2016.8.31    新增参数cond,cond为条件查询的相关参数
                        修改bug，设置当数据只有一页时，同时隐藏上一页，下一页等四个按钮
           2016.9.05    修改bug，当条件查询查无数据时，显示查无数据，并且隐藏隐藏上一页，
           				下一页等四个按钮以及清空记录数栏位
           2017.5.25    
*********************************************************/
(function($) {                                      	
	/******************listener start***********************
	              监听 data-pagetype 传递的值
	       暂时只设置对<input> <button> <a> <i> 四种标签的绑定
	              请在listener区域绑定新的监听标签
    *********************************************************/
	$.fn.pageListener = function(){ 
		/****清除绑定****/
		$('input[data-pagetype]').off('click');	
		$('button[data-pagetype]').off('click');
		$('a[data-pagetype]').off('click');	
		$('i[data-pagetype]').off('click');
		/****绑定<inpu>t标签****/
		$('input[data-pagetype]').on('click', function() {		
			$(this).page($(this).data());
		});
		/****绑定<button>标签****/
		$('button[data-pagetype]').on('click', function() {
			$(this).page($(this).data());
		});
		/****绑定<a>标签****/
		$('a[data-pagetype]').on('click', function(e) {
			e.preventDefault();/****阻止<a>标签默认的点击事件（超链接跳转）****/
			$(this).page($(this).data());
		});
		/****绑定<i>标签****/
		$('i[data-pagetype]').on('click', function() {
			$(this).page($(this).data());
		});
	}   
	/******************listener end***********************/	
	
	/****执行监听函数****/
	$().pageListener();

    $.fn.page = function(options) {	        
		/*********************************************
                        设置默认属性  
        *********************************************/	    
		var defaults={
			load:'.ajax_loading',
			closebtn:'.close-setting',
			setpagesize:'.set_page_size'
		}		
		/****继承默认属性****/
        var options = $.extend({}, defaults, options);
		var tablename=$('#'+options.pageframe).attr('data-table');
		
		/****全局函数****/
		jQuery.global={
		    main:function(){
		    	param=$('option:selected',$('#'+options.pageframe+' select[data-type="select"]')).val();
				value=$('#'+options.pageframe+' input[data-type="query_val"]').val();
				//if(options.func!=null||options.func!=''){
				//	eval(options.func);
				//}
				$('table[data-table="'+tablename+'"] i[data-show="true"]').css('visibility','hidden');//MODIFY BY BIRD 2017.05.25
				//$('table[data-table="'+tablename+'"] i[data-show="true"]').css('visibility','inherit');//MODIFY BY BIRD 2016.12.21
       	        if(!value){
       	            param=null;
       	        }else{
       	            /***************************************
       	                 	因为%在url中为转义符，%25表达%字符本身，
       	                 	所以需要使用正则表达式全局替换，把字符串中
       	                 	所有的%替换为%25
       	            *****************************************/
       	            value=value.replace(/%/g,'%25');       
       	            param=param+'='+value;
       	        }	
		    	/****如果为最后一页按钮，设置goLastPage参数为true,其余则为false****/
		    	if(options.pagetype=='lastpage'){
		    		param=param+'&pageSize='+pageSize+'&pageNo='+pageNo+'&goLastPage=true';
		    	}else{
		    		param=param+'&pageSize='+pageSize+'&pageNo='+pageNo+'&goLastPage=false';
		    	}		   	
				queryurl=$('#'+options.pageframe+' input[data-type="url"]').val();
				orderby=$('#'+options.pageframe+' input[data-type="orderby"]').val();
				cond=$('#'+options.pageframe+' input[data-type="cond"]').val();
				param=param+'&orderby='+orderby;
				if(cond!=null&&cond!=''){
					param=param+'&'+cond;
				}
				if(options.func!=null||options.func!=''){
					eval(options.func);
				}
				$.ajax({
					async:true,
					type:'post', 
					data:param,
					url:queryurl,
					dataType:'json',
					success: function (data) {
						$('table[data-table="'+tablename+'"] td[data-column="db"]').html('');
						jQuery.json.getMSG(data);/****获取json参数数据****/
						$('table[data-table="'+tablename+'"] tr').css('display','');
						if(parseInt(data.pageMinRow)!=0){
							jsontype=$('#'+options.pageframe+' input[data-type="jsontype"]').val();
							jQuery.json.getContent(data,jsontype);/****获取json遍历数据，插入表格****/
							$('table[data-table="'+tablename+'"] i[data-show="true"]').css('visibility','inherit');//MODIFY BY BIRD 2017.05.25
							for(j=0;j<=(pageSize-(pageMaxRow-pageMinRow+1));j++){/****隐藏空白行****/
								$('table[data-table="'+tablename+'"] tr:eq('+(pageSize-j+1)+')').css('display','none');
		                	}
							if(firstPageFlag=='true'){/****判断是否为首页，若是，隐藏第一页与上一页按钮****/
								$('#'+options.pageframe+' i[data-pagetype="prevpage"]').css('display','none');
								$('#'+options.pageframe+' i[data-pagetype="firstpage"]').css('display','none');
			       			}else{
			       				$('#'+options.pageframe+' i[data-pagetype="prevpage"]').css('display','');
								$('#'+options.pageframe+' i[data-pagetype="firstpage"]').css('display','');
			       			}
			       			if(lastPageFlag=='true'){/****判断是否为末页，若是，隐藏最后一页与下一页按钮****/
			       				$('#'+options.pageframe+' i[data-pagetype="lastpage"]').css('display','none');
								$('#'+options.pageframe+' i[data-pagetype="nextpage"]').css('display','none');
			       			}else{
			       				$('#'+options.pageframe+' i[data-pagetype="lastpage"]').css('display','');
								$('#'+options.pageframe+' i[data-pagetype="nextpage"]').css('display','');
			       			}
							if(pageMinRow!=pageMaxRow){/****判断该页是否只有一行数据****/
								$('#'+options.pageframe+' span[data-type="row"]').text(pageMinRow+'-'+pageMaxRow);
							}else{
								$('#'+options.pageframe+' span[data-type="row"]').text(pageMaxRow);
							}
							if(options.pagetype=='setpagesize'){/****如果为设置页行数按钮，则点击关闭按钮****/
								$('#'+options.pageframe+' input[data-type="number"]').val(1);
								$(options.closebtn).click();
							}else if(options.pagetype=='lastpage'){/****如果为最后一页按钮，将参数totalPages赋值到页码html标签中****/
								if(totalPages==1){
									$('#'+options.pageframe+' i[data-pagetype="prevpage"]').css('display','none');
									$('#'+options.pageframe+' i[data-pagetype="firstpage"]').css('display','none');
								}
								$('#'+options.pageframe+' input[data-type="number"]').val(parseInt(totalPages));
							}else{
								$('#'+options.pageframe+' input[data-type="number"]').val(pageNo);
							}
						}else{
							if(options.pagetype=='nextpage'){								
								if($('#'+options.pageframe+' i[data-pagetype="lastpage"]').length>0){
									layer.msg("当前页无数据,即将自动跳转到最后一页");	
									$('#'+options.pageframe+' i[data-pagetype="lastpage"]').click();
								}else{
									layer.msg("当前页为最后一页");	
									pageNo=parseInt($('#'+options.pageframe+' input[data-type="number"]').val());
									jQuery.global.main();
								}								
							}else if(options.pagetype=='refresh'){
								pageNo=parseInt($('#'+options.pageframe+' input[data-type="number"]').val());
								if(pageNo==1){
									console.log("查无数据");
									$('#'+options.pageframe+' i[data-pagetype="prevpage"]').css('display','none');
									$('#'+options.pageframe+' i[data-pagetype="firstpage"]').css('display','none');
									$('#'+options.pageframe+' i[data-pagetype="lastpage"]').css('display','none');
									$('#'+options.pageframe+' i[data-pagetype="nextpage"]').css('display','none');
									$('#'+options.pageframe+' span[data-type="row"]').text('');
									$('table[data-table="'+tablename+'"] i[data-show="true"]').css('visibility','hidden');
									//$('td',$('table[data-table="'+tablename+'"] tr:eq(1)')).html('');
									$('td[data-column="db"]',$('table[data-table="'+tablename+'"] tr:eq(1)')).html('');
									blank_tr=$('tr:eq(1)',$('table[data-table="'+tablename+'"]'));
									$('td',$('table[data-table="'+tablename+'"]')).parent().remove();
									for(j=1;j<=pageSize;j++){
										$('tr:eq(0)',$('table[data-table="'+tablename+'"]')).parent().append(blank_tr.clone());
									}
								}
							}
						}
						$(options.load).hide();/****隐藏加载动画****/
					},
					error: function () {
						layer.alert('获取Json数据失败',{title:'警告',offset:[150]});				
					}
				});	
		    }
       	}
			
        return this.each(function() {
        	$(options.load).show();/****显示加载数据加载动画****/
        	pageSize=parseInt($('#'+options.pageframe+' input[data-type="size"]').val());
            /****预查询****/
			if(options.pagetype=='refresh'){
				pageNo=parseInt($('#'+options.pageframe+' input[data-type="number"]').val());
				$('td[data-column="db"]',$('table[data-table="'+tablename+'"] tr:eq(1)')).html('');
				blank_tr=$('tr:eq(1)',$('table[data-table="'+tablename+'"]'));
				$('td',$('table[data-table="'+tablename+'"]')).parent().remove();
				for(j=1;j<=pageSize;j++){
					$('tr:eq(0)',$('table[data-table="'+tablename+'"]')).parent().append(blank_tr.clone());
				}
				jQuery.global.main();
			}
			/****第一页****/
			else if(options.pagetype=='firstpage'){
				pageNo=parseInt(1);
				jQuery.global.main();
			}
			/****最后一页****/
			else if(options.pagetype=='lastpage'){
				pageNo=parseInt($('#'+options.pageframe+' input[data-type="number"]').val())+1;
				jQuery.global.main();
			}
			/****下一页****/
			else if(options.pagetype=='nextpage'){
				pageNo=parseInt($('#'+options.pageframe+' input[data-type="number"]').val())+1;
				jQuery.global.main();
			}
			/****上一页****/
			else if(options.pagetype=='prevpage'){	
				pageNo=parseInt($('#'+options.pageframe+' input[data-type="number"]').val())-1;
				jQuery.global.main();
			}				
			/****页行数设置****/
			else if(options.pagetype=='setpagesize'){
				pageSize=parseInt($(this).text());
				$('i',$(options.setpagesize)).css('visibility','hidden');
				$('i',$(this)).css('visibility','visible');
				$('#'+options.pageframe+' input[data-type="size"]').attr('value',pageSize);
				pageNo=parseInt(1);
				linenum=($('table[data-table="'+tablename+'"] tr').length-1);
				for(i=1;i<linenum;i++){
					$('tr:eq(1)',$('table[data-table="'+tablename+'"]')).remove();
				}
				$('td[data-column="db"]',$('tr:eq(1)')).html('');
				blank_tr=$('tr:eq(1)',('table[data-table="'+tablename+'"]'));
				for(j=1;j<pageSize;j++){
					$('tr:eq(1)',$('table[data-table="'+tablename+'"]')).parent().append(blank_tr.clone());
				}
				jQuery.global.main();
			}
		});
	}
})(jQuery);