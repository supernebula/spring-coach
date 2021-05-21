package com.evol.domain.response;

import com.evol.domain.model.annotation.NotRequire;
import lombok.Data;

import java.io.Serializable;

/**
 * 统一下单返回结果
 * @author phil
 * @data 2017年6月27日
 *
 */
@Data
public class UnifiedOrderResult extends AbstractPayResult implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 9030465964635155064L;

    private String return_code; // 返回状态码

    private String return_msg; // 返回信息

    // 以下字段在return_code为SUCCESS的时候有返回(包括父类)
    private String device_info; // 设备号
    private String result_code; // 业务结果 SUCCESS/FAIL
    @NotRequire
    private String err_code; // 错误代码
    @NotRequire
    private String err_code_des; // 错误代码描述

    // 以下字段在return_code 和result_code都为SUCCESS的时候有返回
    private String trade_type; // 交易类型
    private String prepay_id; // 预支付交易会话标识，有效期为2小时
    @NotRequire
    private String code_url; // 二维码链接
    @NotRequire
    private String mweb_url; //mweb_url为拉起微信支付收银台的中间页面，可通过访问该url来拉起微信客户端，完成支付,mweb_url的有效期为5分钟。



    @Override
    public String toString() {
        return "UnifiedOrderResult [return_code=" + return_code + ", return_msg=" + return_msg + ", device_info="
                + device_info + ", result_code=" + result_code + ", err_code=" + err_code + ", err_code_des="
                + err_code_des + ", trade_type=" + trade_type + ", prepay_id=" + prepay_id + ", code_url=" + code_url
                + ", mweb_url = " + mweb_url + "]";
    }

}
