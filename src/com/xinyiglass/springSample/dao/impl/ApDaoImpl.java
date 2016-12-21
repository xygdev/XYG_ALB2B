package com.xinyiglass.springSample.dao.impl;

import java.util.HashMap;
import java.util.Map;

import com.xinyiglass.springSample.dao.ApDao;

import xygdev.commons.entity.PlsqlRetValue;
import xygdev.commons.springjdbc.DevJdbcDaoSupport;

public class ApDaoImpl extends DevJdbcDaoSupport implements ApDao{

	public PlsqlRetValue apQuery(Long orgId,Long custId,String apDate) throws Exception{
		String sql = "Declare "
				+	"  L_REQUEST_ID NUMBER; "
				+	"begin "
				+ "  XYG_ALB2B_REPORT_PKG.STATEMENT_ACCOUNT( "
				+ "  :1"
				+ " ,:2"
				+ " ,:3"
				+ ",:"+PlsqlRetValue.PARAM1
				+ ",:"+PlsqlRetValue.RETCODE
				+ ",:"+PlsqlRetValue.ERRBUF
				+ " ); "
				+ "end;";
		Map<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("1", orgId);
		paramMap.put("2", custId);
		paramMap.put("3", apDate);	
		return this.getDevJdbcTemplate().executeForRetValue(sql, paramMap);
	}
}
