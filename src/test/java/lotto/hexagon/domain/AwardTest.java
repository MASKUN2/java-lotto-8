package lotto.hexagon.domain;

import static lotto.hexagon.domain.Prize.RANK_1;
import static lotto.hexagon.domain.Prize.RANK_2;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class AwardTest {

    @Test
    void initiate() {
        Award award = Award.initiate();

        assertThat(award.total()).isEqualTo(Money.EMPTY);
    }

    @Test
    void add() {
        Award award = Award.initiate();
        Award updated = award.add(RANK_1);

        Money total = award.total();
        Money updatedTotal = updated.total();

        assertThat(total).isEqualTo(Money.EMPTY);
        assertThat(updatedTotal).isEqualTo(total.plus(RANK_1.money));
    }

    @Test
    void total() {
        Award award = Award.initiate();
        Award updated = award.add(RANK_1);
        updated = updated.add(RANK_2);

        assertThat(award.total()).isEqualTo(Money.EMPTY);
        assertThat(updated.total()).isEqualTo(RANK_1.money.plus(RANK_2.money));
    }

    @Test
    void iterator() {
        Award initiate = Award.initiate();

        for (PrizeTally prizeTally : initiate) {
            assertThat(prizeTally).isNotNull();
        }
    }
}
