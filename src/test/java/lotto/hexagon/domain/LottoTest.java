package lotto.hexagon.domain;

import lotto.helper.MockRandomGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoTest {

    @Test
    @DisplayName("생성테스트")
    void creation() {
        Numbers numbers = MockRandomGenerator.numbersOf(1, 2, 3, 4, 5, 6);
        new Lotto(numbers);
    }
}
