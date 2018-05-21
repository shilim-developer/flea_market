package com.market.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import sun.misc.BASE64Decoder;

@SuppressWarnings("restriction")
public class Base64Util {
	// base64字符串转化成图片
	public static boolean GenerateImage(String imgStr, String saveUrl) {
		if (imgStr == null) // 图像数据为空
			return false;
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			// Base64解码
			byte[] b = decoder.decodeBuffer(imgStr.substring(imgStr.indexOf(",")+1));
			for (int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {// 调整异常数据
					b[i] += 256;
				}
			}
			// 生成jpeg图片
			String dirUrl = saveUrl.substring(0, saveUrl.lastIndexOf("/")).replace("/", File.separator);
			File file = new File(dirUrl);
			if(!file.exists() && !file.isDirectory()) {
				file.mkdirs();
				System.out.println("新建文件夹");
			}
			OutputStream out = new FileOutputStream(saveUrl);
			out.write(b);
			out.flush();
			out.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/** 
	 *通过图片base64流判断图片等于多少字节 
	 *image 图片流 
	 */  
	public static Integer imageSize(String image){ 
		String str = image;
		if(image.indexOf("data:image")>=0) {
			str = image.substring(22); // 1.需要计算文件流大小，首先把头部的data:image/png;base64,（注意有逗号）去掉。  
		}
		Integer equalIndex= str.indexOf("=");//2.找到等号，把等号也去掉  
		if(str.indexOf("=")>0) {  
			str=str.substring(0, equalIndex);  
		}  
		Integer strLength=str.length();//3.原来的字符流大小，单位为字节  
		Integer size=strLength-(strLength/8)*2;//4.计算后得到的文件流大小，单位为字节  
		return size;  
	}  
}
