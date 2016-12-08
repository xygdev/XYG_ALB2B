package com.xinyiglass.springSample.dao.impl;

import java.util.HashMap;
import java.util.Map;

import com.xinyiglass.springSample.dao.OnhandSetVODao;
import com.xinyiglass.springSample.entity.OnhandSetVO;
import com.xinyiglass.springSample.util.Constant;

import xygdev.commons.entity.PlsqlRetValue;
import xygdev.commons.entity.SqlResultSet;
import xygdev.commons.springjdbc.DevJdbcDaoSupport;

public class OnhandSetVODaoImpl extends DevJdbcDaoSupport implements  OnhandSetVODao{
	public void log(String log){
		if (Constant.DEBUG_MODE){
			System.out.println(log);
		}
	}
	
	public SqlResultSet findByIdForJSON(Long organizationId) throws Exception{
		Map<String,Object> paramMap=new  HashMap<String,Object>();
		String sql = "SELECT * FROM XYG_ALB2B_ONHAND_SET_V WHERE ORGANIZATION_ID = :1";
		paramMap.put("1", organizationId);
		return this.getDevJdbcTemplate().queryForResultSet(sql, paramMap);
	}
	
	public OnhandSetVO findById(Long organizationId) throws Exception{
		Map<String,Object> paramMap=new  HashMap<String,Object>();
		String sql = "SELECT * FROM XYG_ALB2B_ONHAND_SET_V WHERE ORGANIZATION_ID = :1";
		paramMap.put("1", organizationId);
		return this.getDevJdbcTemplate().queryForObject(sql, paramMap, new OnhandSetVO());
	}
	
	//insert
	public PlsqlRetValue insert(OnhandSetVO os) throws Exception{
		String sql ="Declare "
				+ "     l_organ_id number; "
				+ "  begin "
				+ "  XYG_ALB2B_ONHAND_SET_PKG.INSERT_ONHAND_SET( "
				+ "  :1"
				+ " ,:2"
				+ " ,:3"
				+ " ,:"+PlsqlRetValue.RETCODE
				+ " ,:"+PlsqlRetValue.ERRBUF
				+ " ); "
				+ "end;";
		Map<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("1", os.getOrganizationId());
		paramMap.put("2", os.getOnhandGreaterBox());
		paramMap.put("3", os.getOnhandDisplayBox());
		return this.getDevJdbcTemplate().executeForRetValue(sql, paramMap);
	}	
	
	//lock
	public PlsqlRetValue lock(OnhandSetVO os) throws Exception{
		String sql ="Declare "
				+ "     l_organ_id number; "
				+ "  begin "
				+ "  XYG_ALB2B_ONHAND_SET_PKG.LOCK_ONHAND_SET( "
				+ "  :1"
				+ " ,:2"
				+ " ,:3"
				+ " ,:"+PlsqlRetValue.RETCODE
				+ " ,:"+PlsqlRetValue.ERRBUF
				+ " ); "
				+ "end;";
		Map<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("1", os.getOrganizationId());
		paramMap.put("2", os.getOnhandGreaterBox());
		paramMap.put("3", os.getOnhandDisplayBox());
		return this.getDevJdbcTemplate().executeForRetValue(sql, paramMap);
	}
	
	//update
	public PlsqlRetValue update(OnhandSetVO os) throws Exception{
		String sql ="Declare "
				+ "     l_organ_id number; "
				+ "  begin "
				+ "  XYG_ALB2B_ONHAND_SET_PKG.UPDATE_ONHAND_SET( "
				+ "  :1"
				+ " ,:2"
				+ " ,:3"
				+ " ,:"+PlsqlRetValue.RETCODE
				+ " ,:"+PlsqlRetValue.ERRBUF
				+ " ); "
				+ "end;";
		Map<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("1", os.getOrganizationId());
		paramMap.put("2", os.getOnhandGreaterBox());
		paramMap.put("3", os.getOnhandDisplayBox());
		return this.getDevJdbcTemplate().executeForRetValue(sql, paramMap);
	}
}
