package com.xinyiglass.springSample.websocket;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

/**
 * 这个的主要作用是取得当前请求中的用户名，并且保存到当前的WebSocketHandler中，
 * 以便确定WebSocketHandler所对应的用户，具体可参考HttpSessionHandshakeInterceptor
 * @author Sam.T 2016.8.18
 *
 */
public class WebSocketHandshakeInterceptor implements HandshakeInterceptor {

	@Override  
    public boolean beforeHandshake(ServerHttpRequest request,  
            ServerHttpResponse response, WebSocketHandler wsHandler,  
            Map<String, Object> attributes) throws Exception {
		if(request instanceof ServletServerHttpRequest){
			ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
            HttpSession session = servletRequest.getServletRequest().getSession(false);
            System.out.println("beforeHandshake,session id:"+session.getId());
            if (session != null) {
                //使用USER_ID区分WebSocketHandler，以便定向发送消息
            	if(session.getAttribute("USER_ID")!=null){
            		Long userId = (Long) session.getAttribute("USER_ID");
                    attributes.put("USER_ID",userId);
            	}else{
            		return false;//如果用户不存在，则不用申请握手！
            	}
            }
		}
		return true;  
	}
	
    @Override
    public void afterHandshake(ServerHttpRequest request
    		, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {
    }
}
