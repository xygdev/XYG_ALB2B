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

import com.xinyiglass.springSample.entity.FuncVO;
import com.xinyiglass.springSample.service.FuncVOService;

@Controller
@RequestMapping("/func")
public class FuncController {
    
	@Autowired
	FuncVOService fvs;
	
	
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
    
    @RequestMapping("/setFuncId.do")
	public void setFuncId(){
    	Long funcId = TypeConvert.str2Long(req.getParameter("FUNC_ID"));
    	sess.setAttribute("FUNC_ID", funcId);
    }
	
	@RequestMapping("/funcManage.do")
	public String listFuncVO(){
		if(sess.getAttribute("USER_ID")!=null&&sess.getAttribute("USER_ID").toString().length()>0){
			return "basic/funcManage";
		}else{
			return "error/sessionTimeout";
		}
	}
	
	@RequestMapping(value = "/getFuncPage.do", method = RequestMethod.POST)
	public void getFuncPage() throws Exception
	{   	
		int pageSize=Integer.parseInt(req.getParameter("pageSize"));
		int pageNo=Integer.parseInt(req.getParameter("pageNo"));
		boolean goLastPage=Boolean.parseBoolean(req.getParameter("goLastPage"));
		Long funcId = TypeConvert.str2Long(req.getParameter("FUNCTION_ID"));
		String orderBy=req.getParameter("orderby");
		res.getWriter().print(fvs.findForPage(pageSize, pageNo, goLastPage, funcId, orderBy));
	}
	
	@RequestMapping(value = "/insert.do", method = RequestMethod.POST)
	public void insret() throws Exception
	{ 
    	FuncVO f = new FuncVO();
    	f.setFunctionCode(req.getParameter("FUNCTION_CODE"));
    	f.setFunctionName(req.getParameter("FUNCTION_NAME"));
    	f.setFunctionHref(req.getParameter("FUNCTION_HREF"));
    	f.setIconId(TypeConvert.str2Long(req.getParameter("ICON_ID")));
    	f.setDescription(req.getParameter("DESCRIPTION"));
    	res.getWriter().print(fvs.insert(f).toJsonStr());
	}
	
	@RequestMapping(value = "/update.do", method = RequestMethod.POST)
	public void update() throws Exception
	{
    	Long funcId = Long.parseLong(req.getParameter("FUNCTION_ID"));
    	FuncVO lockFuncVO = (FuncVO)sess.getAttribute("lockFuncVO");
    	if (lockFuncVO ==null){
			throw new RuntimeException("更新数据出错:会话数据已经无效!请返回再重新操作!");
		}
		if (!funcId.equals(lockFuncVO.getFunctionId())){
			throw new RuntimeException("更新的数据无法匹配!请重新查询!");
		}
		FuncVO f = new FuncVO();
		f = (FuncVO)lockFuncVO.clone();
		f.setFunctionId(funcId);
		f.setFunctionCode(req.getParameter("FUNCTION_CODE"));
    	f.setFunctionName(req.getParameter("FUNCTION_NAME"));
    	f.setFunctionHref(req.getParameter("FUNCTION_HREF"));
    	f.setIconId(TypeConvert.str2Long(req.getParameter("ICON_ID")));
    	f.setDescription(req.getParameter("DESCRIPTION"));
    	res.getWriter().print(fvs.update(lockFuncVO, f).toJsonStr());
	}
	
	@RequestMapping(value = "/preUpdate.do", method = RequestMethod.POST)
    public void preUpdate() throws Exception
    {
    	Long funcId = Long.parseLong(req.getParameter("FUNCTION_ID"));
    	FuncVO funcVO = fvs.findForFuncVOById(funcId);
    	sess.setAttribute("lockFuncVO", funcVO);//记录在session变量
    	res.getWriter().print(fvs.findRespByIdForJSON(funcId));
    }
}
