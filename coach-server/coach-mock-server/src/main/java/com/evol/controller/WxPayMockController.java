package com.evol.controller;

import com.evol.domain.request.wxpay.CloseOrderParams;
import com.evol.domain.request.wxpay.OrderQueryParams;
import com.evol.domain.request.wxpay.RefundParams;
import com.evol.domain.request.wxpay.UnifiedOrderParams;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 模拟微信H5微信端
 * https://pay.weixin.qq.com/wiki/doc/api/H5.php?chapter=15_1
 */
@RequestMapping("/mock/pay")
@RestController
public class WxPayMockController {

    /**
     * 模拟统一下单
     * https://api.mch.weixin.qq.com/pay/unifiedorder
     * @return
     */
    @PostMapping("/mock/unifiedorder")
    public String unifiedOrder(UnifiedOrderParams param){

        // region result
        String result = "<xml>\n" +
                "   <return_code><![CDATA[SUCCESS]]></return_code>\n" +
                "   <return_msg><![CDATA[OK]]></return_msg>\n" +
                "   <appid><![CDATA[wx2421b1c4370ec43b]]></appid>\n" +
                "   <mch_id><![CDATA[10000100]]></mch_id>\n" +
                "   <nonce_str><![CDATA[IITRi8Iabbblz1Jc]]></nonce_str>\n" +
                "   <sign><![CDATA[7921E432F65EB8ED0CE9755F0E86D72F]]></sign>\n" +
                "   <result_code><![CDATA[SUCCESS]]></result_code>\n" +
                "   <prepay_id><![CDATA[wx201411101639507cbf6ffd8b0779950874]]></prepay_id>\n" +
                "   <trade_type><![CDATA[MWEB]]></trade_type>\n" +
                "   <mweb_url><![CDATA[https://wx.tenpay.com/cgi-bin/mmpayweb-bin/checkmweb?prepay_id=wx2016121516420242444321ca0631331346&package=1405458241]]></mweb_url>\n" +
                "</xml>";
        // endregion
        return result;
    }

    /**
     * 模拟查询订单
     * https://api.mch.weixin.qq.com/pay/orderquery
     * @return
     */
    @PostMapping("/mock/orderquery")
    public String orderQuery(OrderQueryParams params){

        // region result
        String result = "<xml>\n" +
                "   <return_code><![CDATA[SUCCESS]]></return_code>\n" +
                "   <return_msg><![CDATA[OK]]></return_msg>\n" +
                "   <appid><![CDATA[wx2421b1c4370ec43b]]></appid>\n" +
                "   <mch_id><![CDATA[10000100]]></mch_id>\n" +
                "   <device_info><![CDATA[1000]]></device_info>\n" +
                "   <nonce_str><![CDATA[TN55wO9Pba5yENl8]]></nonce_str>\n" +
                "   <sign><![CDATA[BDF0099C15FF7BC6B1585FBB110AB635]]></sign>\n" +
                "   <result_code><![CDATA[SUCCESS]]></result_code>\n" +
                "   <openid><![CDATA[oUpF8uN95-Ptaags6E_roPHg7AG0]]></openid>\n" +
                "   <is_subscribe><![CDATA[Y]]></is_subscribe>\n" +
                "   <trade_type><![CDATA[MICROPAY]]></trade_type>\n" +
                "   <bank_type><![CDATA[CCB_DEBIT]]></bank_type>\n" +
                "   <total_fee>1</total_fee>\n" +
                "   <fee_type><![CDATA[CNY]]></fee_type>\n" +
                "   <transaction_id><![CDATA[1008450740201411110005820873]]></transaction_id>\n" +
                "   <out_trade_no><![CDATA[1415757673]]></out_trade_no>\n" +
                "   <attach><![CDATA[订单额外描述]]></attach>\n" +
                "   <time_end><![CDATA[20141111170043]]></time_end>\n" +
                "   <trade_state><![CDATA[SUCCESS]]></trade_state>\n" +
                "</xml>";
        // endregion
        return result;
    }

    /**
     * 关闭订单
     * https://api.mch.weixin.qq.com/pay/closeorder
     * @return
     */
    @PostMapping("/mock/closeorder")
    public String closeOrder(CloseOrderParams param){

        // region result
        String result = "<xml>\n" +
                "   <return_code><![CDATA[SUCCESS]]></return_code>\n" +
                "   <return_msg><![CDATA[OK]]></return_msg>\n" +
                "   <appid><![CDATA[wx2421b1c4370ec43b]]></appid>\n" +
                "   <mch_id><![CDATA[10000100]]></mch_id>\n" +
                "   <nonce_str><![CDATA[BFK89FC6rxKCOjLX]]></nonce_str>\n" +
                "   <sign><![CDATA[72B321D92A7BFA0B2509F3D13C7B1631]]></sign>\n" +
                "   <result_code><![CDATA[SUCCESS]]></result_code>\n" +
                "   <result_msg><![CDATA[OK]]></result_msg>\n" +
                "</xml>";
        // endregion
        return result;
    }

