package com.evol.controller;

import com.evol.domain.request.PayOrderParam;
import com.evol.domain.response.PaidHandleOrderResult;
import com.evol.service.NetOrderService;
import com.evol.utils.Util;
import com.evol.utils.XMLParser;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.xml.sax.SAXException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.Map;

@Slf4j
public class WxCallbackController {

    private static final Logger logger = LoggerFactory.getLogger(WxCallbackController.class);

    @Autowired
    private NetOrderService netOrderService;

    public String callback(HttpServletRequest request, HttpServletResponse response) throws IOException, ParserConfigurationException, SAXException {


        String retStr = new String(Util.readInput(request.getInputStream()), "utf-8");
        logger.info("retStr:\n" + retStr);
        Map<String, String> treeMap = XMLParser.getSortMapFromXML(retStr);

        // 支付响应是否成功
        if (!"SUCCESS".equals((String) treeMap.get("result_code"))) {
            String resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"
                    + "<return_msg><![CDATA[参数错误]]></return_msg>" + "</xml> ";
            //处理完成返回通知给微信
            return resXml;
        }
        //重新签名并验证
        if(!wxResponseReSignOk(treeMap)){
            String resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"
                    + "<return_msg><![CDATA[参数错误]]></return_msg>" + "</xml> ";
            return resXml;
        }
        //验证签商户
        //if(!wxPayConfig.getMchId.equals()treeMap.get("mch_id")){
//        String resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"
//                + "<return_msg><![CDATA[参数错误]]></return_msg>" + "</xml> ";
//        return resXml;
        // }


//        String outTradeNo = (String) treeMap.get("out_trade_no");
//        String transactionId = (String) treeMap.get("transaction_id");
//        String mch_id = (String) treeMap.get("mch_id"); // 商户号
//        String total_fee = (String) treeMap.get("total_fee");

        PayOrderParam payOrderParam = new PayOrderParam();
        payOrderParam.setOutTradeNo((String) treeMap.get("out_trade_no"));
        payOrderParam.setTransactionId((String) treeMap.get("transaction_id"));
        payOrderParam.setMchId((String) treeMap.get("mch_id"));
        payOrderParam.setTotalFee((String) treeMap.get("total_fee"));

        try {
            Date date = DateUtils.parseDate(treeMap.get("time_end"), new String[]{"yyyyMMddHHmmss"});
            payOrderParam.setTimeEnd(date);
        }catch (ParseException paEx){
            log.error(paEx.getMessage());
            payOrderParam.setTimeEnd(new Date());
        }

        PaidHandleOrderResult result = netOrderService.paidHandleOrder(payOrderParam);

        if(result.isSuccess()){
            return "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>"
                    + "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";
        }
        return  "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"
                + "<return_msg><![CDATA[" + result.getMessage() + "]]></return_msg>" + "</xml> ";
    }

    private boolean wxResponseReSignOk(Map<String, String> sortMap){
        //重新签名逻辑
        return true;
    }
}
