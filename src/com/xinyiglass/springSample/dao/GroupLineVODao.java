package com.xinyiglass.springSample.dao;

import com.xinyiglass.springSample.entity.GroupLineVO;

import xygdev.commons.entity.PlsqlRetValue;
import xygdev.commons.entity.SqlResultSet;

public interface GroupLineVODao {
	public Long autoAddSequence(Long groupId) throws Exception;
	public SqlResultSet findGroupLineForJSON(Long groupId,Long groupSeq) throws Exception;
	public GroupLineVO findByGroupId(Long groupId,Long groupSeq) throws Exception;
	public PlsqlRetValue insert(GroupLineVO g) throws Exception;
	public PlsqlRetValue lock(GroupLineVO g) throws Exception;
	public PlsqlRetValue update(GroupLineVO g) throws Exception;
}
