package lotto.adaptor.implement;

import lotto.hexagon.domain.Money;

public class MoneyReader {
    static final String ERROR_NUMBER = "금액은 숫자만 입력할 수 있습니다";

    private final ApplicationInputLineReader lineReader;

    public MoneyReader(ApplicationInputLineReader reader) {
        this.lineReader = reader;
    }

    public Money read() throws IllegalArgumentException {
        String line = lineReader.readLine();
        long value = parse(line);
        return new Money(value);
    }

    private long parse(String string) {
        try {
            return Long.parseLong(string);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERROR_NUMBER);
        }
    }
}
