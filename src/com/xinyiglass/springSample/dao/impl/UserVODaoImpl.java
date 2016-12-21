package com.xinyiglass.springSample.dao.impl;

import java.util.HashMap;
import java.util.Map;

import com.xinyiglass.springSample.dao.UserVODao;
import com.xinyiglass.springSample.entity.UserVO;
import com.xinyiglass.springSample.util.LogUtil;

import xygdev.commons.entity.PlsqlRetValue;
import xygdev.commons.entity.SqlResultSet;
import xygdev.commons.springjdbc.DevJdbcDaoSupport;
import xygdev.commons.util.TypeConvert;

public class UserVODaoImpl extends DevJdbcDaoSupport implements UserVODao{
	public PlsqlRetValue insert(UserVO u) throws Exception{
		String sql ="Declare "
				+ "     l_user_id number; "
				+ "  begin "
				+ "  XYG_ALB2B_USER_PKG.INSERT_USER( "
				+ "  :1"
				+ " ,:2"
				+ " ,:3"
                + " ,:4"
                + " ,:5"
                + " ,:6"
                + " ,:7"
                + " ,:8"
                + " ,:9"
                + " ,:10"
                + " ,:11"
				+ " ,:"+PlsqlRetValue.RETCODE
				+ " ,:"+PlsqlRetValue.ERRBUF
				+ " ); "
				+ "end;";
		Map<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("1", u.getUserId());
		paramMap.put("2", u.getUserName());
		paramMap.put("3", u.getDescription());
		paramMap.put("4", TypeConvert.u2tDate(u.getStartDate()));
		paramMap.put("5", TypeConvert.u2tDate(u.getEndDate()));
		paramMap.put("6", u.getEncryptedUserPassword());
		paramMap.put("7", u.getRespId());
		paramMap.put("8", u.getEmpId());
		paramMap.put("9", u.getUserType());
		paramMap.put("10", u.getUserGroupId());
		paramMap.put("11", u.getImgUrl());
		//xygdev.commons.util.Constant.LINE_SEPARATOR="\\r\\n";//System.getProperty("line.separator");
		//log("xygdev.commons.util.Constant.LINE_SEPARATOR:"+xygdev.commons.util.Constant.LINE_SEPARATOR);
		return this.getDevJdbcTemplate().executeForRetValue(sql, paramMap);
	}
	
	//lock
	public PlsqlRetValue lock(UserVO u) throws Exception{
		String sql ="Declare "
				+ "     l_user_id number; "
				+ "  begin "
				+ "  XYG_ALB2B_USER_PKG.LOCK_USER( "
				+ "  :1"
				+ " ,:2"
				+ " ,:3"
                + " ,:4"
                + " ,:5"
                + " ,:6"
                + " ,:7"
                + " ,:8"
                + " ,:9"
                + " ,:10"
                + " ,:11"
				+ " ,:"+PlsqlRetValue.RETCODE
				+ " ,:"+PlsqlRetValue.ERRBUF
				+ " ); "
				+ "end;";
		LogUtil.log("LOCK Emp ID:"+u.getUserId());
		Map<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("1", u.getUserId());
		paramMap.put("2", u.getUserName());
		paramMap.put("3", u.getDescription());
		paramMap.put("4", TypeConvert.u2tDate(u.getStartDate()));
		paramMap.put("5", TypeConvert.u2tDate(u.getEndDate()));
		paramMap.put("6", u.getEncryptedUserPassword());
		paramMap.put("7", u.getRespId());
		paramMap.put("8", u.getEmpId());
		paramMap.put("9", u.getUserType());
		paramMap.put("10", u.getUserGroupId());
		paramMap.put("11", u.getImgUrl());
		return this.getDevJdbcTemplate().executeForRetValue(sql, paramMap);	
	}
	
	//update
	public PlsqlRetValue update(UserVO u) throws Exception{
		String sql = "Declare "
				+	"  l_user_id number; "
				+	"begin "
				+ "  XYG_ALB2B_USER_PKG.UPDATE_USER( "
				+ "  :1"
				+ " ,:2"
				+ " ,:3"
	            + " ,:4"
	            + " ,:5"
	            + " ,:6"
                + " ,:7"
                + " ,:8"
	            + " ,:9"
	            + " ,:10"
	            + " ,:11"
	            + " ,:12"
				+ " ,:"+PlsqlRetValue.RETCODE
				+ " ,:"+PlsqlRetValue.ERRBUF
				+ " ); "
				+ "end;";
		LogUtil.log("UPDATE Emp ID:"+u.getUserId());
		Map<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("1", u.getUserId());
		paramMap.put("2", u.getUserName());
		paramMap.put("3", u.getDescription());
		paramMap.put("4", TypeConvert.u2tDate(u.getStartDate()));
		paramMap.put("5", TypeConvert.u2tDate(u.getEndDate()));
		paramMap.put("6", u.getEncryptedUserPassword());
		paramMap.put("7", u.getRespId());
		paramMap.put("8", u.getEmpId());
		paramMap.put("9", u.getUserType());
		paramMap.put("10", u.getUserGroupId());
		paramMap.put("11", u.getImgUrl());
		paramMap.put("12", TypeConvert.u2tDate(u.getPasswordDate()));
		//测试会话初始化功能
		/*Long sid=this.getDevJdbcTemplate().queryForLong("select USERENV('SESSIONID') from dual");
		Long longId=this.getDevJdbcTemplate().queryForLong("select XYG_ALD_GLOBAL_PKG.login_id from dual");
		System.out.println("DAO sid:"+sid+",longId:"+longId);*/
		return this.getDevJdbcTemplate().executeForRetValue(sql, paramMap);	
	}
	
