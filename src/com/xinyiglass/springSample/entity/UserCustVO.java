package com.xinyiglass.springSample.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.jdbc.core.RowMapper;

@SuppressWarnings("rawtypes")
public class UserCustVO implements FactoryBean,RowMapper<UserCustVO>, Cloneable{
	   private Long userCustId;
	   private Long userId;
	   private Long orgId;
	   private String orgName;
	   private Long customerId;
	   private String partyNumber;
	   private String partyName;
	   private String accountNumber;
	   private String accountName;
	   private java.util.Date startDate;
	   private java.util.Date endDate;
	   private Long createdBy;
	   private java.util.Date creationDate;
	   private Long lastUpdatedBy;
	   private java.util.Date lastUpdateDate;
	   private Long lastUpdateLogin;
	 
	   //GET&SET Method
	   public Long getUserCustId() {
	      return userCustId;
	   }
	   public void setUserCustId(Long userCustId) {
	      this.userCustId = userCustId;
	   }
	   public Long getUserId() {
	      return userId;
	   }
	   public void setUserId(Long userId) {
	      this.userId = userId;
	   }
	   public Long getOrgId() {
	      return orgId;
	   }
	   public void setOrgId(Long orgId) {
	      this.orgId = orgId;
	   }
	   public String getOrgName() {
	      return orgName;
	   }
	   public void setOrgName(String orgName) {
	      this.orgName = orgName;
	   }
	   public Long getCustomerId() {
	      return customerId;
	   }
	   public void setCustomerId(Long customerId) {
	      this.customerId = customerId;
	   }
	   public String getPartyNumber() {
	      return partyNumber;
	   }
	   public void setPartyNumber(String partyNumber) {
	      this.partyNumber = partyNumber;
	   }
	   public String getPartyName() {
	      return partyName;
	   }
	   public void setPartyName(String partyName) {
	      this.partyName = partyName;
	   }
	   public String getAccountNumber() {
	      return accountNumber;
	   }
	   public void setAccountNumber(String accountNumber) {
	      this.accountNumber = accountNumber;
	   }
	   public String getAccountName() {
	      return accountName;
	   }
	   public void setAccountName(String accountName) {
	      this.accountName = accountName;
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
	       UserCustVO ucVO = null;  
	       try{  
	           ucVO = (UserCustVO)super.clone();  
	       }catch(CloneNotSupportedException e) {  
	           e.printStackTrace();  
	       }  
	       return ucVO;  
	   } 
	   
	   @Override
	   public UserCustVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		   UserCustVO uc = new UserCustVO();
		   uc.setUserCustId(rs.getLong("user_cust_id"));
		   uc.setUserId(rs.getLong("user_id"));
		   uc.setOrgId(rs.getLong("org_id"));
		   uc.setOrgName(rs.getString("org_name"));
		   uc.setCustomerId(rs.getLong("customer_id"));
		   uc.setPartyNumber(rs.getString("party_number"));
		   uc.setPartyName(rs.getString("party_name"));
		   uc.setAccountNumber(rs.getString("account_number"));
		   uc.setAccountName(rs.getObject("account_name")==null?null:rs.getString("account_name"));
		   uc.setStartDate(rs.getDate("start_date"));
		   uc.setEndDate(rs.getObject("end_date")==null?null:rs.getDate("end_date"));
		   uc.setCreatedBy(rs.getLong("created_by"));
		   uc.setCreationDate(rs.getDate("creation_date"));
		   uc.setLastUpdatedBy(rs.getLong("last_updated_by"));
		   uc.setLastUpdateDate(rs.getDate("last_update_date"));
		   uc.setLastUpdateLogin(rs.getLong("last_update_login"));
		   return uc;
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
