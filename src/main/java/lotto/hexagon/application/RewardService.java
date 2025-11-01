package lotto.hexagon.application;

import java.util.List;
import java.util.Optional;
import lotto.hexagon.domain.Award;
import lotto.hexagon.domain.Drawn;
import lotto.hexagon.domain.Lottos;
import lotto.hexagon.domain.Match;
import lotto.hexagon.domain.Prize;

public class RewardService {

    public Award determine(Drawn drawn, Lottos lottos) {
        List<Match> matches = getMatches(drawn, lottos);

        List<Prize> prizes = findPrize(matches);

        return getAward(prizes);
    }

    private List<Match> getMatches(Drawn drawn, Lottos lottos) {
        return lottos.stream()
                .map(drawn::check)
                .toList();
    }

    private List<Prize> findPrize(List<Match> matches) {
        return matches.stream()
                .map(Prize::findBy)
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
