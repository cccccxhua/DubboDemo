package com.timor.service;

import org.springframework.stereotype.Service;

@Service
public class OrderService {

    public String createOrder(){
        System.out.println("创建订单");

        return " succeeded in creating the order";
    }
}