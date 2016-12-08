package com.xinyiglass.springSample.dao.impl;

import java.util.HashMap;
import java.util.Map;

import xygdev.commons.entity.PlsqlRetValue;
import xygdev.commons.entity.SqlResultSet;
import xygdev.commons.springjdbc.DevJdbcDaoSupport;

import com.xinyiglass.springSample.dao.FuncVODao;
import com.xinyiglass.springSample.entity.FuncVO;
import com.xinyiglass.springSample.util.Constant;

public class FuncVODaoImpl extends DevJdbcDaoSupport implements FuncVODao{
	public void log(String log){
		if (Constant.DEBUG_MODE){
			System.out.println(log);
		}
	}
	
	//insert
	public PlsqlRetValue insert(FuncVO f) throws Exception{
		String sql ="Declare "
				+ "     l_func_id number; "
				+ "  begin "
				+ "  XYG_ALB2B_FUNCTIONS_PKG.INSERT_FUNCTION( "
				+ "  :1"
				+ " ,:2"
				+ " ,:3"
                + " ,:4"
                + " ,:5"
                + " ,:6"
				+ " ,:"+PlsqlRetValue.RETCODE
				+ " ,:"+PlsqlRetValue.ERRBUF
				+ " ); "
				+ "end;";
		Map<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("1", f.getFunctionId());
		paramMap.put("2", f.getFunctionCode());
		paramMap.put("3", f.getFunctionName());
		paramMap.put("4", f.getFunctionHref());
		paramMap.put("5", f.getIconId());
		paramMap.put("6", f.getDescription());
		return this.getDevJdbcTemplate().executeForRetValue(sql, paramMap);
	}
	
	//lock
	public PlsqlRetValue lock(FuncVO f) throws Exception{
		String sql ="Declare "
				+ "     l_func_id number; "
				+ "  begin "
				+ "  XYG_ALB2B_FUNCTIONS_PKG.LOCK_FUNCTION( "
				+ "  :1"
				+ " ,:2"
				+ " ,:3"
                + " ,:4"
                + " ,:5"
                + " ,:6"
				+ " ,:"+PlsqlRetValue.RETCODE
				+ " ,:"+PlsqlRetValue.ERRBUF
				+ " ); "
				+ "end;";
		log("LOCK Func ID:"+f.getFunctionId());
		Map<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("1", f.getFunctionId());
		paramMap.put("2", f.getFunctionCode());
		paramMap.put("3", f.getFunctionName());
		paramMap.put("4", f.getFunctionHref());
		paramMap.put("5", f.getIconId());
		paramMap.put("6", f.getDescription());
		return this.getDevJdbcTemplate().executeForRetValue(sql, paramMap);
	}
	
	//update
	public PlsqlRetValue update(FuncVO f) throws Exception{
		String sql ="Declare "
				+ "     l_func_id number; "
				+ "  begin "
				+ "  XYG_ALB2B_FUNCTIONS_PKG.UPDATE_FUNCTION( "
				+ "  :1"
				+ " ,:2"
				+ " ,:3"
                + " ,:4"
                + " ,:5"
                + " ,:6"
				+ " ,:"+PlsqlRetValue.RETCODE
				+ " ,:"+PlsqlRetValue.ERRBUF
				+ " ); "
				+ "end;";
		Map<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("1", f.getFunctionId());
		paramMap.put("2", f.getFunctionCode());
		paramMap.put("3", f.getFunctionName());
		paramMap.put("4", f.getFunctionHref());
		paramMap.put("5", f.getIconId());
		paramMap.put("6", f.getDescription());
		return this.getDevJdbcTemplate().executeForRetValue(sql, paramMap);
	}	
	
	public SqlResultSet findByIdForJSON(Long funcId) throws Exception{
		Map<String,Object> paramMap=new  HashMap<String,Object>();
		String sql = "SELECT * FROM XYG_ALB2B_FUNCTIONS_V WHERE FUNCTION_ID = :1";
		paramMap.put("1", funcId);
		return this.getDevJdbcTemplate().queryForResultSet(sql, paramMap);
	}
	
	public FuncVO findByFuncId(Long funcId) throws Exception{
		Map<String,Object> paramMap=new  HashMap<String,Object>();
		String sql = "SELECT * FROM XYG_ALB2B_FUNCTIONS_V WHERE FUNCTION_ID = :1";
		paramMap.put("1", funcId);
		return this.getDevJdbcTemplate().queryForObject(sql, paramMap, new FuncVO());
	}
}
