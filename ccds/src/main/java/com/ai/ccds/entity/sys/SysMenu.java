package com.ai.ccds.entity.sys;

import java.io.Serializable;
import java.util.Date;

public class SysMenu implements Serializable{
	private static final long serialVersionUID = 1L;

	private String menuUuid;

    private String menuTitle;

    private String menuUrl;

    private String menuPuuid;

    private String menuType;

    private String menuDesc;

    private String iconClass;

    private Integer menuOrder;

    private String status;

    private String creator;

    private Date createTime;

    private String updateOper;

    private Date updateTime;

    private String btnMenuCode;

    public String getMenuUuid() {
        return menuUuid;
    }

    public void setMenuUuid(String menuUuid) {
        this.menuUuid = menuUuid == null ? null : menuUuid.trim();
    }

    public String getMenuTitle() {
        return menuTitle;
    }

    public void setMenuTitle(String menuTitle) {
        this.menuTitle = menuTitle == null ? null : menuTitle.trim();
    }

    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl == null ? null : menuUrl.trim();
    }

    public String getMenuPuuid() {
        return menuPuuid;
    }

    public void setMenuPuuid(String menuPuuid) {
        this.menuPuuid = menuPuuid == null ? null : menuPuuid.trim();
    }

    public String getMenuType() {
        return menuType;
    }

    public void setMenuType(String menuType) {
        this.menuType = menuType == null ? null : menuType.trim();
    }

    public String getMenuDesc() {
        return menuDesc;
    }

    public void setMenuDesc(String menuDesc) {
        this.menuDesc = menuDesc == null ? null : menuDesc.trim();
    }

    public String getIconClass() {
        return iconClass;
    }

    public void setIconClass(String iconClass) {
        this.iconClass = iconClass == null ? null : iconClass.trim();
    }

    public Integer getMenuOrder() {
        return menuOrder;
    }

    public void setMenuOrder(Integer menuOrder) {
        this.menuOrder = menuOrder;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateOper() {
        return updateOper;
    }

    public void setUpdateOper(String updateOper) {
        this.updateOper = updateOper == null ? null : updateOper.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getBtnMenuCode() {
        return btnMenuCode;
    }

    public void setBtnMenuCode(String btnMenuCode) {
        this.btnMenuCode = btnMenuCode == null ? null : btnMenuCode.trim();
    }
}