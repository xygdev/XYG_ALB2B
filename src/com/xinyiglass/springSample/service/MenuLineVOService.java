package com.xinyiglass.springSample.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xinyiglass.springSample.dao.MenuLineVODao;
import com.xinyiglass.springSample.entity.MenuLineVO;

import xygdev.commons.entity.PlsqlRetValue;
import xygdev.commons.page.PagePub;
import xygdev.commons.springjdbc.DevJdbcSubProcess;

@Service
@Transactional(rollbackFor=Exception.class)//指定checked的异常Exception也要回滚！
public class MenuLineVOService {
	
	@Autowired
	PagePub pagePub;
	@Autowired
	MenuLineVODao menuDao;

	private Long loginId;
	
	public Long getLoginId() {
		return loginId;
	}
	
	public void setLoginId(Long loginId) {
		this.loginId = loginId;
	}
	
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public String findForPage(int pageSize,int pageNo,boolean goLastPage,String orderby,Long menuId) throws Exception{
		String sql="SELECT A.*,XYG_ALD_COMMON_PKG.GET_LKM_BY_LKCODE('XYG_ALB2B_YN',A.ENABLED_FLAG) ENABLED FROM XYG_ALB2B_MENU_LINES_V A WHERE MENU_ID = :1 ORDER BY "+orderby;
		Map<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("1", menuId);
		return pagePub.qPageForJson(sql, paramMap, pageSize, pageNo, goLastPage);
	}
	
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public String findAutoAddSequence(Long menuId) throws Exception{
		Long Seq = menuDao.autoAddSequence(menuId);
		return "{\"rows\":[{\"MENU_SEQUENCE\":\""+Seq+"\"}]}";
	}
	
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public String findMenuLineForJSON(Long menuId,Long menuSeq) throws Exception{
		return "{\"rows\":"+menuDao.findMenuLineForJSON(menuId, menuSeq).toJsonStr()+"}";
	}
	
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public MenuLineVO findForMenuVOById(Long menuId,Long menuSeq) throws Exception{
		return menuDao.findByMenuId(menuId, menuSeq);
	}
	
	public PlsqlRetValue insert(MenuLineVO m) throws Exception{
		PlsqlRetValue ret=menuDao.insert(m);
		if(ret.getRetcode()!=0){
			DevJdbcSubProcess.setRollbackOnly();//该事务必须要回滚！
		}
		return ret;
	}
	
	public PlsqlRetValue update(MenuLineVO lockMenuLineVO,MenuLineVO updateMenuVO) throws Exception
	{ 
		PlsqlRetValue ret=menuDao.lock(lockMenuLineVO);
		if(ret.getRetcode()==0){
			ret=menuDao.update(updateMenuVO);
		}else{
			DevJdbcSubProcess.setRollbackOnly();//该事务必须要回滚！
		}
		return ret;
	}

}
