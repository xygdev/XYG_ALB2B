package com.xinyiglass.springSample.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xinyiglass.springSample.dao.CustGroupDao;

import xygdev.commons.entity.PlsqlRetValue;
import xygdev.commons.page.PagePub;
import xygdev.commons.springjdbc.DevJdbcSubProcess;
import xygdev.commons.sqlStmt.SqlStmtPub;

@Service
@Transactional(rollbackFor=Exception.class)//指定checked的异常Exception也要回滚！
public class CustGroupService {
	@Autowired
	PagePub pagePub;
	@Autowired
	CustGroupDao groupDao;
	
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public String findForCustPage(int pageSize,int pageNo,boolean goLastPage,Map<String,Object> conditionMap,Long loginId) throws Exception{
		Map<String,Object> paramMap=new HashMap<String,Object>();
		StringBuffer sqlBuff = new StringBuffer();
		sqlBuff.append("SELECT * FROM XYG_ALFR_CUST_ACCOUNT_V A WHERE 1=1");
		sqlBuff.append(SqlStmtPub.getAndStmt("CUST_ACCOUNT_ID",conditionMap.get("custId"),paramMap));
		sqlBuff.append(SqlStmtPub.getAndStmt("GROUP_ID",conditionMap.get("groupId"),paramMap));
		sqlBuff.append(" ORDER BY "+conditionMap.get("orderBy"));
		return pagePub.qPageForJson(sqlBuff.toString(), paramMap, pageSize, pageNo, goLastPage);
	}
	
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public String findCustForJSON(Long orgId,Long custId,Long loginId) throws Exception{
		return "{\"rows\":"+groupDao.findForCust(orgId, custId).toJsonStr()+"}";
	}
	
	//update
	public PlsqlRetValue update(Long orgId,Long custAccountId,Long groupId,Long loginId) throws Exception
	{ 
		PlsqlRetValue ret=groupDao.updateCustGroup(orgId, custAccountId, groupId);
		System.out.println("Retcode:"+ret.toString());
		if(ret.getRetcode()!=0){
			DevJdbcSubProcess.setRollbackOnly();//该事务必须要回滚！
		}
		return ret;
	}	
	
}
