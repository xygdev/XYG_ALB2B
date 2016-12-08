package com.xinyiglass.springSample.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.jdbc.core.RowMapper;

@SuppressWarnings("rawtypes")
public class GroupLineVO implements FactoryBean,RowMapper<GroupLineVO>,Cloneable{
	   private Long groupId;
	   private Long groupSequence;
	   private Long subGroupId;
	   private String enabledFlag;
	   private Long createdBy;
	   private java.util.Date creationDate;
	   private Long lastUpdatedBy;
	   private java.util.Date lastUpdateDate;
	   private Long lastUpdateLogin;
	 
	   //GET&SET Method
	   public Long getGroupId() {
	      return groupId;
	   }
	   public void setGroupId(Long groupId) {
	      this.groupId = groupId;
	   }
	   public Long getGroupSequence() {
	      return groupSequence;
	   }
	   public void setGroupSequence(Long groupSequence) {
	      this.groupSequence = groupSequence;
	   }
	   public Long getSubGroupId() {
	      return subGroupId;
	   }
	   public void setSubGroupId(Long subGroupId) {
	      this.subGroupId = subGroupId;
	   }
	   public String getEnabledFlag() {
	      return enabledFlag;
	   }
	   public void setEnabledFlag(String enabledFlag) {
	      this.enabledFlag = enabledFlag;
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
	   
	   public Object clone() {  
	       GroupLineVO groupVO = null;  
	       try{  
	           groupVO = (GroupLineVO)super.clone();  
	       }catch(CloneNotSupportedException e) {  
	           e.printStackTrace();  
	       }  
	       return groupVO;  
	   }
	   
	   
	   @Override
	   public GroupLineVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		   GroupLineVO group = new GroupLineVO();
		   group.setGroupId(rs.getLong("group_id"));
		   group.setGroupSequence(rs.getLong("group_sequence"));
		   group.setSubGroupId(rs.getLong("sub_group_id"));
		   group.setEnabledFlag(rs.getString("enabled_flag"));
		   group.setCreatedBy(rs.getLong("created_by"));
		   group.setCreationDate(rs.getDate("creation_date"));
		   group.setLastUpdatedBy(rs.getLong("last_updated_by"));
		   group.setLastUpdateDate(rs.getDate("last_update_date"));
		   group.setLastUpdateLogin(rs.getLong("last_update_login"));
		   return group;
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
