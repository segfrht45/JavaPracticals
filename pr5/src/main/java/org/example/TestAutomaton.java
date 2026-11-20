package org.example;

public class TestAutomaton {

    public State process(String input) {
        if (input == null) {
            return State.S;
        }

        State currentState = State.S;

        for (char ch : input.toCharArray()) {
            currentState = nextState(currentState, ch);
        }

        return currentState;
    }

    private State nextState(State current, char ch) {
        switch (current) {
            case S:
                return (ch == 'T') ? State.ONE : State.S;
            case ONE:
                if (ch == 'E') return State.TWO;
                if (ch == 'T') return State.ONE;
                return State.S;
            case TWO:
                if (ch == 'S') return State.THREE;
                if (ch == 'T') return State.ONE;
                return State.S;
            case THREE:
                return (ch == 'T') ? State.F : State.S;
            case F:
                return State.F;
            default:
                return State.S;
        }
    }
}