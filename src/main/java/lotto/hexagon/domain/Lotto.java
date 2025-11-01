package lotto.hexagon.domain;

import java.util.Optional;

public class Lotto {
    public static final Money PRICE = new Money(1000);

    private final Numbers numbers;

    public Lotto(Numbers numbers) {
        this.numbers = numbers;
    }

    public Numbers getNumbers() {
        return numbers;
    }

    public Optional<Prize> evaluate(Drawn drawn) {
        Match match = check(drawn);
        return Prize.findBy(match);
    }

    public Match check(Drawn drawn) {
        Numbers lucky = drawn.lucky();
        Number bonus = drawn.bonus();

        int luckyCount = lucky.getMatchCount(numbers);
        boolean bonusMatch = numbers.has(bonus);

        return Match.of(luckyCount, bonusMatch);
    }
}
