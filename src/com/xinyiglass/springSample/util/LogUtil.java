package com.xinyiglass.springSample.util;

import xygdev.commons.util.TypeConvert;


/** 
 * 日志处理包。以后所有的日志 LogUtil.log()
 * @author Sam.T  2016-12-21
 */  
public class LogUtil {  
    public static void log(Object log){  
    	if (Constant.DEBUG_MODE){
			System.out.println(TypeConvert.type2Str(log));
		}
    }    
}  


