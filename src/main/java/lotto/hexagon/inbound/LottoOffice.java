package lotto.hexagon.inbound;

import lotto.hexagon.domain.Award;
import lotto.hexagon.domain.Bill;
import lotto.hexagon.domain.Drawn;
import lotto.hexagon.domain.Lottos;
import lotto.hexagon.domain.Money;

public interface LottoOffice {
    Bill purchase(Money money) throws IllegalArgumentException;

    Award determine(Drawn drawn, Lottos lottos);
}
