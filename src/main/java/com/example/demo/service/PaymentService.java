package com.example.demo.service;

import com.example.demo.dto.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PaymentService {

    public void processPayment(Order order) throws InterruptedException {
        log.info("Initiate payment for order " + order.getProductId());
        // call actual payment gateway
        Thread.sleep(2000L);
        log.info("Completed payment for order " + order.getProductId());
    }
}
