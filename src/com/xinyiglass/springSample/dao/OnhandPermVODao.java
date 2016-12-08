package com.xinyiglass.springSample.dao;

import com.xinyiglass.springSample.entity.OnhandPermVO;

import xygdev.commons.entity.PlsqlRetValue;
import xygdev.commons.entity.SqlResultSet;

public interface OnhandPermVODao {
	public SqlResultSet findByIdForJSON(Long pId) throws Exception;
	public OnhandPermVO findByPId(Long pId) throws Exception;
	public PlsqlRetValue insert(OnhandPermVO op) throws Exception;
	public PlsqlRetValue lock(OnhandPermVO op) throws Exception;
	public PlsqlRetValue update(OnhandPermVO op) throws Exception;
}
