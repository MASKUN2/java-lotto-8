package lotto.hexagon.domain;

import static lotto.helper.MockRandomGenerator.lottoOf;
import static lotto.helper.MockRandomGenerator.numbersOf;
import static org.assertj.core.api.Assertions.assertThat;

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

    @Test
    void check() {
        Drawn drawn = new Drawn(numbersOf(1, 2, 3, 4, 5, 6), new Number(7));
        Lotto lotto = lottoOf(1, 2, 3, 4, 5, 7);

        Match match = lotto.check(drawn);

        assertThat(match.luckyCount()).isEqualTo(5);
        assertThat(match.bonusMatch()).isTrue();
    }
}
