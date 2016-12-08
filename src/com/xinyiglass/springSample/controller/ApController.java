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

import com.xinyiglass.springSample.service.ApService;

import xygdev.commons.util.TypeConvert;

@Controller
@RequestMapping("/ap")
public class ApController {
	@Autowired
	ApService as;
	
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
    
    @RequestMapping("/apQuery.do")
	public String listApQuery(){
		if(sess.getAttribute("USER_ID")!=null&&sess.getAttribute("USER_ID").toString().length()>0){
			return "ap/apQuery";
		}else{
			return "error/sessionTimeout";
		}
	}
    
    @RequestMapping("/reqApQuery.do")
    public void reqApQuery() throws Exception{
    	Long orgId = TypeConvert.str2Long(req.getParameter("ORG_ID"));
    	Long custId = TypeConvert.str2Long(req.getParameter("CUSTOMER_ID"));
    	String apDate = req.getParameter("AP_DATE");
    	Long userId = (Long)sess.getAttribute("USER_ID");
    	as.apQuery(orgId, custId, apDate, userId); 	
    }
    
    @RequestMapping(value = "/getApHistory.do", method = RequestMethod.POST)
   	public void getOnhandPerm() throws Exception
   	{   	
   		int pageSize=Integer.parseInt(req.getParameter("pageSize"));
   		int pageNo=Integer.parseInt(req.getParameter("pageNo"));
   		boolean goLastPage=Boolean.parseBoolean(req.getParameter("goLastPage"));
   		String orderBy=req.getParameter("orderby");
   		Long userId = (Long)sess.getAttribute("USER_ID");
   		res.getWriter().print(as.findForPage(pageSize, pageNo, goLastPage, userId, orderBy));
   	}
    
}
