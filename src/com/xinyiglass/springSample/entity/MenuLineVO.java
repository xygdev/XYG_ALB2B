package com.xinyiglass.springSample.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.jdbc.core.RowMapper;

@SuppressWarnings("rawtypes")
public class MenuLineVO implements FactoryBean,RowMapper<MenuLineVO>,Cloneable{
	   private Long menuId;
	   private String menuCode;
	   private String menuName;
	   private String menuDesc;
	   private Long menuIconId;
	   private Long menuSequence;
	   private Long subMenuId;
	   private String subMenuCode;
	   private String subMenuName;
	   private String subMenuDesc;
	   private Long subMenuIconId;
	   private Long functionId;
	   private String functionCode;
	   private String functionName;
	   private String functionHref;
	   private Long functionIconId;
	   private String iconCode;
	   private String enabledFlag;
	   private java.util.Date creationDate;
	   private Long createdBy;
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
	   public String getMenuDesc() {
	      return menuDesc;
	   }
	   public void setMenuDesc(String menuDesc) {
	      this.menuDesc = menuDesc;
	   }
	   public Long getMenuIconId() {
	      return menuIconId;
	   }
	   public void setMenuIconId(Long menuIconId) {
	      this.menuIconId = menuIconId;
	   }
	   public Long getMenuSequence() {
	      return menuSequence;
	   }
	   public void setMenuSequence(Long menuSequence) {
	      this.menuSequence = menuSequence;
	   }
	   public Long getSubMenuId() {
	      return subMenuId;
	   }
	   public void setSubMenuId(Long subMenuId) {
	      this.subMenuId = subMenuId;
	   }
	   public String getSubMenuCode() {
	      return subMenuCode;
	   }
	   public void setSubMenuCode(String subMenuCode) {
	      this.subMenuCode = subMenuCode;
	   }
	   public String getSubMenuName() {
	      return subMenuName;
	   }
	   public void setSubMenuName(String subMenuName) {
	      this.subMenuName = subMenuName;
	   }
	   public String getSubMenuDesc() {
	      return subMenuDesc;
	   }
	   public void setSubMenuDesc(String subMenuDesc) {
	      this.subMenuDesc = subMenuDesc;
	   }
	   public Long getSubMenuIconId() {
	      return subMenuIconId;
	   }
	   public void setSubMenuIconId(Long subMenuIconId) {
	      this.subMenuIconId = subMenuIconId;
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
	   public String getFunctionHref() {
	      return functionHref;
	   }
	   public void setFunctionHref(String functionHref) {
	      this.functionHref = functionHref;
	   }
	   public Long getFunctionIconId() {
	      return functionIconId;
	   }
	   public void setFunctionIconId(Long functionIconId) {
	      this.functionIconId = functionIconId;
	   }
	   public String getIconCode() {
	      return iconCode;
	   }
	   public void setIconCode(String iconCode) {
	      this.iconCode = iconCode;
	   }
	   public String getEnabledFlag() {
	      return enabledFlag;
	   }
	   public void setEnabledFlag(String enabledFlag) {
	      this.enabledFlag = enabledFlag;
	   }
	   public java.util.Date getCreationDate() {
	      return creationDate;
	   }
	   public void setCreationDate(java.util.Date creationDate) {
	      this.creationDate = creationDate;
	   }
	   public Long getCreatedBy() {
	      return createdBy;
	   }
	   public void setCreatedBy(Long createdBy) {
	      this.createdBy = createdBy;
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
	       MenuLineVO menuVO = null;  
	       try{  
	           menuVO = (MenuLineVO)super.clone();  
	       }catch(CloneNotSupportedException e) {  
	           e.printStackTrace();  
	       }  
	       return menuVO;  
	   } 
	   
	   @Override
	   public MenuLineVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		   MenuLineVO menu = new MenuLineVO();
		   menu.setMenuId(rs.getLong("menu_id"));
		   menu.setMenuCode(rs.getObject("menu_code")==null?null:rs.getString("menu_code"));
		   menu.setMenuName(rs.getObject("menu_name")==null?null:rs.getString("menu_name"));
		   menu.setMenuDesc(rs.getObject("menu_desc")==null?null:rs.getString("menu_desc"));
		   menu.setMenuIconId(rs.getLong("menu_icon_id"));
		   menu.setMenuSequence(rs.getLong("menu_sequence"));
		   menu.setSubMenuId(rs.getObject("sub_menu_id")==null?null:rs.getLong("sub_menu_id"));
		   menu.setSubMenuCode(rs.getObject("sub_menu_code")==null?null:rs.getString("sub_menu_code"));
		   menu.setSubMenuName(rs.getObject("sub_menu_name")==null?null:rs.getString("sub_menu_name"));
		   menu.setSubMenuDesc(rs.getObject("sub_menu_desc")==null?null:rs.getString("sub_menu_desc"));
		   menu.setSubMenuIconId(rs.getObject("sub_menu_icon_id")==null?null:rs.getLong("sub_menu_icon_id"));
		   menu.setFunctionId(rs.getObject("function_id")==null?null:rs.getLong("function_id"));
		   menu.setFunctionCode(rs.getObject("function_code")==null?null:rs.getString("function_code"));
		   menu.setFunctionName(rs.getObject("function_name")==null?null:rs.getString("function_name"));
		   menu.setFunctionHref(rs.getObject("function_href")==null?null:rs.getString("function_href"));
		   menu.setFunctionIconId(rs.getObject("function_icon_id")==null?null:rs.getLong("function_icon_id"));
		   menu.setIconCode(rs.getString("icon_code"));
		   menu.setEnabledFlag(rs.getString("enabled_flag"));
		   menu.setCreationDate(rs.getDate("creation_date"));
		   menu.setCreatedBy(rs.getLong("created_by"));
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
