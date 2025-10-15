package calculator.domain;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class OperandsTest {
    @DisplayName("분리된 문자를 Operand 객체로 생성한다.")
    @Test
    void separate_With_Custom_Delimiter() {
        String[] inputNumbers = {"1", "2", "3"};
        Operands operands = Operands.from(inputNumbers);

        assertThat(operands.getOperands())
                .isInstanceOf(List.class)
                .allSatisfy(operand -> assertThat(operand).isInstanceOf(Operand.class));
    }
}
