package calculator.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DelimiterTest {

    @DisplayName("커스텀 구분자가 있다면 커스텀 문자열로 문자열을 분리한다.")
    @Test
    void separate_With_Custom_Delimiter() {
        String input = "//;\\n1;2;3";

        Delimiter delimiter = new Delimiter(input);
        String[] numbers = delimiter.getTokens();

        assertThat(numbers).containsExactly("1", "2", "3");
    }
}
