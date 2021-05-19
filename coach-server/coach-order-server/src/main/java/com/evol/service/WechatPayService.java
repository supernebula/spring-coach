package com.evol.service;


import com.evol.domain.request.AbstractPayCustomParams;
import com.evol.domain.request.wxpay.AbstractPayParams;

public interface WechatPayService {

    public String abstractPayToXml(AbstractPayParams params);

    public String abstractCustomPayToXml(AbstractPayCustomParams params, Class paramClazz);
}
