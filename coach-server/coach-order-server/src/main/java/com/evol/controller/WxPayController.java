package com.evol.controller;

import com.arronlong.httpclientutil.HttpClientUtil;
import com.arronlong.httpclientutil.common.HttpConfig;
import com.arronlong.httpclientutil.common.HttpHeader;
import com.arronlong.httpclientutil.common.Utils;
import com.arronlong.httpclientutil.exception.HttpProcessException;
import com.evol.config.SystemConfig;
import com.evol.config.WXPayConfig;
import com.evol.domain.request.UnifiedOrderCustomParams;
import com.evol.domain.request.wxpay.UnifiedOrderParams;
import com.evol.domain.response.JsPayResult;
import com.evol.domain.response.UnifiedOrderCustomResult;
import com.evol.domain.response.UnifiedOrderResult;
import com.evol.service.WechatPayService;
import com.evol.util.*;
import com.evol.utils.*;
//import com.sun.deploy.net.HttpUtils;
import com.sun.jndi.toolkit.url.UrlUtil;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.commons.lang.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpRequest;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.tomcat.util.json.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.xml.sax.SAXException;
import sun.net.util.URLUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Controller
@RequestMapping("/wx")
public class WxPayController {

    private static final Logger logger = LoggerFactory.getLogger(WxPayController.class);

    @Autowired
    private WXPayConfig wXPayConfig;

    @Autowired
    private WechatPayService wechatPayService;


    @GetMapping("index")
    public String index(){
        return "index";
    }

    @GetMapping("pay")
    public String getPay(){
        return "pay";
    }

    @GetMapping("reqcode")
    public void reqCode( HttpServletResponse response) throws IOException {

        String redirectUri = URLEncoder.encode(wXPayConfig.getAuthCodeUrl(), StandardCharsets.UTF_8.toString()) ;

        System.out.println("redirectUri:" + redirectUri);

        String uri = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + wXPayConfig.getAppId() +
                "&redirect_uri=" + redirectUri + "&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";

        response.sendRedirect(uri);
    }

