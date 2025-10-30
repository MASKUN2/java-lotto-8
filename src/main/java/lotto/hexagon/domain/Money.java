package lotto.hexagon.domain;

public record Money(long value) {
    static final String ERROR_NEGATIVE = "금액은 음수일 수 없습니다";

    public Money {
        if (value < 0) {
            throw new IllegalArgumentException(ERROR_NEGATIVE);
        }
    }
}
