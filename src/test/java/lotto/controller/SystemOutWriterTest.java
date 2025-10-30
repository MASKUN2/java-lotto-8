package lotto.controller;

import static org.assertj.core.api.Assertions.assertThat;

import lotto.helper.SystemIoTestHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SystemOutWriterTest {
    private final SystemOutWriter writer = new SystemOutWriter();

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
    void write() {
        writer.write("Some message");
        assertThat(SystemIoTestHelper.output())
                .isEqualTo("Some message\n");
    }
}
