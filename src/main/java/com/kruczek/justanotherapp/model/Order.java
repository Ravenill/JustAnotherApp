package com.kruczek.justanotherapp.model;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;

@Data
public class Order {

    @CsvBindByName(column = "Client_Id")
    private String clientID;

    @CsvBindByName(column = "Request_id")
    private Long requestID;

    @CsvBindByName(column = "name")
    private String name;

    @CsvBindByName(column = "quantity")
    private int quantity;

    @CsvBindByName(column = "price")
    private double price;

    public Order() {

    }

    @java.beans.ConstructorProperties({"clientID", "requestID", "name", "quantity", "price"})
    Order(String clientID, Long requestID, String name, int quantity, double price) {
        this.clientID = clientID;
        this.requestID = requestID;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public static OrderBuilder builder() {
        return new OrderBuilder();
    }

    @Override
    public String toString() {
        return "Order:\n" +
                "\tclientID = " + clientID + "\n" +
                "\trequestID = " + requestID + "\n" +
                "\tname = " + name + "\n" +
                "\tquantity = " + quantity + "\n" +
                "\tprice = " + price + "\n";
    }

    public static class OrderBuilder {
        private String clientID;
        private Long requestID;
        private String name;
        private int quantity;
        private double price;

        OrderBuilder() {
        }

        public OrderBuilder clientID(String clientID) {
            this.clientID = clientID;
            return this;
        }

        public OrderBuilder requestID(Long requestID) {
            this.requestID = requestID;
            return this;
        }

        public OrderBuilder name(String name) {
            this.name = name;
            return this;
        }

        public OrderBuilder quantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        public OrderBuilder price(double price) {
            this.price = price;
            return this;
        }

        public Order build() {
            return new Order(clientID, requestID, name, quantity, price);
        }

        public String toString() {
            return "Order.OrderBuilder(clientID=" + this.clientID + ", requestID=" + this.requestID + ", name=" + this.name + ", quantity=" + this.quantity + ", price=" + this.price + ")";
        }
    }
}
