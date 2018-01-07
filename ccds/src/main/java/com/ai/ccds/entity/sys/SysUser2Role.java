package com.ai.ccds.entity.sys;

import java.io.Serializable;
import java.util.Date;

public class SysUser2Role implements Serializable{
	private static final long serialVersionUID = 1L;
	
    private String userUuid;

    private String roleUuid;

    private Integer status;

    private Date createTime;

    private String createStaff;

    private Date delTime;

    private String delOper;

    private Integer loginRole;

    public String getUserUuid() {
        return userUuid;
    }

    public void setUserUuid(String userUuid) {
        this.userUuid = userUuid == null ? null : userUuid.trim();
    }

    public String getRoleUuid() {
        return roleUuid;
    }

    public void setRoleUuid(String roleUuid) {
        this.roleUuid = roleUuid == null ? null : roleUuid.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateStaff() {
        return createStaff;
    }

    public void setCreateStaff(String createStaff) {
        this.createStaff = createStaff == null ? null : createStaff.trim();
    }

    public Date getDelTime() {
        return delTime;
    }

    public void setDelTime(Date delTime) {
        this.delTime = delTime;
    }

    public String getDelOper() {
        return delOper;
    }

    public void setDelOper(String delOper) {
        this.delOper = delOper == null ? null : delOper.trim();
    }

    public Integer getLoginRole() {
        return loginRole;
    }

    public void setLoginRole(Integer loginRole) {
        this.loginRole = loginRole;
    }
}