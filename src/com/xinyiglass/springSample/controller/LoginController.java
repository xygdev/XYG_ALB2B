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
		mv.addObject("username",username);
		mv.addObject("password",password);
		mv.addObject("lang",lang);
		PlsqlRetValue ret=ls.handleLogin(password, username, lang);
		int retCode = ret.getRetcode();
		if(retCode==2){
			mv.setViewName("login-ch");
			sess.setAttribute("errorMsg", ret.getErrbuf());
			System.out.println(ret.getErrbuf());
		 }else{
			 UserVO user=uvos.findForUserVOByName(username);
			 sess.setAttribute("USER_ID", user.getUserId());
			 sess.setAttribute("USER_NAME", user.getUserName());
			 sess.setAttribute("DESC", user.getDescription());
			 sess.setAttribute("IMG", user.getImgUrl());
			 sess.setAttribute("RESP_ID", user.getRespId());
			 sess.setAttribute("RESP", user.getRespName());
			 sess.setAttribute("USER_TYPE", user.getUserType());
			 if(retCode==1){
				 mv.setViewName("index/modifyPWD");
				 sess.setAttribute("errorMsg", ret.getErrbuf());
				 System.out.println(ret.getErrbuf());
			 }else if(retCode==0){
				 mv.setViewName("index/index");
			 }
		 }
		return mv;
	}

	@RequestMapping(value="/login.do",method=RequestMethod.GET)
	public String getLogin(){
		if(sess.getAttribute("USER_ID")!=null&&sess.getAttribute("USER_ID").toString().length()>0){
			return "redirect:/";
		}else{
			return "error/sessionTimeout";
		}
	}

	@RequestMapping(value="/logout.do",method=RequestMethod.POST)
	public ModelAndView getLogout(){
		ModelAndView mv = new ModelAndView();
		sess.setAttribute("USER_ID", null);
		sess.setAttribute("errorMsg",null);
		mv.setViewName("login-ch");
		return mv;
	}
	
	@RequestMapping(value="/home.do")
	public String listHome(){
		if(sess.getAttribute("USER_ID")!=null&&sess.getAttribute("USER_ID").toString().length()>0){
			return "home/home";
		}else{
			return "error/sessionTimeout";
		}
	}
	
	@RequestMapping(value="/404.do")
	public String pageNotFonud(){
		return "error/404notFound";
	}
	
}