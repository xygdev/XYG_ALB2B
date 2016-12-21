package com.xinyiglass.springSample.dao;

import xygdev.commons.entity.PlsqlRetValue;

public interface LoginDao {
	public PlsqlRetValue handleLogin(String lang,String userName,String password,String ipAddress) throws Exception;
	public PlsqlRetValue logout(Long loginId) throws Exception;
}
