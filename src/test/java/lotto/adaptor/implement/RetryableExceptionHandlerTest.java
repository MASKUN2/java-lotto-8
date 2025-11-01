package lotto.adaptor.implement;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RetryableExceptionHandlerTest {
    private final RetryableExceptionHandler handler = new RetryableExceptionHandler();

    @Test
    @DisplayName("정상반환")
    void handle() {
        int result = handler.handle(() -> Integer.valueOf("100"));
        assertThat(result).isEqualTo(100);
    }

    @Test
    @DisplayName("예외 발생후엔 정상일때까지 반복하고 반환")
    void handleException() {
        Queue<String> inputs = new ArrayDeque<>(List.of("A", "?", "1000A", "100"));

        Integer result = handler.handle(() -> Integer.valueOf(inputs.poll()));

        assertThat(result).isEqualTo(100);
    }
}
