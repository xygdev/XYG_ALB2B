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
           2017.1.5 修改Lov的逻辑以及添加联动List的逻辑。 samt
           2017.1.6 修改Lov的多级联动的逻辑以及修正联动List的问题。 samt
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
            		if(options.clickfunc!=null||options.clickfunc!=''){
        				eval(options.clickfunc);
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
        			$('tr:eq(0)',$('table[data-table="'+tablename+'"]')).append('<th class="'+options.td[i].replace("&"," ")+'">'+options.th[i]+'</th>');
        		}else{
        			break;
        			return i;
        		}
        	}
        	for(m=1;m<=10;m++){
        		$('table[data-table="'+tablename+'"]').append('<tr>');
        		for(n=0;n<i;n++){
        			//console.log("options.td[n]:"+options.td[n]);
        			$('tr:eq('+m+')',$('table[data-table="'+tablename+'"]')).append('<td class="'+options.td[n].replace("&"," ")+'" data-column="db"></td>');
        		}
        	}
        	$('#'+options.pageframe+' select[data-type="select"]').html('');
        	$('#'+options.pageframe+' input[data-type="query_val"]').val('');
        	$('#'+options.pageframe+' input[data-type="extend_param"]').val('');
        	if(options.func!=null||options.func!=''){
				eval(options.func);
			}
        	for(j=0;j!=-1;j++){
        		if(options.selectname[j]!=null&&options.selectvalue[j]!=null){
        			$('#'+options.pageframe+' select[data-type="select"]').append('<option value='+options.selectvalue[j]+'>'+options.selectname[j]+'</option>');
        		}else{
        			break;
        		}
        	}
			//2017.1.6新增自动获取扩展lov参数的逻辑
			//console.log('options.extparam:'+options.extparam);
			if(options.extparam){
				var extParam="";
				for(var c in options.extparam){
					extParam+="&"+options.extparam[c]+"="+$(options.extparamid[c]).val().replace(/%/g,'%25');
				}
				$('#'+options.pageframe+' input[data-type="extend_param"]').val(extParam);
			}
			//console.log('extParam:'+extParam);
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
    		//validurl:'',//验证LOV框输入值的url-->已经自动整合
    		//queryurl:'',//匹配ID的url -->已经自动整合
    		lovbtn:'',//LOV按钮的ID
    		//hiddenid:'',//隐藏的ID值得input框ID 这里不需要重复定义了。因为主LOV已经会定义所有的LOV的关联栏位！
    		suffixflag:false,//是否默认加%做查询
    		queryparam:''//查询条件名称
    	} 
    	
    	/****继承默认属性****/
    	var options = $.extend({}, defaults, options); 
    	
    	return this.each(function() { 
    		if(options.modify==true){
    			input=$(this);
    			var value=$(this).val();
				//自动赋值。需要注意的是，JSON.parse可能不支持过于老版本的IE浏览器！
				var recidArray=JSON.parse($('#'+options.lovbtn).attr('data-recid'));
				var chooseArray=JSON.parse($('#'+options.lovbtn).attr('data-choose'));
				if(!recidArray||recidArray.length==0){
					alert("数组转换出错！可能该版本的浏览器不支持函数JSON.parse，请联系信义ERP确认问题！");
					/*for(n in recidArray){
						$(recidArray[n]).val('');
					}*/
					return;
				}
				var extpArray,extpidArray;
				if($('#'+options.lovbtn).attr('data-extparam')){
					extpArray=JSON.parse($('#'+options.lovbtn).attr('data-extparam'));
					extpidArray=JSON.parse($('#'+options.lovbtn).attr('data-extparamid'));
				}
    			if(value){
    				if(options.suffixflag) value+='%';
    				//2017.1.4新逻辑：自动根据LOV的属性来找值。如果找到多个，则需要提示用户自己挑选
    				param=options.param+'='+value.replace(/%/g,'%25')+'&pageSize=2&pageNo=1';
    				if(extpArray){
    					for(var e in extpArray){
    						//console.log('extpidArray[e]:'+extpidArray[e]);
    						param+="&"+extpArray[e]+"="+$(extpidArray[e]).val().replace(/%/g,'%25');
    					}
    					//console.log('extpArray:'+extpArray.length+',NEW param:'+param);
    				}
    				//$('#'+options.pageframe).draggable('disable');
    				var _validurl=$('#'+options.lovbtn).attr('data-queryurl');//console.log('_validurl:'+_validurl+',param:'+param);
        			$.ajax({
    					type:'post', 
    					data:param,
    					url:_validurl,
    					dataType:'json',
    					success: function (data) {
    						var retRows=data.rows.length;
    						if(retRows!=1){
								for(n in recidArray){
									//console.log('recidArray[n]:'+recidArray[n]);
									$(recidArray[n]).val('');
								}
    							result=confirm("输入的值不存在或者存在多个匹配的值，是否通过值列表准确选取?");	
    							if(result==true){
    								var defQuery=$('#'+options.lovbtn).attr('data-defaultquery')||'false';
    								$('#'+options.pageframe).draggable('enable');
    								if(retRows>1&&defQuery!='false'){
    									$('#'+options.lovbtn).attr('data-defaultquery','false');
    								}
    								$('#'+options.lovbtn).click();
    								if(retRows>1){
    									$('#'+$('#'+options.lovbtn).attr('data-pageframe')+' input[data-type="query_val"]').val(value);
    									$('#'+$('#'+options.lovbtn).attr('data-pageframe')+' i[data-crudtype="lovquery"]').click();
    									if(defQuery!='false') $('#'+options.lovbtn).attr('data-defaultquery','true');
    								}
    							}else{
    								$('#'+options.pageframe).draggable('enable');
    								return;
    							}			
    						}else if(retRows==1){
    							//$('#'+options.pageframe).draggable('enable');
								//console.log('recidArray:'+recidArray+','+typeof recidArray);
    							for(var a in recidArray){
    								//console.log('recid:'+recidArray[a]+',value-->'+chooseArray[a].replace('.',''));
    								$(recidArray[a]).val(data.rows[0][chooseArray[a].replace('.','')]);
    							}
    							return;
    						}
    					},
    					error: function () {
    						alert("获取Json数据失败");
    					}
    				}); 
    			}else{
					for(n in recidArray){
						$(recidArray[n]).val('');
					}
    			}    			  		
    		}else{
    			return;
    		}
    	});   
    }
    
    /***list
     * 2017.1.5修改逻辑：
     * 增加联动List的逻辑。
     * 处理逻辑是：
     * 1当新页面的时候，会自动将所有的父级list(没定义子级的都认为是父级)都自动初始化，填充值。子联动菜单默认不处理。
     *   并且父联动菜单会自动添加change触发器，实现效果：当父菜单变更，子(或者儿子们)都自动清空。
     * 2当新增数据或者更新数据的时候，会根据父菜单重新更新一次子菜单的列表内容。
     *   注意：功能支持多级父List的联动List：出现一个list A，既是B的父级List，又是C级的子List。
     * ***/
    $.fn.listCreator = function(listObj){
	    //返回的select为DOM对象，使用原生JS对其进行操作
    	if(!listObj) return false;
    	//var listVal=$(listObj).val();
    	//console.log('listVal:'+listVal);
    	listObj.options.length = 0;//删除原有的选项
    	if($(listObj).attr('data-notnull')=='false'){// select[m].attributes['data-notnull'].value
    		var opt=document.createElement('option');
    		listObj.appendChild(opt);
    	}
    	var listParam="";
    	if($(listObj).attr('data-extparam')){
			var lpArray=JSON.parse($(listObj).attr('data-extparam'));
			var lpidArray=JSON.parse($(listObj).attr('data-extparamid'));
			for(var b in lpArray){
				listParam+="&"+lpArray[b]+"="+$(lpidArray[b]).val();
			}
			listParam=listParam.substr(1);
    	}
    	$.ajax({
			type:'post', 
			data:listParam,
			url:$(listObj).attr('data-listurl'),
			async: false,
			dataType:'json',
			success: function (data) {
				if(data.rows!=null){
					for(n=0;n<data.rows.length;n++){					
						var option=document.createElement('option');
						//用位置取值法(规则：第一位是显示值，第二位是返回值)
						var count=0;
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
						listObj.appendChild(option);
					}
					//if(listVal) $(listObj).val(listVal);
				}else{
					alert('返回数据为空，请联系IT部门人员');
				}
			},
			error: function () {
				console.log("获取Json数据失败");
			}
		});
    }
    
    //初始化list，只需要初始化不包括定义父list的即可
    $.fn.listInit = function() {
        var $initLists=$('select[data-listurl]');
        $initLists.each(function(c,listObj){
        	var extParamid=$(listObj).attr('data-extparamid');
        	if(!extParamid){
        		$().listCreator(listObj);//console.log('initLists:'+$(listObj).attr('id'));
        	}else{
        		var extArry=JSON.parse(extParamid);
        		for(var b in extArry){
            		//增加list变更的时候，自动将对应的子list清空的逻辑
            		$(extArry[b]).change(function(){
            			$childList=$('select[data-listurl][data-extparamid*="'+$(this).attr('id')+'"]');
            			$childList.each(function(i,childObj){
            				$().listCreator(childObj);//console.log('childList:'+$(childObj).attr('id'));
            			})
            		})
        		}
        	}
        })
    }
    
    //2017.1.7新增函数：自动初始化(清空)所有带有extParamid的list。初始化用！
    $.fn.listExtInit = function() {
        var $listExtInit=$('select[data-listurl]');
        $listExtInit.each(function(c,listObj){
        	var extParamid=$(listObj).attr('data-extparamid');
        	if(extParamid){
        		$(listObj).empty();
        	}
        })
    }
    
    //刷新所有带有父定义list。注意：该功能暂时不需要用到了！
    $.fn.listRef = function(){
        var $refLists=$('select[data-listurl]');
        for(var c=0;c<$refLists.length;c++){
        	if($($refLists[c]).attr('data-extparamid')){
        		//console.log($($refLists[c]).val('id'));
        		$().listCreator($refLists[c]);
        	}
        }
    }

    $().listInit();
})(jQuery);


/*****************************插件配置说明*****************************
@                              配置参数                             
@
@                             暂无，待更新
@
*******************************************************************/