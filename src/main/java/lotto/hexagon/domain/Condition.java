package lotto.hexagon.domain;

public record Condition(int luckyCount, boolean bonusRequired) {
    static final String ERROR_RANGE = String.format("당첨번호 일치 범위는 %d 이상 %d 이하여야합니다", 0, Numbers.REQUIRED_SIZE);

    public static Condition of(int luckyCount, boolean bonusMatchRequired) {
        return new Condition(luckyCount, bonusMatchRequired);
    }

    public Condition {
        assertInRange(luckyCount);
    }

    private void assertInRange(int luckyCount) {
        if (luckyCount < 0 || luckyCount > Numbers.REQUIRED_SIZE) {
            throw new IllegalArgumentException(ERROR_RANGE);
        }
    }

    public boolean isMatch(Match match) {
        if (bonusRequired) {
            return match.luckyCount() >= luckyCount && match.bonusMatch();
        }
        return match.luckyCount() >= luckyCount;
    }
}
