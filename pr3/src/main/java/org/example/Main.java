package org.example;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Calculator calculator = new Calculator();

        try {
            System.out.print("Введіть перше число: ");
            double a = scanner.nextDouble();

            System.out.print("Введі друге число: ");
            double b = scanner.nextDouble();

            System.out.print("Введіть операцію (+, -, *, /): ");
            String operation = scanner.next();

            double result = switch (operation) {
                case "+" -> calculator.add(a, b);
                case "-" -> calculator.subtract(a, b);
                case "*" -> calculator.multiply(a, b);
                case "/" -> calculator.divide(a, b);
                default -> throw new InvalidInputException("Невідома операція: " + operation);
            };

            System.out.println("Результат: " + result);

        } catch (InputMismatchException e) {
            System.out.println("Помилка введення: очікувалося число.");
        } catch (ArithmeticException | InvalidInputException e) {
            System.out.println("Помилка виконання: " + e.getMessage());
        } finally {
            System.out.println("Обробку запиту завершено.");
            scanner.close();
        }
    }
}