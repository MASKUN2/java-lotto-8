package lotto.hexagon.domain;

public record Match(int luckyCount, boolean bonusMatch) {
    static final String ERROR_RANGE = String.format("일치번호 개수는 0에서 %d 사이여야합니다", Numbers.REQUIRED_SIZE);

    public static Match of(int luckyCount, boolean bonusMatch) {
        return new Match(luckyCount, bonusMatch);
    }

    public Match {
        assertInRange(luckyCount);
    }

    private void assertInRange(int luckyCount) {
        if (luckyCount < 0 || luckyCount > Numbers.REQUIRED_SIZE) {
            throw new IllegalArgumentException(ERROR_RANGE);
        }
    }
}
