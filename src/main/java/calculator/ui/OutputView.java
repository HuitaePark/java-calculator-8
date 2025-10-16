package calculator.ui;

public class OutputView {

    private final String START_MESSAGE = "덧셈할 문자열을 입력해 주세요.";
    private final String RESULT_MESSAGE = "결과 : %d%n";

    public void printStartMessage() {
        System.out.println(START_MESSAGE);
    }

    public void printResultMessage(long result) {
        System.out.printf(RESULT_MESSAGE, result);
    }
}