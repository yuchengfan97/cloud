package com.isss.springcloud.service;

import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements PaymentHystrixService{
    @Override
    public String paymentInfo_ok(Integer id) {
        return "----------PaymentFallbackService fall back-paymentInfo_ok";
    }

    @Override
    public String paymentInfo_TimeOut(Integer id) {
        return "----------PaymentFallbackService fall paymentInfo_TimeOut";
    }
}
