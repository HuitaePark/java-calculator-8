package calculator.domain;

public class Calculator {

    private final Operands operands;

    private Calculator(Operands operands) {
        this.operands = operands;
    }

    public static Calculator operation(String input) {
        Delimiter delimiter = Delimiter.from(input);
        Operands operands = Operands.from(delimiter.getTokens());
        return new Calculator(operands);
    }

    public int addition() {
        return operands.getOperands().stream().mapToInt(Operand::getOperand).sum();
    }
}
