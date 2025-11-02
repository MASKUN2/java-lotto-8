package lotto.hexagon.domain;

public record Money(long value) {
    static final String ERROR_NEGATIVE = "금액은 음수일 수 없습니다";
    static final String ERROR_DIVISOR_ZERO = "나누는 금액은 0일 수 없습니다";

    public static final Money EMPTY = new Money(0);

    public Money {
        if (value < 0) {
            throw new IllegalArgumentException(ERROR_NEGATIVE);
        }
    }

    public static Money of(long value) {
        return new Money(value);
    }

    public Divided divideBy(Money money) {
        assertDivisorNotZero(money);

        long dividend = this.value;
        long divisor = money.value();
        long quotient = dividend / divisor;
        long remainder = dividend % divisor;

        return new Divided(quotient, new Money(remainder));
    }

    private void assertDivisorNotZero(Money money) {
        if (Money.EMPTY.equals(money)) {
            throw new IllegalArgumentException(ERROR_DIVISOR_ZERO);
        }
    }

    public boolean isEmpty() {
        return this.equals(EMPTY);
    }

    public Money multiple(int multiple) {
        return new Money(this.value * multiple);
    }

    public Money plus(Money money) {
        return new Money(this.value + money.value);
    }
}
