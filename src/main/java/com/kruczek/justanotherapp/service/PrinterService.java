package com.kruczek.justanotherapp.service;

import com.kruczek.justanotherapp.model.Order;

import java.util.List;

public class PrinterService {

    public void print(List<Order> orders) {
        orders.forEach(order -> System.out.printf(order.toString()));
    }

    public void print(int amount) {
        System.out.printf("Amount of orders: " + String.valueOf(amount) + "\n");
    }

    public void printCost(double cost) {
        System.out.printf("Cost of items: " + String.valueOf(cost) + "\n");
    }

    public void printAverageCost(double averageCost) {
        System.out.printf("Average cost of items: " + String.valueOf(averageCost) + "\n");
    }
}
