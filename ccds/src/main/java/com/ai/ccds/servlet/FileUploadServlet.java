package com.ai.ccds.servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ai.UuidUtil;
import com.ai.mongodbToFile.MongoDBFileUtil;

/**
 * 附件上传入口
 * @author 陈嘉瑛
 * @version 2016-0-08
 *
 */
public class FileUploadServlet extends HttpServlet {

	protected static final Logger LOGGER = LoggerFactory.getLogger(FileUploadServlet.class);

	private static final long serialVersionUID = 1L;

	private final String SERVICEFLAG = "serviceFlag";

	/**
	 * 以get方式请求
	 * 作者：陈嘉瑛
	 * 时间：2015-11-27
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response){
		try {
			List<FileItem> lsFileItem = getFileItems(request, response);
			if(lsFileItem == null){
				response.getWriter().write("{}");
			}else{
				List<Map<String, String>> lsRs = new ArrayList<Map<String, String>>();
				for(FileItem f : lsFileItem){
					Map<String, String> rs = new HashMap<String,String>();
					String serviceFlag = request.getParameter(SERVICEFLAG);
					if(serviceFlag.equals("SOHO_FLAG")){
						String newFileName = f.getName();
						newFileName = UuidUtil.getUUID() + newFileName.substring(newFileName.lastIndexOf(".")); 
						MongoDBFileUtil.saveFile(f.getInputStream(), newFileName);
						rs.put("banchId", newFileName);
						rs.put("fileName", f.getName());
					}
					lsRs.add(rs);
				}
				response.getWriter().write(JSONArray.fromObject(lsRs).toString());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 以post方式请求
	 * 作者：陈嘉瑛
	 * 时间：2015-11-27
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response){
		doGet(request, response);
	}

	/**
	 * 取得上传的附件列表
	 * 作者：陈嘉瑛
	 * 时间：2015-11-27
	 * @param request
	 * @param response
	 * @return
	 */
	private List<FileItem> getFileItems(HttpServletRequest request, HttpServletResponse response){
		try {
			request.setCharacterEncoding("utf-8");
			//普通属性
			List<FileItem> fileLs = new ArrayList<FileItem>();
			DiskFileItemFactory factory = new DiskFileItemFactory();  
			//指定在内存中缓存数据大小,单位为byte,这里设为Mb  
			factory.setSizeThreshold(1024 * 1024);  
			//设置一旦文件大小超过getSizeThreshold()的值时数据存放在硬盘的目录   
			//factory.setRepository(new File("D:\\temp"));  
			//创建一个新的文件上传手柄
			ServletFileUpload upload = new ServletFileUpload(factory);  
			// 指定单个上传文件的最大尺寸,单位:字节，这里设为Mb    
			upload.setFileSizeMax(5 * 1024 * 1024);    
			//指定一次上传多个文件的总尺寸,单位:字节，这里设为Mb  
			upload.setSizeMax(100 * 1024 * 1024);     
			upload.setHeaderEncoding("UTF-8");
			// 解析request请求  
			List<FileItem> items = upload.parseRequest(request);
			if(items !=null ){  
				//解析表单项目  
				Iterator<FileItem> iter = items.iterator();
				for(FileItem item; iter.hasNext(); ){
					item = iter.next(); 
					//如果是普通表单属性  
					if (item.isFormField()) {  
						//相当于input的name属性   <input type="text" name=REQDATA>  
					} else {  
						//如果是上传文件  
						fileLs.add(item);
					} 
				} 
			}
			return fileLs;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
		return null;
	}
}
