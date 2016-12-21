package com.xinyiglass.springSample.dao.impl;

import java.util.HashMap;
import java.util.Map;

import xygdev.commons.entity.PlsqlRetValue;
import xygdev.commons.entity.SqlResultSet;
import xygdev.commons.springjdbc.DevJdbcDaoSupport;

import com.xinyiglass.springSample.dao.CustGroupDao;

public class CustGroupDaoImpl extends DevJdbcDaoSupport implements CustGroupDao{

	public SqlResultSet findForCust(Long orgId,Long custId) throws Exception{
		Map<String,Object> paramMap=new  HashMap<String,Object>();
		String sql = "SELECT * FROM XYG_ALFR_CUST_ACCOUNT_V A WHERE ORG_ID = :1 AND CUST_ACCOUNT_ID = :2";
		paramMap.put("1", orgId);
		paramMap.put("2", custId);
		return this.getDevJdbcTemplate().queryForResultSet(sql, paramMap);
	}
	
	public PlsqlRetValue updateCustGroup(Long orgId,Long custAccountId,Long groupId) throws Exception{
		String sql = "Declare "
				+	"  l_group_id number; "
				+	"begin "
				+ "  XYG_ALB2B_GROUP_HEADERS_PKG.UPDATE_CUST_GROUP( "
				+ "  :1"
				+ " ,:2"
				+ " ,:3"
				+ ",:"+PlsqlRetValue.RETCODE
				+ ",:"+PlsqlRetValue.ERRBUF
				+ " ); "
				+ "end;";
		Map<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("1", orgId);
		paramMap.put("2", custAccountId);
		paramMap.put("3", groupId);	
		return this.getDevJdbcTemplate().executeForRetValue(sql, paramMap);
	}
	
	
}
