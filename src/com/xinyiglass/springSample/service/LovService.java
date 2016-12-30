package com.xinyiglass.springSample.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import xygdev.commons.page.PagePub;
import xygdev.commons.sqlStmt.SqlStmtPub;

//正常应该是写在dao层的。Lov特殊处理，因为只有一个SQL就可以得到要的内容，所以先写在Service层。
//而且Lov的数据访问的重用性不是很高，以查询和验证数据为主，所以确实可以不用封装dao。
//关于分页处理的必须要封装。基本的逻辑是：给一个SQL，还有相关的条件，返回页的json数据结果-->2016.8.9已经完成
@Service
//不需要事务管理的(只查询的)方法，可以提高效率。
@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
public class LovService {

	@Autowired
	PagePub pagePub;
	
	/***用户LOV***/
	public String findUserForPage(int pageSize,int pageNo,boolean goLastPage,String userName,String userDesc,Long loginId) throws Exception{
		StringBuffer sqlBuf=new StringBuffer();
		Map<String,Object> paramMap=new HashMap<String,Object>();
		sqlBuf.append("SELECT USER_ID,USER_NAME,DESCRIPTION FROM XYG_ALB2B_USER");
		sqlBuf.append(" WHERE (END_DATE IS NULL OR END_DATE > SYSDATE)");
		sqlBuf.append(SqlStmtPub.getAndStmt("USER_NAME", userName,paramMap));
		sqlBuf.append(SqlStmtPub.getAndStmt("DESCRIPTION", userDesc,paramMap));
		sqlBuf.append(" ORDER BY USER_ID ");
		return pagePub.qPageForJson(sqlBuf.toString(), paramMap, pageSize, pageNo, goLastPage);
	}
	
	public String countByUserName(String userName,Long loginId) throws Exception{
		String sql = "SELECT COUNT(*) COUNT FROM XYG_ALB2B_USER WHERE (END_DATE IS NULL OR END_DATE > SYSDATE) AND USER_NAME=:1";
		Map<String,Object> paramMap=new  HashMap<String,Object>();
		paramMap.put("1", userName);
		return pagePub.qSqlForJson(sql, paramMap);
	}
	
	public String findForUserId(String userName,Long loginId) throws Exception{
		String sql = "SELECT USER_ID,DESCRIPTION FROM XYG_ALB2B_USER WHERE (END_DATE IS NULL OR END_DATE > SYSDATE) AND USER_NAME=:1";
		Map<String,Object> paramMap=new  HashMap<String,Object>();
		paramMap.put("1", userName);
		return pagePub.qSqlForJson(sql, paramMap);
	}
	
	/***职责LOV***/
	public String findRespForPage(int pageSize,int pageNo,boolean goLastPage,String respCode,String respName,Long loginId) throws Exception{
		StringBuffer sqlBuf=new StringBuffer();
		Map<String,Object> paramMap=new HashMap<String,Object>();
		sqlBuf.append("SELECT RESP_ID,RESP_CODE,RESP_NAME,DESCRIPTION FROM XYG_ALB2B_RESPONSIBILITY");
		sqlBuf.append(" WHERE (END_DATE IS NULL OR END_DATE > SYSDATE)");
		sqlBuf.append(SqlStmtPub.getAndStmt("RESP_CODE", respCode,paramMap));
		sqlBuf.append(SqlStmtPub.getAndStmt("RESP_NAME", respName,paramMap));
		sqlBuf.append(" ORDER BY RESP_ID ");
		return pagePub.qPageForJson(sqlBuf.toString(), paramMap, pageSize, pageNo, goLastPage);
	}	
	
	public String countByRespName(String respName,Long loginId) throws Exception{
		String sql = "SELECT COUNT(*) COUNT FROM XYG_ALB2B_RESPONSIBILITY WHERE (END_DATE IS NULL OR END_DATE > SYSDATE) AND RESP_NAME=:1";
		Map<String,Object> paramMap=new  HashMap<String,Object>();
		paramMap.put("1", respName);
		return pagePub.qSqlForJson(sql, paramMap);
	}
	
