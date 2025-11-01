package lotto.adaptor.implement;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import camp.nextstep.edu.missionutils.Console;
import java.util.NoSuchElementException;
import lotto.helper.SystemIoTestHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ConsoleInputLineReaderTest {
    ConsoleInputLineReader reader = new ConsoleInputLineReader();

    @AfterEach
    void tearDown() {
        SystemIoTestHelper.restore();
        Console.close();
    }

    @Test
    @DisplayName("입력읽기")
    void readLineLine() {
        SystemIoTestHelper.setInput("You know nothing", "John Snow");

        assertThat(reader.readLine()).isEqualTo("You know nothing");
        assertThat(reader.readLine()).isEqualTo("John Snow");
    }

    @Test
    @DisplayName("입력이 없는 경우 예외")
    void readLineLineNoInput() {
        assertThatThrownBy(() -> reader.readLine())
                .isInstanceOf(NoSuchElementException.class);

    }

}
