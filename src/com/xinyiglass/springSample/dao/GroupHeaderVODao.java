package com.xinyiglass.springSample.dao;

import com.xinyiglass.springSample.entity.GroupHeaderVO;

import xygdev.commons.entity.PlsqlRetValue;
import xygdev.commons.entity.SqlResultSet;

public interface GroupHeaderVODao {
	public SqlResultSet findByIdForJSON(Long groupId) throws Exception;
	public PlsqlRetValue insert(GroupHeaderVO g) throws Exception;
	public GroupHeaderVO findByGroupId(Long groupId) throws Exception;
	public PlsqlRetValue lock(GroupHeaderVO g) throws Exception;
	public PlsqlRetValue update(GroupHeaderVO g) throws Exception;
}
