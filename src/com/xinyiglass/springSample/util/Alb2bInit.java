package com.xinyiglass.springSample.util;

import org.apache.commons.net.ftp.FTP;

/** 
 * ALB2B浮法大板B2B系统全局初始化。登录的时候使用！
 * @author Sam.T  2016-12-21
 */  
public class Alb2bInit {  
    public static void init(){  
		xygdev.commons.util.Constant.DEBUG_MODE=false;//common包的调试模式
    	Constant.DEBUG_MODE = true;//b2b系统的调试模式
    	Constant.IMAGE_USER_PATH = "E:\\image\\user\\";//"/ebs/data/image/user/"
    	Constant.LOCAL_CHARSET = "GBK";
    	Constant.SERVER_CHARSET = FTP.DEFAULT_CONTROL_ENCODING;
    	Constant.DIR_SEP="/";//ftp和unix用：/    ；本地win系统用：\\\\
    	//报表输出文件夹路径。注意：不包括/结尾的！
    	Constant.CONC_OUT="/home/oracle/conc/out";
    	Constant.FTP_HOST="192.168.0.26";
    	Constant.FTP_PORT=21;
    	Constant.FTP_USER="oracle";
    	Constant.FTP_PASS="oracle";
    }    
}  


