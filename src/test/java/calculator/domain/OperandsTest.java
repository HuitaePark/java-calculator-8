package calculator.domain;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class OperandsTest {

    @DisplayName("분리된 문자를 Operand 객체로 생성한다.")
    @Test
    void make_Operand_object() {
        String[] inputNumbers = {"1", "2", "3"};
        Operands operands = Operands.from(inputNumbers);

        assertThat(operands.getOperands()).isInstanceOf(List.class)
                .allSatisfy(operand -> assertThat(operand).isInstanceOf(Operand.class));
    }

    @DisplayName("피연산자는 숫자만 가능하다.")
    @Test
    void Operand_is_only_Number() {
        String[] inputNumbers = {"일", "이", "삼"};
        assertThatThrownBy(() -> {
            Operands operands = Operands.from(inputNumbers);
        })
                .isInstanceOf(IllegalArgumentException.class);
    }
}