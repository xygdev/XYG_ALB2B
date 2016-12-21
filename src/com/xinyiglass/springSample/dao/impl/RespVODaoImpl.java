package com.xinyiglass.springSample.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import xygdev.commons.entity.PlsqlRetValue;
import xygdev.commons.entity.SqlResultSet;
import xygdev.commons.springjdbc.DevJdbcDaoSupport;
import xygdev.commons.util.TypeConvert;

import com.xinyiglass.springSample.dao.RespVODao;
import com.xinyiglass.springSample.entity.RespVO;
import com.xinyiglass.springSample.util.LogUtil;

public class RespVODaoImpl extends DevJdbcDaoSupport implements RespVODao{
	//insert
	public PlsqlRetValue insert(RespVO r) throws Exception{
		String sql ="Declare "
				+ "     l_resp_id number; "
				+ "  begin "
				+ "  XYG_ALB2B_RESP_PKG.INSERT_RESP( "
				+ "  :1"
				+ " ,:2"
				+ " ,:3"
                + " ,:4"
                + " ,:5"
                + " ,:6"
                + " ,:7"
				+ " ,:"+PlsqlRetValue.RETCODE
				+ " ,:"+PlsqlRetValue.ERRBUF
				+ " ); "
				+ "end;";
		Map<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("1", r.getRespId());
		paramMap.put("2", r.getRespCode());
		paramMap.put("3", r.getRespName());
		paramMap.put("4", r.getDescription());
		paramMap.put("5", r.getMenuId());
		paramMap.put("6", TypeConvert.u2tDate(r.getStartDate()));
		paramMap.put("7", TypeConvert.u2tDate(r.getEndDate()));
		return this.getDevJdbcTemplate().executeForRetValue(sql, paramMap);
	}
	
	//lock
	public PlsqlRetValue lock(RespVO r) throws Exception{
		String sql ="Declare "
				+ "     l_resp_id number; "
				+ "  begin "
				+ "  XYG_ALB2B_RESP_PKG.LOCK_RESP( "
				+ "  :1"
				+ " ,:2"
				+ " ,:3"
                + " ,:4"
                + " ,:5"
                + " ,:6"
                + " ,:7"
				+ " ,:"+PlsqlRetValue.RETCODE
				+ " ,:"+PlsqlRetValue.ERRBUF
				+ " ); "
				+ "end;";
		LogUtil.log("LOCK Resp ID:"+r.getRespId());
		Map<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("1", r.getRespId());
		paramMap.put("2", r.getRespCode());
		paramMap.put("3", r.getRespName());
		paramMap.put("4", r.getDescription());
		paramMap.put("5", r.getMenuId());
		paramMap.put("6", TypeConvert.u2tDate(r.getStartDate()));
		paramMap.put("7", TypeConvert.u2tDate(r.getEndDate()));
		return this.getDevJdbcTemplate().executeForRetValue(sql, paramMap);
	}
	
	//update
	public PlsqlRetValue update(RespVO r) throws Exception{
		String sql ="Declare "
				+ "     l_resp_id number; "
				+ "  begin "
				+ "  XYG_ALB2B_RESP_PKG.UPDATE_RESP( "
				+ "  :1"
				+ " ,:2"
				+ " ,:3"
                + " ,:4"
                + " ,:5"
                + " ,:6"
                + " ,:7"
				+ " ,:"+PlsqlRetValue.RETCODE
				+ " ,:"+PlsqlRetValue.ERRBUF
				+ " ); "
				+ "end;";
		Map<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("1", r.getRespId());
		paramMap.put("2", r.getRespCode());
		paramMap.put("3", r.getRespName());
		paramMap.put("4", r.getDescription());
		paramMap.put("5", r.getMenuId());
		paramMap.put("6", TypeConvert.u2tDate(r.getStartDate()));
		paramMap.put("7", TypeConvert.u2tDate(r.getEndDate()));
		return this.getDevJdbcTemplate().executeForRetValue(sql, paramMap);
	}
	
	public RespVO findByRespId(Long respId) throws Exception{
		Map<String,Object> paramMap=new  HashMap<String,Object>();
		String sql = "SELECT * FROM XYG_ALB2B_RESPONSIBILITY_V WHERE RESP_ID = :1";
		paramMap.put("1", respId);
		return this.getDevJdbcTemplate().queryForObject(sql, paramMap, new RespVO());
	}
	
	public SqlResultSet findByIdForJSON(Long respId) throws Exception{
		Map<String,Object> paramMap=new  HashMap<String,Object>();
		String sql = "SELECT * FROM XYG_ALB2B_RESPONSIBILITY_V WHERE RESP_ID = :1";
		paramMap.put("1", respId);
		return this.getDevJdbcTemplate().queryForResultSet(sql, paramMap);
	}
	
	public String findMenuId(Long respId) throws Exception{
		String sql = "SELECT MENU_ID FROM XYG_ALB2B_RESPONSIBILITY_V WHERE RESP_ID = :1";
		Map<String,Object> paramMap=new  HashMap<String,Object>();
		paramMap.put("1", respId);
		return this.getDevJdbcTemplate().queryForObjSingle(sql, paramMap).toString();
	}
	
	//测试专用
	@SuppressWarnings("resource")
	public static void main(String[] args){
	    ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
	    RespVODao rDao= (RespVODao)context.getBean("RespVODao");
		try{
			Long respId=1L;
			String menuId = rDao.findMenuId(respId);
			System.out.println("MENU_ID:"+menuId);
		} catch (Exception EmptyResultDataAccessException) {
			EmptyResultDataAccessException.printStackTrace();
			System.out.println("无返回数据");
		}
	}

}
