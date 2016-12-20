package com.xinyiglass.springSample.dao.impl;

import java.util.HashMap;
import java.util.Map;

import com.xinyiglass.springSample.dao.LoginDao;
import com.xinyiglass.springSample.util.Constant;

import xygdev.commons.entity.PlsqlRetValue;
import xygdev.commons.springjdbc.DevJdbcDaoSupport;

public class LoginDaoImpl extends DevJdbcDaoSupport implements LoginDao{
	public void log(String log){
		if (Constant.DEBUG_MODE){
			System.out.println(log);
		}
	}
	
	public PlsqlRetValue handleLogin(String lang,String userName,String password) throws Exception{
		String sql ="Declare "
				+ "     l_user_id number; "
				+ "  begin "
				+ "  XYG_ALB2B_LOGIN_PKG.HANDLE_LOGIN( "
				+ "  :1"
				+ " ,:2"
				+ " ,:3"
				+ " ,:4"
				+ " ,:"+PlsqlRetValue.PARAM1
				+ " ,:"+PlsqlRetValue.RETCODE
				+ " ,:"+PlsqlRetValue.ERRBUF
				+ " ); "
				+ "end;";
		Map<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("1", userName);
		paramMap.put("2", password);
		paramMap.put("3", lang);
		paramMap.put("4", "");
		return this.getDevJdbcTemplate().executeForRetValue(sql, paramMap);
	}
	
	public PlsqlRetValue logout(Long loginId) throws Exception{
		String sql ="Declare "
				+ "     l_user_id number; "
				+ "  begin "
				+ "  XYG_ALB2B_LOGIN_PKG.LOGOUT( "
				+ "  :1"
				+ " ,:"+PlsqlRetValue.RETCODE
				+ " ,:"+PlsqlRetValue.ERRBUF
				+ " ); "
				+ "end;";
		xygdev.commons.util.Constant.DEBUG_MODE=true;
		Map<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("1", loginId);
		return this.getDevJdbcTemplate().executeForRetValue(sql, paramMap);
	}
}
