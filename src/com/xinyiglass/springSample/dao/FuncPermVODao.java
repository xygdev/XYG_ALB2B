package com.xinyiglass.springSample.dao;

import xygdev.commons.entity.PlsqlRetValue;
import xygdev.commons.entity.SqlResultSet;

import com.xinyiglass.springSample.entity.FuncPermVO;

public interface FuncPermVODao {
	public FuncPermVO findByRespId(Long pId) throws Exception;
	public SqlResultSet findByIdForJSON(Long pId) throws Exception;
	public SqlResultSet findByUserAndFuncForJSON(Long userId,Long funcId) throws Exception;
	public PlsqlRetValue insert(FuncPermVO fp) throws Exception;
	public PlsqlRetValue update(FuncPermVO fp) throws Exception;
}
