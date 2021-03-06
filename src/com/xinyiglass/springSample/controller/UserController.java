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
import org.springframework.web.servlet.ModelAndView;

import xygdev.commons.entity.PlsqlRetValue;
import xygdev.commons.util.TypeConvert;

import com.xinyiglass.springSample.entity.UserVO;
import com.xinyiglass.springSample.service.UserVOService;
import com.xinyiglass.springSample.util.Base64Convert;
import com.xinyiglass.springSample.util.Constant;
import com.xinyiglass.springSample.util.MD5Util;

@Controller
@RequestMapping("/user")
@Scope("prototype")
public class UserController {
	
	@Autowired
	UserVOService UVS;
	
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
    
    @RequestMapping(value = "/insert.do", method = RequestMethod.POST)
	public void insret() throws Exception
	{ 
    	UserVO u = new UserVO();
    	u.setUserName(req.getParameter("USER_NAME").toUpperCase());
    	u.setDescription(req.getParameter("DESC"));
    	u.setStartDate(TypeConvert.str2uDate(req.getParameter("START_DATE")));
    	u.setEndDate(TypeConvert.str2uDate(req.getParameter("END_DATE")));
    	u.setEncryptedUserPassword(MD5Util.string2MD5(req.getParameter("PASSWORD")));
    	u.setRespId(TypeConvert.str2Long(req.getParameter("RESP_ID")));
    	u.setEmpId(TypeConvert.str2Long(req.getParameter("EMP_ID")));
    	u.setUserType(req.getParameter("USER_TYPE"));
        u.setUserGroupId(TypeConvert.str2Long(req.getParameter("GROUP_ID")));
    	u.setImgUrl("default.png");
    	res.getWriter().print(UVS.insert(u,loginId).toJsonStr());
	}
    
    @RequestMapping(value = "/update.do", method = RequestMethod.POST)
	public void update() throws Exception
	{ 
    	Long userId = Long.parseLong(req.getParameter("U_ID"));
		UserVO lockUserVO = (UserVO)sess.getAttribute("lockUserVO");
		if (lockUserVO ==null){
			throw new RuntimeException("更新数据出错:会话数据已经无效!请返回再重新操作!");
		}
		if (!userId.equals(lockUserVO.getUserId())){
			throw new RuntimeException("更新的数据无法匹配!请重新查询!");
		}
		UserVO u = new UserVO();//复制对象！
		u = (UserVO) lockUserVO.clone();		
		u.setUserId(userId);
		u.setUserName(req.getParameter("USER_NAME").toUpperCase());
    	u.setDescription(req.getParameter("DESC"));
    	u.setStartDate(TypeConvert.str2uDate(req.getParameter("START_DATE")));
    	u.setEndDate(TypeConvert.str2uDate(req.getParameter("END_DATE")));
    	String password = req.getParameter("PASSWORD");
    	if(password==null||password.equals("")){
    		u.setEncryptedUserPassword(req.getParameter("ENCRYPTED_USER_PASSWORD"));
    		u.setPasswordDate(TypeConvert.str2uDate(req.getParameter("PASSWORD_DATE")));
    	}else{
    		u.setEncryptedUserPassword(MD5Util.string2MD5(password));
    		u.setPasswordDate(null);
    	}
    	u.setRespId(TypeConvert.str2Long(req.getParameter("RESP_ID")));
    	u.setEmpId(TypeConvert.str2Long(req.getParameter("EMP_ID")));
    	u.setUserType(req.getParameter("USER_TYPE"));
    	u.setUserGroupId(TypeConvert.str2Long(req.getParameter("GROUP_ID")));
    	u.setImgUrl(req.getParameter("IMG_URL"));
    	res.getWriter().print(UVS.update(lockUserVO, u,loginId).toJsonStr());
	}
    
    @RequestMapping("/userManage.do")
	public String listEmpVO(){
		return "basic/userManage";
	}
    
