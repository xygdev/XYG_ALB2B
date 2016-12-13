package com.xinyiglass.springSample.util;

import org.apache.commons.net.ftp.FTP;

public class Constant {
	public static boolean DEBUG_MODE = true;
	public static String LOCAL_CHARSET = "GBK";
	public static String SERVER_CHARSET = FTP.DEFAULT_CONTROL_ENCODING;
	public static String DIR_SEP="/";//ftp和unix用：/    ；本地win系统用：\\\\
	//报表输出文件夹路径。注意：不包括/结尾的！
	public static String CONC_OUT="/home/oracle/conc/out";
	public static String FTP_HOST="192.168.0.26";
	public static int FTP_PORT=21;
	public static String FTP_USER="oracle";
	public static String FTP_PASS="oracle";
}
