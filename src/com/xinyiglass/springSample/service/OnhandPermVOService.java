package com.xinyiglass.springSample.service;

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
	
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public String findForPage(int pageSize,int pageNo,boolean goLastPage,Map<String,Object> conditionMap,Long loginId) throws Exception{
		Map<String,Object> paramMap=new HashMap<String,Object>();
		StringBuffer sqlBuff = new StringBuffer();
		sqlBuff.append("SELECT * FROM XYG_ALB2B_ONHAND_PERM_V A WHERE 1=1");
		sqlBuff.append(SqlStmtPub.getAndStmt("USER_ID",conditionMap.get("userId"),paramMap));
		sqlBuff.append(SqlStmtPub.getAndStmt("ORGANIZATION_ID",conditionMap.get("organId"),paramMap));
		sqlBuff.append(SqlStmtPub.getAndStmt("START_DATE",conditionMap.get("startDate_F"),conditionMap.get("startDate_T"),paramMap));
		sqlBuff.append(SqlStmtPub.getAndStmt("END_DATE",conditionMap.get("endDate_F"),conditionMap.get("endDate_T"),paramMap));
		sqlBuff.append(" ORDER BY "+conditionMap.get("orderBy"));
		return pagePub.qPageForJson(sqlBuff.toString(), paramMap, pageSize, pageNo, goLastPage);
	}
	
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public OnhandPermVO findForOnhandPermVOById(Long pId,Long loginId) throws Exception{
		return onhandDao.findByPId(pId);
	}
	
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public String findOnhandPermByIdForJSON(Long pId,Long loginId) throws Exception{
		return "{\"rows\":"+onhandDao.findByIdForJSON(pId).toJsonStr()+"}";
	}
	
	public PlsqlRetValue insert(OnhandPermVO op,Long loginId) throws Exception{
		PlsqlRetValue ret=onhandDao.insert(op);
		if(ret.getRetcode()!=0){
			DevJdbcSubProcess.setRollbackOnly();//该事务必须要回滚！
		}
		return ret;
	}
	
	public PlsqlRetValue update(OnhandPermVO lockOpVO,OnhandPermVO updateOpVO,Long loginId) throws Exception
	{ 
		PlsqlRetValue ret=onhandDao.lock(lockOpVO);
		if(ret.getRetcode()==0){
			ret=onhandDao.update(updateOpVO);
		}else{
			DevJdbcSubProcess.setRollbackOnly();//该事务必须要回滚！
		}
		return ret;
	}
	
	//验证是否在更新库存
	public String validateEdiLog(String syncCode,Long loginId) throws Exception{
		String sql = "SELECT COUNT(*) COUNT FROM XYG_ALB2B_EDI_LOG WHERE SYNC_CODE = :1 AND PROCESS_FLAG = '1'";
		Map<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("1", syncCode);
		return pagePub.qSqlForJson(sql, paramMap);
	}
}
