package com.shop.processing;

import com.shop.model.Order;
import com.shop.model.Product;
import com.shop.storage.OrderStorage;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class OrderProcessor<T extends Product> {
    private final OrderStorage<T> storage;

    public OrderProcessor(OrderStorage<T> storage) {
        this.storage = storage;
    }

    public void processSingleOrder(Order<T> order) {
        System.out.println("Потік " + Thread.currentThread().getName() + " обробляє замовлення: " + order.getOrderId());
        try {
            Thread.sleep(500);
            order.markAsProcessed();
            System.out.printf("Замовлення %s успішно оброблено! Сума: %.2f грн%n", order.getOrderId(), order.getTotalPrice());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Потоку було перервано обробку замовлення " + order.getOrderId());
        }
    }

    public void processAllOrdersConcurrently() {
        var orders = storage.getOrders();

        ExecutorService executor = Executors.newFixedThreadPool(3);

        for (Order<T> order : orders) {
            executor.submit(() -> processSingleOrder(order));
        }

        executor.shutdown();
        try {
            executor.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}