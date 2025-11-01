package lotto.hexagon.application;

import static lotto.helper.MockRandomGenerator.LottosBuilder;
import static lotto.helper.MockRandomGenerator.lottoOf;
import static lotto.helper.MockRandomGenerator.numbersOf;
import static lotto.hexagon.domain.Prize.RANK_1;
import static lotto.hexagon.domain.Prize.RANK_2;
import static lotto.hexagon.domain.Prize.RANK_3;
import static lotto.hexagon.domain.Prize.RANK_4;
import static lotto.hexagon.domain.Prize.RANK_5;
import static org.junit.jupiter.api.Assertions.assertEquals;

import lotto.hexagon.domain.Award;
import lotto.hexagon.domain.Drawn;
import lotto.hexagon.domain.Lottos;
import lotto.hexagon.domain.Money;
import lotto.hexagon.domain.Number;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RewardServiceTest {
    private Drawn drawn;
    private Lottos lottos;
    private RewardService service = new RewardService();

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void determine() {
        drawn = new Drawn(numbersOf(1, 2, 3, 4, 5, 6), new Number(7));

        LottosBuilder builder = new LottosBuilder();
        builder.add(lottoOf(1, 2, 3, 4, 5, 6));
        builder.add(lottoOf(1, 2, 3, 4, 5, 7));
        builder.add(lottoOf(1, 2, 3, 4, 5, 45));
        builder.add(lottoOf(1, 2, 3, 4, 44, 45));
        builder.add(lottoOf(1, 2, 3, 43, 44, 45));
        builder.add(lottoOf(1, 2, 3, 43, 44, 45));
        builder.add(lottoOf(1, 2, 42, 43, 44, 45));
        builder.add(lottoOf(1, 41, 42, 43, 44, 45));
        builder.add(lottoOf(40, 41, 42, 43, 44, 45));

        lottos = builder.build();

        Award award = service.determine(drawn, lottos);

        Money expected = RANK_1.money
                .plus(RANK_2.money)
                .plus(RANK_3.money)
                .plus(RANK_4.money)
                .plus(RANK_5.money.multiple(2));

        assertEquals(expected, award.total());

    }
}
