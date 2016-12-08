package com.xinyiglass.springSample.dao;

import com.xinyiglass.springSample.entity.RespVO;

import xygdev.commons.entity.PlsqlRetValue;
import xygdev.commons.entity.SqlResultSet;

public interface RespVODao {
    
	public SqlResultSet findByIdForJSON(Long respId) throws Exception;
	
	public PlsqlRetValue insert(RespVO r) throws Exception;
	
	public PlsqlRetValue lock(RespVO r) throws Exception;
	
	public PlsqlRetValue update(RespVO r) throws Exception;
	
	public RespVO findByRespId(Long respId) throws Exception;
	
	public String findMenuId(Long respId) throws Exception;
}
