package com.xinyiglass.springSample.dao.impl;

import java.util.HashMap;
import java.util.Map;

import xygdev.commons.entity.PlsqlRetValue;
import xygdev.commons.entity.SqlResultSet;
import xygdev.commons.springjdbc.DevJdbcDaoSupport;
import xygdev.commons.util.TypeConvert;

import com.xinyiglass.springSample.dao.FuncPermVODao;
import com.xinyiglass.springSample.entity.FuncPermVO;
import com.xinyiglass.springSample.util.Constant;

public class FuncPermVODaoImpl extends DevJdbcDaoSupport implements FuncPermVODao{
	public void log(String log){
		if (Constant.DEBUG_MODE){
			System.out.println(log);
		}
	}
	
	public FuncPermVO findByRespId(Long pId) throws Exception{
		Map<String,Object> paramMap=new  HashMap<String,Object>();
		String sql = "select * from XYG_ALB2B_FUNC_PERM_V WHERE P_ID = :1";
		paramMap.put("1", pId);
		return this.getDevJdbcTemplate().queryForObject(sql, paramMap, new FuncPermVO());
	}
	
	public SqlResultSet findByIdForJSON(Long pId) throws Exception{
		Map<String,Object> paramMap=new  HashMap<String,Object>();
		String sql = "select * from XYG_ALB2B_FUNC_PERM_V WHERE P_ID = :1";
		paramMap.put("1", pId);
		return this.getDevJdbcTemplate().queryForResultSet(sql, paramMap);
	}
	
	//insert
	public PlsqlRetValue insert(FuncPermVO fp) throws Exception{
		String sql ="Declare "
				+ "     l_p_id number; "
				+ "  begin "
				+ "  XYG_ALB2B_FUNC_PERM_PKG.INSERT_FUNC_PERM( "
				+ "  :1"
				+ " ,:2"
				+ " ,:3"
                + " ,:4"
                + " ,:5"
                + " ,:6"
                + " ,:7"
                + " ,:8"
                + " ,:9"
                + " ,:10"
				+ " ,:"+PlsqlRetValue.RETCODE
				+ " ,:"+PlsqlRetValue.ERRBUF
				+ " ); "
				+ "end;";
		Map<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("1", fp.getPId());
		paramMap.put("2", fp.getUserId());
		paramMap.put("3", fp.getFunctionId());
		paramMap.put("4", fp.getInsertFlag());
		paramMap.put("5", fp.getUpdateFlag());
		paramMap.put("6", fp.getApproveFlag());
		paramMap.put("7", fp.getFinalApproveFlag());
		paramMap.put("8", fp.getDownloadFlag());
		paramMap.put("9", TypeConvert.u2tDate(fp.getStartDate()));
		paramMap.put("10", TypeConvert.u2tDate(fp.getEndDate()));
		return this.getDevJdbcTemplate().executeForRetValue(sql, paramMap);
	}
	
	//update
	public PlsqlRetValue update(FuncPermVO fp) throws Exception{
		String sql ="Declare "
				+ "     l_p_id number; "
				+ "  begin "
				+ "  XYG_ALB2B_FUNC_PERM_PKG.UPDATE_FUNC_PERM( "
				+ "  :1"
				+ " ,:2"
				+ " ,:3"
                + " ,:4"
                + " ,:5"
                + " ,:6"
                + " ,:7"
                + " ,:8"
                + " ,:9"
                + " ,:10"
				+ " ,:"+PlsqlRetValue.RETCODE
				+ " ,:"+PlsqlRetValue.ERRBUF
				+ " ); "
				+ "end;";
		Map<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("1", fp.getPId());
		paramMap.put("2", fp.getUserId());
		paramMap.put("3", fp.getFunctionId());
		paramMap.put("4", fp.getInsertFlag());
		paramMap.put("5", fp.getUpdateFlag());
		paramMap.put("6", fp.getApproveFlag());
		paramMap.put("7", fp.getFinalApproveFlag());
		paramMap.put("8", fp.getDownloadFlag());
		paramMap.put("9", TypeConvert.u2tDate(fp.getStartDate()));
		paramMap.put("10", TypeConvert.u2tDate(fp.getEndDate()));
		return this.getDevJdbcTemplate().executeForRetValue(sql, paramMap);
	}	
}
