package com.kruczek.justanotherapp.service;

import com.kruczek.justanotherapp.model.Order;
import com.kruczek.justanotherapp.repository.DatabaseDAO;
import com.kruczek.justanotherapp.repository.Validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class OrderService {

    private List<Order> proceedOrders;
    private double costOfProceedOrders;
    private double averageCostOfProceedOrders;

    private DatabaseDAO databaseDAO;
    private PrinterService printerService;
    private SaveService saveService;
    private MenuService menuService;
    private Validator validator;

    public OrderService() {
        this.proceedOrders = new ArrayList<>();
        this.costOfProceedOrders = 0;
        this.databaseDAO = new DatabaseDAO();
        this.printerService = new PrinterService();
        this.saveService = new SaveService();
        this.menuService = new MenuService();
        this.validator = new Validator();
    }

    public void saveOrdersInBase(List<Order> orders) {
        orders = returnWithoutDuplicates(orders);
        validator.validate(orders);
        databaseDAO.save(orders);
    }

    private List<Order> returnWithoutDuplicates(List<Order> orders) {
        return orders.stream().distinct().collect(Collectors.toList());
    }

    public void proceedOrders(String clientID) {
        handle(clientID);
        showResult();
        proceedOrders();
    }

    private void proceedOrders() {
        proceedOrders.clear();
        costOfProceedOrders = 0;
        averageCostOfProceedOrders = 0;
    }

    private void handle(String clientID) {
        List<Order> orders = Optional.ofNullable(clientID)
                .map(id -> databaseDAO.getOrdersWith(clientID))
                .orElse(databaseDAO.getAllOrders());

        proceedOrders.addAll(orders);
        countCost();
    }

    private void countCost() {
        costOfProceedOrders = proceedOrders.stream()
                .mapToDouble(order -> order.getPrice() * order.getQuantity())
                .sum();

        averageCostOfProceedOrders = costOfProceedOrders / (proceedOrders.stream()
                .mapToDouble(Order::getQuantity)
                .sum());
    }

    private void showResult() {
        if (isSavingToFile()) {
            saveService.saveToFile(proceedOrders);
        }
        showInTerminal();
    }

    private Boolean isSavingToFile() {
        return menuService.isSavingToFile();
    }

    private void showInTerminal() {
        printerService.print(proceedOrders);
        printerService.print(proceedOrders.size());
        printerService.printCost(costOfProceedOrders);
        printerService.printAverageCost(averageCostOfProceedOrders);
    }

    public void startLoading() {
        databaseDAO.createDbConnection();
    }

    public void stopTransferOrders() {
        databaseDAO.closeDbConnection();
    }
}