	public String findForRespId(String respName,Long loginId) throws Exception{
		String sql = "SELECT RESP_ID FROM XYG_ALB2B_RESPONSIBILITY WHERE (END_DATE IS NULL OR END_DATE > SYSDATE) AND RESP_NAME=:1";
		Map<String,Object> paramMap=new  HashMap<String,Object>();
		paramMap.put("1", respName);
		return pagePub.qSqlForJson(sql, paramMap);
	}
	
	/***客户LOV***/
	public String findCustForPage(int pageSize,int pageNo,boolean goLastPage,String accountNumber,String partyName,Long loginId) throws Exception{
		StringBuffer sqlBuf=new StringBuffer();
		Map<String,Object> paramMap=new HashMap<String,Object>();
		sqlBuf.append("SELECT *");
		sqlBuf.append("  FROM (SELECT OPERATING_UNIT");
		sqlBuf.append("          FROM XYG_ALI_ORGANIZATION_VL");
		sqlBuf.append("         WHERE GLASS_INDUSTRY IN('FF','GF','JB')");
		sqlBuf.append("         GROUP BY OPERATING_UNIT) XAO,");
		sqlBuf.append("       XYG_ALFR_CUST_ACCOUNT_V XACA");
		sqlBuf.append(" WHERE 1 = 1");
		sqlBuf.append("   AND STATUS='A'");
		sqlBuf.append("   AND XACA.ORG_ID=XAO.OPERATING_UNIT");
		sqlBuf.append(SqlStmtPub.getAndStmt("ACCOUNT_NUMBER",accountNumber,paramMap));
		sqlBuf.append(SqlStmtPub.getAndStmt("PARTY_NAME", partyName,paramMap));
		sqlBuf.append(" ORDER BY CUST_ACCOUNT_ID");
		return pagePub.qPageForJson(sqlBuf.toString(), paramMap, pageSize, pageNo, goLastPage);
	}	
	
	/***客户LOV(用户限制)***/
	public String findUserCustForPage(int pageSize,int pageNo,boolean goLastPage,String accountNumber,String partyName,Long userId,String userType,Long loginId) throws Exception{
		StringBuffer sqlBuf=new StringBuffer();
		Map<String,Object> paramMap=new HashMap<String,Object>();
		if(userType.equals("EMP")){
			paramMap.put("1", userId);
			//sqlBuf.append("SELECT ORG_ID,ORG_NAME,PARTY_NAME,ACCOUNT_NUMBER,CUST_ACCOUNT_ID CUSTOMER_ID FROM XYG_ALB2B_ONHAND_PERM XAOP,XYG_ALI_ORGANIZATION XAO,XYG_ALFR_CUST_ACCOUNT_V XACA");	
			sqlBuf.append("SELECT XACA.ORG_ID,XACA.ORG_NAME,XACA.PARTY_NAME,XACA.ACCOUNT_NUMBER,XACA.CUST_ACCOUNT_ID CUSTOMER_ID,XAO.ORGANIZATION_ID,XAO.ORGANIZATION_NAME FROM XYG_ALB2B_ONHAND_PERM XAOP,XYG_ALI_ORGANIZATION_VL XAO,XYG_ALFR_CUST_ACCOUNT_V XACA");		
			sqlBuf.append(" WHERE 1 = 1");
			sqlBuf.append(" AND XAOP.ORGANIZATION_ID=XAO.ORGANIZATION_ID");
			sqlBuf.append(" AND XACA.ORG_ID=XAO.OPERATING_UNIT");
			sqlBuf.append(" AND USER_ID=:1");
			sqlBuf.append(SqlStmtPub.getAndStmt("ACCOUNT_NUMBER",accountNumber,paramMap));
			sqlBuf.append(SqlStmtPub.getAndStmt("PARTY_NAME", partyName,paramMap));
			sqlBuf.append(" ORDER BY CUSTOMER_ID");
		}else if(userType.equals("CUSTOMER")){
			paramMap.put("1", userId);
			//sqlBuf.append("SELECT * FROM XYG_ALB2B_USER_CUSTOMER_V");		
			//sqlBuf.append(" WHERE 1 = 1");
			sqlBuf.append("SELECT XAUC.*,XAOT.ORGANIZATION_ID,XAOT.ORGANIZATION_NAME");
			sqlBuf.append("  FROM XYG_ALB2B_USER_CUSTOMER_V XAUC");
			sqlBuf.append("      ,(SELECT OPERATING_UNIT,MAX(ORGANIZATION_ID) ORGANIZATION_ID");
			sqlBuf.append("          FROM XYG_ALI_ORGANIZATION");
			sqlBuf.append("         GROUP BY OPERATING_UNIT) XAO");
			sqlBuf.append("      ,XYG_ALI_ORGANIZATION_TL XAOT");
			sqlBuf.append(" WHERE USER_ID = :1");
			sqlBuf.append("   AND XAUC.ORG_ID = XAO.OPERATING_UNIT");
			sqlBuf.append("   AND XAOT.ORGANIZATION_ID=XAO.ORGANIZATION_ID");
			sqlBuf.append("   AND XAOT.LANGUAGE = USERENV ('LANG')");
			//sqlBuf.append(" AND USER_ID=:1");
			sqlBuf.append(SqlStmtPub.getAndStmt("ACCOUNT_NUMBER",accountNumber,paramMap));
			sqlBuf.append(SqlStmtPub.getAndStmt("PARTY_NAME", partyName,paramMap));
			sqlBuf.append(" ORDER BY CUSTOMER_ID");
		}else{
			throw new RuntimeException("用户类型错误！");
		}
		return pagePub.qPageForJson(sqlBuf.toString(), paramMap, pageSize, pageNo, goLastPage);
	}					
	
