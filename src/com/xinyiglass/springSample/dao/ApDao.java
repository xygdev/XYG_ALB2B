package com.xinyiglass.springSample.dao;

import xygdev.commons.entity.PlsqlRetValue;

public interface ApDao {
	public PlsqlRetValue apQuery(Long orgId,Long custId,String apDate) throws Exception;
}
