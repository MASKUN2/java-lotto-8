package lotto.hexagon.domain;

public record Drawn(Numbers lucky, Number bonus) {
    static final String ERROR_UNIQUE = "보너스 번호는 당첨번호와 달라야 합니다";

    public Drawn {
        assertUnique(lucky, bonus);
    }

    private void assertUnique(Numbers lucky, Number bonus) {
        if (lucky.has(bonus)) {
            throw new IllegalArgumentException(ERROR_UNIQUE);
        }
    }
}
