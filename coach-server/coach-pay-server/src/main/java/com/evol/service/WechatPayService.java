package com.evol.service;

import com.evol.model.request.AbstractPayCustomParams;
import com.evol.model.request.AbstractPayParams;

public interface WechatPayService {

    public String abstractPayToXml(AbstractPayParams params);

    public String abstractCustomPayToXml(AbstractPayCustomParams params, Class paramClazz);
}
