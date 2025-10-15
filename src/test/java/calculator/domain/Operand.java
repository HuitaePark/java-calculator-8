package calculator.domain;

public class Operand {

    private final int operand;

    private Operand(int number) {
        this.operand = number;
    }

    public static Operand from(String number) {
        validate(number);
        int operand = parseToInt(number);
        return new Operand(operand);
    }

    private static int parseToInt(String number) {
        return Integer.parseInt(number);
    }

    private static void validate(String input) {
        verifyInputIsNumber(input);
    }

    private static void verifyInputIsNumber(String input) {
        if (!input.matches("\\d+")) {
            throw new IllegalArgumentException();
        }
    }
}