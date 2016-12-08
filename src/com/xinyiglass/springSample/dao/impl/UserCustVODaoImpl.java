package com.xinyiglass.springSample.dao.impl;

import java.util.HashMap;
import java.util.Map;

import xygdev.commons.entity.PlsqlRetValue;
import xygdev.commons.entity.SqlResultSet;
import xygdev.commons.springjdbc.DevJdbcDaoSupport;
import xygdev.commons.util.TypeConvert;

import com.xinyiglass.springSample.dao.UserCustVODao;
import com.xinyiglass.springSample.entity.UserCustVO;
import com.xinyiglass.springSample.util.Constant;

public class UserCustVODaoImpl extends DevJdbcDaoSupport implements UserCustVODao{
	public void log(String log){
		if (Constant.DEBUG_MODE){
			System.out.println(log);
		}
	}
	
	//insert
	public PlsqlRetValue insert(UserCustVO u) throws Exception{
		String sql ="Declare "
				+ "     l_user_cust_id number; "
				+ "  begin "
				+ "  XYG_ALB2B_USER_CUSTOMER_PKG.INSERT_USER_CUSTOMER( "
				+ "  :1"
				+ " ,:2"
				+ " ,:3"
                + " ,:4"
                + " ,:5"
                + " ,:6"
				+ " ,:"+PlsqlRetValue.RETCODE
				+ " ,:"+PlsqlRetValue.ERRBUF
				+ " );"
				+ "end;";
		Map<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("1", u.getUserCustId());
		paramMap.put("2", u.getUserId());
		paramMap.put("3", u.getOrgId());
		paramMap.put("4", u.getCustomerId());
		paramMap.put("5", TypeConvert.u2tDate(u.getStartDate()));
		paramMap.put("6", TypeConvert.u2tDate(u.getEndDate()));
		return this.getDevJdbcTemplate().executeForRetValue(sql, paramMap);
	}
	
	//update
	public PlsqlRetValue update(UserCustVO u) throws Exception{
		String sql ="Declare "
				+ "     l_user_cust_id number; "
				+ "  begin "
				+ "  XYG_ALB2B_USER_CUSTOMER_PKG.UPDATE_USER_CUSTOMER( "
				+ "  :1"
				+ " ,:2"
				+ " ,:"+PlsqlRetValue.RETCODE
				+ " ,:"+PlsqlRetValue.ERRBUF
				+ " ); "
				+ "end;";
		Map<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("1", u.getUserCustId());
		paramMap.put("2", TypeConvert.u2tDate(u.getEndDate()));
		return this.getDevJdbcTemplate().executeForRetValue(sql, paramMap);
	}
	
	public SqlResultSet findByIdForJSON(Long userCustId) throws Exception{
		Map<String,Object> paramMap=new  HashMap<String,Object>();
		String sql = "SELECT * FROM XYG_ALB2B_USER_CUSTOMER_V WHERE USER_CUST_ID = :1";
		paramMap.put("1",userCustId);
		return this.getDevJdbcTemplate().queryForResultSet(sql, paramMap);
	}
}
