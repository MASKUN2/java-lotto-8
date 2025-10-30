package lotto.hexagon.domain;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MoneyTest {

    @Test
    @DisplayName("불변식")
    void invariant() {
        assertThatIllegalArgumentException().isThrownBy(() -> new Money(-1))
                .withMessage(Money.ERROR_NEGATIVE);
    }
}
