package lotto.adaptor.implement;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import lotto.helper.MockLineReader;
import lotto.hexagon.domain.Number;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class NumberReaderTest {

    private NumberReader reader;
    private MockLineReader mockLineReader;

    @BeforeEach
    void setUp() {
        mockLineReader = new MockLineReader();
        reader = new NumberReader(mockLineReader);
    }

    @Test
    @DisplayName("숫자리스트를 읽음")
    void read() {
        mockLineReader.setLine("1");

        Number number = reader.read();

        Number expected = new Number(1);
        assertThat(number).isEqualTo(expected);

    }

    @ParameterizedTest
    @ValueSource(strings = {"", "A", "?", " "})
    @DisplayName("잘못된 입력 오류")
    void readWrong(String value) {
        mockLineReader.setLine(value);

        assertThatIllegalArgumentException().isThrownBy(() -> reader.read());

    }
}
