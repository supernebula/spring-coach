package com.evol.domain.request;

import lombok.Data;

import java.io.Serializable;

/**
 * 扫码模式一回调商户支付URL请求参数
 * @author phil
 * @date  2017年6月27日
 *
 */
@Data
public class PayCallBackParam implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1996870796182913776L;
	private String appid;// 公众账号ID
	private String attach;
	private String bank_type;
	private String fee_type;
	private String is_subscribe;// 用户是否关注公众账号，仅在公众账号类型支付有效，取值范围：Y或N;Y-关注;N-未关注
	private String mch_id;// 商户号
	private String nonce_str; // 随机字符串，不长于32位
	private String openid;// 用户标识
	private String out_trade_no;
	private String result_code;
	private String return_code;
	private String sign;// 签名
	private String time_end;
	private Integer total_fee;
	private String coupon_fee;
	private String coupon_count;
	private String coupon_type;
	private String coupon_id;
	private String trade_type;
	private String transaction_id;

}
