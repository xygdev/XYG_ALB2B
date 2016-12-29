package com.xinyiglass.springSample.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xinyiglass.springSample.dao.PoLineVODao;
import com.xinyiglass.springSample.entity.PoLineVO;

import xygdev.commons.entity.PlsqlRetValue;
import xygdev.commons.page.PagePub;
import xygdev.commons.springjdbc.DevJdbcSubProcess;

@Service
@Transactional(rollbackFor=Exception.class)//指定checked的异常Exception也要回滚！
public class PoLineVOService {
    
	@Autowired
	PagePub pagePub;
	@Autowired
	PoLineVODao plvDao;
	
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public String findForPage(int pageSize,int pageNo,boolean goLastPage,String orderby,Long poHeaderId,Long loginId) throws Exception{
		String sql="SELECT * FROM XYG_ALB2B_LG_PO_LINES_V WHERE PO_HEADER_ID = :1 ORDER BY "+orderby;
		Map<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("1", poHeaderId);
		return pagePub.qPageForJson(sql, paramMap, pageSize, pageNo, goLastPage);
	}
	
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public String findAutoAddSequence(Long poHeaderId,Long loginId) throws Exception{
		Long Seq = plvDao.autoAddSequence(poHeaderId);
		return "{\"rows\":[{\"LINE_NUM\":\""+Seq+"\"}]}";
	}
	
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public String findPoLineByIdForJSON(Long poLineId,Long loginId) throws Exception{
		return "{\"rows\":"+plvDao.findByIdForJSON(poLineId).toJsonStr()+"}";
	}
	
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public PoLineVO findForPoLineVOById(Long poLineId,Long loginId) throws Exception{
		return plvDao.findByPoLineId(poLineId);
	}
	
	//insert
	public PlsqlRetValue insert(PoLineVO pl,Long funcId,Long loginId) throws Exception{
		PlsqlRetValue ret = plvDao.insert(pl, funcId);
		if(ret.getRetcode()!=0){
			DevJdbcSubProcess.setRollbackOnly();//该事务必须要回滚！
		}
		return ret;
	}
	
	//update
	public PlsqlRetValue update(PoLineVO lockPoLineVO,PoLineVO updatePoLineVO,String userType,Long funcId,Long loginId) throws Exception
	{ 
		PlsqlRetValue ret=plvDao.lock(lockPoLineVO);
		if(ret.getRetcode()==0){
			ret=plvDao.update(updatePoLineVO,userType,funcId);
		}else{
			DevJdbcSubProcess.setRollbackOnly();//该事务必须要回滚！
		}
		return ret;
	}
	
	//delete
	public PlsqlRetValue delete(Long poLineId,Long loginId) throws Exception{
		PlsqlRetValue ret = plvDao.delete(poLineId);
		if(ret.getRetcode()!=0){
			DevJdbcSubProcess.setRollbackOnly();//该事务必须要回滚！
		}
		return ret;
	}
}
