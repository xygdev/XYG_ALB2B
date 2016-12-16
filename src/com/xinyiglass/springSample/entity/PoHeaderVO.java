package com.xinyiglass.springSample.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.jdbc.core.RowMapper;

@SuppressWarnings("rawtypes")
public class PoHeaderVO implements FactoryBean,RowMapper<PoHeaderVO>,Cloneable{
	   private Long poHeaderId;
	   private String poNumber;
	   private String customerContractNumber;
	   private String currCode;
	   private Long customerId;
	   private String partyName;
	   private String accountNumber;
	   private Long orgId;
	   private Long salesOrgId;
	   private String salesOrgName;
	   private Long shipFromOrgId;
	   private String organizationName;
	   private String status;
	   private String statusDesc;
	   private Long bookId;
	   private String bookName;
	   private java.util.Date bookDate;
	   private Long checkId;
	   private String checkName;
	   private java.util.Date checkDate;
	   private Long approveId;
	   private String approveName;
	   private java.util.Date approvalDate;
	   private Long cancelId;
	   private String cancelName;
	   private java.util.Date cancelDate;
	   private String remark;
	   private Long createdBy;
	   private java.util.Date creationDate;
	   private Long lastUpdatedBy;
	   private java.util.Date lastUpdateDate;
	   private Long lastUpdateLogin;
	 
