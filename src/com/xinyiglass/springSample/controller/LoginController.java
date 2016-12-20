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
import org.springframework.web.servlet.ModelAndView;

import xygdev.commons.entity.PlsqlRetValue;
import xygdev.commons.util.TypeConvert;

import com.xinyiglass.springSample.entity.UserVO;
import com.xinyiglass.springSample.service.LoginService;
import com.xinyiglass.springSample.service.UserVOService;

@Controller
public class LoginController {
	
	@Autowired
	UserVOService uvos;
	@Autowired
	LoginService ls;
	
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
	
	@RequestMapping("/")
	public String index(){
		return "login-ch";
	}
	
	@RequestMapping(value="/login.do",method=RequestMethod.POST)
	public ModelAndView postLogin(String username,String password,String lang) throws Exception{
		ModelAndView mv = new ModelAndView();
		PlsqlRetValue ret=ls.handleLogin(password, username, lang);
		int retCode = ret.getRetcode();
		if(retCode==2){
			mv.setViewName("login-ch");
			sess.setAttribute("errorMsg", ret.getErrbuf());
			System.out.println(ret.getErrbuf());
		 }else{
			 UserVO user=uvos.findForUserVOByName(username);
			 sess.setAttribute("LOGIN_ID",TypeConvert.str2Long(ret.getParam1()));
			 sess.setAttribute("USER_ID", user.getUserId());
			 sess.setAttribute("USER_NAME", user.getUserName());
			 sess.setAttribute("DESC", user.getDescription());
			 sess.setAttribute("IMG", user.getImgUrl());
			 sess.setAttribute("RESP_ID", user.getRespId());
			 sess.setAttribute("RESP", user.getRespName());
			 sess.setAttribute("USER_TYPE", user.getUserType());
			 System.out.println("Login_ID:"+ret.getParam1());
			 if(retCode==1){
				 mv.setViewName("redirect:/modifyPWD.do");
				 sess.setAttribute("errorMsg", ret.getErrbuf());
				 System.out.println(ret.getErrbuf());
			 }else if(retCode==0){				 
				 mv.setViewName("redirect:/index.do"); 
			 }
		 }
		return mv;
	}

	@RequestMapping(value="/login.do",method=RequestMethod.GET)
	public String getLogin(){
		return "redirect:/";
	}
	
	@RequestMapping(value="/index.do")
	public void listIndex() throws Exception{
		req.getRequestDispatcher("/WEB-INF/page/index/index.jsp").forward(req,res);	
	}
	
	@RequestMapping(value="/modifyPWD.do")
	public void listModifyPWD() throws Exception{
		req.getRequestDispatcher("/WEB-INF/page/index/modifyPWD.jsp").forward(req,res);	
	}	

	@RequestMapping(value="/logout.do",method=RequestMethod.POST)
	public ModelAndView getLogout() throws Exception{
		ModelAndView mv = new ModelAndView();
		Long loginId = (Long)sess.getAttribute("LOGIN_ID");
		PlsqlRetValue ret=ls.logout(loginId);
		if(ret.getRetcode()!=0){
			System.out.println("Error:"+ret.getErrbuf());
		}	
		sess.invalidate();
		mv.setViewName("login-ch");
		return mv;
	}
	
	@RequestMapping(value="/home.do")
	public String listHome(){
		return "home/home";
	}
	
	@RequestMapping(value="/404.do")
	public String pageNotFonud(){
		return "error/404notFound";
	}	
}