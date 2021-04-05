package com.evol.domain.model.user;

import java.io.Serializable;
import java.util.Date;

public class UserBalanceRecord implements Serializable {
    private Integer id;

    private Integer userId;

    private Integer historyBalanceMoney;

    private Integer currentBalanceMoney;

    private Integer changeMoney;

    private Integer moneyInOutType;

    private Integer userTransRecordId;

    private String remark;

    private Date createTime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getHistoryBalanceMoney() {
        return historyBalanceMoney;
    }

    public void setHistoryBalanceMoney(Integer historyBalanceMoney) {
        this.historyBalanceMoney = historyBalanceMoney;
    }

    public Integer getCurrentBalanceMoney() {
        return currentBalanceMoney;
    }

    public void setCurrentBalanceMoney(Integer currentBalanceMoney) {
        this.currentBalanceMoney = currentBalanceMoney;
    }

    public Integer getChangeMoney() {
        return changeMoney;
    }

    public void setChangeMoney(Integer changeMoney) {
        this.changeMoney = changeMoney;
    }

    public Integer getMoneyInOutType() {
        return moneyInOutType;
    }

    public void setMoneyInOutType(Integer moneyInOutType) {
        this.moneyInOutType = moneyInOutType;
    }

    public Integer getUserTransRecordId() {
        return userTransRecordId;
    }

    public void setUserTransRecordId(Integer userTransRecordId) {
        this.userTransRecordId = userTransRecordId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", historyBalanceMoney=").append(historyBalanceMoney);
        sb.append(", currentBalanceMoney=").append(currentBalanceMoney);
        sb.append(", changeMoney=").append(changeMoney);
        sb.append(", moneyInOutType=").append(moneyInOutType);
        sb.append(", userTransRecordId=").append(userTransRecordId);
        sb.append(", remark=").append(remark);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}