package com.ai.ccds.entity.batch;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class CaseBat {
    private String cbatId;

    private String cbatTypeId;

    private String cbatCode;
    
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date cbatDate;

    private Integer cbatNum;

    private String cbatMon;

    private Date cbatUpDate;

    private String cbatRemark;

    private String cbatState;

    private String cbatInsUser;

    private String cbatAreaId;

    private String cbatTypBid;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date cbatBackdateP;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date cbatBackdate;

    private String cbatTips;

    private Date cbatLog;

    private String cbatTarget;

    public String getCbatId() {
        return cbatId;
    }

    public void setCbatId(String cbatId) {
        this.cbatId = cbatId == null ? null : cbatId.trim();
    }

    public String getCbatTypeId() {
        return cbatTypeId;
    }

    public void setCbatTypeId(String cbatTypeId) {
        this.cbatTypeId = cbatTypeId == null ? null : cbatTypeId.trim();
    }

    public String getCbatCode() {
        return cbatCode;
    }

    public void setCbatCode(String cbatCode) {
        this.cbatCode = cbatCode == null ? null : cbatCode.trim();
    }

    public Date getCbatDate() {
        return cbatDate;
    }

    public void setCbatDate(Date cbatDate) {
        this.cbatDate = cbatDate;
    }

    public Integer getCbatNum() {
        return cbatNum;
    }

    public void setCbatNum(Integer cbatNum) {
        this.cbatNum = cbatNum;
    }

    public String getCbatMon() {
        return cbatMon;
    }

    public void setCbatMon(String cbatMon) {
        this.cbatMon = cbatMon == null ? null : cbatMon.trim();
    }

    public Date getCbatUpDate() {
        return cbatUpDate;
    }

    public void setCbatUpDate(Date cbatUpDate) {
        this.cbatUpDate = cbatUpDate;
    }

    public String getCbatRemark() {
        return cbatRemark;
    }

    public void setCbatRemark(String cbatRemark) {
        this.cbatRemark = cbatRemark == null ? null : cbatRemark.trim();
    }

    public String getCbatState() {
        return cbatState;
    }

    public void setCbatState(String cbatState) {
        this.cbatState = cbatState == null ? null : cbatState.trim();
    }

    public String getCbatInsUser() {
        return cbatInsUser;
    }

    public void setCbatInsUser(String cbatInsUser) {
        this.cbatInsUser = cbatInsUser == null ? null : cbatInsUser.trim();
    }

    public String getCbatAreaId() {
        return cbatAreaId;
    }

    public void setCbatAreaId(String cbatAreaId) {
        this.cbatAreaId = cbatAreaId == null ? null : cbatAreaId.trim();
    }

    public String getCbatTypBid() {
        return cbatTypBid;
    }

    public void setCbatTypBid(String cbatTypBid) {
        this.cbatTypBid = cbatTypBid == null ? null : cbatTypBid.trim();
    }

    public Date getCbatBackdateP() {
        return cbatBackdateP;
    }

    public void setCbatBackdateP(Date cbatBackdateP) {
        this.cbatBackdateP = cbatBackdateP;
    }

    public Date getCbatBackdate() {
        return cbatBackdate;
    }

    public void setCbatBackdate(Date cbatBackdate) {
        this.cbatBackdate = cbatBackdate;
    }

    public String getCbatTips() {
        return cbatTips;
    }

    public void setCbatTips(String cbatTips) {
        this.cbatTips = cbatTips == null ? null : cbatTips.trim();
    }

    public Date getCbatLog() {
        return cbatLog;
    }

    public void setCbatLog(Date cbatLog) {
        this.cbatLog = cbatLog;
    }

    public String getCbatTarget() {
        return cbatTarget;
    }

    public void setCbatTarget(String cbatTarget) {
        this.cbatTarget = cbatTarget == null ? null : cbatTarget.trim();
    }
}