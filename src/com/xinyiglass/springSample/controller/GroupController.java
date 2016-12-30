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

import com.xinyiglass.springSample.entity.GroupHeaderVO;
import com.xinyiglass.springSample.entity.GroupLineVO;
import com.xinyiglass.springSample.service.CustGroupService;
import com.xinyiglass.springSample.service.GroupHeaderVOService;
import com.xinyiglass.springSample.service.GroupLineVOService;

@Controller
@RequestMapping("/group")
@Scope("prototype")
public class GroupController {
    
	@Autowired
	CustGroupService gvs;
	@Autowired
	GroupHeaderVOService ghvs;
	@Autowired
	GroupLineVOService glvs;
	
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
    
    /************************客户组分配页码方法************************/
    @RequestMapping("/groupMatch.do")
	public String listGroupMatch(){
    	return "basic/groupMatch";
	}
    
    @RequestMapping(value = "/getMatchPage.do", method = RequestMethod.POST)
	public void getMatchPage() throws Exception
	{   	
		int pageSize=Integer.parseInt(req.getParameter("pageSize"));
		int pageNo=Integer.parseInt(req.getParameter("pageNo"));
		boolean goLastPage=Boolean.parseBoolean(req.getParameter("goLastPage"));
   		Map<String,Object> conditionMap=new HashMap<String,Object>();
   		conditionMap.put("orderBy", req.getParameter("orderby"));
   		conditionMap.put("custId", TypeConvert.str2Long(req.getParameter("CUSTOMER_ID")));
		res.getWriter().print(gvs.findForCustPage(pageSize, pageNo, goLastPage, conditionMap,loginId));
	}
    
    @RequestMapping(value = "/preUpdateCustGroup.do", method = RequestMethod.POST)
    public void preUpdateCustGroup() throws Exception
    {
    	Long orgId = Long.parseLong(req.getParameter("ORG_ID"));
    	Long custId = Long.parseLong(req.getParameter("CUST_ACCOUNT_ID"));
    	res.getWriter().print(gvs.findCustForJSON(orgId, custId,loginId));
    }
    
    @RequestMapping(value = "/updateCustGroup.do", method = RequestMethod.POST)
    public void updateCustGroup() throws Exception
    {
    	Long orgId = Long.parseLong(req.getParameter("ORG_ID"));
    	Long custAccountId = Long.parseLong(req.getParameter("CUST_ACCOUNT_ID"));
    	Long groupId = Long.parseLong(req.getParameter("GROUP_ID"));
    	res.getWriter().print(gvs.update(orgId, custAccountId, groupId,loginId).toJsonStr());
    }
    /************************客户组分配页码方法************************/
    
    @RequestMapping("/groupManage.do")
	public String listGroupManage(){
		return "basic/groupManage";
	}
    
    @RequestMapping(value = "/getGroupHeaderPage.do", method = RequestMethod.POST)
	public void getGroupHeaderPage() throws Exception
	{   	
		int pageSize=Integer.parseInt(req.getParameter("pageSize"));
		int pageNo=Integer.parseInt(req.getParameter("pageNo"));
		boolean goLastPage=Boolean.parseBoolean(req.getParameter("goLastPage"));
   		Map<String,Object> conditionMap=new HashMap<String,Object>();
   		conditionMap.put("orderBy", req.getParameter("orderby"));
   		conditionMap.put("groupId", TypeConvert.str2Long(req.getParameter("GROUP_ID")));
		res.getWriter().print(ghvs.findForPage(pageSize, pageNo, goLastPage, conditionMap,loginId));
	}
    
    @RequestMapping(value = "/preUpdateGroupHeader.do", method = RequestMethod.POST)
    public void preUpdateGroupHeader() throws Exception
    {
    	Long groupId = Long.parseLong(req.getParameter("GROUP_ID"));
    	GroupHeaderVO groupVO = ghvs.findForGroupVOById(groupId,loginId);
    	sess.setAttribute("lockGroupHeaderVO", groupVO);//记录在session变量
    	res.getWriter().print(ghvs.findGroupByIdForJSON(groupId,loginId));
    }
    
    @RequestMapping(value = "/insertGroupHeader.do", method = RequestMethod.POST)
	public void insretGroupHeader() throws Exception
	{ 
    	GroupHeaderVO g = new GroupHeaderVO();
    	g.setGroupCode(req.getParameter("GROUP_CODE"));
    	g.setGroupName(req.getParameter("GROUP_NAME"));
    	g.setDescription(req.getParameter("DESCRIPTION"));
    	res.getWriter().print(ghvs.insert(g,loginId).toJsonStr());
	}
    