    @RequestMapping(value = "/getUserPage.do", method = RequestMethod.POST)
	public void getEmpPage() throws Exception
	{   	
		int pageSize=Integer.parseInt(req.getParameter("pageSize"));
		int pageNo=Integer.parseInt(req.getParameter("pageNo"));
		boolean goLastPage=Boolean.parseBoolean(req.getParameter("goLastPage"));
		Map<String,Object> conditionMap=new HashMap<String,Object>();
		conditionMap.put("userId", TypeConvert.str2Long(req.getParameter("USER_ID")));
		conditionMap.put("respId", TypeConvert.str2Long(req.getParameter("RESP_ID")));
		conditionMap.put("userType", req.getParameter("USER_TYPE"));
		conditionMap.put("startDate_F", TypeConvert.str2uDate(req.getParameter("START_DATE_F")));
		conditionMap.put("startDate_T", TypeConvert.str2uDate(req.getParameter("START_DATE_T")));
		conditionMap.put("endDate_F", TypeConvert.str2uDate(req.getParameter("END_DATE_F")));
		conditionMap.put("endDate_T", TypeConvert.str2uDate(req.getParameter("END_DATE_T")));
		conditionMap.put("orderBy", req.getParameter("orderby"));
		//test并发安全
		/*if(loginId==75){//uvos.getLoginId()!=null&&uvos.getLoginId()
			System.out.println(Thread.currentThread().getId()+":WAITTING...该线程应该的登录ID:"+loginId+",会话:"+sess);
			try {
				Thread.sleep(20000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getId()+":WAITTING END!");
		 }
		System.out.println(Thread.currentThread().getId()+":-->该线程匹配的longId:"+loginId+",会话:"+sess+",Serv:"+UVS);
		 */
		res.getWriter().print(UVS.findForPage(pageSize, pageNo, goLastPage, conditionMap,loginId));
	}
    
    @RequestMapping(value = "/userPreUpdate.do", method = RequestMethod.POST)
    public void userPreUpdate() throws Exception
    {
    	Long userId = Long.parseLong(req.getParameter("USER_ID"));
    	UserVO userVO = UVS.findForUserVOById(userId,loginId);
    	sess.setAttribute("lockUserVO", userVO);//记录在session变量
    	res.getWriter().print(UVS.findUserByIdForJSON(userId,loginId));
    }
    
    @RequestMapping(value = "/setUserImg.do", method = RequestMethod.POST)
    public void setUserImg() throws Exception
    {
    	Long userId = Long.parseLong(req.getParameter("userId"));
    	String fileName=req.getParameter("fileName");
    	String strBase64=req.getParameter("img"); 
    	strBase64=strBase64.substring(22);
    	//String basePath=req.getSession().getServletContext().getRealPath("/"); 
    	//System.out.println(basePath);
    	//String path=basePath+"plugin\\img\\user\\";
    	//设置Tomcat本地文件服务器地址
    	//String path="/ebs/data/image/user/";
    	String path=Constant.IMAGE_USER_PATH;
    	Base64Convert.base64ToIo(strBase64, path, fileName);
        res.getWriter().print(UVS.updateImgUrl(fileName, userId,loginId).toJsonStr());
    }
    
    //非Ajax请求
    @RequestMapping(value = "/updatePWD.do", method = RequestMethod.POST)
    public ModelAndView updatePWD() throws Exception
    {   
    	ModelAndView mv = new ModelAndView();
    	Long userId = (Long)sess.getAttribute("USER_ID");
    	String oldPassword = MD5Util.string2MD5(req.getParameter("O_PASSWORD"));
    	String newPassword = MD5Util.string2MD5(req.getParameter("N_PASSWORD"));
    	PlsqlRetValue ret=UVS.updatePWD(userId, oldPassword, newPassword,loginId);
		int retCode = ret.getRetcode();
        if(retCode==0){
            mv.setViewName("redirect:/index.do");
        }else if(retCode==2){
        	mv.setViewName("redirect:/modifyPWD.do");
			sess.setAttribute("errorMsg", ret.getErrbuf());
        }
        return mv;
    }
    
    //Ajax请求
    @RequestMapping(value = "/updatePassword.do", method = RequestMethod.POST)
    public void updatePassword() throws Exception{
    	Long userId = (Long)sess.getAttribute("USER_ID");
    	String oldPassword = MD5Util.string2MD5(req.getParameter("O_PASSWORD"));
    	String newPassword = MD5Util.string2MD5(req.getParameter("N_PASSWORD"));
    	res.getWriter().print(UVS.updatePWD(userId, oldPassword, newPassword,loginId).toJsonStr());
    }
}
