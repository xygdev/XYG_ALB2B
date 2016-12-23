package com.xinyiglass.springSample.util;

import org.apache.log4j.Logger;

import xygdev.commons.util.TypeConvert;


/** 
 * 日志处理包。以后所有的日志 LogUtil.log()
 * @author Sam.T  2016-12-21
 */  
public class LogUtil {  
	private static Logger logger = Logger.getLogger(LogUtil.class); 
	//正常的日志调试信息。调试模式打开之后会自动记录到log。
    public static void log(Object log){  
    	if (Constant.DEBUG_MODE){
			System.out.println(TypeConvert.type2Str(log));
			logger.info(log);
		}
    }    
    //error级别的异常信息，是无论如何都必须要记录到日志的。
    public static void error(Object err){  
		logger.error(err);
		System.out.println(TypeConvert.type2Str(err));
    }    
}  


