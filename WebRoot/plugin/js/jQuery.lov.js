/*********************************************************
                    jQuery 页面弹出框功能
                    Create Date:2015.1.20
                    Create By:bird
                    Last Update Date:2016.8.16
                    Last Update By:bird
                          修改日志
           2015.1.20   创建文件
           2016.5.10   新增Lov取值函数
           2016.7.13   代码优化，并新增插件配置说明
           2016.8.16   新增LOV框输入值验证功能，新增on change事件，
                       监听对LOV框值得手动修改结果，AJAX传递到后台验证是否存在，
                       若存在，则匹配相应ID值并存放到相应的hidden框内，
                       若不存在，则清空LOV的text框并提醒用户使用LOV选择
                       或重新手动输入
           2016.8.22   代码重构，减少配置参数
           2016.8.31   修改fn.modify,设置情况lov框后,同时清空隐藏id框
           2016.10.31  修改list方法，新增notnull参数，判断是否加入空<option>
*********************************************************/
(function($) {	
	/******************listener start***********************
    			监听 data-lovname 传递的值                      
		暂时只设置对<input> <button> <a> <i> 四种标签的监听绑定               
   				如需为其他更多元素增加点击弹出框效果                      
    			请在listener区域绑定新的监听标签
	 *******************************************************/
	$.fn.lovListener = function(){ 
		/****绑定<input>标签****/
		$('input[data-lovname]').on('click', function() {		
			$(this).lov($(this).data());
		});
		$('input[data-modify]').on('change', function() {		
			$(this).modify($(this).data());
		});
		/****绑定<button>标签****/
		$('button[data-lovname]').on('click', function() {
			$(this).lov($(this).data());
		});
		/****绑定<a>标签****/
		$('a[data-lovname]').on('click', function(e) {
			e.preventDefault();/****阻止<a>标签默认的点击事件（超链接跳转）****/
			$(this).lov($(this).data());
		});
		/****绑定<i>标签****/
		$('i[data-lovname]').on('click', function() {
			$(this).lov($(this).data());
		});	
	}   
	/******************listener end***********************/	
	
	/****执行监听函数****/
	$().lovListener();
	
	/***lov***/
    $.fn.lov = function(options) {	
        /**************************
        		设置默认属性
		**************************/	    
    	var defaults={
    		defaultquery:false
    	} 
    	/****继承默认属性****/
        var options = $.extend({}, defaults, options); 
    	var tablename=$('#'+options.pageframe).attr('data-table');
    	
        return this.each(function() {  
        	/****Lov取值函数，将Lov表格中的值选入预更新框并关闭lov框****/        	        	
        	$.fn.choose = function(){
            	$('table[data-table="'+tablename+'"] td').on('click', function() {
            		for(k=0;k<options.choose.length;k++){
            			text=$(this).parent().children(options.choose[k]).text();
            			if(!text){
            				alert('不能选择空值');	
            				return;
            			}else{
            				$(options.recid[k]).val(text);
            				$(options.recid[k]).click();
            			}   			
            		}
            		$('#'+options.pageframe+' .'+options.dismissmodalclass).click();
            	});
        	}     
        	$('#'+options.pageframe+' h1[data-type="title"]').text(options.lovname);
        	$('#'+options.pageframe+' input[data-type="number"]').val('1');
        	$('#'+options.pageframe+' i[data-pagetype="prevpage"]').css('display','none');
        	$('#'+options.pageframe+' i[data-pagetype="nextpage"]').css('display','none');
        	$('#'+options.pageframe+' input[data-type="jsontype"]').val(options.jsontype);
        	$('#'+options.pageframe+' input[data-type="url"]').val(options.queryurl);
        	$('table[data-table="'+tablename+'"]').html('');  
        	$('table[data-table="'+tablename+'"]').append('<tr>');     
        	for(i=0;i!=-1;i++){
        		if(options.th[i]!=null){
        			$('tr:eq(0)',$('table[data-table="'+tablename+'"]')).append('<th>'+options.th[i]+'</th>');
        		}else{
        			break;
        			return i;
        		}
        	}
        	for(m=1;m<=10;m++){
        		$('table[data-table="'+tablename+'"]').append('<tr>');
        		for(n=0;n<i;n++){
        			$('tr:eq('+m+')',$('table[data-table="'+tablename+'"]')).append('<td class="'+options.td[n]+'" data-column="db"></td>');
        		}
        	}
        	$('#'+options.pageframe+' select[data-type="select"]').html('');
        	$('#'+options.pageframe+' input[data-type="query_val"]').val('');
        	for(j=0;j!=-1;j++){
        		if(options.selectname[j]!=null&&options.selectvalue[j]!=null){
        			$('#'+options.pageframe+' select[data-type="select"]').append('<option value='+options.selectvalue[j]+'>'+options.selectname[j]+'</option>');
        		}else{
        			break;
        		}
        	}
        	$().choose();
        	/****默认查询参数如果为true，则默认打开Lov时点击一次查询按钮****/
        	if(options.defaultquery==true){
        		$('#'+options.pageframe+' i[data-crudtype="lovquery"]').click();
        	}
        	width='-'+parseInt($('#'+options.pageframe).css('width'))/2+'px';
        	$('#'+options.pageframe).css('margin-left',width);
        });    	
    }
    
    /***modify***/
    $.fn.modify = function(options) {
    	/**************************
		         设置默认属性
    	**************************/	    
    	var defaults={
    		pageframe:'',
    		validurl:'',//验证LOV框输入值的url
    		queryurl:'',//匹配ID的url
    		lovbtn:'',//LOV按钮的ID
    		hiddenid:'',//隐藏的ID值得input框ID
    		queryparam:''//查询条件名称
    	} 
    	
    	/****继承默认属性****/
    	var options = $.extend({}, defaults, options); 
    	
    	return this.each(function() { 
    		if(options.modify==true){
    			input=$(this);
    			param=$(this).val();
    			param=options.param+'='+param;
    			$('#'+options.pageframe).draggable('disable');
    			$.ajax({
					type:'post', 
					data:param,
					url:options.validurl,
					dataType:'json',
					success: function (data) {
						if(data.rows[0].COUNT==0){
							input.val('');
							result=confirm("输入的值不存在，是否通过值列表选取");	
							if(result==true){
								$('#'+options.pageframe).draggable('enable');
								for(n=0;n<options.hiddenid.length;n++){
									$('#'+options.hiddenid[n]).val('');
								}
								$('#'+options.lovbtn).click();
							}else{
								for(n=0;n<options.hiddenid.length;n++){
									$('#'+options.hiddenid[n]).val('');
								}
								$('#'+options.pageframe).draggable('enable');
								return;
							}					
						}else if(data.rows[0].COUNT==1){
							$('#'+options.pageframe).draggable('enable');
							$.ajax({
								type:'post', 
								data:param,
								url:options.queryurl,
								dataType:'json',
								success: function (data) {
									if(data.rows!=null){
										for(m=0;m<options.hiddenid.length;m++){
											$('#'+options.hiddenid[m]).val(data.rows[0][options.hiddenval[m]]);
										}
									}else{
										alert('返回数据为空，请联系IT部门人员');
									}
								},
								error: function () {
									alert("获取Json数据失败");
								}
							});
							return;
						}else{
							alert('程序错误，返回值不能为0或1之外的值');
						}
					},
					error: function () {
						alert("获取Json数据失败");
					}
				});     					
    		}else{
    			return;
    		}
    	});   
    }
    
    /***list***/
    var select=$('select[data-listurl]');
    console.log(select);
    //返回的select为DOM对象，使用原生JS对其进行操作
    for(m=0;m<select.length;m++){
    	list=document.getElementById(select[m].id);
    	if(select[m].attributes['data-notnull'].value=='false'){
    		opt=document.createElement('option');
    		list.appendChild(opt);
    	}
    	$.ajax({
			type:'post', 
			url:select[m].attributes['data-listurl'].value,
			async: false,
			dataType:'json',
			success: function (data) {
				if(data.rows!=null){
					for(n=0;n<data.rows.length;n++){					
						option=document.createElement('option');
						//用位置取值法(规则：第一位是显示值，第二位是返回值)
						count=0;
						for (var key in data.rows[n]){
							if(count==0){
								text=document.createTextNode(data.rows[n][key]);
							}else if(count==1){
								option.value=data.rows[n][key];
								break;
							}
							count++;
						}
						option.appendChild(text);
						list.appendChild(option);
					}
				}else{
					alert('返回数据为空，请联系IT部门人员');
				}
			},
			error: function () {
				console.log("获取Json数据失败");
			}
		});
    }    
    
})(jQuery);


/*****************************插件配置说明*****************************
@                              配置参数                             
@
@                             暂无，待更新
@
*******************************************************************/