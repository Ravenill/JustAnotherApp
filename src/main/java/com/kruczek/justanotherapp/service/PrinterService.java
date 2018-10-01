package com.kruczek.justanotherapp.service;

import com.kruczek.justanotherapp.model.Order;

import java.util.List;

public class PrinterService {

    public void print(List<Order> orders) {
        for (Order order : orders) {
            System.out.printf(order.toString());
        }
    }

    public void print(int amount) {
        System.out.printf(String.valueOf(amount));
    }

    public void print(double cost) {
        System.out.printf(String.valueOf(cost));
    }
}
