package lotto.controller;

import static org.assertj.core.api.Assertions.assertThat;

import camp.nextstep.edu.missionutils.Console;
import lotto.helper.SystemIoTestHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ConsoleLineReaderTest {
    ConsoleLineReader reader = new ConsoleLineReader();

    @AfterEach
    void tearDown() {
        SystemIoTestHelper.restore();
        Console.close();
    }

    @Test
    @DisplayName("입력읽기")
    void readLine() {
        SystemIoTestHelper.setInput("You know nothing", "John Snow");

        Line first = reader.read();
        Line second = reader.read();
        Line expectedFallBack = reader.read();

        assertThat(first.value()).isEqualTo("You know nothing");
        assertThat(second.value()).isEqualTo("John Snow");
        assertThat(expectedFallBack.value()).isEmpty();
    }

}