	   //GET&SET Method
	   public Long getPoHeaderId() {
	      return poHeaderId;
	   }
	   public void setPoHeaderId(Long poHeaderId) {
	      this.poHeaderId = poHeaderId;
	   }
	   public String getPoNumber() {
	      return poNumber;
	   }
	   public void setPoNumber(String poNumber) {
	      this.poNumber = poNumber;
	   }
	   public String getCustomerContractNumber() {
	      return customerContractNumber;
	   }
	   public void setCustomerContractNumber(String customerContractNumber) {
	      this.customerContractNumber = customerContractNumber;
	   }
	   public String getCurrCode() {
	      return currCode;
	   }
	   public void setCurrCode(String currCode) {
	      this.currCode = currCode;
	   }
	   public Long getCustomerId() {
	      return customerId;
	   }
	   public void setCustomerId(Long customerId) {
	      this.customerId = customerId;
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
	   public Long getOrgId() {
	      return orgId;
	   }
	   public void setOrgId(Long orgId) {
	      this.orgId = orgId;
	   }
	   public Long getSalesOrgId() {
	      return salesOrgId;
	   }
	   public void setSalesOrgId(Long salesOrgId) {
	      this.salesOrgId = salesOrgId;
	   }
	   public String getSalesOrgName() {
	      return salesOrgName;
	   }
	   public void setSalesOrgName(String salesOrgName) {
	      this.salesOrgName = salesOrgName;
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
	   public Long getBookId() {
	      return bookId;
	   }
	   public void setBookId(Long bookId) {
	      this.bookId = bookId;
	   }
	   public String getBookName() {
	      return bookName;
	   }
	   public void setBookName(String bookName) {
	      this.bookName = bookName;
	   }
	   public java.util.Date getBookDate() {
	      return bookDate;
	   }
	   public void setBookDate(java.util.Date bookDate) {
	      this.bookDate = bookDate;
	   }
	   public Long getCheckId() {
	      return checkId;
	   }
	   public void setCheckId(Long checkId) {
	      this.checkId = checkId;
	   }
	   public String getCheckName() {
	      return checkName;
	   }
	   public void setCheckName(String checkName) {
	      this.checkName = checkName;
	   }
	   public java.util.Date getCheckDate() {
	      return checkDate;
	   }
	   public void setCheckDate(java.util.Date checkDate) {
	      this.checkDate = checkDate;
	   }
	   public Long getApproveId() {
	      return approveId;
	   }
	   public void setApproveId(Long approveId) {
	      this.approveId = approveId;
	   }
	   public String getApproveName() {
	      return approveName;
	   }
	   public void setApproveName(String approveName) {
	      this.approveName = approveName;
	   }
	   public java.util.Date getApprovalDate() {
	      return approvalDate;
	   }
	   public void setApprovalDate(java.util.Date approvalDate) {
	      this.approvalDate = approvalDate;
	   }
	   public Long getCancelId() {
	      return cancelId;
	   }
	   public void setCancelId(Long cancelId) {
	      this.cancelId = cancelId;
	   }
	   public String getCancelName() {
	      return cancelName;
	   }
	   public void setCancelName(String cancelName) {
	      this.cancelName = cancelName;
	   }
	   public java.util.Date getCancelDate() {
	      return cancelDate;
	   }
	   public void setCancelDate(java.util.Date cancelDate) {
	      this.cancelDate = cancelDate;
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
	       PoHeaderVO poHeaderVO = null;  
	       try{  
	    	   poHeaderVO = (PoHeaderVO)super.clone();  
	       }catch(CloneNotSupportedException e) {  
	           e.printStackTrace();  
	       }  
	       return poHeaderVO;  
	   } 
	   
	   @Override
	   public PoHeaderVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		   PoHeaderVO lph = new PoHeaderVO();
		   lph.setPoHeaderId(rs.getLong("po_header_id"));
		   lph.setPoNumber(rs.getString("po_number"));
		   lph.setCustomerContractNumber(rs.getObject("customer_contract_number")==null?null:rs.getString("customer_contract_number"));
		   lph.setCurrCode(rs.getString("curr_code"));
		   lph.setCustomerId(rs.getLong("customer_id"));
		   lph.setPartyName(rs.getString("party_name"));
		   lph.setAccountNumber(rs.getObject("account_number")==null?null:rs.getString("account_number"));
		   lph.setOrgId(rs.getObject("org_id")==null?null:rs.getLong("org_id"));
		   lph.setSalesOrgId(rs.getLong("sales_org_id"));
		   lph.setSalesOrgName(rs.getString("sales_org_name"));
		   lph.setShipFromOrgId(rs.getObject("ship_from_org_id")==null?null:rs.getLong("ship_from_org_id"));
		   lph.setOrganizationName(rs.getString("organization_name"));
		   lph.setStatus(rs.getString("status"));
		   lph.setStatusDesc(rs.getObject("status_desc")==null?null:rs.getString("status_desc"));
		   lph.setBookId(rs.getObject("book_id")==null?null:rs.getLong("book_id"));
		   lph.setBookName(rs.getObject("book_name")==null?null:rs.getString("book_name"));
		   lph.setBookDate(rs.getObject("book_date")==null?null:rs.getDate("book_date"));
		   lph.setCheckId(rs.getObject("check_id")==null?null:rs.getLong("check_id"));
		   lph.setCheckName(rs.getObject("check_name")==null?null:rs.getString("check_name"));
		   lph.setCheckDate(rs.getObject("check_date")==null?null:rs.getDate("check_date"));
		   lph.setApproveId(rs.getObject("approve_id")==null?null:rs.getLong("approve_id"));
		   lph.setApproveName(rs.getObject("approve_name")==null?null:rs.getString("approve_name"));
		   lph.setApprovalDate(rs.getObject("approval_date")==null?null:rs.getDate("approval_date"));
		   lph.setCancelId(rs.getObject("cancel_id")==null?null:rs.getLong("cancel_id"));
		   lph.setCancelName(rs.getObject("cancel_name")==null?null:rs.getString("cancel_name"));
		   lph.setCancelDate(rs.getObject("cancel_date")==null?null:rs.getDate("cancel_date"));
		   lph.setRemark(rs.getObject("remark")==null?null:rs.getString("remark"));
		   lph.setCreatedBy(rs.getLong("created_by"));
		   lph.setCreationDate(rs.getDate("creation_date"));
		   lph.setLastUpdatedBy(rs.getLong("last_updated_by"));
		   lph.setLastUpdateDate(rs.getDate("last_update_date"));
		   lph.setLastUpdateLogin(rs.getLong("last_update_login"));
		   return lph;
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
