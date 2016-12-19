package com.xinyiglass.springSample.controller;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
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
public class PermController {
    
	@Autowired
	OnhandPermVOService opvs;
	@Autowired
	FuncPermVOService fpvs;
	
	protected HttpServletRequest req; 
    protected HttpServletResponse res; 
    protected HttpSession sess; 
    
    @ModelAttribute 
    public void setReqAndRes(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{ 
        this.req = request; 
        this.res = response; 
        this.sess = request.getSession(); 
        req.setCharacterEncoding("utf-8");
		res.setCharacterEncoding("utf-8");
		res.setContentType("text/html;charset=utf-8");  
    }
    
    @RequestMapping("/onhandPerm.do")
	public String listOnhandPermVO(){
		if(sess.getAttribute("USER_ID")!=null&&sess.getAttribute("USER_ID").toString().length()>0){
			return "basic/onhandPerm";
		}else{
			return "error/sessionTimeout";
		}
	}
    
    @RequestMapping(value = "/getOnhandPerm.do", method = RequestMethod.POST)
	public void getOnhandPerm() throws Exception
	{   	
		int pageSize=Integer.parseInt(req.getParameter("pageSize"));
		int pageNo=Integer.parseInt(req.getParameter("pageNo"));
		boolean goLastPage=Boolean.parseBoolean(req.getParameter("goLastPage"));
		Long userId = TypeConvert.str2Long(req.getParameter("USER_ID"));
		Long organId = TypeConvert.str2Long(req.getParameter("ORGANIZATION_ID"));
		Date startDate_F = TypeConvert.str2uDate(req.getParameter("START_DATE_F"));
		Date startDate_T = TypeConvert.str2uDate(req.getParameter("START_DATE_T"));
		Date endDate_F = TypeConvert.str2uDate(req.getParameter("END_DATE_F"));
		Date endDate_T = TypeConvert.str2uDate(req.getParameter("END_DATE_T"));
		String orderBy=req.getParameter("orderby");
		res.getWriter().print(opvs.findForPage(pageSize, pageNo, goLastPage, userId, organId, startDate_F, startDate_T, endDate_F, endDate_T, orderBy));
	}
    
    @RequestMapping(value = "/insertOP.do", method = RequestMethod.POST)
   	public void insretOP() throws Exception
   	{   
    	OnhandPermVO op = new OnhandPermVO();
    	op.setUserId(TypeConvert.str2Long(req.getParameter("USER_ID")));
    	op.setOrganizationId(TypeConvert.str2Long(req.getParameter("ORGANIZATION_ID")));
    	op.setStartDate(TypeConvert.str2uDate(req.getParameter("START_DATE")));
       	op.setEndDate(TypeConvert.str2uDate(req.getParameter("END_DATE")));
       	res.getWriter().print(opvs.insert(op).toJsonStr());
   	}
    
    @RequestMapping(value = "/preUpdateOP.do", method = RequestMethod.POST)
    public void preUpdateOP() throws Exception
    {
    	Long pId = Long.parseLong(req.getParameter("P_ID"));
    	OnhandPermVO opVO = opvs.findForOnhandPermVOById(pId);
    	sess.setAttribute("lockOpVO", opVO);//记录在session变量
    	res.getWriter().print(opvs.findOnhandPermByIdForJSON(pId));
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
    	res.getWriter().print(opvs.update(lockOpVO, op).toJsonStr());
	}
    
    @RequestMapping("/funcPerm.do")
	public String listFuncPermVO(){
		if(sess.getAttribute("USER_ID")!=null&&sess.getAttribute("USER_ID").toString().length()>0){
			return "basic/funcPerm";
		}else{
			return "error/sessionTimeout";
		}
	}
    
    @RequestMapping(value = "/getFuncPerm.do", method = RequestMethod.POST)
	public void getFuncPerm() throws Exception
	{   	
		int pageSize=Integer.parseInt(req.getParameter("pageSize"));
		int pageNo=Integer.parseInt(req.getParameter("pageNo"));
		boolean goLastPage=Boolean.parseBoolean(req.getParameter("goLastPage"));
		Long userId = TypeConvert.str2Long(req.getParameter("USER_ID"));
		Long funcId = TypeConvert.str2Long(req.getParameter("FUNCTION_ID"));
		Date startDate_F = TypeConvert.str2uDate(req.getParameter("START_DATE_F"));
		Date startDate_T = TypeConvert.str2uDate(req.getParameter("START_DATE_T"));
		Date endDate_F = TypeConvert.str2uDate(req.getParameter("END_DATE_F"));
		Date endDate_T = TypeConvert.str2uDate(req.getParameter("END_DATE_T"));
		String orderBy=req.getParameter("orderby");
		res.getWriter().print(fpvs.findForPage(pageSize, pageNo, goLastPage, userId, funcId, startDate_F, startDate_T, endDate_F, endDate_T, orderBy));
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
    	res.getWriter().print(fpvs.insert(fp).toJsonStr());
	}
    
    @RequestMapping(value = "/getUserFuncPerm.do", method = RequestMethod.POST)
    public void getUserFuncPerm() throws Exception
    {
    	Long userId = (Long)sess.getAttribute("USER_ID");
    	Long funcId = (Long)sess.getAttribute("FUNC_ID");
    	res.getWriter().print(fpvs.findFuncPermByUserAndFunc(userId, funcId));
    }
    
    @RequestMapping(value = "/preUpdateFP.do", method = RequestMethod.POST)
    public void preUpdateFP() throws Exception
    {
    	Long pId = Long.parseLong(req.getParameter("P_ID"));
    	FuncPermVO fpVO = fpvs.findForFuncPermVOById(pId);
    	sess.setAttribute("lockFpVO", fpVO);//记录在session变量
    	res.getWriter().print(fpvs.findFuncPermByIdForJSON(pId));
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
    	res.getWriter().print(fpvs.update(fp).toJsonStr());
	}
}
