package lotto.controller;

import lotto.hexagon.domain.Number;

public class NumberReader implements InputReader<Number> {
    static final String ERROR_NUMBER = "숫자만 입력 할 수 있습니다";

    private final LineReader lineReader;

    public NumberReader(LineReader reader) {
        this.lineReader = reader;
    }

    @Override
    public Number read() {
        Line line = lineReader.read();
        Integer number = parse(line.value());
        return new Number(number);
    }

    private Integer parse(String value) {
        try {
            return Integer.parseInt(value.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERROR_NUMBER);
        }
    }
}
