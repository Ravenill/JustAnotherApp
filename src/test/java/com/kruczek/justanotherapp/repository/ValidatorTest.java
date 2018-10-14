package com.kruczek.justanotherapp.repository;

import com.kruczek.justanotherapp.model.Order;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class ValidatorTest {

    private Validator validator = new Validator();

    @Test
    public void shouldEraseOneOrder() {
        //given
        List<Order> orders = new ArrayList<>();
        orders.add(Order.builder()
                .clientID("123")
                .requestID(1L)
                .name("Brak")
                .quantity(12)
                .price(1)
                .build());
        orders.add(Order.builder()
                .clientID("123SADASDASDASD")
                .requestID(3L)
                .name("mi")
                .quantity(12)
                .price(1)
                .build());
        orders.add(Order.builder()
                .clientID("123")
                .requestID(2L)
                .name("CZASU")
                .quantity(12)
                .price(1)
                .build());

        //when
        validator.validate(orders);

        //then
        assertThat(orders.size()).isEqualTo(2);
    }

    @Test
    public void shouldEraseTwoOrders() {
//given
        List<Order> orders = new ArrayList<>();
        orders.add(Order.builder()
                .clientID("123 22")
                .requestID(1L)
                .name("Brak")
                .quantity(12)
                .price(1)
                .build());
        orders.add(Order.builder()
                .clientID("123SADASDASDASD")
                .requestID(3L)
                .name("mi")
                .quantity(12)
                .price(1)
                .build());
        orders.add(Order.builder()
                .clientID("123")
                .requestID(2L)
                .name("CZASU")
                .quantity(12)
                .price(1)
                .build());

        //when
        validator.validate(orders);

        //then
        assertThat(orders.size()).isEqualTo(1);
    }

    @Test
    public void shouldEraseThreeOrders() {
//given
        List<Order> orders = new ArrayList<>();
        orders.add(Order.builder()
                .clientID("123 233")
                .requestID(1L)
                .name("Brak")
                .quantity(12)
                .price(1)
                .build());
        orders.add(Order.builder()
                .clientID("123SADASDASDASD")
                .requestID(3L)
                .name("mi")
                .quantity(12)
                .price(1)
                .build());
        orders.add(Order.builder()
                .clientID("12 3")
                .requestID(2L)
                .name("CZASU")
                .quantity(12)
                .price(1)
                .build());

        //when
        validator.validate(orders);

        //then
        assertThat(orders.size()).isEqualTo(0);
    }

    @Test
    public void shouldNotEraseOrders() {
//given
        List<Order> orders = new ArrayList<>();
        orders.add(Order.builder()
                .clientID("123")
                .requestID(1L)
                .name("Brak")
                .quantity(12)
                .price(1)
                .build());
        orders.add(Order.builder()
                .clientID("123")
                .requestID(3L)
                .name("mi")
                .quantity(12)
                .price(1)
                .build());
        orders.add(Order.builder()
                .clientID("123")
                .requestID(2L)
                .name("CZASU")
                .quantity(12)
                .price(1)
                .build());

        //when
        validator.validate(orders);

        //then
        assertThat(orders.size()).isEqualTo(3);
    }
}