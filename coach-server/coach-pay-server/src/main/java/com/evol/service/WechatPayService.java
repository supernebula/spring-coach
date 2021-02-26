package com.evol.service;

import com.evol.model.request.AbstractPayParams;

public interface WechatPayService {

    public String abstractPayToXml(AbstractPayParams params, Class paramClazz);
}
