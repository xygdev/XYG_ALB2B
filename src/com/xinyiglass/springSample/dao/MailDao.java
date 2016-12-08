package com.xinyiglass.springSample.dao;

import xygdev.commons.entity.PlsqlRetValue;
import xygdev.commons.entity.SqlResultSet;

public interface MailDao {
	public SqlResultSet findRecMailByIdForRS(Long sendid) throws Exception;
	
	public SqlResultSet findSendMailByIdForRS(Long sendid) throws Exception;
	
	public PlsqlRetValue deleteRecMail(Long recid) throws Exception;
	
	public PlsqlRetValue updateRecMail(Long recid) throws Exception;
	
	public PlsqlRetValue insertMail(Long sendUserId,String title,String content,String sendType,String recUser)throws Exception;

}
