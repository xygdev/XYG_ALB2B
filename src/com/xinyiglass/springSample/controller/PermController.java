package com.xinyiglass.springSample.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import xygdev.commons.util.TypeConvert;

import com.xinyiglass.springSample.entity.FuncPermVO;
import com.xinyiglass.springSample.entity.OnhandPermVO;
import com.xinyiglass.springSample.service.FuncPermVOService;
import com.xinyiglass.springSample.service.OnhandPermVOService;

@Controller
@RequestMapping("/perm")
@Scope("prototype")
public class PermController {
    
	@Autowired
	OnhandPermVOService opvs;
	@Autowired
	FuncPermVOService fpvs;
	
	protected HttpServletRequest req; 
    protected HttpServletResponse res; 
    protected HttpSession sess; 
    protected Long loginId; 
    
    @ModelAttribute 
    public void setReqAndRes(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{ 
        this.req = request; 
        this.res = response; 
        this.sess = request.getSession(); 
        req.setCharacterEncoding("utf-8");
		res.setCharacterEncoding("utf-8");
		res.setContentType("text/html;charset=utf-8");  
	    loginId=(Long)sess.getAttribute("LOGIN_ID");
    }
    
    @RequestMapping(value = "/getEdiLog.do", method = RequestMethod.POST)
	public void getEdiLog() throws Exception
	{  
    	String syncCode = req.getParameter("SYNC_CODE");
    	res.getWriter().print(opvs.validateEdiLog(syncCode,loginId));
	}
    
    @RequestMapping("/onhandPerm.do")
	public String listOnhandPermVO(){
		return "basic/onhandPerm";
	}
    
    @RequestMapping(value = "/getOnhandPerm.do", method = RequestMethod.POST)
	public void getOnhandPerm() throws Exception
	{   	
		int pageSize=Integer.parseInt(req.getParameter("pageSize"));
		int pageNo=Integer.parseInt(req.getParameter("pageNo"));
		boolean goLastPage=Boolean.parseBoolean(req.getParameter("goLastPage"));
   		Map<String,Object> conditionMap=new HashMap<String,Object>();
   		conditionMap.put("orderBy", req.getParameter("orderby"));
   		conditionMap.put("userId", TypeConvert.str2Long(req.getParameter("USER_ID")));
   		conditionMap.put("organId", TypeConvert.str2Long(req.getParameter("ORGANIZATION_ID")));
   		conditionMap.put("startDate_F", TypeConvert.str2uDate(req.getParameter("START_DATE_F")));
   		conditionMap.put("startDate_T", TypeConvert.str2uDate(req.getParameter("START_DATE_T")));
   		conditionMap.put("endDate_F", TypeConvert.str2uDate(req.getParameter("END_DATE_F")));
   		conditionMap.put("endDate_T", TypeConvert.str2uDate(req.getParameter("END_DATE_T")));
		res.getWriter().print(opvs.findForPage(pageSize, pageNo, goLastPage, conditionMap, loginId));
	}
    
    @RequestMapping(value = "/insertOP.do", method = RequestMethod.POST)
   	public void insretOP() throws Exception
   	{   
    	OnhandPermVO op = new OnhandPermVO();
    	op.setUserId(TypeConvert.str2Long(req.getParameter("USER_ID")));
    	op.setOrganizationId(TypeConvert.str2Long(req.getParameter("ORGANIZATION_ID")));
    	op.setStartDate(TypeConvert.str2uDate(req.getParameter("START_DATE")));
       	op.setEndDate(TypeConvert.str2uDate(req.getParameter("END_DATE")));
       	res.getWriter().print(opvs.insert(op,loginId).toJsonStr());
   	}
    
    @RequestMapping(value = "/preUpdateOP.do", method = RequestMethod.POST)
    public void preUpdateOP() throws Exception
    {
    	Long pId = Long.parseLong(req.getParameter("P_ID"));
    	OnhandPermVO opVO = opvs.findForOnhandPermVOById(pId,loginId);
    	sess.setAttribute("lockOpVO", opVO);//记录在session变量
    	res.getWriter().print(opvs.findOnhandPermByIdForJSON(pId,loginId));
    }
    
    @RequestMapping(value = "/updateOP.do", method = RequestMethod.POST)
	public void updateOP() throws Exception
	{
    	Long pId = Long.parseLong(req.getParameter("P_ID"));
    	OnhandPermVO lockOpVO = (OnhandPermVO)sess.getAttribute("lockOpVO");
    	if (lockOpVO ==null){
			throw new RuntimeException("更新数据出错:会话数据已经无效!请返回再重新操作!");
		}
		if (!pId.equals(lockOpVO.getPId())){
			throw new RuntimeException("更新的数据无法匹配!请重新查询!");
		}
		OnhandPermVO op = new OnhandPermVO();
		op = (OnhandPermVO)lockOpVO.clone();
		op.setPId(pId);
		op.setUserId(TypeConvert.str2Long(req.getParameter("USER_ID")));
    	op.setOrganizationId(TypeConvert.str2Long(req.getParameter("ORGANIZATION_ID")));
    	op.setStartDate(TypeConvert.str2uDate(req.getParameter("START_DATE")));
       	op.setEndDate(TypeConvert.str2uDate(req.getParameter("END_DATE")));
    	res.getWriter().print(opvs.update(lockOpVO, op,loginId).toJsonStr());
	}
    
    @RequestMapping("/funcPerm.do")
	public String listFuncPermVO(){
		return "basic/funcPerm";
	}
    
    @RequestMapping(value = "/getFuncPerm.do", method = RequestMethod.POST)
	public void getFuncPerm() throws Exception
	{   	
		int pageSize=Integer.parseInt(req.getParameter("pageSize"));
		int pageNo=Integer.parseInt(req.getParameter("pageNo"));
		boolean goLastPage=Boolean.parseBoolean(req.getParameter("goLastPage"));
   		Map<String,Object> conditionMap=new HashMap<String,Object>();
   		conditionMap.put("orderBy", req.getParameter("orderby"));
   		conditionMap.put("userId", TypeConvert.str2Long(req.getParameter("USER_ID")));
   		conditionMap.put("funcId", TypeConvert.str2Long(req.getParameter("FUNCTION_ID")));
   		conditionMap.put("startDate_F", TypeConvert.str2uDate(req.getParameter("START_DATE_F")));
   		conditionMap.put("startDate_T", TypeConvert.str2uDate(req.getParameter("START_DATE_T")));
   		conditionMap.put("endDate_F", TypeConvert.str2uDate(req.getParameter("END_DATE_F")));
   		conditionMap.put("endDate_T", TypeConvert.str2uDate(req.getParameter("END_DATE_T")));
		res.getWriter().print(fpvs.findForPage(pageSize, pageNo, goLastPage, conditionMap, loginId));
	}
    
    @RequestMapping(value = "/insertFP.do", method = RequestMethod.POST)
	public void insretFP() throws Exception
	{ 
    	FuncPermVO fp = new FuncPermVO();
    	fp.setUserId(TypeConvert.str2Long(req.getParameter("USER_ID")));
    	fp.setFunctionId(TypeConvert.str2Long(req.getParameter("FUNCTION_ID")));
    	String insertFlag=req.getParameter("INSERT_FLAG");
    	if(insertFlag!=null&&insertFlag.equals("on")){
    		fp.setInsertFlag("Y");
    	}else{
    		fp.setInsertFlag("N");
    	}
    	String updateFlag=req.getParameter("UPDATE_FLAG");
    	if(updateFlag!=null&&updateFlag.equals("on")){
    		fp.setUpdateFlag("Y");
    	}else{
    		fp.setUpdateFlag("N");
    	}
    	String approveFlag=req.getParameter("APPROVE_FLAG");
    	if(approveFlag!=null&&approveFlag.equals("on")){
    		fp.setApproveFlag("Y");
    	}else{
    		fp.setApproveFlag("N");
    	}
    	String finalApproveFlag=req.getParameter("FINAL_APPROVE_FLAG");
    	if(finalApproveFlag!=null&&finalApproveFlag.equals("on")){
    		fp.setFinalApproveFlag("Y");
    	}else{
    		fp.setFinalApproveFlag("N");
    	}
    	String downloadFlag=req.getParameter("DELETE_FLAG");
    	if(downloadFlag!=null&&downloadFlag.equals("on")){
    		fp.setDeleteFlag("Y");
    	}else{
    		fp.setDeleteFlag("N");
    	}
    	fp.setStartDate(TypeConvert.str2uDate(req.getParameter("START_DATE")));
    	fp.setEndDate(TypeConvert.str2uDate(req.getParameter("END_DATE")));
    	res.getWriter().print(fpvs.insert(fp,loginId).toJsonStr());
	}
    
    @RequestMapping(value = "/getUserFuncPerm.do", method = RequestMethod.POST)
    public void getUserFuncPerm() throws Exception
    {
    	Long userId = (Long)sess.getAttribute("USER_ID");
    	Long funcId = (Long)sess.getAttribute("FUNC_ID");
    	res.getWriter().print(fpvs.findFuncPermByUserAndFunc(userId, funcId,loginId));
    }
    
    @RequestMapping(value = "/preUpdateFP.do", method = RequestMethod.POST)
    public void preUpdateFP() throws Exception
    {
    	Long pId = Long.parseLong(req.getParameter("P_ID"));
    	FuncPermVO fpVO = fpvs.findForFuncPermVOById(pId,loginId);
    	sess.setAttribute("lockFpVO", fpVO);//记录在session变量
    	res.getWriter().print(fpvs.findFuncPermByIdForJSON(pId,loginId));
    }
    
    @RequestMapping(value = "/updateFP.do", method = RequestMethod.POST)
	public void updateFP() throws Exception
	{ 
    	FuncPermVO fp = new FuncPermVO();
    	fp.setPId(TypeConvert.str2Long(req.getParameter("P_ID")));
    	fp.setUserId(TypeConvert.str2Long(req.getParameter("USER_ID")));
    	fp.setFunctionId(TypeConvert.str2Long(req.getParameter("FUNCTION_ID")));
    	String insertFlag=req.getParameter("INSERT_FLAG");
    	if(insertFlag!=null&&insertFlag.equals("on")){
    		fp.setInsertFlag("Y");
    	}else{
    		fp.setInsertFlag("N");
    	}
    	String updateFlag=req.getParameter("UPDATE_FLAG");
    	if(updateFlag!=null&&updateFlag.equals("on")){
    		fp.setUpdateFlag("Y");
    	}else{
    		fp.setUpdateFlag("N");
    	}
    	String approveFlag=req.getParameter("APPROVE_FLAG");
    	if(approveFlag!=null&&approveFlag.equals("on")){
    		fp.setApproveFlag("Y");
    	}else{
    		fp.setApproveFlag("N");
    	}
    	String finalApproveFlag=req.getParameter("FINAL_APPROVE_FLAG");
    	if(finalApproveFlag!=null&&finalApproveFlag.equals("on")){
    		fp.setFinalApproveFlag("Y");
    	}else{
    		fp.setFinalApproveFlag("N");
    	}
    	String downloadFlag=req.getParameter("DELETE_FLAG");
    	if(downloadFlag!=null&&downloadFlag.equals("on")){
    		fp.setDeleteFlag("Y");
    	}else{
    		fp.setDeleteFlag("N");
    	}
    	fp.setStartDate(TypeConvert.str2uDate(req.getParameter("START_DATE")));
    	fp.setEndDate(TypeConvert.str2uDate(req.getParameter("END_DATE")));
    	res.getWriter().print(fpvs.update(fp,loginId).toJsonStr());
	}
}
