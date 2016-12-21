package com.xinyiglass.springSample.util;

import java.io.IOException;  
import java.io.InputStream;
import java.io.PrintWriter;  
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;

import org.apache.commons.net.PrintCommandListener;  
import org.apache.commons.net.ftp.FTPClient;  
import org.apache.commons.net.ftp.FTPFile;  
import org.apache.commons.net.ftp.FTPReply;  

public class IterateFtpDir {
    //private static Logger logger = Logger.getLogger(FTPListAllFiles.class);  
    private FTPClient ftp; 
    //public ArrayList<String> arFiles;  

	public FTPClient getFtp() {
		return ftp;
	}
    
	public void log(String logMessage){
		System.out.println(logMessage);
	}
	
    /** 
     * 重载构造函数 
     * @param isPrintCommmand 是否打印与FTPServer的交互命令 
     */  
    public IterateFtpDir(boolean isPrintCommmand){  
        ftp = new FTPClient();  
        try {
			this.login(Constant.FTP_HOST, Constant.FTP_PORT, Constant.FTP_USER, Constant.FTP_PASS);
		} catch (IOException e) {
			e.printStackTrace();
		}
        if(isPrintCommmand){  
            ftp.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));  
        }  
    } 

    /** 
     * 登陆FTP服务器 
     * @param host FTPServer IP地址 
     * @param port FTPServer 端口 
     * @param username FTPServer 登陆用户名 
     * @param password FTPServer 登陆密码 
     * @return 是否登录成功 
     * @throws IOException 
     */  
    public boolean login(String host,int port,String username,String password) throws IOException{  
        this.ftp.connect(host,port);  
        if(FTPReply.isPositiveCompletion(this.ftp.getReplyCode())){  
            if(this.ftp.login(username, password)){  
                //设置编码格式，解决中文乱码问题  
                if (FTPReply.isPositiveCompletion(ftp.sendCommand("OPTS UTF8", "ON"))) {
                	Constant.LOCAL_CHARSET = "UTF-8";  // 开启服务器对UTF-8的支持，如果服务器支持就用UTF-8编码，否则就使用本地编码（GBK）.  
                }  
                ftp.setControlEncoding(Constant.LOCAL_CHARSET);  
                return true;  
            }  
        }  
        if(this.ftp.isConnected()){  
            this.ftp.disconnect();  
        }  
        return false;  
    }  
      
    /** 
     * 关闭数据链接 
     * @throws IOException 
     */  
    public void disConnection() throws IOException{  
        if(this.ftp.isConnected()){  
        	this.ftp.logout();
            this.ftp.disconnect();  
        }  
    }  

	//获取文件类型
	@SuppressWarnings("unused")
	private static String getFileType(String fileName) {
		return fileName.substring(fileName.lastIndexOf(".")+1).toUpperCase();
	}
	
	//获取文件名称
	public static String getFileName(String filePath) {
		if(filePath==null||filePath.length()==0){
			return "";
		}else{
			String [] fileItems=filePath.split(Constant.DIR_SEP);
			return fileItems[fileItems.length-1];
		}
	}
	//取得文件大小
	public long getFileSizes(FTPFile f) throws IOException{
        long s=0;
        if(f!=null) s=f.getSize();
        return s;
    }
	//转换文件大小
	public static String FormetFileSize(long fileS) {
        DecimalFormat df = new DecimalFormat("#.00");
        String fileSizeString = "";
        if (fileS==0){
        	fileSizeString = "文件无法读取!";
        } else if (fileS < 1024){
            fileSizeString = df.format((double) fileS) + "B";
        } else if (fileS < 1048576) {
            fileSizeString = df.format((double) fileS / 1024) + "KB";
        } else if (fileS < 1073741824) {
            fileSizeString = df.format((double) fileS / 1048576) + "M";
        } else {
            fileSizeString = df.format((double) fileS / 1073741824) + "G";
        }
        return fileSizeString;
    }
	
	//解决中文乱码的问题。因为服务器的字符集永远是默认的"ISO-8859-1"，不会变更。
	//http://blog.csdn.net/hehuang139/article/details/9072243
	public static String str2FtpCharset(String x){
		String ret=null;
		try {
			ret= new String(x.getBytes(Constant.LOCAL_CHARSET), Constant.SERVER_CHARSET);
		} catch (UnsupportedEncodingException e) {
			ret=x;
			e.printStackTrace();
		}
		return ret;
	}
	
	//文件上传
	public boolean uploadFile(String pathname,String fileName,InputStream input) throws IOException{
		boolean ret=true;
        if(!this.ftp.changeWorkingDirectory(str2FtpCharset(pathname))){
        	LogUtil.log("upload无法切换到FTP对应的工作目录pathname！");
        	return false;
        }; 
		this.ftp.setBufferSize(1024);
        ftp.setFileType(FTPClient.BINARY_FILE_TYPE);   // 设置文件类型（二进制）   
        ftp.storeFile(str2FtpCharset(fileName), input);  // 将上传文件存储到指定目录
		return ret;
	}
	
	 //检查ftp目录 是否存在
    public boolean checkDirectory(String dirName) throws IOException {  
        boolean _flag = false;  
        FTPFile[] files = ftp.listFiles();  
        for (int i = 0; i < files.length; i++) {  
            FTPFile file = files[i];  
            if (file.isDirectory()) {  
            	//System.out.print("确认:"+file.getName());
                if (dirName.equalsIgnoreCase(file.getName())) {  
                    _flag = true;  
                    break;  
                }  
            }  
        }  
        return _flag;  
    }  
}