	/***菜单LOV***/
	public String findMenuForPage(int pageSize,int pageNo,boolean goLastPage,String menuCode,String menuName,Long loginId) throws Exception{
		StringBuffer sqlBuf=new StringBuffer();
		Map<String,Object> paramMap=new HashMap<String,Object>();
		sqlBuf.append("SELECT MENU_ID,MENU_CODE,MENU_NAME,DESCRIPTION FROM XYG_ALB2B_MENU_HEADERS");
		sqlBuf.append(" WHERE 1 = 1");
		sqlBuf.append(SqlStmtPub.getAndStmt("MENU_CODE",menuCode,paramMap));
		sqlBuf.append(SqlStmtPub.getAndStmt("MENU_NAME", menuName,paramMap));
		sqlBuf.append(" ORDER BY MENU_ID");
		return pagePub.qPageForJson(sqlBuf.toString(), paramMap, pageSize, pageNo, goLastPage);
	}	
	
	public String countByMenuCode(String menuCode,Long loginId) throws Exception{
		String sql = "SELECT COUNT(*) COUNT FROM XYG_ALB2B_MENU_HEADERS WHERE MENU_CODE = :1";
		Map<String,Object> paramMap=new  HashMap<String,Object>();
		paramMap.put("1", menuCode);
		return pagePub.qSqlForJson(sql, paramMap);
	}
	
	public String findForMenuId(String menuCode,Long loginId) throws Exception{
		String sql = "SELECT * FROM XYG_ALB2B_MENU_HEADERS WHERE MENU_CODE = :1";
		Map<String,Object> paramMap=new  HashMap<String,Object>();
		paramMap.put("1", menuCode);
		return pagePub.qSqlForJson(sql, paramMap);
	}
	
	/***图标LOV***/
	public String findIconForPage(int pageSize,int pageNo,boolean goLastPage,String iconCode,String iconDesc,Long loginId) throws Exception{
		StringBuffer sqlBuf=new StringBuffer();
		Map<String,Object> paramMap=new HashMap<String,Object>();
		sqlBuf.append("SELECT ICON_ID,ICON_CODE,DESCRIPTION,ICON_SOURCE FROM XYG_ALD_ICONS");
		sqlBuf.append(" WHERE 1 = 1");
		sqlBuf.append(SqlStmtPub.getAndStmt("ICON_CODE",iconCode,paramMap));
		sqlBuf.append(SqlStmtPub.getAndStmt("DESCRIPTION", iconDesc,paramMap));
		sqlBuf.append(" ORDER BY ICON_ID");
		return pagePub.qPageForJson(sqlBuf.toString(), paramMap, pageSize, pageNo, goLastPage);
	}
	
