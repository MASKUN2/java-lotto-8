package lotto.hexagon.inbound;

import lotto.hexagon.domain.Bill;
import lotto.hexagon.domain.Money;

public interface Vendor {
    Bill purchase(Money money);
}
