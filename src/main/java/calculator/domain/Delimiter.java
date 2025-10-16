package calculator.domain;


import java.util.List;
import java.util.regex.Pattern;

public class Delimiter {
    private static final String BASIC_DELIMITER = "[:,]";
    private static final List<String> BASIC_DELIMITER_SYMBOLS = List.of(",", ":");

    private String customDelimiter = "";

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
        verifyCustomDelimiterIsSingle(input);
        verifyCustomDelimiterIsNumber(input);
        verifyBasicDelimiter(input);
        verifyCustomIsBasicDelimiter(input);
    }

    public String[] getTokens() {
        return tokens;
    }

    private String[] separatedByDelimiter(String input) {
        if (!customDelimiter.isEmpty()) {
            input = removeCustomPart(input);
            return input.split(Pattern.quote(customDelimiter));
        }
        return input.split(BASIC_DELIMITER);
    }

    private String determineCustomDelimiter(String input) {
        if (hasCustomDelimiter(input)) {
            return extractCustomDelimiter(input);
        }
        return "";
    }

    private String extractCustomDelimiter(String input) {
        return input.substring(2, input.indexOf("\\n"));
    }

    private boolean hasCustomDelimiter(String input) {
        return input.startsWith("//") && input.contains("\\n");
    }

    private String removeCustomPart(String input) {
        return input.substring(5);
    }

    private void verifyCustomDelimiterIsSingle(String input) {
        if (hasCustomDelimiter(input) && !input.matches("^//.\\\\n.*")) {
            throw new IllegalArgumentException();
        }
    }

    private void verifyCustomDelimiterIsNumber(String input) {
        if (hasCustomDelimiter(input) && this.customDelimiter.matches("\\d+")) {
            throw new IllegalArgumentException();
        }
    }

    private void verifyBasicDelimiter(String input) {
        if (!hasCustomDelimiter(input) && !input.matches(".*[,:].*")) {
            throw new IllegalArgumentException();
        }
    }

    private void verifyCustomIsBasicDelimiter(String input) {
        if (hasCustomDelimiter(input) && BASIC_DELIMITER_SYMBOLS.contains(customDelimiter)) {
            throw new IllegalArgumentException();
        }
    }
}