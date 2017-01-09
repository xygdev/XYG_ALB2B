/***************************************************************************************
                    jQuery 增删查改功能
                    Create Date:2015.1.20
                    Create By:bird
                    Last Update Date:2016.7.12
                    Last Update By:bird
                          修改日志
           2015.1.20   创建文件
           2016.4.18   新增按钮动作绑定监听
		   2016.4.26   为删除功能设置传入数组参数delparam[],数组容量为2
			           delparam[0]为参数名 delparam[1]为获取参数值的html标签id或class
			           为预更新功能设置传入数组参数updateparam[]		
			           updateparam[0]为参数名 updateparam[1]为获取参数值的html标签id或class	
		   2016.4.27   新增数据更新功能
		   2016.4.28   bug:{
			             翻页时需要对删除按钮和预更新按钮被重置，所以每次翻页时需要重新执行绑定监听函数，
			             而确认更新按钮并没有被重置，所以导致了确认按钮被重复绑定了多次监听，点一下确认更新按钮会导致更新多次
			           }
			           解决方法：在绑定监听函数前，新增了解绑函数，绑定前先解绑，避免事件重复绑定
		   2016.5.3    新增条件查询功能
		   2016.5.10   新增隐藏空白行功能，当当前页数据不足一页时，自动遍历设置空白行display:none
		   2016.5.11   代码调优，由300行代码减少至150行
		   2016.7.12   代码优化，并新增插件配置说明
		   2016.8.11   整合Spring框架进行的小修改 by sam.t
		   2016.8.16   新增global函数，validate(),
		               验证更新框中的所有required的input框的值，返回一个boolean值validateFlag
		               点击更新按钮时，验证validateFlag,如果为true,则可以更新
		               如果为false,则存在值为空的必填项
		   2016.8.26   优化代码，减少输入参数
		   2016.8.31   新增条件查询功能
		   2017.1.5 新增匿名函数功能：afterdatafunc，作用是数据完成后自动执行的
 						对应的crudtype方法：pre-update/update/insert/lovquery
***************************************************************************************/
(function($) {
	/******************listener start***********************
	               监听 data-crudtype 传递的值                      
	        暂时只设置对<input> <button> <a> <i> 四种标签的监听绑定               
	              如需为其他更多元素增加点击弹出框效果                      
	               请在listener区域绑定新的监听标签
    *******************************************************/
	$.fn.crudListener = function(){ 
		/****清除绑定****/
		$('input[data-crudtype]').off('click');	
		$('input[data-name]').off('change');
		$('button[data-crudtype]').off('click');
		$('a[data-crudtype]').off('click');	
		$('i[data-crudtype]').off('click');
		/****绑定<input>标签****/
		$('input[data-crudtype]').on('click', function() {		
			$(this).crud($(this).data());
		});
		$('input[data-name]').on('change', function() {		
			$(this).setname($(this).data());
		});
		/****绑定<button>标签****/
		$('button[data-crudtype]').on('click', function() {
			$(this).crud($(this).data());
		});
		/****绑定<a>标签****/
		$('a[data-crudtype]').on('click', function(e) {
			e.preventDefault();//阻止<a>标签默认的点击事件（超链接跳转）
			$(this).crud($(this).data());
		});
		/****绑定<i>标签****/
		$('i[data-crudtype]').on('click', function() {
			$(this).crud($(this).data());
		});
	}  
	/******************listener end***********************/	
	
    $.fn.crud = function(options) {	
    	/*********************************************
        			       设置默认属性 
		*********************************************/   
        var defaults={
        	load:'.ajax_loading',
        	pageframe:'lov',
        	refresh:'refresh',
            func:null
        }             	
        /******继承默认属性******/
        var options = $.extend({}, defaults, options); 
        var tablename=$('#'+options.pageframe).attr('data-table');
        
        /****全局函数****/
        jQuery.global={
        	/******判断当前页是否为首页或末页******/
            pageFlag:function(firstPageFlag,lastPageFlag){
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
           	},
           	validate:function(){
           		var input=$('#'+options.pageframe+' [required="required"]');
           		validate_flag=true;
				for(i=0;i<input.length;i++){
					if(input[i].value==''||input[i].value==null){
						id=input[i].id;		
						label=$('#'+options.pageframe+' label[for="'+id+'"]').text();
						layer.alert('提交失败!错误信息:'+label+'不能为空！',{title:'警告',offset:[150]});
						input[i].focus();
						validate_flag=false;
						return;
					}else{
						continue;
					}
				}
           	}  
        }
        	
        return this.each(function() {       	
        	/******删除方法******/
			if(options.crudtype=='del'){
				tr=$(this).parent().parent();
				col=tr.children('.'+options.col).text();
				result=confirm(options.delmsg+col+'?');
				if(result==true){
					param=options.delparam[0]+'='+tr.children(options.delparam[1]).text();
					$.ajax({
						type:'post', 
						data:param,
						url:options.delurl,
						dataType:'json',
						success: function (data) {
							if(data.retcode=="0"){
								layer.msg("删除成功!");
				    			$('#'+options.refresh).click();/****点击刷新当前页按钮，刷新数据****/	
				    		}else{
				    			layer.alert("删除失败！错误信息:"+data.errbuf,{title:'警告',offset:[150]});
				    		}	
						},
						error: function () {
							layer.alert('获取Json数据失败',{title:'警告',offset:[150]});
						}
					}); 
					return;			
				}else{            
					result=null;
				}    				
			}
			/******预更新方法******/
			else if(options.crudtype=='pre-update'){
				pageframe=$(this).attr('data-reveal-id');
				param = '';
				tr=$(this).parent().parent();
				if(options.func!=null||options.func!=''){
					eval(options.func);
				}
				//console.log(param);
				$('#'+pageframe+' input[data-update="db"]').val('');
				$('#'+pageframe+' input[type="checkbox"]').prop('checked',false);
                $('#'+pageframe+' select[data-update="db"]').val('');
                $('#'+pageframe+' textarea[data-update="db"]').val('');
                $('#'+pageframe+' span[data-type]').hide();
                $('#'+pageframe+' button[data-type]').hide();
                $('#'+pageframe+' span[data-type="'+options.type+'"]').show();
                $('#'+pageframe+' button[data-type="'+options.type+'"]').show();
				param=param+options.updateparam[0]+'='+tr.children(options.updateparam[1]).text();/****设置参数****/
				//console.log(param);
		        $.ajax({
					type:'post', 
					data:param,
					url:options.preupdateurl,
					dataType:'json',
					success: function (data) {
						jQuery.json.getUpdateJSON(data,pageframe);/****获取目标更新行数据****/
						if(options.afterdatafunc!=null||options.afterdatafunc!=''){//2017.1.5新增匿名函数功能：数据完成后自动执行的
							eval(options.afterdatafunc);
						}
					},
					error: function () {
						//alert("获取Json数据失败");
						layer.alert('获取Json数据失败',{title:'警告',offset:[150]});
					}
				});			
			}
			/******更新方法******/
			else if(options.crudtype=='update'){ 
				jQuery.global.validate();
				if(validate_flag==true){
				/****************************/
					param=$('#'+options.pageframe+' form').serialize();
	       	        if(options.func!=null||options.func!=''){
						eval(options.func);
					}
	       	        //console.log(param);
				    //param=options.updateparam[0]+'='+$(options.updateparam[1]).val()+'&'+param;/****设置参数****/
				    //modify by bird 2016.10.26
	       	        $.ajax({
				    	type:'post', 
				    	data:param,
				    	url:options.updateurl,
				    	dataType:'json',
				    	success: function (data) {
				    		if(data.retcode=="0"){
				    			layer.msg("更新成功!");
				    			$('#'+options.pageframe+' a[data-type="close"]').click();/****点击关闭更新框按钮****/
				    			$('#'+options.refresh).click();/****点击刷新当前页按钮，刷新数据****/
				    		}else{
				    			layer.alert("更新处理失败！错误信息:"+data.errbuf,{title:'警告',offset:[150]});
				    		}		
							if(options.afterdatafunc!=null||options.afterdatafunc!=''){//2017.1.5新增匿名函数功能：数据完成后自动执行的
								eval(options.afterdatafunc);
							}				  
				    	},
				    	error: function () {
				    		layer.alert('获取Json数据失败',{title:'警告',offset:[150]});
				    	}			
				    });	
				/*****************************************/
				}else{
					return;
				}
			}
			/******预新增方法******/
			else if(options.crudtype=='pre-insert'){
				$(options.load).show();/****显示加载动画****/
				pageframe=$(this).attr('data-reveal-id');
				if(options.func!=null||options.func!=''){
					eval(options.func);
				}
                $('#'+pageframe+' span[data-type]').hide();
                $('#'+pageframe+' button[data-type]').hide();
                $('#'+pageframe+' span[data-type="'+options.type+'"]').show();
                $('#'+pageframe+' button[data-type="'+options.type+'"]').show();
                $('#'+pageframe+' input[data-update="db"]').val('');
                $('#'+pageframe+' input[type="checkbox"]').prop('checked',false);
                $('#'+pageframe+' select[data-update="db"]').val('');
                $('#'+pageframe+' textarea[data-update="db"]').val('');
                $(options.load).hide();
			}
			/******新增方法******/
			else if(options.crudtype=='insert'){
				jQuery.global.validate();
				if(validate_flag==true){
				/****************************/
					param=$('#'+options.pageframe+' form').serialize();
	       	        if(options.func!=null||options.func!=''){
						eval(options.func);
					}
	       	        //console.log(param);
				    $.ajax({
				    	type:'post', 
				    	data:param,
				    	url:options.inserturl,
				    	dataType:'json',
				    	success: function (data) {
				    		if(data.retcode=="0"){
				    			layer.msg("新增成功!");
				    			$('#'+options.pageframe+' a[data-type="close"]').click();/****点击关闭更新框按钮****/
				    			$('#'+options.refresh).click();/****点击刷新当前页按钮，刷新数据****/
				    		}else{
				    			layer.alert("新增处理失败！错误信息:"+data.errbuf,{title:'警告',offset:[150]});
				    		}		
							if(options.afterdatafunc!=null||options.afterdatafunc!=''){//2017.1.5新增匿名函数功能：数据完成后自动执行的
								eval(options.afterdatafunc);
							}				  
				    	},
				    	error: function () {
				    		layer.alert('获取Json数据失败',{title:'警告',offset:[150]});
				    	}			
				    });	
				/*****************************************/
				}else{
					return;
				}
			}
			/******条件查询方法******/
			else if(options.crudtype=='query'){
				cond=$('#'+options.pageframe+' form').serialize();
				if(options.func!=null||options.func!=''){
					eval(options.func);
				}
       	        //console.log(cond);
       	        $('#'+options.buttonframe+' input[data-type="cond"]').val(cond);
       	        $('#'+options.buttonframe+' input[data-type="number"]').val(1);   
       	        $('#'+options.pageframe+' a[data-type="close"]').click();
       	        $('#'+options.buttonframe+' i[data-pagetype="refresh"]').click();
			}
			/******lov条件查询方法******/
			else if(options.crudtype=='lovquery'){
				param=$('option:selected',$('#'+options.pageframe+' select[data-type="select"]')).val();
				value=$('#'+options.pageframe+' input[data-type="query_val"]').val();		
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
				pageSize=parseInt($('#'+options.pageframe+' input[data-type="size"]').val());				
				pageNo=parseInt(1);
				$('#'+options.pageframe+' input[data-type="number"]').val(pageNo);
				param=param+'&pageSize='+pageSize+'&pageNo='+pageNo;
				extendParam = $('#'+options.pageframe+' input[data-type="extend_param"]').val();
				if(extendParam!=null||extendParam!=''){
					param = param + extendParam;
				}
				queryurl=$('#'+options.pageframe+' input[data-type="url"]').val();
				$.ajax({
					type:'post', 
					data:param,
					url:queryurl,
					dataType:'json',
					success: function (data) {
						$('table[data-table="'+tablename+'"] td').html('');
						if(data.rows.length==0){
							layer.msg('提示: 查询无数据！');
							return false;
						}
						jQuery.json.getMSG(data);
						$('table[data-table="'+tablename+'"] tr').css('display','');
						jsontype=$('#'+options.pageframe+' input[data-type="jsontype"]').val();
						jQuery.json.getContent(data,jsontype);
						for(j=0;j<=(pageSize-(pageMaxRow-pageMinRow+1));j++){/****隐藏空白行****/
							$('table[data-table="'+tablename+'"] tr:eq('+(pageSize-j+1)+')').css('display','none');						
	                	};
						jQuery.global.pageFlag(firstPageFlag,lastPageFlag);
						if(parseInt(data.pageMinRow)==0){
							$('#'+options.pageframe+' i[data-pagetype="nextpage"]').css('display','none');
						}
						width='-'+parseInt($('#'+options.pageframe).css('width'))/2+'px';
			        	$('#'+options.pageframe).css('margin-left',width);
						if(options.afterdatafunc!=null||options.afterdatafunc!=''){//2017.1.5新增匿名函数功能：数据完成后自动执行的
							eval(options.afterdatafunc);
						}
					},
					error: function () {
						layer.alert('获取Json数据失败',{title:'警告',offset:[150]});
					}
				}); 
			}
        });
    }
    
    $.fn.setname = function(options) {	
    	/*********************************************
        			       设置默认属性 
		*********************************************/   
        var defaults={
            fullname:'FULL_NAME'
        }             
        
        /******继承默认属性******/
        var options = $.extend({}, defaults, options); 
        
        return this.each(function() {       	
        	/******姓******/
			if(options.name=='LAST_NAME'){
				lastname=$(this).val();
				fullname=$('#'+options.fullname).val();
				str=fullname.split(',');
				if(str[1]==null||str[1]==''||str[1]==undefined){
					fullname=lastname;
				}else{
					fullname=lastname+','+str[1];
				}
				$('#'+options.fullname).val(fullname);
			}
			/******名******/
			else if(options.name=='FIRST_NAME'){
				firstname=$(this).val();
				fullname=$('#'+options.fullname).val();
				str=fullname.split(',');
				if(str[0]==null||str[0]==''||str[0]==undefined){
					if(firstname==null||firstname==''){
						fullname=firstname;
					}else{
						fullname=','+firstname;
					}
				}else{
					if(firstname==null||firstname==''){
						fullname=str[0];
					}else{
					    fullname=str[0]+','+firstname;
					}
				}
				$('#'+options.fullname).val(fullname);
			}     
        });
    }
    
    $.fn.validateRequired = function(pageframe){
    	var input=$('#'+pageframe+' [required="required"]');
   		validate_flag=true;
		for(i=0;i<input.length;i++){
			if(input[i].value==''||input[i].value==null){
				id=input[i].id;		
				label=$('#'+pageframe+' label[for="'+id+'"]').text();
				layer.alert('提交失败!错误信息:'+label+'不能为空！',{title:'警告',offset:[150]});
				input[i].focus();
				validate_flag=false;
				return validate_flag;
			}else{
				continue;
			}
		}
		return validate_flag;
    }
    
    //栏位清除按钮
    $('i[data-eraser]').on('click',function(){
	    eraser=$(this).data('eraser');
	    for(m=0;m<eraser.length;m++){
	        $('#'+eraser[m]).val('');
	    }
	});	
    
    //2017.1.9 二次封装返回数据匹配json的方法
    /*
     * data:返回的分页数据
     * table:mapContent对应的table的选择器
     * mapRowArray:匹配关系数组定义。
     * 是一个数组，可以是匹配完整模式：[['.HEADER_ID','HEADER_ID'],['.DEPARTMENT_CODE',DEPARTMENT_CODE],...]
     * 也可以是简写：['HEADER_ID','DEPARTMENT_CODE',...]
     */
    $.fn.mapContentJson = function(data,table,mapRowArray){
    	var minRow=parseInt(data.pageMinRow);
    	var maxRow=parseInt(data.pageMaxRow);
    	if(maxRow==0&&minRow==0){
            console.log('no data');
            return false;
        }
    	for(i=0;i<(maxRow-minRow+1);i++){
        	var $trRow=$(table).find('tr:eq('+(i+1)+')');
        	//console.log('typeof:'+typeof mapRowArray);console.log($trRow[0]);
        	for(var n in mapRowArray){
            	if(typeof mapRowArray[n]=='object'){
                	//console.log('col:'+mapRowArray[n][0]+',json:'+data.rows[i][mapRowArray[n][1]]);
                	$trRow.find(mapRowArray[n][0]).html(data.rows[i][mapRowArray[n][1]]); 
                	if(mapRowArray[n][2]&&typeof mapRowArray[n][2]== "function"){
                		mapRowArray[n][2].call();
                	}
            	}else if(typeof mapRowArray[n]=='string'){
                	$trRow.find('.'+mapRowArray[n]).html(data.rows[i][mapRowArray[n]]);
            	}else{
            		alert('mapRowArray 定义有误！请联系ERP确认处理！');
            	}
        	}
    	}
        /*“原生”的执行方式：
        for(i=0;i<(pageMaxRow-pageMinRow+1);i++){
        	var $trRow=$('#worklogHeader tr:eq('+(i+1)+')');
        	$('.HEADER_ID',$trRow).html(data.rows[i].HEADER_ID); 
       	 	$('.DEPARTMENT_CODE',$trRow).html(data.rows[i].DEPARTMENT_CODE); 
        	$('.DEPARTMENT_DESC',$trRow).html(data.rows[i].DEPARTMENT_DESC); 
        	$('.WORK_GROUP',$trRow).html(data.rows[i].WORK_GROUP);   
        	$('.WORK_GROUP_DESC',$trRow).html(data.rows[i].WORK_GROUP_DESC); 
        	$('.WORK_ITEM',$trRow).html(data.rows[i].WORK_ITEM); 
        	$('.WORK_REQ_DOCUMENT',$trRow).html(data.rows[i].WORK_REQ_DOCUMENT); 
        	$('.WORK_REQUEST_NAME',$trRow).html(data.rows[i].WORK_REQUEST_NAME); 
        	$('.WORK_OWNER_NAME',$trRow).html(data.rows[i].WORK_OWNER_NAME); 
        	$('.WORK_DATE',$trRow).html(data.rows[i].WORK_DATE); 
        	$('.DESCRIPTION',$trRow).html(data.rows[i].DESCRIPTION); 
    	}*/
    }


    /*
     * data:返回的更新的数据
     * mapRowArray:匹配关系数组定义。
     * 是一个数组，可以是匹配完整模式：[['#H_ID','HEADER_ID'],['#DEPARTMENT_CODE',DEPARTMENT_CODE],...]
     * 如果可以确定ID匹配的代码和json的名称是一样，也可以是简写：['HEADER_ID','DEPARTMENT_CODE',...]
     * 注意：匹配完整模式，第三个参数可以是一个匿名函数。如果有定义，则会自动执行。
     * 例如：['#WORK_GROUP','WORK_GROUP',function(){$().listCreator($('#WORK_GROUP')[0]);}]
     */
    $.fn.mapUpdateJson = function(data,mapRowArray){
    	for(var n in mapRowArray){
        	if(typeof mapRowArray[n]=='object'){
            	$(mapRowArray[n][0]).val(data.rows[0][mapRowArray[n][1]]);
            	if(mapRowArray[n][2]&&typeof mapRowArray[n][2]== "function"){
            		mapRowArray[n][2].call();
            	}
        	}else if(typeof mapRowArray[n]=='string'){
            	$('#'+mapRowArray[n]).val(data.rows[0][mapRowArray[n]]);
        	}else{
        		alert('mapUpdateJson-->mapRowArray 定义有误！请联系ERP确认处理！');
        	}
    	}
       	/*需要注意的是：如果有些特殊的代码要写在定义json的时候赋值的，
       	 * 可以考虑用最原生的脚本写。
       	 * 例如下面的自动刷新List的逻辑。
       	 * 也可以用定义匿名函数自动执行。
       	$('#H_ID').val(data.rows[0].H_ID);
       	$('#DEPARTMENT_CODE').val(data.rows[0].DEPARTMENT_CODE);
        $().listCreator($('#WORK_GROUP')[0]);//需要在这里自动重新刷新list
       	$('#WORK_GROUP').val(data.rows[0].WORK_GROUP);
       	$('#WORK_ITEM').val(data.rows[0].WORK_ITEM);
       	$('#WORK_REQ_DOCUMENT').val(data.rows[0].WORK_REQ_DOCUMENT);
       	$('#WORK_REQUEST_PID').val(data.rows[0].WORK_REQUEST_PID);
       	$('#WORK_REQUEST_NAME').val(data.rows[0].WORK_REQUEST_NAME);
       	$('#WORK_OWNER_PID').val(data.rows[0].WORK_OWNER_PID);
       	$('#WORK_OWNER_NAME').val(data.rows[0].WORK_OWNER_NAME);
       	$('#WORK_DATE').val(data.rows[0].WORK_DATE);
       	$('#DESCRIPTION').val(data.rows[0].DESCRIPTION);*/
    }
    
})(jQuery);

/*****************************插件配置说明*****************************
@                              配置参数                             
@               
@                             暂无，待更新
@
*******************************************************************/