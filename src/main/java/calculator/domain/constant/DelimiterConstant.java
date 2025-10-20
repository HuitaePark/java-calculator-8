package calculator.domain.constant;

import java.util.List;

public class DelimiterConstant {
    public static final String BASIC_DELIMITER_REGEX = "[:,]";
    public static final List<String> BASIC_DELIMITER_SYMBOLS = List.of(",", ":");

    public static final String FIND_SINGLE_CUSTOM_DELIMITER_REGEX = "^//.\\\\n.*";
    public static final String FIND_NUMBER_REGEX = "\\d+";
    public static final String FIND_BASIC_DELIMITER_REGEX = ".*[,:].*";
    public static final String SEPARATION_BEGIN_EXPRESSION = "//";
    public static final String SEPARATION_END_EXPRESSION = "\\n";

    public static final int CUSTOM_DELIMITER_EXPRESSION_INDEX = 5;
    public static final int CUSTOM_DELIMITER_INDEX = 2;
}
