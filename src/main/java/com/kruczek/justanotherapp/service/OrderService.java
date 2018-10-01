package com.kruczek.justanotherapp.service;

import com.kruczek.justanotherapp.model.Order;
import com.kruczek.justanotherapp.repository.DatabaseDAO;

import java.util.ArrayList;
import java.util.List;

public class OrderService {

    private List<Order> proceedOrders;
    private double costOfProceedOrders;

    private DatabaseDAO databaseDAO;
    private PrinterService printerService;

    public OrderService() {
        this.proceedOrders = new ArrayList<>();
        this.costOfProceedOrders = 0;
        this.databaseDAO = new DatabaseDAO();
        this.printerService = new PrinterService();
    }

    public void saveOrdersInBase(List<Order> orders) {
        databaseDAO.save(orders);
    }

    public void proceedOrders(Integer clientID) {
        handle(clientID);
        showResult();
    }

    private void handle(Integer clientID) {
        if (clientID == null) {
            proceedOrders.addAll(databaseDAO.getAllOrders());
        } else {
            proceedOrders.addAll(databaseDAO.getOrdersWith(clientID));
        }

        countCost();
    }

    private void countCost() {

        //TODO

    }

    private void showResult() {
        printerService.print(proceedOrders);
        printerService.print(proceedOrders.size());
        printerService.print(costOfProceedOrders);
    }
}
