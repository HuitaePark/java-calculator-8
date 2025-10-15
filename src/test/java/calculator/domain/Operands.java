package calculator.domain;

import static java.util.Collections.unmodifiableList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Operands {

    private final List<Operand> operands;

    private Operands(List<Operand> operandList) {
        this.operands = operandList;
    }

    public static Operands from(String[] inputNumbers) {
        List<Operand> operandList = new ArrayList<>();

        Arrays.asList(inputNumbers).forEach(number -> operandList.add(new Operand(number)));

        return new Operands(operandList);
    }

    public List<Operand> getOperands() {
        return unmodifiableList(operands);
    }

}
