package com.xinyiglass.springSample.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xinyiglass.springSample.dao.FuncPermVODao;
import com.xinyiglass.springSample.entity.FuncPermVO;

import xygdev.commons.entity.PlsqlRetValue;
import xygdev.commons.page.PagePub;
import xygdev.commons.springjdbc.DevJdbcSubProcess;
import xygdev.commons.sqlStmt.SqlStmtPub;

@Service
@Transactional(rollbackFor=Exception.class)//指定checked的异常Exception也要回滚！
public class FuncPermVOService {
	@Autowired
	PagePub pagePub;
	@Autowired
	FuncPermVODao fpDao;
	
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public String findForPage(int pageSize,int pageNo,boolean goLastPage,Map<String,Object> conditionMap,Long loginId) throws Exception{
		Map<String,Object> paramMap=new HashMap<String,Object>();
		StringBuffer sqlBuff = new StringBuffer();
		sqlBuff.append("SELECT * FROM XYG_ALB2B_FUNC_PERM_V A WHERE 1=1");
		sqlBuff.append(SqlStmtPub.getAndStmt("USER_ID",conditionMap.get("userId"),paramMap));
		sqlBuff.append(SqlStmtPub.getAndStmt("FUNCTION_ID",conditionMap.get("funcId"),paramMap));
		sqlBuff.append(SqlStmtPub.getAndStmt("START_DATE",conditionMap.get("startDate_F"),conditionMap.get("startDate_T"),paramMap));
		sqlBuff.append(SqlStmtPub.getAndStmt("END_DATE",conditionMap.get("endDate_F"),conditionMap.get("endDate_T"),paramMap));
		sqlBuff.append(" ORDER BY "+conditionMap.get("orderBy"));
		return pagePub.qPageForJson(sqlBuff.toString(), paramMap, pageSize, pageNo, goLastPage);
	}
	
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public FuncPermVO findForFuncPermVOById(Long pId,Long loginId) throws Exception{
		return fpDao.findByRespId(pId);
	}
	
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public String findFuncPermByIdForJSON(Long pId,Long loginId) throws Exception{
		return "{\"rows\":"+fpDao.findByIdForJSON(pId).toJsonStr()+"}";
	}
	
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public String findFuncPermByUserAndFunc(Long userId,Long funcId,Long loginId) throws Exception{
		return "{\"rows\":"+fpDao.findByUserAndFuncForJSON(userId, funcId).toJsonStr()+"}";
	}
	
	public PlsqlRetValue insert(FuncPermVO fp,Long loginId) throws Exception{
		PlsqlRetValue ret=fpDao.insert(fp);
		if(ret.getRetcode()!=0){
			DevJdbcSubProcess.setRollbackOnly();//该事务必须要回滚！
		}
		return ret;
	}
	
	public PlsqlRetValue update(FuncPermVO fp,Long loginId) throws Exception{
		PlsqlRetValue ret=fpDao.update(fp);
		if(ret.getRetcode()!=0){
			DevJdbcSubProcess.setRollbackOnly();//该事务必须要回滚！
		}
		return ret;
	}
}