    @GetMapping("getCode")
    @ResponseBody
    public Object getCode(@RequestParam(name = "code", required = false) String code){
        System.out.println("code:" + code);


        String uri = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + wXPayConfig.getAppId() +
                "&secret=" + wXPayConfig.getAppSecret() +"&code=" + code + "&grant_type=authorization_code";

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(Duration.ofSeconds(10))//连接超时
                .writeTimeout(Duration.ofSeconds(5))//写超时，也就是请求超时
                .readTimeout(Duration.ofSeconds(5))//读取超时
                .callTimeout(Duration.ofSeconds(15))//调用超时，也是整个请求过程的超时
                .build();

        String bodyStr = "null";

        Request request = new Request.Builder().url(uri).build();
        try (Response response = okHttpClient.newCall(request).execute()) {
            okhttp3.ResponseBody body = (okhttp3.ResponseBody) response.body();
            if (response.isSuccessful()) {
                bodyStr = body.string();
                System.out.println("success:" + body == null ? "" : bodyStr);
            } else {
                System.out.println("error,statusCode={" + response.code() + "},body={" + body == null ? "" :
                        body.string() + "}");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("bodyStr:" + bodyStr);

        return bodyStr;
    }


    /**
     * 微信统一下单接口 https://api.mch.weixin.qq.com/pay/unifiedorder
     * @param out_trade_no 商户订单号，商户网站订单系统中唯一订单号，必填
     * @param subject 订单名称，必填
     * @param total_fee 付款金额，必填
     * @param detail 商品描述，可空
     * @return
     */
    @PostMapping("pay")
    @ResponseBody
    public void postPay(@RequestParam(name = "outTradeNo", required = true) String out_trade_no
            , @RequestParam(name = "subject", required = true) String subject
            , @RequestParam(name = "totalFee", required = true) Integer total_fee
            , @RequestParam(name = "detail", required = false) String detail
            , @RequestParam(name = "openId", required = false) String openId
            , HttpServletResponse response
            , HttpServletRequest request) throws Exception {

        logger.info("****正在支付的openId****" + openId);
        // 统一下单
        //String out_trade_no = PayUtil.createOutTradeNo();
        //int total_fee = 1; // 产品价格1分钱,用于测试
        String spbill_create_ip = HttpReqUtil.getRemortIP(request);
        logger.info("支付IP" + spbill_create_ip);
        String nonce_str = PayUtil.createNonceStr(); // 随机数据
        //参数组装
        UnifiedOrderParams unifiedOrderParams = new UnifiedOrderParams();
        unifiedOrderParams.setAppid(wXPayConfig.getAppId());// 必须
        unifiedOrderParams.setMch_id(wXPayConfig.getMchId());// 必须
        unifiedOrderParams.setNonce_str(nonce_str); // 必须
        unifiedOrderParams.setOut_trade_no(out_trade_no);// 必须
        unifiedOrderParams.setBody("支付测试");// 必须
        unifiedOrderParams.setTotal_fee(total_fee); // 必须

        unifiedOrderParams.setSpbill_create_ip(spbill_create_ip); // 必须
        unifiedOrderParams.setTrade_type("MWEB"); // 必须
        unifiedOrderParams.setOpenid(openId);
        unifiedOrderParams.setNotify_url(wXPayConfig.getNotifyUrl());// 异步通知url
        unifiedOrderParams.setScene_info("{\"h5_info\": {\"type\":\"IOS\",\"app_name\": \"H5测试\",\"package_name\": " +
                "\"com.tencent.tmgp.sgame\"}}");


        JsPayResult result = null;
        String wxPayUrl = null;
        // 统一下单 请求的Xml(正常的xml格式)
        String unifiedXmL = wechatPayService.abstractPayToXml(unifiedOrderParams);////签名并入service
        // 返回<![CDATA[SUCCESS]]>格式的XML
        String unifiedOrderResultXmL =
                HttpReqUtil.HttpsDefaultExecute(HttpReqUtil.POST_METHOD, wXPayConfig.getUnifiedOrderUrl(),
                        null, unifiedXmL);
        // 进行签名校验
        if (SignatureUtil.checkIsSignValidFromWeiXin(unifiedOrderResultXmL)) {
            String timeStamp = PayUtil.createTimeStamp();
            //统一下单响应
            UnifiedOrderResult unifiedOrderResult = XmlUtil.getObjectFromXML(unifiedOrderResultXmL, UnifiedOrderResult.class);
            wxPayUrl = unifiedOrderResult.getMweb_url();
            /**** 用map方式进行签名 ****/
            // SortedMap<Object, Object> signMap = new TreeMap<Object,
            // Object>();
            // signMap.put("appId", WeiXinConfig.APP_ID); // 必须
            // signMap.put("timeStamp", timeStamp);
            // signMap.put("nonceStr", nonceStr);
            // signMap.put("signType", "MD5");
            // signMap.put("package", "prepay_id=" + prepay_id);
            // String paySign = SignatureUtil.createSign(signMap, key, SystemConfig.CHARACTER_ENCODING);
            result = new JsPayResult();
            result.setAppId(wXPayConfig.getAppId());
            result.setTimeStamp(timeStamp);
            result.setNonceStr(unifiedOrderResult.getNonce_str());//直接用返回的
            /**** prepay_id 2小时内都有效，再次支付方法自己重写 ****/
            result.setPackageStr("prepay_id=" + unifiedOrderResult.getPrepay_id());
            /**** 用对象进行签名 ****/
            String paySign = SignatureUtil.createSign(result, WXPayConfig.apiKey,
                    SystemConfig.CHARACTER_ENCODING);
            result.setPaySign(paySign);
            result.setResultCode(unifiedOrderResult.getResult_code());
        } else {
            logger.info("签名验证错误");
        }


        if(StringUtils.isNotBlank(wxPayUrl)){
            response.sendRedirect(wxPayUrl);

        }

//        /**** 返回对象给页面 ****/
//        return result;
    }

    /**
     * 微信统一下单接口 https://api.mch.weixin.qq.com/pay/unifiedorder
     * @param outTradeNo 商户订单号，商户网站订单系统中唯一订单号，必填
     * @param subject 订单名称，必填
     * @param totalFee 付款金额，必填
     * @param detail 商品描述，可空
     * @return
     */
    @PostMapping("pay1")
    @ResponseBody
    public void postPay1(@RequestParam(name = "outTradeNo", required = true) String outTradeNo
            , @RequestParam(name = "subject", required = true) String subject
            , @RequestParam(name = "totalFee", required = true) Integer totalFee
            , @RequestParam(name = "detail", required = false) String detail
            , @RequestParam(name = "openId", required = false) String openId
            , HttpServletResponse response
            , HttpServletRequest request) throws Exception {

        logger.info("****正在支付的openId****" + openId);
        String spbillCreateIp = HttpReqUtil.getRemortIP(request);
        logger.info("支付IP" + spbillCreateIp);
        // 统一下单
        //String out_trade_no = PayUtil.createOutTradeNo();
        //int total_fee = 1; // 产品价格1分钱,用于测试

        String nonceStr = PayUtil.createNonceStr(); // 随机数据
        //参数组装
        UnifiedOrderCustomParams unifiedOrderParams = new UnifiedOrderCustomParams();
        unifiedOrderParams.setAppId(wXPayConfig.getAppId());// 必须
        unifiedOrderParams.setMchId(wXPayConfig.getMchId());// 必须
        unifiedOrderParams.setNonceStr(nonceStr); // 必须
        unifiedOrderParams.setOutTradeNo(outTradeNo);// 必须
        unifiedOrderParams.setBody("支付测试");// 必须
        unifiedOrderParams.setTotalFee(totalFee); // 必须

        unifiedOrderParams.setSpbillCreateIp(spbillCreateIp); // 必须
        unifiedOrderParams.setTradeType("MWEB"); // 必须
        unifiedOrderParams.setOpenId(openId);
        unifiedOrderParams.setNotifyUrl(wXPayConfig.getNotifyUrl());// 异步通知url
        unifiedOrderParams.setSceneInfo("{\"h5_info\": {\"type\":\"IOS\",\"app_name\": \"H5测试\",\"package_name\": " +
                "\"com.tencent.tmgp.sgame\"}}");


        JsPayResult result;
        String wxPayUrl = null;
        // 统一下单 请求的Xml(正常的xml格式)
        String unifiedXmL = wechatPayService.abstractCustomPayToXml(unifiedOrderParams, UnifiedOrderParams.class);
        ////签名并入service
        // 返回<![CDATA[SUCCESS]]>格式的XML







        Map<String, Object> map1 = new HashMap<>();
        map1.put(Utils.ENTITY_STRING, unifiedXmL);
        Header[] headers = HttpHeader.custom().contentType("application/xml; charset=utf-8").build();
        HttpConfig config = HttpConfig.custom().url(wXPayConfig.getUnifiedOrderUrl()).headers(headers).map(map1);
        //简单调用
        String unifiedOrderResultXmL = null;
        try {
            unifiedOrderResultXmL = HttpClientUtil.post(config);
        } catch (HttpProcessException e) {
            e.printStackTrace();
        }



        // 进行签名校验
        if (SignatureUtil.checkIsSignValidFromWeiXin(unifiedOrderResultXmL)) {
            String timeStamp = PayUtil.createTimeStamp();
            //统一下单响应
            UnifiedOrderCustomResult unifiedOrderResult = XmlUtil.getObjectFromXML(unifiedOrderResultXmL,
                    UnifiedOrderCustomResult.class);
            wxPayUrl = unifiedOrderResult.getMwebUrl();
            /**** 用map方式进行签名 ****/
            // SortedMap<Object, Object> signMap = new TreeMap<Object,
            // Object>();
            // signMap.put("appId", WeiXinConfig.APP_ID); // 必须
            // signMap.put("timeStamp", timeStamp);
            // signMap.put("nonceStr", nonceStr);
            // signMap.put("signType", "MD5");
            // signMap.put("package", "prepay_id=" + prepay_id);
            // String paySign = SignatureUtil.createSign(signMap, key, SystemConfig.CHARACTER_ENCODING);
            result = new JsPayResult();
            result.setAppId(wXPayConfig.getAppId());
            result.setTimeStamp(timeStamp);
            result.setNonceStr(unifiedOrderResult.getNonce_str());//直接用返回的
            /**** prepay_id 2小时内都有效，再次支付方法自己重写 ****/
            result.setPackageStr("prepay_id=" + unifiedOrderResult.getPrepayId());
            /**** 用对象进行签名 ****/
            String paySign = SignatureUtil.createSign(result, WXPayConfig.apiKey,
                    SystemConfig.CHARACTER_ENCODING);
            result.setPaySign(paySign);
            result.setResultCode(unifiedOrderResult.getResultCode());
        } else {
            logger.info("签名验证错误");
        }


        if(StringUtils.isNotBlank(wxPayUrl)){
            response.sendRedirect(wxPayUrl);

        }

//        /**** 返回对象给页面 ****/
//        return result;
    }

    @PostMapping("callback")
    @ResponseBody
    public void callback(HttpServletRequest request, HttpServletResponse response) throws IOException, SAXException,
            ParserConfigurationException {


        String retStr = new String(Util.readInput(request.getInputStream()),"utf-8");
        logger.info("retStr:\n" + retStr);
        Map<String, Object> map = XMLParser.getMapFromXML(retStr);
        //返回的数据

        //支付回调处理订单 更改订单状态

        response.setContentType("text/xml");
        String xml= "<xml>"
                + "<return_code><![CDATA[SUCCESS]]></return_code>"
                + "<return_msg><![CDATA[OK]]></return_msg>"
                + "</xml>";
        response.getWriter().print(xml);
        response.getWriter().flush();
        response.getWriter().close();


//        boolean verify_result = true;
//        if(verify_result){//验证成功
//            //////////////////////////////////////////////////////////////////////////////////////////
//            //请在这里加上商户的业务逻辑程序代码
//            //该页面可做页面美工编辑
//            //return "验证成功<br />";
//            return "<xml>\n" +
//                    "  <return_code><![CDATA[SUCCESS]]></return_code>\n" +
//                    "  <return_msg><![CDATA[OK]]></return_msg>\n" +
//                    "</xml>";
//            //——请根据您的业务逻辑来编写程序（以上代码仅作参考）——
//
//            //////////////////////////////////////////////////////////////////////////////////////////
//        }else{
//            //该页面可做页面美工编辑
//            return "验证失败";
//
//        }
    }

    /**
     * //获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)
     * @param out_trade_no 商户订单号
     * @param trade_no 支付宝交易号
     * @param trade_no 支付宝交易号
     * @param request 交易状态
     * @return
     */
    @GetMapping("notify")
    public String getNotify(@RequestParam(name = "out_trade_no" , required = true) String out_trade_no
            , @RequestParam(name = "trade_no" , required = true) String  trade_no
            , @RequestParam(name = "trade_status" , required = true) String  trade_status
            , HttpServletRequest request) {


        return "fail";
    }

    @GetMapping("query")
    public String getQuery(){
        return "query";
    }

    /**
     *
     * @param out_trade_no 商户订单号，商户网站订单系统中唯一订单号，必填
     * @param trade_no 支付宝交易号
     * @param request
     * @return
     * @throws UnsupportedEncodingException
     */
    @PostMapping("query")
    @ResponseBody
    public String postQuery(@RequestParam(name = "WIDout_trade_no", required = true) String out_trade_no
            , @RequestParam(name = "WIDtrade_no", required = false)  String trade_no
            , HttpServletRequest request) {

        String body = "";
        //String body = alipay_response.getBody();
        //System.out.println(alipay_response.getBody());
        return body;
    }

    @GetMapping("refund")
    public String getRefund(){
        return "refund";
    }

    /**
     * 商户订单号和支付宝交易号不能同时为空。 trade_no、  out_trade_no如果同时存在优先取trade_no
     * @param out_trade_no 商户订单号，和支付宝交易号二选一
     * @param trade_no 支付宝交易号，和商户订单号二选一
     * @param request
     * @return
     */
    @PostMapping("refund")
    @ResponseBody
    public String postRefund(@RequestParam(name = "WIDout_trade_no", required = true) String out_trade_no
            , @RequestParam(name = "WIDtrade_no", required = false) String trade_no
            , HttpServletRequest request) throws UnsupportedEncodingException {

        //退款金额，不能大于订单总金额
        String refund_amount=new String(request.getParameter("WIDrefund_amount").getBytes("ISO-8859-1"),"UTF-8");
        //退款的原因说明
        String refund_reason=new String(request.getParameter("WIDrefund_reason").getBytes("ISO-8859-1"),"UTF-8");
        //标识一次退款请求，同一笔交易多次退款需要保证唯一，如需部分退款，则此参数必传。
        String  out_request_no=new String(request.getParameter("WIDout_request_no").getBytes("ISO-8859-1"),"UTF-8");
        /**********************/

        String body = "body";

        //String body = alipay_response.getBody();
        //System.out.println(alipay_response.getBody());
        return body;
    }

    @GetMapping("refundquery")
    public String getRefundquery(){
        return "refundquery";
    }

    /**
     * 商户订单号和支付宝交易号不能同时为空。 trade_no、  out_trade_no如果同时存在优先取trade_no
     * @param out_trade_no
     * @param trade_no
     * @param request
     * @return
     * @throws UnsupportedEncodingException
     */
    @PostMapping("refundquery")
    public String postRefundquery(@RequestParam(name = "WIDout_trade_no", required = true) String out_trade_no
            , @RequestParam(name = "WIDtrade_no", required = false) String trade_no
            , HttpServletRequest request) throws UnsupportedEncodingException {
//        if(request.getParameter("WIDout_trade_no")!=null||request.getParameter("WIDtrade_no")!=null&&request.getParameter("WIDout_request_no")!=null) {
//        }
        //请求退款接口时，传入的退款请求号，如果在退款请求时未传入，则该值为创建交易时的外部交易号
        String out_request_no = new String(request.getParameter("WIDout_request_no").getBytes("ISO-8859-1"),"UTF-8");

        String body = "";
        //String body = alipay_response.getBody();
        //System.out.println(alipay_response.getBody());
        return body;

    }


}
