package com.xinyiglass.springSample.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xinyiglass.springSample.service.ApService;
import com.xinyiglass.springSample.util.IterateFtpDir;
import com.xinyiglass.springSample.util.Constant;

import xygdev.commons.util.TypeConvert;

@Controller
@RequestMapping("/ap")
@Scope("prototype")
public class ApController {
	@Autowired
	ApService as;
	
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
    
    @RequestMapping("/apQuery.do")
	public String listApQuery(){
		return "ap/apQuery";
	}
    
    @RequestMapping("/reqApQuery.do")
    public void reqApQuery() throws Exception{
    	Long orgId = TypeConvert.str2Long(req.getParameter("ORG_ID"));
    	Long custId = TypeConvert.str2Long(req.getParameter("CUSTOMER_ID"));
    	String apDate = req.getParameter("AP_DATE");
    	Long userId = (Long)sess.getAttribute("USER_ID");
    	as.apQuery(orgId, custId, apDate, userId,loginId); 	
    }
    
    @RequestMapping(value = "/getApHistory.do", method = RequestMethod.POST)
   	public void getApHistory() throws Exception
   	{   	
   		int pageSize=Integer.parseInt(req.getParameter("pageSize"));
   		int pageNo=Integer.parseInt(req.getParameter("pageNo"));
   		boolean goLastPage=Boolean.parseBoolean(req.getParameter("goLastPage"));
   		String orderBy=req.getParameter("orderby");
   		Long userId = (Long)sess.getAttribute("USER_ID");
   		res.getWriter().print(as.findForPage(pageSize, pageNo, goLastPage, userId, orderBy,loginId));
   	}

    @RequestMapping(value = "/getApOutput.do")
   	public void getApOutput() throws Exception
   	{   
    	//LogUtil.log("sess.getAttribute(USER_ID)3:"+sess.getAttribute("USER_ID"));
    	if(TypeConvert.isNullValue(sess.getAttribute("USER_ID"))){//以后要改在Fiter里面统一监控。
    		req.getRequestDispatcher("/WEB-INF/page/error/sessionTimeout.jsp").forward(req,res);
    	}else{
       		int requestId=Integer.parseInt(req.getParameter("REQUEST_ID"));
    		String filePath = Constant.CONC_OUT;
    		String fileName=requestId+".pdf";
    		String fileDownload=filePath+Constant.DIR_SEP+fileName;
    		if (fileDownload!=null&&fileDownload.trim().length()!=0){
    			//LogUtil.log("fileName:"+fileName+",fileDownload:"+fileDownload);
    	   		res.setContentType("application/pdf");
    			String agent = req.getHeader("USER-AGENT");
				if (agent!=null&&agent.toUpperCase().indexOf("MSIE") != -1) {//IE内核浏览器
					fileName = URLEncoder.encode(fileName, "UTF-8");
					fileName = fileName.replace("+", "%20");//处理IE文件名中有空格会变成+"的问题;
				} else {
					fileName = URLDecoder.decode(fileName,"UTF-8");
					Base64 b64=new Base64();//火狐文件名空格被截断问题
					fileName = "=?UTF-8?B?" + (new String (b64.encode(fileName.getBytes("UTF-8")))) + "?="; 
				}
    			res.addHeader("Content-Disposition", "inline;filename="+fileName);//inline(打开),attachment下载
    			java.io.OutputStream outp=null;
    			java.io.FileInputStream in = null;
    			java.io.InputStream ips = null;
    			IterateFtpDir ftp=null;
    			try{
    				outp=res.getOutputStream();
    				byte[] b=new byte[1024];
    				int i=0;
    				ftp=new IterateFtpDir(false);
    				ftp.getFtp();
    				ftp.getFtp().setFileType(FTPClient.BINARY_FILE_TYPE);  
    				ftp.getFtp().enterLocalPassiveMode();  
    				ips = ftp.getFtp().retrieveFileStream(IterateFtpDir.str2FtpCharset(fileDownload)); 
    				if(ips!=null){
        				while((i=ips.read(b))>0){
        					outp.write(b,0,i);
        				}
    				}else{
    					throw new RuntimeException("文件不存在！该请求"+requestId+"可能执行失败，或者输出文件被清理！");
    				}
    				outp.flush();
    				outp.close();
    			}finally{
    				if(in!=null)in.close();
    				if(outp!=null)outp.close();
    				if(ips!=null)ips.close();
    				if(ftp!=null)ftp.disConnection();
    				//System.out.println("close .");
    			}
    		}else{
    			throw new RuntimeException("fileDownload path is null!");
    		}
    	}
   	}
}
