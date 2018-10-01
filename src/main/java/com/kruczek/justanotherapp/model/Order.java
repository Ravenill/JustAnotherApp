package com.kruczek.justanotherapp.model;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;

@Data
public class Order {

    @CsvBindByName(column = "Client_Id")
    private String clientID;

    @CsvBindByName(column = "Request_id")
    private Long requestID;

    @CsvBindByName
    private String name;

    @CsvBindByName
    private int quantity;

    @CsvBindByName
    private double price;

    @Override
    public String toString() {
        return "Order{" +
                "clientID='" + clientID + '\'' +
                ", requestID=" + requestID +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}
