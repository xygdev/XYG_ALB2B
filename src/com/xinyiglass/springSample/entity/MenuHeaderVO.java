package com.xinyiglass.springSample.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.jdbc.core.RowMapper;

@SuppressWarnings("rawtypes")
public class MenuHeaderVO implements FactoryBean,RowMapper<MenuHeaderVO>,Cloneable{
	   private Long menuId;
	   private String menuCode;
	   private String menuName;
	   private String description;
	   private Long menuIconId;
	   private String iconCode;
	   private String iconSource;
	   private String iconDesc;
	   private Long createdBy;
	   private java.util.Date creationDate;
	   private Long lastUpdatedBy;
	   private java.util.Date lastUpdateDate;
	   private Long lastUpdateLogin;
	 
	   //GET&SET Method
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
	   public String getDescription() {
	      return description;
	   }
	   public void setDescription(String description) {
	      this.description = description;
	   }
	   public Long getMenuIconId() {
	      return menuIconId;
	   }
	   public void setMenuIconId(Long menuIconId) {
	      this.menuIconId = menuIconId;
	   }
	   public String getIconCode() {
	      return iconCode;
	   }
	   public void setIconCode(String iconCode) {
	      this.iconCode = iconCode;
	   }
	   public String getIconSource() {
	      return iconSource;
	   }
	   public void setIconSource(String iconSource) {
	      this.iconSource = iconSource;
	   }
	   public String getIconDesc() {
	      return iconDesc;
	   }
	   public void setIconDesc(String iconDesc) {
	      this.iconDesc = iconDesc;
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
	       MenuHeaderVO menuVO = null;  
	       try{  
	           menuVO = (MenuHeaderVO)super.clone();  
	       }catch(CloneNotSupportedException e) {  
	           e.printStackTrace();  
	       }  
	       return menuVO;  
	   } 
	   
	   @Override
	   public MenuHeaderVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		   MenuHeaderVO menu = new MenuHeaderVO();
		   menu.setMenuId(rs.getLong("menu_id"));
		   menu.setMenuCode(rs.getObject("menu_code")==null?null:rs.getString("menu_code"));
		   menu.setMenuName(rs.getObject("menu_name")==null?null:rs.getString("menu_name"));
		   menu.setDescription(rs.getObject("description")==null?null:rs.getString("description"));
		   menu.setMenuIconId(rs.getLong("menu_icon_id"));
		   menu.setIconCode(rs.getString("icon_code"));
		   menu.setIconSource(rs.getString("icon_source"));
		   menu.setIconDesc(rs.getObject("icon_desc")==null?null:rs.getString("icon_desc"));
		   menu.setCreatedBy(rs.getLong("created_by"));
		   menu.setCreationDate(rs.getDate("creation_date"));
		   menu.setLastUpdatedBy(rs.getLong("last_updated_by"));
		   menu.setLastUpdateDate(rs.getDate("last_update_date"));
		   menu.setLastUpdateLogin(rs.getLong("last_update_login"));
		   return menu;
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