    /**
     * 申请退款
     * https://api.mch.weixin.qq.com/pay/refund
     * @return
     */
    @PostMapping("/mock/refund")
    public String refund(RefundParams params){

        // region result
        String result = "<xml>\n" +
                "   <return_code><![CDATA[SUCCESS]]></return_code>\n" +
                "   <return_msg><![CDATA[OK]]></return_msg>\n" +
                "   <appid><![CDATA[wx2421b1c4370ec43b]]></appid>\n" +
                "   <mch_id><![CDATA[10000100]]></mch_id>\n" +
                "   <nonce_str><![CDATA[NfsMFbUFpdbEhPXP]]></nonce_str>\n" +
                "   <sign><![CDATA[B7274EB9F8925EB93100DD2085FA56C0]]></sign>\n" +
                "   <result_code><![CDATA[SUCCESS]]></result_code>\n" +
                "   <transaction_id><![CDATA[1008450740201411110005820873]]></transaction_id>\n" +
                "   <out_trade_no><![CDATA[1415757673]]></out_trade_no>\n" +
                "   <out_refund_no><![CDATA[1415701182]]></out_refund_no>\n" +
                "   <refund_id><![CDATA[2008450740201411110000174436]]></refund_id>\n" +
                "   <refund_fee>1</refund_fee>\n" +
                "</xml>";
        // endregion
        return result;
    }

    /**
     * 查询退款
     * https://api.mch.weixin.qq.com/pay/refundquery
     * @return
     */
    @PostMapping("/mock/refundquery")
    public String refundQuery(RefundParams params){

        // region result
        String result = "<xml>\n" +
                "   <appid><![CDATA[wx2421b1c4370ec43b]]></appid>\n" +
                "   <mch_id><![CDATA[10000100]]></mch_id>\n" +
                "   <nonce_str><![CDATA[TeqClE3i0mvn3DrK]]></nonce_str>\n" +
                "   <out_refund_no_0><![CDATA[1415701182]]></out_refund_no_0>\n" +
                "   <out_trade_no><![CDATA[1415757673]]></out_trade_no>\n" +
                "   <refund_count>1</refund_count>\n" +
                "   <refund_fee_0>1</refund_fee_0>\n" +
                "   <refund_id_0><![CDATA[2008450740201411110000174436]]></refund_id_0>\n" +
                "   <refund_status_0><![CDATA[PROCESSING]]></refund_status_0>\n" +
                "   <result_code><![CDATA[SUCCESS]]></result_code>\n" +
                "   <return_code><![CDATA[SUCCESS]]></return_code>\n" +
                "   <return_msg><![CDATA[OK]]></return_msg>\n" +
                "   <sign><![CDATA[1F2841558E233C33ABA71A961D27561C]]></sign>\n" +
                "   <transaction_id><![CDATA[1008450740201411110005820873]]></transaction_id>\n" +
                "</xml>";
        // endregion
        return result;
    }

    /**
     * 支付结果通知
     */
    public void payResultNotify(){

        //请求order server
        // region result

        String  result = "<xml>\n" +
                "  <appid><![CDATA[wx2421b1c4370ec43b]]></appid>\n" +
                "  <attach><![CDATA[支付测试]]></attach>\n" +
                "  <bank_type><![CDATA[CFT]]></bank_type>\n" +
                "  <fee_type><![CDATA[CNY]]></fee_type>\n" +
                "  <is_subscribe><![CDATA[Y]]></is_subscribe>\n" +
                "  <mch_id><![CDATA[10000100]]></mch_id>\n" +
                "  <nonce_str><![CDATA[5d2b6c2a8db53831f7eda20af46e531c]]></nonce_str>\n" +
                "  <openid><![CDATA[oUpF8uMEb4qRXf22hE3X68TekukE]]></openid>\n" +
                "  <out_trade_no><![CDATA[1409811653]]></out_trade_no>\n" +
                "  <result_code><![CDATA[SUCCESS]]></result_code>\n" +
                "  <return_code><![CDATA[SUCCESS]]></return_code>\n" +
                "  <sign><![CDATA[B552ED6B279343CB493C5DD0D78AB241]]></sign>\n" +
                "  <time_end><![CDATA[20140903131540]]></time_end>\n" +
                "  <total_fee>1</total_fee>\n" +
                "  <coupon_fee><![CDATA[10]]></coupon_fee>\n" +
                "  <coupon_count><![CDATA[1]]></coupon_count>\n" +
                "  <coupon_type><![CDATA[CASH]]></coupon_type>\n" +
                "  <coupon_id><![CDATA[10000]]></coupon_id>\n" +
                "  <trade_type><![CDATA[JSAPI]]></trade_type>\n" +
                "  <transaction_id><![CDATA[1004400740201409030005092168]]></transaction_id>\n" +
                "</xml>";

        // endregion


    }

    /**
     * 退款结果通知
     */
    public void refundResultNotify(){
        //请求order server

        // region result

        String  result = "<root>\n" +
                "<out_refund_no><![CDATA[131811191610442717309]]></out_refund_no>\n" +
                "<out_trade_no><![CDATA[71106718111915575302817]]></out_trade_no>\n" +
                "<refund_account><![CDATA[REFUND_SOURCE_RECHARGE_FUNDS]]></refund_account>\n" +
                "<refund_fee><![CDATA[3960]]></refund_fee>\n" +
                "<refund_id><![CDATA[50000408942018111907145868882]]></refund_id>\n" +
                "<refund_recv_accout><![CDATA[支付用户零钱]]></refund_recv_accout>\n" +
                "<refund_request_source><![CDATA[API]]></refund_request_source>\n" +
                "<refund_status><![CDATA[SUCCESS]]></refund_status>\n" +
                "<settlement_refund_fee><![CDATA[3960]]></settlement_refund_fee>\n" +
                "<settlement_total_fee><![CDATA[3960]]></settlement_total_fee>\n" +
                "<success_time><![CDATA[2018-11-19 16:24:13]]></success_time>\n" +
                "<total_fee><![CDATA[3960]]></total_fee>\n" +
                "<transaction_id><![CDATA[4200000215201811190261405420]]></transaction_id>\n" +
                "</root>";

        // endregion

    }

}
