/***************************************************************************************
                    jQuery 邮件信箱功能
                    Create Date:2016.9.2
                    Create By:bird
                    Last Update Date:2016.9.2
                    Last Update By:bird
                          修改日志
           2016.9.2    创建文件
 
***************************************************************************************/
(function($) {
	/******************listener start***********************
	               监听 data-maitype 传递的值                      
	              暂时只设置对 <i> 一种标签的监听绑定                                   
	               请在listener区域绑定新的监听标签
    *******************************************************/
	$.fn.mailListener = function(){ 
		/****绑定<i>标签****/
		$('i[data-mailtype]').on('click', function() {
			$(this).mail($(this).data());
		});
		/****绑定<button>标签****/
		$('button[data-mailtype]').on('click', function() {
			$(this).mail($(this).data());
		});
	}  
	/******************listener end***********************/	
	
	$().mailListener();
	
	$.fn.mail = function(options) {	
    	/*********************************************
        			       设置默认属性 
		*********************************************/   
        var defaults={
            refresh:'#refresh'
        }     
        
        /******继承默认属性******/
        var options = $.extend({}, defaults, options); 
        
        return this.each(function() {       	
			if(options.mailtype=='detail'){
				tr=$(this).parent().parent();
		        readdate=tr.children(options.mailparam[0]).text();
		        if(readdate==null||readdate==''){
		            var param=options.mailparam[1]+'='+tr.children(options.mailparam[2]).text();
		            console.log('开始detail');
		            console.log(param);
		            $.ajax({
						type:'post', 
						data:param,
						url:options.mailurl,
						dataType:'json',
						success: function (data) {
							if(data.retcode=="0"){
			    				$(options.refresh).click();
			    				parent.$(options.btn).click();
			    			}else{
			    				layer.alert("更新处理失败！错误信息:"+data.errbuf,{title:'警告',offset:[150]});
			    			}
						},
						error: function () {
							layer.alert('获取Json数据失败',{title:'警告',offset:[150]});
					    }
				    });
		        }else{
		            console.log('该邮件已阅读');
		        }
			}
			else if(options.mailtype=='unread'){
				$.ajax({
					type:'post', 
					url:options.counturl,
					dataType:'json',
					success: function (data) {
						$('#'+options.pageframe+' span[data-type="tips"]').text(data.rows[0].COUNT);
						$('#'+options.pageframe+' span[data-type="inside"]').text(data.rows[0].COUNT);
						$.ajax({
							type:'post',
							url:options.mailurl,
							dataType:'json',
							success: function (data) {
								jQuery.json.getUnreadMail(data);
							},
							error: function () {
								layer.alert('获取Json数据失败',{title:'警告',offset:[150]});
							}
						});		
					},
					error: function () {
						layer.alert('获取Json数据失败',{title:'警告',offset:[150]});
				    }
			    }); 				
			}
        });
	}
})(jQuery);