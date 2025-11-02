package lotto.hexagon.domain;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MatchTest {
    @Test
    @DisplayName("불변식검증")
    void invariant() {
        assertThatIllegalArgumentException().isThrownBy(() -> Match.of(-1, false))
                .withMessage(Match.ERROR_RANGE);

        assertThatIllegalArgumentException().isThrownBy(() -> Match.of(Numbers.REQUIRED_SIZE + 1, false))
                .withMessage(Match.ERROR_RANGE);
    }

}
