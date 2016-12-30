package com.xinyiglass.springSample.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import xygdev.commons.page.PagePub;
import xygdev.commons.sqlStmt.SqlStmtPub;

@Service
@Transactional(rollbackFor=Exception.class)//指定checked的异常Exception也要回滚！
public class LgInvService {
    
	@Autowired
	PagePub pagePub;
	
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public String findForPage(int pageSize,int pageNo,boolean goLastPage,Map<String,Object> conditionMap,Long loginId) throws Exception{
		Map<String,Object> paramMap=new HashMap<String,Object>();
		StringBuffer sqlBuf=new StringBuffer();
		sqlBuf.append("SELECT * FROM XYG_ALB2B_LG_ONHAND_V WHERE 1=1");
		sqlBuf.append(SqlStmtPub.getAndStmt("ORGANIZATION_ID",conditionMap.get("organizationId"),paramMap));
		sqlBuf.append(SqlStmtPub.getAndStmt("WIDTH",conditionMap.get("width"),paramMap));
		sqlBuf.append(SqlStmtPub.getAndStmt("HEIGHT",conditionMap.get("height"),paramMap));
		sqlBuf.append(SqlStmtPub.getAndStmt("THICKNESS",conditionMap.get("thickness"),paramMap));
		sqlBuf.append(SqlStmtPub.getAndStmt("COATING_TYPE",conditionMap.get("coatingType"),paramMap));
		sqlBuf.append(" ORDER BY "+conditionMap.get("orderby"));
		return pagePub.qPageForJson(sqlBuf.toString(), paramMap, pageSize, pageNo, goLastPage);
	}
}
