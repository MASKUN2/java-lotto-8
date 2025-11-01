package lotto.adaptor.implement;

import java.util.stream.Collectors;
import lotto.hexagon.domain.Bill;
import lotto.hexagon.domain.Lotto;
import lotto.hexagon.domain.Lottos;
import lotto.hexagon.domain.Number;
import lotto.hexagon.domain.Numbers;

public class BillWriter {

    public void write(Bill bill) {
        Lottos lottos = bill.lottos();

        writeQuantity(lottos);

        for (Lotto lotto : lottos) {
            writeNumbers(lotto);
        }
    }

    private void writeQuantity(Lottos lottos) {
        System.out.printf("%n%d개를 구매했습니다.%n", lottos.getSize());
    }

    private void writeNumbers(Lotto lotto) {
        String formatted = toString(lotto.getNumbers());
        System.out.println(formatted);
    }

    private String toString(Numbers numbers) {
        String values = numbers.stream()
                .map(Number::value)
                .map(String::valueOf)
                .collect(Collectors.joining(", "));

        return String.format("[%s]", values);
    }
}
