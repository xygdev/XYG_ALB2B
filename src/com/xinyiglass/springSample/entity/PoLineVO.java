package com.xinyiglass.springSample.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.jdbc.core.RowMapper;

@SuppressWarnings("rawtypes")
public class PoLineVO implements FactoryBean,RowMapper<PoLineVO>,Cloneable{
	   private Long poLineId;
	   private Long poHeaderId;
	   private Long shipFromOrgId;
	   private String organizationName;
	   private Long lineNum;
	   private Long thickness;
	   private String coatingType;
	   private Long inventoryItemId;
	   private String itemDesc;
	   private Long width;
	   private Long height;
	   private Long widthEn;
	   private Long heightEn;
	   private Long pieQuantity;
	   private Long sqm;
	   private Double sqmUnitPrice;
	   private Long pieUnitPrice;
	   private Long amount;
	   private String status;
	   private String statusDesc;
	   private String remark;
	   private Long createdBy;
	   private java.util.Date creationDate;
	   private Long lastUpdatedBy;
	   private java.util.Date lastUpdateDate;
	   private Long lastUpdateLogin;
	 
	   //GET&SET Method
	   public Long getPoLineId() {
	      return poLineId;
	   }
	   public void setPoLineId(Long poLineId) {
	      this.poLineId = poLineId;
	   }
	   public Long getPoHeaderId() {
	      return poHeaderId;
	   }
	   public void setPoHeaderId(Long poHeaderId) {
	      this.poHeaderId = poHeaderId;
	   }
	   public Long getShipFromOrgId() {
	      return shipFromOrgId;
	   }
	   public void setShipFromOrgId(Long shipFromOrgId) {
	      this.shipFromOrgId = shipFromOrgId;
	   }
	   public String getOrganizationName() {
	      return organizationName;
	   }
	   public void setOrganizationName(String organizationName) {
	      this.organizationName = organizationName;
	   }
	   public Long getLineNum() {
	      return lineNum;
	   }
	   public void setLineNum(Long lineNum) {
	      this.lineNum = lineNum;
	   }
	   public Long getThickness() {
	      return thickness;
	   }
	   public void setThickness(Long thickness) {
	      this.thickness = thickness;
	   }
	   public String getCoatingType() {
	      return coatingType;
	   }
	   public void setCoatingType(String coatingType) {
	      this.coatingType = coatingType;
	   }
	   public Long getInventoryItemId() {
	      return inventoryItemId;
	   }
	   public void setInventoryItemId(Long inventoryItemId) {
	      this.inventoryItemId = inventoryItemId;
	   }
	   public String getItemDesc() {
	      return itemDesc;
	   }
	   public void setItemDesc(String itemDesc) {
	      this.itemDesc = itemDesc;
	   }
	   public Long getWidth() {
	      return width;
	   }
	   public void setWidth(Long width) {
	      this.width = width;
	   }
	   public Long getHeight() {
	      return height;
	   }
	   public void setHeight(Long height) {
	      this.height = height;
	   }
	   public Long getWidthEn() {
	      return widthEn;
	   }
	   public void setWidthEn(Long widthEn) {
	      this.widthEn = widthEn;
	   }
	   public Long getHeightEn() {
	      return heightEn;
	   }
	   public void setHeightEn(Long heightEn) {
	      this.heightEn = heightEn;
	   }
	   public Long getPieQuantity() {
	      return pieQuantity;
	   }
	   public void setPieQuantity(Long pieQuantity) {
	      this.pieQuantity = pieQuantity;
	   }
	   public Long getSqm() {
	      return sqm;
	   }
	   public void setSqm(Long sqm) {
	      this.sqm = sqm;
	   }
	   public Double getSqmUnitPrice() {
	      return sqmUnitPrice;
	   }
	   public void setSqmUnitPrice(Double sqmUnitPrice) {
	      this.sqmUnitPrice = sqmUnitPrice;
	   }
	   public Long getPieUnitPrice() {
	      return pieUnitPrice;
	   }
	   public void setPieUnitPrice(Long pieUnitPrice) {
	      this.pieUnitPrice = pieUnitPrice;
	   }
	   public Long getAmount() {
	      return amount;
	   }
	   public void setAmount(Long amount) {
	      this.amount = amount;
	   }
	   public String getStatus() {
	      return status;
	   }
	   public void setStatus(String status) {
	      this.status = status;
	   }
	   public String getStatusDesc() {
	      return statusDesc;
	   }
	   public void setStatusDesc(String statusDesc) {
	      this.statusDesc = statusDesc;
	   }
	   public String getRemark() {
	      return remark;
	   }
	   public void setRemark(String remark) {
	      this.remark = remark;
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
	       PoLineVO poLineVO = null;  
	       try{  
	    	   poLineVO = (PoLineVO)super.clone();  
	       }catch(CloneNotSupportedException e) {  
	           e.printStackTrace();  
	       }  
	       return poLineVO;  
	   } 
	   
	   @Override
	   public PoLineVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		   PoLineVO lpl = new PoLineVO();
		   lpl.setPoLineId(rs.getLong("po_line_id"));
		   lpl.setPoHeaderId(rs.getLong("po_header_id"));
		   lpl.setShipFromOrgId(rs.getLong("ship_from_org_id"));
		   lpl.setOrganizationName(rs.getString("organization_name"));
		   lpl.setLineNum(rs.getLong("line_num"));
		   lpl.setThickness(rs.getLong("thickness"));
		   lpl.setCoatingType(rs.getString("coating_type"));
		   lpl.setInventoryItemId(rs.getLong("inventory_item_id"));
		   lpl.setItemDesc(rs.getObject("item_desc")==null?null:rs.getString("item_desc"));
		   lpl.setWidth(rs.getLong("width"));
		   lpl.setHeight(rs.getLong("height"));
		   lpl.setWidthEn(rs.getObject("width_en")==null?null:rs.getLong("width_en"));
		   lpl.setHeightEn(rs.getObject("height_en")==null?null:rs.getLong("height_en"));
		   lpl.setPieQuantity(rs.getLong("pie_quantity"));
		   lpl.setSqm(rs.getObject("sqm")==null?null:rs.getLong("sqm"));
		   lpl.setSqmUnitPrice(rs.getObject("sqm_unit_price")==null?null:rs.getDouble("sqm_unit_price"));
		   lpl.setPieUnitPrice(rs.getObject("pie_unit_price")==null?null:rs.getLong("pie_unit_price"));
		   lpl.setAmount(rs.getObject("amount")==null?null:rs.getLong("amount"));
		   lpl.setStatus(rs.getString("status"));
		   lpl.setStatusDesc(rs.getObject("status_desc")==null?null:rs.getString("status_desc"));
		   lpl.setRemark(rs.getObject("remark")==null?null:rs.getString("remark"));
		   lpl.setCreatedBy(rs.getLong("created_by"));
		   lpl.setCreationDate(rs.getDate("creation_date"));
		   lpl.setLastUpdatedBy(rs.getLong("last_updated_by"));
		   lpl.setLastUpdateDate(rs.getDate("last_update_date"));
		   lpl.setLastUpdateLogin(rs.getLong("last_update_login"));
		   return lpl;
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
