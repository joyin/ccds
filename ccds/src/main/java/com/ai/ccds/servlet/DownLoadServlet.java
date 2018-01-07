package com.ai.ccds.servlet;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.ai.ContentType;
import com.ai.mongodbToFile.FileCatcheUtile;
import com.ai.mongodbToFile.MongoDBFileUtil;

/**
 * 文件下载类
 * 描述：通过banchId、附件名获取mongodb中的文件
 * @author 陈嘉瑛
 * @version 2016-05-09
 */
@SuppressWarnings("serial")
public class DownLoadServlet extends HttpServlet{
	/**
	 * 以get方式请求
	 * 作者：陈嘉瑛
	 * 时间：2016-05-09
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response){
		try {
			BufferedOutputStream output = new BufferedOutputStream(response.getOutputStream());
			ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
			InputStream in = null;
			try {
				//截取文件后缀
				String fileType = "";
				//mongodb中的附件名称
				String newFileName = null;
				//附件原有名称
				String oldFileName = null;
				//用户传过来mongodb中的附件名
				String fileName = request.getParameter("fileName");
				if(!StringUtils.isEmpty(fileName)) {
					//附件名非空，从mongodb中直接获取
					newFileName = oldFileName = fileName;
					//先从缓存中获取附件
					in = FileCatcheUtile.getFileFromCatche4InPStream(newFileName);
					//如果缓存中没有就从mongodb中获取
					if(in == null) in = MongoDBFileUtil.findFile(fileName);
				}
				int lastPoint = oldFileName.toLowerCase().lastIndexOf(".");
				if(lastPoint != -1) 
					fileType = oldFileName.toLowerCase().substring(lastPoint + 1);
				//设置文件名称
				response.setContentType(ContentType.get(fileType)+";charset=UTF-8"); 
				//设置下载文件名称提示
				response.setHeader("Content-Disposition", "attachment;filename=\""
						+ new String(oldFileName.getBytes("GBK"), "ISO-8859-1") + "\"");
				//文件长度
				Long fileLen = 0L;
				if(in == null) throw new Exception("找不到文件");
				byte[] buffer = new byte[4096];
				int len = 0;
				while (-1 != (len = in.read(buffer))) {
					byteOut.write(buffer, 0, len);
					fileLen += len;
				}
				//如果是mongodb获取的附件保存到缓存中
				if(fileLen > 0){
					FileCatcheUtile.saveFileToCatche4Out(newFileName, byteOut);
				}
				//设置文件大小
				response.setHeader("Content-length", (new Long(fileLen)).toString());
				//响应文件流
				output.write(byteOut.toByteArray());
				byteOut.flush();
			} catch (Exception e) {
				
			} finally{
				try {
					output.flush();
					if(in != null) in.close();
					if(output != null) output.close();
					if(byteOut != null) byteOut.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	/**
	 * 以post方式请求
	 * 作者：陈嘉瑛
	 * 时间：2016-05-09
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response){
		doGet(request, response);
	}
}