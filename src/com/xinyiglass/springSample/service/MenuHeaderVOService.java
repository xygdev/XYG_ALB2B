package com.xinyiglass.springSample.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import xygdev.commons.entity.PlsqlRetValue;
import xygdev.commons.page.PagePub;
import xygdev.commons.springjdbc.DevJdbcSubProcess;
import xygdev.commons.sqlStmt.SqlStmtPub;

import com.xinyiglass.springSample.dao.MenuHeaderVODao;
import com.xinyiglass.springSample.entity.MenuHeaderVO;

@Service
@Transactional(rollbackFor=Exception.class)//指定checked的异常Exception也要回滚！
public class MenuHeaderVOService {
	
	@Autowired
	MenuHeaderVODao menuDao;
	@Autowired
	PagePub pagePub;
	
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public String findPersonalMenuById(Long menuId,Long loginId) throws Exception{
		return menuDao.findPersonalMenuById(menuId).toJsonStr();
	}
	
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public String findForPage(int pageSize,int pageNo,boolean goLastPage,Long menuId,String orderBy,Long loginId) throws Exception{
		Map<String,Object> paramMap=new HashMap<String,Object>();
		StringBuffer sqlBuff = new StringBuffer();
		sqlBuff.append("SELECT * FROM XYG_ALB2B_MENU_HEADERS_V A WHERE 1=1");
		sqlBuff.append(SqlStmtPub.getAndStmt("MENU_ID",menuId,paramMap));
		sqlBuff.append(" ORDER BY "+orderBy);
		return pagePub.qPageForJson(sqlBuff.toString(), paramMap, pageSize, pageNo, goLastPage);
	}
	
	public PlsqlRetValue insert(MenuHeaderVO m,Long loginId) throws Exception{
		PlsqlRetValue ret=menuDao.insert(m);
		if(ret.getRetcode()!=0){
			DevJdbcSubProcess.setRollbackOnly();//该事务必须要回滚！
		}
		return ret;
	}
	
	public PlsqlRetValue update(MenuHeaderVO lockMenuVO,MenuHeaderVO updateMenuVO,Long loginId) throws Exception
	{ 
		PlsqlRetValue ret=menuDao.lock(lockMenuVO);
		if(ret.getRetcode()==0){
			ret=menuDao.update(updateMenuVO);
		}else{
			DevJdbcSubProcess.setRollbackOnly();//该事务必须要回滚！
		}
		return ret;
	}
	
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public MenuHeaderVO findForMenuVOById(Long menuId,Long loginId) throws Exception{
		return menuDao.findByMenuId(menuId);
	}
	
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public String findMenuByIdForJSON(Long menuId,Long loginId) throws Exception{
		return "{\"rows\":"+menuDao.findByIdForJSON(menuId).toJsonStr()+"}";
	}

}
