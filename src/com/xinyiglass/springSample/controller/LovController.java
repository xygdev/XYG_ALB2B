package com.xinyiglass.springSample.controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import xygdev.commons.util.TypeConvert;

import com.xinyiglass.springSample.service.LovService;

@Controller
@RequestMapping("/lov")
public class LovController {
	
	@Autowired
	LovService lovService;
	
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
		lovService.setLoginId((Long)sess.getAttribute("LOGIN_ID"));
    } 
	
    //获取USER值列表
	@RequestMapping(value = "/getUserPage.do", method = RequestMethod.POST)
	public void getUserPage() throws Exception
	{   	
		int pageSize=Integer.parseInt(req.getParameter("pageSize"));
		int pageNo=Integer.parseInt(req.getParameter("pageNo"));
		boolean goLastPage=Boolean.parseBoolean(req.getParameter("goLastPage"));
		String userName=req.getParameter("USER_NAME");
		String userDesc=req.getParameter("DESCRIPTION");
		res.getWriter().print(lovService.findUserForPage(pageSize, pageNo, goLastPage, userName, userDesc));
	}
	
	//USER值列表值验证
    @RequestMapping(value = "/validUserName.do", method = RequestMethod.POST)
    public void validUserName() throws Exception
    {	
    	String userName=req.getParameter("username");
    	System.out.println(userName);
    	res.getWriter().print(lovService.countByUserName(userName));
    }
    
    //通过USER_NAME获取USER_ID,DESCRIPTION
    @RequestMapping(value = "/getUserId.do", method = RequestMethod.POST)
    public void getUserId() throws Exception
    {	
    	String userName=req.getParameter("username");
    	System.out.println(userName);
    	res.getWriter().print(lovService.findForUserId(userName));
    }
	
    //获取RESP值列表
	@RequestMapping(value = "/getRespPage.do", method = RequestMethod.POST)
	public void getRespPage() throws Exception
	{   	
		int pageSize=Integer.parseInt(req.getParameter("pageSize"));
		int pageNo=Integer.parseInt(req.getParameter("pageNo"));
		boolean goLastPage=Boolean.parseBoolean(req.getParameter("goLastPage"));
		String respName=req.getParameter("RESP_NAME");
		String respCode=req.getParameter("RESP_CODE");
		res.getWriter().print(lovService.findRespForPage(pageSize, pageNo, goLastPage, respCode, respName));
	}
	
    //RESP值列表值验证
    @RequestMapping(value = "/validRespName.do", method = RequestMethod.POST)
    public void validRespName() throws Exception
    {	
    	String respName=req.getParameter("respname");
    	res.getWriter().print(lovService.countByRespName(respName));
    }
    
    //通过RESP_NAME获取RESP_ID,RESP_CODE,RESP_NAME,DESCRIPTION
    @RequestMapping(value = "/getRespId.do", method = RequestMethod.POST)
    public void getRespId() throws Exception
    {	
    	String respName=req.getParameter("respname");
    	res.getWriter().print(lovService.findForRespId(respName));
    }
    
    //获取CUST值列表
  	@RequestMapping(value = "/getCustPage.do", method = RequestMethod.POST)
  	public void getCustPage() throws Exception
  	{   	
  		int pageSize=Integer.parseInt(req.getParameter("pageSize"));
  		int pageNo=Integer.parseInt(req.getParameter("pageNo"));
  		boolean goLastPage=Boolean.parseBoolean(req.getParameter("goLastPage"));
  		String accountNumber=req.getParameter("ACCOUNT_NUMBER");
  		String partyName=req.getParameter("PARTY_NAME");
  		res.getWriter().print(lovService.findCustForPage(pageSize, pageNo, goLastPage, accountNumber, partyName));
  	}
  	
    //获取CUST值列表(用户限制)
  	@RequestMapping(value = "/getUserCustPage.do", method = RequestMethod.POST)
    public void getUserCustPage() throws Exception
    {
    	int pageSize=Integer.parseInt(req.getParameter("pageSize"));
  		int pageNo=Integer.parseInt(req.getParameter("pageNo"));
  		boolean goLastPage=Boolean.parseBoolean(req.getParameter("goLastPage"));
  		String accountNumber=req.getParameter("ACCOUNT_NUMBER");
  		String partyName=req.getParameter("PARTY_NAME");
  		Long userId = (Long)sess.getAttribute("USER_ID");
  		String userType = (String)sess.getAttribute("USER_TYPE");
  		res.getWriter().print(lovService.findUserCustForPage(pageSize, pageNo, goLastPage, accountNumber, partyName, userId, userType));
    }

	
    //获取MENU值列表
  	@RequestMapping(value = "/getMenuPage.do", method = RequestMethod.POST)
  	public void getMenuPage() throws Exception
  	{   	
  		int pageSize=Integer.parseInt(req.getParameter("pageSize"));
  		int pageNo=Integer.parseInt(req.getParameter("pageNo"));
  		boolean goLastPage=Boolean.parseBoolean(req.getParameter("goLastPage"));
  		String menuCode=req.getParameter("MENU_CODE");
  		String menuName=req.getParameter("MENU_NAME");
  		res.getWriter().print(lovService.findMenuForPage(pageSize, pageNo, goLastPage, menuCode, menuName));
  	}
  	
    //MENU值列表值验证
    @RequestMapping(value = "/validMenuCode.do", method = RequestMethod.POST)
    public void validMenuCode() throws Exception
    {	
      	String menuCode=req.getParameter("menucode");
      	res.getWriter().print(lovService.countByMenuCode(menuCode));
    }
      
    //通过MENU_CODE获取MENU_ID
    @RequestMapping(value = "/getMenuId.do", method = RequestMethod.POST)
    public void getMenuId() throws Exception
    {	
      	String menuCode=req.getParameter("menucode");
      	res.getWriter().print(lovService.findForMenuId(menuCode));
    }
    
    //获取图标值列表
  	@RequestMapping(value = "/getIconPage.do", method = RequestMethod.POST)
  	public void getIconPage() throws Exception
  	{   	
  		int pageSize=Integer.parseInt(req.getParameter("pageSize"));
  		int pageNo=Integer.parseInt(req.getParameter("pageNo"));
  		boolean goLastPage=Boolean.parseBoolean(req.getParameter("goLastPage"));
  		String iconCode=req.getParameter("ICON_CODE");
  		String iconDesc=req.getParameter("ICON_DESC");
  		res.getWriter().print(lovService.findIconForPage(pageSize, pageNo, goLastPage, iconCode, iconDesc));
  	}
  	
    //获取功能值列表
  	@RequestMapping(value = "/getFuncPage.do", method = RequestMethod.POST)
  	public void getFuncPage() throws Exception
  	{
  		int pageSize=Integer.parseInt(req.getParameter("pageSize"));
  		int pageNo=Integer.parseInt(req.getParameter("pageNo"));
  		boolean goLastPage=Boolean.parseBoolean(req.getParameter("goLastPage"));
  		String funcCode=req.getParameter("FUNCTION_CODE");
  		String funcName=req.getParameter("FUNCTION_NAME");
  		res.getWriter().print(lovService.findFuncForPage(pageSize, pageNo, goLastPage, funcCode, funcName));
  	}
  	
    //FUNC值列表值验证
    @RequestMapping(value = "/validFuncCode.do", method = RequestMethod.POST)
    public void validFuncCode() throws Exception
    {	
      	String funcCode=req.getParameter("funccode");
      	res.getWriter().print(lovService.countByFuncCode(funcCode));
    }
      
    //通过FUNC_CODE获取FUNC_ID
    @RequestMapping(value = "/getFuncId.do", method = RequestMethod.POST)
    public void getFuncId() throws Exception
    {	
      	String funcCode=req.getParameter("funccode");
      	res.getWriter().print(lovService.findForFuncId(funcCode));
    }
  	
    //获取EMP值列表
  	@RequestMapping(value = "/getEmpPage.do", method = RequestMethod.POST)
  	public void getEmpPage() throws Exception
  	{
  		int pageSize=Integer.parseInt(req.getParameter("pageSize"));
  		int pageNo=Integer.parseInt(req.getParameter("pageNo"));
  		boolean goLastPage=Boolean.parseBoolean(req.getParameter("goLastPage"));
  		String ename=req.getParameter("ENAME");
  		String eno=req.getParameter("ENO");
  		res.getWriter().print(lovService.findEmpForPage(pageSize, pageNo, goLastPage, ename, eno));
  	}
  	
    //EMP值列表值验证
    @RequestMapping(value = "/validEmpName.do", method = RequestMethod.POST)
    public void validEmpName() throws Exception
    {	
      	String ename=req.getParameter("ename");
      	res.getWriter().print(lovService.countByEmpName(ename));
    }
      
    //通过ENAME获取EMP_ID
    @RequestMapping(value = "/getEmpId.do", method = RequestMethod.POST)
    public void getEmpId() throws Exception
    {	
      	String ename=req.getParameter("ename");
      	res.getWriter().print(lovService.findForEmpId(ename));
    }
    
    //获取GROUP值列表
  	@RequestMapping(value = "/getGroupPage.do", method = RequestMethod.POST)
  	public void getGroupPage() throws Exception
  	{
  		int pageSize=Integer.parseInt(req.getParameter("pageSize"));
  		int pageNo=Integer.parseInt(req.getParameter("pageNo"));
  		boolean goLastPage=Boolean.parseBoolean(req.getParameter("goLastPage"));
  		String groupCode=req.getParameter("GROUP_CODE");
  		String groupName=req.getParameter("GROUP_NAME");
  		res.getWriter().print(lovService.findGroupForPage(pageSize, pageNo, goLastPage, groupCode, groupName));
  	}
  	
    //GROUP值列表值验证
    @RequestMapping(value = "/validGroupName.do", method = RequestMethod.POST)
    public void validGroupCode() throws Exception
    {	
      	String groupName=req.getParameter("groupName");
      	res.getWriter().print(lovService.countByGroupCode(groupName));
    }
      
    //通过GROUP_CODE获取GROUP_ID
    @RequestMapping(value = "/getGroupId.do", method = RequestMethod.POST)
    public void getGroupId() throws Exception
    {	
      	String groupName=req.getParameter("groupName");
      	res.getWriter().print(lovService.findForGroupId(groupName));
    }
    
    //获取Organization值列表
  	@RequestMapping(value = "/getOrganPage.do", method = RequestMethod.POST)
  	public void getOrganPage() throws Exception
  	{
  		int pageSize=Integer.parseInt(req.getParameter("pageSize"));
  		int pageNo=Integer.parseInt(req.getParameter("pageNo"));
  		boolean goLastPage=Boolean.parseBoolean(req.getParameter("goLastPage"));
  		String organCode=req.getParameter("ORGANIZATION_CODE");
  		String organName=req.getParameter("ORGANIZATION_NAME");
  		res.getWriter().print(lovService.findOrganForPage(pageSize, pageNo, goLastPage, organCode, organName));
  	}
  	
  	//Organization_code值验证
  	@RequestMapping(value = "/validOrganCode.do", method = RequestMethod.POST)
    public void validOrganCode() throws Exception
    {	
  		String organCode = req.getParameter("organCode");
  		res.getWriter().print(lovService.countByOrganCode(organCode));
    }
  	
  	//通过Organization_code获取其他值
  	@RequestMapping(value = "/getOrganId.do", method = RequestMethod.POST)
    public void getOrganId() throws Exception
    {	
  		String organCode = req.getParameter("organCode");
  		res.getWriter().print(lovService.findForOrganId(organCode));
    }
  	
    //获取(建玻产业)用户Organization值列表
  	@RequestMapping(value = "/getJbUserOrganPage.do", method = RequestMethod.POST)
  	public void getJbUserOrganPage() throws Exception
  	{
  		int pageSize=Integer.parseInt(req.getParameter("pageSize"));
  		int pageNo=Integer.parseInt(req.getParameter("pageNo"));
  		boolean goLastPage=Boolean.parseBoolean(req.getParameter("goLastPage"));
  		String organCode=req.getParameter("ORGANIZATION_CODE");
  		String organName=req.getParameter("ORGANIZATION_NAME");
  		Long userId = (Long)sess.getAttribute("USER_ID");
  		String glassIndustry = "JB";
  		res.getWriter().print(lovService.findUserOrganForPage(pageSize, pageNo, goLastPage, userId, organCode, organName, glassIndustry));
  	}
  	
    //获取(浮法产业)用户Organization值列表
  	@RequestMapping(value = "/getFfUserOrganPage.do", method = RequestMethod.POST)
  	public void getFfUserOrganPage() throws Exception
  	{
  		int pageSize=Integer.parseInt(req.getParameter("pageSize"));
  		int pageNo=Integer.parseInt(req.getParameter("pageNo"));
  		boolean goLastPage=Boolean.parseBoolean(req.getParameter("goLastPage"));
  		String organCode=req.getParameter("ORGANIZATION_CODE");
  		String organName=req.getParameter("ORGANIZATION_NAME");
  		Long userId = (Long)sess.getAttribute("USER_ID");
  		String glassIndustry = "FF";
  		res.getWriter().print(lovService.findUserOrganForPage(pageSize, pageNo, goLastPage, userId, organCode, organName, glassIndustry));
  	}
  	
  	//物料值列表
  	@RequestMapping(value = "/getItemPage.do", method = RequestMethod.POST)
  	public void getItemPage() throws Exception
  	{
  		int pageSize=Integer.parseInt(req.getParameter("pageSize"));
  		int pageNo=Integer.parseInt(req.getParameter("pageNo"));
  		boolean goLastPage=Boolean.parseBoolean(req.getParameter("goLastPage"));
  		Long thickness = TypeConvert.str2Long(req.getParameter("THICKNESS"));
  		Long organizationId = TypeConvert.str2Long(req.getParameter("ORGANIZATION_ID"));
  		String coatingCode = req.getParameter("COATING_CODE");
  		String itemNumber = req.getParameter("itemNumber");
  		res.getWriter().print(lovService.findItemForPage(pageSize, pageNo, goLastPage, thickness, coatingCode, organizationId, itemNumber));
  	}
  	
    //从LOOKUP_VALUES获取Coating值列表
  	@RequestMapping(value = "/getCoatingPage.do", method = RequestMethod.POST)
  	public void getCoatingPage() throws Exception
  	{
  		int pageSize=Integer.parseInt(req.getParameter("pageSize"));
  		int pageNo=Integer.parseInt(req.getParameter("pageNo"));
  		boolean goLastPage=Boolean.parseBoolean(req.getParameter("goLastPage"));
  		String lookupType="XYG_JB_GLASS_COATING";
  		String lookupCode=req.getParameter("LOOKUP_CODE");
  		String meaning=req.getParameter("MEANING");
  		res.getWriter().print(lovService.findLookupForPage(pageSize, pageNo, goLastPage, lookupType, lookupCode, meaning));
  	}
  	
    //Coating值列表MEANING值验证
    @RequestMapping(value = "/validCoating.do", method = RequestMethod.POST)
    public void validCoating() throws Exception
    {	
      	String meaning=req.getParameter("meaning");
      	String lookupType="XYG_JB_GLASS_COATING";
      	res.getWriter().print(lovService.countByLookupMeaning(meaning, lookupType));
    }
      
    //通过MEANING获取Coating_Code
    @RequestMapping(value = "/getCoatingCode.do", method = RequestMethod.POST)
    public void getCoatingCode() throws Exception
    {	
      	String meaning=req.getParameter("meaning");
      	String lookupType="XYG_JB_GLASS_COATING";
      	res.getWriter().print(lovService.findForLookupCode(meaning, lookupType));
    }
    
    //从LOOKUP_VALUES获取Colour值列表
  	@RequestMapping(value = "/getColourPage.do", method = RequestMethod.POST)
  	public void getColourPage() throws Exception
  	{
  		int pageSize=Integer.parseInt(req.getParameter("pageSize"));
  		int pageNo=Integer.parseInt(req.getParameter("pageNo"));
  		boolean goLastPage=Boolean.parseBoolean(req.getParameter("goLastPage"));
  		String lookupType="XYG_FLOAT_COLOR";
  		String lookupCode=req.getParameter("LOOKUP_CODE");
  		String meaning=req.getParameter("MEANING");
  		res.getWriter().print(lovService.findLookupForPage(pageSize, pageNo, goLastPage, lookupType, lookupCode, meaning));
  	}
  	
  	 //Colour值列表MEANING值验证
    @RequestMapping(value = "/validColour.do", method = RequestMethod.POST)
    public void validColour() throws Exception
    {	
      	String meaning=req.getParameter("meaning");
      	String lookupType="XYG_FLOAT_COLOR";
      	res.getWriter().print(lovService.countByLookupMeaning(meaning, lookupType));
    }
      
    //通过MEANING获取ColourCode
    @RequestMapping(value = "/getColourCode.do", method = RequestMethod.POST)
    public void getColourCode() throws Exception
    {	
      	String meaning=req.getParameter("meaning");
      	String lookupType="XYG_FLOAT_COLOR";
      	res.getWriter().print(lovService.findForLookupCode(meaning, lookupType));
    }
    
    //从LOOKUP_VALUES获取Grade值列表
  	@RequestMapping(value = "/getGradePage.do", method = RequestMethod.POST)
  	public void getGradePage() throws Exception
  	{
  		int pageSize=Integer.parseInt(req.getParameter("pageSize"));
  		int pageNo=Integer.parseInt(req.getParameter("pageNo"));
  		boolean goLastPage=Boolean.parseBoolean(req.getParameter("goLastPage"));
  		String lookupType="XYG_FLOAT_GRADE";
  		String lookupCode=req.getParameter("LOOKUP_CODE");
  		String meaning=req.getParameter("MEANING");
  		res.getWriter().print(lovService.findLookupForPage(pageSize, pageNo, goLastPage, lookupType, lookupCode, meaning));
  	}
  	
  	 //Grade值列表MEANING值验证
    @RequestMapping(value = "/validGrade.do", method = RequestMethod.POST)
    public void validGrade() throws Exception
    {	
      	String meaning=req.getParameter("meaning");
      	String lookupType="XYG_FLOAT_GRADE";
      	res.getWriter().print(lovService.countByLookupMeaning(meaning, lookupType));
    }
      
    //通过MEANING获取GradeCode
    @RequestMapping(value = "/getGradeCode.do", method = RequestMethod.POST)
    public void getGradeCode() throws Exception
    {	
      	String meaning=req.getParameter("meaning");
      	String lookupType="XYG_FLOAT_GRADE";
      	res.getWriter().print(lovService.findForLookupCode(meaning, lookupType));
    }
    
    //从LOOKUP_VALUES获取Pack值列表
  	@RequestMapping(value = "/getPackPage.do", method = RequestMethod.POST)
  	public void getPackPage() throws Exception
  	{
  		int pageSize=Integer.parseInt(req.getParameter("pageSize"));
  		int pageNo=Integer.parseInt(req.getParameter("pageNo"));
  		boolean goLastPage=Boolean.parseBoolean(req.getParameter("goLastPage"));
  		String lookupType="XYG_FFI_PACK";
  		String lookupCode=req.getParameter("LOOKUP_CODE");
  		String meaning=req.getParameter("MEANING");
  		res.getWriter().print(lovService.findLookupForPage(pageSize, pageNo, goLastPage, lookupType, lookupCode, meaning));
  	}
  	
  	 //Pack值列表MEANING值验证
    @RequestMapping(value = "/validPack.do", method = RequestMethod.POST)
    public void validPack() throws Exception
    {	
      	String meaning=req.getParameter("meaning");
      	String lookupType="XYG_FFI_PACK";
      	res.getWriter().print(lovService.countByLookupMeaning(meaning, lookupType));
    }
      
    //通过MEANING获取PackCode
    @RequestMapping(value = "/getPackCode.do", method = RequestMethod.POST)
    public void getPackCode() throws Exception
    {	
      	String meaning=req.getParameter("meaning");
      	String lookupType="XYG_FFI_PACK";
      	res.getWriter().print(lovService.findForLookupCode(meaning, lookupType));
    }
}
