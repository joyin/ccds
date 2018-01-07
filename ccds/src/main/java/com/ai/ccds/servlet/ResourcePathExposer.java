package com.ai.ccds.servlet;

import javax.servlet.ServletContext;

import org.springframework.web.context.ServletContextAware;

import com.ai.PropertyUtil;

/**
 * 对某些静态资源，如css,图片等进行过滤 ,有引用 "/resources/**" 的路径引用转到相应映射配置目录取资源
 * @ClassName: ResourcePathExposer 
 * @Description: TODO(设置WEB层文件路径) 
 * @author 陈嘉瑛
 * @date 2015-1-27
 *
 */
public class ResourcePathExposer implements ServletContextAware {
	private ServletContext servletContext;
	private String resourceRoot;
	private String resourceWebRoot;

	/**
	 * 初始化方法
	 * 作者：陈嘉瑛
	 * 时间：2015-2-9
	 */
	public void init() {
		//获取配置的静态资源根路径
		resourceRoot = "/" + PropertyUtil.getValue("resourceBundle.properties", "webapp.static");
		//拼接成相对路径
		resourceRoot = getServletContext().getContextPath() + resourceRoot;
		getServletContext().setAttribute("resourceRoot", resourceRoot);
		//项目的访问根路径
		getServletContext().setAttribute("ctx", getServletContext().getContextPath());
		
		initLog4jPath(getServletContext());
	}
	
	/**
	 * 实例化日志输出文件根路径
	 * 作者：陈嘉瑛
	 * 时间：2016-09-26
	 * @param sce
	 */
	private void initLog4jPath(ServletContext sce){
		String webRoot = sce.getRealPath("/");
		System.setProperty("log4jdir", webRoot);
		//DOMConfigurator.configure(webRoot+"/WEB-INF/classes/log4j.xml");
	}

	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	public String getResourceRoot() {
		return resourceRoot;
	}

	public ServletContext getServletContext() {
		return servletContext;
	}

	public String getResourceWebRoot() {
		return resourceWebRoot;
	}

	public void setResourceWebRoot(String resourceWebRoot) {
		this.resourceWebRoot = resourceWebRoot;
	}

	public void setResourceRoot(String resourceRoot) {
		this.resourceRoot = resourceRoot;
	}


}
