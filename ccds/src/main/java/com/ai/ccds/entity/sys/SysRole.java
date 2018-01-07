package com.ai.ccds.entity.sys;

import java.io.Serializable;
import java.util.Date;

public class SysRole implements Serializable{
	private static final long serialVersionUID = 1L;
	
    private String roleUuid;

    private String roleName;

    private String rolePuuid;

    private Integer roleOrder;

    private String status;

    private String roleDesc;

    private Date createTime;

    private String creator;

    private Date updateTime;

    private String updateOper;

    public String getRoleUuid() {
        return roleUuid;
    }

    public void setRoleUuid(String roleUuid) {
        this.roleUuid = roleUuid == null ? null : roleUuid.trim();
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public String getRolePuuid() {
        return rolePuuid;
    }

    public void setRolePuuid(String rolePuuid) {
        this.rolePuuid = rolePuuid == null ? null : rolePuuid.trim();
    }

    public Integer getRoleOrder() {
        return roleOrder;
    }

    public void setRoleOrder(Integer roleOrder) {
        this.roleOrder = roleOrder;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc == null ? null : roleDesc.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateOper() {
        return updateOper;
    }

    public void setUpdateOper(String updateOper) {
        this.updateOper = updateOper == null ? null : updateOper.trim();
    }
}