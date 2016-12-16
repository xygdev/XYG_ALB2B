package com.xinyiglass.springSample.dao;

import xygdev.commons.entity.PlsqlRetValue;
import xygdev.commons.entity.SqlResultSet;

import com.xinyiglass.springSample.entity.PoLineVO;

public interface PoLineVODao {
	public Long autoAddSequence(Long poHeaderId) throws Exception;
	public SqlResultSet findByIdForJSON(Long poLineId) throws Exception;
	public PoLineVO findByPoLineId(Long poLineId) throws Exception;
	public PlsqlRetValue insert(PoLineVO pl,Long funcId) throws Exception;
	public PlsqlRetValue lock(PoLineVO pl) throws Exception;
	public PlsqlRetValue update(PoLineVO pl,String userType,Long funcId) throws Exception;
	public PlsqlRetValue delete(Long poLineId) throws Exception;
}
