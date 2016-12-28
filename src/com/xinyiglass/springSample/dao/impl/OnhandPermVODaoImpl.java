package com.xinyiglass.springSample.dao.impl;

import java.util.HashMap;
import java.util.Map;

import xygdev.commons.entity.PlsqlRetValue;
import xygdev.commons.entity.SqlResultSet;
import xygdev.commons.springjdbc.DevJdbcDaoSupport;
import xygdev.commons.util.TypeConvert;

import com.xinyiglass.springSample.dao.OnhandPermVODao;
import com.xinyiglass.springSample.entity.OnhandPermVO;

public class OnhandPermVODaoImpl extends DevJdbcDaoSupport implements OnhandPermVODao{

	public SqlResultSet findByIdForJSON(Long pId) throws Exception{
		Map<String,Object> paramMap=new  HashMap<String,Object>();
		String sql = "SELECT * FROM XYG_ALB2B_ONHAND_PERM_V WHERE P_ID = :1";
		paramMap.put("1", pId);
		return this.getDevJdbcTemplate().queryForResultSet(sql, paramMap);
	}
	
	public OnhandPermVO findByPId(Long pId) throws Exception{
		Map<String,Object> paramMap=new  HashMap<String,Object>();
		String sql = "SELECT * FROM XYG_ALB2B_ONHAND_PERM_V WHERE P_ID = :1";
		paramMap.put("1", pId);
		return this.getDevJdbcTemplate().queryForObject(sql, paramMap, new OnhandPermVO());
	}
	
	//insert
	public PlsqlRetValue insert(OnhandPermVO op) throws Exception{
		String sql ="Declare "
				+ "     l_p_id number; "
				+ "  begin "
				+ "  XYG_ALB2B_ONHAND_PERM_PKG.INSERT_ONHAND_PERM( "
				+ "  :1"
				+ " ,:2"
				+ " ,:3"
                + " ,:4"
                + " ,:5"
				+ " ,:"+PlsqlRetValue.RETCODE
				+ " ,:"+PlsqlRetValue.ERRBUF
				+ " ); "
				+ "end;";
		Map<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("1", op.getPId());
		paramMap.put("2", op.getUserId());
		paramMap.put("3", op.getOrganizationId());
		paramMap.put("4", TypeConvert.u2tDate(op.getStartDate()));
		paramMap.put("5", TypeConvert.u2tDate(op.getEndDate()));
		return this.getDevJdbcTemplate().executeForRetValue(sql, paramMap);
	}
	
	//lock
	public PlsqlRetValue lock(OnhandPermVO op) throws Exception{
		String sql ="Declare "
				+ "     l_p_id number; "
				+ "  begin "
				+ "  XYG_ALB2B_ONHAND_PERM_PKG.LOCK_ONHAND_PERM( "
				+ "  :1"
				+ " ,:2"
				+ " ,:3"
                + " ,:4"
                + " ,:5"
				+ " ,:"+PlsqlRetValue.RETCODE
				+ " ,:"+PlsqlRetValue.ERRBUF
				+ " ); "
				+ "end;";
		Map<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("1", op.getPId());
		paramMap.put("2", op.getUserId());
		paramMap.put("3", op.getOrganizationId());
		paramMap.put("4", TypeConvert.u2tDate(op.getStartDate()));
		paramMap.put("5", TypeConvert.u2tDate(op.getEndDate()));
		return this.getDevJdbcTemplate().executeForRetValue(sql, paramMap);
	}
	
	//update
	public PlsqlRetValue update(OnhandPermVO op) throws Exception{
		String sql ="Declare "
				+ "     l_p_id number; "
				+ "  begin "
				+ "  XYG_ALB2B_ONHAND_PERM_PKG.UPDATE_ONHAND_PERM( "
				+ "  :1"
				+ " ,:2"
				+ " ,:3"
                + " ,:4"
                + " ,:5"
				+ " ,:"+PlsqlRetValue.RETCODE
				+ " ,:"+PlsqlRetValue.ERRBUF
				+ " ); "
				+ "end;";
		Map<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("1", op.getPId());
		paramMap.put("2", op.getUserId());
		paramMap.put("3", op.getOrganizationId());
		paramMap.put("4", TypeConvert.u2tDate(op.getStartDate()));
		paramMap.put("5", TypeConvert.u2tDate(op.getEndDate()));
		return this.getDevJdbcTemplate().executeForRetValue(sql, paramMap);
	}
		
}
