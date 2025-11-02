package lotto.adaptor.implement;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;
import lotto.adaptor.ExceededRetryException;
import lotto.helper.MockLineWriter;
import lotto.hexagon.domain.Number;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RetryableExceptionHandlerImplTest {
    private final MockLineWriter mockLineWriter = new MockLineWriter();
    private final RetryableExceptionHandlerImpl handler = new RetryableExceptionHandlerImpl(mockLineWriter);

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
        String output = mockLineWriter.output();
        String expected = """
                [ERROR] For input string: "A"
                [ERROR] For input string: "?"
                [ERROR] For input string: "1000A"
                """;
        assertThat(result).isEqualTo(100);
        assertThat(output).isEqualTo(expected);
    }

    @Test
    @DisplayName("재시도 횟수 초과")
    void overException() {

        try {
            handler.handle(() -> new Number(-1));
        } catch (ExceededRetryException exception) {
            mockLineWriter.writeLine(exception.getMessage());
        }

        String output = mockLineWriter.output();
        String expected = """
                [ERROR] 로또 번호는 1 부터 45 사이의 숫자여야 합니다.
                [ERROR] 로또 번호는 1 부터 45 사이의 숫자여야 합니다.
                [ERROR] 로또 번호는 1 부터 45 사이의 숫자여야 합니다.
                [ERROR] 로또 번호는 1 부터 45 사이의 숫자여야 합니다.
                [ERROR] 로또 번호는 1 부터 45 사이의 숫자여야 합니다.
                [ERROR] 로또 번호는 1 부터 45 사이의 숫자여야 합니다.
                [ERROR] 로또 번호는 1 부터 45 사이의 숫자여야 합니다.
                [ERROR] 로또 번호는 1 부터 45 사이의 숫자여야 합니다.
                [ERROR] 로또 번호는 1 부터 45 사이의 숫자여야 합니다.
                [ERROR] 로또 번호는 1 부터 45 사이의 숫자여야 합니다.
                [ERROR] 재시도 횟수가 초과하였습니다
                """;
        assertThat(output).isEqualTo(expected);
    }
}
