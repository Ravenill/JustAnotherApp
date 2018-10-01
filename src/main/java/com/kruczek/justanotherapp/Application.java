package com.kruczek.justanotherapp;

import com.kruczek.justanotherapp.exception.ExitTypeException;
import com.kruczek.justanotherapp.exception.NoIDException;
import com.kruczek.justanotherapp.service.FileService;
import com.kruczek.justanotherapp.service.MenuService;
import com.kruczek.justanotherapp.service.OrderService;

public class Application {

    private MenuService menuService;
    private FileService fileService;
    private OrderService orderService;

    boolean running = true;

    public Application() {
        this.menuService = new MenuService();
        this.fileService = new FileService();
        this.orderService = new OrderService();
    }

    public void run(String[] filesNames) {
        loadOrders(filesNames);

        while (running) {
            showMenu();
            proceedOrders(getClientID());
        }
    }

    private void loadOrders(String[] filesNames) {
        orderService.saveOrdersInBase(fileService.decodeToOrderList(filesNames));
    }

    private void showMenu() {
        menuService.showMenu();
    }

    private Integer getClientID() {
        try {
            return menuService.getClientID();
        } catch (ExitTypeException e) {
            running = false;
            return null;
        } catch (NoIDException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void proceedOrders(Integer clientID) {
        if (running) {
            orderService.proceedOrders(clientID);
        }
    }
}
