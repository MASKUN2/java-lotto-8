package lotto.controller;

import java.util.List;
import lotto.hexagon.domain.Number;
import lotto.hexagon.domain.Numbers;

public class NumbersReader implements InputReader<Numbers> {
    static final String ERROR_NUMBER = "숫자만 입력 할 수 있습니다";

    private final LineReader lineReader;
    private static final String DELIMITER = ",";

    public NumbersReader(LineReader reader) {
        this.lineReader = reader;
    }

    @Override
    public Numbers read() {
        List<String> delimited = readDelimited();

        List<Number> numbers = delimited.stream()
                .map(this::parse)
                .map(Number::new)
                .toList();

        return Numbers.of(numbers);
    }

    private Integer parse(String value) {
        try {
            return Integer.parseInt(value.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERROR_NUMBER);
        }
    }

    private List<String> readDelimited() {
        Line line = lineReader.read();
        String value = line.value();
        String[] split = value.split(DELIMITER);
        return List.of(split);
    }
}
