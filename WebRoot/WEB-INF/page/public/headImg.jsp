<%@ page contentType="text/html;charset=utf-8" language="java" pageEncoding="utf-8" %>
<html>
  <body>
    <%
        String headImgType=request.getParameter("headImgType");
     %>
    <script type="text/javascript" src="plugin/js/cropbox.js"></script>
    <input type="hidden" id="headImgType" value=<%=headImgType %> />
    <div id="headimg">
      <input type="hidden" id="headimg_uid"/>
      <input type="hidden" id="headimg_uname"/>
      <a class="close-reveal-modal" data-type="close">&#215;</a>
      <div class="imageBox">
        <div class="thumbBox"></div>
        <div class="spinner" style="display: none"></div>
      </div>
      <div class="action"> 
      <!-- <input type="file" id="file" style=" width: 200px">-->
        <div class="new-contentarea tc"> 
          <a href="javascript:void(0)" class="upload-img">
            <label for="upload-file">选择图片</label>
          </a>
          <input type="file" class="" name="upload-file" id="upload-file" />
        </div>
        <input type="button" id="btnCrop"  class="Btnsty_peyton" value="OK">
        <input type="button" id="btnZoomIn" class="Btnsty_peyton" value="+"  >
        <input type="button" id="btnZoomOut" class="Btnsty_peyton" value="-" >
      </div>
      <div class="cropped">
        <div class="cropped_old">
          <img src="" align="absmiddle" style="width:180px;margin-top:4px;border-radius:180px;box-shadow:0px 0px 12px #7E7E7E;">
          <p>原头像</p>
        </div>
        <div class="cropped_new">
          <img src="/image/user/system_blank.png" align="absmiddle" style="width:180px;margin-top:4px;border-radius:180px;box-shadow:0px 0px 12px #7E7E7E;">
          <p>新头像</p>
        </div>
      </div>
    </div>
    <script type="text/javascript">
	$(window).load(function() {
		var options =
		{
			thumbBox: '.thumbBox',
			spinner: '.spinner',
			imgSrc: ''
		}
		var cropper = $('.imageBox').cropbox(options);
		var img="";
		
		$('#upload-file').on('change', function(){
			var reader = new FileReader();
			reader.onload = function(e) {
				options.imgSrc = e.target.result;
				cropper = $('.imageBox').cropbox(options);
				//getImg();
			}
			reader.readAsDataURL(this.files[0]);
			this.files = [];
			//getImg();
		})
	
		$('#btnCrop').on('click', function(){
			img=$('.cropped_new img').attr('src');
			if(img=='/image/user/system_blank.png'){
			    alert('请选择要上传的图像');
			    return;
			}else{
				/***************************************
       	                                     因为%在url中为转义符，%25表达%字符本身，
       	                                     所以需要使用正则表达式全局替换，把字符串中
       	          	               所有的%替换为%25
       	   		*****************************************/
       	    	img=img.replace(/\+/g,'%2B');       
				console.log(img);
				userId=$('#headimg_uid').val();
				fileName=$('#headimg_uname').val()+'.png';
				param='img='+img+'&userId='+userId+'&fileName='+fileName;
				$.ajax({
					type:'post', 
					data:param,
					url:'user/setUserImg.do',
					dataType:'json',
					success: function (data) {
						if(data.retcode=="0"){
				    		$('#headimg a[data-type="close"]').click();/****点击关闭更新框按钮****/
				    		headImgType=$('#headImgType').val()
				    		if(headImgType=='personal'){
				    		    image='/image/user/'+fileName+'?temp=' + Math.random();
				    		    $('#user-img-sm').attr('src',image);
				    		    $('#user-img-md').attr('src',image);
				    		}else if(headImgType=='admin'){
				    		    $('#refresh').click();
				    		}		
				    	}else{
				    		alert("更新处理失败！错误信息:"+data.errbuf);
				    	}			
					},
					error: function () {
						alert("获取Json数据失败");
					}
				});
			}
		});
	
		function getImg(){
			img = cropper.getDataURL();
			$('.cropped_new').html('');
			$('.cropped_new').append('<img src="'+img+'" align="absmiddle" style="width:180px;margin-top:4px;border-radius:180px;box-shadow:0px 0px 12px #7E7E7E;"><p>新头像</p>');
		}
		
		$(".imageBox").on("mouseup",function(){
 			getImg();
  		});
		
		
		$('#btnZoomIn').on('click', function(){
			cropper.zoomIn();
		})
		
		$('#btnZoomOut').on('click', function(){
			cropper.zoomOut();
		})
	});
	</script>
  </body>
</html>