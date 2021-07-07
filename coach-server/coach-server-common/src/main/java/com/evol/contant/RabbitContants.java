package com.evol.contant;

public interface RabbitContants {
    String MSG_QUEUE = "msg_queue";
    String MSG_EXCHANGE = "msg_exchange";
    String MSG_ROUTING_KEY = "msg";

    String USER_BALANCE_EXCHANGE = "user_balance_exchange";
    String USER_BALANCE_ROUTING_KEY = "user_balance_routing_key";
    String USER_BALANCE_QUEUE = "user_balance_queue";

    String ORDER_DELAY_CANCEL_EXCHANGE = "order_delay_cancel_exchange";
    String ORDER_DELAY_CANCEL_QUEUE = "order_delay_cancel_queue";
    String ORDER_DELAY_CANCEL_ROUTING_KEY = "order_delay_cancel_routing_key";
}

