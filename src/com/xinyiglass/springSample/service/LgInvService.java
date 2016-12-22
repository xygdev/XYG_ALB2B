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

	private Long loginId;
	
	public Long getLoginId() {
		return loginId;
	}
	
	public void setLoginId(Long loginId) {
		this.loginId = loginId;
	}
	
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public String findForPage(int pageSize,int pageNo,boolean goLastPage,String orderby,Long organizationId,Long width,Long height,Long thickness,String coatingType) throws Exception{
		Map<String,Object> paramMap=new HashMap<String,Object>();
		StringBuffer sqlBuf=new StringBuffer();
		sqlBuf.append("SELECT * FROM XYG_ALB2B_LG_ONHAND_V WHERE 1=1");
		sqlBuf.append(SqlStmtPub.getAndStmt("ORGANIZATION_ID",organizationId,paramMap));
		sqlBuf.append(SqlStmtPub.getAndStmt("WIDTH",width,paramMap));
		sqlBuf.append(SqlStmtPub.getAndStmt("HEIGHT",height,paramMap));
		sqlBuf.append(SqlStmtPub.getAndStmt("THICKNESS",thickness,paramMap));
		sqlBuf.append(SqlStmtPub.getAndStmt("COATING_TYPE",coatingType,paramMap));
		sqlBuf.append(" ORDER BY "+orderby);
		return pagePub.qPageForJson(sqlBuf.toString(), paramMap, pageSize, pageNo, goLastPage);
	}
}
