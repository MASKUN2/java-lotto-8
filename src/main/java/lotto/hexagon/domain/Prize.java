package lotto.hexagon.domain;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public enum Prize {
    RANK_5(5, Condition.of(3, false), Money.of(5_000)),
    RANK_4(4, Condition.of(4, false), Money.of(50_000)),
    RANK_3(3, Condition.of(5, false), Money.of(1_500_000)),
    RANK_2(2, Condition.of(5, true), Money.of(30_000_000)),
    RANK_1(1, Condition.of(6, false), Money.of(2_000_000_000)),

    ;

    Prize(int rank, Condition condition, Money money) {
        this.rank = rank;
        this.condition = condition;
        this.money = money;
    }

    public final int rank;
    public final Condition condition;
    public final Money money;

    static {
        assertRankUnique();
        assertMatchUnique();
    }

    public static Optional<Prize> findBy(Match match) {
        return Arrays.stream(Prize.values())
                .filter(prize -> prize.condition.isMatch(match))
                .min(Comparator.comparing(prize -> prize.rank));
    }

    private static void assertRankUnique() {
        Set<Integer> ranks = new HashSet<>();
        boolean isDuplicated = Arrays.stream(Prize.values())
                .anyMatch(prize -> !ranks.add(prize.rank));

        if (isDuplicated) {
            throw new IllegalStateException("랭크가 중복되었습니다.");
        }
    }

    private static void assertMatchUnique() {
        Set<Condition> matches = new HashSet<>();
        boolean isDuplicated = Arrays.stream(Prize.values())
                .anyMatch(prize -> !matches.add(prize.condition));

        if (isDuplicated) {
            throw new IllegalStateException("일치 조건이 중복되었습니다");
        }
    }

}
