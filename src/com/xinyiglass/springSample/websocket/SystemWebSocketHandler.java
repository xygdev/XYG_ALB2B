package com.xinyiglass.springSample.websocket;


import java.io.IOException;
import java.util.ArrayList;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class SystemWebSocketHandler extends  TextWebSocketHandler {
	 
    private static final ArrayList<WebSocketSession> users = new ArrayList<WebSocketSession>();;
 
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
    	System.out.println("ConnectionEstablished");
        users.add(session);
        System.out.println("当前总在线用户数:"+users.size()+",当前用户ID:"+session.getAttributes().get("USER_ID"));
    }
    
    /**
     * 在UI在用js调用websocket.send()时候，会调用该方法
     *
     */
	@Override
	protected void handleTextMessage(WebSocketSession session,
			TextMessage message) throws Exception {
		super.handleTextMessage(session, message);
		ArrayList<Long> userIdList=new ArrayList<Long>();
		userIdList.add(999L);//特定用户发送成功
		sendMessageToUsers(userIdList,message);
	}
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws IOException {
        if(session.isOpen()){
        	session.close();
        }
        users.remove(session);
    }
 
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        users.remove(session);
    }
 
    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
    /**
     * 给所有在线用户发送消息
     *
     */
    public void sendMessageToUsers(TextMessage message) {
        for (WebSocketSession user : users) {
            try {
                if (user.isOpen()) {
                	user.sendMessage(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    /**
     * 给特定的在线用户发送消息
     *
     */
    public void sendMessageToUsers(ArrayList<Long> userIdList,TextMessage message) {
    	if(userIdList==null){
    		return;
    	}
    	int listSize=userIdList.size();
    	if(listSize==0){
    		return;
    	}
    	int i=0;
    	for (WebSocketSession user : users) {
            try {
            	if(user.getAttributes().get("USER_ID")!=null){
                    if (user.isOpen()&&userIdList.contains(user.getAttributes().get("USER_ID"))) {
                    	user.sendMessage(message);
                    	i++;
                    	if(listSize==i){
                    		break;
                    	}
                    }
            	}
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}