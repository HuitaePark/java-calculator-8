package calculator.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class DelimiterTest {

    @DisplayName("커스텀 구분자가 있다면 커스텀 문자열로 문자열을 분리한다.")
    @Test
    void separate_With_Custom_Delimiter() {
        String input = "//;\\n1;2;3";

        Delimiter delimiter = Delimiter.from(input);
        String[] numbers = delimiter.getTokens();

        assertThat(numbers).containsExactly("1", "2", "3");
    }

    @DisplayName("커스텀 구분자가 없다면 기본 문자열로 문자열을 분리한다.")
    @ParameterizedTest
    @ValueSource(strings = {"1:2:3", "1,2,3", "1:2,3", "1,2:3"})
    void separate_With_Basic_Delimiter(String input) {
        Delimiter delimiter = Delimiter.from(input);
        String[] numbers = delimiter.getTokens();

        assertThat(numbers).containsExactly("1", "2", "3");
    }

    @DisplayName("커스텀 구분자가 한 자가 아닐경우 에러가 발생한다.")
    @Test
    void customDelimiter_is_not_single() {
        String input = "//;;\\n1;;2;;3";
        assertThatThrownBy(() -> {
            Delimiter delimiter = Delimiter.from(input);
        })
                .isInstanceOf(IllegalArgumentException.class);
    }
}
