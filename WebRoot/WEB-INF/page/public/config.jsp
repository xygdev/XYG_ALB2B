<%@ page contentType="text/html;charset=utf-8" language="java" pageEncoding="utf-8" %>
<html>
  <body>
    <!-- 个人配置区域 start -->
    <div id="config">
      <div class="title pointer">
        <span class="title_name">个人配置</span>
      </div>
      <a class="close-reveal-modal">&#215;</a>
      <div class="line"></div>
      <div class="contain" id="configtabs">
        <ul>
          <li><a href="#tabs-1">保存配置</a></li>
          <li><a href="#tabs-2">加载配置</a></li>
        </ul>
        <div id="tabs-1" class='tab'>
          <div class="item" style="text-indent:3rem">
            <label class="pointer" for="USER_INTERACT_NAME">自定义名称：</label>
            <input type="text" name="USER_INTERACT_NAME" id="USER_INTERACT_NAME">
          </div>
          <div class="item" style="text-indent:6rem">
            <label class="pointer" for="CONFIG_DESCRIPTION">描述：</label>
            <input type="text" name="DESCRIPTION" id="CONFIG_DESCRIPTION">
          </div>
          <div class="item" style="text-indent:6rem">
            <label class="pointer" for="PUBLIC_FLAG">共享</label>
            <input type="checkbox" name="PUBLIC_FLAG" id="PUBLIC_FLAG">
            <label class="pointer" for="DEFAULT_FLAG">默认</label>
            <input type="checkbox" name="DEFAULT_FLAG" id="DEFAULT_FLAG">
            <label class="pointer" for="AUTOQUERY_FLAG">自动查询</label>
            <input type="checkbox" name="AUTOQUERY_FLAG" id="AUTOQUERY_FLAG"> 
          </div>
          <div class="item">
            <button class='pointer' data-config="save" >保存</button>
          </div>
        </div>
        <div id="tabs-2" class='tab'>
          <div class="item" style="text-indent:6rem;margin:50px auto 60px">
            <label class="pointer" for="loding_format">选择配置:</label>
            <select id="loding_format"></select>
          </div>
          <div class="item">
            <button class='pointer' data-config="load">加载</button>
          </div>
        </div>
      </div>
    </div>
    <script>
         $("#config").draggable({ handle: ".title" });
         //设置tab分栏
    	 /**屏蔽<base>标签对JQuery UI tabs()的影响**/
         $.fn.__tabs = $.fn.tabs;
			$.fn.tabs = function (a, b, c, d, e, f) {
				var base = window.location.href.replace(/#.*$/, '');
				$('ul>li>a[href^="#"]', this).each(function () {
					var href = $(this).attr('href');
					$(this).attr('href', base + href);
				});
				$(this).__tabs(a, b, c, d, e, f);
			};
    		$("#configtabs").tabs();
     </script>
    <!-- 个人配置区域 end -->
  </body>
</html>