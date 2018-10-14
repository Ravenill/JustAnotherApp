package com.kruczek.justanotherapp.service;

import com.kruczek.justanotherapp.model.Order;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Brak czasu na debug StatefulBeanToCsv::write -> zwraca pusty plik

public class SaveService {

    public static final String FILE_HEADER = "Client_Id,Request_id,Name,Quantity,Price";
    public static final String NEW_LINE_SEPARATOR = "\n";
    public static final String SEPARATOR = ",";

    public void saveToFile(List<Order> proceedOrders) {

        try {
            FileWriter fileWriter = new FileWriter("./"+ getFileNameFromUser() + ".csv");

            fileWriter.append(FILE_HEADER);
            fileWriter.append(NEW_LINE_SEPARATOR);
            proceedOrders.forEach(order -> save(order, fileWriter));

            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void save(Order order, FileWriter fileWriter) {
        try {
            fileWriter.append(String.valueOf(order.getClientID()));
            fileWriter.append(SEPARATOR);
            fileWriter.append(String.valueOf(order.getRequestID()));
            fileWriter.append(SEPARATOR);
            fileWriter.append(String.valueOf(order.getName()));
            fileWriter.append(SEPARATOR);
            fileWriter.append(String.valueOf(order.getQuantity()));
            fileWriter.append(SEPARATOR);
            fileWriter.append(String.valueOf(order.getPrice()));
            fileWriter.append(NEW_LINE_SEPARATOR);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public void saveToFile(List<Order> proceedOrders, double costOfProceedOrders) {
//        Writer writer = getWriter();
//
//        StatefulBeanToCsv beanToCsv = new StatefulBeanToCsvBuilder(writer)
//                .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
//                .build();
//
//        try {
//            beanToCsv.write(proceedOrders);
//        } catch (CsvDataTypeMismatchException e) {
//            e.printStackTrace();
//        } catch (CsvRequiredFieldEmptyException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private BufferedWriter getWriter() {
//        BufferedWriter writer = null;
//        try {
//            writer = Files.newBufferedWriter(Paths.get("./"+ getFileNameFromUser() + ".csv"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return writer;
//    }

    private String getFileNameFromUser() {
        System.out.println("Filename:");
        return new Scanner(System.in).nextLine();
    }
}
