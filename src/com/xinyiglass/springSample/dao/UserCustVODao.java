package com.xinyiglass.springSample.dao;

import xygdev.commons.entity.PlsqlRetValue;
import xygdev.commons.entity.SqlResultSet;

import com.xinyiglass.springSample.entity.UserCustVO;

public interface UserCustVODao {
	public PlsqlRetValue insert(UserCustVO u) throws Exception;
	public SqlResultSet findByIdForJSON(Long userCustId) throws Exception;
	public PlsqlRetValue update(UserCustVO u) throws Exception;
}
