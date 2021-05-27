package com.evol.domain.request;

import lombok.Data;

@Data
public class CreateOrderParam {

    private Integer userId;

    private Integer movieId;

    private String movieName;

    private String orderNo;

    private Integer amount;

}
