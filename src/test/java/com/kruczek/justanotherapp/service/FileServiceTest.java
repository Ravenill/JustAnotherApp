package com.kruczek.justanotherapp.service;

import com.kruczek.justanotherapp.exception.UnsupportedExtensionException;
import com.kruczek.justanotherapp.model.Order;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertThat;

public class FileServiceTest {

    FileService fileService = new FileService();

    @Test
    public void shouldParseXmlFile() {
        //given
        String[] path = new String[] {"resources/test2.xml"};

        //when
        ArrayList<Order> orders = (ArrayList<Order>)fileService.decodeToOrderList(path);

        //then

    }

    @Test
    public void shouldParseCsvFile() {
        //given
        String[] path = new String[] {"resources/test1.csv"};

        //when
        ArrayList<Order> orders = (ArrayList<Order>)fileService.decodeToOrderList(path);

        //then

    }

    @Test(expected = UnsupportedExtensionException.class)
    public void shouldThrowException() {

    }
}