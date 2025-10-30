package lotto.controller;

import lotto.hexagon.domain.Money;

public class MoneyReader implements InputReader<Money> {
    static final String ERROR_NUMBER = "입력 줄이 숫자가 아닙니다";

    private final LineReader lineReader;

    public MoneyReader(LineReader reader) {
        this.lineReader = reader;
    }

    @Override
    public Money read() {
        Line line = lineReader.read();
        long amount = parse(line.value());
        return new Money(amount);
    }

    private long parse(String value) {
        try {
            return Long.parseLong(value);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERROR_NUMBER);
        }
    }
}
