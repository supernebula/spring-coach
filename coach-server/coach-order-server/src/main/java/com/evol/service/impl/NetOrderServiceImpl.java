package com.evol.service.impl;

import com.evol.base.client.UserDTO;
import com.evol.contancts.enums.NetOrderStatusEnum;
import com.evol.contancts.enums.PayModeTypeEnum;
import com.evol.domain.PageBase;
import com.evol.domain.dto.MovieDetailDTO;
import com.evol.domain.model.NetOrder;
import com.evol.domain.model.NetOrderExample;
import com.evol.domain.request.NetOrderQueryRequest;
import com.evol.domain.request.OrderCancelParam;
import com.evol.domain.request.PayOrderParam;
import com.evol.domain.request.UpdateUserBalanceParam;
import com.evol.domain.response.CreateOrderResult;
import com.evol.domain.response.PaidHandleOrderResult;
import com.evol.enums.ApiResponseEnum;
import com.evol.enums.MoneyInOutTypeEnum;
import com.evol.mapper.NetOrderMapper;
import com.evol.service.NetOrderService;
import com.evol.service.invoke.FeignMovieClient;
import com.evol.service.invoke.FeignUserClient;
import com.evol.util.IDUtil;
import com.evol.util.JsonUtil;
import com.evol.web.ApiResponse;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import com.evol.contant.RabbitContants;
import org.springframework.util.CollectionUtils;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class NetOrderServiceImpl implements NetOrderService {

    @Autowired
    private NetOrderMapper netOrderMapper;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @Autowired
    FeignMovieClient feignMovieClient;

    @Autowired
    FeignUserClient feignUserClient;


//    @Override
//    public CreateOrderResult newOrder(CreateOrderParam reqParam) {
//        NetOrder netOrder = new NetOrder();
//        netOrder.setOrderNo(IDUtil.hourIdNo());
//        netOrder.setUserId(reqParam.getUserId());
//        netOrder.setMoviceId(reqParam.getMovieId());
//        netOrder.setMoviceName(reqParam.getMovieName());
//        netOrder.setStatus(NetOrderStatusEnum.UNPAID.getCode());
//        netOrder.setAmount(reqParam.getAmount());
//        netOrder.setPayModeType(PayModeTypeEnum.NONE.getCode());
//        netOrder.setCreateTime(new Date());
//        netOrdersMapper.insert(netOrder);
//        CreateOrderResult result = new CreateOrderResult();
//        result.setOrderId(netOrder.getId());
//        result.setOrderNo(netOrder.getOrderNo());
//        return result;
//    }

    @Override
    public CreateOrderResult newOrder(Integer movieId, Integer userId) {
        ApiResponse<MovieDetailDTO> apiResp = feignMovieClient.getMovie(movieId);
        if(apiResp.getSubCode() != 0 || apiResp.getData() == null  ){
            return new CreateOrderResult(false, userId,null, null, null, null, apiResp.getSubMsg());
        }
        MovieDetailDTO movieDTO = apiResp.getData();

        ApiResponse<UserDTO> userApiResp = feignUserClient.getUserById(userId);
        if(userApiResp.getSubCode() != 0 || userApiResp.getData() == null  ){
            return new CreateOrderResult(false, userId, null, null, null, null, userApiResp.getSubMsg());
        }
        UserDTO userDTO = userApiResp.getData();

        NetOrder netOrder = new NetOrder();
        netOrder.setOrderNo(IDUtil.hourIdNo());
        netOrder.setUserId(userDTO.getId());
        netOrder.setUsername(userDTO.getUsername());
        netOrder.setMovieId(movieDTO.getId());
        netOrder.setMovieName(movieDTO.getName());

        netOrder.setPaidAmount(0);
        netOrder.setAmount(movieDTO.getDiscountPrice());
        netOrder.setStatus(NetOrderStatusEnum.UNPAID.getCode());

        netOrder.setPayModeType(PayModeTypeEnum.NONE.getCode());
        netOrder.setCreateTime(new Date());
        netOrderMapper.insert(netOrder);

        this.cancelDelayNotPaidOrder(netOrder.getId(), netOrder.getOrderNo(), netOrder.getCreateTime());

        return new CreateOrderResult(true, userId, netOrder.getId(), netOrder.getOrderNo(), netOrder.getAmount(),
                netOrder.getMovieName(),
                null);
    }

    @Override
    public PaidHandleOrderResult paidHandleOrder(PayOrderParam payOrderParam) {
        NetOrder netOrder = this.getByOrderNo(payOrderParam.getOutTradeNo());
        if(netOrder == null){
            return PaidHandleOrderResult.noOrderRecord(payOrderParam.getOutTradeNo());
        }

        netOrder.setPayOrderNo(payOrderParam.getTransactionId());
        netOrder.setPayTime(payOrderParam.getTimeEnd());
        netOrder.setStatus(NetOrderStatusEnum.PAID.getCode());
        netOrder.setPaidAmount(payOrderParam.getTotalFee());
        netOrder.setPayModeType(payOrderParam.getPayModeType());
        netOrder.setUdpateTime(new Date());
        netOrderMapper.updateByPrimaryKeySelective(netOrder);
        return PaidHandleOrderResult.success(netOrder.getId(), payOrderParam.getOutTradeNo());
    }

    @Override
    public ApiResponse<PaidHandleOrderResult> payByBalance(Integer userId, Integer orderId) {
        NetOrder netOrder = this.getNetOrderById(orderId);
        if(netOrder == null){
            return new ApiResponse(ApiResponseEnum.NO_RECORD, PaidHandleOrderResult.noOrderRecord("" + orderId));
        }

        if(NetOrderStatusEnum.EXPIRE_CANCELED.getCode().equals(netOrder.getStatus())){
            return ApiResponse.fail(ApiResponseEnum.USER_DEFINED_ERROR, "订单未及时支付已过期，请重新下单");
        }

        if(!netOrder.getUserId().equals(userId)){
            return ApiResponse.fail(ApiResponseEnum.USER_DEFINED_ERROR, "用户和订单不匹配");
        }


        netOrder.setPayOrderNo("Balance" + netOrder.getId());
        netOrder.setPayTime(new Date());
        netOrderMapper.updateByPrimaryKeySelective(netOrder);
        //余额支付
        this.updateUserBalance(netOrder.getUserId(), netOrder.getAmount(), netOrder.getOrderNo());
        return ApiResponse.success(PaidHandleOrderResult.success(netOrder.getId(), netOrder.getOrderNo()));
    }

    @Override
    public boolean expireCanceledOrder(OrderCancelParam param) {
        NetOrder netOrder = netOrderMapper.selectByPrimaryKey(param.getOrderId());
        if(netOrder == null){return true;}

        if(!NetOrderStatusEnum.UNPAID.getCode().equals(netOrder.getStatus())){
            return true;
        }

        netOrder.setStatus(NetOrderStatusEnum.EXPIRE_CANCELED.getCode());
        Integer count = netOrderMapper.updateByPrimaryKeySelective(netOrder);
        return count > 0;
    }

    @Override
    public PaidHandleOrderResult payByBalance(NetOrder netOrder) {
        return null;
//        if(netOrder == null){
//            return PaidHandleOrderResult.noOrderRecord("" + orderId);
//        }
//
//        netOrder.setPayOrderNo("");
//        netOrder.setPayTime(new Date());
//        netOrderMapper.updateByPrimaryKeySelective(netOrder);
//        //余额支付
//        this.updateUserBalance(netOrder.getUserId(), netOrder.getAmount(), netOrder.getOrderNo());
//        return PaidHandleOrderResult.success(netOrder.getOrderNo());
    }


    @Override
    public NetOrder getByOrderNo(String orderNo) {
        NetOrderExample example = new NetOrderExample();
        example.createCriteria().andOrderNoEqualTo(orderNo);
        List<NetOrder> list = netOrderMapper.selectByExample(example);
        return (list != null && list.size() > 0) ? list.get(0) : null;
    }

    @Override
    public NetOrder getNetOrderById(Integer id) {
        return netOrderMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageBase<NetOrder> queryNetOrder(NetOrderQueryRequest netOrderQueryRequest) {

        Page<NetOrder> page = PageHelper.startPage(netOrderQueryRequest.getPage(), netOrderQueryRequest.getPageSize()
                ,"id asc");
        //Page page =  PageHelper.startPage(movieQueryRequest.getPageNo(), movieQueryRequest.getPageSize());

        NetOrderExample netOrderExample = new NetOrderExample();
        if(!StringUtils.isBlank(netOrderQueryRequest.getUsername())){
            netOrderExample.createCriteria().andUsernameEqualTo(netOrderQueryRequest.getUsername());
        }

        if(!StringUtils.isBlank(netOrderQueryRequest.getOrderNo())){
            netOrderExample.createCriteria().andOrderNoEqualTo(netOrderQueryRequest.getOrderNo());
        }

        if(!StringUtils.isBlank(netOrderQueryRequest.getPayOrderNo())){
            netOrderExample.createCriteria().andPayOrderNoEqualTo(netOrderQueryRequest.getPayOrderNo());
        }


        List<NetOrder> netOrderList = netOrderMapper.selectByExample(netOrderExample);
        if(CollectionUtils.isEmpty(netOrderList)){
            return PageBase.create(page.getTotal(), netOrderQueryRequest.getPage(), netOrderQueryRequest.getPageSize(),
                    new ArrayList<>());
        }
        return PageBase.create(page.getTotal(), netOrderQueryRequest.getPage(), netOrderQueryRequest.getPageSize(),netOrderList);

    }


    /**
     * MQ消息，更新用户余额
     * @param userId
     * @param money
     * @param orderNo
     */
    public void updateUserBalance(Integer userId, Integer money, String orderNo){
        UpdateUserBalanceParam param = new UpdateUserBalanceParam();
        param.setUserId(userId);
        param.setChangeMoney(money);
        param.setMoneyInOutType(MoneyInOutTypeEnum.CONSUME.getCode());
        param.setTradeNo(orderNo);
        String jsonStr = JsonUtil.ParseString(param);
        //rabbitTemplate.convertAndSend(RabbitContants.USER_BALANCE_EXCHANGE, RabbitContants.USER_BALANCE_ROUTING_KEY, jsonStr);
        rocketMQTemplate.convertAndSend("test-topic-1", jsonStr);
    }





    /**
     * 延迟消息，取消30分钟未支付的订单
     * @param orderId
     * @param orderNo
     * @param createTime
     */
    @Override
    public void cancelDelayNotPaidOrder(Integer orderId, String orderNo, Date createTime){
        OrderCancelParam param = new OrderCancelParam();
        param.setOrderId(orderId);
        param.setOrderNo(orderNo);
        param.setCreateTime(createTime);
        String jsonStr = JsonUtil.ParseString(param);

        //rabbitMQ 发送
//        rabbitTemplate.convertAndSend(RabbitContants.ORDER_DELAY_CANCEL_EXCHANGE, RabbitContants.ORDER_DELAY_CANCEL_ROUTING_KEY, jsonStr, message ->{
//            message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);  //消息持久化
//            message.getMessageProperties().setDelay(30 * 60 * 1000);   // 单位为毫秒, 30分钟定时取消
//            return message;
//        });

        //rocketMQ 发送
        // delayLevel=1  2  3   4   5  6  7  8  9  10 11 12 13 14  15  16  17 18
        // delayTime =1s 5s 10s 30s 1m 2m 3m 4m 5m 6m 7m 8m 9m 10m 20m 30m 1h 2h
        // 设置延迟延迟级别延迟30s
        SendResult sendResult = rocketMQTemplate.syncSend("test-topic-1", MessageBuilder.withPayload(jsonStr).build(),
                1000, 4);
        if(!sendResult.getSendStatus().equals(SendStatus.SEND_OK)){
            log.error("延迟消息发送失败：{0}" , sendResult.getSendStatus().toString());
            throw new RuntimeException("延迟消息发送失败：" +sendResult.getSendStatus().toString());
        }
    }
}
