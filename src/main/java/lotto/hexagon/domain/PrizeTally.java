package lotto.hexagon.domain;

public record PrizeTally(Prize prize, int count) {
    private static final int ZERO = 0;
    static final String ERROR_NEGATIVE = "개수는 음수일 수 없습니다";

    public static PrizeTally newOf(Prize prize) {
        return new PrizeTally(prize, ZERO);
    }

    public PrizeTally {
        assertPositive(count);
    }

    private static void assertPositive(int count) {
        if (count < 0) {
            throw new IllegalArgumentException(ERROR_NEGATIVE);
        }
    }

    public PrizeTally increase() {
        return new PrizeTally(prize, this.count + 1);
    }

    public PrizeTally increaseIfMatch(Prize prize) {
        if (isMatch(prize)) {
            return increase();
        }
        return this;
    }

    public boolean isMatch(Prize prize) {
        return this.prize.equals(prize);
    }

    public Money total() {
        Money money = prize.money;
        return money.multiple(count);
    }
}
