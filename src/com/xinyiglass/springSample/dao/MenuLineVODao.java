package com.xinyiglass.springSample.dao;

import com.xinyiglass.springSample.entity.MenuLineVO;

import xygdev.commons.entity.PlsqlRetValue;
import xygdev.commons.entity.SqlResultSet;

public interface MenuLineVODao {
	
	public Long autoAddSequence(Long menuId) throws Exception;
	
	public SqlResultSet findMenuLineForJSON(Long menuId,Long menuSeq) throws Exception;

	public PlsqlRetValue insert(MenuLineVO m) throws Exception;
	
	public MenuLineVO findByMenuId(Long menuId,Long menuSeq) throws Exception;
	
	public PlsqlRetValue lock(MenuLineVO m) throws Exception;
	
	public PlsqlRetValue update(MenuLineVO m) throws Exception;
}
