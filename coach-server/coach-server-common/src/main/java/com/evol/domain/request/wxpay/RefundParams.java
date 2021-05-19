package com.evol.domain.request.wxpay;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

import java.io.Serializable;

@Data
@JacksonXmlRootElement(localName = "xml")
public class RefundParams extends AbstractPayParams implements Serializable {

    private String out_refund_no;


    private String out_trade_no;

    private Integer refund_fee;

    private Integer total_fee;

    private String transaction_id;

    private String notify_url;

}
