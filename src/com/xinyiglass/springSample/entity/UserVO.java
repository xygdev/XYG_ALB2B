package com.xinyiglass.springSample.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.jdbc.core.RowMapper;

@SuppressWarnings("rawtypes")
public class UserVO implements FactoryBean,RowMapper<UserVO>, Cloneable {
	   private Long userId;
	   private String userName;
	   private String description;
	   private java.util.Date startDate;
	   private java.util.Date endDate;
	   private String encryptedUserPassword;
	   private java.util.Date passwordDate;
	   private Long respId;
	   private String respCode;
	   private String respName;
	   private String userType;
	   private Long empId;
	   private String empName;
	   private String empNumber;
	   private Long userGroupId;
	   private String groupCode;
	   private String groupName;
	   private String imgUrl;
	   private Long createdBy;
	   private java.util.Date creationDate;
	   private Long lastUpdatedBy;
	   private java.util.Date lastUpdateDate;
	   private Long lastUpdateLogin;
	 
	   //GET&SET Method
	   public Long getUserId() {
	      return userId;
	   }
	   public void setUserId(Long userId) {
	      this.userId = userId;
	   }
	   public String getUserName() {
	      return userName;
	   }
	   public void setUserName(String userName) {
	      this.userName = userName;
	   }
	   public String getDescription() {
	      return description;
	   }
	   public void setDescription(String description) {
	      this.description = description;
	   }
	   public java.util.Date getStartDate() {
	      return startDate;
	   }
	   public void setStartDate(java.util.Date startDate) {
	      this.startDate = startDate;
	   }
	   public java.util.Date getEndDate() {
	      return endDate;
	   }
	   public void setEndDate(java.util.Date endDate) {
	      this.endDate = endDate;
	   }
	   public String getEncryptedUserPassword() {
	      return encryptedUserPassword;
	   }
	   public void setEncryptedUserPassword(String encryptedUserPassword) {
	      this.encryptedUserPassword = encryptedUserPassword;
	   }
	   public java.util.Date getPasswordDate() {
		   return passwordDate;
	   }
	   public void setPasswordDate(java.util.Date passwordDate){
		   this.passwordDate = passwordDate;
	   }
	   public Long getRespId() {
	      return respId;
	   }
	   public void setRespId(Long respId) {
	      this.respId = respId;
	   }
	   public String getRespCode() {
	      return respCode;
	   }
	   public void setRespCode(String respCode) {
	      this.respCode = respCode;
	   }
	   public String getRespName() {
	      return respName;
	   }
	   public void setRespName(String respName) {
	      this.respName = respName;
	   }
	   public String getUserType() {
	      return userType;
	   }
	   public void setUserType(String userType) {
	      this.userType = userType;
	   }
	   public Long getEmpId() {
	      return empId;
	   }
	   public void setEmpId(Long empId) {
	      this.empId = empId;
	   }
	   public String getEmpName() {
	      return empName;
	   }
	   public void setEmpName(String empName) {
	      this.empName = empName;
	   }
	   public String getEmpNumber() {
	      return empNumber;
	   }
	   public void setEmpNumber(String empNumber) {
	      this.empNumber = empNumber;
	   }
	   public Long getUserGroupId() {
	      return userGroupId;
	   }
	   public void setUserGroupId(Long userGroupId) {
	      this.userGroupId = userGroupId;
	   }
	   public String getGroupCode() {
	      return groupCode;
	   }
	   public void setGroupCode(String groupCode) {
	      this.groupCode = groupCode;
	   }
	   public String getGroupName() {
	      return groupName;
	   }
	   public void setGroupName(String groupName) {
	      this.groupName = groupName;
	   }
	   public String getImgUrl() {
	      return imgUrl;
	   }
	   public void setImgUrl(String imgUrl) {
	      this.imgUrl = imgUrl;
	   }
	   public Long getCreatedBy() {
	      return createdBy;
	   }
	   public void setCreatedBy(Long createdBy) {
	      this.createdBy = createdBy;
	   }
	   public java.util.Date getCreationDate() {
	      return creationDate;
	   }
	   public void setCreationDate(java.util.Date creationDate) {
	      this.creationDate = creationDate;
	   }
	   public Long getLastUpdatedBy() {
	      return lastUpdatedBy;
	   }
	   public void setLastUpdatedBy(Long lastUpdatedBy) {
	      this.lastUpdatedBy = lastUpdatedBy;
	   }
	   public java.util.Date getLastUpdateDate() {
	      return lastUpdateDate;
	   }
	   public void setLastUpdateDate(java.util.Date lastUpdateDate) {
	      this.lastUpdateDate = lastUpdateDate;
	   }
	   public Long getLastUpdateLogin() {
	      return lastUpdateLogin;
	   }
	   public void setLastUpdateLogin(Long lastUpdateLogin) {
	      this.lastUpdateLogin = lastUpdateLogin;
	   }
	   
	   @Override  
	    public Object clone() {  
	        UserVO userVO = null;  
	        try{  
	        	userVO = (UserVO)super.clone();  
	        }catch(CloneNotSupportedException e) {  
	            e.printStackTrace();  
	        }  
	        return userVO;  
	    } 
	   
	   @Override
	   public UserVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		   UserVO user = new UserVO();
		   user.setUserId(rs.getLong("user_id"));
		   user.setUserName(rs.getString("user_name"));
		   user.setDescription(rs.getObject("description")==null?null:rs.getString("description"));
		   user.setStartDate(rs.getDate("start_date"));
		   user.setEndDate(rs.getObject("end_date")==null?null:rs.getDate("end_date"));
		   user.setEncryptedUserPassword(rs.getString("encrypted_user_password"));
		   user.setPasswordDate(rs.getObject("password_date")==null?null:rs.getDate("password_date"));
		   user.setRespId(rs.getLong("resp_id"));
		   user.setRespCode(rs.getString("resp_code"));
		   user.setRespName(rs.getString("resp_name"));
		   user.setUserType(rs.getString("user_type"));
		   user.setEmpId(rs.getObject("emp_id")==null?null:rs.getLong("emp_id"));
		   user.setEmpName(rs.getObject("emp_name")==null?null:rs.getString("emp_name"));
		   user.setEmpNumber(rs.getObject("emp_number")==null?null:rs.getString("emp_number"));
		   user.setUserGroupId(rs.getObject("user_group_id")==null?null:rs.getLong("user_group_id"));
		   user.setGroupCode(rs.getObject("group_code")==null?null:rs.getString("group_code"));
		   user.setGroupName(rs.getObject("group_name")==null?null:rs.getString("group_name"));
		   user.setImgUrl(rs.getObject("img_url")==null?null:rs.getString("img_url"));
		   user.setCreatedBy(rs.getLong("created_by"));
		   user.setCreationDate(rs.getDate("creation_date"));
		   user.setLastUpdatedBy(rs.getLong("last_updated_by"));
		   user.setLastUpdateDate(rs.getDate("last_update_date"));
		   user.setLastUpdateLogin(rs.getLong("last_update_login"));
	       return user;
	   }
	   @Override
		public Object getObject() throws Exception {
			// TODO Auto-generated method stub
			return null;
		}
		@Override
		public Class getObjectType() {
			// TODO Auto-generated method stub
			return null;
		}
		@Override
		public boolean isSingleton() {
			// TODO Auto-generated method stub
			return false;
		}
	
}