	/***功能LOV***/
	public String findFuncForPage(int pageSize,int pageNo,boolean goLastPage,String funcCode,String funcName,Long loginId) throws Exception{
		StringBuffer sqlBuf=new StringBuffer();
		Map<String,Object> paramMap=new HashMap<String,Object>();
		sqlBuf.append("SELECT FUNCTION_ID,FUNCTION_CODE,FUNCTION_NAME,DESCRIPTION FROM XYG_ALB2B_FUNCTIONS");
		sqlBuf.append(" WHERE 1 = 1");
		sqlBuf.append(SqlStmtPub.getAndStmt("FUNCTION_CODE",funcCode,paramMap));
		sqlBuf.append(SqlStmtPub.getAndStmt("FUNCTION_NAME", funcName,paramMap));
		sqlBuf.append(" ORDER BY FUNCTION_ID");
		return pagePub.qPageForJson(sqlBuf.toString(), paramMap, pageSize, pageNo, goLastPage);
	}
	
	public String countByFuncCode(String funcCode,Long loginId) throws Exception{
		String sql = "SELECT COUNT(*) COUNT FROM XYG_ALB2B_FUNCTIONS WHERE FUNCTION_CODE = :1";
		Map<String,Object> paramMap=new  HashMap<String,Object>();
		paramMap.put("1", funcCode);
		return pagePub.qSqlForJson(sql, paramMap);
	}
	
	public String findForFuncId(String funcCode,Long loginId) throws Exception{
		String sql = "SELECT FUNCTION_ID,FUNCTION_NAME FROM XYG_ALB2B_FUNCTIONS WHERE FUNCTION_CODE = :1";
		Map<String,Object> paramMap=new  HashMap<String,Object>();
		paramMap.put("1", funcCode);
		return pagePub.qSqlForJson(sql, paramMap);
	}
	
	/***员工LOV***/
	public String findEmpForPage(int pageSize,int pageNo,boolean goLastPage,String ename,String eno,Long loginId) throws Exception{
		StringBuffer sqlBuf=new StringBuffer();
		Map<String,Object> paramMap=new HashMap<String,Object>();
		sqlBuf.append("SELECT PERSON_ID EMP_ID,FULL_NAME ENAME,EMPLOYEE_NUMBER EMPNO FROM XYG_ALH_PER_PEOPLE");
		sqlBuf.append(" WHERE 1 = 1");
		sqlBuf.append(SqlStmtPub.getAndStmt("FULL_NAME",ename,paramMap));
		sqlBuf.append(SqlStmtPub.getAndStmt("EMPLOYEE_NUMBER", eno,paramMap));
		sqlBuf.append(" ORDER BY EMP_ID");
		return pagePub.qPageForJson(sqlBuf.toString(), paramMap, pageSize, pageNo, goLastPage);
	}
	
	public String countByEmpName(String ename,Long loginId) throws Exception{
		String sql = "SELECT COUNT(*) COUNT FROM XYG_ALH_PER_PEOPLE WHERE FULL_NAME = :1";
		Map<String,Object> paramMap=new  HashMap<String,Object>();
		paramMap.put("1", ename);
		return pagePub.qSqlForJson(sql, paramMap);
	}
	
	public String findForEmpId(String ename,Long loginId) throws Exception{
		String sql = "SELECT PERSON_ID EMP_ID FROM XYG_ALH_PER_PEOPLE WHERE FULL_NAME = :1";
		Map<String,Object> paramMap=new  HashMap<String,Object>();
		paramMap.put("1", ename);
		return pagePub.qSqlForJson(sql, paramMap);
	}
	
	/***工作组LOV***/
	public String findGroupForPage(int pageSize,int pageNo,boolean goLastPage,String groupCode,String groupName,Long loginId) throws Exception{
		StringBuffer sqlBuf=new StringBuffer();
		Map<String,Object> paramMap=new HashMap<String,Object>();
		sqlBuf.append("SELECT GROUP_ID,GROUP_CODE,GROUP_NAME,DESCRIPTION FROM XYG_ALB2B_GROUP_HEADERS");
		sqlBuf.append(" WHERE 1 = 1");
		sqlBuf.append(SqlStmtPub.getAndStmt("GROUP_CODE",groupCode,paramMap));
		sqlBuf.append(SqlStmtPub.getAndStmt("GROUP_NAME", groupName,paramMap));
		sqlBuf.append(" ORDER BY GROUP_ID");
		return pagePub.qPageForJson(sqlBuf.toString(), paramMap, pageSize, pageNo, goLastPage);
	}
	
