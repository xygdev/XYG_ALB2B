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
public class ResnInvService {
	@Autowired
	PagePub pagePub;
	
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public String findForPage(int pageSize,int pageNo,boolean goLastPage,String orderby,Long custId,Long width,Long height,String coatingType,String packCode) throws Exception{
		Map<String,Object> paramMap=new HashMap<String,Object>();
		StringBuffer sqlBuf=new StringBuffer();
		sqlBuf.append("SELECT * FROM XYG_ALB2B_RESERVATIONS_V WHERE 1=1");
		sqlBuf.append(SqlStmtPub.getAndStmt("CUSTOMER_ID",custId,paramMap));
		sqlBuf.append(SqlStmtPub.getAndStmt("WIDTH",width,paramMap));
		sqlBuf.append(SqlStmtPub.getAndStmt("HEIGHT",height,paramMap));
		sqlBuf.append(SqlStmtPub.getAndStmt("COATING_TYPE",coatingType,paramMap));
		sqlBuf.append(SqlStmtPub.getAndStmt("PACK_CODE",packCode,paramMap));
		sqlBuf.append(" ORDER BY "+orderby);
		return pagePub.qPageForJson(sqlBuf.toString(), paramMap, pageSize, pageNo, goLastPage);
	}
}
