package com.evol.config;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

@Component
@Data
public  class WXPayConfig {

    /**
     * 获取授权code的地址
     */
    private String wxAuthorizeUrl = "https://open.weixin.qq.com/connect/oauth2/authorize";

    /**
     * 获取accessToken获取地址
     */
    private String wxTokenUrl = "https://api.weixin.qq.com/sns/oauth2/access_token";




    /**
     * 获取 App ID
     *
     * @return App ID
     */
    private String appId =  "wx732"; // "wx52fd0bec358888888";

    private String appSecret = "1337";  //"a82cbbe72f8f61234567898765432";


    /**
     * 获取 Mch ID
     *
     * @return Mch ID
     */
    private String mchId = "41";

    //"http://h5test.dd.com/wx/callback";
    private String notifyUrl = "http://127.0.0.1:8084/wx/callback";

    private String reqCodeCallbackUrl = "http://h5test.natapp1.cc/wechat/reqToken";


    //统一下单接口地址
    //https://api.mch.weixin.qq.com/pay/unifiedorder
    private String unifiedOrderUrl =  "http://127.0.0.1:9000/mock/pay/unifiedorder";

    //查询订单
    //https://api.mch.weixin.qq.com/pay/orderquery
    private String orderQueryUrl =  "http://127.0.0.1:9000/mock/pay/orderquery";

    //关闭订单
    //https://api.mch.weixin.qq.com/pay/closeorder
    private String closeOrderUrl =  "http://127.0.0.1:9000/mock/pay/closeorder";

    //申请退款
    //https://api.mch.weixin.qq.com/pay/refund
    private String refundUrl =  "http://127.0.0.1:9000/mock/pay/refund";

    //查询退款
    //https://api.mch.weixin.qq.com/pay/refundquery
    private String refundQueryUrl =  "http://127.0.0.1:9000/mock/pay/refundquery";




    /**
     * 获取 API 密钥
     *
     * @return API密钥
     */
    public static final String apiKey = "0F";

    public String getKey(){
        return  apiKey;
    }

    private String certPath = "cert.p12";

    /**
     * 获取商户证书内容
     *
     * @return 商户证书内容
     */
    public InputStream getCertStream() throws FileNotFoundException {
        return new FileInputStream(certPath);
    }

    /**
     * HTTP(S) 连接超时时间，单位毫秒
     *
     * @return
     */
    private int httpConnectTimeoutMs = 6 * 1000;

    /**
     * HTTP(S) 读数据超时时间，单位毫秒
     *
     * @return
     */
    private int httpReadTimeoutMs = 8*1000;

    /**
     * 获取WXPayDomain, 用于多域名容灾自动切换
     * @return
     */
    private IWXPayDomain wXPayDomain;

    /**
     * 是否自动上报。
     * 若要关闭自动上报，子类中实现该函数返回 false 即可。
     *
     * @return
     */
    public boolean shouldAutoReport() {
        return true;
    };

    /**
     * 进行健康上报的线程的数量
     *
     * @return
     */
    private int reportWorkerNum = 6;


    /**
     * 健康上报缓存消息的最大数量。会有线程去独立上报
     * 粗略计算：加入一条消息200B，10000消息占用空间 2000 KB，约为2MB，可以接受
     *
     * @return
     */
    private int reportQueueMaxSize = 10000;

    /**
     * 批量上报，一次最多上报多个数据
     *
     * @return
     */
    private int reportBatchSize = 10;

}
