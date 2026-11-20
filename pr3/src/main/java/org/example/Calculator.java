package org.example;

public class Calculator {

    private void validateInputs(double a, double b) throws InvalidInputException {
        if (Double.isNaN(a) || Double.isNaN(b) || Double.isInfinite(a) || Double.isInfinite(b)) {
            throw new InvalidInputException("Вхідні числа повинні бути кінцевими і не бути NaN");
        }
    }

    public double add(double a, double b) throws InvalidInputException {
        validateInputs(a, b);
        return a + b;
    }

    public double subtract(double a, double b) throws InvalidInputException {
        validateInputs(a, b);
        return a - b;
    }

    public double multiply(double a, double b) throws InvalidInputException {
        validateInputs(a, b);
        return a * b;
    }

    public double divide(double a, double b) throws InvalidInputException {
        validateInputs(a, b);
        if (b == 0.0) {
            throw new ArithmeticException("Поділ на нуль заборонено");
        }
        return a / b;
    }
}