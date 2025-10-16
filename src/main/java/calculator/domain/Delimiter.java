package calculator.domain;


import calculator.domain.constant.DelimiterConstant;
import java.util.regex.Pattern;

public class Delimiter {
    private final String customDelimiter;
    private final String[] tokens;

    private Delimiter(String input) {
        this.customDelimiter = determineCustomDelimiter(input);
        validate(input);
        this.tokens = separatedByDelimiter(input);
    }

    public static Delimiter from(String input) {
        return new Delimiter(input);
    }

    private void validate(String input) {
        if (!hasCustomDelimiter(input)) {
            verifyBasicDelimiter(input);
            return;
        }

        verifyCustomDelimiterIsSingle(input);
        verifyCustomDelimiterIsNumber();
        verifyCustomIsBasicDelimiter();
    }

    public String[] getTokens() {
        return tokens;
    }

    private String[] separatedByDelimiter(String input) {
        if (!customDelimiter.isEmpty()) {
            input = removeCustomDelimiterExpression(input);
            return input.split(Pattern.quote(customDelimiter));
        }
        return input.split(DelimiterConstant.BASIC_DELIMITER_REGEX);
    }

    private String determineCustomDelimiter(String input) {
        if (hasCustomDelimiter(input)) {
            return extractCustomDelimiter(input);
        }
        return "";
    }

    private String extractCustomDelimiter(String input) {
        return input.substring(DelimiterConstant.CUSTOM_DELIMITER_INDEX,
                input.indexOf(DelimiterConstant.SEPARATION_END_EXPRESSION));
    }

    private boolean hasCustomDelimiter(String input) {
        return input.startsWith(DelimiterConstant.SEPARATION_BEGIN_EXPRESSION) && input.contains(
                DelimiterConstant.SEPARATION_END_EXPRESSION);
    }

    private String removeCustomDelimiterExpression(String input) {
        return input.substring(DelimiterConstant.CUSTOM_DELIMITER_EXPRESSION_INDEX);
    }

    private void verifyCustomDelimiterIsSingle(String input) {
        if (!input.matches(DelimiterConstant.FIND_SINGLE_CUSTOM_DELIMITER_REGEX)) {
            throw new IllegalArgumentException();
        }
    }

    private void verifyCustomDelimiterIsNumber() {
        if (this.customDelimiter.matches(DelimiterConstant.FIND_NUMBER_REGEX)) {
            throw new IllegalArgumentException();
        }
    }

    private void verifyBasicDelimiter(String input) {
        if (!input.matches(DelimiterConstant.FIND_BASIC_DELIMITER_REGEX)) {
            throw new IllegalArgumentException();
        }
    }

    private void verifyCustomIsBasicDelimiter() {
        if (DelimiterConstant.BASIC_DELIMITER_SYMBOLS.contains(customDelimiter)) {
            throw new IllegalArgumentException();
        }
    }
}