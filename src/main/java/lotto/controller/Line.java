package lotto.controller;

public record Line(String value) {
    static final String ERROR_NULL = "입력 줄은 null이어서는 안됩니다";

    public Line {
        if (value == null) {
            throw new IllegalArgumentException(ERROR_NULL);
        }
    }
}
