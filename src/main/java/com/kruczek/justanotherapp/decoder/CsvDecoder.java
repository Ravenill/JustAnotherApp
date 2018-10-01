package com.kruczek.justanotherapp.decoder;

import com.kruczek.justanotherapp.model.Order;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class CsvDecoder implements Decoder {

    @Override
    public void decode(String path, List<Order> orders) {
        CsvToBean<Order> csvToBean = getCsvBean(path);
        orders.addAll(csvToBean.parse());
    }

    private CsvToBean getCsvBean(String path) {
        return new CsvToBeanBuilder(getReader(path))
                .withType(Order.class)
                .withIgnoreLeadingWhiteSpace(true)
                .build();
    }

    private BufferedReader getReader(String path) {
        try {
            return Files.newBufferedReader(Paths.get(path));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }
}
