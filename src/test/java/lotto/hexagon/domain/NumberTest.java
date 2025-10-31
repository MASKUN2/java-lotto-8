package lotto.hexagon.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class NumberTest {

    @ParameterizedTest
    @ValueSource(ints = {0, 46})
    @DisplayName("불변식을 테스트 한다")
    void invariant(int value) {
        assertThatIllegalArgumentException().isThrownBy(() -> new Number(value))
                .withMessage(Number.ERROR_RANGE);
    }

    @Test
    @DisplayName("정렬을 테스트한다")
    void compare() {
        int result = new Number(1).compareTo(new Number(2));
        assertThat(result).isLessThan(0);

        result = new Number(2).compareTo(new Number(1));
        assertThat(result).isGreaterThan(0);

        result = new Number(1).compareTo(new Number(1));
        assertThat(result).isEqualTo(0);
    }

}
