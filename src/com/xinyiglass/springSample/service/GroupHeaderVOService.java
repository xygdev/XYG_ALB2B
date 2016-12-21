package com.xinyiglass.springSample.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xinyiglass.springSample.dao.GroupHeaderVODao;
import com.xinyiglass.springSample.entity.GroupHeaderVO;

import xygdev.commons.entity.PlsqlRetValue;
import xygdev.commons.page.PagePub;
import xygdev.commons.springjdbc.DevJdbcSubProcess;
import xygdev.commons.sqlStmt.SqlStmtPub;

@Service
@Transactional(rollbackFor=Exception.class)//指定checked的异常Exception也要回滚
public class GroupHeaderVOService {
	
	@Autowired
	PagePub pagePub;
	@Autowired
	GroupHeaderVODao groupDao;
    
	private HttpSession sess;
	
	public HttpSession getSess() {
		return sess;
	}

	public void setSess(HttpSession sess) {
		this.sess = sess;
	}
    
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public String findForPage(int pageSize,int pageNo,boolean goLastPage,Long groupId,String orderBy) throws Exception{
		Map<String,Object> paramMap=new HashMap<String,Object>();
		StringBuffer sqlBuff = new StringBuffer();
		sqlBuff.append("SELECT * FROM XYG_ALB2B_GROUP_HEADERS_V A WHERE 1=1");
		sqlBuff.append(SqlStmtPub.getAndStmt("GROUP_ID",groupId,paramMap));
		sqlBuff.append(" ORDER BY "+orderBy);
		return pagePub.qPageForJson(sqlBuff.toString(), paramMap, pageSize, pageNo, goLastPage);
	}
	
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public GroupHeaderVO findForGroupVOById(Long groupId) throws Exception{
		return groupDao.findByGroupId(groupId);
	}
	
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public String findGroupByIdForJSON(Long groupId) throws Exception{
		return "{\"rows\":"+groupDao.findByIdForJSON(groupId).toJsonStr()+"}";
	}
	
	//insert
	public PlsqlRetValue insert(GroupHeaderVO g) throws Exception{
		PlsqlRetValue ret=groupDao.insert(g);
		if(ret.getRetcode()!=0){
			DevJdbcSubProcess.setRollbackOnly();//该事务必须要回滚！
		}
		return ret;
	}
	
	//update
	public PlsqlRetValue update(GroupHeaderVO lockGroupVO,GroupHeaderVO updateGroupVO) throws Exception
	{ 
		PlsqlRetValue ret=groupDao.lock(lockGroupVO);
		if(ret.getRetcode()==0){
			ret=groupDao.update(updateGroupVO);
		}else{
			DevJdbcSubProcess.setRollbackOnly();//该事务必须要回滚！
		}
		return ret;
	}
	
}
