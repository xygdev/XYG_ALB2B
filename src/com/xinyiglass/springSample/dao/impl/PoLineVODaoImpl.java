package com.xinyiglass.springSample.dao.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import xygdev.commons.entity.PlsqlRetValue;
import xygdev.commons.entity.SqlResultSet;
import xygdev.commons.springjdbc.DevJdbcDaoSupport;

import com.xinyiglass.springSample.dao.PoLineVODao;
import com.xinyiglass.springSample.entity.PoLineVO;

public class PoLineVODaoImpl extends DevJdbcDaoSupport implements PoLineVODao{
	
	//自动序号递增，每次递增量为addCount
	public Long autoAddSequence(Long poHeaderId) throws Exception{
		String addCount = "1";
		String sql="SELECT MAX(LINE_NUM)+"+addCount+" LINE_NUM FROM XYG_ALB2B_LG_PO_LINES WHERE PO_HEADER_ID = :1";
		Map<String,Object> paramMap=new  HashMap<String,Object>();
		paramMap.put("1", poHeaderId);
		Long seq = null;
		try{
			BigDecimal seqBD = (BigDecimal) this.getDevJdbcTemplate().queryForObjSingle(sql, paramMap);
		    seq=seqBD.longValue();
		}catch(NullPointerException NullPointerException) {
			seq = 1L;
		}		
		return seq;
	}
	
	public SqlResultSet findByIdForJSON(Long poLineId) throws Exception{
		Map<String,Object> paramMap=new  HashMap<String,Object>();
		String sql = "SELECT * FROM XYG_ALB2B_LG_PO_LINES_V WHERE PO_LINE_ID = :1";
		paramMap.put("1", poLineId);
		return this.getDevJdbcTemplate().queryForResultSet(sql, paramMap);
	}
	
	public PoLineVO findByPoLineId(Long poLineId) throws Exception{
		Map<String,Object> paramMap=new  HashMap<String,Object>();
		String sql = "SELECT * FROM XYG_ALB2B_LG_PO_LINES_V WHERE PO_LINE_ID = :1";
		paramMap.put("1", poLineId);
		return this.getDevJdbcTemplate().queryForObject(sql, paramMap, new PoLineVO());
	}
	
	//insert
	public PlsqlRetValue insert(PoLineVO pl,Long funcId) throws Exception{
		String sql ="Declare "
				+ "     l_po_line_id number; "
				+ "  begin "
				+ "  XYG_ALB2B_LG_PO_PKG.INSERT_LG_PO_LINES( "
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
                + " ,:12"
                + " ,:13"
                + " ,:14"
				+ " ,:"+PlsqlRetValue.RETCODE
				+ " ,:"+PlsqlRetValue.ERRBUF
				+ " ); "
				+ "end;";
		Map<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("1", pl.getPoLineId());
		paramMap.put("2", pl.getPoHeaderId());
		paramMap.put("3", pl.getShipFromOrgId());
		paramMap.put("4", pl.getLineNum());
		paramMap.put("5", pl.getThickness());
		paramMap.put("6", pl.getCoatingType());
		paramMap.put("7", pl.getInventoryItemId());
		paramMap.put("8", pl.getWidth());
		paramMap.put("9", pl.getHeight());		
		paramMap.put("10", pl.getPieQuantity());
		paramMap.put("11", pl.getStatus());
		paramMap.put("12", pl.getRemark());
		paramMap.put("13", pl.getCreatedBy());
		paramMap.put("14", funcId);	
		return this.getDevJdbcTemplate().executeForRetValue(sql, paramMap);
	}
	
	//lock
	public PlsqlRetValue lock(PoLineVO pl) throws Exception{
		String sql ="Declare "
				+ "     l_po_line_id number; "
				+ "  begin "
				+ "  XYG_ALB2B_LG_PO_PKG.LOCK_LG_PO_LINES( "
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
                + " ,:12"
                + " ,:13"
				+ " ,:"+PlsqlRetValue.RETCODE
				+ " ,:"+PlsqlRetValue.ERRBUF
				+ " ); "
				+ "end;";
		Map<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("1", pl.getPoLineId());
		paramMap.put("2", pl.getPoHeaderId());
		paramMap.put("3", pl.getShipFromOrgId());
		paramMap.put("4", pl.getLineNum());
		paramMap.put("5", pl.getThickness());
		paramMap.put("6", pl.getCoatingType());
		paramMap.put("7", pl.getInventoryItemId());
		paramMap.put("8", pl.getWidth());
		paramMap.put("9", pl.getHeight());		
		paramMap.put("10", pl.getPieQuantity());
		paramMap.put("11", pl.getSqmUnitPrice());
		paramMap.put("12", pl.getStatus());
		paramMap.put("13", pl.getRemark());
		return this.getDevJdbcTemplate().executeForRetValue(sql, paramMap);
	}
	
	//update
	public PlsqlRetValue update(PoLineVO pl,String userType,Long funcId) throws Exception{
		String sql ="Declare "
				+ "     l_po_line_id number; "
				+ "  begin "
				+ "  XYG_ALB2B_LG_PO_PKG.UPDATE_LG_PO_LINES( "
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
                + " ,:12"
                + " ,:13"
                + " ,:14"
                + " ,:15"
                + " ,:16"
				+ " ,:"+PlsqlRetValue.RETCODE
				+ " ,:"+PlsqlRetValue.ERRBUF
				+ " ); "
				+ "end;";
		Map<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("1", pl.getPoLineId());
		paramMap.put("2", pl.getPoHeaderId());
		paramMap.put("3", pl.getShipFromOrgId());
		paramMap.put("4", pl.getLineNum());
		paramMap.put("5", pl.getThickness());
		paramMap.put("6", pl.getCoatingType());
		paramMap.put("7", pl.getInventoryItemId());
		paramMap.put("8", pl.getWidth());
		paramMap.put("9", pl.getHeight());		
		paramMap.put("10", pl.getPieQuantity());
		paramMap.put("11", pl.getSqmUnitPrice());
		paramMap.put("12", pl.getStatus());
		paramMap.put("13", pl.getRemark());
		paramMap.put("14", userType);
		paramMap.put("15", pl.getLastUpdatedBy());
		paramMap.put("16", funcId);
		return this.getDevJdbcTemplate().executeForRetValue(sql, paramMap);
	}
	
	//delete
	public PlsqlRetValue delete(Long poLineId) throws Exception{
		String sql ="Declare "
				+ "     l_po_line_id number; "
				+ "  begin "
				+ "  XYG_ALB2B_LG_PO_PKG.DELETE_LG_PO_LINES( "
				+ "  :1"
				+ " ,:"+PlsqlRetValue.RETCODE
				+ " ,:"+PlsqlRetValue.ERRBUF
				+ " ); "
				+ "end;";
		Map<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("1", poLineId);	
		return this.getDevJdbcTemplate().executeForRetValue(sql, paramMap);
	}
}
