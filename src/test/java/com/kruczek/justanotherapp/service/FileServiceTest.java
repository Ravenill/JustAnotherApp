package com.kruczek.justanotherapp.service;

import com.kruczek.justanotherapp.exception.UnsupportedExtensionException;
import com.kruczek.justanotherapp.model.Order;
import org.junit.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

public class FileServiceTest {

    FileService fileService = new FileService();

    @Test
    public void shouldParseXmlFile() {
        //given
        String[] path = new String[] {"src/test/resources/test2.xml"};

        //when
        ArrayList<Order> orders = (ArrayList<Order>)fileService.decodeToOrderList(path);

        //then
        assertThat(orders.size()).isNotEqualTo(0);
    }

    @Test
    public void shouldParseCsvFile() {
        //given
        String[] path = new String[] {"src/test/resources/test1.csv"};

        //when
        ArrayList<Order> orders = (ArrayList<Order>)fileService.decodeToOrderList(path);

        //then
        assertThat(orders.size()).isNotEqualTo(0);
    }

    @Test(expected = UnsupportedExtensionException.class)
    public void shouldThrowException() {
        String[] path = new String[] {"src/test/resources/test3.xxx"};

        //when
        ArrayList<Order> orders = (ArrayList<Order>)fileService.decodeToOrderList(path);
    }
}