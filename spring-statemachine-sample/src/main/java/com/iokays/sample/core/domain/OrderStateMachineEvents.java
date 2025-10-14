package com.iokays.sample.core.domain;

public enum OrderStateMachineEvents {
    CREATE_ORDER,    // 创建订单
    PAY_ORDER,       // 支付订单
    SHIP_ORDER,      // 发货
    DELIVER_ORDER,   // 签收
    CANCEL_ORDER,    // 取消订单
    COMPLETE_ORDER   // 完成订单
}