package com.xinyiglass.springSample.util;

import java.security.MessageDigest;  
  
/** 
 * 采用MD5加密解密 
 * @author bird 
 * @datetime 2016-8-30 
 */  
public class MD5Util {  
  
    /*** 
     * MD5加码 生成32位md5码 
     */  
	
	//添加盐值  一次加密
	public static String string2MD5(String inStr,String salt){ 
		MessageDigest md5 = null;  
        try{  
            md5 = MessageDigest.getInstance("MD5");  
        }catch (Exception e){  
            System.out.println(e.toString());  
            e.printStackTrace();  
            return "";  
        }  
        if(salt!=null){
        	inStr = inStr +salt;
        }
        char[] charArray = inStr.toCharArray();  
        byte[] byteArray = new byte[charArray.length];  
  
        for (int i = 0; i < charArray.length; i++)  
            byteArray[i] = (byte) charArray[i];  
        byte[] md5Bytes = md5.digest(byteArray);  
        
        StringBuffer hexValue = new StringBuffer();  
        for (int i = 0; i < md5Bytes.length; i++){  
            int val = ((int) md5Bytes[i]) & 0xff;  
            if (val < 16)  
                hexValue.append("0");  
            hexValue.append(Integer.toHexString(val));  
        }  
        return hexValue.toString();  
	}
	
	//不添加盐值 一次加密
    public static String string2MD5(String inStr){  
        return string2MD5(inStr,null);
    }  
    
    //不添加盐值 多次加密
    public static String string2MD5(String inStr,int times){  
    	String hexValue = null;
    	for(int j = 0;j<times;j++){
    		if(j==0){
    			hexValue = string2MD5(inStr);
    		}else{
    			hexValue = string2MD5(hexValue);
    		}
    	}
    	return hexValue;
    }
    
    //不添加盐值 多次加密
    public static String string2MD5(String inStr,int times,String salt){  
    	String hexValue = null;
    	for(int j = 0;j<times;j++){
    		if(j==0){
    			hexValue = string2MD5(inStr,salt);
    		}else{
    			hexValue = string2MD5(hexValue);
    		}
    	}
    	return hexValue;
    }
  
    // 测试主函数  
    public static void main(String args[]) {  
    	/*
    	StringBuffer sb = new StringBuffer(); 
    	for(int i=0;i<=1000000;i++){    
    		if(i==0){
    			sb.append("B");
    		}else{
    			sb.append("A");
    			if(i==1000000){
    				System.out.println("length:"+sb.length());
    				System.out.println("MD5加密后：" + string2MD5(sb.toString())); 
    				System.out.println("MD5加密后length：" + string2MD5(sb.toString()).length()); 
    			}
    		}
        }
        */
        String s = new String("123456");  
        int times = 3;
        String salt = "@X@Y@G@";
        System.out.println("MD5加密前：" + s);  
        System.out.println("MD5不加盐加密一次后：" + string2MD5(s)); 
        System.out.println("MD5加盐加密一次后：" + string2MD5(s,salt)); 
        System.out.println("MD5不加盐加密三次后：" + string2MD5(s,times));
        System.out.println("MD5加盐加密三次后：" + string2MD5(s,times,salt));
    }  
}  


