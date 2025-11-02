package lotto.adaptor.implement;

import static org.assertj.core.api.Assertions.assertThat;

import lotto.helper.SystemIoTestHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SystemOutputLineWriterTest {
    private final SystemOutputLineWriter writer = new SystemOutputLineWriter();

    @BeforeEach
    void setUp() {
        SystemIoTestHelper.startRecord();
    }

    @AfterEach
    void tearDown() {
        SystemIoTestHelper.restore();
    }

    @Test
    @DisplayName("출력이 한줄씩 되는지 확인")
    void writeLine() {
        writer.writeLine("Some message");
        assertThat(SystemIoTestHelper.output())
                .isEqualTo("Some message\n");
    }
}
