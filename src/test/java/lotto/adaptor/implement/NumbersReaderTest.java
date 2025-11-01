package lotto.adaptor.implement;

import static lotto.helper.MockRandomGenerator.numbersOf;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import lotto.helper.MockLineReader;
import lotto.hexagon.domain.Numbers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class NumbersReaderTest {
    private NumbersReader reader;
    private MockLineReader mockLineReader;

    @BeforeEach
    void setUp() {
        mockLineReader = new MockLineReader();
        reader = new NumbersReader(mockLineReader);
    }

    @Test
    @DisplayName("숫자리스트를 읽음")
    void read() {
        mockLineReader.setLine("1,2,3,4,5,6");

        Numbers numbers = reader.read();

        Numbers expected = numbersOf(1, 2, 3, 4, 5, 6);
        assertThat(numbers).isEqualTo(expected);

    }

    @ParameterizedTest
    @ValueSource(strings = {"", "1,2,3,4,5", "1,2,3,4,A,B", "1,2,3,4,5,-6"})
    @DisplayName("잘못된 입력 오류")
    void readWrong(String value) {
        mockLineReader.setLine(value);

        assertThatIllegalArgumentException().isThrownBy(() -> reader.read());

    }
}
