package com.evol.domain.request;

import com.evol.domain.model.annotation.NotRequire;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

@Data
public class AbstractPayCustomParams {
    @XStreamAlias("appid")
    private String appId; // 公众号id
    @XStreamAlias("mch_id")
    private String mchId; // 商户号
    @XStreamAlias("nonce_str")
    private String nonceStr; // 随机字符串
    @XStreamAlias("sign")
    private String sign; // 签名
    @NotRequire
    @XStreamAlias("sign_type")
    private String signType; // 签名类型
}
