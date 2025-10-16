package calculator.domain;

import static java.util.Collections.unmodifiableList;

import java.util.Arrays;
import java.util.List;

public class Operands {

    private final List<Operand> operands;

    private Operands(List<Operand> operandList) {
        this.operands = operandList;
    }

    public static Operands from(String[] inputNumbers) {
        return new Operands(createOperandList(inputNumbers));
    }

    public static Operands empty() {
        return new Operands(List.of(createOperand("0")));
    }

    public List<Operand> getOperands() {
        return unmodifiableList(operands);
    }

    private static Operand createOperand(String number) {
        return Operand.from(number);
    }

    private static List<Operand> createOperandList(String[] inputNumbers) {
        return Arrays.stream(inputNumbers).map(Operands::createOperand).toList();
    }
}