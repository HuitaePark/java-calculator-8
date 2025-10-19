package calculator.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CalculatorTest {

    @DisplayName("계산기는 분리된 각 숫자를 합한다.")
    @Test
    void combine_element_by_calculator() {
        String input = "//;\\n1;2;3";
        Calculator calculator = Calculator.operation(input);

        long result = calculator.addition();

        assertThat(result).isEqualTo(6);
    }

    @DisplayName("공백 문자열이 들어올경우 0을 반환한다.")
    @Test
    void empty_string_return_zero() {
        String input = "";
        Calculator calculator = Calculator.operation(input);

        long result = calculator.addition();

        assertThat(result).isEqualTo(0);
    }

    @DisplayName("정수 최대값 합산 테스트")
    @Test
    void combine_max_element_by_calculator() {
        String input = "//;\\n2147483647;2147483647;2147483647";
        Calculator calculator = Calculator.operation(input);

        long result = calculator.addition();

        assertThat(result).isEqualTo(6442450941L);
    }
}