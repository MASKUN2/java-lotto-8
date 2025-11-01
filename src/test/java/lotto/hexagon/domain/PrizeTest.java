package lotto.hexagon.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;
import org.junit.jupiter.api.Test;

class PrizeTest {

    @Test
    void findBy() {
        Match match = new Match(5, true);
        Optional<Prize> prize = Prize.findBy(match);

        assertThat(prize).isNotEmpty();
        assertThat(prize.get()).isEqualTo(Prize.RANK_2);
    }
}
