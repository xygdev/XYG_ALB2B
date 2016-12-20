    var path = document.getElementsByTagName("base")[0].href;  
    console.log(path);
    document.write("<script language=javascript src='"+path+"plugin/jQuery/jQuery-2.1.4.min.js'></script>");
    document.write("<script language=javascript src='"+path+"plugin/layer/layer.js'></script>");
    document.write("<script language=javascript src='"+path+"plugin/js/sockjs-0.3.min.js'></script>");
    

    var ws = null;
    var basePath = 'ws://'+path.split('://')[1];
    console.log(basePath);
    //var basePath = "ws://192.168.88.41:8080/B2B_Demo_Version01/";
	if ('WebSocket' in window) {
    	 ws = new WebSocket(basePath+'webSocketServer'); 
	} 
	else if ('MozWebSocket' in window) {
		ws = new MozWebSocket(basePath+"webSocketServer");
	} 
	else {
		ws = new SockJS(basePath+"sockjs/webSocketServer");
	}
    ws.onopen = function () {
        
    };
    ws.onmessage = function (event) {
    	pop(event.data);
    };
    ws.onclose = function (event) {
    	 ws.close();
    };
//提示信息
function pop(message){
	$('#mailrefresh').click();
	layer.alert(message);
}