	public PlsqlRetValue updateImgUrl(String fileName,Long userId) throws Exception{
		String sql = "Declare "
				+	"  l_user_id number; "
				+	"begin "
				+ "  XYG_ALB2B_USER_PKG.UPDATE_USER_IMGURL( "
				+ "  :1"
				+ " ,:2"
				+ ",:"+PlsqlRetValue.RETCODE
				+ ",:"+PlsqlRetValue.ERRBUF
				+ " ); "
				+ "end;";
		LogUtil.log("UPDATE User ID:" + userId);
		Map<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("1", userId);
		paramMap.put("2", fileName);
		return this.getDevJdbcTemplate().executeForRetValue(sql, paramMap);
	}
	
	public PlsqlRetValue updatePWD(Long userId,String oldPassword,String newPassword) throws Exception{
		String sql= "Declare "
				+	"   l_user_id number; "
				+	"begin "
				+ "  XYG_ALB2B_USER_PKG.UPDATE_PWD( "
				+ "  :1"
				+ " ,:2"
				+ " ,:3"
				+ ",:"+PlsqlRetValue.RETCODE
				+ ",:"+PlsqlRetValue.ERRBUF
				+ " ); "
				+ "end;";
		LogUtil.log("UPDATE User ID:" + userId);
		Map<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("1", userId);
		paramMap.put("2", oldPassword);
		paramMap.put("3", newPassword);
		return this.getDevJdbcTemplate().executeForRetValue(sql, paramMap);
	}
	
	public UserVO findByUserName(String userName) throws Exception{
		Map<String,Object> paramMap=new  HashMap<String,Object>();
		String sql = "SELECT * FROM XYG_ALB2B_USER_V WHERE USER_NAME = :1";
		paramMap.put("1", userName);
		return this.getDevJdbcTemplate().queryForObject(sql, paramMap, new UserVO());
	}
	
	public UserVO findByUserId(Long userId) throws Exception{
		Map<String,Object> paramMap=new  HashMap<String,Object>();
		String sql = "SELECT * FROM XYG_ALB2B_USER_V WHERE USER_ID = :1";
		paramMap.put("1", userId);
		return this.getDevJdbcTemplate().queryForObject(sql, paramMap, new UserVO());
	}
	
	public SqlResultSet findByIdForJSON(Long userId) throws Exception{
		Map<String,Object> paramMap=new  HashMap<String,Object>();
		String sql = "SELECT * FROM XYG_ALB2B_USER_V WHERE USER_ID = :1";
		paramMap.put("1", userId);
		return this.getDevJdbcTemplate().queryForResultSet(sql, paramMap);
	}
	
	public SqlResultSet findByUserForRS(Long sendUserId,String userName) throws Exception{
		String sql = "SELECT * FROM XYG_ALB2B_USER WHERE USER_NAME LIKE :1 AND USER_ID <> :2";
		Map<String,Object> paramMap=new  HashMap<String,Object>();
		userName=userName+"%";
		paramMap.put("1", userName);
		paramMap.put("2", sendUserId);
		return this.getDevJdbcTemplate().queryForResultSet(sql, paramMap);
	}
	
	public String findOtherUsers(Long userId) throws Exception{
		String userGroup = null;
		String sql = "SELECT USER_ID FROM XYG_ALB2B_USER WHERE USER_ID <> :1";
		Map<String,Object> paramMap=new  HashMap<String,Object>();
		paramMap.put("1", userId);
		SqlResultSet rs = this.getDevJdbcTemplate().queryForResultSet(sql, paramMap);
		for(int i=0;i<rs.getResultSet().size();i++){
			if(i==0){
				userGroup=(rs.getResultSet().get(i)[0]).toString();
			}else{
				userGroup=userGroup+","+(rs.getResultSet().get(i)[0]).toString();
			}
		}
		return userGroup;
	}
	
}
