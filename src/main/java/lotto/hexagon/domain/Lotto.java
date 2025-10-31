package lotto.hexagon.domain;

public class Lotto {
    public static final Money PRICE = new Money(1000);

    private final Numbers numbers;

    public Lotto(Numbers numbers) {
        this.numbers = numbers;
    }

    public Numbers getNumbers() {
        return numbers;
    }
}
