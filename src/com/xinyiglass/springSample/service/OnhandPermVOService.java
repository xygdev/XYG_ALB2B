package com.xinyiglass.springSample.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xinyiglass.springSample.dao.OnhandPermVODao;
import com.xinyiglass.springSample.entity.OnhandPermVO;

import xygdev.commons.entity.PlsqlRetValue;
import xygdev.commons.page.PagePub;
import xygdev.commons.springjdbc.DevJdbcSubProcess;
import xygdev.commons.sqlStmt.SqlStmtPub;

@Service
@Transactional(rollbackFor=Exception.class)//指定checked的异常Exception也要回滚
public class OnhandPermVOService {
	@Autowired
	PagePub pagePub;
	@Autowired
	OnhandPermVODao onhandDao;

	private ThreadLocal<Long> loginIdTL = new ThreadLocal<Long>();
	
	public Long getLoginId() {
		return this.loginIdTL.get();
	}
	
	public void setLoginId(Long loginId) {
		this.loginIdTL.set(loginId); 
	}
	
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public String findForPage(int pageSize,int pageNo,boolean goLastPage,Long userId,Long organId,Date startDate_F,Date startDate_T,Date endDate_F,Date endDate_T,String orderBy) throws Exception{
		Map<String,Object> paramMap=new HashMap<String,Object>();
		StringBuffer sqlBuff = new StringBuffer();
		sqlBuff.append("SELECT * FROM XYG_ALB2B_ONHAND_PERM_V A WHERE 1=1");
		sqlBuff.append(SqlStmtPub.getAndStmt("USER_ID",userId,paramMap));
		sqlBuff.append(SqlStmtPub.getAndStmt("ORGANIZATION_ID",organId,paramMap));
		sqlBuff.append(SqlStmtPub.getAndStmt("START_DATE",startDate_F,startDate_T,paramMap));
		sqlBuff.append(SqlStmtPub.getAndStmt("END_DATE",endDate_F,endDate_T,paramMap));
		sqlBuff.append(" ORDER BY "+orderBy);
		return pagePub.qPageForJson(sqlBuff.toString(), paramMap, pageSize, pageNo, goLastPage);
	}
	
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public OnhandPermVO findForOnhandPermVOById(Long pId) throws Exception{
		return onhandDao.findByPId(pId);
	}
	
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public String findOnhandPermByIdForJSON(Long pId) throws Exception{
		return "{\"rows\":"+onhandDao.findByIdForJSON(pId).toJsonStr()+"}";
	}
	
	public PlsqlRetValue insert(OnhandPermVO op) throws Exception{
		PlsqlRetValue ret=onhandDao.insert(op);
		if(ret.getRetcode()!=0){
			DevJdbcSubProcess.setRollbackOnly();//该事务必须要回滚！
		}
		return ret;
	}
	
	public PlsqlRetValue update(OnhandPermVO lockOpVO,OnhandPermVO updateOpVO) throws Exception
	{ 
		PlsqlRetValue ret=onhandDao.lock(lockOpVO);
		if(ret.getRetcode()==0){
			ret=onhandDao.update(updateOpVO);
		}else{
			DevJdbcSubProcess.setRollbackOnly();//该事务必须要回滚！
		}
		return ret;
	}
}
