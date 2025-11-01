package lotto.hexagon.inbound;

import lotto.hexagon.domain.Award;
import lotto.hexagon.domain.Drawn;
import lotto.hexagon.domain.Lottos;

public interface AwardOffice {
    Award determine(Drawn drawn, Lottos lottos);
}
