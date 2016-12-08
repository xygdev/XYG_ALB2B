package com.xinyiglass.springSample.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.jdbc.core.RowMapper;

@SuppressWarnings("rawtypes")
public class OnhandSetVO implements FactoryBean,RowMapper<OnhandSetVO>, Cloneable {
	   private Long organizationId;
	   private String orgCode;
	   private String orgName;
	   private String glassIndustry;
	   private Long onhandGreaterBox;
	   private Long onhandDisplayBox;
	   private Long createdBy;
	   private java.util.Date creationDate;
	   private Long lastUpdatedBy;
	   private java.util.Date lastUpdateDate;
	   private Long lastUpdateLogin;
	 
	   //GET&SET Method
	   public Long getOrganizationId() {
	      return organizationId;
	   }
	   public void setOrganizationId(Long organizationId) {
	      this.organizationId = organizationId;
	   }
	   public String getOrgCode() {
		   return orgCode;
	   }
	   public void setOrgCode(String orgCode) {
		   this.orgCode = orgCode;
	   }
	   public String getOrgName() {
	      return orgName;
	   }
	   public void setOrgName(String orgName) {
	      this.orgName = orgName;
	   }
	   public String getGlassIndustry() {
	      return glassIndustry;
	   }
	   public void setGlassIndustry(String glassIndustry) {
	      this.glassIndustry = glassIndustry;
	   }
	   public Long getOnhandGreaterBox() {
	      return onhandGreaterBox;
	   }
	   public void setOnhandGreaterBox(Long onhandGreaterBox) {
	      this.onhandGreaterBox = onhandGreaterBox;
	   }
	   public Long getOnhandDisplayBox() {
	      return onhandDisplayBox;
	   }
	   public void setOnhandDisplayBox(Long onhandDisplayBox) {
	      this.onhandDisplayBox = onhandDisplayBox;
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
		   OnhandSetVO osVO = null;  
	       try{  
	           osVO = (OnhandSetVO)super.clone();  
	       }catch(CloneNotSupportedException e) {  
	           e.printStackTrace();  
	       }  
	       return osVO;  
	   } 
	   
	   @Override
	   public OnhandSetVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		   OnhandSetVO os = new OnhandSetVO();
		   os.setOrganizationId(rs.getLong("organization_id"));
		   os.setOrgCode(rs.getString("org_code"));
		   os.setOrgName(rs.getString("org_name"));
		   os.setGlassIndustry(rs.getObject("glass_industry")==null?null:rs.getString("glass_industry"));
		   os.setOnhandGreaterBox(rs.getLong("onhand_greater_box"));
		   os.setOnhandDisplayBox(rs.getLong("onhand_display_box"));
		   os.setCreatedBy(rs.getLong("created_by"));
		   os.setCreationDate(rs.getDate("creation_date"));
		   os.setLastUpdatedBy(rs.getLong("last_updated_by"));
		   os.setLastUpdateDate(rs.getDate("last_update_date"));
		   os.setLastUpdateLogin(rs.getLong("last_update_login"));
	       return os;
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
