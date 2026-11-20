package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CalculatorTest {

    private final Calculator calculator = new Calculator();

    @ParameterizedTest
    @CsvSource({
            "2.0, 3.0, 5.0",
            "-1.5, 4.5, 3.0",
            "0.0, 0.0, 0.0"
    })
    void testAdd(double a, double b, double expected) throws InvalidInputException {
        assertEquals(expected, calculator.add(a, b), 1e-9);
    }

    @ParameterizedTest
    @CsvSource({
            "10.0, 4.0, 6.0",
            "3.0, 8.0, -5.0"
    })
    void testSubtract(double a, double b, double expected) throws InvalidInputException {
        assertEquals(expected, calculator.subtract(a, b), 1e-9);
    }

    @ParameterizedTest
    @CsvSource({
            "3.0, 5.0, 15.0",
            "-2.0, 4.0, -8.0",
            "7.5, 0.0, 0.0"
    })
    void testMultiply(double a, double b, double expected) throws InvalidInputException {
        assertEquals(expected, calculator.multiply(a, b), 1e-9);
    }

    @ParameterizedTest
    @CsvSource({
            "10.0, 2.0, 5.0",
            "9.0, -2.0, -4.5"
    })
    void testDivide(double a, double b, double expected) throws InvalidInputException {
        assertEquals(expected, calculator.divide(a, b), 1e-9);
    }

    @Test
    void testDivideByZeroThrowsArithmeticException() {
        assertThrows(ArithmeticException.class, () -> calculator.divide(10.0, 0.0));
    }

    @Test
    void testInvalidInputThrowsCustomException() {
        assertThrows(InvalidInputException.class, () -> calculator.add(Double.NaN, 5.0));
        assertThrows(InvalidInputException.class, () -> calculator.divide(Double.POSITIVE_INFINITY, 2.0));
    }

    @Test
    void testMainHandlesNonNumericInputAndRunsFinally() {
        String simulatedInput = "abc\n5\n+\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes(StandardCharsets.UTF_8));
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        PrintStream originalOut = System.out;
        java.io.InputStream originalIn = System.in;
        try {
            System.setIn(inputStream);
            System.setOut(new PrintStream(outputStream, true, StandardCharsets.UTF_8));
            Main.main(new String[]{});
        } finally {
            System.setIn(originalIn);
            System.setOut(originalOut);
        }

        String consoleOutput = outputStream.toString(StandardCharsets.UTF_8);
        assertTrue(consoleOutput.contains("Помилка введення: очікувалося число."));
        assertTrue(consoleOutput.contains("Обробку запиту завершено."));
    }
}