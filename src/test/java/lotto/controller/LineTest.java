package lotto.controller;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LineTest {

    @Test
    @DisplayName("불변식")
    void invariant() {
        assertThatIllegalArgumentException().isThrownBy(() -> new Line(null))
                .withMessage(Line.ERROR_NULL);
    }

}
