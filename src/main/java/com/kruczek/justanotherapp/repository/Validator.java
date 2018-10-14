package com.kruczek.justanotherapp.repository;

import com.kruczek.justanotherapp.model.Order;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class Validator {

    public void validate(List<Order> orders) {
        List<Order> invalidOrders = gatherInvalidate(orders);
        invalidOrders.forEach(orders::remove);
        if (!invalidOrders.isEmpty()) {
            log.warn("Invalid data in file. Invalid orders will be skipped: ");
            invalidOrders.forEach(order -> log.warn(order.toString()));
        }
    }

    private List<Order> gatherInvalidate(List<Order> orders) {
        List<Order> invalidateOrders = new ArrayList<>();
        orders.forEach(order -> validate(order, invalidateOrders));
        return invalidateOrders;
    }

    private void validate(Order order, List<Order> invalidOrders) {
        String clientID = order.getClientID();
        String name = order.getName();

        if (clientID.length() > 6 || clientID.contains(" ")) {
            invalidOrders.add(order);
            return;
        }

        if (name.length() > 255) {
            invalidOrders.add(order);
            return;
        }
    }
}
