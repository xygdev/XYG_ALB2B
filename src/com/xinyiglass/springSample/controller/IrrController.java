package com.xinyiglass.springSample.controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import xygdev.commons.entity.PlsqlRetValue;
import xygdev.commons.interact.InteractPub;
import xygdev.commons.util.TypeConvert;


@Controller
@RequestMapping("/irr")
public class IrrController {

	@Autowired
	InteractPub irrPub;
	
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

	@RequestMapping(value = "/getDefaultIrr.do", method = RequestMethod.POST)
	public void getDefaultIrr() throws Exception
	{   	
		//获取用户的默认打开的交互式报表定义
		Long userId=Long.parseLong((String)req.getParameter("USER_ID"));
		String interactCode=(String)req.getParameter("INTERACT_CODE");
		res.getWriter().print(irrPub.getIrr(irrPub.getDefaultIrrHid(userId, interactCode)));
	}

	//对应旧的：/getSaveData
	@RequestMapping(value = "/saveIrr.do", method = RequestMethod.POST)
	public void saveIrr() throws Exception
	{   
		//将交互式报表的定义存到数据库
		Long userId=TypeConvert.str2Long((String)req.getParameter("USER_ID"));
		String interactCode=(String)req.getParameter("INTERACT_CODE");
		String userInteractName=(String)req.getParameter("USER_INTERACT_NAME");
		String description=(String)req.getParameter("DESCRIPTION");
		String publicFlag=(String)req.getParameter("PUBLIC_FLAG");
		String autoqueryFlag=(String)req.getParameter("AUTOQUERY_FLAG");
		String defaultFlag=(String)req.getParameter("DEFAULT_FLAG");
		String orderBy=(String)req.getParameter("ORDER_BY");
		int pageSize=Integer.parseInt((String)req.getParameter("PAGE_SIZE")) ;
		String seq=(String)req.getParameter("SEQ");
		PlsqlRetValue ret=irrPub.saveIrr(userId, interactCode, userInteractName, description
															, publicFlag, autoqueryFlag, defaultFlag, orderBy, pageSize, seq);
		//前端判断：0：处理成功。非0：处理失败。
        res.getWriter().print(ret.toJsonStr()); 
	}
	
	@RequestMapping(value = "/getIrrHead.do", method = RequestMethod.POST)
	public void getIrrHead() throws Exception
	{   
		//获取用户可用的所有IRR定义的头列表
		Long userId=TypeConvert.str2Long((String)req.getParameter("USER_ID"));
		String interactCode=(String)req.getParameter("INTERACT_CODE");
		System.out.println("userId:"+userId); 
		//根据用户和报表的名称获取默认打开的文件夹 
        res.getWriter().print(irrPub.getIrrHead(userId, interactCode)); 
	}
	
	@RequestMapping(value = "/getIrr.do", method = RequestMethod.POST)
	public void getIrr() throws Exception
	{   
		Long header_id = TypeConvert.str2Long(req.getParameter("HEADER_ID")); 
        res.getWriter().print(irrPub.getIrr(header_id)); 
	}
	
}
