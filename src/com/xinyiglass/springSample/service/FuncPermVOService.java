package com.xinyiglass.springSample.service;

import java.util.Date;
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

	private ThreadLocal<Long> loginIdTL = new ThreadLocal<Long>();
	
	public Long getLoginId() {
		return this.loginIdTL.get();
	}
	
	public void setLoginId(Long loginId) {
		this.loginIdTL.set(loginId); 
	}
	
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public String findForPage(int pageSize,int pageNo,boolean goLastPage,Long userId,Long funcId,Date startDate_F,Date startDate_T,Date endDate_F,Date endDate_T,String orderBy) throws Exception{
		Map<String,Object> paramMap=new HashMap<String,Object>();
		StringBuffer sqlBuff = new StringBuffer();
		sqlBuff.append("SELECT * FROM XYG_ALB2B_FUNC_PERM_V A WHERE 1=1");
		sqlBuff.append(SqlStmtPub.getAndStmt("USER_ID",userId,paramMap));
		sqlBuff.append(SqlStmtPub.getAndStmt("FUNCTION_ID",funcId,paramMap));
		sqlBuff.append(SqlStmtPub.getAndStmt("START_DATE",startDate_F,startDate_T,paramMap));
		sqlBuff.append(SqlStmtPub.getAndStmt("END_DATE",endDate_F,endDate_T,paramMap));
		sqlBuff.append(" ORDER BY "+orderBy);
		return pagePub.qPageForJson(sqlBuff.toString(), paramMap, pageSize, pageNo, goLastPage);
	}
	
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public FuncPermVO findForFuncPermVOById(Long pId) throws Exception{
		return fpDao.findByRespId(pId);
	}
	
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public String findFuncPermByIdForJSON(Long pId) throws Exception{
		return "{\"rows\":"+fpDao.findByIdForJSON(pId).toJsonStr()+"}";
	}
	
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public String findFuncPermByUserAndFunc(Long userId,Long funcId) throws Exception{
		return "{\"rows\":"+fpDao.findByUserAndFuncForJSON(userId, funcId).toJsonStr()+"}";
	}
	
	public PlsqlRetValue insert(FuncPermVO fp) throws Exception{
		PlsqlRetValue ret=fpDao.insert(fp);
		if(ret.getRetcode()!=0){
			DevJdbcSubProcess.setRollbackOnly();//该事务必须要回滚！
		}
		return ret;
	}
	
	public PlsqlRetValue update(FuncPermVO fp) throws Exception{
		PlsqlRetValue ret=fpDao.update(fp);
		if(ret.getRetcode()!=0){
			DevJdbcSubProcess.setRollbackOnly();//该事务必须要回滚！
		}
		return ret;
	}
}
