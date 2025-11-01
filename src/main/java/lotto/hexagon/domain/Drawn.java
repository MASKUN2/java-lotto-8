package lotto.hexagon.domain;

public record Drawn(Numbers lucky, Number bonus) {

    public Match check(Lotto lotto) {
        Numbers numbers = lotto.getNumbers();
        int luckyCount = lucky.getMatchCount(numbers);
        boolean bonusMatch = numbers.has(bonus);

        return Match.of(luckyCount, bonusMatch);
    }
}
