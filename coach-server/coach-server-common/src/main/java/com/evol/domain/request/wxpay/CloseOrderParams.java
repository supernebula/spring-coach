package com.evol.domain.request.wxpay;

import lombok.Data;

import java.io.Serializable;

@Data
public class CloseOrderParams extends AbstractPayParams implements Serializable  {

    private String out_trade_no;
}
