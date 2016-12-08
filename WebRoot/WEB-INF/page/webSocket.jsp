<%@ page contentType="text/html;charset=utf-8" language="java" pageEncoding="utf-8"
	      import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>消息发送管理器</title>
<script type="text/javascript" src="/B2B_Demo_Version01/plugin/jQuery/jQuery-2.1.4.min.js"></script>	
<script type="text/javascript">
$(document).ready(function(){
  $("#btn").click(function(){
		var param="USER_IDS="+$("#USER_IDS").val()+"&MESSAGE="+$("#MESSAGE").val();
		//alert("param:"+param);
		$.ajax({
			type:'post', 
			data:param,
			url:'auditing.do',
			dataType:'json',
			success: function (data) {
  					if(data.retcode=="0"){
  						alert("消息发送成功!");
  					}else{
  						alert("更新处理失败！错误信息:"+data.errbuf);
  					}						  
			},
			error: function () {
				alert("获取发送结果数据失败");
			}			
		});	
		//alert("debug1");
  });
});
</script>
</head>
<body>
    <form id="webSocketFrom" name="webSocketFrom" >
        <label>请输入发送用户的USER_ID(同时发送多个用户请用逗号分隔):</label><br/>
        <input id="USER_IDS" name="USER_IDS" type="text"/><br/>
        <label>请输入发送消息内容:</label><br/>
        <textarea id="MESSAGE" name="MESSAGE" ></textarea><br/>
    </form>
    <button  id="btn" >推送消息</button>
</body>
</html>