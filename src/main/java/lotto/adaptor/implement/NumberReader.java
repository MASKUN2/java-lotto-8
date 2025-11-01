package lotto.adaptor.implement;

import lotto.hexagon.domain.Number;

public class NumberReader {
    static final String ERROR_NUMBER = "숫자만 입력할 수 있습니다";

    private final ApplicationInputLineReader lineReader;

    public NumberReader(ApplicationInputLineReader reader) {
        this.lineReader = reader;
    }

    public Number read() throws IllegalArgumentException {
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
}
