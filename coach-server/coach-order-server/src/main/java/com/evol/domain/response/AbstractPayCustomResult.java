package com.evol.domain.response;

import lombok.Data;

/**
 * 返回参数的抽类型
 * @author phil
 * @date  2017年6月27日
 *
 */
@Data
public abstract class AbstractPayCustomResult {

    private String appid; // 公众号id

    private String mch_id; // 商户号

    private String nonce_str; // 随机字符串

    private String sign; // 签名
}
