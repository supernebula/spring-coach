package com.evol.domain.request.wxpay;

import lombok.Data;

import java.io.Serializable;

@Data
public class RefundQueryParams extends AbstractPayParams implements Serializable {

    private String out_refund_no;

    private String out_trade_no;

    private String refund_id;

    private String transaction_id;
}
