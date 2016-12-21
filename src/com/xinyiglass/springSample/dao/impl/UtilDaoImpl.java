package com.xinyiglass.springSample.dao.impl;

import java.util.HashMap;
import java.util.Map;

import com.xinyiglass.springSample.dao.UtilDao;

import xygdev.commons.entity.PlsqlRetValue;
import xygdev.commons.springjdbc.DevJdbcDaoSupport;

public class UtilDaoImpl extends DevJdbcDaoSupport implements UtilDao{
	public PlsqlRetValue alb2bInit(Long loginId) throws Exception{
		String sql ="Declare "
				+ "     l_user_id number; "
				+ "  begin "
				+ "  XYG_ALD_GLOBAL_PKG.ALB2B_INIT( "
				+ "  :1"
				+ " ,null "
				+ " ,:"+PlsqlRetValue.RETCODE
				+ " ,:"+PlsqlRetValue.ERRBUF
				+ " ); "
				+ "end;";
		Map<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("1", loginId);
		return this.getDevJdbcTemplate().executeForRetValue(sql, paramMap);
	}
}
