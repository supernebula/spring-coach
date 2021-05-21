package com.evol.domain.response;

import com.evol.domain.model.annotation.NotRequire;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

import java.io.Serializable;

/**
 * 统一下单返回结果
 * @author phil
 * @data 2017年6月27日
 *
 */
@Data
public class UnifiedOrderCustomResult extends AbstractPayCustomResult implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 9030465964635155064L;

    @XStreamAlias("return_code")
    private String returnCode; // 返回状态码

    @XStreamAlias("return_msg")
    private String returnMsg; // 返回信息

    // 以下字段在return_code为SUCCESS的时候有返回(包括父类)
    @XStreamAlias("device_info")
    private String deviceInfo; // 设备号
    @XStreamAlias("return_code")
    private String resultCode; // 业务结果 SUCCESS/FAIL
    @NotRequire
    @XStreamAlias("err_code")
    private String errCode; // 错误代码
    @NotRequire
    @XStreamAlias("err_code_des")
    private String errCodeDes; // 错误代码描述

    // 以下字段在return_code 和result_code都为SUCCESS的时候有返回
    @XStreamAlias("trade_type")
    private String tradeType; // 交易类型
    @XStreamAlias("prepay_id")
    private String prepayId; // 预支付交易会话标识，有效期为2小时
    @NotRequire
    @XStreamAlias("code_url")
    private String codeUrl; // 二维码链接
    @NotRequire
    @XStreamAlias("mweb_url")
    private String mwebUrl; //mweb_url为拉起微信支付收银台的中间页面，可通过访问该url来拉起微信客户端，完成支付,mweb_url的有效期为5分钟。



    @Override
    public String toString() {
        return "UnifiedOrderResult [return_code=" + returnCode + ", return_msg=" + returnMsg + ", device_info="
                + deviceInfo + ", result_code=" + resultCode + ", err_code=" + errCode + ", err_code_des="
                + errCodeDes + ", trade_type=" + tradeType + ", prepay_id=" + prepayId + ", code_url=" + codeUrl
                + ", mweb_url = " + mwebUrl + "]";
    }

}
