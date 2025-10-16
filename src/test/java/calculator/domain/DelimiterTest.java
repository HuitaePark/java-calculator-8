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

    @DisplayName("커스텀 구분자가 숫자일경우 에러가 발생한다.")
    @Test
    void customDelimiter_is_number() {
        String input = "//9\\n19293";
        assertThatThrownBy(() -> {
            Delimiter delimiter = Delimiter.from(input);
        })
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("기본 구분자가 쉼표나 콜론이 아닐경우 에러가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"1;2;3", "1.2.3", "1!2!3", "1@2@3"})
    void basicDelimiter_validate(String input) {
        assertThatThrownBy(() -> {
            Delimiter delimiter = Delimiter.from(input);
        })
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("커스텀 구분자는 기본 구분자를 사용할수 없다.")
    @Test
    void CustomDelimiter_is_not_Basic_Delimiter() {
        String input = "//:\\n1:2:3";
        assertThatThrownBy(() -> {
            Delimiter delimiter = Delimiter.from(input);
        })
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("공백인 커스텀 구분자 테스트")
    @Test
    void CustomDelimiter_is_whiteSpace() {
        String input = "// \\n1 2 3";

        Delimiter delimiter = Delimiter.from(input);
        String[] numbers = delimiter.getTokens();

        assertThat(numbers).containsExactly("1", "2", "3");
    }

    @DisplayName("정규식 특수문자 커스텀 구분자 테스트")
    @ParameterizedTest(name = "입력값: {0}")
    @ValueSource(strings = {
            "//[\\n1[2[3",     // 대괄호
            "//.\\n1.2.3",     // 마침표
            "//*\\n1*2*3",     // 별
            "//+\\n1+2+3",     // 플러스
            "//?\\n1?2?3",     // 물음표
            "//|\\n1|2|3",     // 파이프
            "//^\\n1^2^3",     // 캐럿
            "//$\\n1$2$3",     // 달러
            "//(\\n1(2(3",     // 여는 괄호
            "//)\\n1)2)3",     // 닫는 괄호
            "//{\\n1{2{3",     // 중괄호
            "//\\\\n1\\2\\3" // 역슬래시
    })
    void customDelimiter_withRegexSpecialCharacters_shouldSplitCorrectly(String input) {
        Delimiter delimiter = Delimiter.from(input);
        String[] numbers = delimiter.getTokens();

        assertThat(numbers).containsExactly("1", "2", "3");
    }


}
