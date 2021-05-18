package com.evol.domain.request.wxpay;

import lombok.Data;
import java.io.Serializable;

@Data
public class OrderQueryParams extends AbstractPayParams implements Serializable {

    private String transaction_id;

}
