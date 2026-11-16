package com.shop.storage;

import com.shop.model.Order;
import com.shop.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class OrderStorage<T extends Product> {
    private final List<Order<T>> orders = new CopyOnWriteArrayList<>();

    public void addOrder(Order<T> order) {
        orders.add(order);
    }

    public List<Order<T>> getOrders() {
        return new ArrayList<>(orders);
    }

    public int getOrderCount() {
        return orders.size();
    }
}