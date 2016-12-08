package com.xinyiglass.springSample.dao.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.xinyiglass.springSample.dao.MenuLineVODao;
import com.xinyiglass.springSample.entity.MenuLineVO;
import com.xinyiglass.springSample.util.Constant;

import xygdev.commons.entity.PlsqlRetValue;
import xygdev.commons.entity.SqlResultSet;
import xygdev.commons.springjdbc.DevJdbcDaoSupport;

public class MenuLineVODaoImpl extends DevJdbcDaoSupport implements MenuLineVODao{
	public void log(String log){
		if (Constant.DEBUG_MODE){
			System.out.println(log);
		}
	}
	
	//自动序号递增，每次递增量为addCount
	public Long autoAddSequence(Long menuId) throws Exception{
		String addCount = "10";
		String sql="SELECT MAX(MENU_SEQUENCE)+"+addCount+" MENU_SEQUENCE FROM XYG_ALB2B_MENU_LINES WHERE MENU_ID = :1";
		Map<String,Object> paramMap=new  HashMap<String,Object>();
		paramMap.put("1", menuId);
		Long seq = null;
		try{
			BigDecimal seqBD = (BigDecimal) this.getDevJdbcTemplate().queryForObjSingle(sql, paramMap);
		    seq=seqBD.longValue();
		}catch(NullPointerException NullPointerException) {
			seq = 10L;
		}		
		return seq;
	}
	
	//通过MENU_ID和MENU_SEQUENCE获取 菜单行明细
	public SqlResultSet findMenuLineForJSON(Long menuId,Long menuSeq) throws Exception{
		Map<String,Object> paramMap=new  HashMap<String,Object>();
		String sql = "SELECT * FROM XYG_ALB2B_MENU_LINES_V WHERE MENU_ID = :1 AND MENU_SEQUENCE = :2";
		paramMap.put("1", menuId);
		paramMap.put("2", menuSeq);
		return this.getDevJdbcTemplate().queryForResultSet(sql, paramMap);
	}
	
	//Find MenuLineVO By Id
	public MenuLineVO findByMenuId(Long menuId,Long menuSeq) throws Exception{
		Map<String,Object> paramMap=new  HashMap<String,Object>();
		String sql = "SELECT * FROM XYG_ALB2B_MENU_LINES_V WHERE MENU_ID = :1 AND MENU_SEQUENCE = :2";
		paramMap.put("1", menuId);
		paramMap.put("2", menuSeq);
		return this.getDevJdbcTemplate().queryForObject(sql, paramMap, new MenuLineVO());
	}
	
	//insert
	public PlsqlRetValue insert(MenuLineVO m) throws Exception{
		String sql ="Declare "
				+ "     l_menu_id number; "
				+ "  begin "
				+ "  XYG_ALB2B_MENU_LINES_PKG.INSERT_MENU_LINE( "
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
		paramMap.put("1", m.getMenuId());
		paramMap.put("2", m.getMenuSequence());
		paramMap.put("3", m.getSubMenuId());
		paramMap.put("4", m.getFunctionId());
		paramMap.put("5", m.getEnabledFlag());
		return this.getDevJdbcTemplate().executeForRetValue(sql, paramMap);
	}
	
	//lock
	public PlsqlRetValue lock(MenuLineVO m) throws Exception{
		String sql ="Declare "
				+ "     l_menu_id number; "
				+ "  begin "
				+ "  XYG_ALB2B_MENU_LINES_PKG.LOCK_MENU_LINE( "
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
		paramMap.put("1", m.getMenuId());
		paramMap.put("2", m.getMenuSequence());
		paramMap.put("3", m.getSubMenuId());
		paramMap.put("4", m.getFunctionId());
		paramMap.put("5", m.getEnabledFlag());
		return this.getDevJdbcTemplate().executeForRetValue(sql, paramMap);
	}
	
	//update
	public PlsqlRetValue update(MenuLineVO m) throws Exception{
		String sql ="Declare "
				+ "     l_menu_id number; "
				+ "  begin "
				+ "  XYG_ALB2B_MENU_LINES_PKG.UPDATE_MENU_LINE( "
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
		paramMap.put("1", m.getMenuId());
		paramMap.put("2", m.getMenuSequence());
		paramMap.put("3", m.getSubMenuId());
		paramMap.put("4", m.getFunctionId());
		paramMap.put("5", m.getEnabledFlag());
		return this.getDevJdbcTemplate().executeForRetValue(sql, paramMap);
	}
}
