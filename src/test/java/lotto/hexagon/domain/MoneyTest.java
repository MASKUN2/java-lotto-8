package lotto.hexagon.domain;

import static org.assertj.core.api.Assertions.assertThat;
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

    @Test
    @DisplayName("나눗셈을 테스트 한다")
    void division() {
        Money money = new Money(100);
        Money other = new Money(9);

        Divided divided = money.divideBy(other);

        assertThat(divided.quotient()).isEqualTo(11);
        assertThat(divided.remainder()).isEqualTo(new Money(1));
    }

    @Test
    @DisplayName("0에 관련된 나눗셈을 테스트 한다")
    void dividendZero() {
        Money money = new Money(0);
        Money other = new Money(1);

        Divided divided = money.divideBy(other);

        assertThat(divided.quotient()).isEqualTo(0);
        assertThat(divided.remainder()).isEqualTo(new Money(0));
    }

    @Test
    @DisplayName("0에 관련된 나눗셈을 테스트 한다")
    void divisorZero() {
        Money money = new Money(1);
        Money other = new Money(0);

        assertThatIllegalArgumentException().isThrownBy(() -> money.divideBy(other));
    }

    @Test
    @DisplayName("비어있는지 테스트")
    void isEmpty() {
        Money money = new Money(0);

        assertThat(money.isEmpty()).isTrue();
    }
}
