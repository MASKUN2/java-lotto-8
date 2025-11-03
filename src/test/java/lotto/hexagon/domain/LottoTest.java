package lotto.hexagon.domain;

import static lotto.helper.MockRandomGenerator.numbersOf;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;
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
    @DisplayName("로또 당첨확인")
    void evaluate() {
        Drawn drawn = new Drawn(numbersOf(1, 2, 3, 4, 5, 6), new Number(7));
        Lotto lotto = MockRandomGenerator.lottoOf(1, 2, 3, 4, 5, 7);

        Optional<Prize> prize = lotto.evaluate(drawn);

        assertThat(prize).isNotEmpty();
        assertThat(prize.get()).isEqualTo(Prize.RANK_2);
    }
}
