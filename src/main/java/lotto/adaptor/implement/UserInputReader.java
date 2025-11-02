package lotto.adaptor.implement;

import java.util.List;
import lotto.adaptor.InputReader;
import lotto.hexagon.domain.Money;
import lotto.hexagon.domain.Number;
import lotto.hexagon.domain.Numbers;

public class UserInputReader implements InputReader {
    private static final String DELIMITER = ",";
    static final String ERROR_NUMBER = "숫자만 입력 할 수 있습니다";

    private final ApplicationInputLineReader lineReader;

    public UserInputReader(ApplicationInputLineReader reader) {
        this.lineReader = reader;
    }

    @Override
    public Money readMoney() throws IllegalArgumentException {
        String line = lineReader.readLine();
        long value = parse(line);
        return new Money(value);
    }

    @Override
    public Numbers readNumbers() throws IllegalArgumentException {
        List<String> delimited = readDelimited();
        List<Number> numbers = parse(delimited);
        return Numbers.of(numbers);
    }

    @Override
    public Number readNumber() throws IllegalArgumentException {
        String line = lineReader.readLine();
        Integer value = parse(line);
        return new Number(value);
    }

    private Integer parse(String value) {
        try {
            return Integer.parseInt(value.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERROR_NUMBER);
        }
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
}
