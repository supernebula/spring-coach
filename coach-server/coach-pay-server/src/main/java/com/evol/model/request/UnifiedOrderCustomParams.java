
package com.evol.model.request;

import com.evol.model.annotation.NotRequire;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;
import java.io.Serializable;

@Data
public class UnifiedOrderCustomParams extends AbstractPayCustomParams implements Serializable {

    @NotRequire
    @XStreamAlias("device_info")
    private String deviceInfo; // 设备号 可以为终端设备号(门店号或收银设备ID)，PC网页或公众号内支付可以传"WEB"

    @XStreamAlias("body")
    private String body; // 商品描述
    @NotRequire
    @XStreamAlias("detail")
    private String detail; // 商品详情
    @NotRequire
    @XStreamAlias("attach")
    private String attach; // 附加数据
    @XStreamAlias("out_trade_no")
    private String outTradeNo; // 商户订单号
    @NotRequire
    @XStreamAlias("fee_type")
    private String feeType; // 货币类型 默认为人民币CNY
    @XStreamAlias("total_fee")
    private Integer totalFee; // 总金额 传入int型的数据
    @XStreamAlias("spbill_create_ip")
    private String spbillCreateIp;// 终端ip
    @NotRequire
    @XStreamAlias("time_start")
    private String timeStart; // 交易起始时间 订单生成时间
    @NotRequire
    @XStreamAlias("time_expire")
    private String timeExpire; // 交易结束时间 订单失效时间
    @NotRequire
    @XStreamAlias("goods_tag")
    private String goodsTag; // 订单优惠标记
    @XStreamAlias("notify_url")
    private String notifyUrl; // 通知url
    @XStreamAlias("trade_type")
    private String tradeType; // 交易类型 JSAPI，NATIVE，APP

    @XStreamAlias("product_id")
    private String productId; // 商品id trade_type=NATIVE时（即扫码支付），此参数必传
    @NotRequire
    @XStreamAlias("limit_pay")
    private String limitPay; // 指定支付方式 no_credit--可限制用户不能使用信用卡支付
    @XStreamAlias("openid")
    private String openId; // 用户标识(trade_type=JSAPI，此参数必传，用户在商户appid下的唯一标识)
    @NotRequire
    @XStreamAlias("scene_info")
    private String sceneInfo; // 该字段用于统一下单时上报场景信息，目前支持上报实际门店信息 格式{"store_id":// "SZT10000", "store_name":"腾讯大厦腾大餐厅"}
}
