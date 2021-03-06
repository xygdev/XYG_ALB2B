package com.xinyiglass.springSample.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xinyiglass.springSample.dao.UserVODao;
import com.xinyiglass.springSample.entity.UserVO;

import xygdev.commons.entity.PlsqlRetValue;
import xygdev.commons.page.PagePub;
import xygdev.commons.springjdbc.DevJdbcSubProcess;
import xygdev.commons.sqlStmt.SqlStmtPub;

@Service
@Transactional(rollbackFor=Exception.class)//指定checked的异常Exception也要回滚！
public class UserVOService {
	
	@Autowired
	UserVODao userDao;
	@Autowired
	PagePub pagePub;
	/*private Long loginId;
	
	public Long getLoginId() {
		return loginId;
	}
	
	public void setLoginId(Long loginId) {
		this.loginId = loginId;
	}
	*/
	public PlsqlRetValue insert(UserVO u,Long loginId) throws Exception{
		PlsqlRetValue ret=userDao.insert(u);
		if(ret.getRetcode()!=0){
			DevJdbcSubProcess.setRollbackOnly();//该事务必须要回滚！
		}
		return ret;
	}
	
	public PlsqlRetValue update(UserVO lockUserVO,UserVO updateUserVO,Long loginId) throws Exception
	{ 
		PlsqlRetValue ret=userDao.lock(lockUserVO);
		if(ret.getRetcode()==0){
			ret=userDao.update(updateUserVO);
		}else{
			DevJdbcSubProcess.setRollbackOnly();//该事务必须要回滚！
		}
		return ret;
	}
	
	public PlsqlRetValue updateImgUrl(String fileName,Long userId,Long loginId) throws Exception{
		PlsqlRetValue ret = userDao.updateImgUrl(fileName, userId);
		if(ret.getRetcode()!=0){
			DevJdbcSubProcess.setRollbackOnly();//该事务必须要回滚！
		}
		return ret;
	}	
	
	public PlsqlRetValue updatePWD(Long userId,String oldPassword,String newPassword,Long loginId) throws Exception{
		PlsqlRetValue ret = userDao.updatePWD(userId, oldPassword, newPassword);
		if(ret.getRetcode()!=0){
			DevJdbcSubProcess.setRollbackOnly();//该事务必须要回滚！
		}
		return ret;
	}
	
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public UserVO findForUserVOByName(String userName,Long loginId) throws Exception{
		return userDao.findByUserName(userName);
	}
	
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public UserVO findForUserVOById(Long userId,Long loginId) throws Exception{
		return userDao.findByUserId(userId);
	}
		
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public String findForPage(int pageSize,int pageNo,boolean goLastPage,Map<String,Object> conditionMap,Long loginId) throws Exception{
		Map<String,Object> paramMap=new HashMap<String,Object>();
		StringBuffer sqlBuff = new StringBuffer();
		sqlBuff.append("select A.*,XYG_ALD_COMMON_PKG.GET_LKM_BY_LKCODE('XYG_ALB2B_USER_TYPE',A.USER_TYPE) USER_TYPE_M from XYG_ALB2B_USER_V A WHERE 1=1");
		sqlBuff.append(SqlStmtPub.getAndStmt("USER_ID",conditionMap.get("userId"),paramMap));
		sqlBuff.append(SqlStmtPub.getAndStmt("RESP_ID",conditionMap.get("respId"),paramMap));
		sqlBuff.append(SqlStmtPub.getAndStmt("USER_TYPE",conditionMap.get("userType"),paramMap));
		sqlBuff.append(SqlStmtPub.getAndStmt("START_DATE",conditionMap.get("startDate_F"),conditionMap.get("startDate_T"),paramMap));
		sqlBuff.append(SqlStmtPub.getAndStmt("END_DATE",conditionMap.get("endDate_F"),conditionMap.get("endDate_T"),paramMap));
		sqlBuff.append(" ORDER BY "+conditionMap.get("orderBy"));
		//测试会话初始化功能
		//Long sid=pagePub.getDevJdbcTemplate().queryForLong("select USERENV('SESSIONID') from dual");
		//Long longId=pagePub.getDevJdbcTemplate().queryForLong("select XYG_ALD_GLOBAL_PKG.login_id from dual");
		//LogUtil.log("...当前SESS登录id:"+this.getLoginId()+"-->对应的数据库sid:"+sid+",longId:"+longId);
		//Thread.sleep(10000);//模拟等待10秒，经过测试，确实会用另外一个会话ID。
		return pagePub.qPageForJson(sqlBuff.toString(), paramMap, pageSize, pageNo, goLastPage);
	}		
	
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public String findUserForLOV(Long sendUserId,String userName,Long loginId) throws Exception{
		return userDao.findByUserForRS(sendUserId, userName).toJsonStr();
	}
	
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public String findUserByIdForJSON(Long userId,Long loginId) throws Exception{
		return "{\"rows\":"+userDao.findByIdForJSON(userId).toJsonStr()+"}";
	}
	
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public String findOtherUsers(Long userId,Long loginId) throws Exception{
		return userDao.findOtherUsers(userId);
	}
}
