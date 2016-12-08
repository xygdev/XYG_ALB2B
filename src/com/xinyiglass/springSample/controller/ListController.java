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

import com.xinyiglass.springSample.service.ListService;

@Controller
@RequestMapping("/list")
public class ListController {
	
	@Autowired
	ListService listService;
	
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
	
	@RequestMapping(value = "/getEnableFlag.do", method = RequestMethod.POST)
	public void getEnableFlag() throws Exception
	{
		res.getWriter().print(listService.findForEnableFlag());
	}
	
	@RequestMapping(value = "/getUserType.do", method = RequestMethod.POST)
	public void getUserType() throws Exception
	{
		res.getWriter().print(listService.findForUserType());
	}
}
