package com.xinyiglass.springSample.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.jdbc.core.RowMapper;

@SuppressWarnings("rawtypes")
public class RespVO implements FactoryBean,RowMapper<RespVO>, Cloneable {
	   private Long respId;
	   private String respCode;
	   private String respName;
	   private String description;
	   private Long menuId;
	   private String menuCode;
	   private String menuName;
	   private String menuDesc;
	   private java.util.Date startDate;
	   private java.util.Date endDate;
	   private Long createdBy;
	   private java.util.Date creationDate;
	   private Long lastUpdatedBy;
	   private java.util.Date lastUpdateDate;
	   private Long lastUpdateLogin;
	   private String attributeCategory;
	 
	   //GET&SET Method
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
	   public String getDescription() {
	      return description;
	   }
	   public void setDescription(String description) {
	      this.description = description;
	   }
	   public Long getMenuId() {
	      return menuId;
	   }
	   public void setMenuId(Long menuId) {
	      this.menuId = menuId;
	   }
	   public String getMenuCode() {
	      return menuCode;
	   }
	   public void setMenuCode(String menuCode) {
	      this.menuCode = menuCode;
	   }
	   public String getMenuName() {
	      return menuName;
	   }
	   public void setMenuName(String menuName) {
	      this.menuName = menuName;
	   }
	   public String getMenuDesc() {
	      return menuDesc;
	   }
	   public void setMenuDesc(String menuDesc) {
	      this.menuDesc = menuDesc;
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
	       RespVO respVO = null;  
	       try{  
	           respVO = (RespVO)super.clone();  
	       }catch(CloneNotSupportedException e) {  
	           e.printStackTrace();  
	       }  
	       return respVO;  
	   } 
	   
	   @Override
	   public RespVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		   RespVO resp = new RespVO();
		   resp.setRespId(rs.getLong("resp_id"));
		   resp.setRespCode(rs.getString("resp_code"));
		   resp.setRespName(rs.getString("resp_name"));
		   resp.setDescription(rs.getObject("description")==null?null:rs.getString("description"));
		   resp.setMenuId(rs.getLong("menu_id"));
		   resp.setMenuCode(rs.getObject("menu_code")==null?null:rs.getString("menu_code"));
		   resp.setMenuName(rs.getObject("menu_name")==null?null:rs.getString("menu_name"));
		   resp.setMenuDesc(rs.getObject("menu_desc")==null?null:rs.getString("menu_desc"));
		   resp.setStartDate(rs.getDate("start_date"));
		   resp.setEndDate(rs.getObject("end_date")==null?null:rs.getDate("end_date"));
		   resp.setCreatedBy(rs.getLong("created_by"));
		   resp.setCreationDate(rs.getDate("creation_date"));
		   resp.setLastUpdatedBy(rs.getLong("last_updated_by"));
		   resp.setLastUpdateDate(rs.getDate("last_update_date"));
		   resp.setLastUpdateLogin(rs.getLong("last_update_login"));
		   resp.setAttributeCategory(rs.getObject("attribute_category")==null?null:rs.getString("attribute_category"));
	       return resp;
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