	public String countByGroupCode(String groupName,Long loginId) throws Exception{
		String sql = "SELECT COUNT(*) COUNT FROM XYG_ALB2B_GROUP_HEADERS WHERE GROUP_NAME = :1";
		Map<String,Object> paramMap=new  HashMap<String,Object>();
		paramMap.put("1", groupName);
		return pagePub.qSqlForJson(sql, paramMap);
	}
	
	public String findForGroupId(String groupName,Long loginId) throws Exception{
		String sql = "SELECT * FROM XYG_ALB2B_GROUP_HEADERS WHERE GROUP_NAME = :1";
		Map<String,Object> paramMap=new  HashMap<String,Object>();
		paramMap.put("1", groupName);
		return pagePub.qSqlForJson(sql, paramMap);
	}
	
    /***库存组织LOV***/
	public String findOrganForPage(int pageSize,int pageNo,boolean goLastPage,String organCode,String organName,Long loginId) throws Exception{
		StringBuffer sqlBuf=new StringBuffer();
		Map<String,Object> paramMap=new HashMap<String,Object>();
		sqlBuf.append("SELECT * FROM XYG_ALI_ORGANIZATION_VL");
		sqlBuf.append(" WHERE 1 = 1");
		sqlBuf.append(" AND GLASS_INDUSTRY IN('FF','GF','JB')");
		sqlBuf.append(SqlStmtPub.getAndStmt("ORGANIZATION_CODE",organCode,paramMap));
		sqlBuf.append(SqlStmtPub.getAndStmt("ORGANIZATION_NAME", organName,paramMap));
		sqlBuf.append(" ORDER BY ORGANIZATION_ID");
		return pagePub.qPageForJson(sqlBuf.toString(), paramMap, pageSize, pageNo, goLastPage);
	}
	
	public String countByOrganCode(String organCode,Long loginId) throws Exception{
		String sql = "SELECT COUNT(*) COUNT FROM XYG_ALI_ORGANIZATION_VL WHERE ORGANIZATION_CODE = :1";
		Map<String,Object> paramMap=new  HashMap<String,Object>();
		paramMap.put("1", organCode);
		return pagePub.qSqlForJson(sql, paramMap);
	}
	
	public String findForOrganId(String organCode,Long loginId) throws Exception{
		String sql = "SELECT * FROM XYG_ALI_ORGANIZATION_VL WHERE ORGANIZATION_CODE = :1";
		Map<String,Object> paramMap=new  HashMap<String,Object>();
		paramMap.put("1", organCode);
		return pagePub.qSqlForJson(sql, paramMap);
	}
	
	/***用户库存组织LOV***/
	public String findUserOrganForPage(int pageSize,int pageNo,boolean goLastPage,Long userId,String organCode,String organName,String glassIndustry,Long loginId) throws Exception{
		StringBuffer sqlBuf=new StringBuffer();
		Map<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("1", userId);
		paramMap.put("2", glassIndustry);
		sqlBuf.append("SELECT ORGANIZATION_ID,ORGANIZATION_CODE,ORGANIZATION_NAME FROM XYG_ALB2B_ONHAND_PERM_V");
		sqlBuf.append(" WHERE USER_ID = :1");
		sqlBuf.append(" AND GLASS_INDUSTRY = :2");
		sqlBuf.append(SqlStmtPub.getAndStmt("ORGANIZATION_CODE",organCode,paramMap));
		sqlBuf.append(SqlStmtPub.getAndStmt("ORGANIZATION_NAME", organName,paramMap));
		sqlBuf.append(" ORDER BY ORGANIZATION_ID");
		return pagePub.qPageForJson(sqlBuf.toString(), paramMap, pageSize, pageNo, goLastPage);
	}
	
