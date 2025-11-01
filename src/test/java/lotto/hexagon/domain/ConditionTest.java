package lotto.hexagon.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.junit.jupiter.api.Test;

class ConditionTest {

    @Test
    void of() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> Condition.of(Numbers.REQUIRED_SIZE + 1, false))
                .withMessage(Condition.ERROR_RANGE);
    }

    @Test
    void isMatch() {
        Condition countOnly = Condition.of(3, false);
        Condition bonusRequired = Condition.of(3, true);

        Match match = Match.of(4, false);

        assertThat(countOnly.isMatch(match)).isTrue();
        assertThat(bonusRequired.isMatch(match)).isFalse();
    }
}
