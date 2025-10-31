package lotto.hexagon.application;

import static lotto.hexagon.domain.Lotto.PRICE;

import java.util.List;
import java.util.stream.LongStream;
import lotto.hexagon.domain.Bill;
import lotto.hexagon.domain.Divided;
import lotto.hexagon.domain.Lotto;
import lotto.hexagon.domain.Lottos;
import lotto.hexagon.domain.Money;
import lotto.hexagon.inbound.Vendor;

public class LottoVendor implements Vendor {
    static final String ERROR_REMAINDER = String.format("금액은 가격으로 나누어 떨어져야합니다. 가격: %s", PRICE);

    private final LottoFactory factory;

    public LottoVendor(LottoFactory factory) {
        this.factory = factory;
    }

    @Override
    public Bill purchase(Money money) {
        Divided divided = money.divideBy(PRICE);
        long quantity = divided.quotient();

        assertEmpty(divided.remainder());

        Lottos lottos = buyUpTo(quantity);
        return new Bill(money, lottos);
    }

    private void assertEmpty(Money change) {
        if (!change.isEmpty()) {
            throw new IllegalArgumentException(ERROR_REMAINDER);
        }
    }

    private Lottos buyUpTo(long quantity) {
        List<Lotto> bought = LongStream.range(0, quantity)
                .mapToObj(n -> factory.create())
                .toList();

        return new Lottos(bought);
    }
}
