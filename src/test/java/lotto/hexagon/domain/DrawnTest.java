package lotto.hexagon.domain;

import static lotto.helper.MockRandomGenerator.lottoOf;
import static lotto.helper.MockRandomGenerator.numbersOf;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class DrawnTest {

    @Test
    void check() {
        Drawn drawn = new Drawn(numbersOf(1, 2, 3, 4, 5, 6), new Number(7));
        Lotto lotto = lottoOf(1, 2, 3, 4, 5, 7);

        Match match = drawn.check(lotto);

        assertThat(match.luckyCount()).isEqualTo(5);
        assertThat(match.bonusMatch()).isTrue();
    }
}
