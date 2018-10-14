package com.kruczek.justanotherapp.repository;

import com.kruczek.justanotherapp.exception.SQLQueryException;
import com.kruczek.justanotherapp.model.Order;

import java.sql.*;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class DatabaseDAO {

    private static final String DB_DRIVER = "org.h2.Driver";
    private static final String DB_CONNECTION = "jdbc:h2:mem:test";
    private static final String DB_SETTINGS = ";DB_CLOSE_DELAY=-1;INIT=RUNSCRIPT FROM 'classpath:schema.sql'";
    private static final String DB_USER = "";
    private static final String DB_PASSWORD = "";

    private static final String INSERT_STATEMENT = "INSERT INTO ORDERS (REQUEST_ID, CLIENT_ID, NAME, QUANTITY, PRICE) VALUES (''{0}'', ''{1}'', ''{2}'', ''{3}'', ''{4}'')";
    private static final String GET_ALL_ORDER_QUERY = "SELECT * FROM ORDERS";
    private static final String GET_ORDER_WITH_CLIENT_QUERY = "SELECT * FROM ORDERS WHERE CLIENT_ID=''{0}''";

    private Connection connection;
    private Statement statement;

    public void save(List<Order> orders) {
        orders.forEach(order -> insertToDb(order));
    }

    private void insertToDb(Order order) {
        String query = MessageFormat.format(INSERT_STATEMENT
                , order.getRequestID()
                , order.getClientID()
                , order.getName()
                , order.getQuantity()
                , order.getPrice());

        execute(query);
    }

    public void createDbConnection() {
        try {
            Class.forName(DB_DRIVER);
            connection = DriverManager.getConnection(DB_CONNECTION + DB_SETTINGS, DB_USER, DB_PASSWORD);
            statement = connection.createStatement();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void closeDbConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Order> getAllOrders() {
        ArrayList<Order> orders = new ArrayList<>();

        ResultSet result = executeQuery(GET_ALL_ORDER_QUERY);
        add(result, orders);

        return orders;
    }

    private void add(ResultSet result, ArrayList<Order> orders) {
        try {
            while (result.next()) {
                Order actualOrder = Order.builder()
                        .requestID(result.getLong("REQUEST_ID"))
                        .clientID(result.getString("CLIENT_ID"))
                        .name(result.getString("NAME"))
                        .quantity(result.getInt("QUANTITY"))
                        .price(result.getDouble("PRICE"))
                        .build();

                orders.add(actualOrder);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Order> getOrdersWith(String clientID) {
        ArrayList<Order> orders = new ArrayList<>();

        String query = MessageFormat.format(GET_ORDER_WITH_CLIENT_QUERY
                , clientID);

        ResultSet result = executeQuery(query);
        add(result, orders);

        return orders;
    }

    private ResultSet executeQuery(String query) {
        try {
            return statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLQueryException();
        }
    }

    private boolean execute(String query) {
        try {
            return statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLQueryException();
        }
    }

}
