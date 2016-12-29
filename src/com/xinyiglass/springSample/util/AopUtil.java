package com.xinyiglass.springSample.util;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;

import xygdev.commons.entity.PlsqlRetValue;
import xygdev.commons.util.TypeConvert;

import com.xinyiglass.springSample.dao.UtilDao;
  
/** 
 * AOP面向切面编程
*  目前主要是添加初始化session的环境变量
 * @author Sam.T  2016-12-20 
 */  
@Aspect 
public class AopUtil {  

    @Autowired
    UtilDao utilDao;
    /*** 
     * service层调用之前先自动初始化环境变量
     * 需要注意的是，登录ID必须的参数放在最后！
     * @throws Exception 
     */  
	@Before("execution(* com.xinyiglass.springSample.service..*.*(..))  && args(..,loginId)")  
    public void alb2bInit(JoinPoint joinPoint,Long loginId) throws Exception{
		//System.out.println("AOP loginId:"+loginId+"-->"+joinPoint.getTarget().getClass()+":"+joinPoint.getSignature().getName());
        if(loginId!=null&&loginId>0&&loginId!=utilDao.getLoginId()){
    		PlsqlRetValue ret =utilDao.alb2bInit(loginId);//初始化环境变量！
        	if(ret!=null&&!TypeConvert.isNullValue(ret.getErrbuf())) LogUtil.log("ret:"+ret.toJsonStr());
        }
    }    
}  


