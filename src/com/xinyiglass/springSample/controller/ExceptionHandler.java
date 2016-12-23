package com.xinyiglass.springSample.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.xinyiglass.springSample.util.LogUtil;

/*
 * 统一controller层的异常处理！
 * 这里主要是500页面的处理。以后可以考虑添加页面的返回。
 */
public class ExceptionHandler implements HandlerExceptionResolver {  
    public ModelAndView resolveException(HttpServletRequest req, HttpServletResponse res, Object handler,  
            Exception ex) {  
        //Map<String, Object> model = new HashMap<String, Object>();  
        //model.put("ex", ex);  
          
        // 根据不同错误转向不同页面  
        /*
        if(ex instanceof BusinessException) {  
            return new ModelAndView("error-business", model);  
        }else if(ex instanceof ParameterException) {  
            return new ModelAndView("error-parameter", model);  
        } else {  
            return new ModelAndView("error", model);  
        }  */
    	LogUtil.error(req.getRequestURI()+",Exception:"+ex);
    	return null;
    }  
}  
