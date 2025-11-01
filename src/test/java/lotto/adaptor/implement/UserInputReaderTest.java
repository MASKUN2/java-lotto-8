package lotto.adaptor.implement;

import static lotto.helper.MockRandomGenerator.numbersOf;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import lotto.helper.MockLineReader;
import lotto.hexagon.domain.Money;
import lotto.hexagon.domain.Number;
import lotto.hexagon.domain.Numbers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class UserInputReaderTest {
    private UserInputReader reader;
    private MockLineReader mockLineReader;

    @BeforeEach
    void setUp() {
        mockLineReader = new MockLineReader();
        reader = new UserInputReader(mockLineReader);
    }

    @Test
    @DisplayName("숫자리스트를 읽음")
    void readNumbers() {
        mockLineReader.setLine("1,2,3,4,5,6");

        Numbers numbers = reader.readNumbers();

        Numbers expected = numbersOf(1, 2, 3, 4, 5, 6);
        assertThat(numbers).isEqualTo(expected);

    }

    @ParameterizedTest
    @ValueSource(strings = {"", "1,2,3,4,5", "1,2,3,4,A,B", "1,2,3,4,5,-6"})
    @DisplayName("잘못된 입력 오류")
    void readNumberNumbersWrong(String value) {
        mockLineReader.setLine(value);

        assertThatIllegalArgumentException().isThrownBy(() -> reader.readNumbers());

    }

    @Test
    @DisplayName("숫자를 읽음")
    void readNumber() {
        mockLineReader.setLine("1");

        lotto.hexagon.domain.Number number = reader.readNumber();

        lotto.hexagon.domain.Number expected = new Number(1);
        assertThat(number).isEqualTo(expected);

    }

    @ParameterizedTest
    @ValueSource(strings = {"", "A", "?", " "})
    @DisplayName("잘못된 입력 오류")
    void readWrong(String value) {
        mockLineReader.setLine(value);

        assertThatIllegalArgumentException().isThrownBy(() -> reader.readNumber());

    }

    @Test
    @DisplayName("금액을 읽음")
    void read() {
        mockLineReader.setLine("10000");

        Money money = reader.readMoney();

        assertThat(money).isEqualTo(new Money(10000));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "?", "1000원"})
    @DisplayName("잘못된 금액 입력 오류")
    void failRead(String value) {
        mockLineReader.setLine(value);

        assertThatIllegalArgumentException().isThrownBy(reader::readMoney)
                .withMessage(UserInputReader.ERROR_NUMBER);
    }
}
