/*********************************************************
                    基于Jquery1.11.3版本
                        Bootstrap
                      Juery动态菜单功能
                      creat by bird
*********************************************************/

	/*******************************************************
							修改日志
				2016.06.06 创建js文件,初始程序逻辑运行成功
				2016.06.07 根据BootstrapUI,修改程序内容
				2016.08.09 为功能新增icon设置功能
				2016.08.10 整个动态功能逻辑推倒重构，更改了JSON数据的格式，
				           参考后台数据的关系逻辑，使用了类似于一维数组的JSON格式，
				           用prev(menu)的方式，让父菜单与子菜单之间产生关联，
				           使用循环遍历的方式，动态生成菜单。
				           （之前的方法，存在过多Hard Code，且JSON格式
				           多层嵌套，过于复杂繁琐，不利于后端构造，且扩展性较差）
			    2016.11.25 修改面包屑跳转逻辑
	*******************************************************/
    $.fn.iframeListener = function(){
    	$('a[data-iframehref]').on('click', function(e) {
    		e.preventDefault();//阻止<a>标签默认的点击事件（超链接跳转）
		    value=$(this).data().iframehref;	
		    $("#mainframe").attr("src",value);
		    $('ol.breadcrumb li[data-hrefflag="N"]').remove();
		    /****modify by bird 2016.12.20****/
		    $('a[data-funcid]').css('text-shadow','');
		    $('a[data-funcid]').css('color','');
		    /****modify by bird 2016.12.20****/
		    if($(this).data().title){
		    	$('section.content-header h1').text($(this).data().title);
		    }else{
		    	$('section.content-header h1').html('信义玻璃B2B电子商务平台<small>Version 1.0</small>');
		    }
    	});
    }

	$.fn.menu=function(menu_url,set_funcid_url){
		$.ajax({
			type:'post', 
			url:menu_url,
			dataType:'json',
			success: function (data) {
				for(i=0;i<data.length;i++){
					if(data[i].PREV_CODE==null||data[i].PREV_CODE==''){
						$('ul.sidebar-menu').append("<li class=\"treeview "+data[i].CODE+"\"><a href=\"#\"><i class=\""+data[i].ICON+"\"></i><span>"+data[i].NAME+"</span><i class=\"fa fa-angle-left pull-right\"></i></a><ul class=\"treeview-menu\"></ul></li>");
					}else{
						if(data[i].FUNC==null||data[i].FUNC==''){
							$('.'+data[i].PREV_CODE+' ul:first').append("<li class=\"treeview "+data[i].CODE+"\"><a href=\"#\"><i class=\""+data[i].ICON+"\"></i><span>"+data[i].NAME+"</span><i class=\"fa fa-angle-left pull-right\"></i></a><ul class=\"treeview-menu\"></ul></li>");
						}else if(data[i].FUNC!=null||data[i].FUNC!=''){
							$('.'+data[i].PREV_CODE+' ul:first').append("<li><a href=\"#\" data-href=\""+data[i].HREF+"\" data-funcid=\""+data[i].FUNC_ID+"\"><i class=\""+data[i].ICON+"\"></i>"+data[i].FUNC+"</a></li>");
						}else{
							alert("Error");
						}
					}		
				}
				$('a[data-href]').on('click', function(e) {
					e.preventDefault();//阻止<a>标签默认的点击事件（超链接跳转）
				    value=$(this).data().href;	
				    /****modify by bird 2016.12.12****/
				    var func_id=$(this).data().funcid;
				    var param='FUNC_ID='+func_id;
				    $.ajax({
				    	type:'post', 
				    	data:param,
				    	url:set_funcid_url				    	
				    });
				    /****modify by bird 2016.12.12****/
				    
				    /****modify by bird 2016.12.20****/
				    $('a[data-funcid]').css('text-shadow','');
				    $('a[data-funcid]').css('color','');
				    $(this).css('text-shadow','#fff 1px 1px 5px');
				    $(this).css('color','#fff');
				    /****modify by bird 2016.12.20****/
				    
				    $("#mainframe").attr("src",value);
				    $('section.content-header h1').text($(this).text());
					$('ol.breadcrumb li').remove();
					$('ol.breadcrumb').append('<li><a href=\"#"\ data-iframehref=\"home.do\"><i class=\"fa fa-dashboard\"></i> 主页</a></li>');
					$().iframeListener();
					var json=$(this).parentsUntil('ul.sidebar-menu').children('a').children('span');
					for(i=0;i<json.length;i++){
					    $('ol.breadcrumb').append('<li data-hrefflag="N">'+json[i].innerText+'</li>');
					    //alert(json[i].innerText);
					}
				});
			},error: function(){
			    alert("获取json数据错误");
			}
	    });		
	}
	
	
	
