package com.xinyiglass.springSample.util;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;

import xygdev.commons.entity.PlsqlRetValue;
import xygdev.commons.util.TypeConvert;

import com.xinyiglass.springSample.dao.UtilDao;

import java.lang.reflect.Method;
  
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
     * @throws Exception 
     */  
	@Before("execution(* com.xinyiglass.springSample.service..*.*(..))")  
    public void alb2bInit(JoinPoint joinPoint) throws Exception{
		Method method = null;
        Object target = null;
        Long loginId=null;
        String methodName = "getLoginId";
        target = joinPoint.getTarget();
        method = getMethodByClassAndName(target.getClass(), methodName);
        if(method!=null){
        	loginId=(Long) method.invoke(target);//LogUtil.log("loginId:"+loginId);
            if(loginId!=null&&loginId>0&&loginId!=utilDao.getLoginId()){
        		PlsqlRetValue ret =utilDao.alb2bInit(loginId);//初始化环境变量！
            	if(ret!=null&&!TypeConvert.isNullValue(ret.getErrbuf())) LogUtil.log("ret:"+ret.toJsonStr());
            }
        }
    }    
	/**
	 * 根据类和方法名得到方法
	 */
	@SuppressWarnings("rawtypes")
	public Method getMethodByClassAndName(Class c, String methodName)throws Exception {
		Method[] methods = c.getDeclaredMethods();
		for (Method method : methods) {
			if (method.getName().equals(methodName)) {
				return method;
			}
		}
		return null;
	}
}  


