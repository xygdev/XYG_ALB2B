/*********************************************************
                    jQuery 页面弹出框功能
                    Create Date:2015.11.7
                    Create By:bird
                    Last Update Date:2016.7.11
                    Last Update By:bird
                          修改日志
           2015.11.7   创建文件
           2015.12.20  为绑定函数添加命名空间
           2016.3.15   因为弹出框里可能再弹出一个弹出框，所以新增
                       data-bg属性，为不同的弹出框设置不同的背景
           2016.7.11   代码优化，并新增插件配置说明
*********************************************************/

(function($) {
	/******************listener start***********************
    				  监听data-reveal-id属性
			暂时只设置对<input> <button> <a> <i> 四种标签的绑定
		     如需对更多标签进行绑定，请在listener区域绑定新的监听标签
	*********************************************************/
	$.fn.revealListener = function(){ 
		/****绑定<input>标签****/
		$('input[data-reveal-id]').on('click', function(e) {		
			var modalLocation = $(this).attr('data-reveal-id');
			$('#'+modalLocation).reveal($(this).data());
		});	
		/****绑定<button>标签****/
		$('button[data-reveal-id]').on('click', function(e) {
			var modalLocation = $(this).attr('data-reveal-id');
			$('#'+modalLocation).reveal($(this).data());
		});	
		/****绑定<a>标签****/
		$('a[data-reveal-id]').on('click', function(e) {
			e.preventDefault();/****阻止<a>标签默认的点击事件（超链接跳转）****/
			var modalLocation = $(this).attr('data-reveal-id');
			$('#'+modalLocation).reveal($(this).data());
		});	
		/****绑定<i>标签****/
		$('i[data-reveal-id]').on('click', function(e) {
			var modalLocation = $(this).attr('data-reveal-id');
			$('#'+modalLocation).reveal($(this).data());
		});
	}   
	/******************listener end***********************/	
	
	/****执行监听函数****/
	$().revealListener();
	
	$.fn.reveal = function(options) {
		/**************************
		         设置默认属性
		**************************/	
        var defaults = {  
	    	animation: 'none', /****弹出特效默认值***/
		    animationspeed: 300, /****弹出速度默认值***/
		    closeonbackgroundclick: false, /****点击背景是否关闭弹出框***/
		    dismissmodalclass: 'close-reveal-modal' ,/****弹出框关闭按钮的默认值***/
		    bg:'reveal-modal-bg'/****默认背景***/
    	}; 
    	
        /****继承默认属性****/
        var options = $.extend({}, defaults, options); 
	
        return this.each(function() {
        
            /*****************************
                     设置全局变量
            *****************************/
        	var modal = $(this),
        		topMeasure  = parseInt(modal.css('top')),
				topOffset = modal.height() + topMeasure,
          		locked = false,
				modalBG = $('.'+options.bg);

            /*****************************
                        创建背景元素
            *****************************/
			if(modalBG.length == 0) {
				modalBG = $('<div class="'+options.bg+'" />').insertAfter(modal);
			}	
     
	        /*****************************
                       弹出与关闭动作
            *****************************/
			/****打开弹出框****/
			modal.bind('reveal:open', function () {
			    modalBG.unbind('click.modalEvent');
				$('.' + options.dismissmodalclass).unbind('click.modalEvent');
				if(!locked) {
					lockModal();
					/****渐显加弹出特效****/
					if(options.animation == "FadeAndPop") {
						modal.css({'top': $(document).scrollTop()-topOffset, 'opacity' : 0, 'visibility' : 'visible'});
						modalBG.fadeIn(options.animationspeed/2);
						modal.delay(options.animationspeed/2).animate({
							"top": $(document).scrollTop()+topMeasure + 'px',
							"opacity" : 1
						}, options.animationspeed,unlockModal());					
					}
					/****渐显特效****/
					if(options.animation == "Fade") {
						modal.css({'opacity' : 0, 'visibility' : 'visible'});
						modalBG.fadeIn(options.animationspeed/2);
						modal.delay(options.animationspeed/2).animate({
							"opacity" : 1
						}, options.animationspeed,unlockModal());					
					} 
					/****无特效****/
					if(options.animation == "none") {
						modal.css('visibility','visible');
						modalBG.css({"display":"block"});	
						unlockModal()				
					}
				}
				/****执行打开界面之后的匿名函数****/
				if(options.revealfunc!=null||options.revealfunc!=''){
					eval(options.revealfunc);
				}
				/****终止打开弹出框函数****/
				modal.unbind('reveal:open');
			}); 	

			/****关闭弹出框****/
			modal.bind('reveal:close', function () {
			  if(!locked) {
					lockModal();
					/****渐隐加弹出特效****/
					if(options.animation == "fadeAndPop") {
						modalBG.delay(options.animationspeed).fadeOut(options.animationspeed);
						modal.animate({
							"top":  $(document).scrollTop()-topOffset + 'px',
							"opacity" : 0
						}, options.animationspeed/2, function() {
							modal.css({'top':topMeasure, 'opacity' : 1, 'visibility' : 'hidden'});
							unlockModal();
						});					
					}  	
					/****渐隐特效****/
					if(options.animation == "fade") {
						modalBG.delay(options.animationspeed).fadeOut(options.animationspeed);
						modal.animate({
							"opacity" : 0
						}, options.animationspeed, function() {
							modal.css({'opacity' : 1, 'visibility' : 'hidden'});
							unlockModal();
						});					
					}  	
					/****无特效****/
					if(options.animation == "none") {
						modal.css('visibility','hidden');
						modalBG.css({'display' : 'none'});	
					}		
				}
			    /****终止关闭弹出框函数****/
				modal.unbind('reveal:close');
			});     
   	
	        /*****************************
                   弹出框打开与关闭监听器
            ******************************/
        	/****监听打开弹出框****/
    	    modal.trigger('reveal:open')
			
			/****监听关闭按钮，添加关闭动作****/
			var closeButton = $('.' + options.dismissmodalclass).bind('click.modalEvent', function () {
			  modal.trigger('reveal:close')
			});
			
			/****点击背景时关闭弹出框****/
			if(options.closeonbackgroundclick) {
				modalBG.css({"cursor":"pointer"})
				modalBG.bind('click.modalEvent', function () {
				  modal.trigger('reveal:close')
				});
			}
			/****设置键盘ESC键为关闭弹出框快捷键****/
			//$('body').keyup(function(e) {
				/****键盘代码27为ESC键****/
        		//if(e.which===27){ modal.trigger('reveal:close'); } 
			//});
			
			
	        /***************************
                    设定弹出框虚拟状态
            ****************************/
			function unlockModal() { 
				locked = false;
			}
			function lockModal() {
				locked = true;
			}	
			
        });
    }
})(jQuery);

/*****************************插件配置说明*****************************
@                              配置参数                             
@    必需参数：														 
@    data-reveal-id:
@    data-raveal-id 为弹出框的id，属于必设参数属性
@    非必需参数：
@    data-animation:
@    data-animation 为弹出框的动画特效类型，有'FadeAndPop','Fade','none'
@    三种特效，'FadeAndPop'为渐显加弹出特效，'Fade'为渐显特效，'none'为无特效
@    不设置此参数则默认为'none'类型
@    data-animationspeed:
@    data-animationspeed 为弹出框的弹出速度，单位为ms(毫秒)
@    不设置此参数则默认为 300 (ms)
@    data-closeonbackgroundclick:
@    data-closeonbackgroundclick 为是否设置点击背景时关闭弹出框
@    有true和false两个选项，true为点击背景可关闭弹出框,false则为点击背景不可关闭
@    不设置此参数则默认为 true
@    data-dismissmodalclass:
@    data-dismissmodalclass 为设置弹出框关闭按钮标签的class
@    不设置此参数则默认为 close-reveal-modal
@    data-bg:
@    data-bg 为设置弹出框的背景标签的class
@    不设置此参数则默认为 reveal-modal-bg
@
@
***********************************************************/
        
