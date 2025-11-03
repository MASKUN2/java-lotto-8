package lotto.hexagon.domain;

import static lotto.helper.MockRandomGenerator.numbersOf;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import lotto.helper.MockRandomGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DrawnTest {

    @Test
    @DisplayName("불변식검증")
    void invariant() {

        Numbers numbers = MockRandomGenerator.numbersOf(1, 2, 3, 4, 5, 6);
        Number number = new Number(1);

        assertThatIllegalArgumentException().isThrownBy(() -> new Drawn(numbers, number))
                .withMessage(Drawn.ERROR_UNIQUE);
    }

    @Test
    void match() {
        Drawn drawn = new Drawn(numbersOf(1, 2, 3, 4, 5, 6), new Number(7));
        Numbers numbers = MockRandomGenerator.numbersOf(1, 2, 3, 4, 5, 7);

        Match match = drawn.match(numbers);

        assertThat(match.luckyCount()).isEqualTo(5);
        assertThat(match.bonusMatch()).isTrue();
    }

}
