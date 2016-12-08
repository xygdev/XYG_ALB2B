package com.xinyiglass.springSample.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.jdbc.core.RowMapper;

@SuppressWarnings("rawtypes")
public class OnhandPermVO implements FactoryBean,RowMapper<OnhandPermVO>, Cloneable {
	   private Long pId;
	   private Long userId;
	   private String userName;
	   private String userDesc;
	   private Long organizationId;
	   private String organizationCode;
	   private String organizationName;
	   private String glassIndustry;
	   private java.util.Date startDate;
	   private java.util.Date endDate;
	   private Long createdBy;
	   private java.util.Date creationDate;
	   private Long lastUpdatedBy;
	   private java.util.Date lastUpdateDate;
	   private Long lastUpdateLogin;
	   private String attributeCategory;
	 
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
	   public Long getOrganizationId() {
	      return organizationId;
	   }
	   public void setOrganizationId(Long organizationId) {
	      this.organizationId = organizationId;
	   }
	   public String getOrganizationCode() {
	      return organizationCode;
	   }
	   public void setOrganizationCode(String organizationCode) {
	      this.organizationCode = organizationCode;
	   }
	   public String getOrganizationName() {
	      return organizationName;
	   }
	   public void setOrganizationName(String organizationName) {
	      this.organizationName = organizationName;
	   }
       public String getGlassIndustry(){
    	   return glassIndustry;
       }
       public void setGlassIndustry(String glassIndustry){
    	   this.glassIndustry = glassIndustry;
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
	   public String getAttributeCategory() {
	      return attributeCategory;
	   }
	   public void setAttributeCategory(String attributeCategory) {
	      this.attributeCategory = attributeCategory;
	   }
	   
	   @Override  
	   public Object clone() {  
	       OnhandPermVO opVO = null;  
	       try{  
	           opVO = (OnhandPermVO)super.clone();  
	       }catch(CloneNotSupportedException e) {  
	           e.printStackTrace();  
	       }  
	       return opVO;  
	   } 
	   
	   @Override
	   public OnhandPermVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		   OnhandPermVO op = new OnhandPermVO();
		   op.setPId(rs.getLong("p_id"));
		   op.setUserId(rs.getLong("user_id"));
		   op.setUserName(rs.getString("user_name"));
		   op.setUserDesc(rs.getObject("user_desc")==null?null:rs.getString("user_desc"));
		   op.setOrganizationId(rs.getLong("organization_id"));
		   op.setOrganizationCode(rs.getString("organization_code"));
		   op.setOrganizationName(rs.getString("organization_name"));
		   op.setGlassIndustry(rs.getString("glass_industry"));
		   op.setStartDate(rs.getDate("start_date"));
		   op.setEndDate(rs.getObject("end_date")==null?null:rs.getDate("end_date"));
		   op.setCreatedBy(rs.getLong("created_by"));
		   op.setCreationDate(rs.getDate("creation_date"));
		   op.setLastUpdatedBy(rs.getLong("last_updated_by"));
		   op.setLastUpdateDate(rs.getDate("last_update_date"));
		   op.setLastUpdateLogin(rs.getLong("last_update_login"));
		   op.setAttributeCategory(rs.getObject("attribute_category")==null?null:rs.getString("attribute_category"));
		   return op;   
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
