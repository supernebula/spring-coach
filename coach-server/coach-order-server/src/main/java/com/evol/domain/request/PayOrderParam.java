package com.evol.domain.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Data
@Getter
@Setter
public class PayOrderParam {

    private String outTradeNo;

    private String transactionId;

    private String mchId;

    private Integer totalFee;

    private Integer payModeType;

    /**
     * 支付完成时间
     */
    private Date timeEnd;


}
