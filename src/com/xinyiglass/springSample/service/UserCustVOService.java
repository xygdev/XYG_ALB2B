package com.xinyiglass.springSample.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xinyiglass.springSample.dao.UserCustVODao;
import com.xinyiglass.springSample.entity.UserCustVO;

import xygdev.commons.entity.PlsqlRetValue;
import xygdev.commons.page.PagePub;
import xygdev.commons.springjdbc.DevJdbcSubProcess;

@Service
@Transactional(rollbackFor=Exception.class)//指定checked的异常Exception也要回滚！
public class UserCustVOService {
	@Autowired
	PagePub pagePub;
	@Autowired
	UserCustVODao ucDao;

	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public String findForPage(int pageSize,int pageNo,boolean goLastPage,Map<String,Object> conditionMap,Long loginId) throws Exception{
		String sql="select * from XYG_ALB2B_USER_CUSTOMER_V WHERE USER_ID = :1 order by "+conditionMap.get("orderby");
		Map<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("1", conditionMap.get("userId"));
		return pagePub.qPageForJson(sql, paramMap, pageSize, pageNo, goLastPage);
	}
	
	public PlsqlRetValue insert(UserCustVO u,Long loginId) throws Exception{
		PlsqlRetValue ret=ucDao.insert(u);
		if(ret.getRetcode()!=0){
			DevJdbcSubProcess.setRollbackOnly();//该事务必须要回滚！
		}
		return ret;
	}
	
	public PlsqlRetValue update(UserCustVO u,Long loginId) throws Exception
	{ 
		PlsqlRetValue ret=ucDao.update(u);
		if(ret.getRetcode()!=0){
			DevJdbcSubProcess.setRollbackOnly();//该事务必须要回滚！
		}
		return ret;
	}
	
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public String findByIdForJSON(Long userCustId,Long loginId) throws Exception{
		return "{\"rows\":"+ucDao.findByIdForJSON(userCustId).toJsonStr()+"}";
	}
}
