package com.xinyiglass.springSample.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xinyiglass.springSample.dao.FuncVODao;
import com.xinyiglass.springSample.entity.FuncVO;

import xygdev.commons.entity.PlsqlRetValue;
import xygdev.commons.page.PagePub;
import xygdev.commons.springjdbc.DevJdbcSubProcess;
import xygdev.commons.sqlStmt.SqlStmtPub;

@Service
@Transactional(rollbackFor=Exception.class)//指定checked的异常Exception也要回滚！
public class FuncVOService {
	@Autowired
	PagePub pagePub;
	@Autowired
	FuncVODao funcDao;
	
	public PlsqlRetValue insert(FuncVO f) throws Exception{
		PlsqlRetValue ret=funcDao.insert(f);
		System.out.println("Retcode:"+ret.toString());
		if(ret.getRetcode()!=0){
			DevJdbcSubProcess.setRollbackOnly();//该事务必须要回滚！
		}
		return ret;
	}
	
	public PlsqlRetValue update(FuncVO lockFuncVO,FuncVO updateFuncVO) throws Exception
	{ 
		PlsqlRetValue ret=funcDao.lock(lockFuncVO);
		System.out.println(ret);
		if(ret.getRetcode()==0){
			ret=funcDao.update(updateFuncVO);
		}else{
			DevJdbcSubProcess.setRollbackOnly();//该事务必须要回滚！
		}
		return ret;
	}
	
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public String findForPage(int pageSize,int pageNo,boolean goLastPage,Long funcId,String orderBy) throws Exception{
		Map<String,Object> paramMap=new HashMap<String,Object>();
		StringBuffer sqlBuff = new StringBuffer();
		sqlBuff.append("SELECT * FROM XYG_ALB2B_FUNCTIONS_V A WHERE 1=1");
		sqlBuff.append(SqlStmtPub.getAndStmt("FUNCTION_ID",funcId,paramMap));
		sqlBuff.append(" ORDER BY "+orderBy);
		return pagePub.qPageForJson(sqlBuff.toString(), paramMap, pageSize, pageNo, goLastPage);
	}
	
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public String findRespByIdForJSON(Long funcId) throws Exception{
		return "{\"rows\":"+funcDao.findByIdForJSON(funcId).toJsonStr()+"}";
	}
	
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public FuncVO findForFuncVOById(Long funcId) throws Exception{
		return funcDao.findByFuncId(funcId);
	}
}