	/***物料LOV***/
	public String findItemForPage(int pageSize,int pageNo,boolean goLastPage,Long thickness,String coatingCode,Long organizationId,String itemNumber,Long loginId) throws Exception{
		StringBuffer sqlBuf=new StringBuffer();
		Map<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("1", thickness);
		paramMap.put("2", coatingCode);
		paramMap.put("3", organizationId);
		sqlBuf.append("SELECT INVENTORY_ITEM_ID,ITEM_NUMBER,DESCRIPTION,LONG_DESCRIPTION FROM XYG_ALI_ITEMS_VL");
		sqlBuf.append(" WHERE THICKNESS = :1");
		sqlBuf.append(" AND COATING_TYPE = :2");
		sqlBuf.append(" AND ORGANIZATION_ID = :3");
		sqlBuf.append(" AND PRODUCT_FINANCE_TYPE IN ('204001','204002','204003','204004','204005','204006','204014')");
		sqlBuf.append(SqlStmtPub.getAndStmt("ITEM_NUMBER",itemNumber,paramMap));
		sqlBuf.append(" ORDER BY INVENTORY_ITEM_ID");
		return pagePub.qPageForJson(sqlBuf.toString(), paramMap, pageSize, pageNo, goLastPage);
	}
	
	/***LOOKUP VALUE LOV***/
	//新增过滤条件 MODIFY　BY BIRD --2016.12.19
	public String findLookupForPage(int pageSize,int pageNo,boolean goLastPage,String lookupType,String lookupCode,String meaning,Long loginId) throws Exception{
		StringBuffer sqlBuf=new StringBuffer();
		Map<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("1", lookupType);
		sqlBuf.append("SELECT * FROM XYG_ALD_LOOKUP_VALUES");
		sqlBuf.append(" WHERE 1 = 1 AND LOOKUP_TYPE=:1");
		sqlBuf.append("   AND ENABLED_FLAG = 'Y'");
		sqlBuf.append("   AND SYSDATE BETWEEN START_DATE_ACTIVE AND NVL(END_DATE_ACTIVE,SYSDATE+1)");
		sqlBuf.append(SqlStmtPub.getAndStmt("LOOKUP_CODE",lookupCode,paramMap));
		sqlBuf.append(SqlStmtPub.getAndStmt("MEANING", meaning,paramMap));
		sqlBuf.append(" ORDER BY LOOKUP_CODE");
		return pagePub.qPageForJson(sqlBuf.toString(), paramMap, pageSize, pageNo, goLastPage);
	}
	
	public String countByLookupMeaning(String meaning,String lookupType,Long loginId) throws Exception{
		Map<String,Object> paramMap=new  HashMap<String,Object>();
		paramMap.put("1", meaning);
		paramMap.put("2", lookupType);
		StringBuffer sqlBuf=new StringBuffer();
		sqlBuf.append("SELECT COUNT(*) COUNT FROM XYG_ALD_LOOKUP_VALUES");
		sqlBuf.append(" WHERE 1 = 1");
		sqlBuf.append("   AND ENABLED_FLAG = 'Y'");
		sqlBuf.append("   AND SYSDATE BETWEEN START_DATE_ACTIVE AND NVL(END_DATE_ACTIVE,SYSDATE+1)");
		sqlBuf.append("   AND MEANING = :1");
		sqlBuf.append("   AND LOOKUP_TYPE=:2");
		return pagePub.qSqlForJson(sqlBuf.toString(), paramMap);
	}
	
	public String findForLookupCode(String meaning,String lookupType,Long loginId) throws Exception{
		Map<String,Object> paramMap=new  HashMap<String,Object>();
		paramMap.put("1", meaning);
		paramMap.put("2", lookupType);
		StringBuffer sqlBuf=new StringBuffer();
		sqlBuf.append("SELECT LOOKUP_CODE FROM XYG_ALD_LOOKUP_VALUES");
		sqlBuf.append(" WHERE 1 = 1");
		sqlBuf.append("   AND ENABLED_FLAG = 'Y'");
		sqlBuf.append("   AND SYSDATE BETWEEN START_DATE_ACTIVE AND NVL(END_DATE_ACTIVE,SYSDATE+1)");
		sqlBuf.append("   AND MEANING = :1");
		sqlBuf.append("   AND LOOKUP_TYPE=:2");
		return pagePub.qSqlForJson(sqlBuf.toString(), paramMap);
	}
	

}
