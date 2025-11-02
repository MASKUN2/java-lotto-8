package lotto.hexagon.application;

import static lotto.hexagon.domain.Lotto.PRICE;

import java.util.List;
import java.util.stream.LongStream;
import lotto.hexagon.domain.Bill;
import lotto.hexagon.domain.Divided;
import lotto.hexagon.domain.Lotto;
import lotto.hexagon.domain.Lottos;
import lotto.hexagon.domain.Money;
import lotto.hexagon.domain.Numbers;
import lotto.hexagon.domain.NumbersGenerator;

public class VendorService {
    static final String ERROR_REMAINDER = String.format("금액은 가격으로 나누어 떨어져야합니다. 가격: %,d", PRICE.value());
    static final String ERROR_NO_QUANTITY = "구입수량은 1 이상이여야 합니다.";

    private final NumbersGenerator numbersGenerator;

    public VendorService(NumbersGenerator numbersGenerator) {
        this.numbersGenerator = numbersGenerator;
    }

    public Bill purchase(Money money) {
        Divided divided = money.divideBy(PRICE);
        assertEmptyRemainder(divided);

        long quantity = divided.quotient();
        assertPositiveQuantity(quantity);

        Lottos lottos = buyUpTo(quantity);
        return new Bill(money, lottos);
    }

    private void assertEmptyRemainder(Divided divided) {
        Money remainder = divided.remainder();

        if (!remainder.isEmpty()) {
            throw new IllegalArgumentException(ERROR_REMAINDER);
        }
    }

    private void assertPositiveQuantity(long quantity) {
        if (quantity < 1) {
            throw new IllegalArgumentException(ERROR_NO_QUANTITY);
        }
    }

    private Lottos buyUpTo(long quantity) {
        List<Lotto> bought = LongStream.range(0, quantity)
                .mapToObj(n -> this.issue())
                .toList();

        return new Lottos(bought);
    }

    private Lotto issue() {
        Numbers numbers = numbersGenerator.generate();
        return new Lotto(numbers);
    }
}
