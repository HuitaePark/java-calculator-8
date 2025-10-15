package calculator.controller;

import calculator.domain.Calculator;
import calculator.ui.InputHandler;
import calculator.ui.OutputView;

public class CalculatorController {

    private final InputHandler inputHandler;
    private final OutputView outputView;

    private CalculatorController(InputHandler inputHandler, OutputView outputView) {
        this.inputHandler = inputHandler;
        this.outputView = outputView;
    }

    public static CalculatorController createController() {
        return new CalculatorController(new InputHandler(), new OutputView());
    }

    public void startCalculate() {
        showStartMessage();
        String input = receiveInput();
        int result = calculate(input);
        showResultMessage(result);
    }

    private int calculate(String input) {
        return Calculator.operation(input).addition();
    }

    private String receiveInput() {
        return inputHandler.inputText();
    }

    private void showStartMessage() {
        outputView.printStartMessage();
    }

    private void showResultMessage(int result) {
        outputView.printResultMessage(result);
    }

}