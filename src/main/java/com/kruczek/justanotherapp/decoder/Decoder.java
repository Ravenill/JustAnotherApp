package com.kruczek.justanotherapp.decoder;

import com.kruczek.justanotherapp.model.Order;

import java.util.List;

public interface Decoder {

    void decode(String path, List<Order> orders);
}
