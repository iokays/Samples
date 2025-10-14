package com.iokays.sample.core.domain;

public enum OrderStates {
    DRAFT,          // 草稿
    PENDING_PAYMENT, // 待支付
    PAID,           // 已支付
    SHIPPED,        // 已发货
    DELIVERED,      // 已签收
    COMPLETED,      // 已完成 (订单成功结束)
    CANCELED        // 已取消 (订单失败结束)
}