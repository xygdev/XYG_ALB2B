package com.xinyiglass.springSample.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.jdbc.core.RowMapper;

@SuppressWarnings("rawtypes")
public class GroupHeaderVO implements FactoryBean,RowMapper<GroupHeaderVO>,Cloneable{
	   private Long groupId;
	   private String groupCode;
	   private String groupName;
	   private String description;
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
	   public String getDescription() {
	      return description;
	   }
	   public void setDescription(String description) {
	      this.description = description;
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
	       GroupHeaderVO groupVO = null;  
	       try{  
	           groupVO = (GroupHeaderVO)super.clone();  
	       }catch(CloneNotSupportedException e) {  
	           e.printStackTrace();  
	       }  
	       return groupVO;  
	   } 
	   
	   @Override
	   public GroupHeaderVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		   GroupHeaderVO group = new GroupHeaderVO();
		   group.setGroupId(rs.getLong("group_id"));
		   group.setGroupCode(rs.getString("group_code"));
		   group.setGroupName(rs.getString("group_name"));
		   group.setDescription(rs.getObject("description")==null?null:rs.getString("description"));
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
