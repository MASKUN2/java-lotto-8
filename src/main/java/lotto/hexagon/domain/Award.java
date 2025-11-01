package lotto.hexagon.domain;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Award implements Iterable<PrizeTally> {
    private final List<PrizeTally> prizeTallies;

    private Award(List<PrizeTally> prizeTallies) {
        this.prizeTallies = List.copyOf(prizeTallies);
    }

    public static Award initiate() {
        List<PrizeTally> prizeTallyList = Arrays.stream(Prize.values())
                .map(PrizeTally::newOf)
                .toList();

        return new Award(prizeTallyList);
    }

    public Award add(Prize prize) {
        List<PrizeTally> updated = prizeTallies.stream()
                .map(tally -> tally.increaseIfMatch(prize))
                .toList();

        return new Award(updated);
    }

    public Money total() {
        return prizeTallies.stream()
                .map(PrizeTally::total)
                .reduce(Money.EMPTY, Money::plus);
    }

    @Override
    public Iterator<PrizeTally> iterator() {
        return prizeTallies.iterator();
    }
}
