package com.xinyiglass.springSample.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xinyiglass.springSample.dao.PoHeaderVODao;
import com.xinyiglass.springSample.entity.PoHeaderVO;

import xygdev.commons.entity.PlsqlRetValue;
import xygdev.commons.page.PagePub;
import xygdev.commons.springjdbc.DevJdbcSubProcess;
import xygdev.commons.sqlStmt.SqlStmtPub;

@Service
@Transactional(rollbackFor=Exception.class)//指定checked的异常Exception也要回滚！
public class PoHeaderVOService {
    
	@Autowired
	PagePub pagePub;
	@Autowired
	PoHeaderVODao phvDao;
    
	private HttpSession sess;
	
	public HttpSession getSess() {
		return sess;
	}

	public void setSess(HttpSession sess) {
		this.sess = sess;
	}
	
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public String findForPage(int pageSize,int pageNo,boolean goLastPage,Long userId,String poNumber,String custContractNumber,String status,Long custId,String orderBy) throws Exception{
		Map<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("1", userId);
		StringBuffer sqlBuff = new StringBuffer();
		sqlBuff.append("SELECT *");
		sqlBuff.append("  FROM XYG_ALB2B_LG_PO_HEADERS_V");
		sqlBuff.append(" WHERE ((CUSTOMER_ID IN (SELECT CUSTOMER_ID");
		sqlBuff.append("                          FROM XYG_ALB2B_USER_CUSTOMER");
		sqlBuff.append("                         WHERE USER_ID = :1)");
		sqlBuff.append("       AND (SELECT USER_TYPE");
		sqlBuff.append("              FROM XYG_ALB2B_USER");
		sqlBuff.append("             WHERE USER_ID = :1) = 'CUSTOMER')");
		sqlBuff.append("    OR (CUSTOMER_ID IN (SELECT CUST_ACCOUNT_ID");
		sqlBuff.append("                          FROM XYG_ALFR_CUST_ACCOUNT");
		sqlBuff.append("                         WHERE GROUP_ID IN (SELECT L.SUB_GROUP_ID");
		sqlBuff.append("                                             FROM XYG_ALB2B_GROUP_HEADERS H");
		sqlBuff.append("                                                 ,XYG_ALB2B_GROUP_LINES L");
		sqlBuff.append("                                            WHERE 1=1");
		sqlBuff.append("                                              AND L.GROUP_ID=H.GROUP_ID");
		sqlBuff.append("                                       CONNECT BY H.GROUP_ID = PRIOR L.SUB_GROUP_ID");
		sqlBuff.append("                                       START WITH H.GROUP_ID=(SELECT USER_GROUP_ID");
		sqlBuff.append("                                                                FROM XYG_ALB2B_USER");
		sqlBuff.append("                                                               WHERE USER_ID = :1)");
		sqlBuff.append("                                            UNION");
		sqlBuff.append("                                           SELECT USER_GROUP_ID");
		sqlBuff.append("                                             FROM XYG_ALB2B_USER");
		sqlBuff.append("                                            WHERE USER_ID = :1)");
		sqlBuff.append("       AND (SELECT USER_TYPE");
		sqlBuff.append("              FROM XYG_ALB2B_USER");
		sqlBuff.append("             WHERE USER_ID = :1) = 'EMP')))");
		sqlBuff.append(SqlStmtPub.getAndStmt("PO_NUMBER",poNumber,paramMap));
		sqlBuff.append(SqlStmtPub.getAndStmt("CUSTOMER_CONTRACT_NUMBER",custContractNumber,paramMap));
		sqlBuff.append(SqlStmtPub.getAndStmt("STATUS",status,paramMap));
		sqlBuff.append(SqlStmtPub.getAndStmt("CUSTOMER_ID",custId,paramMap));
		sqlBuff.append(" ORDER BY "+orderBy);
		return pagePub.qPageForJson(sqlBuff.toString(), paramMap, pageSize, pageNo, goLastPage);
	}
	
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public String findPoHeaderByIdForJSON(Long poHeaderId) throws Exception{
		return "{\"rows\":"+phvDao.findByIdForJSON(poHeaderId).toJsonStr()+"}";
	}
	
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public PoHeaderVO findForPoHeaderVOById(Long poHeaderId) throws Exception{
		return phvDao.findByPoHeaderId(poHeaderId);
	}
	
	//insert
	public PlsqlRetValue insert(PoHeaderVO ph,Long funcId) throws Exception{
		PlsqlRetValue ret = phvDao.insert(ph, funcId);
		if(ret.getRetcode()!=0){
			DevJdbcSubProcess.setRollbackOnly();//该事务必须要回滚！
		}
		return ret;
	}
	
	//update
	public PlsqlRetValue update(PoHeaderVO lockPoHeaderVO,PoHeaderVO updatePoHeaderVO,Long userId,Long funcId) throws Exception
	{ 
		PlsqlRetValue ret=phvDao.lock(lockPoHeaderVO, userId, funcId);
		if(ret.getRetcode()==0){
			ret=phvDao.update(updatePoHeaderVO);
		}else{
			DevJdbcSubProcess.setRollbackOnly();//该事务必须要回滚！
		}
		return ret;
	}
	
	//delete
	public PlsqlRetValue delete(Long poHeaderId) throws Exception{
		PlsqlRetValue ret = phvDao.delete(poHeaderId);
		if(ret.getRetcode()!=0){
			DevJdbcSubProcess.setRollbackOnly();//该事务必须要回滚！
		}
		return ret;
	}	
	
	//changeStatus
	public PlsqlRetValue changeStatus(Long poHeaderId,String status,Long userId) throws Exception{
		PlsqlRetValue ret = phvDao.changeStatus(poHeaderId, status,userId);
		if(ret.getRetcode()!=0){
			DevJdbcSubProcess.setRollbackOnly();//该事务必须要回滚！
		}
		return ret;
	}
}
