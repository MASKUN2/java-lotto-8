package lotto.controller;

import java.util.function.Supplier;

public interface ExceptionHandler<E extends RuntimeException> {
    <T> T handle(Supplier<T> supplier);
}
