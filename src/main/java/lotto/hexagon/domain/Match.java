package lotto.hexagon.domain;

public record Match(int luckyCount, boolean bonusMatch) {

    public static Match of(int luckyCount, boolean bonusMatch) {
        return new Match(luckyCount, bonusMatch);
    }

    public Match {
        if (luckyCount < 0 || luckyCount > Numbers.REQUIRED_SIZE) {
            throw new IllegalArgumentException("luckyMatch는 0에서 6 사이여야 합니다.");
        }
    }
}
