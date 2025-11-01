package lotto.adaptor.implement;

import java.util.stream.Collectors;
import lotto.hexagon.domain.Lotto;
import lotto.hexagon.domain.Lottos;
import lotto.hexagon.domain.Number;
import lotto.hexagon.domain.Numbers;

public class LottosWriter {
    private static final String QUANTITY_FORMAT = "\n%d개를 구매했습니다.";
    private static final String NUMBER_DELIMITER = ", ";
    private static final String NUMBERS_FORMAT = "[%s]";

    private final ApplicationOutputLineWriter writer;

    public LottosWriter(ApplicationOutputLineWriter writer) {
        this.writer = writer;
    }

    public void write(Lottos lottos) {
        writeQuantity(lottos);

        for (Lotto lotto : lottos) {
            writeNumbers(lotto);
        }
        writerLineBreak();
    }

    private void writeQuantity(Lottos lottos) {
        int quantity = lottos.getSize();
        String formatted = String.format(QUANTITY_FORMAT, quantity);
        writer.writeLine(formatted);
    }

    private void writeNumbers(Lotto lotto) {
        Numbers numbers = lotto.getNumbers();
        String formatted = toString(numbers);
        writer.writeLine(formatted);
    }

    private String toString(Numbers numbers) {
        String values = numbers.stream()
                .map(Number::value)
                .map(String::valueOf)
                .collect(Collectors.joining(NUMBER_DELIMITER));

        return String.format(NUMBERS_FORMAT, values);
    }

    private void writerLineBreak() {
        writer.writeLine("");
    }
}
