package com.xinyiglass.springSample.dao.impl;

import java.util.HashMap;
import java.util.Map;

import xygdev.commons.entity.PlsqlRetValue;
import xygdev.commons.entity.SqlResultSet;
import xygdev.commons.springjdbc.DevJdbcDaoSupport;

import com.xinyiglass.springSample.dao.GroupHeaderVODao;
import com.xinyiglass.springSample.entity.GroupHeaderVO;

public class GroupHeaderVODaoImpl extends DevJdbcDaoSupport implements GroupHeaderVODao{

	public SqlResultSet findByIdForJSON(Long groupId) throws Exception{
		Map<String,Object> paramMap=new  HashMap<String,Object>();
		String sql = "SELECT * FROM XYG_ALB2B_GROUP_HEADERS_V WHERE GROUP_ID = :1";
		paramMap.put("1", groupId);
		return this.getDevJdbcTemplate().queryForResultSet(sql, paramMap);
	}
	
	public GroupHeaderVO findByGroupId(Long groupId) throws Exception{
		Map<String,Object> paramMap=new  HashMap<String,Object>();
		String sql = "SELECT * FROM XYG_ALB2B_GROUP_HEADERS_V WHERE GROUP_ID = :1";
		paramMap.put("1", groupId);
		return this.getDevJdbcTemplate().queryForObject(sql, paramMap, new GroupHeaderVO());
	}
	
	//insert
	public PlsqlRetValue insert(GroupHeaderVO g) throws Exception{
		String sql ="Declare "
				+ "     l_group_id number; "
				+ "  begin "
				+ "  XYG_ALB2B_GROUP_HEADERS_PKG.INSERT_GROUP_HEADER( "
				+ "  :1"
				+ " ,:2"
				+ " ,:3"
                + " ,:4"
				+ " ,:"+PlsqlRetValue.RETCODE
				+ " ,:"+PlsqlRetValue.ERRBUF
				+ " ); "
				+ "end;";
		Map<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("1", g.getGroupId());
		paramMap.put("2", g.getGroupCode());
		paramMap.put("3", g.getGroupName());
		paramMap.put("4", g.getDescription());
		return this.getDevJdbcTemplate().executeForRetValue(sql, paramMap);
	}
	
	//lock
	public PlsqlRetValue lock(GroupHeaderVO g) throws Exception{
		String sql ="Declare "
				+ "     l_group_id number; "
				+ "  begin "
				+ "  XYG_ALB2B_GROUP_HEADERS_PKG.LOCK_GROUP_HEADER( "
				+ "  :1"
				+ " ,:2"
				+ " ,:3"
                + " ,:4"
				+ " ,:"+PlsqlRetValue.RETCODE
				+ " ,:"+PlsqlRetValue.ERRBUF
				+ " ); "
				+ "end;";
		Map<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("1", g.getGroupId());
		paramMap.put("2", g.getGroupCode());
		paramMap.put("3", g.getGroupName());
		paramMap.put("4", g.getDescription());
		return this.getDevJdbcTemplate().executeForRetValue(sql, paramMap);
	}
	
	//update
	public PlsqlRetValue update(GroupHeaderVO g) throws Exception{
		String sql ="Declare "
				+ "     l_group_id number; "
				+ "  begin "
				+ "  XYG_ALB2B_GROUP_HEADERS_PKG.UPDATE_GROUP_HEADER( "
				+ "  :1"
				+ " ,:2"
				+ " ,:3"
                + " ,:4"
				+ " ,:"+PlsqlRetValue.RETCODE
				+ " ,:"+PlsqlRetValue.ERRBUF
				+ " ); "
				+ "end;";
		Map<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("1", g.getGroupId());
		paramMap.put("2", g.getGroupCode());
		paramMap.put("3", g.getGroupName());
		paramMap.put("4", g.getDescription());
		return this.getDevJdbcTemplate().executeForRetValue(sql, paramMap);
	}
}
