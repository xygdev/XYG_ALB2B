package com.xinyiglass.springSample.dao;

import xygdev.commons.entity.PlsqlRetValue;
import xygdev.commons.entity.SqlResultSet;

import com.xinyiglass.springSample.entity.PoHeaderVO;

public interface PoHeaderVODao {
	public SqlResultSet findByIdForJSON(Long poHeaderId) throws Exception;
	public PoHeaderVO findByPoHeaderId(Long poHeaderId) throws Exception;
	public PlsqlRetValue insert(PoHeaderVO ph,Long funcId) throws Exception;
	public PlsqlRetValue lock(PoHeaderVO ph,Long funcId) throws Exception;
	public PlsqlRetValue update(PoHeaderVO ph) throws Exception;
	public PlsqlRetValue delete(Long poHeaderId) throws Exception;
	public PlsqlRetValue changeStatus(Long poHeaderId,String status,Long userId) throws Exception;
}
