package com.xinyiglass.springSample.dao.impl;

import java.util.HashMap;
import java.util.Map;

import xygdev.commons.entity.PlsqlRetValue;
import xygdev.commons.entity.SqlResultSet;
import xygdev.commons.springjdbc.DevJdbcDaoSupport;

import com.xinyiglass.springSample.dao.PoHeaderVODao;
import com.xinyiglass.springSample.entity.PoHeaderVO;

public class PoHeaderVODaoImpl extends DevJdbcDaoSupport implements PoHeaderVODao{

	public SqlResultSet findByIdForJSON(Long poHeaderId) throws Exception{
		Map<String,Object> paramMap=new  HashMap<String,Object>();
		String sql = "SELECT * FROM XYG_ALB2B_LG_PO_HEADERS_V WHERE PO_HEADER_ID = :1";
		paramMap.put("1", poHeaderId);
		return this.getDevJdbcTemplate().queryForResultSet(sql, paramMap);
	}
	
	public PoHeaderVO findByPoHeaderId(Long poHeaderId) throws Exception{
		Map<String,Object> paramMap=new  HashMap<String,Object>();
		String sql = "SELECT * FROM XYG_ALB2B_LG_PO_HEADERS_V WHERE PO_HEADER_ID = :1";
		paramMap.put("1", poHeaderId);
		return this.getDevJdbcTemplate().queryForObject(sql, paramMap, new PoHeaderVO());
	}
	
	//insert
	public PlsqlRetValue insert(PoHeaderVO ph,Long funcId) throws Exception{
		String sql ="Declare "
				+ "     l_po_header_id number; "
				+ "  begin "
				+ "  XYG_ALB2B_LG_PO_PKG.INSERT_LG_PO_HEADERS( "
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
                + " ,:11"
				+ " ,:"+PlsqlRetValue.RETCODE
				+ " ,:"+PlsqlRetValue.ERRBUF
				+ " ); "
				+ "end;";
		Map<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("1", ph.getPoHeaderId());
		paramMap.put("2", ph.getPoNumber());
		paramMap.put("3", ph.getCustomerContractNumber());
		paramMap.put("4", ph.getCurrCode());
		paramMap.put("5", ph.getCustomerId());
		paramMap.put("6", ph.getSalesOrgId());
		paramMap.put("7", ph.getShipFromOrgId());
		paramMap.put("8", ph.getStatus());
		paramMap.put("9", ph.getRemark());		
		paramMap.put("10", ph.getCreatedBy());
		paramMap.put("11", funcId);
		return this.getDevJdbcTemplate().executeForRetValue(sql, paramMap);
	}
	
	//lock
	public PlsqlRetValue lock(PoHeaderVO ph,Long userId,Long funcId) throws Exception{
		String sql ="Declare "
				+ "     l_po_header_id number; "
				+ "  begin "
				+ "  XYG_ALB2B_LG_PO_PKG.LOCK_LG_PO_HEADERS( "
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
		paramMap.put("1", ph.getPoHeaderId());
		paramMap.put("2", ph.getPoNumber());
		paramMap.put("3", ph.getCustomerContractNumber());
		paramMap.put("4", ph.getCurrCode());
		paramMap.put("5", ph.getCustomerId());
		paramMap.put("6", ph.getSalesOrgId());
		paramMap.put("7", ph.getStatus());
		paramMap.put("8", ph.getRemark());		
		paramMap.put("9", userId);
		paramMap.put("10", funcId);
		return this.getDevJdbcTemplate().executeForRetValue(sql, paramMap);
	}
	
	//update
	public PlsqlRetValue update(PoHeaderVO ph) throws Exception{
		String sql ="Declare "
				+ "     l_po_header_id number; "
				+ "  begin "
				+ "  XYG_ALB2B_LG_PO_PKG.UPDATE_LG_PO_HEADERS( "
				+ "  :1"
				+ " ,:2"
				+ " ,:3"
                + " ,:4"
				+ " ,:"+PlsqlRetValue.RETCODE
				+ " ,:"+PlsqlRetValue.ERRBUF
				+ " ); "
				+ "end;";
		Map<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("1", ph.getPoHeaderId());
		paramMap.put("2", ph.getPoNumber());
		paramMap.put("3", ph.getCustomerContractNumber());
		paramMap.put("4", ph.getRemark());		
		return this.getDevJdbcTemplate().executeForRetValue(sql, paramMap);
	}
	
	//delete
	public PlsqlRetValue delete(Long poHeaderId) throws Exception{
		String sql ="Declare "
				+ "     l_po_header_id number; "
				+ "  begin "
				+ "  XYG_ALB2B_LG_PO_PKG.DELETE_LG_PO_HEADERS( "
				+ "  :1"
				+ " ,:"+PlsqlRetValue.RETCODE
				+ " ,:"+PlsqlRetValue.ERRBUF
				+ " ); "
				+ "end;";
		Map<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("1", poHeaderId);	
		return this.getDevJdbcTemplate().executeForRetValue(sql, paramMap);
	}
	
	//Status Change
	public PlsqlRetValue changeStatus(Long poHeaderId,String status,Long userId) throws Exception{
		String sql ="Declare "
				+ "     l_po_header_id number; "
				+ "  begin "
				+ "  XYG_ALB2B_LG_PO_PKG.UPDATE_STATUS( "
				+ "  :1"
				+ " ,:2"
				+ " ,:3"
                + " ,:4"
				+ " ,:"+PlsqlRetValue.RETCODE
				+ " ,:"+PlsqlRetValue.ERRBUF
				+ " ); "
				+ "end;";
		Map<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("1", poHeaderId);
		paramMap.put("2", status);
		paramMap.put("3", userId);
		paramMap.put("4", "N");		
		return this.getDevJdbcTemplate().executeForRetValue(sql, paramMap);
	}
}
