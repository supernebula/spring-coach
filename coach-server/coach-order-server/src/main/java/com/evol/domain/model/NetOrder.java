package com.evol.domain.model;

import java.io.Serializable;
import java.util.Date;

public class NetOrder implements Serializable {
    private Integer id;

    private String orderNo;

    private Integer userId;

    private String username;

    private Integer movieId;

    private String movieName;

    private Integer paidAmount;

    private Integer amount;

    private Integer status;

    private String payOrderNo;

    private Date payTime;

    private String refundOrderNo;

    private Date refundTime;

    private Integer payModeType;

    private Date createTime;

    private Date udpateTime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName == null ? null : movieName.trim();
    }

    public Integer getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(Integer paidAmount) {
        this.paidAmount = paidAmount;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getPayOrderNo() {
        return payOrderNo;
    }

    public void setPayOrderNo(String payOrderNo) {
        this.payOrderNo = payOrderNo == null ? null : payOrderNo.trim();
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public String getRefundOrderNo() {
        return refundOrderNo;
    }

    public void setRefundOrderNo(String refundOrderNo) {
        this.refundOrderNo = refundOrderNo == null ? null : refundOrderNo.trim();
    }

    public Date getRefundTime() {
        return refundTime;
    }

    public void setRefundTime(Date refundTime) {
        this.refundTime = refundTime;
    }

    public Integer getPayModeType() {
        return payModeType;
    }

    public void setPayModeType(Integer payModeType) {
        this.payModeType = payModeType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUdpateTime() {
        return udpateTime;
    }

    public void setUdpateTime(Date udpateTime) {
        this.udpateTime = udpateTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", orderNo=").append(orderNo);
        sb.append(", userId=").append(userId);
        sb.append(", username=").append(username);
        sb.append(", movieId=").append(movieId);
        sb.append(", movieName=").append(movieName);
        sb.append(", paidAmount=").append(paidAmount);
        sb.append(", amount=").append(amount);
        sb.append(", status=").append(status);
        sb.append(", payOrderNo=").append(payOrderNo);
        sb.append(", payTime=").append(payTime);
        sb.append(", refundOrderNo=").append(refundOrderNo);
        sb.append(", refundTime=").append(refundTime);
        sb.append(", payModeType=").append(payModeType);
        sb.append(", createTime=").append(createTime);
        sb.append(", udpateTime=").append(udpateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}