package com.xinyiglass.springSample.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.xinyiglass.springSample.dao.MailDao;
import com.xinyiglass.springSample.util.Constant;

import xygdev.commons.entity.PlsqlRetValue;
import xygdev.commons.entity.SqlResultSet;
import xygdev.commons.springjdbc.DevJdbcDaoSupport;

public class MailDaoImpl  extends DevJdbcDaoSupport implements MailDao{
	public void log(String log){
		if (Constant.DEBUG_MODE){
			System.out.println(log);
		}
	}
	
	public SqlResultSet findRecMailByIdForRS(Long sendid) throws Exception{
		String sql = "SELECT * FROM XYG_ALB2B_RECEIVE_V WHERE SEND_ID = :1";
		Map<String,Object> paramMap=new  HashMap<String,Object>();
		paramMap.put("1", sendid);
		return this.getDevJdbcTemplate().queryForResultSet(sql, paramMap);
	}
	
	public SqlResultSet findSendMailByIdForRS(Long sendid) throws Exception{
		String sql = "SELECT * FROM XYG_ALB2B_SEND_V WHERE SEND_ID = :1";
		Map<String,Object> paramMap=new  HashMap<String,Object>();
		paramMap.put("1", sendid);
		return this.getDevJdbcTemplate().queryForResultSet(sql, paramMap);
	}
	
	public PlsqlRetValue updateRecMail(Long recid) throws Exception{
		String sql = "Declare "
				+	"  l_receive_id number; "
				+	"begin "
				+ "  XYG_ALB2B_RECEIVE_PKG.UPDATE_RECEIVE("
				+ "  :1"//P_EMP_ID IN NUMBER
				+ " ,:2"//,P_EMP_NUMBER IN VARCHAR2 DEFAULT NULL
				+ ",:"+PlsqlRetValue.RETCODE
				+ ",:"+PlsqlRetValue.ERRBUF
				+ " ); "
				+ "end;";
		Map<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("1", recid);
		paramMap.put("2", "Y");
		return this.getDevJdbcTemplate().executeForRetValue(sql, paramMap);
	}      
	
	public PlsqlRetValue deleteRecMail(Long recid) throws Exception{
		String sql = "Declare "
				+	"  l_receive_id number; "
				+	"begin "
				+ "  XYG_ALB2B_RECEIVE_PKG.DELETE_RECEIVE("
				+ "  :1"//P_EMP_ID IN NUMBER
				+ ",:"+PlsqlRetValue.RETCODE
				+ ",:"+PlsqlRetValue.ERRBUF
				+ " ); "
				+ "end;";
		Map<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("1", recid);
		return this.getDevJdbcTemplate().executeForRetValue(sql, paramMap);
	}
	
	public PlsqlRetValue insertMail(Long sendUserId,String title,String content,String sendType,String recUser)throws Exception{
		Map<String,Object> paramMap=new HashMap<String,Object>();
		System.out.println("content="+content);
		System.out.println("recUser="+recUser);
		String sql= " declare"
				+ " begin "
				+ " XYG_ALB2B_SEND_PKG.HANDLE_SEND( "
				+ " :SEND_USER_ID "
				+ " ,:SEND_TITLE "
				+ " ,:SEND_CONTENT "
				+ " ,SYSDATE "
				+ " ,:SEND_TYPE "
				+ " ,:RECEIVE_USER_IDS"//:RECEIVE_USER_IDS L_RECEIVE_USER_IDS
				+ " ,:"+PlsqlRetValue.PARAM1
				+ " ,:"+PlsqlRetValue.RETCODE
				+ " ,:"+PlsqlRetValue.ERRBUF
				+ " ); "
				+ "end; ";
		paramMap.clear();
		paramMap.put("SEND_USER_ID", sendUserId);
		paramMap.put("SEND_TITLE", title);
		paramMap.put("SEND_CONTENT", this.getOracleCLOB(content));//this.getOracleCLOB(content)
		//paramMap.put("SEND_DATE", TypeConvert.str2sDate("2016-9-5"));
		paramMap.put("SEND_TYPE", sendType);
		String[] userArray=null;
		System.out.println("Ruser:"+recUser);
		if(recUser==null||recUser==""){
				System.out.println("sendALL");
		}else{
			userArray=recUser.split(",");	
		}
		paramMap.put("RECEIVE_USER_IDS", this.getOracleARRAY("APEX_UAT.XYG_ALB2B_SEND_PKG.NUM_TBL_TYPE", userArray));
		return this.getDevJdbcTemplate().executeForRetValue(sql, paramMap);
	}
	
	
	
	
	
	//测试专用
	@SuppressWarnings("resource")
	public static void main(String[] args){
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
	    MailDao mDao= (MailDao)context.getBean("MailDao");
		try {
			mDao.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
