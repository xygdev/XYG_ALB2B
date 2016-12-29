package com.xinyiglass.springSample.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.socket.TextMessage;

import xygdev.commons.entity.PlsqlRetValue;

import com.xinyiglass.springSample.util.LogUtil;
import com.xinyiglass.springSample.websocket.SystemWebSocketHandler;

/**
 * 服务端推送消息给用户
 */
@Controller
@RequestMapping("/admin")
@Scope("prototype")
public class AdminController {
	protected HttpServletRequest req; 
    protected HttpServletResponse res; 
    protected HttpSession sess; 
    
    @ModelAttribute 
    public void setReqAndRes(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{ 
        this.req = request; 
        this.res = response; 
        this.sess = request.getSession(); 
        req.setCharacterEncoding("utf-8");
		res.setCharacterEncoding("utf-8");
		res.setContentType("text/html;charset=utf-8");  
    } 
	@Bean
    public SystemWebSocketHandler systemWebSocketHandler() {
        return new SystemWebSocketHandler();
    }
	
	//http://192.168.88.123:8080/web08Spring/admin/sendMessage.do
	@RequestMapping("/sendMessage.do")
	public String sendMessage(){
		return "webSocket";
	}
	
	@RequestMapping("/auditing.do")
    public void auditing() throws Exception{
        PlsqlRetValue rs=new PlsqlRetValue();
        LogUtil.log("user_id:"+req.getParameter("USER_IDS"));
        if(req.getParameter("USER_IDS")==null||req.getParameter("MESSAGE")==null){
            rs.setRetcode(2);
            rs.setErrbuf("接收人或者消息都不允许为空！");
        }else{
            ArrayList<Long> userIdList=new ArrayList<Long>();
            for(String userIdStr:req.getParameter("USER_IDS").split(",")){
            	userIdList.add(Long.parseLong(userIdStr));
            	LogUtil.log("user_id:"+Long.parseLong(userIdStr));
            }
            String message=req.getParameter("MESSAGE");
            systemWebSocketHandler().sendMessageToUsers(userIdList, new TextMessage(message));
            rs.setRetcode(0);
            rs.setErrbuf("发送成功！");
        }
        res.getWriter().print(rs.toJsonStr());
    }
	
}
