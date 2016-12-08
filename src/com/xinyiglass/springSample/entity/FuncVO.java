package com.xinyiglass.springSample.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.jdbc.core.RowMapper;

@SuppressWarnings("rawtypes")
public class FuncVO implements FactoryBean,RowMapper<FuncVO>, Cloneable{
	   private Long functionId;
	   private String functionCode;
	   private String functionName;
	   private String functionHref;
	   private String description;
	   private Long iconId;
	   private String iconCode;
	   private Long createdBy;
	   private java.util.Date creationDate;
	   private Long lastUpdatedBy;
	   private java.util.Date lastUpdateDate;
	   private Long lastUpdateLogin;
	 
	   //GET&SET Method
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
	   public String getFunctionHref() {
	      return functionHref;
	   }
	   public void setFunctionHref(String functionHref) {
	      this.functionHref = functionHref;
	   }
	   public String getDescription() {
	      return description;
	   }
	   public void setDescription(String description) {
	      this.description = description;
	   }
	   public Long getIconId() {
	      return iconId;
	   }
	   public void setIconId(Long iconId) {
	      this.iconId = iconId;
	   }
	   public String getIconCode() {
	      return iconCode;
	   }
	   public void setIconCode(String iconCode) {
	      this.iconCode = iconCode;
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
	       FuncVO funcVO = null;  
	       try{  
	           funcVO = (FuncVO)super.clone();  
	       }catch(CloneNotSupportedException e) {  
	           e.printStackTrace();  
	       }  
	       return funcVO;  
	   } 
	   
	   @Override
	   public FuncVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		   FuncVO func = new FuncVO();
		   func.setFunctionId(rs.getLong("function_id"));
		   func.setFunctionCode(rs.getString("function_code"));
		   func.setFunctionName(rs.getString("function_name"));
		   func.setFunctionHref(rs.getString("function_href"));
		   func.setDescription(rs.getObject("description")==null?null:rs.getString("description"));
		   func.setIconId(rs.getLong("icon_id"));
		   func.setIconCode(rs.getString("icon_code"));
		   func.setCreatedBy(rs.getLong("created_by"));
		   func.setCreationDate(rs.getDate("creation_date"));
		   func.setLastUpdatedBy(rs.getLong("last_updated_by"));
		   func.setLastUpdateDate(rs.getDate("last_update_date"));
		   func.setLastUpdateLogin(rs.getLong("last_update_login"));
		   return func;
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
