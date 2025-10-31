package lotto.hexagon.domain;

public record Money(long value) {
    static final String ERROR_NEGATIVE = "금액은 음수일 수 없습니다";

    public Money {
        if (value < 0) {
            throw new IllegalArgumentException(ERROR_NEGATIVE);
        }
    }

    public Divided divideBy(Money money) {
        long dividend = this.value;
        long divisor = money.value();

        long quotient = dividend / divisor;
        long remainder = dividend % divisor;

        return new Divided(quotient, new Money(remainder));
    }

    public boolean isEmpty() {
        return this.value == 0;
    }
}
