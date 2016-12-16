package com.xinyiglass.springSample.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.jdbc.core.RowMapper;

@SuppressWarnings("rawtypes")
public class FuncPermVO implements FactoryBean,RowMapper<FuncPermVO>, Cloneable {
	   private Long pId;
	   private Long userId;
	   private String userName;
	   private String userDesc;
	   private Long functionId;
	   private String functionCode;
	   private String functionName;
	   private String functionDesc;
	   private String insertFlag;
	   private String updateFlag;
	   private String approveFlag;
	   private String finalApproveFlag;
	   private String deleteFlag;
	   private java.util.Date startDate;
	   private java.util.Date endDate;
	   private Long createdBy;
	   private java.util.Date creationDate;
	   private Long lastUpdatedBy;
	   private java.util.Date lastUpdateDate;
	   private Long lastUpdateLogin;
	 
	   //GET&SET Method
	   public Long getPId() {
	      return pId;
	   }
	   public void setPId(Long pId) {
	      this.pId = pId;
	   }
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
	   public String getUserDesc() {
	      return userDesc;
	   }
	   public void setUserDesc(String userDesc) {
	      this.userDesc = userDesc;
	   }
	   public Long getFunctionId() {
	      return functionId;
	   }
	   public void setFunctionId(Long functionId) {
	      this.functionId = functionId;
	   }
	   public String getFunctionCode() {
	      return functionCode;
	   }
	   public void setFunctionCode(String functionCode) {
	      this.functionCode = functionCode;
	   }
	   public String getFunctionName() {
	      return functionName;
	   }
	   public void setFunctionName(String functionName) {
	      this.functionName = functionName;
	   }
	   public String getFunctionDesc() {
	      return functionDesc;
	   }
	   public void setFunctionDesc(String functionDesc) {
	      this.functionDesc = functionDesc;
	   }
	   public String getInsertFlag() {
	      return insertFlag;
	   }
	   public void setInsertFlag(String insertFlag) {
	      this.insertFlag = insertFlag;
	   }
	   public String getUpdateFlag() {
	      return updateFlag;
	   }
	   public void setUpdateFlag(String updateFlag) {
	      this.updateFlag = updateFlag;
	   }
	   public String getApproveFlag() {
	      return approveFlag;
	   }
	   public void setApproveFlag(String approveFlag) {
	      this.approveFlag = approveFlag;
	   }
	   public String getFinalApproveFlag() {
	      return finalApproveFlag;
	   }
	   public void setFinalApproveFlag(String finalApproveFlag) {
	      this.finalApproveFlag = finalApproveFlag;
	   }
	   public String getDeleteFlag() {
	      return deleteFlag;
	   }
	   public void setDeleteFlag(String deleteFlag) {
	      this.deleteFlag = deleteFlag;
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
	       FuncPermVO fpVO = null;  
	       try{  
	           fpVO = (FuncPermVO)super.clone();  
	       }catch(CloneNotSupportedException e) {  
	           e.printStackTrace();  
	       }  
	       return fpVO;  
	   }
	   
	   @Override
	   public FuncPermVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		   FuncPermVO fp = new FuncPermVO();
		   fp.setPId(rs.getLong("p_id"));
		   fp.setUserId(rs.getLong("user_id"));
		   fp.setUserName(rs.getString("user_name"));
		   fp.setUserDesc(rs.getObject("user_desc")==null?null:rs.getString("user_desc"));
		   fp.setFunctionId(rs.getLong("function_id"));
		   fp.setFunctionCode(rs.getString("function_code"));
		   fp.setFunctionName(rs.getString("function_name"));
		   fp.setFunctionDesc(rs.getObject("function_desc")==null?null:rs.getString("function_desc"));
		   fp.setInsertFlag(rs.getString("insert_flag"));
		   fp.setUpdateFlag(rs.getString("update_flag"));
		   fp.setApproveFlag(rs.getString("approve_flag"));
		   fp.setFinalApproveFlag(rs.getString("final_approve_flag"));
		   fp.setDeleteFlag(rs.getString("delete_flag"));
		   fp.setStartDate(rs.getDate("start_date"));
		   fp.setEndDate(rs.getObject("end_date")==null?null:rs.getDate("end_date"));
		   fp.setCreatedBy(rs.getLong("created_by"));
		   fp.setCreationDate(rs.getDate("creation_date"));
		   fp.setLastUpdatedBy(rs.getLong("last_updated_by"));
		   fp.setLastUpdateDate(rs.getDate("last_update_date"));
		   fp.setLastUpdateLogin(rs.getLong("last_update_login"));
		   return fp;
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
