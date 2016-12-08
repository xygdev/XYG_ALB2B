package com.xinyiglass.springSample.dao;

import com.xinyiglass.springSample.entity.OnhandSetVO;

import xygdev.commons.entity.PlsqlRetValue;
import xygdev.commons.entity.SqlResultSet;

public interface OnhandSetVODao {
	public SqlResultSet findByIdForJSON(Long organizationId) throws Exception;
	public OnhandSetVO findById(Long organizationId) throws Exception;
	public PlsqlRetValue insert(OnhandSetVO os) throws Exception;
	public PlsqlRetValue lock(OnhandSetVO os) throws Exception;
	public PlsqlRetValue update(OnhandSetVO os) throws Exception;
}
