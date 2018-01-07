package com.ai.ccds.util.config.userCache;

import java.util.List;

import com.ai.ccds.entity.sys.SysMenu;

public class CatcheUserInfo {
	//用户id
	private String userId;
	//用户名
	private String userName;
	//当前角色
	private String nowRoleUuid;
	//已分配的目录
	private List<SysMenu> caterlogLs;
	//已分配的模块
	private List<SysMenu> moduleLs;
	//已分配的菜单
	private List<SysMenu> menuLs;
	//所有菜单
	private List<SysMenu> allMenuLs;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getNowRoleUuid() {
		return nowRoleUuid;
	}

	public void setNowRoleUuid(String nowRoleUuid) {
		this.nowRoleUuid = nowRoleUuid;
	}

	public List<SysMenu> getCaterlogLs() {
		return caterlogLs;
	}

	public void setCaterlogLs(List<SysMenu> caterlogLs) {
		this.caterlogLs = caterlogLs;
	}

	public List<SysMenu> getModuleLs() {
		return moduleLs;
	}

	public void setModuleLs(List<SysMenu> moduleLs) {
		this.moduleLs = moduleLs;
	}

	public List<SysMenu> getMenuLs() {
		return menuLs;
	}

	public void setMenuLs(List<SysMenu> menuLs) {
		this.menuLs = menuLs;
	}

	public List<SysMenu> getAllMenuLs() {
		return allMenuLs;
	}

	public void setAllMenuLs(List<SysMenu> allMenuLs) {
		this.allMenuLs = allMenuLs;
	}

}
