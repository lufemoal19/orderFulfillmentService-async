package com.example.demo.service;


import com.example.demo.dto.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class OrderFulfillmentService {

    @Autowired
    private InventoryService inventoryService;

    @Autowired
    private PaymentService paymentService;

    /* All Required process */
    /*
        1. Inventory service check order availability
        2. Process payment for order
        3. Notify to the user
        4. Assign to vendor
        5. Packaging
        6. Assign delivery partner
        7. Assign trailer
        8. Dispatch product
    */

    public Order processOrder(Order order) throws InterruptedException {
        order.setTrackingId(UUID.randomUUID().toString());

        if (inventoryService.checkProductAvailability(order.getProductId())){
            // handle exception here
            paymentService.processPayment(order);
        } else {
            throw new RuntimeException("Technical issue please retry");
        }

        return order;
    }

    @Async("asyncTaskExecutor")
    public void notifyUser(Order order) throws InterruptedException {
        Thread.sleep(4000L);
        log.info("Notified to the user " + Thread.currentThread().getName());
    }

    @Async("asyncTaskExecutor")
    public void assignVendor(Order order) throws InterruptedException {
        Thread.sleep(5000L);
        log.info("Order packaging completed " + Thread.currentThread().getName());
    }

    @Async("asyncTaskExecutor")
    public void packaging(Order order) throws InterruptedException{
        Thread.sleep(2000L);
        log.info("Delivery partner assigned " + Thread.currentThread().getName());
    }

    @Async("asyncTaskExecutor")
    public void assignDeliveryPartner(Order order) throws InterruptedException {
        Thread.sleep(1000L);
        log.info("Delivery partner assigned " + Thread.currentThread().getName());
    }

    @Async("asyncTaskExecutor")
    public void assignTrailerAndDispatch(Order order) throws InterruptedException {
        Thread.sleep(3000L);
        log.info("Trailer assigned and Order dispatched " + Thread.currentThread().getName());
    }
}
