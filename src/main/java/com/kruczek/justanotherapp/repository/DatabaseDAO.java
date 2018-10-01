package com.kruczek.justanotherapp.repository;

import com.kruczek.justanotherapp.model.Order;

import java.util.List;

public class DatabaseDAO {

    private static final String DB_DRIVER = "org.h2.Driver";
    private static final String DB_CONNECTION = "jdbc:h2:~/test";
    private static final String DB_USER = "";
    private static final String DB_PASSWORD = "";

    public void save(List<Order> orders) {

        //TODO

    }

    public List<Order> getAllOrders() {

        //TODO
        return null;
    }

    public List<Order> getOrdersWith(Integer clientID) {

        //TODO
        return null;
    }

}
