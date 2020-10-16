package com.evol.model.response;


import com.evol.model.request.JsPayParams;
import lombok.Data;

/**
 * 微信内H5返回结果
 * @author phil
 * @date  2017年6月27日
 *
 */
@Data
public class JsPayResult extends JsPayParams {

    /**
     *
     */
    private static final long serialVersionUID = 392188712101246402L;

    private String errMsg;

    private String resultCode;



}