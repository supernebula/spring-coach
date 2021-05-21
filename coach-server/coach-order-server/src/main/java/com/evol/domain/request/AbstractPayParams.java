package com.evol.domain.request;

import com.evol.domain.model.annotation.NotRequire;
import lombok.Data;

@Data
public class AbstractPayParams {
    private String appid; // 公众号id
    private String mch_id; // 商户号
    private String nonce_str; // 随机字符串
    private String sign; // 签名
    @NotRequire
    private String sign_type; // 签名类型
}
