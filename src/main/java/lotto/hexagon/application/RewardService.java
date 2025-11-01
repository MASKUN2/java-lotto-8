package lotto.hexagon.application;

import java.util.List;
import java.util.Optional;
import lotto.hexagon.domain.Award;
import lotto.hexagon.domain.Drawn;
import lotto.hexagon.domain.Lottos;
import lotto.hexagon.domain.Prize;

public class RewardService {

    public Award determine(Drawn drawn, Lottos lottos) {
        List<Prize> prizes = getEvaluatedPrizes(drawn, lottos);
        return getAward(prizes);
    }

    private List<Prize> getEvaluatedPrizes(Drawn drawn, Lottos lottos) {
        return lottos.stream()
                .map(lotto -> lotto.evaluate(drawn))
                .flatMap(Optional::stream)
                .toList();
    }

    private Award getAward(List<Prize> prizes) {
        Award award = Award.initiate();

        for (Prize prize : prizes) {
            award = award.add(prize);
        }

        return award;
    }
}
