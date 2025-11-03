package lotto.hexagon.domain;

public record Number(int value) implements Comparable<Number> {
    public static final int MIN_VALUE = 1;
    public static final int MAX_VALUE = 45;
    static final String ERROR_RANGE = String.format("로또 번호는 %d 부터 %d 사이의 숫자여야 합니다.", MIN_VALUE, MAX_VALUE);

    public Number {
        assertInRange(value);
    }

    private void assertInRange(int value) {
        if (value < MIN_VALUE || value > MAX_VALUE) {
            throw new IllegalArgumentException(ERROR_RANGE);
        }
    }

    @Override
    public int compareTo(Number o) {
        return Integer.compare(this.value, o.value);
    }
}
