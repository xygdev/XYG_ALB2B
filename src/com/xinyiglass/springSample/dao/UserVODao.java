package com.xinyiglass.springSample.dao;

import xygdev.commons.entity.PlsqlRetValue;
import xygdev.commons.entity.SqlResultSet;

import com.xinyiglass.springSample.entity.UserVO;

public interface UserVODao {
	
	public PlsqlRetValue insert(UserVO u) throws Exception;
	
	public PlsqlRetValue lock(UserVO u) throws Exception;
	
	public PlsqlRetValue update(UserVO u) throws Exception;
	
	public PlsqlRetValue updateImgUrl(String fileName,Long userId) throws Exception;
	
	public PlsqlRetValue updatePWD(Long userId,String oldPassword,String newPassword) throws Exception;
	
	public UserVO findByUserName(String userName) throws Exception;
	
	public UserVO findByUserId(Long userId) throws Exception;
	
	public SqlResultSet findByUserForRS(Long sendUserId,String userName) throws Exception;
	
	public SqlResultSet findByIdForJSON(Long userId) throws Exception;
	
	public String findOtherUsers(Long userId) throws Exception;
}
