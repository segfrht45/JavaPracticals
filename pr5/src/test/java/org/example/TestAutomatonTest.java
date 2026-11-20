package org.example;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestAutomatonTest {

    private final TestAutomaton automaton = new TestAutomaton();

    @ParameterizedTest(name = "Складний кейс із перекриттям префіксів: \"{0}\" -> {1}")
    @CsvSource({
            "TTEST, F",
            "TTTTEST, F",
            "TETEST, F",
            "TETETEST, F",
            "TTES, THREE",
            "TET, ONE"
    })
    void testOverlappingPrefixesBugFix(String input, State expectedState) {
        assertEquals(expectedState, automaton.process(input));
    }

    @ParameterizedTest(name = "Пустий рядок або null повинні повертати S")
    @NullAndEmptySource
    void testNullAndEmpty(String input) {
        assertEquals(State.S, automaton.process(input));
    }
}