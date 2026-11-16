package com.shop.model;

import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@ToString
public class Order<T extends Product> {
    private final String orderId;
    private final List<T> items;
    private boolean processed;

    public Order() {
        this.orderId = UUID.randomUUID().toString().substring(0, 8);
        this.items = new ArrayList<>();
        this.processed = false;
    }

    public void addItem(T item) throws InvalidOrderException {
        if (item == null || item.getPrice() <= 0) {
            throw new InvalidOrderException("Товар не може бути null або мати нульову/від'ємну ціну!");
        }
        items.add(item);
    }

    public double getTotalPrice() {
        return items.stream()
                .mapToDouble(Product::getPrice)
                .sum();
    }

    public void markAsProcessed() {
        this.processed = true;
    }
}