package lotto.adaptor.implement;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import lotto.helper.MockLineReader;
import lotto.hexagon.domain.Money;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class MoneyReaderTest {
    private final MockLineReader mockLineReader = new MockLineReader();
    private final MoneyReader moneyReader = new MoneyReader(mockLineReader);

    @Test
    @DisplayName("금액을 읽음")
    void read() {
        mockLineReader.setLine("10000");

        Money money = moneyReader.read();

        assertThat(money).isEqualTo(new Money(10000));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "?", "1000원", "1000 "})
    @DisplayName("잘못된 금액 입력 오류")
    void failRead(String value) {
        mockLineReader.setLine(value);

        assertThatIllegalArgumentException().isThrownBy(moneyReader::read)
                .withMessage(MoneyReader.ERROR_NUMBER);
    }

}
