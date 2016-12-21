package com.xinyiglass.springSample.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.socket.TextMessage;

import xygdev.commons.entity.PlsqlRetValue;
import xygdev.commons.page.PagePub;
import xygdev.commons.sqlStmt.SqlStmtPub;

import com.xinyiglass.springSample.dao.ApDao;
import com.xinyiglass.springSample.websocket.SystemWebSocketHandler;

@Service
@Transactional(rollbackFor=Exception.class)
public class ApService {
	@Autowired
	ApDao apDao;
	@Autowired
	PagePub pagePub;
    
	private HttpSession sess;
	
	public HttpSession getSess() {
		return sess;
	}

	public void setSess(HttpSession sess) {
		this.sess = sess;
	}
	
	@Bean
    public SystemWebSocketHandler systemWebSocketHandler() {
        return new SystemWebSocketHandler();
    }
	
	@Async  
    public void apQuery(Long orgId,Long custId,String apDate,Long userId) throws Exception{  
		Thread.sleep(5000);
		PlsqlRetValue ret= apDao.apQuery(orgId, custId, apDate);
        ArrayList<Long> userIdList=new ArrayList<Long>();
        userIdList.add(userId);
        if(ret.getRetcode()==0){
        	String message="您的请求编号为："+ret.getParam1()+"的报表已经生成，请在收件箱查收";
            systemWebSocketHandler().sendMessageToUsers(userIdList, new TextMessage(message));
            System.out.println("Request Complete");  
        }else if(ret.getRetcode()==2){
        	String message="报表运行失败，错误信息"+ret.getErrbuf();
        	systemWebSocketHandler().sendMessageToUsers(userIdList, new TextMessage(message));
            System.out.println("Request Failed");  
        }
        return;
    } 
	
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public String findForPage(int pageSize,int pageNo,boolean goLastPage,Long userId,String orderBy) throws Exception{
		Map<String,Object> paramMap=new HashMap<String,Object>();
		StringBuffer sqlBuff = new StringBuffer();
		sqlBuff.append("SELECT * FROM XYG_ALB2B_REQUEST_LOG_V A WHERE 1=1");
		sqlBuff.append(SqlStmtPub.getAndStmt("REQUESTED_BY",userId,paramMap));
		sqlBuff.append(" ORDER BY "+orderBy);
		return pagePub.qPageForJson(sqlBuff.toString(), paramMap, pageSize, pageNo, goLastPage);
	}

}
