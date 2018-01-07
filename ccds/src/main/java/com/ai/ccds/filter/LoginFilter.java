package com.ai.ccds.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ai.PropertyUtil;
import com.ai.ccds.entity.sys.SysMenu;
import com.ai.ccds.util.Constants;
import com.ai.ccds.util.config.userCache.CatcheUserInfo;

public class LoginFilter extends HttpServlet implements Filter{
	private static final long serialVersionUID = 1L;
	private Log logger = LogFactory.getLog(LoginFilter.class);
	
	//注册无需登录过滤的链接
	private List<String> unFilterUrl = new ArrayList<String>();
	
	{
		//注意以/开头注册
		unFilterUrl.add("/login.jsp");
		unFilterUrl.add("/login/doLogin.do");
		unFilterUrl.add("/sessionEffectiveness.jsp");
	}
	
	/**
	 * 校验访问的路劲是否需要校验
	 * 作者：陈嘉瑛
	 * 时间：2015-12-30
	 * @param contPath 访问根路劲
	 * @param uri 访问的路径
	 * @return
	 */
	private Boolean validateUri(String contPath, String uri){
		uri = uri.replaceAll("/", "").replaceAll("\\\\", "");
		for(String unFilterUri : unFilterUrl){
			unFilterUri = contPath+unFilterUri;
			unFilterUri = unFilterUri.replaceAll("/", "").replaceAll("\\\\", "");
			if(uri.startsWith(unFilterUri)) return true;
		}
		return false;
	}

	/**
	 * 校验用户当前访问的路径是否是当前角色下，有权限的访问路径
	 * 作者：陈嘉瑛
	 * 时间：2015-12-30
	 * @param uri 用户当前访问的路径
	 * @param contPath 访问根路劲
	 * @param user 缓存中的用户信息
	 * @return
	 */
	private Boolean checkUserCanReqUri(String uri, String contPath, CatcheUserInfo user){
		uri = uri.trim().replace("/", "");
		//取得系统所有权限
		List<SysMenu> menuAllLs = user.getAllMenuLs();
		//如果系统中没有注册任何菜单，直接让它过
		if(menuAllLs == null || menuAllLs.size() == 0) return true;
		//标记访问的路径是否是可查的（即数据库中有的）
		Boolean flag = false;
		for(SysMenu m : menuAllLs) {
			String menuUri = m.getMenuUrl();
			if(StringUtils.isEmpty(menuUri)) continue;
			menuUri = (contPath + "/" + menuUri).trim().replace("/", "");
			if(menuUri.equals(uri)) {
				flag = true;
				break;
			}
		}
		//如果库中中不到对应的菜单，直接过，否则继续判断是否在用户在的角色菜单中
		if(!flag) return true;
		//取得当前用户的所有菜单权限
		List<SysMenu> aiWoefMenuLs = new ArrayList<SysMenu>();
		aiWoefMenuLs.addAll(user.getCaterlogLs());
		aiWoefMenuLs.addAll(user.getModuleLs());
		aiWoefMenuLs.addAll(user.getMenuLs());
		//遍历该菜单权限，判断如果当前访问的路径不存在用户菜单中，直接过掉
		//否则如果在用户菜单中，则判断是否在当前角色对应的菜单中，如果是返回true，否则访问false
		for(SysMenu m : aiWoefMenuLs) {
			String menuUri = m.getMenuUrl();
			if(StringUtils.isEmpty(menuUri)) continue;
			menuUri = (contPath + "/" + menuUri).trim().replace("/", "");
			//如果找到该菜单继续判断是否是该角色所有
			if(menuUri.equals(uri)) {
				return true;
			}
		}
		//说明该路径有配置到数据库，但是不是该用户当前角色可访问的路径
		return false;
	}

	public void doFilter(ServletRequest req, ServletResponse rsp,
			FilterChain filterChain) throws IOException, ServletException {
		/*logger.debug("用户登录过滤开始");
		HttpServletRequest request = (HttpServletRequest)req;
		String uri = request.getRequestURI();
		String contPath = request.getContextPath();
		//如果是静态资源直接过
		uri = uri.replaceAll("/", "").replaceAll("\\\\", "");
		String staticRes = PropertyUtil.getValue("resourceBundle.properties", "webapp.static");
		staticRes = contPath+staticRes;
		staticRes = staticRes.replaceAll("/", "").replaceAll("\\\\", "");
		if(uri.startsWith(staticRes)) {
			filterChain.doFilter(req, rsp);
			return;
		}
		//校验该访问路劲是否需要登录
		if(validateUri(contPath, uri)){
			//pass
		}else{
			String url = "";
			Object o = request.getSession().getAttribute(Constants.SESSION_USER);
			if(o == null){
				logger.debug("过滤器发现用户未登入或用户Session超时");
				url = "/sessionEffectiveness.jsp";
			} else {
				Boolean flag = checkUserCanReqUri(uri, contPath, (CatcheUserInfo)o);
				//如果用户当前访问的路径不在当前角色下的菜单中，跳转到无法访问界面
				if(!flag) url = "/common/404.jsp";
			}
			if(!url.equals("")) {
				HttpServletResponse response = (HttpServletResponse)rsp;
				url = "http://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+url;
				if (request.getHeader("x-requested-with") != null && request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) {
					//注意点：必须保证ajax异步请求是通过serverReqUtil.js下的方法请求的，才能自动跳转到登录页
					response.setContentType("text/html;charset=utf-8");
					PrintWriter out = response.getWriter();
					//session 失效重新跳转到登录页
					out.print(url);
					out.close();
					out.flush();
				}else{
					response.setHeader("Cache-Control", "no-store"); 
					response.setHeader("Pragma", "no-cache"); 
					response.setDateHeader("Expires", 0); 
					response.setHeader("Location", url);
					response.sendRedirect(url);
				}
				return;
			}
		}
		logger.debug("用户登录过滤结束");*/
		filterChain.doFilter(req, rsp);
	}

	public void init(FilterConfig arg0) throws ServletException {
		logger.debug("loginFilter init");
	}

	public void destroy() {

	}


}
