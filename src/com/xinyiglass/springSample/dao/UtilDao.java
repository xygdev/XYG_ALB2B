package com.xinyiglass.springSample.dao;

import xygdev.commons.entity.PlsqlRetValue;

//公用dao处理。目前是添加环境变量的初始化的动作 2016.12.20
public interface UtilDao {
	public PlsqlRetValue alb2bInit(Long loginId) throws Exception;
	public Long getLoginId() throws Exception;
}
