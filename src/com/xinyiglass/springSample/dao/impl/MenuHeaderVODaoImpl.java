package com.xinyiglass.springSample.dao.impl;

import java.util.HashMap;
import java.util.Map;

import xygdev.commons.entity.PlsqlRetValue;
import xygdev.commons.entity.SqlResultSet;
import xygdev.commons.springjdbc.DevJdbcDaoSupport;

import com.xinyiglass.springSample.dao.MenuHeaderVODao;
import com.xinyiglass.springSample.entity.MenuHeaderVO;
import com.xinyiglass.springSample.util.Constant;

public class MenuHeaderVODaoImpl extends DevJdbcDaoSupport implements MenuHeaderVODao{
	public void log(String log){
		if (Constant.DEBUG_MODE){
			System.out.println(log);
		}
	}
	
	public SqlResultSet findPersonalMenuById(Long menuId) throws Exception{
		String sql = "SELECT DECODE(LEVEL,1,'',MENU_CODE) PREV_CODE,SUB_MENU_NAME NAME,SUB_MENU_CODE CODE,ICON_CODE ICON,FUNCTION_NAME FUNC,FUNCTION_HREF HREF FROM (SELECT * FROM XYG_ALB2B_MENU_LINES_V WHERE ENABLED_FLAG = 'Y') WHERE 1=1 CONNECT BY PRIOR SUB_MENU_ID = MENU_ID START WITH MENU_ID=:1 ORDER BY LEVEL,MENU_CODE,MENU_SEQUENCE";
		Map<String,Object> paramMap=new  HashMap<String,Object>();
		paramMap.put("1", menuId);
		return this.getDevJdbcTemplate().queryForResultSet(sql, paramMap);
	}
	
	//insert
	public PlsqlRetValue insert(MenuHeaderVO m) throws Exception{
		String sql ="Declare "
				+ "     l_menu_id number; "
				+ "  begin "
				+ "  XYG_ALB2B_MENU_HEADERS_PKG.INSERT_MENU_HEADER( "
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
		paramMap.put("2", m.getMenuCode());
		paramMap.put("3", m.getMenuName());
		paramMap.put("4", m.getDescription());
		paramMap.put("5", m.getMenuIconId());
		return this.getDevJdbcTemplate().executeForRetValue(sql, paramMap);
	}
	
	//lock
	public PlsqlRetValue lock(MenuHeaderVO m) throws Exception{
		String sql ="Declare "
				+ "     l_menu_id number; "
				+ "  begin "
				+ "  XYG_ALB2B_MENU_HEADERS_PKG.LOCK_MENU_HEADER( "
				+ "  :1"
				+ " ,:2"
				+ " ,:3"
                + " ,:4"
                + " ,:5"
				+ " ,:"+PlsqlRetValue.RETCODE
				+ " ,:"+PlsqlRetValue.ERRBUF
				+ " ); "
				+ "end;";
		log("LOCK MENU ID:"+m.getMenuId());
		Map<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("1", m.getMenuId());
		paramMap.put("2", m.getMenuCode());
		paramMap.put("3", m.getMenuName());
		paramMap.put("4", m.getDescription());
		paramMap.put("5", m.getMenuIconId());
		return this.getDevJdbcTemplate().executeForRetValue(sql, paramMap);
	}
	
	//update
	public PlsqlRetValue update(MenuHeaderVO m) throws Exception{
		String sql ="Declare "
				+ "     l_menu_id number; "
				+ "  begin "
				+ "  XYG_ALB2B_MENU_HEADERS_PKG.UPDATE_MENU_HEADER( "
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
		paramMap.put("2", m.getMenuCode());
		paramMap.put("3", m.getMenuName());
		paramMap.put("4", m.getDescription());
		paramMap.put("5", m.getMenuIconId());
		return this.getDevJdbcTemplate().executeForRetValue(sql, paramMap);
	}
	
	//Find MenuHeaderVO By Id
	public MenuHeaderVO findByMenuId(Long menuId) throws Exception{
		Map<String,Object> paramMap=new  HashMap<String,Object>();
		String sql = "SELECT * FROM XYG_ALB2B_MENU_HEADERS_V WHERE MENU_ID = :1";
		paramMap.put("1", menuId);
		return this.getDevJdbcTemplate().queryForObject(sql, paramMap, new MenuHeaderVO());
	}
	
	public SqlResultSet findByIdForJSON(Long menuId) throws Exception{
		Map<String,Object> paramMap=new  HashMap<String,Object>();
		String sql = "SELECT * FROM XYG_ALB2B_MENU_HEADERS_V WHERE MENU_ID = :1";
		paramMap.put("1", menuId);
		return this.getDevJdbcTemplate().queryForResultSet(sql, paramMap);
	}
}
