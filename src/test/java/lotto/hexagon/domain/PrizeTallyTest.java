package lotto.hexagon.domain;

import static lotto.hexagon.domain.Prize.RANK_1;
import static lotto.hexagon.domain.Prize.RANK_2;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PrizeTallyTest {

    @Test
    void newOf() {
        PrizeTally tally = PrizeTally.newOf(RANK_1);

        assertThat(tally.prize()).isEqualTo(RANK_1);
        assertThat(tally.count()).isEqualTo(0);
    }

    @Test
    @DisplayName("불변식 검증")
    void invariant() {
        assertThatIllegalArgumentException().isThrownBy(() -> new PrizeTally(RANK_1, -1))
                .withMessage(PrizeTally.ERROR_NEGATIVE);
    }

    @Test
    void increase() {
        PrizeTally tally = PrizeTally.newOf(RANK_1);
        PrizeTally updated = tally.increase();

        assertThat(updated.count()).isEqualTo(1);
    }

    @Test
    void isMatch() {
        PrizeTally tally = PrizeTally.newOf(RANK_1);

        assertThat(tally.isMatch(RANK_1)).isTrue();
        assertThat(tally.isMatch(RANK_2)).isFalse();
    }

    @Test
    void increaseIfMatch() {
        PrizeTally tally = PrizeTally.newOf(RANK_1);
        PrizeTally updated = tally.increaseIfMatch(RANK_1);

        assertThat(updated.count()).isEqualTo(1);
        assertThat(updated.isMatch(RANK_1)).isTrue();
        assertThat(updated.isMatch(RANK_2)).isFalse();
    }

    @Test
    void total() {
        PrizeTally tally = PrizeTally.newOf(RANK_1);
        PrizeTally updated = tally.increase();
        updated = updated.increase();

        assertThat(updated.total()).isEqualTo(RANK_1.money.multiple(2));
    }
}
