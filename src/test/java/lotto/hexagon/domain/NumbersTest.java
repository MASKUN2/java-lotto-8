package lotto.hexagon.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NumbersTest {

    @Test
    @DisplayName("초기화: 오름차순 정렬")
    void initialization() {
        List<Number> list = numbers(6, 5, 4, 3, 2, 1);

        Numbers numbers = Numbers.of(list);

        List<Number> expected = numbers(1, 2, 3, 4, 5, 6);
        assertThat(numbers).containsExactly(expected.toArray(Number[]::new));
    }

    @Test
    @DisplayName("불변식을 테스트한다: 중복")
    void invariantDuplicate() {
        List<Number> list = numbers(1, 2, 3, 4, 5, 5);

        assertThatIllegalArgumentException().isThrownBy(() -> Numbers.of(list))
                .withMessage(Numbers.ERROR_UNIQUE);

    }

    @Test
    @DisplayName("불변식을 테스트한다: 개수")
    void invariantAmount() {
        List<Number> list = numbers(1, 2, 3, 4, 5);

        assertThatIllegalArgumentException().isThrownBy(() -> Numbers.of(list))
                .withMessage(Numbers.ERROR_SIZE);
    }

    private static List<Number> numbers(int... ints) {
        return Arrays.stream(ints)
                .mapToObj(Number::new)
                .toList();
    }

}
