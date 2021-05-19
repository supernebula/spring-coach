package com.evol.domain.request.wxpay;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;
import java.io.Serializable;

@Data
@JacksonXmlRootElement(localName = "xml")
public class OrderQueryParams extends AbstractPayParams implements Serializable {

    private String transaction_id;

    private String out_trade_no;

}