    @RequestMapping(value = "/updateGroupHeader.do", method = RequestMethod.POST)
   	public void updateGroupHeader() throws Exception
   	{
    	Long groupId = Long.parseLong(req.getParameter("GROUP_ID"));
       	GroupHeaderVO lockGroupVO = (GroupHeaderVO)sess.getAttribute("lockGroupHeaderVO");
       	if (lockGroupVO ==null){
   			throw new RuntimeException("更新数据出错:会话数据已经无效!请返回再重新操作!");
   		}
   		if (!groupId.equals(lockGroupVO.getGroupId())){
   			throw new RuntimeException("更新的数据无法匹配!请重新查询!");
   		}
   		GroupHeaderVO g = new GroupHeaderVO();
   		g =  (GroupHeaderVO)lockGroupVO.clone();
   		g.setGroupId(groupId);
   		g.setGroupCode(req.getParameter("GROUP_CODE"));
   		g.setGroupName(req.getParameter("GROUP_NAME"));
   		g.setDescription(req.getParameter("DESCRIPTION"));
       	res.getWriter().print(ghvs.update(lockGroupVO, g,loginId).toJsonStr());
   	}
    
    @RequestMapping(value = "/getGroupLinePage.do", method = RequestMethod.POST)
	public void getGroupLinePage() throws Exception
	{   	
		int pageSize=Integer.parseInt(req.getParameter("pageSize"));
		int pageNo=Integer.parseInt(req.getParameter("pageNo"));
		boolean goLastPage=Boolean.parseBoolean(req.getParameter("goLastPage"));
   		Map<String,Object> conditionMap=new HashMap<String,Object>();
   		conditionMap.put("orderBy", req.getParameter("orderby"));
   		conditionMap.put("groupId", Long.parseLong(req.getParameter("GROUP_ID")));
		res.getWriter().print(glvs.findForPage(pageSize, pageNo, goLastPage, conditionMap,loginId));
	}
    
    @RequestMapping("/getAutoAddSeq.do")
    public void getAutoAddSeq() throws Exception
	{
    	Long groupId = Long.parseLong(req.getParameter("GROUP_ID"));
    	res.getWriter().print(glvs.findAutoAddSequence(groupId,loginId));
	}
    
    @RequestMapping("/preUpdateGroupLine.do")
    public void preUpdateGroupLine() throws Exception
    {
    	Long groupId = Long.parseLong(req.getParameter("GROUP_ID"));
    	Long groupSeq = Long.parseLong(req.getParameter("GROUP_SEQUENCE"));
    	GroupLineVO groupVO = glvs.findForGroupVOById(groupId, groupSeq,loginId);
    	sess.setAttribute("lockGroupLineVO", groupVO);//记录在session变量
    	res.getWriter().print(glvs.findGroupLineForJSON(groupId, groupSeq,loginId));
    }
    
    @RequestMapping(value = "/insertGroupLine.do", method = RequestMethod.POST)
	public void insretGroupLine() throws Exception
	{   
    	GroupLineVO g = new GroupLineVO();
    	g.setGroupId(TypeConvert.str2Long(req.getParameter("GROUP_ID")));
    	g.setGroupSequence(TypeConvert.str2Long(req.getParameter("GROUP_SEQUENCE")));
    	g.setSubGroupId(TypeConvert.str2Long(req.getParameter("SUB_GROUP_ID")));
    	g.setEnabledFlag(req.getParameter("ENABLED_FLAG"));
    	res.getWriter().print(glvs.insert(g,loginId).toJsonStr());
	}
    
    @RequestMapping(value = "/updateGroupLine.do", method = RequestMethod.POST)
   	public void updateGroupLine() throws Exception
   	{
       	Long groupId = TypeConvert.str2Long(req.getParameter("GROUP_ID"));
       	GroupLineVO lockGroupLineVO = (GroupLineVO)sess.getAttribute("lockGroupLineVO");
       	if (lockGroupLineVO == null){
   			throw new RuntimeException("更新数据出错:会话数据已经无效!请返回再重新操作!");
   		}
   		if (!groupId.equals(lockGroupLineVO.getGroupId())){
   			throw new RuntimeException("更新的数据无法匹配!请重新查询!");
   		}
   		GroupLineVO g = new GroupLineVO();
   		g = (GroupLineVO)lockGroupLineVO.clone();
    	g.setGroupId(groupId);
    	g.setGroupSequence(TypeConvert.str2Long(req.getParameter("GROUP_SEQUENCE")));
    	g.setSubGroupId(TypeConvert.str2Long(req.getParameter("SUB_GROUP_ID")));
    	g.setEnabledFlag(req.getParameter("ENABLED_FLAG"));
    	res.getWriter().print(glvs.update(lockGroupLineVO, g,loginId).toJsonStr());
   	}
}
