package com.xinyiglass.springSample.dao.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import xygdev.commons.entity.PlsqlRetValue;
import xygdev.commons.entity.SqlResultSet;
import xygdev.commons.springjdbc.DevJdbcDaoSupport;

import com.xinyiglass.springSample.dao.GroupLineVODao;
import com.xinyiglass.springSample.entity.GroupLineVO;

public class GroupLineVODaoImpl extends DevJdbcDaoSupport implements GroupLineVODao{

	//自动序号递增，每次递增量为addCount
	public Long autoAddSequence(Long groupId) throws Exception{
		String addCount = "10";
		String sql="SELECT MAX(GROUP_SEQUENCE)+"+addCount+" GROUP_SEQUENCE FROM XYG_ALB2B_GROUP_LINES WHERE GROUP_ID = :1";
		Map<String,Object> paramMap=new  HashMap<String,Object>();
		paramMap.put("1", groupId);
		Long seq = null;
		try{
			BigDecimal seqBD = (BigDecimal) this.getDevJdbcTemplate().queryForObjSingle(sql, paramMap);
		    seq=seqBD.longValue();
		}catch(NullPointerException NullPointerException) {
			seq = 10L;
		}		
		return seq;
	}	
	
	//通过GROUP_ID和GROUP_SEQUENCE获取 菜单行明细
	public SqlResultSet findGroupLineForJSON(Long groupId,Long groupSeq) throws Exception{
		Map<String,Object> paramMap=new  HashMap<String,Object>();
		String sql = "SELECT * FROM XYG_ALB2B_GROUP_LINES_V WHERE GROUP_ID = :1 AND GROUP_SEQUENCE = :2";
		paramMap.put("1", groupId);
		paramMap.put("2", groupSeq);
		return this.getDevJdbcTemplate().queryForResultSet(sql, paramMap);
	}	
		
    //Find GroupLineVO By Id
	public GroupLineVO findByGroupId(Long groupId,Long groupSeq) throws Exception{
		Map<String,Object> paramMap=new  HashMap<String,Object>();
		String sql = "SELECT * FROM XYG_ALB2B_GROUP_LINES_V WHERE GROUP_ID = :1 AND GROUP_SEQUENCE = :2";
		paramMap.put("1", groupId);
		paramMap.put("2", groupSeq);
		return this.getDevJdbcTemplate().queryForObject(sql, paramMap, new GroupLineVO());
	}
	
	//insert
	public PlsqlRetValue insert(GroupLineVO g) throws Exception{
		String sql ="Declare "
				+ "     l_group_id number; "
				+ "  begin "
				+ "  XYG_ALB2B_GROUP_LINES_PKG.INSERT_GROUP_LINE( "
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
		paramMap.put("2", g.getGroupSequence());
		paramMap.put("3", g.getSubGroupId());
		paramMap.put("4", g.getEnabledFlag());
		return this.getDevJdbcTemplate().executeForRetValue(sql, paramMap);
	}
	
	//lock
	public PlsqlRetValue lock(GroupLineVO g) throws Exception{
		String sql ="Declare "
				+ "     l_group_id number; "
				+ "  begin "
				+ "  XYG_ALB2B_GROUP_LINES_PKG.LOCK_GROUP_LINE( "
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
		paramMap.put("2", g.getGroupSequence());
		paramMap.put("3", g.getSubGroupId());
		paramMap.put("4", g.getEnabledFlag());
		return this.getDevJdbcTemplate().executeForRetValue(sql, paramMap);
	}
	
	//update
	public PlsqlRetValue update(GroupLineVO g) throws Exception{
		String sql ="Declare "
				+ "     l_group_id number; "
				+ "  begin "
				+ "  XYG_ALB2B_GROUP_LINES_PKG.UPDATE_GROUP_LINE( "
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
		paramMap.put("2", g.getGroupSequence());
		paramMap.put("3", g.getSubGroupId());
		paramMap.put("4", g.getEnabledFlag());
		return this.getDevJdbcTemplate().executeForRetValue(sql, paramMap);
	}
}
