package com.xinyiglass.springSample.util;

import java.io.IOException;  
import javax.servlet.Filter;  
import javax.servlet.FilterChain;  
import javax.servlet.FilterConfig;  
import javax.servlet.ServletException;  
import javax.servlet.ServletRequest;  
import javax.servlet.ServletResponse;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
import javax.servlet.http.HttpSession; 

public class SecurityServlet extends HttpServlet implements Filter {  
    private static final long serialVersionUID = 1L;  
  
    public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2) throws IOException, ServletException {  
           HttpServletRequest req=(HttpServletRequest)arg0;     
           HttpServletResponse res  =(HttpServletResponse) arg1;      
           HttpSession sess = req.getSession(true);       
           String user = (String)sess.getAttribute("USER_NAME");// 登录人  
           String resp = (String)sess.getAttribute("RESP");//登录人角色  
           String url=req.getRequestURI();  
           System.out.println("/****fliter start*****/");
           System.out.println("user:"+user);
           System.out.println("resp:"+resp);
           System.out.println("ReqURI:"+url);
           System.out.println("/****fliter end*****/");
           if(user==null || "".equals(user) || resp == null || "".equals(resp)) {        
                //判断获取的路径不为空且不是访问登录页面或执行登录操作时跳转     
                if(url!=null && !url.equals("") && (url.indexOf("login.do")<0 ) && (url.indexOf("404.do")<0) && (url.indexOf("logout.do")<0)) {     
                    //res.sendRedirect(req.getContextPath() + "/error/sessionTimeout");    
                    req.getRequestDispatcher("/WEB-INF/page/error/sessionTimeout.jsp").forward(req,res);
                    return ;     
                }                
            }     
            arg2.doFilter(arg0, arg1);     
            return;     
    }  
    public void init(FilterConfig arg0) throws ServletException {  
    }  
  
}  