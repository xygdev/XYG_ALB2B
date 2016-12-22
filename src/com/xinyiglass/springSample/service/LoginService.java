package com.xinyiglass.springSample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import xygdev.commons.entity.PlsqlRetValue;

import com.xinyiglass.springSample.dao.LoginDao;
import com.xinyiglass.springSample.util.MD5Util;

@Service
@Transactional(rollbackFor=Exception.class)
public class LoginService {
    @Autowired
    LoginDao loginDao;

	private Long loginId;
	
	public Long getLoginId() {
		return loginId;
	}
	
	public void setLoginId(Long loginId) {
		this.loginId = loginId;
	}
    
    @Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
    public PlsqlRetValue handleLogin(String password,String userName,String lang,String ipAddress) throws Exception{ 
    	password = MD5Util.string2MD5(password);
    	PlsqlRetValue ret=loginDao.handleLogin(lang, userName, password,ipAddress);
		return ret;    	
    }
    
    @Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
    public PlsqlRetValue logout(Long loginId) throws Exception{ 
    	PlsqlRetValue ret=loginDao.logout(loginId);
    	return ret;
    }
    
}
