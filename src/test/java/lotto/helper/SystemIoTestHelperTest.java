package lotto.helper;

import static lotto.helper.SystemIoTestHelper.output;
import static lotto.helper.SystemIoTestHelper.restore;
import static lotto.helper.SystemIoTestHelper.setInput;
import static lotto.helper.SystemIoTestHelper.startRecord;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import camp.nextstep.edu.missionutils.Console;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SystemIoTestHelperTest {

    @BeforeEach
    void setup() {
        startRecord();
    }

    @AfterEach
    void tearDown() {
        restore();
        Console.close();
    }

    @Test
    @DisplayName("입출력 테스트")
    void io() {
        setInput("John Snow", "Arya Stark", "");

        String firstLine = Console.readLine();
        String secondLine = Console.readLine();
        String thirdLine = Console.readLine();

        assertThat(firstLine).isEqualTo("John Snow");
        assertThat(secondLine).isEqualTo("Arya Stark");
        assertThat(thirdLine).isEmpty();

        assertThatThrownBy(Console::readLine).isInstanceOf(NoSuchElementException.class);

        System.out.println(firstLine);
        System.out.println(secondLine);
        System.out.println(thirdLine);

        assertThat(output()).isEqualTo(firstLine + "\n" + secondLine + "\n" + thirdLine + "\n");
    }
}
