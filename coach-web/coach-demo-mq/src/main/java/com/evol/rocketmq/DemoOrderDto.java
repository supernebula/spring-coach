package com.evol.rocketmq;

import jdk.nashorn.internal.objects.annotations.Constructor;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
public class DemoOrderDto implements Serializable {

    private String orderNo;

    private Integer amount;

    private Date createTime;
}
