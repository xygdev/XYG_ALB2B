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
import com.xinyiglass.springSample.util.Alb2bInit;
import com.xinyiglass.springSample.util.LogUtil;

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
		uvos.setLoginId((Long)sess.getAttribute("LOGIN_ID"));
		ls.setLoginId((Long)sess.getAttribute("LOGIN_ID"));
    } 
	
	@RequestMapping("/")
	public String index(){
		return "login-ch";
	}
	
	@RequestMapping(value="/login.do",method=RequestMethod.POST)
	public ModelAndView postLogin(String username,String password,String lang) throws Exception{
		ModelAndView mv = new ModelAndView();
		String ipAddress = getIp(req);
		username=username.toUpperCase();//用户名大小写都可以登录
		PlsqlRetValue ret=ls.handleLogin(password, username, lang,ipAddress);
		int retCode = ret.getRetcode();
		if(retCode==2){
			mv.setViewName("login-ch");
			sess.setAttribute("errorMsg", ret.getErrbuf());
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
			 if(retCode==1){
				 mv.setViewName("redirect:/modifyPWD.do");
				 sess.setAttribute("errorMsg", ret.getErrbuf());
			 }else if(retCode==0){				
				 mv.setViewName("redirect:/index.do"); 
			 }
			 //这里全局初始化。例如启用调试等
			 Alb2bInit.init(null);//Boolean.parseBoolean("false")
			 LogUtil.log("成功登录!-->当前SESS会话"+sess.getId()+" 匹配的longId:"+ret.getParam1());
		 }
		return mv;
	}

	@RequestMapping(value="/login.do",method=RequestMethod.GET)
	public String getLogin(){
		return "redirect:/";
	}
	
	@RequestMapping(value="/index.do")
	public void listIndex() throws Exception{
		String debug=req.getParameter("debug");
		if(debug!=null){//重新初始化
			Alb2bInit.init(Boolean.parseBoolean(debug));
		}
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
			LogUtil.log("Error:"+ret.getErrbuf());
		}	
		sess.invalidate();
		/*Enumeration<String> sessionKeys = sess.getAttributeNames();
		while(sessionKeys.hasMoreElements()){
			sess.removeAttribute(sessionKeys.nextElement());
		}*/
		//LogUtil.log("TEST LOGIN_ID:"+sess.getAttribute("LOGIN_ID"));
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
	
	public static String getIp(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");     
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {     
			ip = request.getHeader("Proxy-Client-IP");     
		}     
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {     
			ip = request.getHeader("WL-Proxy-Client-IP");     
		}     
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {     
			ip = request.getRemoteAddr();     
		}     
		return ip;
		/*
        String ip = request.getHeader("X-Forwarded-For");
        if(StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)){
            //多次反向代理后会有多个ip值，第一个ip才是真实ip
            int index = ip.indexOf(",");
            if(index != -1){
                return ip.substring(0,index);
            }else{
                return ip;
            }
        }
        ip = request.getHeader("X-Real-IP");
        if(StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)){
            return ip;
        }
        return request.getRemoteAddr();*/
    }
}