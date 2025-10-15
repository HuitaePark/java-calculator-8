package calculator.domain;


public class Delimiter {
    private final String basicDelimiter = "[:,]";
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
    }

    public String[] getTokens() {
        return tokens;
    }

    private String[] separatedByDelimiter(String input) {
        if (!customDelimiter.isEmpty()) {
            input = removeCustomPart(input);
            return input.split(customDelimiter);
        }
        return input.split(basicDelimiter);
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

}
