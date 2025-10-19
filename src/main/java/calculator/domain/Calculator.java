package calculator.domain;

public class Calculator {

    private final Operands operands;

    private Calculator(Operands operands) {
        this.operands = operands;
    }

    public static Calculator operation(String input) {
        if (input == null || input.isEmpty()) {
            return new Calculator(Operands.defaultValue());
        }

        Delimiter delimiter = Delimiter.from(input);
        Operands operands = Operands.from(delimiter.getTokens());
        return new Calculator(operands);
    }

    public long addition() {
        return operands.getOperands().stream().mapToLong(Operand::getOperand).sum();
    }
}