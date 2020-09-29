package com.evol;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WxPayConfig {

    public static String appId = "";

    public static String mchId;

    public static String feeType;

    public static String notifyUrl;

}
