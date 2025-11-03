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
        Match match = drawn.match(numbers);
        return Prize.findBy(match);
    }
}
