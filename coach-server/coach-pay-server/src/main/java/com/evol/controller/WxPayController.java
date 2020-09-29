package com.evol.controller;

import com.evol.config.WXPayConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Controller
@RequestMapping("/wx")
public class WxPayController {

    @Autowired
    private WXPayConfig wXPayConfig;


    @GetMapping("index")
    public String index(){
        return "index";
    }

    @GetMapping("pay")
    public String getPay(){
        return "pay";
    }


    /**
     *
     * @param out_trade_no 商户订单号，商户网站订单系统中唯一订单号，必填
     * @param body 订单名称，必填
     * @param total_fee 付款金额，必填
     * @param detail 商品描述，可空
     * @return
     */
    @PostMapping("pay")
    public String postPay(@RequestParam(name = "out_trade_no", required = true) String out_trade_no
            , @RequestParam(name = "body", required = true) String body
            , @RequestParam(name = "total_fee", required = true) String total_fee
            , @RequestParam(name = "detail", required = false) String detail
            , HttpServletResponse response){

        // 超时时间 可空
        String timeout_express="2m";
        // 销售产品码 必填
        String product_code="QUICK_WAP_WAY";
        /**********************/

        return "";
    }

    @GetMapping("return")
    @ResponseBody
    public String callback(HttpServletRequest request, HttpServletResponse response) {

        boolean verify_result = true;
        if(verify_result){//验证成功
            //////////////////////////////////////////////////////////////////////////////////////////
            //请在这里加上商户的业务逻辑程序代码
            //该页面可做页面美工编辑
            return "验证成功<br />";
            //——请根据您的业务逻辑来编写程序（以上代码仅作参考）——

            //////////////////////////////////////////////////////////////////////////////////////////
        }else{
            //该页面可做页面美工编辑
            return "验证失败";
        }
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
