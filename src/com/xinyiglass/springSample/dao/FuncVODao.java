package com.xinyiglass.springSample.dao;

import com.xinyiglass.springSample.entity.FuncVO;

import xygdev.commons.entity.PlsqlRetValue;
import xygdev.commons.entity.SqlResultSet;

public interface FuncVODao {
	public SqlResultSet findByIdForJSON(Long funcId) throws Exception;
	
	public FuncVO findByFuncId(Long funcId) throws Exception;
	
	public PlsqlRetValue insert(FuncVO f) throws Exception;
	
	public PlsqlRetValue lock(FuncVO f) throws Exception;
	
	public PlsqlRetValue update(FuncVO f) throws Exception;
}
