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

    @DisplayName("피연산자는 양수만 가능하다.")
    @Test
    void Operand_is_only_positive() {
        String[] inputNumbers = {"-1", "-2", "-3"};
        assertThatThrownBy(() -> {
            Operands operands = Operands.from(inputNumbers);
        })
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("Integer 최댓값 생성 테스트")
    @Test
    void make_Operand_maxValue() {
        String[] inputNumbers = {"2147483647", "2147483647", "2147483647"};
        Operands operands = Operands.from(inputNumbers);

        assertThat(operands.getOperands()).isInstanceOf(List.class)
                .allSatisfy(operand -> assertThat(operand).isInstanceOf(Operand.class));
    }

    @DisplayName("정규식 문자 테스트")
    @Test
    void make_Operand_regex() {
        String[] inputNumbers = {"\\", "\\", "\\"};
        assertThatThrownBy(() -> {
            Operands operands = Operands.from(inputNumbers);
        })
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("operand를 null로 만드는 테스트")
    @Test
    void make_Operand_null() {
        String[] inputNumbers = {null, null, null};
        assertThatThrownBy(() -> {
            Operands operands = Operands.from(inputNumbers);
        })
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("operand를 소수로 만드는 테스트")
    @Test
    void make_Operand_Decimal() {
        String[] inputNumbers = {"2.1", "2.1", "2.1"};
        assertThatThrownBy(() -> {
            Operands operands = Operands.from(inputNumbers);
        })
                .isInstanceOf(IllegalArgumentException.class);
    }

}
