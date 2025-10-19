package calculator.controller;


import static org.assertj.core.api.Assertions.assertThat;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CalculatorIntegrationTest {

    private final InputStream originalIn = System.in;
    private final PrintStream originalOut = System.out;
    private ByteArrayOutputStream outContent;

    @BeforeEach
    void setUp() {
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void tearDown() {
        System.setIn(originalIn);
        System.setOut(originalOut);
    }

    @DisplayName("사용자의 문자열을 입력받아서 덧셈 결과를 출력한다.")
    @Test
    void success_calculator_controller_flow() {

        String input = "1,2,3\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        CalculatorController controller = CalculatorController.createController();
        controller.startCalculate();

        assertThat(outContent.toString()).contains("결과 : 6");
    }
}