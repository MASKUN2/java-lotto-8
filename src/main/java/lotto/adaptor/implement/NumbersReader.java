package lotto.adaptor.implement;

import java.util.List;
import lotto.hexagon.domain.Number;
import lotto.hexagon.domain.Numbers;

public class NumbersReader {
    static final String ERROR_NUMBER = "숫자만 입력 할 수 있습니다";
    static final String DELIMITER = ",";

    private final ApplicationInputLineReader lineReader;

    public NumbersReader(ApplicationInputLineReader reader) {
        this.lineReader = reader;
    }

    public Numbers read() throws IllegalArgumentException {
        List<String> delimited = readDelimited();
        List<Number> numbers = parse(delimited);
        return Numbers.of(numbers);
    }

    private List<String> readDelimited() {
        String line = lineReader.readLine();
        String[] split = line.split(DELIMITER);
        return List.of(split);
    }

    private List<Number> parse(List<String> delimited) {
        return delimited.stream()
                .map(this::parse)
                .map(Number::new)
                .toList();
    }

    private Integer parse(String value) {
        try {
            return Integer.parseInt(value.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERROR_NUMBER);
        }
    }
}
