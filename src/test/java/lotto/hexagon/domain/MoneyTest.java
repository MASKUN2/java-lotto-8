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
    @DisplayName("0을 나누면 0")
    void dividendZero() {
        Money money = new Money(0);
        Money other = new Money(1);

        Divided divided = money.divideBy(other);

        assertThat(divided.quotient()).isEqualTo(0);
        assertThat(divided.remainder()).isEqualTo(new Money(0));
    }

    @Test
    @DisplayName("0으로 나누면 오류")
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

    @Test
    @DisplayName("더하기")
    void plus() {
        Money money = new Money(100);
        Money other = new Money(10);

        Money updated = money.plus(other);

        assertThat(updated).isEqualTo(new Money(110));
    }

    @Test
    @DisplayName("곱하기")
    void multiple() {
        Money money = new Money(100);
        int multiple = 10;
        Money updated = money.multiple(multiple);

        assertThat(updated).isEqualTo(new Money(1000));
    }
}
