package com.xinyiglass.springSample.dao;

import xygdev.commons.entity.PlsqlRetValue;
import xygdev.commons.entity.SqlResultSet;

public interface CustGroupDao {
	/************************客户组分配页码方法************************/
	public SqlResultSet findForCust(Long orgId,Long custId) throws Exception;
	public PlsqlRetValue updateCustGroup(Long orgId,Long custAccountId,Long groupId) throws Exception;
	/************************客户组分配页码方法************************/
}
