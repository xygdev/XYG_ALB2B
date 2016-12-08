package com.xinyiglass.springSample.dao;

import com.xinyiglass.springSample.entity.MenuHeaderVO;

import xygdev.commons.entity.PlsqlRetValue;
import xygdev.commons.entity.SqlResultSet;

public interface MenuHeaderVODao {
    
	public SqlResultSet findPersonalMenuById(Long menuId) throws Exception;
	
	public PlsqlRetValue insert(MenuHeaderVO m) throws Exception;
	
	public PlsqlRetValue lock(MenuHeaderVO m) throws Exception;
	
	public PlsqlRetValue update(MenuHeaderVO m) throws Exception;
	
	public MenuHeaderVO findByMenuId(Long menuId) throws Exception;
	
	public SqlResultSet findByIdForJSON(Long menuId) throws Exception;
}
