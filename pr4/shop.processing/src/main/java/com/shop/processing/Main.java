package com.shop.processing;

import com.github.javafaker.Faker;
import com.shop.model.Clothing;
import com.shop.model.Electronics;
import com.shop.model.InvalidOrderException;
import com.shop.model.Order;
import com.shop.model.Product;
import com.shop.storage.OrderStorage;

public class Main {
    public static void main(String[] args) {
        Faker faker = new Faker();

        OrderStorage<Product> storage = new OrderStorage<>();

        System.out.println("=== Генерація замовлень за допомогою JavaFaker ===");

        try {
            for (int i = 0; i < 5; i++) {
                Order<Product> order = new Order<>();

                Electronics electronics = Electronics.builder()
                        .name(faker.commerce().productName())
                        .price(Double.parseDouble(faker.commerce().price(1000, 25000).replace(',','.')))
                        .warrantyMonths(12)
                        .build();
                order.addItem(electronics);

                Clothing clothing = Clothing.builder()
                        .name(faker.commerce().material() + " item")
                        .price(Double.parseDouble(faker.commerce().price(300, 3000).replace(',','.')))
                        .size("M")
                        .material(faker.commerce().material())
                        .build();
                order.addItem(clothing);

                storage.addOrder(order);
                System.out.printf("Створено замовлення №: %s на суму: %.2f грн%n", order.getOrderId(), order.getTotalPrice());
            }
        } catch (InvalidOrderException e) {
            System.err.println("Помилка при створенні замовлення: " + e.getMessage());
        }

        System.out.println("\n=== Запуск багатопотокової обробки замовлень ===");

        OrderProcessor<Product> processor = new OrderProcessor<>(storage);
        processor.processAllOrdersConcurrently();

        System.out.println("\n=== Всі замовлення опрацьовано! Система завершила роботу. ===");
    }
}